package ru.urban.android_album

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class PhotoAlbumActivity : AppCompatActivity() {
    private lateinit var toolbarMain: Toolbar

    private lateinit var previousBTN: Button
    private lateinit var nextBTN: Button

    private lateinit var imageIV: ImageView
    private var position = 0;

    private val photoAlbum = PhotoAlbumDB()

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_photo_album)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        toolbarMain = findViewById(R.id.toolbarMain)
        setSupportActionBar(toolbarMain)

        previousBTN = findViewById(R.id.previousBTN)
        nextBTN = findViewById(R.id.nextBTN)

        imageIV = findViewById(R.id.imageIV)
        imageIV.setImageResource(photoAlbum.photos[position])

        previousBTN.setOnClickListener{
            position--

            if (position < 0 ){
                position = 0
                return@setOnClickListener
            }

            if (position == photoAlbum.photos.size-2){
                nextBTN.text = "Следующая"
            }

            imageIV.setImageResource(photoAlbum.photos[position])
        }

        nextBTN.setOnClickListener{
            position++

            if (position == photoAlbum.photos.size-1){
                nextBTN.text = "Конец"
            }

            if (position == photoAlbum.photos.size){
                position = photoAlbum.photos.size-1
                val intent = Intent(this, FinalWindowActivity::class.java)
                startActivity(intent)
                finish()
            }

            imageIV.setImageResource(photoAlbum.photos[position])



        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        toolbarMain.menu.findItem(R.id.exitMenuMain).setIcon(R.drawable.exit_to_app)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId){
            R.id.exitMenuMain -> {
                Toast.makeText(
                    applicationContext,
                    "Работа завершена",
                    Toast.LENGTH_LONG
                ).show()
                finish()
            }
        }
        return super.onOptionsItemSelected(item)
    }
}