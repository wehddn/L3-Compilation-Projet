package zoot.exceptions;

public class NonConcordanceDesTypes extends AnalyseException{
    protected NonConcordanceDesTypes(String m, String n) {
        super("NON CONCORDANCE DES TYPES : " + m + " et " + n) ;
    }
}
