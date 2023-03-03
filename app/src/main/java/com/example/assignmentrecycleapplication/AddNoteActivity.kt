package com.example.assignmentrecycleapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class AddNoteActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_note)
        
        val name = findViewById<EditText>(R.id.nameedt)
        val description = findViewById<EditText>(R.id.descriptioned)
        val number = findViewById<EditText>(R.id.numbered)
        val addbtn = findViewById<Button>(R.id.submitbtn)
        
        val db = Firebase.firestore
        
        addbtn.setOnClickListener {
            val user = hashMapOf(
                "name" to name.text.toString(),
                "charNumber" to number.text.toString().toInt(),
                "description" to description.text.toString(),
                
            )

            db.collection("notes")
                .add(user)
                .addOnSuccessListener { documentReference ->
                    Toast.makeText(this, "note added successfully", Toast.LENGTH_SHORT).show()
                    Log.d("TAG", "DocumentSnapshot added with ID: ${documentReference.id}")
                    finish()
                }.addOnFailureListener { e ->
                    Toast.makeText(this, "failed to add note", Toast.LENGTH_SHORT).show()
                    Log.w("TAG", "Error adding document", e)
                }
        }
        
    }
}