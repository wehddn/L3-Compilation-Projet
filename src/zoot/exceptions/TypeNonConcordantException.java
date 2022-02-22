package zoot.exceptions;

/**
 * @version 1.2.0
 */
public class TypeNonConcordantException extends AnalyseSemantiqueException{
    public TypeNonConcordantException(int ligne, int colonne, String m) {
        super(ligne, colonne, "type non concordant (" + m + ")") ;
    }
}
