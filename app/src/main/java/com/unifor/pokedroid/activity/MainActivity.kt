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

    private lateinit var mainCodeId:TextView
    private lateinit var mainId:TextView
    private lateinit var mainName:TextView
    private lateinit var mainBaseExperience:TextView
    private lateinit var mainHeight:TextView
    private lateinit var mainAbilities:TextView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mainCodeId=findViewById(R.id.main_codeId)
        mainId=findViewById(R.id.main_id)
        mainName=findViewById(R.id.main_name)
        mainBaseExperience=findViewById(R.id.main_baseExperience)
        mainHeight=findViewById(R.id.main_height)
        mainAbilities=findViewById(R.id.main_abilities)

        pokeapiService = RetrofitInitializer.getPokeapiService()

        val pokemon = pokeapiService.getAllPokemons()
        val pokemonById = pokeapiService.getPokemonById(1)

        pokemon.enqueue(pokemonCallbackHandler)
//        pokemonById.enqueue(pokemonCallbackHandler)
    }

    private val pokemonCallbackHandler = object:Callback<Pokemon>{
        override fun onFailure(call: Call<Pokemon>, t: Throwable) {
            Log.i("APP","Nao deu certo")
        }

        override fun onResponse(call: Call<Pokemon>, response: Response<Pokemon>) {
            Log.i("APP","${response.code()}")
            Log.i("APP","${response.body()}")
//            textView.setText("${response.body()}").toString()
            mainCodeId.text=response.code().toString()
            mainId.text=response.body()?.id.toString()
            mainName.text=response.body()?.name.toString()
            mainBaseExperience.text=response.body()?.baseExperience.toString()
            mainHeight.text=response.body()?.height.toString()
            mainAbilities.text=response.body()?.abilities.toString()

            Log.i("REST",response.body().toString())
        }

    }
}
