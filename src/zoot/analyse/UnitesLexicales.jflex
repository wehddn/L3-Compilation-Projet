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

"variables"            { return symbol(CodesLexicaux.VARIABLES); }
"debut"                { return symbol(CodesLexicaux.DEBUT); }
"fin"              	   { return symbol(CodesLexicaux.FIN); }

"entier"               { return symbol(CodesLexicaux.ENTIER, yytext()); }
"booleen"              { return symbol(CodesLexicaux.BOOLEEN, yytext()); }
"="                    { return symbol(CodesLexicaux.EGAL); }
"ecrire"               { return symbol(CodesLexicaux.ECRIRE); }
"vrai"                 { return symbol(CodesLexicaux.CBOOL, yytext()); }
"faux"                 { return symbol(CodesLexicaux.CBOOL, yytext()); }
";"                    { return symbol(CodesLexicaux.POINTVIRGULE); }
{idf}                  { return symbol(CodesLexicaux.IDF, yytext()); }
{csteE}      	       { return symbol(CodesLexicaux.CSTENTIERE, yytext()); }
{espace}               { }
.                      { throw new AnalyseLexicaleException(yyline, yycolumn, yytext()) ; }

