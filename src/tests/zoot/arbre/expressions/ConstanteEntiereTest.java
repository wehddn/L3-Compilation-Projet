package zoot.arbre.expressions;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ConstanteEntiereTest {

    @Test
    void toMIPS1() {
        Expression exp = new ConstanteEntiere("100", 2);
        String attendu = "\t# Constante entiére\n" +
                "\tli $v0, 100";
        assertEquals(attendu, exp.toMIPS());
    }
}