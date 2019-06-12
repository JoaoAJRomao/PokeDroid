package com.unifor.pokedroid.service

import com.unifor.pokedroid.model.Named
import retrofit2.Call
import retrofit2.http.GET

interface PokemonService {
    @GET("pokemon/") //limit=quantos devem aparecer - offset=a partir de qual aparece(do array, comeca do 1 em diante) //pokemon/?limit=1000 => coloque isso entre as aspas, dentro de GET, e trará todos os pokemons
    fun getAllPokemons(): Call<Named>

}
