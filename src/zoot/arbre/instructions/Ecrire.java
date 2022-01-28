package zoot.arbre.instructions;

import zoot.arbre.expressions.Expression;

public class Ecrire extends Instruction {

    protected Expression exp ;

    public Ecrire (Expression e, int n) {
        super(n) ;
        exp = e ;
    }

    @Override
    public void verifier() {
        throw new UnsupportedOperationException("fonction verfier non définie ") ;
    }

    @Override
    public String toMIPS() {
        StringBuilder sb = new StringBuilder() ;
        sb.append(exp.toMIPS());
        sb.append("\t# Ecriture\n" +
                "\tmove $a0, $v0\n" +
                "\tli $v0, 1\n" +
                "\tsyscall\n");

        return sb.toString();
    }

}
