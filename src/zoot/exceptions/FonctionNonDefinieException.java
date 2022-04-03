package zoot.exceptions;

/**
 * Description
 *
 * @author Elhadji Moussa FAYE
 * @version 2.8.1
 * @since 2.0.0
 * created on 19/03/2022
 */
public class FonctionNonDefinieException extends AnalyseSemantiqueException{
    public FonctionNonDefinieException(int ligne, int colonne, String m) {
        super(ligne, colonne, "fonction non définie ( " + m + " )");
    }
}
