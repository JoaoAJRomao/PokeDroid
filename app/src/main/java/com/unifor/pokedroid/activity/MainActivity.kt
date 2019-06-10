package com.unifor.pokedroid.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
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

        ConfiguracaoInicial()


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
                recyclerView.adapter = PokemonAdapter(applicationContext,response.body()!!.listaDeRetorno)
//                recyclerView.addOnScrollListener(RecyclerView.OnScrollListener)

            } else {
                Log.i("onResponse", "response é null")
            }
        }

    }

    fun ConfiguracaoInicial(){
        pokemonService = RetrofitConfig.getPokemonService()
        recyclerView = findViewById(R.id.main_recyclerView)

//        recyclerView.addOnScrollListener()
        //usar biblioteca PICASSO para recuperar a imagem, passando apenas a url
        recyclerView.layoutManager = LinearLayoutManager(applicationContext)
        recyclerView.adapter = PokemonAdapter(applicationContext,listaFakePokemons)
    }
}
