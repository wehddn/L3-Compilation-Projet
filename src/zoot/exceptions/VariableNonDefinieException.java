package zoot.exceptions;

public class VariableNonDefinieException extends AnalyseSemantiqueException{
    public VariableNonDefinieException(int ligne, int colonne, String m) {
        super("VARIABLE NON DEFINIE :\n\tligne " +  ligne + ", colonne " + colonne + ", variable " + m) ;
    }
}
