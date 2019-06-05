package com.unifor.pokedroid.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.unifor.pokedroid.R
import com.unifor.pokedroid.model.GetListPokemon
import com.unifor.pokedroid.model.Named

//O pokemonAdapter deve receber a lista que esta dentro de Named( val results: List<GetListPokemon>)
class PokemonAdapter(val context:Context, val lista:List<GetListPokemon>): RecyclerView.Adapter<PokemonAdapter.NamedViewHolder>(){

    private var layoutInflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): NamedViewHolder {
        val item = layoutInflater.inflate(R.layout.adapterlist_pokemon,p0,false)
        return NamedViewHolder(item)
    }

    override fun getItemCount(): Int {
        return lista.size
    }

    override fun onBindViewHolder(p0: NamedViewHolder, p1: Int) {
        p0.nomePokemon.text=lista[p1].name
    }

    class NamedViewHolder(item:View):RecyclerView.ViewHolder(item){
        var imagem: ImageView
        var nomePokemon: TextView
        init {
            this.imagem=item.findViewById(R.id.imagemPokemon)
            this.nomePokemon = item.findViewById(R.id.nomePokemon)
        }
    }

}