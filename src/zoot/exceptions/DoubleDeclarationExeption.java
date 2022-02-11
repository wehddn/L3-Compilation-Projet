package zoot.exceptions;

public class DoubleDeclarationExeption extends AnalyseException{
    public DoubleDeclarationExeption(String m) {
        super("DOUBLE DECLARATION : " + m) ;
    }
}