package com.unifor.pokedroid.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.squareup.picasso.Picasso
import com.unifor.pokedroid.R
import com.unifor.pokedroid.model.GetListPokemon

class PokemonAdapter(val context: Context, val lista:List<GetListPokemon>): RecyclerView.Adapter<PokemonAdapter.NamedViewHolder>(){


    private var layoutInflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): NamedViewHolder {

        val item = layoutInflater.inflate(R.layout.adapterlist_pokemon,p0,false)
//        val item = LayoutInflater.from(p0.context).inflate(R.layout.adapterlist_pokemon,p0,false)
        return NamedViewHolder(item)
    }



    override fun onBindViewHolder(p0: NamedViewHolder, p1: Int) {
        p0.nomePokemon.text=lista[p1].name
//        Picasso.get().load("http://i.imgur.com/DvpvklR.png").into(imageView);
        Picasso.get().load("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/${p1+1}.png").into(p0.imagemPokemon)
        Log.i("MostraUrlPokemon",lista[p1].url)
    }

    override fun getItemCount(): Int {
        return lista.size
    }


    class NamedViewHolder(item:View):RecyclerView.ViewHolder(item){
        var nomePokemon: TextView = item.findViewById(R.id.nomePokemon)
        var imagemPokemon: ImageView = item.findViewById(R.id.imagemPokemon)
    }


}