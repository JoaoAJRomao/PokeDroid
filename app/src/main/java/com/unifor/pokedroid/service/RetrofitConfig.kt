package com.unifor.pokedroid.service

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitConfig {

    private val retrofit: Retrofit

    private val TAG = "POKEDEX"

    init {
        this.retrofit = Retrofit.Builder()
            .baseUrl("https://pokeapi.co/api/v2/").addConverterFactory(GsonConverterFactory.create()).build()
    }
    fun getPokemonService():PokemonService{
        return this.retrofit.create(PokemonService::class.java)
    }

}

/*
fun getPokemonService() {
        val service = this.retrofit.create(PokemonService::class.java)
        val respostaApi = service.getAllPokemons()
        respostaApi.enqueue(object : Callback<Named> {
            override fun onFailure(call: Call<Named>, t: Throwable) {
                Log.i(TAG, "onFailure: " + t.message)
            }

            override fun onResponse(call: Call<Named>, response: Response<Named>) {
                if (response.isSuccessful) {
                    for (i in response.body()!!.listaDeRetorno.indices) {
                        Log.i(TAG, "onResponse: " + response.body()!!.listaDeRetorno[i].name)
                    }
                } else {
                    Log.i(TAG, "onResponse: " + response.errorBody())
                }
            }

        })
    }
* */