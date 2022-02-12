package zoot.arbre.expressions;

import zoot.arbre.ArbreAbstrait;
import zoot.tds.Type;

/**
 * Représente une expression (élément de l'arbre abstrait)
 */
public abstract class Expression extends ArbreAbstrait {

    protected Expression(int n, int m) {
        super(n, m) ;
    }

    public abstract Type getType();

    public abstract String getValeur();
}
