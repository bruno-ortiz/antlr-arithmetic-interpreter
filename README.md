# antlr-arithmetic-interpreter
A study case about writing an arithmetic interpreter with antlr4.

This project uses antlr4 to produce a lexer and parser to simple arithmetic expressions.

#How-to

The first step is make antlr generate the lexer and the parser for the interpreter.
To do so, we'll use a gradle plugin, so just run.  

```shell script
./gradlew clean generateGrammarSource
```

After that you can run the tests or modify the grammar as you see fit.

The grammar is located at **src/main/antlr/Expression.g4** and the interpreter is at **src\main\kotlin\net\arithmetic\Interpreter.kt** .
