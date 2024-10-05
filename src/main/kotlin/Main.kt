package landon.lee
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
import kotlinx.serialization.encodeToString
import kotlinx.serialization.decodeFromString
import java.io.*
import java.util.Scanner
import java.awt.Desktop

fun main(){
    val scanner = Scanner(System.`in`)
    print("""
        
        What would you like to do?
        [1] Create a new recipe
        [2] Search recipes
        [3] Open folder
        [4] Exit program
        >>> 
    """.trimIndent())
    val response = getNumber(scanner, true, 1, 4)
    when (response){
        1 -> write(newFile())
        2 -> searchRecipes()
        3 -> openRecipeFolder()
        4 -> print("")
        else -> {
            println("Invalid input!")
        }
    }
}
fun openRecipeFolder(){
    val directory = getDirectoryToRecipeFolder()
    val file = File(directory)
    openFile(file)
}

fun printRecipe(recipe:Recipe){
    println("==============================")
    println(recipe.name)
    println("Tags: ${recipe.tags}")
    println("Rating: ${recipe.rating}/10")
    println("---- Ingredients ----")
    for (ingredient in recipe.ingredients){
        println(ingredient)
    }
    println("----Instructions ----")
    for (step in recipe.instructions){
        println(step)
    }
    println("---------------------")
    println(recipe.note)
    println("==============================")
}
fun loadFiles(): MutableList<Recipe>{
    val directory = getDirectoryToRecipeFolder()
    val folder = File(directory)
    val folderWalker = folder.walkTopDown()
    var skip = true // Skip the first entry in the list
    val recipes = mutableListOf<Recipe>()

    for (f:File in folderWalker){
        if (skip){
            skip = false
            continue
            // Just skip the first file as it's the parent
        }
        recipes.add(read(f))
    }
    return recipes
}


fun read(f: File): Recipe{
    val text = f.readText()
    return Json.decodeFromString<Recipe>(text)
}

fun write(recipe: Recipe){
    val name = "\\"+ recipe.name + ".json"
    val jsonString = Json.encodeToString(recipe)

    val directory = getDirectoryToRecipeFolder() + name

    val file = File(directory)
    file.writeText(jsonString)
}

fun openFile(file:File){
    if (!Desktop.isDesktopSupported()) {
        println("Not supported!")
        return
    }
    val desktop = Desktop.getDesktop()
    try {
        if (file.exists()) {
            desktop.open(file)  // This will open the file with the default application
        } else {
            println("File does not exist: ${file.absolutePath}")
        }
    } catch (e: IOException) {
        e.printStackTrace()
    }
}

fun getDirectoryToRecipeFolder():String{
    val baseDirectory = System.getProperty("user.dir")
    val directory = "$baseDirectory\\recipes"
    return directory
}

@Serializable
data class Recipe(var name: String, var tags: List<String>, var rating: Int,
                  var ingredients: List<String>, var instructions: List<String>,
                  var note: String)