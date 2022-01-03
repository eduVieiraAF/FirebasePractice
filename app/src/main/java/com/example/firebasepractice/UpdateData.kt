package com.example.firebasepractice

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AlertDialog
import com.example.firebasepractice.databinding.ActivityUpdateDataBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class UpdateData : AppCompatActivity() {

    private lateinit var binding: ActivityUpdateDataBinding
    private lateinit var database: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        binding = ActivityUpdateDataBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnUpdate.setOnClickListener {

            val userName = binding.editUserName3.text.toString()
            val fullName = binding.editFullName3.text.toString()
            val age = binding.editAge3.text.toString()

            updateData(userName, fullName, age)

            
        }
    }

    private fun updateData(userName: String, fullName: String, age: String) {

        database = FirebaseDatabase.getInstance().getReference("Users")
        val user = mapOf<String, String>(
            "fullName" to fullName,
            "age" to age
        )

        database.child(userName).updateChildren(user).addOnSuccessListener {

            binding.editUserName3.text.clear()
            binding.editFullName3.text.clear()
            binding.editAge3.text.clear()

            val IM = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            IM.hideSoftInputFromWindow(binding.editUserName3.windowToken,0)

            AlertDialog.Builder(this)
                .setTitle("UPDATE")
                .setMessage("Data has been successfully updated.")
                .setPositiveButton("Hooray"){_,_ -> binding.editUserName3.requestFocus()}

        }.addOnFailureListener {

            val IM = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            IM.hideSoftInputFromWindow(binding.editUserName3.windowToken,0)

            AlertDialog.Builder(this)
                .setTitle("UPDATE")
                .setMessage("Data has failed.")
                .setPositiveButton("try again"){_,_ -> binding.editUserName3.requestFocus()}
        }
    }
    // TODO : Make it better
}
