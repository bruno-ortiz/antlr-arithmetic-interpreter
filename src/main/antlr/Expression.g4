grammar Expression;

/*
 * Parser Rules
 */
expr  : term ((PLUS|MINUS) term)*;
term  : factor ((MUL|DIV) factor)*;
factor: NUMBER | (LPAREN expr RPAREN);

/*
 * Lexer Rules
 */
LPAREN     : [(] ;
RPAREN     : [)] ;
NUMBER     : [0-9]+ ;
WHITESPACE : ' ' -> skip ;
MUL        : [*];
DIV        : [/];
PLUS       : [+];
MINUS      : [-];