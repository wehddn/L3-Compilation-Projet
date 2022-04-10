package zoot.arbre.instructions;

import zoot.arbre.BlocDInstructions;
import zoot.arbre.Fonction;
import zoot.arbre.expressions.Expression;
import zoot.exceptions.AnalyseSemantiqueException;
import zoot.exceptions.TypeNonConcordantException;
import zoot.tds.Type;

import java.util.UUID;

/**
 * Cette classe implémente une bocle
 * repeter ... jusqua ... finrepeter
 *
 * @author Nicolas GRAFF
 * @version 3.7.0
 * @since 3.0.0
 * created on 04/04/2022
 */
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
        bi.verifier();
        exp.verifier();
        Type expType = exp.getType();
        if (expType != Type.BOOLEEN){
            throw new TypeNonConcordantException(ligne, colonne,  "booleen <- " + expType);
        }
    }

    @Override
    public String toMIPS() {
        String repeter = "repeter__" + UUID.randomUUID().toString().replace("-", "");
        String jusqua = "jusqua__" + UUID.randomUUID().toString().replace("-", "");
        String finrepeter = "finrepeter__" + UUID.randomUUID().toString().replace("-", "");
        return "# boucle\n\n%s :\n%s\n%s :\n%s\n\tbeq $v0, 0, %s\n\tj %s\n\n%s :\n".formatted(repeter, bi.toMIPS(), jusqua, exp.toMIPS(), finrepeter, repeter, finrepeter);
    }

    public boolean setFonction(Fonction f) {
        return bi.setFonction(f);
    }
}
