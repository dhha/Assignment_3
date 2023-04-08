package com.example.assignment_3

import android.app.Activity
import android.app.Instrumentation.ActivityResult
import android.content.Intent
import android.net.Uri
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.LinearLayout
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContract
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.assignment_3.databinding.ActivityMainBinding
import java.time.LocalDate
import kotlin.math.log

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var users: ArrayList<User>
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        users = ArrayList()
        users.add(User("User 1", "N", "user1@gmail.com", "123456"))
        users.add(User("User 2", "N", "user2@gmail.com", "a123456"))
        users.add(User("User 3", "N", "user3@gmail.com", "b123456"))
        users.add(User("User 4", "N", "user4@gmail.com", "c123456"))
        users.add(User("User 5", "N", "user5@gmail.com", "d123456"))

        binding.btnSigin.setOnClickListener{
            var email = binding.email.text.toString()
            var pwd = binding.password.text.toString();
            var result: User? = null

            users.forEach {
                if (it.email == email) {
                    if(it.password == pwd) {
                        //Login success
                        result = it
                    } else {
                        Toast.makeText(this, "Password is incorrect!", Toast.LENGTH_LONG).show();
                    }
                }
            }

            if(result != null) {
                //Call Shoping activity
                Toast.makeText(this, "Login Success!", Toast.LENGTH_LONG).show();
                var intent = Intent(this, ShoppingActivity::class.java)
                intent.putExtra("email", result!!.email)
                startActivity(intent)

            } else {
                Toast.makeText(this, "Login Failed!", Toast.LENGTH_LONG).show();
            }
        }

        //Create new account
        var resultActivity = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){ result ->
            if(result.resultCode == Activity.RESULT_OK) {
                var user = result.data?.getSerializableExtra("user") as User
                users.add(user)
                Log.i("User", user.toString())
            }
        }
        binding.createAccount.setOnClickListener {
            var intent = Intent(this, RegisterActivity::class.java)
            resultActivity.launch(intent)
        }

        //Forgot Password
        binding.fgPassword.setOnClickListener {
            val email = binding.email.text.toString()
            val subject = "Forgot password"
            val user: User? = users.find {
                it.email == email
            }
            Log.i("user", user.toString())
            val content = "mailto:$email&subject=$subject&body=your password is: ${user?.password}"
            Intent(Intent.ACTION_SEND, Uri.parse(content))

            Toast.makeText(this, "Email is sent to ${user?.email}", Toast.LENGTH_LONG).show()
        }
    }
}