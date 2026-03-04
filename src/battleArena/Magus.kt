package com.android.BattleArena

class Magus(name: String) : Character(
    name   = name,
    maxHp  = BASE_HP,
    weapon = Weapon(WEAPON_NAME, WEAPON_POWER)
), Attacker, Healer {

    override val typeName: String = "Magus"
    
    override fun attack(target: Character): Int = target.takeDamage(weapon.power)

    override fun heal(ally: Character): Int = ally.receiveHealing(HEAL_AMOUNT)

    companion object {
        const val BASE_HP      = 150
        const val WEAPON_NAME  = "Magic Staff"
        const val WEAPON_POWER = 15
        const val HEAL_AMOUNT  = 40
    }
}