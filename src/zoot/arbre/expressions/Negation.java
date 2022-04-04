package zoot.arbre.expressions;

import zoot.exceptions.AnalyseSemantiqueException;
import zoot.exceptions.TypeNonConcordantException;
import zoot.tds.Type;

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
