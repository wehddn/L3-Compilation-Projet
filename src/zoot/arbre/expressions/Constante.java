package zoot.arbre.expressions;

/**
 * Représente une constante dans l'arbre abstrait
 */
public abstract class Constante extends Expression {

    protected String cste ;
    
    protected Constante(String valeur, int ligne, int colonne) {
        super(ligne, colonne) ;
        cste = valeur ;
    }

    /**
     * @see zoot.arbre.ArbreAbstrait
     */
    @Override
    public void verifier() {

    }

    public String getCommentaire(){
        return cste ;
    }

    @Override
    public String toString() {
        return cste ;
    }

}
