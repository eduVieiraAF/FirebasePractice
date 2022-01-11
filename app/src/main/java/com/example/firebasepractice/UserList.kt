package com.example.firebasepractice

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.*

class UserList : AppCompatActivity() {

    private lateinit var dbRef: DatabaseReference
    private lateinit var userRV: RecyclerView
    private lateinit var userAL: ArrayList<User>

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_list)

        userRV = findViewById(R.id.RV_userList)
        userRV.layoutManager = LinearLayoutManager(this)
        userRV.setHasFixedSize(true)

        userAL = arrayListOf<User>()

        getUserData()
    }

    private fun getUserData() {

        dbRef = FirebaseDatabase.getInstance().getReference("User")

        dbRef.addValueEventListener(object : ValueEventListener{

            override fun onDataChange(snapshot: DataSnapshot) {

                if (snapshot.exists()){

                    for (userSnapShot in snapshot.children){

                        val user = userSnapShot.getValue(User::class.java)
                        userAL.add(user!!)
                    }

                    userRV.adapter = FireAdapter(userAL)
                }
            }

            override fun onCancelled(error: DatabaseError) {

                AlertDialog.Builder(this@UserList)
                    .setTitle("ERROR")
                    .setMessage("Unable to retrieve data.")
                    .setPositiveButton("Okay"){_,_ ->}
                    .show()
            }
        })
    }
}

//TODO: It's still not working "No adapter attached error"