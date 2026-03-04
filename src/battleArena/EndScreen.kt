package com.android.BattleArena

object EndScreen {
   // ======================Affichage du résumé complet de fin de partie dans la console.=======================
    // 1. @param player1 le premier joueur
    // 1. @param player2 le deuxième joueur
    // 1. @param turnCount le nombre total de tours joués

    fun display(player1: Player, player2: Player, turnCount: Int) {
        val winner = if (player1.isDefeated()) player2 else player1
        val loser  = if (player1.isDefeated()) player1 else player2

        println("\n${"═".repeat(60)}")
        println("  GAME OVER")
        println("═".repeat(60))
        println()
        println("   Winner: ${winner.name}")
        println("   Turns played: $turnCount")
        println()

        printTeamSummary(winner, "Victorious Team")
        printTeamSummary(loser,  "Defeated Team")

        println("═".repeat(60))
        println("  Thanks for playing Battle Arena!")
        println("═".repeat(60))
    }

    // Affiche tous les statuts des personnages d'une équipe donnée sous un en-tête de section.
    private fun printTeamSummary(player: Player, label: String) {
        println("  --- $label (${player.name}) ---")
        player.team.forEach { c ->
            val icon = if (c.isDead()) "✗" else "✔"
            println("    $icon  $c")
        }
        println()
    }
}