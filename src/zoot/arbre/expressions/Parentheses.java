package zoot.arbre.expressions;

import zoot.exceptions.AnalyseSemantiqueException;
import zoot.tds.Type;

/**
 * Description
 *
 * @author Elhadji Moussa FAYE
 * @version 3.5.0
 * @since 3.5.0
 * created on 05/04/2022
 */
public class Parentheses extends Unaire {
    public Parentheses(Expression expression, int ligne, int colonne) {
        super(expression, ligne, colonne);
    }

    @Override
    public void verifier() throws AnalyseSemantiqueException {
        expression.verifier();
    }

    @Override
    public Type getType() {
        return expression.getType();
    }

    @Override
    public String toMIPS() {
        return expression.toMIPS();
    }

    @Override
    public String getCommentaire() {
        return "( " + expression.getCommentaire() + " )";
    }
}
