package com.unifor.pokedroid.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.widget.TextView
import com.unifor.pokedroid.R
import com.unifor.pokedroid.model.Pokemon
import com.unifor.pokedroid.service.PokeapiService
import com.unifor.pokedroid.service.RetrofitInitializer
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var pokeapiService: PokeapiService

    private lateinit var textView:TextView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        textView=findViewById(R.id.main_textView)

        pokeapiService = RetrofitInitializer.getPokeapiService()

        val pokemon = pokeapiService.getAllPokemons()

        pokemon.enqueue(pokemonCallbackHandler)


    }

    private val pokemonCallbackHandler = object:Callback<Pokemon>{
        override fun onFailure(call: Call<Pokemon>, t: Throwable) {
            Log.i("APP","Nao deu certo")
        }

        override fun onResponse(call: Call<Pokemon>, response: Response<Pokemon>) {
            Log.i("APP","${response.code()}")
            Log.i("APP","${response.body()}")
            textView.setText("${response.body()}").toString()
        }

    }
}
