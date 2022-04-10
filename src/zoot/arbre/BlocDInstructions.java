package zoot.arbre;

import zoot.arbre.instructions.Boucle;
import zoot.arbre.instructions.Condition;
import zoot.arbre.instructions.Instruction;
import zoot.arbre.instructions.Retourne;
import zoot.exceptions.AnalyseSemantiqueException;

import java.util.ArrayList;

/**
 * Classe qui représente un bloc d'instruction dans l'arbre abstrait
 * 21 novembre 2018
 *
 * @author brigitte wrobel-dautcourt
 * @version 3.7.0
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

    /**
     * Permet d'englober le bloc d'instruction dans une fonction (utilisé pour les
     * conditions et boucle), elle permet de faire les liens avec la fonction pour
     * les instructions Retourne
     * @param f la fonction englobante
     * @return true s'il y'a au moins un retourne dans ses instructions
     */
    public boolean setFonction(Fonction f) {
        boolean presenceRetourne = false;
        for (Instruction i : instructions) {
            if (i instanceof Retourne r) {
                presenceRetourne = true;
                r.setFonction(f);
            }
            if (i instanceof Boucle b) {
                if (b.setFonction(f))
                    presenceRetourne = true;
            }

            if (i instanceof Condition c) {
                if (c.setFonction(f))
                    presenceRetourne = true;
            }
        }
        return presenceRetourne;
    }
}
