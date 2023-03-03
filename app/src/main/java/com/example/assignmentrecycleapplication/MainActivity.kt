package com.example.assignmentrecycleapplication

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.Button
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class MainActivity : AppCompatActivity() {


    lateinit var recyclerView:RecyclerView
    lateinit var addbtn:FloatingActionButton
    lateinit var db:FirebaseFirestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById<RecyclerView>(R.id.rcyclerView)
        addbtn = findViewById<FloatingActionButton>(R.id.addbtn)
        addbtn.setOnClickListener {
            val i = Intent(this, AddNoteActivity::class.java)
            startActivity(i)
        }

        recyclerView.layoutManager = LinearLayoutManager(this)

        db = Firebase.firestore


    }

    override fun onResume() {
        super.onResume()
        val data = ArrayList<Note>()

        data.clear()
        val adapter = NoteAdapter(this, data)
        db.collection("notes").get().addOnSuccessListener { querySnapshot ->
            for (document in querySnapshot) {

                var n = document.data.get("name").toString()
                var cnum = document.data.get("charNumber").toString().toInt()
                var d = document.data.get("description").toString()
                var note = Note(n, cnum, d)
                data.add(note)
                adapter.notifyDataSetChanged()
            }




            recyclerView.adapter = adapter


        }

    }
}