package zoot.arbre;

import zoot.arbre.instructions.Instruction;
import zoot.exceptions.AnalyseSemantiqueException;
import zoot.tds.Tds;

import java.util.ArrayList;

/**
 * Classe qui représente un bloc d'instruction dans l'arbre abstrait
 * 21 novembre 2018
 *
 * @author brigitte wrobel-dautcourt
 * @version 1.6.0
 */

public class BlocDInstructions extends ArbreAbstrait {

    public ArrayList<Instruction> programme ;
    protected int taillePile = 0;

    public BlocDInstructions(int n, int m) {
        super(n, m) ;
        programme = new ArrayList<>() ;
    }

    /**
     * Ajoute une instruction dans le bloc d'instruction
     * @param i L'instruction à ajouter
     */
    public void ajouter(Instruction i) {
        programme.add(i) ;
    }

    /**
     * @see ArbreAbstrait
     */
    @Override
    public void verifier() {
        StringBuilder sb = new StringBuilder();
        boolean exception = false;
        for (Instruction i : programme) {
            try {
                i.verifier();
            } catch (AnalyseSemantiqueException as) {
                sb.append(as.getMessage()).append("\n");
                exception = true;
            }
        }
        taillePile = Tds.getInstance().getTailleZoneVariables();
        if (exception)
            throw new AnalyseSemantiqueException(sb.toString());
    }

    /**
     * @see ArbreAbstrait
     * @return le code mips du bloc d'instruction
     */
    @Override
    public String toMIPS() {
        StringBuilder sb = new StringBuilder() ;
        // Le code mips des differentes instructions
        int ind = 0;
        for (Instruction i : programme) {
            ind++;
            sb.append(i.toMIPS());
            if (ind < programme.size())
                sb.append("\n");
        }

        return sb.toString();
    }

    /**
     * Renvoie un string contenant le string de toutes les instructions du bloc
     * @return un string contenant le string de toutes les instructions du bloc
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        int ind = 0;
        for (Instruction i : programme) {
            ind++;
            sb.append(i);
            if (ind < programme.size())
                sb.append("\n");
        }
        return "BI\n" + sb + "BI"  ;
    }

    public ArrayList<Instruction> getProgramme() {
        return programme;
    }
}
