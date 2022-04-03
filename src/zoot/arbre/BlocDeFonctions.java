package zoot.arbre;

import zoot.exceptions.AnalyseSemantiqueException;

import java.util.ArrayList;

/**
 * Description
 *
 * @author Elhadji Moussa FAYE
 * @version 2.8.1
 * @since 1.8.0
 * created on 13/03/2022
 */
public class BlocDeFonctions extends ArbreAbstrait {
    private final ArrayList<Fonction> fonctions ;

    public BlocDeFonctions(int ligne, int colonne) {
        super(ligne, colonne);
        fonctions = new ArrayList<>() ;
    }

    @Override
    public void verifier() throws AnalyseSemantiqueException {
        for (Fonction f: fonctions) {
            f.verifier();
        }
    }

    @Override
    public String toMIPS() {
        StringBuilder sb = new StringBuilder();
        for (Fonction f : fonctions) {
            sb.append(f.toMIPS()).append('\n');
        }
        return sb.toString();
    }

    public void ajouter(Fonction f) {
        fonctions.add(f);
    }

    public void ajouter(BlocDeFonctions b) {
        fonctions.addAll(b.fonctions);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Fonction f : fonctions) {
            sb.append(f).append("\n");
        }
        return "BF-start\n" + sb.toString().indent(4) + "BF-end\n"  ;
    }
}
