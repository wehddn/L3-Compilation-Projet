package zoot.arbre;

import zoot.arbre.instructions.Instruction;

import java.util.ArrayList;

/**
 * Classe qui représente un bloc d'instruction dans l'arbre abstrait
 * 21 novembre 2018
 *
 * @author brigitte wrobel-dautcourt
 * @version 1.0.2
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
        throw new UnsupportedOperationException("fonction verifier non définie ") ;
    }

    /**
     * @see ArbreAbstrait
     * @return le code mips du bloc d'instruction
     */
    @Override
    public String toMIPS() {
        StringBuilder sb = new StringBuilder() ;
        sb.append("""
                main: #debut
                \t# Initialisation de la base des variables
                \tmove $s7, $sp
                
                """);
        for (Instruction i : programme) {
            sb.append(i.toMIPS()).append("\n\n");
        }
        sb.append("""
                end: #fin
                \t# Fin du programme
                \tli $v0, 10 # retour système
                \tsyscall""");
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
