package zoot.arbre.instructions;

import zoot.arbre.BlocDeFonction;
import zoot.arbre.expressions.Expression;
import zoot.exceptions.AnalyseSemantiqueException;

import java.util.ArrayList;

/**
 * Instruction pour retourner une expression
 *
 * @author Nicolas GRAFF
 * @version 1.7.0
 * @since 1.7.0
 * created on 08/03/2022
 */
public class Retourne extends Instruction{

    protected Expression exp ;

    public Retourne(Expression e, int n, int m) {
        super(n, m);
        exp = e;
    }

    @Override
    public void verifier() throws AnalyseSemantiqueException {
        exp.verifier();
    }

    @Override
    public String toMIPS() {
        return "\t" + exp.toMIPS() + "\n" + "\tjr $ra";
    }


    public String toString() {
        return "retourne : "  + exp;
    }
}
