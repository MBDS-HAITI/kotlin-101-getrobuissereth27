package com.android.BattleArena

class CombatEngine (private val readLine: () -> String?) {

    // Nombre total de tours joués ; accessible après la fin de la boucle.
    var turnCount: Int = 0
        private set

    // Lance la boucle de combat jusqu'à ce que toute l'équipe d'un joueur soit éliminée.
    // Alterne les tours entre [joueur1] et [joueur2].
    // 1. @param joueur1 le premier joueur (joue en premier)
    // 2. @param joueur2 le deuxième joueur

    fun run(player1: Player, player2: Player) {
        println("\n\n=== BATTLE START ===\n")

        var attacker = player1
        var defender = player2

        while (!player1.isDefeated() && !player2.isDefeated()) {
            turnCount++
            playTurn(attacker, defender)

            // Swap roles
            val temp = attacker
            attacker = defender
            defender = temp
        }
    }

    // ====================logique=========================

    // Exécute un seul tour pour le joueur actif.
    private fun playTurn(active: Player, opponent: Player) {
        println("\n${"─".repeat(60)}")
        println("  TURN $turnCount  |  ${active.name}'s move")
        println("─".repeat(60))

        printBothTeams(active, opponent)

        // Étape 1 : choisissez un personnage de l'équipe active
        val actor = pickAliveCharacter(active, "  Choose your character: ")

        // Étape 2 : choisir une action
        val action = pickAction(actor)

        // Étape 3 : exécuter l'action
        when (action) {
            1 -> {
                // Attack
                val target = pickAliveCharacter(opponent, "  Choose an enemy to attack: ")
                val dmg    = (actor as Attacker).attack(target)
                println("\n   ${actor.name} attacks ${target.name} for $dmg damage!")
                if (target.isDead()) println("  ${target.name} has been slain!")
            }
            2 -> {
                // Soigner (accessible uniquement si l'acteur implémente l'attribut Soigneur)
                val ally     = pickAliveCharacter(active, "  Choose an ally to heal: ")
                val restored = (actor as Healer).heal(ally)
                println("\n   ${actor.name} heals ${ally.name} for $restored HP!")
            }
        }
    }


    //Demande au joueur de sélectionner un de ses personnages vivants.

     //1. @param player le joueur effectuant la sélection
    // 2. @param prompt texte affiché avant la liste numérotée
    // 3. @return le personnage choisi

    private fun pickAliveCharacter(player: Player, prompt: String): Character {
        val alive = player.livingCharacters()
        println()
        println(prompt)
        alive.forEachIndexed { i, c -> println("    ${i + 1}. $c") }
        return alive[readInt(1, alive.size) - 1]
    }

    // Affiche le menu d'actions et renvoie l'option choisie.
    // L'option 2 (Soigner) n'est affichée que si le personnage implémente l'attribut [Soigneur].
    // @param actor le personnage effectuant l'action
    // @return 1 pour Attaquer, 2 pour Soigner

    private fun pickAction(actor: Character): Int {
        println("\n  What will ${actor.name} do?")
        println("    1. Attack an enemy")

        val canHeal = actor is Healer
        if (canHeal) println("    2. Heal an ally")

        return readInt(1, if (canHeal) 2 else 1)
    }

    // Display helpers

    // Affiche un aperçu rapide des HP des deux équipes.
    private fun printBothTeams(active: Player, opponent: Player) {
        println()
        printTeamStatus(active,   "  YOUR TEAM  (${active.name})")
        printTeamStatus(opponent, "  ENEMY TEAM (${opponent.name})")
    }

    //Affiche tous les personnages d'une équipe sous une étiquette d'en-tête.
    private fun printTeamStatus(player: Player, header: String) {
        println(header)
        player.team.forEach { c ->
            val prefix = if (c.isDead()) "  ✗  " else "  ○  "
            println("$prefix$c")
        }
        println()
    }


    // Lit un entier depuis la console, compris entre [[min] et [max]].
    // Continue d'afficher une invite jusqu'à ce qu'un nombre valide soit saisi.

    private fun readInt(min: Int, max: Int): Int {
        while (true) {
            print("  > Enter a number ($min-$max): ")
            val input = readLine()?.trim()
            val value = input?.toIntOrNull()
            if (value != null && value in min..max) return value
            println("  ! Invalid input. Please enter a number between $min and $max.")
        }
    }
}
