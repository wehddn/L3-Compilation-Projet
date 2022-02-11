package zoot.exceptions;

public class VariableNonDefinie extends AnalyseException{
    public VariableNonDefinie(String m) {
        super("VARIABLE NON DEFINIE : " + m) ;
    }
}
