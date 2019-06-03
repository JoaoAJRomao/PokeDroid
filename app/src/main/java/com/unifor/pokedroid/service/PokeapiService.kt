package com.unifor.pokedroid.service

import com.unifor.pokedroid.model.Pokemon
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * @author: João Arthur JR
 *interface fica resposanvel por manter os metodos de chamada ao pokeapi
 */

interface PokeapiService {

    @GET("pokemon/{id}/")
    fun getPokemonById(@Path("id") id: Int): Call<Pokemon>

    @GET("pokemon/")
    fun getAllPokemons(): Call<Pokemon>
}