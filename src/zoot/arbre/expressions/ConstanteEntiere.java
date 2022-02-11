package zoot.arbre.expressions;

import zoot.tds.Type;

/**
 * Represente une constant entière dans l'arbre abstrait
 * @version 1.2.2
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
        return "\t# Constante entiére\n" +
                "\tli $v0, " + this.cste;
    }

    @Override
    public String toString() {
        return cste;
    }

    @Override
    public Type getType() {
        return Type.ENTIER;
    }
}
