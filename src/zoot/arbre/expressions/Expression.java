package zoot.arbre.expressions;

import zoot.arbre.ArbreAbstrait;
import zoot.tds.Type;

/**
 * Représente une expression (élément de l'arbre abstrait)
 */
public abstract class Expression extends ArbreAbstrait {

    protected Expression(int ligne, int colonne) {
        super(ligne, colonne) ;
    }

    public abstract Type getType();

    public abstract String getCommentaire();
}
