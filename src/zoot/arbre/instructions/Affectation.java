package zoot.arbre.instructions;

import zoot.arbre.expressions.Expression;
import zoot.arbre.expressions.Idf;
import zoot.exceptions.TypeNonConcordantException;
import zoot.tds.Tds;
import zoot.tds.Type;

/**
 * Cette classe implémente une opération d'affectation d'expression à une variable
 *
 * @author Elhadji Moussa FAYE
 * @author Nicolas GRAFF
 * @version 1.3.0
 * @since 1.0.0
 * created on 11/02/2022
 */
public class Affectation extends Instruction{
    private Idf idf;
    private Expression exp;

    public Affectation(Idf idf, Expression exp, int noLigne, int noColonne) {
        super(noLigne, noColonne);
        this.idf = idf;
        this.exp = exp;
    }

    /**
     * @see zoot.arbre.ArbreAbstrait
     */
    @Override
    public void verifier() {
        idf.verifier();
        exp.verifier();
        Type idfType = idf.getType();
        Type expType = exp.getType();
        if (idf.getType() != exp.getType()){
            throw new TypeNonConcordantException(noLigne, noColonne,  idfType.toString() + " <- " + expType.toString());
        }
    }

    /**
     * @see zoot.arbre.ArbreAbstrait
     */
    @Override
    public String toMIPS() {
        int idfDeplacement = -idf.getSymbole().getDeplacement();

        return "# "+ idf.getNom() +" = "+ exp.getValeur() +"\n" +
                exp.toMIPS() + "\n" +
                "\tsw $v0, " + idfDeplacement + "($s7)\n";
    }

    @Override
    public String toString() {
        return idf + " = " + exp;
    }
}
