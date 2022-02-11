package zoot.arbre.expressions;

/**
 * Represente une constant entière dans l'arbre abstrait
 * @version 1.0.2
 */
public class ConstanteEntiere extends Constante {

    public ConstanteEntiere(String texte, int n) {
        super(texte, n) ;
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
    public String getType() {
        return "constante";
    }
}
