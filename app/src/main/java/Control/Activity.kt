package Control

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.example.charitable.R
import Model.database.DatabaseManager
import com.google.android.material.textfield.TextInputEditText

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
        val name=requireView().findViewById<TextInputEditText>(R.id.applicantName)
        val email=requireView().findViewById<TextInputEditText>(R.id.applicantEmail)
        val phone=requireView().findViewById<TextInputEditText>(R.id.applicantPhone)
        val nationalID=requireView().findViewById<TextInputEditText>(R.id.applicantNationalID)
        val branch=requireView().findViewById<TextInputEditText>(R.id.branch)
        val age=requireView().findViewById<TextInputEditText>(R.id.applicantAge)
        val volunterButton=requireView().findViewById<Button>(R.id.button)
        val db= DatabaseManager.db
        var listOfActivities=db.getAllActivities()
        val adapter = ArrayAdapter(requireContext() ,R.layout.drop_down_list_item,listOfActivities)
        dropDownMen.setAdapter(adapter)

        volunterButton.setOnClickListener{

            if (db.checkForAge(age.text.toString().toInt(),dropDownMen.text.toString()))
            {
                db.applyForActivity(dropDownMen.text.toString(),name.text.toString(),email.text.toString(),phone.text.toString(),nationalID.text.toString(),branch.text.toString(),age.text.toString().toInt())
                val myToast = Toast.makeText(context,"Application Successfuly sent !", Toast.LENGTH_SHORT)
                myToast.show()
            }
            else
            {
                val myToast = Toast.makeText(context,"Sorry You are age is not applicable to this Activity !", Toast.LENGTH_SHORT)
                myToast.show()
            }
              }
        //DatabaseManager().connect()
        //DatabaseManager().c2()




    }


}
