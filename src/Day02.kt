import kotlin.io.path.Path
import kotlin.io.path.readText
import kotlin.math.abs

fun main() {
    val lines = Path("src/Day02.txt").readText().trim().lines()
    val reports = mutableListOf<List<Int>>()
    lines.forEach { line ->
        val report = line.split(" ")
        reports.add(report.map { it.toInt() })
    }
    println("part1: ${part1(reports)}")
    println("part2: ${part2(reports)}")
}

enum class Ordering { UNKNOWN, ASCENDING, DESCENDING }

fun part1(reports: List<List<Int>>): Int {
    var numOfSafeReports = 0
    reports.forEach { report ->
        var safe = true
        var ordering = Ordering.UNKNOWN
        report.windowed(2, 1, false) { (first, second) ->
            val diff = first - second
            val saveDiff = abs(diff) in 1..3
            if (!saveDiff) safe = false
            if (ordering == Ordering.UNKNOWN) {
                ordering = if (diff > 0) Ordering.ASCENDING else Ordering.DESCENDING
            } else if (ordering == Ordering.ASCENDING && diff < 0) {
                safe = false
            } else if (ordering == Ordering.DESCENDING && diff > 0) {
                safe = false
            }
        }
        if (safe) numOfSafeReports++
    }
    return numOfSafeReports
}

fun part2(reports: List<List<Int>>): Int {
    var numOfSafeReports = 0
    reports.forEach { report ->
        val anySafe = report.indices.any { blockIndex ->
            var safe = true
            var ordering = Ordering.UNKNOWN
            val reducedReport = report.filterIndexed { index, _ -> index != blockIndex }
            reducedReport.windowed(2, 1, false) { (first, second) ->
                val diff = first - second
                val saveDiff = abs(diff) in 1..3
                if (!saveDiff) safe = false
                if (ordering == Ordering.UNKNOWN) {
                    ordering = if (diff > 0) Ordering.ASCENDING else Ordering.DESCENDING
                } else if (ordering == Ordering.ASCENDING && diff < 0) {
                    safe = false
                } else if (ordering == Ordering.DESCENDING && diff > 0) {
                    safe = false
                }
            }
            safe
        }
        if (anySafe) numOfSafeReports++
    }
    return numOfSafeReports
}
