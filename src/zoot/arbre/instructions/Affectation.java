package zoot.arbre.instructions;

import zoot.arbre.expressions.Expression;
import zoot.arbre.expressions.Idf;
import zoot.exceptions.AnalyseSemantiqueException;
import zoot.exceptions.TypeNonConcordantException;
import zoot.tds.Tds;
import zoot.tds.Type;

/**
 * Cette classe implémente une opération d'affectation d'expression à une variable
 *
 * @author Elhadji Moussa FAYE
 * @author Nicolas GRAFF
 * @version 2.6.4
 * @since 1.0.0
 * created on 11/02/2022
 */
public class Affectation extends Instruction{
    private final Idf idf;
    private final Expression exp;

    public Affectation(Idf idf, Expression exp, int noLigne, int noColonne) {
        super(noLigne, noColonne);
        this.idf = idf;
        this.exp = exp;
    }

    /**
     * @see zoot.arbre.ArbreAbstrait
     */
    @Override
    public void verifier() throws AnalyseSemantiqueException {
        idf.verifier();
        exp.verifier();
        Type idfType = idf.getType();
        Type expType = exp.getType();
        if (!idfType.equals(expType)){
            throw new TypeNonConcordantException(noLigne, noColonne,  idfType + " <- " + expType);
        }
    }

    /**
     * @see zoot.arbre.ArbreAbstrait
     */
    @Override
    public String toMIPS() {
        int idfDeplacement = -idf.getSymbole().getDeplacement();

        return "# "+ idf.getCommentaire() +" = "+ exp.getCommentaire() +"\n\t" +
                exp.toMIPS() +
                "\n\tsw $v0, " + idfDeplacement + "($s7)";
    }

    @Override
    public String toString() {
        return idf + " = " + exp;
    }
}
