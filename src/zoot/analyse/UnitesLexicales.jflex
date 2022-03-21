package zoot.analyse ;

import java_cup.runtime.*;
import zoot.exceptions.AnalyseLexicaleException;
      
%%
   
%class AnalyseurLexical
%public

%line
%column
    
%type Symbol
%eofval{
        return symbol(CodesLexicaux.EOF) ;
%eofval}

%cup

%{

  private StringBuilder chaine ;

  private Symbol symbol(int type) {
	return new Symbol(type, yyline, yycolumn) ;
  }

  private Symbol symbol(int type, Object value) {
	return new Symbol(type, yyline, yycolumn, value) ;
  }
%}

csteE = [0-9]+
idf = [a-zA-Z][a-zA-Z0-9]*
finDeLigne = \r|\n
espace = {finDeLigne}  | [ \t\f]

%%
"//".*                                    { /* Commentaires */ }

"variables"     { return symbol(CodesLexicaux.VARIABLES_T); }
"fonctions"     { return symbol(CodesLexicaux.FONCTIONS_T); }
"debut"         { return symbol(CodesLexicaux.DEBUT); }
"fin"           { return symbol(CodesLexicaux.FIN); }
"entier"        { return symbol(CodesLexicaux.ENTIER_T); }
"booleen"       { return symbol(CodesLexicaux.BOOLEEN_T); }
";"             { return symbol(CodesLexicaux.POINTVIRGULE); }
"="             { return symbol(CodesLexicaux.EGAL_T); }
"repeter"       { return symbol(CodesLexicaux.REPETER_T); }
"jusqua"        { return symbol(CodesLexicaux.JUSQUA_T); }
"finrepeter"   { return symbol(CodesLexicaux.FINREPETER_T); }
"si"            { return symbol(CodesLexicaux.SI_T); }
"alors"         { return symbol(CodesLexicaux.ALORS_T); }
"sinon"         { return symbol(CodesLexicaux.SINON_T); }
"finsi"        { return symbol(CodesLexicaux.FINSI_T); }
"retourne"      { return symbol(CodesLexicaux.RETOURNE_T); }
"ecrire"        { return symbol(CodesLexicaux.ECRIRE_T); }
"vrai"          { return symbol(CodesLexicaux.VRAI_T); }
"faux"          { return symbol(CodesLexicaux.FAUX_T); }
"non"           { return symbol(CodesLexicaux.NON_T); }
"-"             { return symbol(CodesLexicaux.MOINS_T); }//
"+"             { return symbol(CodesLexicaux.PLUS_T); }
"*"             { return symbol(CodesLexicaux.FOIS_T); }
"<"             { return symbol(CodesLexicaux.INF_T); }
"=="            { return symbol(CodesLexicaux.EQUIVAUT_T); }
"!="            { return symbol(CodesLexicaux.DIFFERENT_T); }
"et"            { return symbol(CodesLexicaux.ET_T); }
"ou"            { return symbol(CodesLexicaux.OU_T); }
","             { return symbol(CodesLexicaux.VIRGULE_T); }
"("             { return symbol(CodesLexicaux.PARENTOUVERT_T); }
")"             { return symbol(CodesLexicaux.PARENTFERME_T); }
{csteE}      	{ return symbol(CodesLexicaux.CSTENTIERE, yytext()); }
{idf}           { return symbol(CodesLexicaux.IDF_T, yytext()); }

{espace}               {}
.                      { throw new AnalyseLexicaleException(yyline, yycolumn, yytext()) ; }