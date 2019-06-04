package com.unifor.pokedroid.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.unifor.pokedroid.R
import com.unifor.pokedroid.model.Pokemon
import com.unifor.pokedroid.service.PokemonService
import com.unifor.pokedroid.service.RetrofitConfig
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    private lateinit var pokemonService: PokemonService

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        pokemonService = RetrofitConfig.getPokemonService()

        val listaPokemons = pokemonService.getAllPokemons()

        listaPokemons.enqueue(pokemonCallbackHandler)
    }
    private val pokemonCallbackHandler = object:Callback<Pokemon> {
        override fun onFailure(call: Call<Pokemon>?, t: Throwable?) {
            Log.i("onResponse","Falha no callback handler")
        }

        override fun onResponse(call: Call<Pokemon>?, response: Response<Pokemon>?) {
            if (response != null) {
                Log.i("onResponse",response.body().toString())
//                Log.i("onResponse",response.body().results.size.toString())
            }else{
                Log.i("onResponse","response é null")
            }
        }

    }
}
