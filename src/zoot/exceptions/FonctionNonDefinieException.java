package zoot.exceptions;

/**
 * Description
 *
 * @author Elhadji Moussa FAYE
 * @version 2.0.0
 * @since 2.0.0
 * created on 19/03/2022
 */
public class FonctionNonDefinieException extends AnalyseSemantiqueException{
    public FonctionNonDefinieException(int ligne, int col, String m) {
        super(ligne, col, "fonction non définie ( " + m + " )");
    }
}
