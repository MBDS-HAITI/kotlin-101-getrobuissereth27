package com.android.BattleArena

data class Weapon (
    val name: String,
    val power: Int
) {
    override fun toString(): String = "$name (power: $power)"
}