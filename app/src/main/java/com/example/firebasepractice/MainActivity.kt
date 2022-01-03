package com.example.firebasepractice

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.example.firebasepractice.databinding.ActivityMainBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var database: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.txtName.requestFocus()
        binding.btnLogin.setOnClickListener {

            val fulName = binding.txtName.text.toString()
            val age = binding.txtAge.text.toString()
            val userName = binding.txtUserName.text.toString()

            if (fulName.isEmpty() || age.isEmpty() || userName.isEmpty()) {

                //Toast.makeText(this, "You cannot leave any field empty.", Toast
                //    .LENGTH_SHORT).show()

                AlertDialog.Builder(this)
                    .setTitle("REGISTRATION")
                    .setMessage("You cannot leave any field empty.")
                    .setPositiveButton("Got it") {_,_ ->
                        binding.txtName.requestFocus()}.show()

            }

            else {

                if (userName.contains(".") || userName.contains("#") ||
                    userName.contains("$") || userName.contains("[") ||
                    userName.contains("]")) {

                    binding.txtUserName.text.clear()
                    binding.txtUserName.requestFocus()

                    //Toast.makeText(this, "Username must not contain '.', '#', '$', '['," +
                    //        " or ']'", Toast.LENGTH_LONG).show()

                    AlertDialog.Builder(this)
                        .setTitle("USERNAME")
                        .setMessage("Username cannot contain ., #, $, [, or ]")
                        .setPositiveButton("Got it"){_,_ ->}
                        .show()
                }

                else {

                    database = FirebaseDatabase.getInstance().getReference("Users")
                    val user = User(fulName, age, userName)
                    database.child(userName).setValue(user).addOnSuccessListener {

                        binding.txtName.text.clear()
                        binding.txtAge.text.clear()
                        binding.txtUserName.text.clear()
                        binding.txtName.requestFocus()

                       //Toast.makeText(this, "Successfully registered", Toast.LENGTH_SHORT)
                        //   .show()
                        AlertDialog.Builder(this)
                            .setTitle("REGISTRATION")
                            .setMessage("You have successfully completed\nyour registration.")
                            .setPositiveButton("Okay"){_,_ ->}
                            .show()

                    }.addOnFailureListener {

                        //Toast.makeText(this, "Registration failed", Toast.LENGTH_SHORT)
                        //    .show()

                        AlertDialog.Builder(this)
                            .setTitle("REGISTRATION")
                            .setMessage("Registration failed")
                            .setNeutralButton("Try again"){_,_ ->

                                binding.txtName.text.clear()
                                binding.txtAge.text.clear()
                                binding.txtUserName.text.clear()
                                binding.txtName.requestFocus()
                            }
                            .setPositiveButton("Close app"){_,_ ->
                                finish()}
                            .show()
                    }
                }
            }
        }
    }
}