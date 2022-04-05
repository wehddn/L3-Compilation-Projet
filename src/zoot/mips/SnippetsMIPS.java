package zoot.mips;

import zoot.arbre.expressions.Expression;
import zoot.tds.Type;

public class SnippetsMIPS {
    public static String enteteProgramme(){
        // Ecrit le début du programme mips
        return """
                .data
                \tvrai: .asciiz "vrai"
                \tfaux: .asciiz "faux"
                .text
                main :""";
    }

    public static String sauvegarderParametres(int positionDepart, Expression... parametres) {
        StringBuilder sb = new StringBuilder();

        for (int i = parametres.length - 1; i >= 0; i--) {
            sb.append("""
                    %s
                    %s
                    """.formatted(parametres[i].toMIPS(), sauvegardeRegistreDansPile("$v0", (i * 4) + positionDepart)));
        }
        return sb.toString();
    }

    public static String mettreValeurDansRegistre(String registre, String valeur) {
        return "\tli %s, %s".formatted(registre, valeur);
    }

    public static String copieRegistreVersRegistre(String registeSource, String registreDestination) {
        return "\tmove %s, %s".formatted(registreDestination, registeSource);
    }

    public static String sauvegardeRegistreDansPile(String registre, int position) {
        return "\tsw %s, %s($sp)".formatted(registre, position);
    }

    public static String sauvegardeValeurDansPile(int valeur, int position) {
        return """
                \tli $t0, %s
                %s""".formatted(valeur, sauvegardeRegistreDansPile("$t0", position));
    }

    public static String restaurerRegistreDepuisPile(String registre, int position) {
        return "\tlw %s, %s($sp)".formatted(registre, position);
    }


    public static String reserverPlacePile(int nbCases){
        return "\tadd $sp, $sp, -%s".formatted(nbCases * 4);
    }

    public static String libererPlacePile(int nbCases) {
        return "\tadd $sp, $sp, %s".formatted(nbCases * 4);
    }


    public static String appelEcriture(Type typeAEcrire){
        StringBuilder sb = new StringBuilder() ;
        String codeEcriture;
        // quitte le programme en théorie ce cas ne devrait pas arriver
        switch (typeAEcrire) {
            case ENTIER -> codeEcriture = "1";
            case BOOLEEN -> {
                sb.append(appelTraductionBooleen()).append("\n");
                codeEcriture = "4";
            }
            default -> codeEcriture = "10";
        }

        sb.append("""
                    # écriture
                    \tmove $a0, $v0
                    \tli $v0,\s""").append(codeEcriture).append("\n");
        sb.append("""
                \tsyscall
                # saut de ligne après écriture
                \tli $v0, 11
                \tli $a0, 10
                \tsyscall""");

        return sb.toString();
    }

	public static String appelTraductionBooleen(){
        return """
                # appel fonction de traduction
                %s
                %s
                \tjal traductionBooleen
                %s
                %s""".formatted(reserverPlacePile(1) ,
                sauvegardeAdresseRetourAvantAppel(4),
                restaurationAdresseRetourApresAppel(4),
                libererPlacePile(1));
    }

	public static String definitionTraductionBooleen(){
        return """
                # fonction de traduction de booléen
                traductionBooleen :
                \tbeq $v0, $zero, traductionBooleenFaux
                traductionBooleenVrai : # booleen == vrai
                \tla $v0, vrai
                \tj finTraductionBooleen
                traductionBooleenFaux : # booleen == faux
                \tla $v0, faux
                finTraductionBooleen :
                \tjr $ra
                """;
    }

    public static String sauvegardeAdresseRetourAvantAppel(int position) {
        return """
                %s""".formatted(sauvegardeRegistreDansPile("$ra", position));
    }

    public static String restaurationAdresseRetourApresAppel(int position) {
        return """
                %s""".formatted(restaurerRegistreDepuisPile("$ra", position));
    }

    /**
     * Arguments MIPS : $a0 le numero du bloc, $a1 la position de la variable dans le bloc cible
     * Retourne le code MIPS pour la définition de la fonction de recherche de
     * position d'une variable dans la pile
     * @return Le code MIPS de la fonction définie
     */
    public static String definitionRecherchePosition(){
        return """
                # fonction de recherche de position
                recherchePositionMips :
                \tmove $t0, $s7 # position base local
                \tlw $t1, 4($t0) # le numéro de region du bloc courant
                whileRecherchePosition :
                \tbeq $t1, $a0, exitRecherchePosition
                \tlw $t0, 8($t0) # position fonction appelante (chainage dynamique)
                \tlw $t1, 4($t0)
                \tj whileRecherchePosition
                exitRecherchePosition :
                \tadd $t0, $t0, $a1
                \tmove $v0, $t0
                \tjr $ra
                """;
    }

    /**
     * Donne le code MIPS de l'appel de la fonction de recherche de variable dans
     * la pile
     * Arguments MIPS : $a0 le numero du bloc, $a1 la position du bloc courant
     *
     * @return
     */
    public static String appelRecherchePosition(){
        return """
                %s
                %s
                \tjal recherchePositionMips
                %s
                %s""".formatted(reserverPlacePile(1),
                sauvegardeAdresseRetourAvantAppel(4),
                restaurationAdresseRetourApresAppel(4),
                libererPlacePile(1));
    }
}
