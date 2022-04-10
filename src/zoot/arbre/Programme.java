package zoot.arbre;

import zoot.exceptions.AnalyseSemantiqueException;
import zoot.mips.SnippetsMIPS;
import zoot.tds.Tds;

/**
 * Represente l'Arbre abstrait général (le programme)
 *
 * @author Elhadji Moussa FAYE
 * @version 3.7.0
 * @since 1.4.2
 * created on 19/02/2022
 */
public class Programme extends ArbreAbstrait{
    private BlocDeFonctions fonctions = new BlocDeFonctions(0,0) ;
    private BlocDInstructions instructions = new BlocDInstructions(0,0);
    protected int nbVariablesLocales = 0;
    private int noBloc;

    public Programme(int n, int m) {
        super(n, m);
    }

    @Override
    public void verifier() throws AnalyseSemantiqueException {
        StringBuilder sb = new StringBuilder();
        boolean exception = false;
        try {
            fonctions.verifier();
        } catch (AnalyseSemantiqueException as) {
            sb.append(as.getMessage()).append("\n");
            exception = true;
        }
        try {
            instructions.verifier();
        } catch (AnalyseSemantiqueException as) {
            sb.append(as.getMessage());
            exception = true;
        }

        if (exception)
            throw new AnalyseSemantiqueException(sb.toString());

        nbVariablesLocales = Tds.getInstance().getTailleZoneVar()/4;
        noBloc = Tds.getInstance().getNoRegion();
    }

    @Override
    public String toMIPS() {
        // Ecrit le début du programme mips

        return """
                # entete du programme
                %s
                # reservation place pour le numero de bloc et les variables locales
                %s
                # sauvegarde noregion
                %s
                \tadd $s7, $sp, %s # initialisation base locale
                # instructions
                %s
                end :
                \tli $v0, 10
                \tsyscall
                                
                # définition fonctions système
                """.formatted(SnippetsMIPS.enteteProgramme(),
                SnippetsMIPS.reserverPlacePile(1 + nbVariablesLocales),
                SnippetsMIPS.sauvegardeValeurDansPile(noBloc, (1 + nbVariablesLocales) * 4),
                nbVariablesLocales * 4, instructions.toMIPS()) +
                SnippetsMIPS.definitionTraductionBooleen() + "\n" +
                SnippetsMIPS.definitionRecherchePosition() + "\n" +
                "# définition fonctions utilisateurs :\n" +
                fonctions.toMIPS();
    }

    @Override
    public String toString() {
        return "Programme :\nTaille pile : " + nbVariablesLocales + "\nFonctions :\n" + fonctions +
               "Instructions : \n" + instructions;
    }

    public void setBlocDeFonctions(BlocDeFonctions b) {
        fonctions = b;
    }

    public void setBlocDInstructions(BlocDInstructions b) {
        instructions = b;
    }
}
