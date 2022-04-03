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
 * @version 2.8.1
 */

public class BlocDInstructions extends ArbreAbstrait {

    protected final ArrayList<Instruction> instructions;

    public BlocDInstructions(int ligne, int colonne) {
        super(ligne, colonne) ;
        instructions = new ArrayList<>() ;
    }

    /**
     * Ajoute une instruction dans le bloc d'instruction
     * @param i L'instruction à ajouter
     */
    public void ajouter(Instruction i) {
        instructions.add(i) ;
    }

    public void ajouter(BlocDInstructions b) {
        instructions.addAll(b.instructions);
    }

    /**
     * @see ArbreAbstrait
     */
    @Override
    public void verifier() {
        StringBuilder sb = new StringBuilder();
        boolean exception = false;
        for (Instruction i : instructions) {
            try {
                i.verifier();
            } catch (AnalyseSemantiqueException as) {
                sb.append(as.getMessage()).append("\n");
                exception = true;
            }
        }
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
        for (Instruction i : instructions) {
            ind++;
            sb.append(i.toMIPS());
            if (ind < instructions.size())
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
        for (Instruction i : instructions) {
            sb.append(i).append("\n");
        }
        return "BI-start\n" + sb.toString().indent(4) + "BI-end\n"  ;
    }

    public ArrayList<Instruction> getInstructions() {
        return instructions;
    }
}
