package com.example.twiitelum

import android.os.Bundle
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import com.example.twiitelum.databinding.ActivityListaBinding
import com.google.android.material.snackbar.Snackbar

class ListaActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = ActivityListaBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val tweets :List<String> = listOf("Tweet 1","Tweet 2","Tweet 3","Tweet 4","Tweet 5")
        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1,tweets)

        binding.listaTweet.adapter = adapter

        binding.fabAdd.setOnClickListener {
            Snackbar.make(it,"Clicou no botão", Snackbar.LENGTH_LONG)
                .setAction("Desfazer"){}
                .show()

            finish()
        }
    }
}