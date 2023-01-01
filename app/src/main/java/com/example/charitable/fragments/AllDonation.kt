package com.example.charitable.fragments

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.example.charitable.R
import com.example.charitable.database.DatabaseManager
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

class AllDonation : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_all_donation, container, false)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val db= DatabaseManager()
        val text=requireView().findViewById<TextView>(R.id.textView12a)
        text.setText("lolo")
        val str=db.getAllDonations()
        println("--------------------------------------------------")
        print(str)
        text.setText(str)

        //DatabaseManager().connect()
        //DatabaseManager().c2()


            }

            }




