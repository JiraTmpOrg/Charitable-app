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
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

class Donation : Fragment() {
    lateinit var donate: Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_donation, container, false)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        donate = view.findViewById(R.id.button)
        val db= DatabaseManager()
        //DatabaseManager().connect()
        //DatabaseManager().c2()
        val paymentDropDownMen=requireView().findViewById<AutoCompleteTextView>(R.id.paymemts)
        val lisofCountry= mutableListOf("Cash","Card")
        val adapter = ArrayAdapter(requireContext() ,R.layout.drop_down_list_item,lisofCountry)
        paymentDropDownMen.setAdapter(adapter)

        val donerName = requireView().findViewById<TextInputEditText>(R.id.donerName_donations)
        val email=requireView().findViewById<TextInputEditText>(R.id.email_donations)
        val phone=requireView().findViewById<TextInputEditText>(R.id.phone_donations)
        val amount = requireView().findViewById<TextInputEditText>(R.id.amount_donations)
        //val password = requireView().findViewById<EditText>(R.id.editTextTextPassword2)

               donate.setOnClickListener {
            //db.connect()
                   db.insertDonation(donerName.text.toString(),email.text.toString(),phone.text.toString(),amount.text.toString(),paymentDropDownMen.text.toString())
            val myToast = Toast.makeText(context,"Added: ${Login.uid}: ${donerName.text}:${email.text}ph:${phone.text}am:${amount.text}typ:${paymentDropDownMen.text}", Toast.LENGTH_SHORT)
                myToast.show()
                }

            }

            }




