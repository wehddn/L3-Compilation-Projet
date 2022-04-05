package zoot.arbre.expressions;

import zoot.exceptions.AnalyseSemantiqueException;
import zoot.exceptions.TypeNonConcordantException;
import zoot.tds.Type;

/**
 * Cette classe implémente une operation moins
 * - exp
 *
 * @author Nicolas GRAFF
 * @version 3.5.0
 * @since 3.0.0
 * created on 04/04/2022
 */
public class Moins extends Unaire{

    public Moins(Expression expression, int ligne, int colonne) {
        super(expression, ligne, colonne);
    }

    @Override
    public void verifier() throws AnalyseSemantiqueException {
        expression.verifier();
        Type expType = expression.getType();
        if (expType!=Type.ENTIER)
            throw new TypeNonConcordantException(ligne, colonne,  Type.ENTIER + " <- " + expType);
    }

    @Override
    public String toMIPS() {
        return expression.toMIPS() +
                "\n# moins\n" +
                "\tsubu $v0, $zero, $v0\n";
    }

    @Override
    public Type getType() {
        return Type.ENTIER;
    }

    @Override
    public String getCommentaire() {
        return "- " + expression.getCommentaire();
    }
}
