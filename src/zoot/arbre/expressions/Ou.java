package zoot.arbre.expressions;

import zoot.exceptions.AnalyseSemantiqueException;
import zoot.exceptions.TypeNonConcordantException;
import zoot.mips.SnippetsMIPS;
import zoot.tds.Type;

/**
 * Représente le "ou" dans l'arbre abstrait
 *
 * @author Nicolas GRAFF
 * @version 3.6.0
 * @since 3.6.0
 * created on 05/04/2022
 */
public class Ou extends Binaire {
    public Ou(Expression gauche, Expression droite, int ligne, int colonne) {
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
            throw new TypeNonConcordantException(ligne, colonne,  gt + " ou " + dt);
        }
    }

    /**
     * @see Expression
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
                \tor $v0, $v0, $t0""".formatted(gauche.toMIPS(),
                SnippetsMIPS.empilerRegistre("$v0"),
                droite.toMIPS(),
                SnippetsMIPS.depilerVersRegistre("$t0"));
    }

    /**
     * @see Expression
     */
    @Override
    public String getCommentaire() {
        return gauche.getCommentaire() + " ou " + droite.getCommentaire();
    }
}
