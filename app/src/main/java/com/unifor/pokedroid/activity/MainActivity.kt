package com.unifor.pokedroid.activity

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.Toast
import com.unifor.pokedroid.R
import com.unifor.pokedroid.adapter.PokemonAdapter
import com.unifor.pokedroid.model.Named
import com.unifor.pokedroid.service.PokemonService
import com.unifor.pokedroid.service.RecyclerItemClickListener
import com.unifor.pokedroid.service.RetrofitConfig
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    private lateinit var pokemonService: PokemonService
    private lateinit var recyclerView: RecyclerView
    val TAG = "POKEDEX"


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        pokemonService = RetrofitConfig.getPokemonService()
        recyclerView = findViewById(R.id.main_recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(applicationContext)

        val respostaGeralApi = pokemonService.getAllPokemons()

        respostaGeralApi.enqueue(resCallbackHandler)

    }

    val resCallbackHandler = object : Callback<Named> {
        override fun onFailure(call: Call<Named>, t: Throwable) {
            Log.i("onFailure", t.message)
        }

        override fun onResponse(call: Call<Named>, response: Response<Named>) {
            recyclerView.adapter = PokemonAdapter(applicationContext, response.body()!!.listaDeRetorno)

            recyclerView.addOnItemTouchListener(
                RecyclerItemClickListener(applicationContext, recyclerView, object : RecyclerItemClickListener.OnItemClickListener {
                        override fun onItemClick(view: View, position: Int) {
//                            Toast.makeText(this@MainActivity , "Você deu 1 click, pokemon:  $position ", Toast.LENGTH_SHORT).show()
                            Toast.makeText(this@MainActivity , "Você deu 1 click, pokemon:  ${response.body()!!.listaDeRetorno[position].name}  ", Toast.LENGTH_SHORT).show()
                        }
                        override fun onLongItemClick(view: View?, position: Int) {
                            Toast.makeText(this@MainActivity ,"Você deu 1 click longo", Toast.LENGTH_SHORT).show()

                            val chamaOutra = Intent(this@MainActivity,activity_pokemondetalhe::class.java)
                            chamaOutra.putExtra("uri",response.body()!!.listaDeRetorno[position].url )
                            chamaOutra.putExtra("posicao",position+1)
//                            Log.i("MainActivity2",response.body()!!.listaDeRetorno[position].url)
                            startActivity(chamaOutra)

                        }
                        override fun onItemClick(parent: AdapterView<*>, view: View, position: Int, id: Long) {

                        }
                    }
                )
            )
        }

    }

}
