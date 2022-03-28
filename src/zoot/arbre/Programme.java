package zoot.arbre;

import zoot.exceptions.AnalyseSemantiqueException;
import zoot.mips.SnippetsMIPS;
import zoot.tds.Tds;

/**
 * Represente l'Arbre abstrait général (le programme)
 *
 * @author Elhadji Moussa FAYE
 * @version 2.5.0
 * @since 1.4.2
 * created on 19/02/2022
 */
public class Programme extends ArbreAbstrait{
    private BlocDeFonctions fonctions = new BlocDeFonctions(0,0) ;
    private BlocDInstructions instructions = new BlocDInstructions(0,0);
    protected int taillePile = 0;

    public Programme(int n, int m) {
        super(n, m);
    }

    @Override
    public void verifier() throws AnalyseSemantiqueException {
        fonctions.verifier();
        instructions.verifier();
        taillePile = Tds.getInstance().getTailleZoneVar();
    }

    @Override
    public String toMIPS() {
        StringBuilder sb = new StringBuilder() ;
        // Ecrit le début du programme mips
        sb.append(SnippetsMIPS.enteteProgramme());
        sb.append(SnippetsMIPS.reserverPlacePile(taillePile));

        if (taillePile > 4) // Ajoute un s à variable s'il y'a plusieurs variables
            sb.append('s');

        // L'instruction qui réserve la place dans la pile pour les variables
        sb.append("\n\tadd $sp, $sp, ").append(-taillePile).append("\n");

        sb.append(instructions.toMIPS()).append("\n");

        // Ecrit la fin du programme mips (retour et la fonction de traduction bool)
        sb.append("end :\n" +
                "\tli $v0, 10\n" +
                "\tsyscall\n\n");
        sb.append(SnippetsMIPS.definitionTraductionBooleen());
        sb.append(fonctions.toMIPS());

        return sb.toString();
    }

    @Override
    public String toString() {
        return "Programme :\nTaille pile : " + taillePile +"\nFonctions :\n" + fonctions +
                "Instructions : \n" + instructions;
    }

    public void setBlocDeFonctions(BlocDeFonctions b) {
        fonctions = b;
    }

    public void setBlocDInstructions(BlocDInstructions b) {
        instructions = b;
    }
}
