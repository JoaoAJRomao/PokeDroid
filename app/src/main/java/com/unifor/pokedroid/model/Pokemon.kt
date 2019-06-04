package com.unifor.pokedroid.model

data class Pokemon (
    val count:Int,
    val next:String,
    val previous:String,
    val results: List<GetListPokemon>
)

data class GetListPokemon (
    val name: String,
    val url:String
)

