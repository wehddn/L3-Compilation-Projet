package zoot.arbre.instructions;

import zoot.arbre.Fonction;
import zoot.arbre.expressions.Expression;
import zoot.exceptions.AnalyseSemantiqueException;
import zoot.exceptions.TypeNonConcordantException;
import zoot.tds.Tds;
import zoot.tds.Type;

/**
 * Instruction pour retourner une expression
 *
 * @author Nicolas GRAFF
 * @version 2.6.0
 * @since 1.7.0
 * created on 08/03/2022
 */
public class Retourne extends Instruction{

    protected Expression exp ;
    private Fonction fonction;
    private int taillePileFonction = 0;

    public Retourne(Expression e, int n, int m) {
        super(n, m);
        exp = e;
    }

    @Override
    public void verifier() throws AnalyseSemantiqueException {
        exp.verifier();

        if (!fonction.getType().equals(exp.getType())) {
            throw new TypeNonConcordantException(getNoLigne(), getNoColonne(),  "fonction: " + fonction.getType() + ", retourne: " + exp.getType());
        }

        taillePileFonction = Tds.getInstance().getTailleZoneVar()+Tds.getInstance().getTailleZonePar();
    }

    public void setFonction(Fonction f) {
        this.fonction = f;
    }

    @Override
    public String toMIPS() {
        StringBuilder sb = new StringBuilder();
        // On execute le code de l'expression qui mets le résultat dans $v0
        sb.append(exp.toMIPS()).append('\n');
        // On met dans $s7 l'ancien $s7 (la fonction appelante)
        sb.append("# Préparation retour\n\tlw $s7, +8($s7)");
        // Récupération de l'adresse de retour
        sb.append("\n\tlw $ra, +12($s7)\n");
        // On enlève l'environnement de la pile
        sb.append("\taddi $sp, $sp, ").append(taillePileFonction).append('\n');
        // Retour à la fonction appelante
        sb.append("# Retour\n\tjr $ra");
        return sb.toString();
    }

    public Type getType() {
        return exp.getType();
    }

    public String toString() {
        return "retourne : "  + exp;
    }
}
