package zoot.arbre.instructions;

import zoot.arbre.expressions.Expression;

/**
 * Instruction pour écrire une expression
 * @version 1.2.0
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
        // TODO utiliser indent à la place des \t
        return exp.toMIPS() +
                "\n\t# Ecriture\n" +
                "\tmove $a0, $v0\n" +
                "\tli $v0, 1\n" +
                "\tsyscall\n" +
                "\t# Saut de ligne\n" +
                "\tli $v0, 11\n" +
                "\tli $a0, 10\n" +
                "\tsyscall";
    }

    @Override
    public String toString() {
        return "ecrire : "  + exp;
    }
}
