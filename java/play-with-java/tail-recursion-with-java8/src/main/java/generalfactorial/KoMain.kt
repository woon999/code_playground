package generalfactorial

fun tailFactorial(n: Int): Int {
    return tailFactorialFunc(1, n)
}

tailrec fun tailFactorialFunc(acc: Int, n: Int): Int {
    if (n == 1) {
        return acc
    }

    return tailFactorialFunc(acc * n, n - 1)
}

fun main() {
    println("recursion - factorial(10): ${tailFactorial(10)}");
    println("recursion - factorial(20000): ${tailFactorial(20000)}");
}

class KoMain {
}