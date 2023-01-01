package Control

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.charitable.R
import Model.database.DatabaseManager

class Login : Fragment() {

    lateinit var signIn: Button
    lateinit var signUp: Button
     companion object {
        var uid = 0
    }


public fun getLoginID():Int
{
    return 1
}
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        signIn = view.findViewById(R.id.startButton)
        signUp = view.findViewById(R.id.registerButton)
        val db= DatabaseManager.db
        fun showMessage(message:String)
        {
            val myToast = Toast.makeText(context,message,Toast.LENGTH_SHORT)
            myToast.show()
        }


        val userName = requireView().findViewById<EditText>(R.id.nameField)
        val password = requireView().findViewById<EditText>(R.id.editTextTextPassword2)
        
        signIn.setOnClickListener {

            //db.connect()
            val name = userName.text.toString()
            val pass=password.text.toString()
                if (db.checkName(name,pass)==1) {
                    showMessage("Welcome ${name}")
                    uid =db.getID(name)
                        parentFragmentManager.beginTransaction().apply {
                            replace(R.id.flFragment, HomeScreen(1))
                            addToBackStack(null)
                            commit()
                        }
                    }
                else if (db.checkName(name,pass)==2) {
                    showMessage("Welcome Model.Admin")
                        parentFragmentManager.beginTransaction().apply {
                            replace(R.id.flFragment, HomeScreen(0))
                            addToBackStack(null)
                            commit()
                        }
            }
            else
            {
                showMessage("Username or Password is Incorrect !")
            }

        }
        signUp.setOnClickListener {
            parentFragmentManager.beginTransaction().apply {
                replace(R.id.flFragment, SignUp())
                addToBackStack(null)
                commit()
            }
        }

    }


}



