package zoot.arbre.instructions;

import zoot.arbre.Fonction;
import zoot.arbre.expressions.Expression;
import zoot.exceptions.AnalyseSemantiqueException;
import zoot.exceptions.TypeNonConcordantException;
import zoot.mips.SnippetsMIPS;
import zoot.tds.Tds;
import zoot.tds.Type;

/**
 * Instruction pour retourner une expression
 *
 * @author Nicolas GRAFF
 * @version 2.8.1
 * @since 1.7.0
 * created on 08/03/2022
 */
public class Retourne extends Instruction{

    protected Expression exp ;
    private Fonction fonction;
    private int nbVarLocalesFonction = 0;

    public Retourne(Expression e, int ligne, int colonne) {
        super(ligne, colonne);
        exp = e;
    }

    @Override
    public void verifier() throws AnalyseSemantiqueException {
        exp.verifier();

        if (!fonction.getType().equals(exp.getType())) {
            throw new TypeNonConcordantException(getLigne(), getColonne(),  "fonction: " + fonction.getType() + ", retourne: " + exp.getType());
        }

        nbVarLocalesFonction = Tds.getInstance().getTailleZoneVar()/4;
    }

    public void setFonction(Fonction f) {
        this.fonction = f;
    }

    @Override
    public String toMIPS() {
        return """
                %s
                # libération pile des variables locales
                %s
                # retourne
                \tjr $ra""".formatted(exp.toMIPS(), SnippetsMIPS.libererPlacePile(nbVarLocalesFonction));
    }

    public Type getType() {
        return exp.getType();
    }

    public String toString() {
        return "retourne : "  + exp;
    }
}
