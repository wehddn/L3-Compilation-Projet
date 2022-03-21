package zoot.arbre;

import zoot.arbre.instructions.Retourne;
import zoot.exceptions.TypeNonConcordantException;
import zoot.tds.*;

/**
 * Classe qui représente les différentes instructions d'une fonctions du programme
 *
 * @author Nicolas GRAFF
 * @version 2.5.2
 * @since 1.8.0
 * created on 07/03/2022
 */

public class Fonction extends ArbreAbstrait{
    private final EntreeFct entree;
    private SymboleFct symbole = null;
    private final Retourne retour;
    private BlocDInstructions instructions;

    public Fonction(EntreeFct e, Retourne r, int n, int m) {
        super(n, m);
        this.entree = e;
        this.retour = r;
        this.instructions = new BlocDInstructions(0, 0);
    }

    @Override
    public void verifier() {
        Tds.getInstance().entreeBlocUtilisation();
        instructions.verifier();
        retour.verifier();
        this.symbole = (SymboleFct) Tds.getInstance().identifier(entree, noLigne, noColonne);
        Type retourType = retour.getType();
        Type symboleType = symbole.getType();
        Tds.getInstance().sortieBlocUtilisation();
        if (!retourType.equals(symboleType)){
            throw new TypeNonConcordantException(retour.getNoLigne(), retour.getNoColonne(),  "fonction: " + symboleType + ", retourne: " + retourType);
        }
    }

    public String toMIPS(){
        return symbole.getEtiquette() + " :\n" +
                instructions.toMIPS() + '\n' +
                retour.toMIPS() + '\n';
    }

    public void setBlocDInstructions(BlocDInstructions b) {
        if(b != null)
            instructions = b;
    }

    @Override
    public String toString() {
        return entree.toString();
    }
}