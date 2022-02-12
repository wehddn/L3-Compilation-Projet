package zoot.arbre;

import zoot.arbre.instructions.Instruction;
import zoot.tds.Tds;

import java.util.ArrayList;

/**
 * Classe qui représente un bloc d'instruction dans l'arbre abstrait
 * 21 novembre 2018
 *
 * @author brigitte wrobel-dautcourt
 * @version 1.2.0
 */

public class BlocDInstructions extends ArbreAbstrait {

    protected ArrayList<Instruction> programme ;

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
        for (Instruction i : programme) {
            i.verifier();
        }
    }

    /**
     * @see ArbreAbstrait
     * @return le code mips du bloc d'instruction
     */
    @Override
    public String toMIPS() {
        StringBuilder sb = new StringBuilder() ;
        int taillePile = Tds.getInstance().getTailleZoneVariables();
        // Ecrit le début du programme mips
        sb.append(".text\n" +
                  "main :\n" +
                  "# initialiser $s7 avec $sp\n" +
                  "\tmove $s7, $sp\n" +
                  "# réserver la place pour ");
        sb.append(taillePile / 4).append(" variable");

        if (taillePile > 4) // Ajoute un s à variable s'il y'a plusieurs variables
            sb.append('s');

        // L'instruction qui réserve la place dans la pile pour les variables
        sb.append("\n\tadd $sp, $sp, ").append(-taillePile).append("\n");

        // Le code mips des differentes instructions
        for (Instruction i : programme) {
            sb.append(i.toMIPS()).append("\n");
        }
        // Ecrit la fin du programme mips (retour)
        sb.append("end :\n" +
                  "\tli $v0, 0\n" +
                  "\tsyscall");
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
        return "BI\n" + sb.toString().indent(2) + "BI"  ;
    }

}
