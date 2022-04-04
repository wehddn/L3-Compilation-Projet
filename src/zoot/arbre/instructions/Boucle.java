package zoot.arbre.instructions;

import zoot.arbre.BlocDInstructions;
import zoot.arbre.expressions.Expression;
import zoot.exceptions.AnalyseSemantiqueException;
import zoot.exceptions.TypeNonConcordantException;
import zoot.tds.Type;

import java.util.UUID;

public class Boucle extends Instruction{
    private final Expression exp;
    private final BlocDInstructions bi;

    public Boucle(Expression e, BlocDInstructions bi, int ligne, int colonne) {
        super(ligne, colonne);
        this.exp = e;
        this.bi = bi;
    }

    @Override
    public void verifier() throws AnalyseSemantiqueException {
        exp.verifier();
        Type expType = exp.getType();
        bi.verifier();
        if (expType != Type.BOOLEEN){
            throw new TypeNonConcordantException(ligne, colonne,  "booleen <- " + expType);
        }
    }

    @Override
    public String toMIPS() {
        String repeter = "repeter__" + UUID.randomUUID().toString().replace("-", "");
        String jusqua = "jusqua__" + UUID.randomUUID().toString().replace("-", "");
        StringBuilder sb = new StringBuilder();
        sb.append("# boucle\n");
        sb.append("\n").append(repeter).append(" :\n");
        sb.append(bi.toMIPS());
        sb.append("\n").append(jusqua).append(" :\n");
        sb.append(exp.toMIPS()).append("\n");
        sb.append("\tbeq $v0, 1, ").append(repeter).append("\n");
        return sb.toString();
    }
}
