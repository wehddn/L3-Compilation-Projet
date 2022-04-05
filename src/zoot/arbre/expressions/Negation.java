package zoot.arbre.expressions;

import zoot.exceptions.AnalyseSemantiqueException;
import zoot.exceptions.TypeNonConcordantException;
import zoot.tds.Type;

/**
 * Cette classe implémente une operation negation
 * non exp
 *
 * @author Nicolas GRAFF
 * @version 3.0.0
 * @since 3.0.0
 * created on 04/04/2022
 */
public class Negation extends Expression{
    private final Expression exp;

    public Negation(Expression exp, int ligne, int colonne) {
        super(ligne, colonne);
        this.exp=exp;
    }

    @Override
    public void verifier() throws AnalyseSemantiqueException {
        exp.verifier();
        Type expType = exp.getType();
        if (expType!=Type.BOOLEEN)
            throw new TypeNonConcordantException(ligne, colonne,  "booleen <- " + expType);
    }

    @Override
    public String toMIPS() {
        return exp.toMIPS() + "# not\n\txor $v0, $v0, 1\n";
    }

    @Override
    public Type getType() {
        return Type.BOOLEEN;
    }

    @Override
    public String getCommentaire() {
        return "non " + exp.getCommentaire();
    }
}
