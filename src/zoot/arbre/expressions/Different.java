package zoot.arbre.expressions;

import zoot.exceptions.AnalyseSemantiqueException;
import zoot.exceptions.GestionnaireErreursSemantiques;
import zoot.exceptions.TypeNonConcordantException;
import zoot.mips.SnippetsMIPS;
import zoot.tds.Type;

/**
 * Représente une comparaison de difference dans l'arbre abstrait
 *
 * @author Nicolas GRAFF
 * @version 3.5.0
 * @since 3.5.0
 * created on 05/04/2022
 */
public class Different extends Binaire {
    /**
     *
     * @param gauche l'opérande gauche
     * @param droite l'opérande droite
     * @param ligne la ligne de déclaration de l'expression
     * @param colonne la colonne de déclaration de l'expression
     */
    public Different(Expression gauche, Expression droite, int ligne, int colonne) {
        super(gauche, droite, ligne, colonne);
    }

    /**
     * @see zoot.arbre.ArbreAbstrait
     * @throws AnalyseSemantiqueException lorsque les opérandes ne sont pas du même type
     */
    @Override
    public void verifier() throws AnalyseSemantiqueException {
        gauche.verifier();
        droite.verifier();
        Type gt = gauche.getType();
        Type dt = droite.getType();
        if (!gt.equals(dt)) {
            GestionnaireErreursSemantiques.getInstance().ajouterErreurSemantique(
             new TypeNonConcordantException(ligne, colonne,  gt + " != " + dt));
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
        // On va gerer deux cas :
        // 1er cas ce sont des entiers, dans ce cas on compare bêtement les valeurs
        // 2eme cas ce sont des booléens, dans ce cas on vérifie que soit a et b sont
        // tous les deux nuls, soit ils sont tous supérieur à 0
        String resultat = "";

        if (gauche.getType().equals(Type.ENTIER)) {
            resultat = """
                    \tsne $v0, $v0, $t0""";
        }else if (gauche.getType().equals(Type.BOOLEEN)){
            resultat = """
                    \tseq $v0, $v0, $zero
                    \tseq $t0, $t0, $zero
                    \txor $v0, $v0, $t0
                    \tsne $v0, $v0, $zero""";
        }

        return """
                # évaluation de la partie gauche
                %s
                # empilement du résultat
                %s
                # évaluation la partie droite
                %s
                # dépile le resultat et le stock dans t0
                %s
                # comparaison v0 et t0 et mettre dans v0
                %s""".formatted(gauche.toMIPS(),
                SnippetsMIPS.empilerRegistre("$v0"),
                droite.toMIPS(),
                SnippetsMIPS.depilerVersRegistre("$t0"),
                resultat);
    }

    /**
     * @see Expression
     */
    @Override
    public String getCommentaire() {
        return gauche.getCommentaire() + " != " + droite.getCommentaire();
    }
}
