package com.example.memesharing2

import MySingleton
import android.content.Intent
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import kotlinx.android.synthetic.main.activity_new_page.*

class Wholesome : AppCompatActivity() {
    var currenturl: String? =null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_page)
        loadMeme()
    }
    private fun loadMeme(){
        progressBar.visibility = View.VISIBLE
        val url = "https://meme-api.herokuapp.com/gimme/wholesomememes"
        val jsonObjectRequest = JsonObjectRequest(Request.Method.GET, url, null,
            Response.Listener { response ->
                currenturl = response.getString("url")
                Glide.with(this).load(currenturl).listener(object: RequestListener<Drawable>{
                    override fun onLoadFailed(
                        e: GlideException?,
                        model: Any?,
                        target: Target<Drawable>?,
                        isFirstResource: Boolean
                    ): Boolean {
                        progressBar.visibility = View.GONE
                        return false
                    }
                    override fun onResourceReady(
                        resource: Drawable?,
                        model: Any?,
                        target: Target<Drawable>?,
                        dataSource: DataSource?,
                        isFirstResource: Boolean
                    ): Boolean {
                        progressBar.visibility = View.GONE
                        return false
                    }
                }).into(memeImage)
            },
            Response.ErrorListener { error ->
                Toast.makeText(this,"Something went Wrong", Toast.LENGTH_SHORT).show()
            }
        )
        MySingleton.getInstance(this).addToRequestQueue(jsonObjectRequest)

    }

    fun nextMeme(view: View) {
        loadMeme()
    }
    fun sharememe(view: View) {
        val intent = Intent(Intent.ACTION_SEND)
        intent.type = "text/plane"
        intent.putExtra(Intent.EXTRA_TEXT,"Hey, Checkout this meme $currenturl. This app is developed by YAZDAN")
        val chooser = Intent.createChooser(intent, "Share this Meme using ....")
        startActivity(chooser)
    }
}