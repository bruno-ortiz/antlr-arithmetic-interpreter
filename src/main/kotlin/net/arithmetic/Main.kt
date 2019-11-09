package net.arithmetic

fun main() {
    val interpreter = Interpreter()

    val result = interpreter.interpret("5 + (2 * 3)")

    println("Result: $result")
}
