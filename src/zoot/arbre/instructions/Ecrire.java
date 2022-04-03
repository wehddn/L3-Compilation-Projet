package zoot.arbre.instructions;

import zoot.arbre.expressions.Expression;
import zoot.mips.SnippetsMIPS;

/**
 * Instruction pour écrire une expression
 * @version 2.8.1
 */
public class Ecrire extends Instruction {

    /**
     * L'instruction à écrire
     */
    protected Expression exp ;

    /**
     * @param e l'expression à écrire
     * @param ligne le numéro de ligne dans le code zoot de l'instruction écrire
     */
    public Ecrire (Expression e, int ligne, int colonne) {
        super(ligne, colonne) ;
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

        return exp.toMIPS() + "\n" +
               SnippetsMIPS.appelEcriture(exp.getType()) + "\n";
    }

    @Override
    public String toString() {
        return "ecrire : "  + exp;
    }
}
