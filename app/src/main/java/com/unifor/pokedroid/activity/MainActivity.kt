package com.unifor.pokedroid.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import com.unifor.pokedroid.R
import com.unifor.pokedroid.adapter.PokemonAdapter
import com.unifor.pokedroid.model.GetListPokemon
import com.unifor.pokedroid.model.Named
import com.unifor.pokedroid.service.PokemonService
import com.unifor.pokedroid.service.RetrofitConfig
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    private lateinit var pokemonService: PokemonService
    private lateinit var recyclerView: RecyclerView
    private var listaFakePokemons:MutableList<GetListPokemon> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        pokemonService = RetrofitConfig.getPokemonService()
        recyclerView = findViewById(R.id.main_recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(applicationContext)
        recyclerView.adapter = PokemonAdapter(applicationContext,listaFakePokemons)

        criarNomePokemon()


        val listaPokemons = pokemonService.getAllPokemons()

        listaPokemons.enqueue(pokemonCallbackHandler)

        Log.i("DaLista",listaFakePokemons.toString())
    }

    private val pokemonCallbackHandler = object : Callback<Named> {
        override fun onFailure(call: Call<Named>?, t: Throwable?) {
            Log.i("onResponse", "Falha no callback handler")
        }

        override fun onResponse(call: Call<Named>?, response: Response<Named>?) {
            if (response != null) {
                Log.i("onResponse",response.body().toString())
                recyclerView.adapter = PokemonAdapter(applicationContext,response.body()!!.listaDeRetorno) //Professor, se eu usar "response.body()?.listaDeRetorno", da erro. Pode explica?

            } else {
                Log.i("onResponse", "response é null")
            }
        }

    }

    fun criarNomePokemon(){
        var poke:GetListPokemon = GetListPokemon("Nome1","url01")
        listaFakePokemons.add(poke)

        poke = GetListPokemon("Nome02","url02")
        listaFakePokemons.add(poke)
    }
}
