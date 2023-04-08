package com.example.assignment_3

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.example.assignment_3.databinding.ActivityShoppingBinding

class ShoppingActivity : AppCompatActivity() {
    private lateinit var binding: ActivityShoppingBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityShoppingBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var email = intent.getStringExtra("email").toString()
        binding.txtEmail.text = email
    }

    fun Click(view: View) {
        Toast.makeText(this, "You have chosen the Shopping category of shopping ", Toast.LENGTH_LONG).show();
    }
}