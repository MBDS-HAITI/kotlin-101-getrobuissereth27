package com.android.BattleArena


fun main() {

    println(" ******  BATTLE ARENA ****** ")
    println(" Console Prototype ")
    println()


    // Step 1 – nom des joueurs
    print("Enter name for Player 1: ")
    val p1Name = readLine()?.trim()?.ifEmpty { "Player 1" } ?: "Player 1"

    print("Enter name for Player 2: ")
    val p2Name = readLine()?.trim()?.ifEmpty { "Player 2" } ?: "Player 2"

    val player1 = Player(p1Name)
    val player2 = Player(p2Name)


    // Step 2 –  creation d'equipe
    val setup = GameSetup(::readLine)
    setup.setup(player1, player2)


    // Step 3 – Combat
    val engine = CombatEngine(::readLine)
    engine.run(player1, player2)

    // Step 4 – fin du jeux
    EndScreen.display(player1, player2, engine.turnCount)
}