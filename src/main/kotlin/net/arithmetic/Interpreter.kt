package net.arithmetic

import net.arithmetic.ExpressionParser.InfixExprContext
import net.arithmetic.ExpressionParser.NumberExprContext
import net.arithmetic.ExpressionParser.ParensExprContext
import org.antlr.v4.runtime.CharStreams
import org.antlr.v4.runtime.CommonTokenStream

class Interpreter : ExpressionBaseVisitor<Int>() {

    fun interpret(expression: String): Int {
        val lexer = ExpressionLexer(CharStreams.fromString(expression))
        val parser = ExpressionParser(CommonTokenStream(lexer)).apply {
            addErrorListener(SyntaxErrorListener)
        }

        return visit(parser.expr())
    }

    override fun visitInfixExpr(ctx: InfixExprContext): Int {
        return when {
            ctx.op.text == "*" -> visit(ctx.left) * visit(ctx.right)
            ctx.op.text == "/" -> visit(ctx.left) / visit(ctx.right)
            ctx.op.text == "+" -> visit(ctx.left) + visit(ctx.right)
            ctx.op.text == "-" -> visit(ctx.left) - visit(ctx.right)
            else -> throw IllegalStateException("Unknown operator ${ctx.op.text}")
        }
    }

    override fun visitNumberExpr(ctx: NumberExprContext): Int {
        return Integer.valueOf(ctx.value.text)
    }

    override fun visitParensExpr(ctx: ParensExprContext): Int {
        return visit(ctx.expr())
    }
}
