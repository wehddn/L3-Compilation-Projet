package zoot.arbre;

import org.junit.jupiter.api.Test;
import zoot.arbre.expressions.ConstanteEntiere;
import zoot.arbre.expressions.Expression;
import zoot.arbre.instructions.Ecrire;

import static org.junit.jupiter.api.Assertions.*;

class BlocDInstructionsTest {

    @Test
    void toMIPS1() {
        Expression exp = new ConstanteEntiere("25", 2);
        Ecrire ecrire = new Ecrire(exp, 2);
        String attendu = "main: #debut\n" +
                "\t# Initialisation de la base des variables\n" +
                "\tmove $s7, $sp\n\n" +
                ecrire.toMIPS() +
                "\n\nend: #fin\n" +
                "\t# Fin du programme\n" +
                "\tli $v0, 10 # retour système\n" +
                "\tsyscall";
        BlocDInstructions bi = new BlocDInstructions(1);
        bi.ajouter(ecrire);
        assertEquals(attendu, bi.toMIPS());
    }
}