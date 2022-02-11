package zoot.exceptions;

public class TypeNonConcordantException extends AnalyseSemantiqueException{
    public TypeNonConcordantException(int ligne, int colonne, String m, String n) {
        super("TYPE NON CONCORDANT :\n\tligne " +  ligne + ", colonne " + colonne + ", variables " + m + " et " + n) ;
    }
}
