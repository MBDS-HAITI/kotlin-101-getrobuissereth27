package com.android.BattleArena

abstract class Character (
    val name: String,
    val maxHp: Int,
    val weapon: Weapon
) {

    // État (encapsulé – modifié uniquement par des méthodes dédiées)
    //Points de vie actuels – tombent à 0 à la mort, peuvent être restaurés par des soins. *

    var currentHp: Int = maxHp
        private set  // only this class can assign directly

    // Abstract property (forces subclasses to declare their type label)
    // Étiquette de type lisible par l'humain, par exemple « Guerrier », « Mage », « Colosse », « Nain ».
    abstract val typeName: String

    //=================== gestion HP===================================

    // Réduit les PV actuels de [dégâts]. Les PV ne descendront jamais en dessous de 0.
    // @param damage dégâts bruts à appliquer (doit être >= 0)
    // @return les PV réellement perdus après application de la limitation
    fun takeDamage(damage: Int): Int {
        val actual = minOf(damage, currentHp)
        currentHp -= actual
        return actual
    }

    // Restaure les PV actuels de [amount]. Les PV ne dépasseront jamais [maxHp].
    // @param amount PV à restaurer (doit être >= 0)
    // @return les PV réellement récupérés après la limitation

    fun receiveHealing(amount: Int): Int {
        val actual = minOf(amount, maxHp - currentHp)
        currentHp += actual
        return actual
    }

    /** @return true if this character has been reduced to 0 HP */
    fun isDead(): Boolean = currentHp <= 0


    // Courte ligne d'état utilisée dans les menus et le résumé de fin de partie.
    // Exemple: «Arthur [Guerrier] PV: 85/120 Arme: Épée de fer (puissance: 30)»

    override fun toString(): String {
        val status = if (isDead()) "DEAD" else "HP: $currentHp/$maxHp"
        return "%-12s [%-8s]  %-18s  Weapon: %s".format(name, typeName, status, weapon)
    }
}