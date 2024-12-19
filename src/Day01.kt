import kotlin.io.path.Path
import kotlin.io.path.readText
import kotlin.math.abs

fun main() {
    val lines = Path("src/Day01.txt").readText().trim().lines()
    val list1 = mutableListOf<Int>()
    val list2 = mutableListOf<Int>()
    lines.forEach {
        val (left, right) = it.split("   ")
        list1.add(left.toInt())
        list2.add(right.toInt())
    }
    println("part1: ${part1(list1, list2)}")
    println("part2: ${part2(list1, list2)}")
}

fun part1(list1: List<Int>, list2: List<Int>): Int {
    val list1Sorted = list1.sorted()
    val list2Sorted = list2.sorted()
    var sum = 0
    list1Sorted.forEachIndexed { index, int ->
        sum += abs(int - list2Sorted[index])
    }
    return sum
}

fun part2(list1: List<Int>, list2: List<Int>): Int {
    val occurrenceMap = list2.groupingBy { it }.eachCount()
    var sum = 0
    list1.forEach {
        sum += it * (occurrenceMap[it] ?: 0)
    }
    return sum
}
