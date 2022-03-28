package zoot.mips;

import zoot.tds.Type;

public class SnippetsMIPS {
    public static String enteteProgramme(){
        StringBuilder sb = new StringBuilder() ;
        // Ecrit le début du programme mips
        sb.append(".data\n" +
                "\tvrai: .asciiz \"vrai\"\n" +
                "\tfaux: .asciiz \"faux\"\n" +
                ".text\n" +
                "main :\n" +
                "# initialiser $s7 avec $sp\n" +
                "\tmove $s7, $sp\n");
        return sb.toString();
    }

    public String empiler(String registre){
        return "";
    }

    public static String reserverPlacePile(int taillePile){
        return "# réserver la place pour " + taillePile / 4 + " variable";
    }

    public String sauvegardeBaseLocale(){
        return "";
    }

    public String definitionRecherchePosition(){
        return "";
    }

    public String appelRecherchePosition(){
        return "";
    }

	public static String appelTraductionBooleen(){
        StringBuilder sb = new StringBuilder() ;
        sb.append("\n#sauvegarde return adress\n" +
                "\tsw $ra, -4($sp)\n" +
                "\tsub $sp, $sp, 4\n" +
                "\tjal traductionbool\n" +
                "#restauration return adress\n" +
                "\tlw $ra, 0($sp)\n" +
                "\taddi $sp, $sp, 4");
        return sb.toString();
    }

	public static String definitionTraductionBooleen(){
        StringBuilder sb = new StringBuilder() ;
        sb.append("traductionbool :\n" +
                "\tbeq $v0, $zero, boolfaux\n" +
                "boolvrai :\n" +
                "\tla $v0, vrai\n" +
                "\tb fintraductionbool\n" +
                "boolfaux :\n" +
                "\tla $v0, faux\n" +
                "fintraductionbool :\n" +
                "\tjr $ra\n");
        return sb.toString();
    }

	public static String appelEcriture(Type typeAEcrire){
        StringBuilder sb = new StringBuilder() ;
        String codeEcriture;
        // quitte le programme en théorie ce cas ne devrait pas arriver
        switch (typeAEcrire) {
            case ENTIER:
                codeEcriture = "1";
                break;
            case BOOLEEN:
                sb.append(appelTraductionBooleen());
                codeEcriture = "4";
                break;
            default:
                codeEcriture = "10";
                break;
        }

        sb.append("\n# Ecriture\n" +
                "\tmove $a0, $v0\n" +
                "\tli $v0, ").append(codeEcriture)
                .append("\n\tsyscall\n" +
                        "# Saut de ligne\n" +
                        "\tli $v0, 11\n" +
                        "\tli $a0, 10\n" +
                        "\tsyscall");

        return sb.toString();
    }
}
