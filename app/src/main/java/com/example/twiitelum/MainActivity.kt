package com.example.twiitelum
import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.core.content.FileProvider
import androidx.lifecycle.ViewModelProvider
import caelum.com.twittelumapp.modelo.Tweet
import com.example.twiitelum.databinding.ActivityMainBinding
import com.example.twiitelum.util.decodificaParaBase64
import com.example.twiitelum.viewmodel.TweetViewModel
import com.example.twiitelum.viewmodel.ViewModelFactory
import java.io.File


class MainActivity : AppCompatActivity() {

    private var localFoto: String? = null
    lateinit var viewModel: TweetViewModel
    private lateinit var binding: ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel = ViewModelProvider(this, ViewModelFactory)[TweetViewModel::class.java]
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.tweet_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean = when (item.itemId) {
        R.id.menu_foto -> {
            tiraFoto()
            true
        }
        R.id.menu_salvar -> {
            publicaTweet()
            true
        }
        android.R.id.home -> {
            onBackPressed()
            true
        }
        else -> false
    }

    private fun tiraFoto() {
        val vaiPraCamera = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        val caminhoFoto = defineLocalDaFoto()
        vaiPraCamera.putExtra(MediaStore.EXTRA_OUTPUT, caminhoFoto)
        startActivityForResult(vaiPraCamera,123)
    }

    private fun defineLocalDaFoto(): Uri? {
        localFoto = "${getExternalFilesDir(Environment.DIRECTORY_PICTURES)}/${System.currentTimeMillis()}.jpg"
        val arquivo = File(localFoto)
        return FileProvider.getUriForFile(this, "br.com.twiitelumapp.fileprovider", arquivo)
    }

    fun publicaTweet() {
        val tweet = criaTweet()
        viewModel.salva(tweet)

        Toast.makeText(this, "$tweet foi salvo com sucesso :D", Toast.LENGTH_LONG).show()

        finish()

    }

    private fun criaTweet(): Tweet {
        val campoDeMensagemDoTweet = findViewById<EditText>(R.id.publicar_tweet)
        val mensagemDoTweet: String = campoDeMensagemDoTweet.text.toString()
        val foto: String? = binding.tweetFoto.tag as String?
        return Tweet(mensagemDoTweet, foto)
    }

    override fun onActivityResult(requestCode: Int,
                                  resultCode: Int,
                                  data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 123) {
            if (resultCode == Activity.RESULT_OK)
                carregaFoto()
            }
        }

    private fun carregaFoto() {
        val bitmap = BitmapFactory.decodeFile(localFoto)
        val bm = Bitmap.createScaledBitmap(bitmap,300,300, true)
        val fotoNaBase64 = bm.decodificaParaBase64()
        binding.tweetFoto.tag = fotoNaBase64
        binding.tweetCard.visibility = View.VISIBLE
        binding.tweetFoto.setImageBitmap(bm)
        binding.tweetFoto.scaleType = ImageView.ScaleType.FIT_XY
    }
}