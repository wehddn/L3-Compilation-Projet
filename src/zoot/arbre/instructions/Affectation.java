package zoot.arbre.instructions;

import zoot.arbre.expressions.Expression;
import zoot.arbre.expressions.Idf;
import zoot.exceptions.NonConcordanceDesTypes;

/**
 * Cette classe implémente une opération d'affectation d'expression à une variable
 *
 * @authors Elhadji Moussa FAYE, Nicolas GRAFF
 * @version 1.1.0
 * @since 1.0.0
 * created on 11/02/2022
 */
public class Affectation extends Instruction{
    private Idf idf;
    private Expression exp;

    public Affectation(Idf idf, Expression exp, int noLigne) {
        super(noLigne);
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
        String type_idf = "";
        String type_exp = "";
            type_idf = idf.getType();
            type_exp = exp.getType();
        if (!(type_idf.equals(type_exp) || (type_idf.equals("entier") && type_exp.equals("constante")))){
            throw new NonConcordanceDesTypes(type_idf, type_exp);
        }
    }

    /**
     * @see zoot.arbre.ArbreAbstrait
     */
    @Override
    public String toMIPS() {
        return null;
    }

    @Override
    public String toString() {
        return idf + " = " + exp;
    }
}
