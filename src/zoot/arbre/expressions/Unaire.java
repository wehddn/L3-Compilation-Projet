package zoot.arbre.expressions;

/**
 * Description
 *
 * @author Elhadji Moussa FAYE
 * @version 3.5.0
 * @since 3.5.0
 * created on 05/04/2022
 */
public abstract class Unaire extends Expression {
    protected Expression expression;
    public Unaire(Expression expression, int ligne, int colonne) {
        super(ligne, colonne);
        this.expression = expression;
    }
}
