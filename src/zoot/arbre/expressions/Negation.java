package zoot.arbre.expressions;

import zoot.exceptions.AnalyseSemantiqueException;
import zoot.exceptions.TypeNonConcordantException;
import zoot.tds.Type;

import java.util.UUID;

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
        String sifaux = "sifaux__" + UUID.randomUUID().toString().replace("-", "");
        String finnot = "finnot__" + UUID.randomUUID().toString().replace("-", "");
        StringBuilder sb = new StringBuilder();
        sb.append(exp.toMIPS());
        sb.append("\n# not\n");
        sb.append("\n\tbeq $v0, 0, ").append(sifaux).append("\n");
        sb.append("\n\tli $v0, 0\n");
        sb.append("\tj ").append(finnot).append("\n");
        sb.append("\n").append(sifaux).append(" :\n");
        sb.append("\n\tli $v0, 1\n");
        sb.append("\n").append(finnot).append(" :\n");

        return sb.toString();
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
