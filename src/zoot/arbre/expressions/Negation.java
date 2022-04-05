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
 * @version 3.5.0
 * @since 3.0.0
 * created on 04/04/2022
 */
public class Negation extends Unaire{

    public Negation(Expression expression, int ligne, int colonne) {
        super(expression, ligne, colonne);
    }

    @Override
    public void verifier() throws AnalyseSemantiqueException {
        expression.verifier();
        Type expType = expression.getType();
        if (expType!=Type.BOOLEEN)
            throw new TypeNonConcordantException(ligne, colonne,  "booleen <- " + expType);
    }

    @Override
    public String toMIPS() {
        String sifaux = "sifaux__" + UUID.randomUUID().toString().replace("-", "");
        String finnot = "finnot__" + UUID.randomUUID().toString().replace("-", "");

        return expression.toMIPS() +
                "\n# not\n" +
                "\n\tbeq $v0, 0, " + sifaux + "\n" +
                "\n\tli $v0, 0\n" +
                "\tj " + finnot + "\n" +
                "\n" + sifaux + " :\n" +
                "\n\tli $v0, 1\n" +
                "\n" + finnot + " :\n";
    }

    @Override
    public Type getType() {
        return Type.BOOLEEN;
    }

    @Override
    public String getCommentaire() {
        return "non " + expression.getCommentaire();
    }
}
