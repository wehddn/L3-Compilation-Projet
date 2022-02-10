package zoot.arbre;

/**
 * Description
 *
 * @author Elhadji Moussa FAYE
 * @version 0.1.0
 * @since 0.1.0
 * created on 08/02/2022
 */
public class Symbole {
    private String type;
    private int deplacement;

    public Symbole(String type) {
        this.type = type;
        this.deplacement = Tds.getInstance().getTailleZoneVariables();
    }

    public String getType() {
        return type;
    }

    public int getDeplacement() {
        return deplacement;
    }
}
