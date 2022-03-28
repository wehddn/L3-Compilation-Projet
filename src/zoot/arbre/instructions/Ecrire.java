package zoot.arbre.instructions;

import zoot.arbre.expressions.Expression;
import zoot.mips.SnippetsMIPS;

/**
 * Instruction pour écrire une expression
 * @version 1.7.1
 */
public class Ecrire extends Instruction {

    /**
     * L'instruction à écrire
     */
    protected Expression exp ;

    /**
     * @param e l'expression à écrire
     * @param n le numéro de ligne dans le code zoot de l'instruction écrire
     */
    public Ecrire (Expression e, int n, int m) {
        super(n, m) ;
        exp = e ;
    }

    /**
     * @see zoot.arbre.ArbreAbstrait
     */
    @Override
    public void verifier() {
        exp.verifier();
    }

    /**
     * @see zoot.arbre.ArbreAbstrait
     */
    @Override
    public String toMIPS() {
        StringBuilder sb = new StringBuilder();
        sb.append(exp.toMIPS());
        sb.append(SnippetsMIPS.appelEcriture(exp.getType()));

        return sb.toString();
    }

    @Override
    public String toString() {
        return "ecrire : "  + exp;
    }
}
