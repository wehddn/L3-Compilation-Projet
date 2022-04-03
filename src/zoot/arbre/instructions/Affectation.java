package zoot.arbre.instructions;

import zoot.arbre.ArbreAbstrait;
import zoot.arbre.expressions.Expression;
import zoot.arbre.expressions.Idf;
import zoot.exceptions.AnalyseSemantiqueException;
import zoot.exceptions.TypeNonConcordantException;
import zoot.mips.SnippetsMIPS;
import zoot.tds.Type;

/**
 * Cette classe implémente une opération d'affectation d'expression à une variable
 *
 * @author Elhadji Moussa FAYE
 * @author Nicolas GRAFF
 * @version 2.8.1
 * @since 1.0.0
 * created on 11/02/2022
 */
public class Affectation extends Instruction{
    private final Idf left;
    private final Expression right;

    public Affectation(Idf left, Expression right, int ligne, int colonne) {
        super(ligne, colonne);
        this.left = left;
        this.right = right;
    }

    /**
     * @see zoot.arbre.ArbreAbstrait
     */
    @Override
    public void verifier() throws AnalyseSemantiqueException {
        left.verifier();
        right.verifier();
        Type leftType = left.getType();
        Type expType = right.getType();
        if (!leftType.equals(expType)){
            throw new TypeNonConcordantException(ligne, colonne,  leftType + " <- " + expType);
        }
    }

    /**
     * @see ArbreAbstrait
     */
    @Override
    public String toMIPS() {
        return """
                # affectation %s = %s
                # évaluation expression
                %s
                # sauvegarde résultat expression dans pile
                %s
                %s
                # récupération position %s
                %s
                # récupération du résultat dans la pile
                %s
                %s
                # sauvegarde de la valeur dans la variable
                \tsw $t0, 0($v0)
                """.formatted(left.getCommentaire(),
                right.getCommentaire(),
                right.toMIPS(),
                SnippetsMIPS.reserverPlacePile(1),
                SnippetsMIPS.sauvegardeRegistreDansPile("$v0", 4),
                left.getCommentaire(),
                left.getPositionMIPS(),
                SnippetsMIPS.restaurerRegistreDepuisPile("$t0", 4),
                SnippetsMIPS.libererPlacePile(1)
                );
    }

    @Override
    public String toString() {
        return left + " = " + right;
    }
}
