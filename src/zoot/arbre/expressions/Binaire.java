package zoot.arbre.expressions;

/**
 * Classe représentant les opérateurs binaire dans l'arbre abstrait
 *
 * @author Elhadji Moussa FAYE
 * @version 3.1.0
 * @since 3.1.0
 * created on 05/04/2022
 */
public abstract class Binaire extends Expression {
    /**
     * L'opérande gauche
     */
    protected Expression gauche;
    /**
     * L'opérande droite
     */
    protected Expression droite;

    /**
     *
     * @param gauche l'opérande gauche
     * @param droite l'opérande droite
     * @param ligne la ligne de déclaration de l'expression
     * @param colonne la colonne de déclaration de l'expression
     */
    public Binaire(Expression gauche, Expression droite, int ligne, int colonne) {
        super(ligne, colonne);
        this.gauche = gauche;
        this.droite = droite;
    }


}
