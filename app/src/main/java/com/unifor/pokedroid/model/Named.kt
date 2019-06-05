package com.unifor.pokedroid.model

import com.google.gson.annotations.SerializedName

data class Named(
    val count: Int,
    val next: String,
    val previous: String,
    val results: List<GetListPokemon>
)

data class GetListPokemon(
    val name: String,
    val url: String
)

data class Pokemon(
    val id: Int,
    val name: String,
    val base_experience: Int,
    val height: Int,
    val weight: Int,
    val abilities: List<PokemonAbility>,
    val sprites: PokemonSprites,
    val types:List<PokemonType>
)

data class PokemonAbility (
    @SerializedName("is_hidden")
    val isHidden: Boolean,
    val slot: Int,
    val ability: Ability
)

data class Ability (
    val id:Int,
    val name:String,
    @SerializedName("is_main_series")
    val isMainSeries: Boolean,
    val generation: Generation,
    val names: Name
    /*@SerializedName("effect_entries") //Procurar no final da documentação
    val effectEntries: VerboseEffect,
    @SerializedName("effect_changes")
    val effectChanges: AbilityEffectChange*/
)

data class PokemonSprites(
    @SerializedName("front_default")
    val frontDefault:String
)

data class PokemonType(
    val slot:String,
    val type:Type,
    val moves: List<Move>
)

data class Type(
    val id: Int,
    val name: String
)

data class Move(
    val id:Int,
    val name: String,
    val accuracy:Int,
    @SerializedName("effect_chance")
    val effectChance: Int,
    val pp:Int,
    val power: Int
)

data class Name(
    val name:String,
    val language: Language
)

data class Language(
    val id:Int,
    val name:String,
    val official: Boolean
)

data class Generation(
    val id:Int,
    val name:String,
    val abilities:List<Ability>
)
