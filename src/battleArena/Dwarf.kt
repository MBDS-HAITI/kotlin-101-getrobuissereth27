package com.android.BattleArena

class Dwarf (name: String) : Character(
    name   = name,
    maxHp  = BASE_HP,
    weapon = Weapon(WEAPON_NAME, WEAPON_POWER)
), Attacker {

    override val typeName: String = "Dwarf"

    // Inflige la puissance de l'arme en dégâts directs à la cible.
    // Risque élevé, récompense élevée: le Nain frappe le plus fort, mais meurt le plus vite.
    // @param target le personnage ennemi à toucher
    // @return les PV réellement retirés à la cible

    override fun attack(target: Character): Int = target.takeDamage(weapon.power)

    companion object {
        const val BASE_HP      = 60
        const val WEAPON_NAME  = "Battle Axe"
        const val WEAPON_POWER = 50
    }
}