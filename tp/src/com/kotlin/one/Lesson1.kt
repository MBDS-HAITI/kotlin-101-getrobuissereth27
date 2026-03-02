package com.android.one

fun main() {

    println("👋 Welcome to the Kotlin Playground!")
    println("Let's start learning step by step.\n")


    // ✅ EXERCISE 1 Variables:
    // Create two variables: `city` (String) and `temperature` (Double)
    val city: String = "Port-au-Prince"   // immutable
    var temperature: Double = 30.5        // mutable
    // Then print: "It is {temperature}°C in {city}"
    println("It is ${temperature}°C in $city")
    // Enforce `city` to be immutable and `temperature` mutable
    temperature = 22.0
    // Then print the sentence again after changing `temperature`
    println("It is ${temperature}°C in $city")



    // ✅ EXERCISE 2 Conditionals:
    // Create a variable `score` (Int)
    val score: Int = 75
    // Handle the following cases:
    // - If score is exactly 100, print "Perfect score!"
    if (score == 100) {
        println("Perfect score!")
        // - If score is below 0 or above 100, print "Invalid score"
    } else if (score < 0 || score > 100) {
        println("Invalid score")
        // - If score between 0 and 49, print "You failed!"
    } else if (score in 0..49) {
        println("You failed!")
        // - If score between 50 and 60, print "Just passed!"
    } else if (score in 50..60) {
        println("Just passed!")
        // - If score between 61 and 99, print "Well done!"
    } else if (score in 61..99) {
        println("Well done!")
    }

    // ✅ EXERCISE 3 list and Loops:
    // Create a list of your favorite fruits
    val fruits = listOf("mango", "banana", "orange")
    // Loop through the list and print each fruit in uppercase
    for (fruit in fruits) {
        println(fruit.uppercase())
    }
    // Then, print the total number of fruits in the list
    println("Total fruits: ${fruits.size}")
    // Ask the user to enter a fruit name and check if it's in the list
    print("Enter a fruit: ")
    val input = readLine()

    if (input != null && fruits.contains(input.lowercase())) {
        println("Fruit is in the list!")
    } else {
        println("Fruit not found.")
    }

    // ✅EXERCISE 4 Elvis Operator:
    // Create a nullable variable `nickname` of type String? and assign it null
    val nickname: String? = null
    // Print the number of characters in `nickname`
    println("Length: ${nickname?.length}")
    // Print the nickname or "No nickname provided" if it's null using the Elvis operator
    val result = nickname ?: "No nickname provided"
    println(result)

}



