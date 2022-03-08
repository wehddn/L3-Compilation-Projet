package zoot.arbre;

import zoot.arbre.instructions.Instruction;
import zoot.tds.Entree;
import zoot.tds.SymboleVar;
import zoot.tds.Tds;

import java.util.ArrayList;

/**
 * Classe qui représente les différentes instructions d'une fonctions du programme
 *
 * @author Nicolas GRAFF
 * @version 1.7.0
 * @since 1.6.0
 * created on 07/03/2022
 */

public class BlocDeFonction extends BlocDInstructions{

    final Entree entree;

    public BlocDeFonction(Entree e, int n, int m) {
        super(n, m);
        this.entree = e;
        programme = new ArrayList<>() ;
    }

    public void ajouter(Instruction i) {
        programme.add(i) ;
    }

    public void verifier(){
        BlocDInstructions bi = new BlocDInstructions(0, 0);
        bi.programme = this.getProgramme();
        bi.verifier();
    }

    public String toMips(){
        System.out.println(Tds.getInstance().getTailleZoneVariables());
        return "test";
    }

    public String toString(){
        return "";
    }
}