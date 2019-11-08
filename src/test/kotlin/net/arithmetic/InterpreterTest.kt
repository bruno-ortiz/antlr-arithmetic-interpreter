package net.arithmetic

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class InterpreterTest {

    @Test
    internal fun `test can interpret expression`() {
        val expression = "5 + 2 * 3"
        val interpreter = Interpreter()

        val result = interpreter.interpret(expression)

        assertEquals(11, result)
    }

    @Test
    internal fun `test can interpret expression with multiple terms`() {
        val expression = "5 + 2 - 3"
        val interpreter = Interpreter()

        val result = interpreter.interpret(expression)

        assertEquals(4, result)
    }

    @Test
    internal fun `test can interpret another expression`() {
        val expression = "8 - 2 + 2 * 6 / 3"
        val interpreter = Interpreter()

        val result = interpreter.interpret(expression)

        assertEquals(10, result)
    }

    @Test
    internal fun `test can interpret expression with parenthesis`() {
        val expression = "(8 + (5 - 2) * 6) / 2"
        val interpreter = Interpreter()

        val result = interpreter.interpret(expression)

        assertEquals(13, result)
    }

    @Test
    internal fun `test cannot interpret broken expression`() {
        val expression = "8 - * 2 + 2"
        val interpreter = Interpreter()

        assertThrows<IllegalArgumentException> { interpreter.interpret(expression) }
    }
}