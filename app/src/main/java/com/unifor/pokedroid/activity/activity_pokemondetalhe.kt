package com.unifor.pokedroid.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.TextView
import com.unifor.pokedroid.R

class activity_pokemondetalhe : AppCompatActivity() {

    private lateinit var textoTela:TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pokemondetalhe)

        textoTela = findViewById(R.id.segundaTela_textView)

        var dados = intent

        textoTela.text = dados.getStringExtra("uri")
    }
}
