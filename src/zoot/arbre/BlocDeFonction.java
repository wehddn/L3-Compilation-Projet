package zoot.arbre;

import zoot.arbre.instructions.Instruction;

import java.util.ArrayList;

/**
 * Classe qui représente les différentes instructions d'une fonctions du programme
 *
 * @author Nicolas GRAFF
 * @version 1.6.0
 * @since 1.6.0
 * created on 07/03/2022
 */

public class BlocDeFonction extends BlocDInstructions{

    static ArrayList<BlocDeFonction> bl = new ArrayList<>();

    public BlocDeFonction(int n, int m) {
        super(n, m);
        programme = new ArrayList<>() ;
    }

    public void ajouter(Instruction i) {
        programme.add(i) ;
    }

    public String toMips(){
        return "";
    }

    public String toString(){
        return "";
    }
}