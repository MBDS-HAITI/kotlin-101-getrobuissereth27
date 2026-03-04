package com.android.BattleArena

class Warrior(name: String) : Character(
    name   = name,
    maxHp  = BASE_HP,
    weapon = Weapon(WEAPON_NAME, WEAPON_POWER)
), Attacker {

    override val typeName: String = "Warrior"

    override fun attack(target: Character): Int = target.takeDamage(weapon.power)

    companion object {
        const val BASE_HP       = 120
        const val WEAPON_NAME   = "Iron Sword"
        const val WEAPON_POWER  = 30
    }
}