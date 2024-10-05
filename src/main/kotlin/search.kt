package landon.lee

import java.io.File
import java.util.*

fun searchRecipes(){
    val scanner = Scanner(System.`in`)
    print("""
        
        What do you want to do?
        [1] Search by tag
        [2] Search by rating
        [3] Exit program
        >>> 
    """.trimIndent())
    val response = getNumber(scanner, true, 1, 3)
    when (response){
        1 -> searchByTag(scanner)
        2 -> searchByRating(scanner)
        3 -> print("")
    }
}

fun searchByTag(scanner:Scanner){
    print("""
                What tag do you want to search for?
                >>> 
    """.trimIndent())
    val matches = searchForTag(scanner.nextLine(), loadFiles())
    if (matches.isEmpty()){
        println("No matches found!")
    }else{
        val targetRecipe = selectARecipe(scanner, matches)
        doSomethingWithRecipe(scanner, targetRecipe)
    }
}

fun searchByRating(scanner:Scanner){
    print("""
                What is the minimum rating you want?
                >>> 
    """.trimIndent())
    val response = getNumber(scanner, true, 0, 10)
    val matches = searchForRating(response, loadFiles())
    if (matches.isEmpty()){
        println("No matches found!")
    }else{
        val targetRecipe = selectARecipe(scanner, matches, true)
        doSomethingWithRecipe(scanner, targetRecipe)
    }
}

fun searchForTag(tag: String, recipes: List<Recipe>): List<Recipe>{
    val matchingRecipes = mutableListOf<Recipe>()
    for (recipe in recipes){
        for (recipeTag in recipe.tags){
            if (tag == recipeTag){
                matchingRecipes.add(recipe)
                break // We can move on from looking at the tags in this recipe
                // as it has already been added to the return list
            }
        }
    }
    return matchingRecipes
}

fun searchForRating(rating:Int, recipes: List<Recipe>): List<Recipe>{
    val matchingRecipes = mutableListOf<Recipe>()
    for (recipe in recipes){
        if (recipe.rating >= rating){
            matchingRecipes.add(recipe)
        }
    }
    matchingRecipes.sortWith(compareBy { it.rating })
    return matchingRecipes
}

fun selectARecipe(scanner: Scanner, recipes: List<Recipe>, displayRating:Boolean = false): Recipe{
    println("Select one: ")
    for ((i, match) in recipes.withIndex()) {
        print("[" + (i + 1) + "] ")
        if (displayRating){
            print("(${match.rating}/10) ")
        }
        println(match.name)
    }
    print(">>> ")
    val targetRecipe = recipes[getNumber(scanner, true, 1, recipes.size)-1]
    return targetRecipe
}

fun doSomethingWithRecipe(scanner:Scanner, targetRecipe: Recipe){
    print("""
        What do you want to do?
        [1] View recipe
        [2] Edit recipe
        [3] Delete recipe
        >>> 
    """.trimIndent())
    val response = getNumber(scanner, true, 1, 3)
    when (response) {
        1 -> printRecipe(targetRecipe)
        2 -> {
            val f = File(getDirectoryToRecipeFolder() + "\\" + targetRecipe.name + ".json")
            openFile(f)
        }

        3 -> {
            val f = File(getDirectoryToRecipeFolder() + "\\" + targetRecipe.name + ".json")
            f.delete()
        }
    }
}