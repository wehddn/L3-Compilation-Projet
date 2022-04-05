package zoot.arbre.expressions;

import zoot.arbre.ArbreAbstrait;
import zoot.tds.Type;

/**
 * Représente une expression (élément de l'arbre abstrait)
 */
public abstract class Expression extends ArbreAbstrait {

    /**
     *
     * @param ligne la ligne de déclaration
     * @param colonne la colonne de déclaration
     */
    protected Expression(int ligne, int colonne) {
        super(ligne, colonne) ;
    }

    /**
     * Donne le type de l'expression
     * @return le type de l'expression
     */
    public abstract Type getType();

    /**
     * Donne le code MIPS qui évalue l'expression et met le résultat dans $v0
     * @return le code MIPS qui évalue l'expression et met le résultat dans $v0
     */
    @Override
    public abstract String toMIPS();

    /**
     * Retourne un string utilisé pour les commentaires
     * @return un string utilisé pour les commentaires
     */
    public abstract String getCommentaire();
}
