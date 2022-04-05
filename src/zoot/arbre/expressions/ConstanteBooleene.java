package zoot.arbre.expressions;

import zoot.mips.SnippetsMIPS;
import zoot.tds.Type;

/**
 * Représente une constante booléene dans l'arbre abstrait
 *
 * @author Elhadji Moussa FAYE
 * @version 3.0.1
 * @since 1.0.0
 * created on 09/02/2022
 */
public class ConstanteBooleene extends Constante{

    /**
     *
     * @param valeur la valeur vrai ou faux
     * @param ligne la ligne de déclaration
     * @param colonne la colonne de déclaration
     */
    public ConstanteBooleene(String valeur, int ligne, int colonne) {
        super(valeur, ligne, colonne);
    }

    /**
     * @see zoot.arbre.ArbreAbstrait
     */
    @Override
    public String toMIPS() {
        String valeur = "0";
        if (cste.equals("vrai"))
            valeur = "1";
        return SnippetsMIPS.mettreValeurDansRegistre("$v0", valeur);
    }

    /**
     * Le type de la constante
     * @return Le type de la constante
     */
    public Type getType() {
        return Type.BOOLEEN;
    }


    @Override
    public String getCommentaire() {
        return this.cste;
    }

}
