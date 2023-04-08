package com.example.assignment_3

import android.app.Activity
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.assignment_3.databinding.ActivityRegisterBinding
import java.util.Objects

class RegisterActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRegisterBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnCreate.setOnClickListener {
            val fname = binding.fname.text.toString()
            val lname = binding.lname.text.toString()
            val email = binding.email.text.toString()
            val pawd = binding.password.text.toString()

            val user = User(fname, lname, email, pawd)
            var intent = intent
            intent.putExtra("user", user)
            setResult(Activity.RESULT_OK, intent)
            finish()
        }
    }

    override fun onBackPressed() {
        setResult(Activity.RESULT_CANCELED)
        finish()
    }
}