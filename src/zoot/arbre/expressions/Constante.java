package zoot.arbre.expressions;

/**
 * Représente une constante dans l'arbre abstrait
 */
public abstract class Constante extends Expression {

    protected String cste ;
    
    protected Constante(String texte, int n, int m) {
        super(n, m) ;
        cste = texte ;
    }

    /**
     * @see zoot.arbre.ArbreAbstrait
     */
    @Override
    public void verifier() {

    }

    @Override
    public String toString() {
        return cste ;
    }

}
