package zoot.arbre.expressions;

/**
 * Représente une constante dans l'arbre abstrait
 */
public abstract class Constante extends Expression {

    protected String cste ;
    
    protected Constante(String texte, int n) {
        super(n) ;
        cste = texte ;
    }

    /**
     * @see zoot.arbre.ArbreAbstrait
     */

    @Override
    public String toString() {
        return cste ;
    }

}
