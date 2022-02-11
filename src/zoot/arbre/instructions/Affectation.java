package zoot.arbre.instructions;

import zoot.arbre.expressions.Expression;
import zoot.arbre.expressions.Idf;

/**
 * Description
 *
 * @author Elhadji Moussa FAYE
 * @version 1.0.2
 * @since 1.0.0
 * created on 09/02/2022
 */
public class Affectation extends Instruction{
    private Idf idf;
    private Expression exp;

    public Affectation(Idf idf, Expression exp, int noLigne) {
        super(noLigne);
        this.idf = idf;
        this.exp = exp;
    }

    /**
     * @see zoot.arbre.ArbreAbstrait
     */
    @Override
    public void verifier() {

    }

    /**
     * @see zoot.arbre.ArbreAbstrait
     */
    @Override
    public String toMIPS() {
        return null;
    }

    @Override
    public String toString() {
        return idf + " = " + exp;
    }
}
