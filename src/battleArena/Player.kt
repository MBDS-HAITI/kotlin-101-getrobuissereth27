package com.android.BattleArena



class Player (val name: String) {

    // L'équipe du joueur – toujours 3 personnages
    private val _team = mutableListOf<Character>()
    val team: List<Character> get() = _team

    // -===================gestion des equipes============================
    // Ajoute un personnage à l'équipe de ce joueur.
    // Doit être appelé exactement 3 fois lors de la mise en place.
    fun addCharacter(character: Character) {
        _team.add(character)
    }

    // Ne renvoie que les personnages vivants (PV > 0) de cette équipe.
    fun livingCharacters(): List<Character> = _team.filter { !it.isDead() }

    // @return vrai si tous les personnages de l'équipe sont morts
    fun isDefeated(): Boolean = livingCharacters().isEmpty()
}