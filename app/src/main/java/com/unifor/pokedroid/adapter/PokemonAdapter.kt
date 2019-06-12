package com.unifor.pokedroid.adapter

import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.unifor.pokedroid.R
import com.unifor.pokedroid.model.Named

class PokemonAdapter: RecyclerView.Adapter<PokemonAdapter.NamedViewHolder>(){



    var dataset = mutableListOf<Named>()

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): NamedViewHolder {

//        val item = layoutInflater.inflate(R.layout.adapterlist_pokemon,p0,false)
        val item = LayoutInflater.from(p0.context).inflate(R.layout.adapterlist_pokemon,p0,false)
        return NamedViewHolder(item)
    }

    override fun getItemCount(): Int {
        return dataset.size
    }

    override fun onBindViewHolder(p0: NamedViewHolder, p1: Int) {
        p0.nomePokemon.text=dataset[p1].listaDeRetorno[p1].name
        Log.i("MostraUrlPokemon",dataset[p1].listaDeRetorno[p1].url)

    }


    class NamedViewHolder(item:View):RecyclerView.ViewHolder(item){
        var nomePokemon: TextView
        var imagemPokemon: ImageView
        init {
            this.nomePokemon = item.findViewById(R.id.nomePokemon)
            this.imagemPokemon = item.findViewById(R.id.imagemPokemon)
        }
    }


}