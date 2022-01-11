package com.example.firebasepractice

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import java.util.ArrayList

class FireAdapter(private val userList : ArrayList<User>):
    RecyclerView.Adapter<FireAdapter.FireViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FireViewHolder {

        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item,
        parent, false)

        return FireViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: FireViewHolder, position: Int) {

        val currentItem = userList[position]

        holder.fullName.text = currentItem.fullName
        holder.age.text = currentItem.age
        holder.userName.text = currentItem.userName
    }

    override fun getItemCount(): Int {

        return userList.size
    }

    class FireViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){

        val fullName: TextView = itemView.findViewById(R.id.TV_nameFB)
        val age: TextView = itemView.findViewById(R.id.TV_ageFB)
        val userName: TextView = itemView.findViewById(R.id.TV_userNameFB)
    }
}