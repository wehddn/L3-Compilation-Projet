package zoot.exceptions;

public class DoubleDeclarationException extends AnalyseSemantiqueException {
    public DoubleDeclarationException(int ligne, int colonne, String m) {
        super(ligne, colonne, "double déclaration (" + m + ")") ;
//        super("DOUBLE DECLARATION :\n\tligne " +  ligne + ", colonne " + colonne + ", variable " + m) ;

    }
}