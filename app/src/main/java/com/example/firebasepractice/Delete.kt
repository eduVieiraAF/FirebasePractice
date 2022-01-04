package com.example.firebasepractice

import android.content.Context
import android.os.Bundle
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.firebasepractice.databinding.ActivityDeleteBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class Delete : AppCompatActivity() {

    private lateinit var binding: ActivityDeleteBinding
    private lateinit var database: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        binding = ActivityDeleteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnSearch2.setOnClickListener {

            val userName = binding.editUserName4.text.toString()

            val IM = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            IM.hideSoftInputFromWindow(binding.editUserName4.windowToken,0)

            if (userName.isNotBlank() || userName.isNotEmpty()) {

                deleteData(userName)
            } else {

                AlertDialog.Builder(this)
                    .setTitle("USERNAME")
                    .setMessage("\nPlease enter a user name.")
                    .setPositiveButton("Got it") { _, _ -> binding.editUserName4.requestFocus()}
                    .show()
            }
        }
    }

    private fun deleteData(userName: String) {

        database = FirebaseDatabase.getInstance().getReference("Users")
        database.child(userName).get().addOnSuccessListener {

            if (it.exists()) {

                val fullName = it.child("fullName").value
                val age = it.child("age").value
                val user = it.child("userName").value

                AlertDialog.Builder(this)
                    .setTitle(userName)
                    .setMessage("Are you sure you want to delete?\n\nName: $fullName\nAge: $age")
                    .setNegativeButton("Cancel") { _, _ ->
                        binding.editUserName4.text.clear()
                        binding.editUserName4.requestFocus()
                    }
                    .setPositiveButton("Delete") { _, _ ->
                        binding.editUserName4.text.clear()
                        binding.editUserName4.requestFocus()
                        database.child(userName).removeValue().addOnSuccessListener {

                            binding.editUserName4.text.clear()
                            binding.editUserName4.requestFocus()
                            Toast.makeText(
                                this, "${user.toString().uppercase()} " +
                                        "has been successfully deleted.", Toast.LENGTH_SHORT
                            ).show()
                        }.addOnFailureListener {

                            AlertDialog.Builder(this)
                                .setTitle("DELETION")
                                .setMessage(
                                    "Unknown error kept ${user.toString().uppercase()}" +
                                            "\nfrom being deleted."
                                )
                                .setPositiveButton("Try again") { _, _ ->

                                    binding.editUserName4.requestFocus()
                                }
                                .show()
                        }
                    }
                    .show()
            }
            else{

                AlertDialog.Builder(this)
                    .setTitle("DELETION")
                    .setMessage("\n${userName.uppercase()} does not exist.")
                    .setPositiveButton("Try again") { _, _ ->

                        binding.editUserName4.text.clear()
                        binding.editUserName4.requestFocus()
                    }
                    .show()
            }
        }
    }
}