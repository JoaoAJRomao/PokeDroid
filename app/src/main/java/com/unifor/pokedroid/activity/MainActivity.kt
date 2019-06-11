package com.unifor.pokedroid.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import com.unifor.pokedroid.R
import com.unifor.pokedroid.model.Named
import com.unifor.pokedroid.service.PokemonService
import com.unifor.pokedroid.service.RetrofitConfig
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    private lateinit var pokemonService: PokemonService
    private lateinit var recyclerView: RecyclerView
    val listaDePokemons = mutableListOf<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        pokemonService = RetrofitConfig.getPokemonService()
        recyclerView = findViewById(R.id.main_recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(applicationContext)


        val listaDePokemons = pokemonService.getAllPokemons()

        listaDePokemons.enqueue(pokemonCallbackHandler)


    }

    private val pokemonCallbackHandler = object : Callback<Named> {
        override fun onFailure(call: Call<Named>, t: Throwable) {
            Log.i("Depuracao", "Erro: " + t.message)
        }

        override fun onResponse(call: Call<Named>, response: Response<Named>) {
//            val poekmon = pokemonService.getPokemonByUrl(response.body()!!.listaDeRetorno!!.size)
//            Log.i("Resultado",response.body()!!.listaDeRetorno[1].url)
            for (i in response.body()!!.listaDeRetorno.indices) {
//                Log.i("Resultado",response.body()!!.listaDeRetorno[i].url)// mostra a url de cada pokemon, separadamente
                listaDePokemons.add(response.body()!!.listaDeRetorno[i].url)//manda as urls pro adapter, e lá ele chama o picasso
            }
        }

    }
}
