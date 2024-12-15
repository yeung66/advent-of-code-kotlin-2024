import kotlin.io.path.Path
import kotlin.io.path.readText

/**
 * Reads lines from the given input txt file.
 */
fun readInput(name: String) = Path("src/input/$name.txt").readText().trim().lines()


fun Any.println() = println(this)