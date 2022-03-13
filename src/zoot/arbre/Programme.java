package zoot.arbre;

import zoot.exceptions.AnalyseSemantiqueException;
import zoot.tds.Tds;

import java.util.ArrayList;

/**
 * Represente l'Arbre abstrait général (le programme)
 *
 * @author Elhadji Moussa FAYE
 * @version 1.8.0
 * @since 1.4.2
 * created on 19/02/2022
 */
public class Programme extends ArbreAbstrait{
    private BlocDeFonctions fonctions = null ;
    private BlocDInstructions instructions = null;
    protected int taillePile = 0;

    public Programme(int n, int m) {
        super(n, m);
    }

    @Override
    public void verifier() throws AnalyseSemantiqueException {
        fonctions.verifier();
        instructions.verifier();
        taillePile = Tds.getInstance().getTaillePile();
    }

    @Override
    public String toMIPS() {
        StringBuilder sb = new StringBuilder() ;
        // Ecrit le début du programme mips
        sb.append(".data\n" +
                "\tvrai: .asciiz \"vrai\"\n" +
                "\tfaux: .asciiz \"faux\"\n" +
                ".text\n" +
                "main :\n" +
                "# initialiser $s7 avec $sp\n" +
                "\tmove $s7, $sp\n" +
                "# réserver la place pour ");
        sb.append(taillePile / 4).append(" variable");

        if (taillePile > 4) // Ajoute un s à variable s'il y'a plusieurs variables
            sb.append('s');

        // L'instruction qui réserve la place dans la pile pour les variables
        sb.append("\n\tadd $sp, $sp, ").append(-taillePile).append("\n");

        sb.append(instructions.toMIPS()).append("\n");

        // Ecrit la fin du programme mips (retour et la fonction de traduction bool)
        sb.append("end :\n" +
                "\tli $v0, 10\n" +
                "\tsyscall\n\n");
        sb.append("traductionbool :\n" +
                "\tbeq $v0, $zero, boolfaux\n" +
                "boolvrai :\n" +
                "\tla $v0, vrai\n" +
                "\tb fintraductionbool\n" +
                "boolfaux :\n" +
                "\tla $v0, faux\n" +
                "fintraductionbool :\n" +
                "\tjr $ra\n");
        sb.append(fonctions.toMIPS());

        return sb.toString();
    }

    @Override
    public String toString() {
        return "Programme :\nTaille pile : " + taillePile + "\n" + super.toString();
    }

    public void setBlocDeFonctions(BlocDeFonctions b) {
        fonctions = b;
    }

    public void setBlocDInstructions(BlocDInstructions b) {
        instructions = b;
    }
}
