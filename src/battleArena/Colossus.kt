package com.android.BattleArena

class Colossus (name: String) : Character(
    name   = name,
    maxHp  = BASE_HP,
    weapon = Weapon(WEAPON_NAME, WEAPON_POWER)
), Attacker {

    override val typeName: String = "Colossus"

    // Inflige des dégâts directs à la cible, équivalents à la puissance de l'arme.
    // @param target le personnage ennemi touché
    // @return les PV réellement retirés à la cible
    override fun attack(target: Character): Int = target.takeDamage(weapon.power)

    companion object {
        const val BASE_HP      = 200
        const val WEAPON_NAME  = "War Hammer"
        const val WEAPON_POWER = 25
    }
}