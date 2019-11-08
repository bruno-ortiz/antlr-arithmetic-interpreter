package net.arithmetic

import net.arithmetic.ExpressionParser.ExprContext
import net.arithmetic.ExpressionParser.FactorContext
import net.arithmetic.ExpressionParser.TermContext
import org.antlr.v4.runtime.CharStreams
import org.antlr.v4.runtime.CommonTokenStream
import org.antlr.v4.runtime.tree.ErrorNode
import org.antlr.v4.runtime.tree.TerminalNode

class Interpreter : ExpressionBaseVisitor<Int>() {

    fun interpret(expression: String): Int {
        val lexer = ExpressionLexer(CharStreams.fromString(expression))
        val parser = ExpressionParser(CommonTokenStream(lexer)).apply {
            addErrorListener(SyntaxErrorListener)
        }

        return visit(parser.expr())
    }

    override fun visitExpr(ctx: ExprContext): Int {
        val leftTerm = ctx.term(0)
        val rightTerm = ctx.term(1)
        return when {
            ctx.PLUS(0) != null -> visit(leftTerm) + visit(rightTerm)
            ctx.MINUS(0) != null -> visit(leftTerm) - visit(rightTerm)
            else -> visit(leftTerm)
        }
    }

    override fun visitTerm(ctx: TermContext): Int {
        val leftFactor = ctx.factor(0)
        val rightFactor = ctx.factor(1)
        return when {
            ctx.MUL(0) != null -> visit(leftFactor) * visit(rightFactor)
            ctx.DIV(0) != null -> visit(leftFactor) / visit(rightFactor)
            else -> visit(leftFactor)
        }
    }

    override fun visitFactor(ctx: FactorContext): Int {
        val number: TerminalNode? = ctx.NUMBER()
        return if (number != null) {
            Integer.valueOf(number.text)
        } else {
            visit(ctx.expr())
        }
    }

    override fun visitErrorNode(node: ErrorNode): Int {
        throw IllegalArgumentException("Error interpreting expression, ${node.text}")
    }
}


fun main() {
    val interpreter = Interpreter()

    val result = interpreter.interpret("5 + (2 * 3)")

    println("Result: $result")
}