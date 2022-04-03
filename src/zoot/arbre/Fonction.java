package zoot.arbre;

import zoot.arbre.instructions.Instruction;
import zoot.arbre.instructions.Retourne;
import zoot.mips.SnippetsMIPS;
import zoot.tds.*;

/**
 * Classe qui représente les différentes instructions d'une fonctions du programme
 *
 * @author Nicolas GRAFF
 * @version 2.8.1
 * @since 1.8.0
 * created on 07/03/2022
 */

public class Fonction extends BlocDInstructions{
    private final EntreeFct entree;
    private SymboleFct symbole = null;
    private int nbVariablesLocales;
    private int noBloc;

    public Fonction(EntreeFct e, int ligne, int colonne) {
        super(ligne, colonne);
        this.entree = e;
    }

    @Override
    public void verifier() {
        Tds.getInstance().entreeBloc();
        this.symbole = (SymboleFct) Tds.getInstance().identifier(entree, ligne, colonne);
        super.verifier();
        noBloc = Tds.getInstance().getNoRegion();
        nbVariablesLocales = Tds.getInstance().getTailleZoneVar()/4;
        Tds.getInstance().sortieBloc();
    }

    public String toMIPS(){
        StringBuilder sb = new StringBuilder();
        // Sauvegarde du numéro de région et initialisation de la base locale
        sb.append("""
                %s :
                %s # sauvegarde noregion
                %s # initialisation base locale
                """.formatted(symbole.getEtiquette(),
                SnippetsMIPS.sauvegardeValeurDansPile(noBloc, 4),
                SnippetsMIPS.copieRegistreVersRegistre("$sp", "$s7")));

        // Réservation des variables locales s'il y'en a
        if (nbVariablesLocales > 0) {
            sb.append("""
                    %s
                    """.formatted(SnippetsMIPS.reserverPlacePile(nbVariablesLocales)));
        }

        // Ecriture du code mips des instructions
        sb.append("""
                # instructions
                %s
                """.formatted(super.toMIPS()));
        return sb.toString();

    }

    public void ajouter(Retourne r) {
        r.setFonction(this);
        instructions.add(r);
    }

    @Override
    public void ajouter(BlocDInstructions b) {
        for (Instruction i : b.instructions) {
            if (i instanceof Retourne r)
                r.setFonction(this);
            instructions.add(i);
        }
    }

    @Override
    public String toString() {
        return entree.toString();
    }

    public Type getType() {
        return symbole.getType();
    }
}