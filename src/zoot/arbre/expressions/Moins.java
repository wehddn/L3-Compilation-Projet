package zoot.arbre.expressions;

import zoot.exceptions.AnalyseSemantiqueException;
import zoot.exceptions.TypeNonConcordantException;
import zoot.tds.Type;

public class Moins extends Expression{
    private final Expression exp;

    public Moins(Expression exp, int ligne, int colonne) {
        super(ligne, colonne);
        this.exp=exp;
    }

    @Override
    public void verifier() throws AnalyseSemantiqueException {
        exp.verifier();
        Type expType = exp.getType();
        if (expType!=Type.ENTIER)
            throw new TypeNonConcordantException(ligne, colonne,  "booleen <- " + expType);
    }

    @Override
    public String toMIPS() {
        return exp.toMIPS() +
                "\n# moins\n" +
                "\tsubu $v0, $zero, $v0\n";
    }

    @Override
    public Type getType() {
        return Type.ENTIER;
    }

    @Override
    public String getCommentaire() {
        return "moins " + exp.getCommentaire();
    }
}
