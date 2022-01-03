package com.example.firebasepractice

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.example.firebasepractice.databinding.ActivityReadDataBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class ReadData : AppCompatActivity() {

    private lateinit var binding: ActivityReadDataBinding
    private lateinit var database: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        binding = ActivityReadDataBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnSearch.setOnClickListener{

            val userName: String = binding.editUserName2.text.toString()

            if (userName.isNotEmpty() || userName.isNotBlank()){

                readData(userName)

                val IM = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                IM.hideSoftInputFromWindow(binding.editUserName2.windowToken,0)
            }

            else {

                AlertDialog.Builder(this)
                    .setTitle("USERNAME")
                    .setMessage("\nEnter username.")
                    .setPositiveButton("Got it"){_,_ -> binding.editUserName2.requestFocus()}
                    .show()
            }
        }
    }

    private fun readData(username: String) {

         database = FirebaseDatabase.getInstance().getReference("Users")
         database.child(username).get().addOnSuccessListener {

             if (it.exists()) {

                 val fullName = it.child("fullName").value
                 val age = it.child("age").value
                 val userName = it.child("userName").value

                 Toast.makeText(this, "Username found!", Toast.LENGTH_SHORT).show()
                 binding.editUserName2.text.clear()

                 binding.txtNameFB.text = fullName.toString()
                 binding.txtAgeFB.text = age.toString()
                 binding.txtUsernameFB.text = userName.toString()

             }

             else {

                 AlertDialog.Builder(this)
                     .setTitle("USERNAME")
                     .setMessage("\nUsername does not exist")
                     .setPositiveButton("Try again"){_,_ ->
                         binding.editUserName2.text.clear()
                         binding.editUserName2.requestFocus()
                     }.show()
             }
         }.addOnFailureListener{

             AlertDialog.Builder(this)
                 .setTitle("USERNAME")
                 .setMessage("\nUnable to read data")
                 .setPositiveButton("Okay"){_,_ ->
                     binding.editUserName2.text.clear()
                     binding.editUserName2.requestFocus()
                 }.show()
         }
    }

}