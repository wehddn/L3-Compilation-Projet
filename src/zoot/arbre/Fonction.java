package zoot.arbre;

import zoot.arbre.instructions.Retourne;
import zoot.exceptions.TypeNonConcordantException;
import zoot.tds.*;

/**
 * Classe qui représente les différentes instructions d'une fonctions du programme
 *
 * @author Nicolas GRAFF
 * @version 1.8.0
 * @since 1.8.0
 * created on 07/03/2022
 */

public class Fonction extends ArbreAbstrait{
    private EntreeFct entree;
    private SymboleFct symbole = null;
    private Retourne retour;
    private BlocDInstructions instructions = null;

    public Fonction(EntreeFct e, Retourne r, int n, int m) {
        super(n, m);
        this.entree = e;
        this.retour = r;
    }

    @Override
    public void verifier() {
        instructions.verifier();
        this.symbole = (SymboleFct) Tds.getInstance().identifier(entree, noLigne, noColonne);
        Type retourType = retour.getType();
        Type symboleType = symbole.getType();
        if (!retourType.equals(symboleType)){
            throw new TypeNonConcordantException(retour.getNoLigne(), retour.getNoColonne(),  symboleType + " <- " + retourType);
        }
    }

    public String toMIPS(){
        //        sb.append(symbole.getEtiquette()).append(" :\n");
        return entree.getNom() + " :\n" +
                instructions.toMIPS() + '\n' +
                retour.toMIPS() + '\n';
    }

    public void setBlocDInstructions(BlocDInstructions b) {
        instructions = b;
    }
}