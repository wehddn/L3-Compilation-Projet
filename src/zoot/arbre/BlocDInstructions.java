package zoot.arbre;

import zoot.arbre.instructions.Instruction;

import java.util.ArrayList;

/**
 * 21 novembre 2018
 *
 * @author brigitte wrobel-dautcourt
 */

public class BlocDInstructions extends ArbreAbstrait {

    protected ArrayList<Instruction> programme ;

    public BlocDInstructions(int n) {
        super(n) ;
        programme = new ArrayList<>() ;
    }

    public void ajouter(Instruction i) {
        programme.add(i) ;
    }

    @Override
    public void verifier() {
        throw new UnsupportedOperationException("fonction verifier non définie ") ;
    }

    @Override
    public String toMIPS() {
        StringBuilder sb = new StringBuilder() ;
        sb.append("""
                main: #debut
                \t# Initialisation de la base des variables
                \tmove $s7, $sp
                
                """);
        for (Instruction i : programme) {
            sb.append(i.toMIPS()).append("\n");
        }
        sb.append("""
                end: #fin
                \t# Fin du programme
                \tli $v0, 10 # retour système
                \tsyscall""");
        return sb.toString();
    }

    @Override
    public String toString() {
        return programme.toString() ;
    }

}
