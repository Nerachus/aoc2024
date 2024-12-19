import kotlin.io.path.Path
import kotlin.io.path.readText

fun main() {
    val lines = Path("src/Day03.txt").readText().trim().lines()
    println("part1: ${part1(lines)}")
    println("part2: ${part2(lines)}")
}

fun part1(memory: List<String>): Long {
    val regex = Regex("mul\\((\\d+),(\\d+)\\)")
    var sum = 0L
    memory.forEach {
        regex.findAll(it).asIterable().forEach { instruction ->
            val (_, first, second) = instruction.groupValues
            sum += first.toInt() * second.toInt()
        }
    }
    return sum
}

fun part2(memory: List<String>): Long {
    val regex = Regex("mul\\((\\d+),(\\d+)\\)|do\\(\\)|don't\\(\\)")
    var sum = 0L
    var enabled = true
    memory.forEach {
        regex.findAll(it).asIterable().forEach { instruction ->
            if (instruction.value.startsWith("do")) {
                if (instruction.value == "do()")
                    enabled = true
                else if (instruction.value == "don't()") {
                    enabled = false
                }
            } else if (enabled) {
                val (_, first, second) = instruction.groupValues
                sum += first.toInt() * second.toInt()
            }
        }
    }
    return sum
}
