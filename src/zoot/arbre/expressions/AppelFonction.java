package zoot.arbre.expressions;

import zoot.exceptions.AnalyseSemantiqueException;
import zoot.exceptions.TypeNonConcordantException;
import zoot.mips.SnippetsMIPS;
import zoot.tds.*;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Classe représentant un appel de fonction
 *
 * @author Nicolas GRAFF
 * @version 2.8.0
 * @since 1.7.0
 * created on 08/03/2022
 */

public class AppelFonction extends Expression{

    private final EntreeFct entree;
    private final ArrayList<Expression> parametres;
    private SymboleFct symbole = null;

    public AppelFonction(EntreeFct i, int ligne, int colonne, Expression... parametres) {
        super(ligne, colonne);
        this.entree = i;
        this.parametres = new ArrayList<>();
        Collections.addAll(this.parametres, parametres);
    }

    @Override
    public void verifier() throws AnalyseSemantiqueException {
        ArrayList<Type> types = new ArrayList<>(parametres.size());
        for (Expression e : parametres) {
            e.verifier();
            types.add(e.getType());
        }

        entree.setTypeParametres(types.toArray(new Type[0]));
        this.symbole= (SymboleFct) Tds.getInstance().identifier(entree, noLigne, noColonne);
    }

    @Override
    public String toMIPS() {

        return """
                # appel de la fonction %s
                %s # reserver place entête (jusqu'a $s7)
                %s # empiler params
                %s # empiler ra
                %s # empiler chainage dynamique (s7 courant)
                \tjal %s
                %s # restaurer s7
                %s # restaurer ra
                %s # restaurer pile
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

    @Override
    public Type getType() {
        if (symbole == null)
            return Type.NONDEFINI;
        else
            return symbole.getType();
    }

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
