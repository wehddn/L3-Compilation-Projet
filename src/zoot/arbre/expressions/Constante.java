package zoot.arbre.expressions;

/**
 * Représente une constante dans l'arbre abstrait
 */
public abstract class Constante extends Expression {

    /**
     * La valeur de la constante
     */
    protected String cste ;

    /**
     *
     * @param valeur la valeur de la constante
     * @param ligne la ligne de déclaration
     * @param colonne la colonne de déclaration
     */
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

    /**
     * Donne la valeur de la constante
     * @return la valeur de la constante
     */
    public String getCommentaire(){
        return cste ;
    }

    @Override
    public String toString() {
        return cste ;
    }

}
