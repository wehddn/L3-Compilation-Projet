package zoot.arbre.expressions;

import zoot.exceptions.AnalyseSemantiqueException;
import zoot.mips.SnippetsMIPS;
import zoot.tds.*;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Classe représentant un appel de fonction dans l'arbre abstrait
 *
 * @author Nicolas GRAFF
 * @version 3.0.1
 * @since 1.7.0
 * created on 08/03/2022
 */

public class AppelFonction extends Expression{

    /**
     * L'entrée de la fonction
     */
    private final EntreeFct entree;
    /**
     * Le symbole de la fonction (récupérée lors de l'analyse sémantique si l'entrée existe dans la TDS)
     */
    private SymboleFct symbole = null;
    /**
     * La liste des paramètres de l'appel de fonction
     */
    private final ArrayList<Expression> parametres;

    /**
     *
     * @param entreeFct L'entrée de la fonction
     * @param ligne la ligne de l'appel
     * @param colonne la colonne de l'appel
     * @param parametres les paramètres donnés lors de l'appel
     */
    public AppelFonction(EntreeFct entreeFct, int ligne, int colonne, Expression... parametres) {
        super(ligne, colonne);
        this.entree = entreeFct;
        this.parametres = new ArrayList<>();
        Collections.addAll(this.parametres, parametres);
    }

    /**
     * Fait l'analyse sémantique :
     * La fonction doit exister dans la TDS (avec les paramètres de même type et dans le même ordre)
     * @throws AnalyseSemantiqueException lorsqu'il y a une erreur sémantique
     */
    @Override
    public void verifier() throws AnalyseSemantiqueException {
        ArrayList<Type> types = new ArrayList<>(parametres.size());
        for (Expression e : parametres) {
            e.verifier();
            types.add(e.getType());
        }

        entree.setTypeParametres(types.toArray(new Type[0]));
        this.symbole= (SymboleFct) Tds.getInstance().identifier(entree, ligne, colonne);
    }

    /**
     * Donne le code MIPS pour l'appel de la fonction, doit être effectué après l'analyse sémantique
     * @return le code MIPS pour l'appel de la fonction
     */
    @Override
    public String toMIPS() {

        return """
                # appel de la fonction %s
                # reserver place entête (jusqu'a $s7)
                %s
                # empiler paramètres
                %s
                # empiler ra
                %s
                # empiler chainage dynamique (s7 courant)
                %s
                \tjal %s
                # restauration s7
                %s
                # restauration ra
                %s
                # restauration état pile
                %s
                """.formatted(getCommentaire(),
                SnippetsMIPS.reserverPlacePile(3 + parametres.size()),
                SnippetsMIPS.sauvegarderParametres(4 * 4 ,parametres.toArray(new Expression[0])),
                SnippetsMIPS.sauvegardeAdresseRetourAvantAppel(3 * 4),
                SnippetsMIPS.sauvegardeRegistreDansPile("$s7", 2 * 4),
                symbole.getEtiquette(),
                SnippetsMIPS.restaurerRegistreDepuisPile("$s7", 2 * 4),
                SnippetsMIPS.restaurationAdresseRetourApresAppel(3 * 4),
                SnippetsMIPS.libererPlacePile(3 + parametres.size()));
    }

    /**
     * Donne le type de retour de l'appel de fonction
     * @return le type de retour de l'appel de fonction
     */
    @Override
    public Type getType() {
        if (symbole == null)
            return Type.NONDEFINI;
        else
            return symbole.getType();
    }

    /**
     * Donne le nom de la fonction
     * @return le nom de la fonction
     */
    @Override
    public String getCommentaire() {
        return entree.getNom();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < parametres.size(); i++) {
            sb.append(parametres.get(i));
            if (i < (parametres.size()-1))
                sb.append(", ");
        }
        return entree.getNom()+ "( " + sb + " )";
    }
}
