package com.unifor.pokedroid.service

import com.unifor.pokedroid.model.Ability
import com.unifor.pokedroid.model.Named
import com.unifor.pokedroid.model.Pokemon
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface PokemonService {
    //limit=quantos devem aparecer - offset=a partir de qual aparece(do array, comeca do 1 em diante) //pokemon/?limit=1000 => coloque isso entre as aspas, dentro de GET, e trará todos os pokemons
    @GET("pokemon/?limit=1000")
    fun getAllPokemons(): Call<Named>

    @GET("pokemon/{id}/")
    fun getPokemonById(@Path("id") id: Int): Call<Pokemon>

    @GET("ability/{id}")
    fun getPokemonAbilityById(@Path("id") id: Int): Call<Ability>
}
