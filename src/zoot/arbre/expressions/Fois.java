package zoot.arbre.expressions;

import zoot.exceptions.AnalyseSemantiqueException;
import zoot.exceptions.TypeNonConcordantException;
import zoot.mips.SnippetsMIPS;
import zoot.tds.Type;

/**
 * Classe représentant une multiplication dans l'arbre abstrait
 *
 * @author Nicolas GRAFF
 * @version 3.1.0
 * @since 3.1.0
 * created on 05/04/2022
 */
public class Fois extends Binaire{
    /**
     *
     * @param gauche  l'opérande gauche
     * @param droite  l'opérande droite
     * @param ligne   la ligne de déclaration de l'expression
     * @param colonne la colonne de déclaration de l'expression
     */
    public Fois(Expression gauche, Expression droite, int ligne, int colonne) {
        super(gauche, droite, ligne, colonne);
    }

    /**
     * @see zoot.arbre.ArbreAbstrait
     * @throws AnalyseSemantiqueException lorsque les opérandes ne sont pas du même type ou que l'une des
     * opérandes n'est pas de type entier
     */
    @Override
    public void verifier() throws AnalyseSemantiqueException {
        gauche.verifier();
        droite.verifier();
        Type gt = gauche.getType();
        Type dt = droite.getType();
        if (!Type.ENTIER.equals(gt)) {
            throw new TypeNonConcordantException(ligne, colonne,  Type.ENTIER + " <- " + gt);
        }

        if (!Type.ENTIER.equals(dt)) {
            throw new TypeNonConcordantException(ligne, colonne,  Type.ENTIER + " <- " + dt);
        }
    }

    /**
     * @see zoot.arbre.expressions.Expression
     */
    @Override
    public Type getType() {
        return Type.ENTIER;
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
                # dépile le resultat et le stock dans t0
                %s
                # multiplier v0 et t0 et mettre dans v0
                %s""".formatted(gauche.toMIPS(),
                SnippetsMIPS.empilerRegistre("$v0"),
                droite.toMIPS(),
                SnippetsMIPS.depilerVersRegistre("$t0"),
                SnippetsMIPS.multiplicationRegistre("$v0", "$v0", "$t0"));
    }

    @Override
    public String getCommentaire() {
        return gauche.getCommentaire() + " * " + droite.getCommentaire();
    }
}
