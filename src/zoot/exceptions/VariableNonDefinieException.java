package zoot.exceptions;

public class VariableNonDefinieException extends AnalyseSemantiqueException{
    public VariableNonDefinieException(int ligne, int colonne, String m) {
        super(ligne, colonne, "variable non définie ( " + m + " )") ;
    }
}
