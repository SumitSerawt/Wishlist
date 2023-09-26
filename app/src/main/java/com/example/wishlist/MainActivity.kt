

package com.example.wishlist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val websiteTv = findViewById<EditText>(R.id.editTextURL)
        val priceTv = findViewById<EditText>(R.id.editTextPrice)
        val itemNameTv = findViewById<EditText>(R.id.editTextName)
        val submitBtn = findViewById<Button>(R.id.button)

        val wishlistRv = findViewById<RecyclerView>(R.id.recyclerView)
        val adapter = WishlistAdapter()
        wishlistRv.adapter = adapter
        wishlistRv.layoutManager = LinearLayoutManager(this)

        submitBtn.setOnClickListener {
            val name = itemNameTv.text.toString()
            val price = priceTv.text.toString().toDoubleOrNull()
            val url = websiteTv.text.toString()

            if(name.isNotEmpty() && price != null && url.isNotEmpty()){
                var newItem = WishlistItem(name, price, url)
                adapter.addItem(newItem)

                itemNameTv.text.clear()
                priceTv.text.clear()
                websiteTv.text.clear()
            } else {
                Toast.makeText(this, "Please enter valid item details", Toast.LENGTH_SHORT).show()
            }
        }

    } //end onCreate()
}
