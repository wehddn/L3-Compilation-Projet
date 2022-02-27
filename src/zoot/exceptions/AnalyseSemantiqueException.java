package zoot.exceptions;

public class AnalyseSemantiqueException extends AnalyseException {

    public AnalyseSemantiqueException(String m) {
        super(m) ;
    }

    public AnalyseSemantiqueException(int ligne, int col, String m) {
        super("ERREUR SEMANTIQUE : ligne " + ligne + " : " + m);
    }

}
