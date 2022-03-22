package zoot.arbre.expressions;

import zoot.tds.Type;

/**
 * Represente une constant entière dans l'arbre abstrait
 * @version 1.3.1
 */
public class ConstanteEntiere extends Constante {

    public ConstanteEntiere(String texte, int n, int m) {
        super(texte, n, m) ;
    }

    /**
     * @see zoot.arbre.ArbreAbstrait
     */
    @Override
    public String toMIPS() {
        return "\tli $v0, " + this.cste;
    }

    @Override
    public Type getType() {
        return Type.ENTIER;
    }

    public String getValeur() { return this.cste; }
}
