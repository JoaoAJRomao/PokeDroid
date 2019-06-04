package com.unifor.pokedroid.service

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitConfig {

    private val retrofit: Retrofit

    init {
        this.retrofit = Retrofit.Builder()
            .baseUrl("https://pokeapi.co/api/v2/").addConverterFactory(GsonConverterFactory.create()).build()
    }

    fun getPokemonService():PokemonService{
        return this.retrofit.create(PokemonService::class.java)
    }

}