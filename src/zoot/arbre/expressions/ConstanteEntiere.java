package zoot.arbre.expressions;

import zoot.mips.SnippetsMIPS;
import zoot.tds.Type;

/**
 * Represente une constant entière dans l'arbre abstrait
 * @version 3.0.1
 */
public class ConstanteEntiere extends Constante {

    /**
     *
     * @param valeur la valeur de la constante (toute valeur numérique pouvant être contenu dans un int)
     * @param ligne la ligne de déclaration
     * @param colonne la colonne de déclaration
     */
    public ConstanteEntiere(String valeur, int ligne, int colonne) {
        super(valeur, ligne, colonne) ;
    }

    /**
     * @see zoot.arbre.ArbreAbstrait
     */
    @Override
    public String toMIPS() {
        return SnippetsMIPS.mettreValeurDansRegistre("$v0", cste);
    }

    /**
     * @see zoot.arbre.expressions.Expression
     */
    @Override
    public Type getType() {
        return Type.ENTIER;
    }

    /**
     * @see zoot.arbre.expressions.Expression
     */
    @Override
    public String getCommentaire() {
        return this.cste;
    }
}
