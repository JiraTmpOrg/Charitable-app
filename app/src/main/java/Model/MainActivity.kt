package Model

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import Control.Login
import com.example.charitable.R

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportFragmentManager.beginTransaction().apply {
            replace(R.id.flFragment, Login())
            commit()
    }
    }
}