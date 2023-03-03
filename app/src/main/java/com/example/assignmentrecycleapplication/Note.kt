package com.example.assignmentrecycleapplication

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

data class Note(var name:String, val charNumber:Int, var description:String)

class NoteAdapter(var context: Context, var data :ArrayList<Note>):RecyclerView.Adapter<NoteAdapter.MyViewHolder>(){

    val db = Firebase.firestore

    class MyViewHolder(itemView:View): RecyclerView.ViewHolder(itemView){
        var name = itemView.findViewById<TextView>(R.id.noteName)
        var number = itemView.findViewById<TextView>(R.id.noteNumber)
        var description = itemView.findViewById<TextView>(R.id.noteDiscription)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteAdapter.MyViewHolder {
        val root = LayoutInflater.from(context)
            .inflate(R.layout.note_item,parent,false)
        return MyViewHolder(root)
    }

    override fun onBindViewHolder(holder: NoteAdapter.MyViewHolder, position: Int) {
        holder.name.text = data[position].name.toString()
        holder.number.text = data[position].charNumber.toString()
        holder.description.text = data[position].description.toString()

    }

    override fun getItemCount(): Int {
        return data.size
    }

}