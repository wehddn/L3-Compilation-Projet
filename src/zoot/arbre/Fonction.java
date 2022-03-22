package zoot.arbre;

import zoot.arbre.instructions.Instruction;
import zoot.arbre.instructions.Retourne;
import zoot.exceptions.TypeNonConcordantException;
import zoot.tds.*;

/**
 * Classe qui représente les différentes instructions d'une fonctions du programme
 *
 * @author Nicolas GRAFF
 * @version 2.6.0
 * @since 1.8.0
 * created on 07/03/2022
 */

public class Fonction extends BlocDInstructions{
    private final EntreeFct entree;
    private SymboleFct symbole = null;

    public Fonction(EntreeFct e, int n, int m) {
        super(n, m);
        this.entree = e;
    }

    @Override
    public void verifier() {
        Tds.getInstance().entreeBlocUtilisation();
        this.symbole = (SymboleFct) Tds.getInstance().identifier(entree, noLigne, noColonne);
        super.verifier();
        Tds.getInstance().sortieBlocUtilisation();
    }

    public String toMIPS(){
        return symbole.getEtiquette() + " :\n" +
                super.toMIPS() + '\n';
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