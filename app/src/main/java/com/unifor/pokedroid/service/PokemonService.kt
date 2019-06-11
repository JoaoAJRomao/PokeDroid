package com.unifor.pokedroid.service

import com.unifor.pokedroid.model.Named
import com.unifor.pokedroid.model.Pokemon
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface PokemonService {
    @GET("pokemon/?limit=1000") //limit=quantos devem aparecer - offset=a partir de qual aparece(do array, comeca do 1 em diante)
    fun getAllPokemons(): Call<Named>

    @GET("pokemon/{id}")
    fun getPokemonById(@Path("id") id: Int): Call<Pokemon>

    @GET("{url}")
    fun getPokemonByUrl(@Path("url") url: String): Call<Pokemon>

}
