package com.unifor.pokedroid.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.unifor.pokedroid.R
import com.unifor.pokedroid.model.GetListPokemon
import com.unifor.pokedroid.model.Pokemon
import com.unifor.pokedroid.service.PokemonService
import com.unifor.pokedroid.service.RetrofitConfig
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PokemonAdapter(val context:Context, val lista:List<GetListPokemon>): RecyclerView.Adapter<PokemonAdapter.NamedViewHolder>(){

    private var layoutInflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
    private lateinit var pokemon: PokemonService

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): NamedViewHolder {
        pokemon = RetrofitConfig.getPokemonService()
        val item = layoutInflater.inflate(R.layout.adapterlist_pokemon,p0,false)
        return NamedViewHolder(item)
    }

    override fun getItemCount(): Int {
        return lista.size
    }

    override fun onBindViewHolder(p0: NamedViewHolder, p1: Int) {
        p0.nomePokemon.text=lista[p1].name
        Log.i("MostraUrlPokemon",lista[p1].url)
        val pokemonEspecifico = pokemon.getPokemonByUrl(lista[p1].url)
        pokemonEspecifico.enqueue(pokemonEspecificoCallbackHandler)

    }

    class NamedViewHolder(item:View):RecyclerView.ViewHolder(item){
        var imagem: ImageView
        var nomePokemon: TextView
        init {
            this.imagem=item.findViewById(R.id.imagemPokemon)
            this.nomePokemon = item.findViewById(R.id.nomePokemon)
        }
    }

    private val pokemonEspecificoCallbackHandler = object : Callback<Pokemon> {
        override fun onFailure(call: Call<Pokemon>, t: Throwable) {
            Log.i("Depuracao","Falha na consulta mais interna: "+t.message)
        }

        override fun onResponse(call: Call<Pokemon>, response: Response<Pokemon>) {
            if(response.isSuccessful){
                Log.i("Depuracao",response.body()!!.sprites.toString())
            }
        }

    }

}