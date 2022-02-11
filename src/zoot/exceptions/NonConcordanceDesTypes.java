package zoot.exceptions;

public class NonConcordanceDesTypes extends AnalyseException{
    public NonConcordanceDesTypes(String m, String n) {
        super("NON CONCORDANCE DES TYPES : " + m + " et " + n) ;
    }
}
