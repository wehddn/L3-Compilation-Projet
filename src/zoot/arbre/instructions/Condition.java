package zoot.arbre.instructions;

import zoot.arbre.BlocDInstructions;
import zoot.arbre.expressions.Expression;
import zoot.exceptions.AnalyseSemantiqueException;
import zoot.exceptions.TypeNonConcordantException;
import zoot.tds.Type;

import java.util.UUID;

/**
 * Cette classe implémente une instruction conditionnelle
 * si ... alors ... sinon ... finsi
 *
 * @author Nicolas GRAFF
 * @version 3.0.0
 * @since 3.0.0
 * created on 04/04/2022
 */
public class Condition extends Instruction {
    private final Expression exp;
    private final BlocDInstructions biSi;
    private final BlocDInstructions biSinon;

    public Condition(Expression e, BlocDInstructions biSi, BlocDInstructions biSinon, int ligne, int colonne) {
        super(ligne, colonne);
        this.exp = e;
        this.biSi = biSi;
        this.biSinon = biSinon;
    }

    @Override
    public void verifier() throws AnalyseSemantiqueException {
        exp.verifier();
        Type expType = exp.getType();
        if (biSi != null)
            biSi.verifier();
        biSinon.verifier();
        if (expType != Type.BOOLEEN){
            throw new TypeNonConcordantException(ligne, colonne,  "booleen <- " + expType);
        }
    }

    @Override
    public String toMIPS() {
        String sinon = "sinon__" + UUID.randomUUID().toString().replace("-", "");
        String finsi = "finsi__" + UUID.randomUUID().toString().replace("-", "");
        StringBuilder sb = new StringBuilder();
        sb.append("# condition\n");
        sb.append(exp.toMIPS());
        sb.append("\n\tbeq $v0, 0, ").append(sinon).append("\n");
        if (biSi != null)
            sb.append(biSi.toMIPS());
        sb.append("\tj ").append(finsi).append("\n");
        sb.append("\n").append(sinon).append(" :\n");
        sb.append(biSinon.toMIPS());
        sb.append("\n").append(finsi).append(" :\n");

        return sb.toString();
    }
}
