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
 * @version 3.5.0
 * @since 3.0.0
 * created on 04/04/2022
 */
public class Condition extends Instruction {
    private final Expression exp;
    private BlocDInstructions biSi;
    private BlocDInstructions biSinon;

    public Condition(Expression e, int ligne, int colonne) {
        super(ligne, colonne);
        this.exp = e;
    }

    @Override
    public void verifier() throws AnalyseSemantiqueException {
        exp.verifier();
        Type expType = exp.getType();
        if (biSi != null)
            biSi.verifier();
        if (biSinon != null)
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
        sb.append(exp.toMIPS()).append("\n");
        if (biSinon != null)
            sb.append("\n\tbeq $v0, 0, ").append(sinon).append("\n");
        else
            sb.append("\n\tbeq $v0, 0, ").append(finsi).append("\n");
        if (biSi != null)
            sb.append(biSi.toMIPS());
        sb.append("\n\tj ").append(finsi).append("\n");
        if (biSinon != null){
            sb.append("\n").append(sinon).append(" :\n");
            sb.append(biSinon.toMIPS());
        }
        sb.append("\n").append(finsi).append(" :\n");

        return sb.toString();
    }

    public void setBiSi(BlocDInstructions biSi){
        this.biSi = biSi;
    }

    public void setBiSinon(BlocDInstructions biSinon){
        this.biSinon = biSinon;
    }
}
