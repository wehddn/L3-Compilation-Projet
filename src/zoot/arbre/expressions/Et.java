package zoot.arbre.expressions;

import zoot.exceptions.AnalyseSemantiqueException;
import zoot.exceptions.TypeNonConcordantException;
import zoot.mips.SnippetsMIPS;
import zoot.tds.Type;

/**
 * Représente le "et" dans l'arbre abstrait
 *
 * @author Elhadji Moussa FAYE
 * @version 3.4.0
 * @since 3.4.0
 * created on 05/04/2022
 */
public class Et extends Binaire {
    public Et(Expression gauche, Expression droite, int ligne, int colonne) {
        super(gauche, droite, ligne, colonne);
    }

    /**
     * @see zoot.arbre.ArbreAbstrait
     * @throws AnalyseSemantiqueException lorsque les opérandes ne sont pas du même type ou que l'une des
     * opérandes n'est pas de type booleen
     */
    @Override
    public void verifier() throws AnalyseSemantiqueException {
        gauche.verifier();
        droite.verifier();
        Type gt = gauche.getType();
        Type dt = droite.getType();
        if (!gt.equals(Type.BOOLEEN) || !gt.equals(dt)) {
            throw new TypeNonConcordantException(ligne, colonne,  gt + " et " + dt);
        }
    }

    /**
     * @see zoot.arbre.expressions.Expression
     */
    @Override
    public Type getType() {
        return Type.BOOLEEN;
    }

    /**
     * @see zoot.arbre.ArbreAbstrait
     */
    @Override
    public String toMIPS() {
        return """
                # évaluation de la partie gauche
                %s
                # empilement du résultat
                %s
                # évaluation la partie droite
                %s
                # dépile le résultat et le stock dans t0
                %s
                # comparaison v0 et t0 et mettre dans v0
                \tand $v0, $v0, $t0""".formatted(gauche.toMIPS(),
                SnippetsMIPS.empilerRegistre("$v0"),
                droite.toMIPS(),
                SnippetsMIPS.depilerVersRegistre("$t0"));
    }

    /**
     * @see zoot.arbre.expressions.Expression
     */
    @Override
    public String getCommentaire() {
        return gauche.getCommentaire() + " et " + droite.getCommentaire();
    }
}
