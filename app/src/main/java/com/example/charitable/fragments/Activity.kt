package com.example.charitable.fragments

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.example.charitable.R
import com.example.charitable.database.DatabaseManager

class Activity : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_activity, container, false)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val dropDownMen=requireView().findViewById<AutoCompleteTextView>(R.id.availableActivities)
        val volunterButton=requireView().findViewById<Button>(R.id.button)
        var lisofCountry= mutableListOf("1","2")
        val adapter = ArrayAdapter(requireContext() ,R.layout.drop_down_list_item,lisofCountry)
        dropDownMen.setAdapter(adapter)
        val db= DatabaseManager()
        volunterButton.setOnClickListener{
            lisofCountry=db.getAllActivities()
            val adapter = ArrayAdapter(requireContext() ,R.layout.drop_down_list_item,lisofCountry)
            dropDownMen.setAdapter(adapter)
            println(lisofCountry)
        }
        //DatabaseManager().connect()
        //DatabaseManager().c2()




    }


}
