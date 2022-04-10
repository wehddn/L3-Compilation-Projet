package zoot.exceptions;

/**
 * Singleton responsable de collecter les erreurs sémantiques et de les déclencher une fois
 * l'analyse sémantique finie
 *
 * @author Elhadji Moussa FAYE
 * @version 3.8.1
 * @since 3.8.1
 * created on 10/04/2022
 */
public class GestionnaireErreursSemantiques {
    /**
     * Les messages des différents analyse sémantique exception
     */
    private String messages = "";
    private static GestionnaireErreursSemantiques instance;
    private GestionnaireErreursSemantiques() {

    }

    public static GestionnaireErreursSemantiques getInstance() {
        if (instance == null)
            instance = new GestionnaireErreursSemantiques();
        return instance;
    }

    /**
     * Ajoute une analyse semantique exception aux exceptions
     * @param as L'exception à ajouter
     */
    public void ajouterErreurSemantique(AnalyseSemantiqueException as) {
        if (messages.length() > 0)
            messages += "\n";
        messages += as.getMessage();
    }

    /**
     * Déclenche une exception sémantique contenant le message de toutes les exceptions sémantiques qui ont été ajouté
     * s'il y'en a, sinon il ne fait rien
     * @throws AnalyseSemantiqueException lorsqu'une exception a déjà été ajouté
     */
    public void declencherException() throws AnalyseSemantiqueException {
        if (messages.length() > 0) {
            throw new AnalyseSemantiqueException(messages);
        }
    }
}
