package com.example.charitable.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import com.example.charitable.R
import com.example.charitable.database.DatabaseManager
import com.google.android.material.textfield.TextInputEditText

class Addition : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_addition, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val addActivity = requireView().findViewById<Button>(R.id.Add)
        val name = requireView().findViewById<TextInputEditText>(R.id.activityName)
        val goal = requireView().findViewById<TextInputEditText>(R.id.goal)
        val duration = requireView().findViewById<TextInputEditText>(R.id.duration)
        val description=requireView().findViewById<TextInputEditText>(R.id.description)
        val from = requireView().findViewById<TextInputEditText>(R.id.from)
        val to = requireView().findViewById<TextInputEditText>(R.id.to)
        val db = DatabaseManager()
        addActivity.setOnClickListener{
            db.addActivity(name.text.toString(),goal.text.toString(),duration.text.toString(),description.text.toString(),from.text.toString().toInt(),to.text.toString().toInt())
            val myToast = Toast.makeText(context,"Added: ${name.text}:${goal.text}ph:${duration.text}am:${description.text}typ:${from.text},${to.text}", Toast.LENGTH_SHORT)
            myToast.show()
        }

    }
}