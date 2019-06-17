package com.unifor.pokedroid.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import com.squareup.picasso.Picasso
import com.unifor.pokedroid.R
import com.unifor.pokedroid.model.Ability
import com.unifor.pokedroid.model.Pokemon
import com.unifor.pokedroid.service.PokemonService
import com.unifor.pokedroid.service.RetrofitConfig
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class activity_pokemondetalhe : AppCompatActivity() {

    private lateinit var imagemPokemon:ImageView
    private lateinit var nome:TextView
    private lateinit var base:TextView
    private lateinit var peso:TextView
    private lateinit var altura:TextView


    private lateinit var pokemonService: PokemonService

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pokemondetalhe)

        var dados = intent
        pokemonService = RetrofitConfig.getPokemonService()
        val respostaPokemon = pokemonService.getPokemonById(dados.getIntExtra("posicao",1))
        val respostaPokemonAbility = pokemonService.getPokemonAbilityById(dados.getIntExtra("posicao",1))

        imagemPokemon = findViewById(R.id.segundatela_imageView)
        nome = findViewById(R.id.st_nome)
        base = findViewById(R.id.st_base)
        peso = findViewById(R.id.st_peso)
        altura = findViewById(R.id.st_altura)


        Picasso.get().load("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/${dados.getIntExtra("posicao",1)}.png").into(imagemPokemon)

        respostaPokemon.enqueue(object : Callback<Pokemon>{
            override fun onFailure(call: Call<Pokemon>, t: Throwable) {
                Log.wtf("MainActivity","wtf= What terrible Failure: "+t.message)
            }

            override fun onResponse(call: Call<Pokemon>, response: Response<Pokemon>) {
                nome.text=response.body()?.name?.toUpperCase()
                base.text=response.body()?.base_experience.toString()+" XP"
                peso.text=response.body()?.weight.toString()+" hectograma(s)"
                altura.text=response.body()?.height.toString()+" decímetro(s)"
            }
        })

        respostaPokemonAbility.enqueue(object:Callback<Ability>{
            override fun onFailure(call: Call<Ability>, t: Throwable) {
                Log.i("SegundaTela","erro ability: "+ t.message)
            }

            override fun onResponse(call: Call<Ability>, response: Response<Ability>) {
                Log.i("SegundaTela",response.body().toString())
            }

        })
    }
}
