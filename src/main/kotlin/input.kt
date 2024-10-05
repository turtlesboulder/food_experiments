package landon.lee

import java.util.*

fun newFile(): Recipe{
    val s = Scanner(System.`in`)

    // Each of these methods here output a prompt and grab the appropriate input.
    // This is gathering all the data to make a member of the recipe class.
    val recipe = Recipe(getName(s), getTags(s), getRating(s),
        getIngredients(s), getInstructions(s), getNote(s))
    
    return recipe
}

fun getName(scanner: Scanner):String{
    print("""
        
        What is the name of the recipe?
        >>>
    """.trimIndent())
    // Kill all the white space as file names can't have it;
    // The name will be used for the file name.
    return scanner.nextLine().replace("\\s+".toRegex(), "")
}
fun getTags(scanner: Scanner):List<String>{
    val tags = mutableListOf<String>()
    print("""
        
        Please enter tags, pressing the enter key after each one.
        Enter DONE when finished.
        >>> 
    """.trimIndent())
    return inputMultipleLines(scanner, true)
}
fun getRating(scanner: Scanner):Int{
    print("""
        
        Please give the recipe a rating [0-10].
        >>> 
    """.trimIndent())
    return getNumber(scanner, true, 0, 10)
}
fun getIngredients(scanner: Scanner):List<String>{
    print("""
        
        Please enter ingredients and quantity, pressing the enter key after each one.
        For example, "2lb chicken breast"
        Enter DONE when finished.
        >>> 
    """.trimIndent())
    return inputMultipleLines(scanner)
}
fun getInstructions(scanner: Scanner):List<String>{
    print("""
        
        Please enter the instructions for the recipe, one step at a time.
        Enter DONE when finished.
        >>> 
    """.trimIndent())
    return inputMultipleLines(scanner)
}
fun getNote(scanner: Scanner):String{
    print("""
        
        Please enter any note for the recipe [can be blank].
        >>> 
    """.trimIndent())
    return scanner.nextLine()
}

fun getNumber(scanner: Scanner, hasRange:Boolean = false, min:Int = 0, max:Int = 0): Int{
    while (true){
        try {
            val num = scanner.nextInt()
            scanner.nextLine()
            if (hasRange){
                if (num < min || num > max){
                    print("Out of the range $min to $max. Try again!\n>>> ")
                    continue
                }
            }
            return num
        }catch (e:InputMismatchException){
            print("Not a number. Try again!\n>>> ")
            scanner.nextLine()
        }
    }
}

fun inputMultipleLines(scanner: Scanner, transformToLowerCase: Boolean = false):List<String>{
    val responseLines = mutableListOf<String>()
    while (true){
        var response = scanner.nextLine().trim()
        if (response.lowercase() == "done"){
            break
        }
        if (response.lowercase() == "back" && responseLines.isNotEmpty()){
            responseLines.removeAt(responseLines.size-1)
            println("Last line removed!")
        }
        if (transformToLowerCase){
            response = response.lowercase()
        }
        responseLines.add(response)
        print(">>> ")
    }
    return responseLines
}
