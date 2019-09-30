package com.example.android.marsrealestate

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.android.marsrealestate.ui.newusertwo.NewUserTwoFragment
import kotlinx.android.synthetic.main.new_user_two_fragment.*

class NewUserTwo : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.new_user_two_activity)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                    .replace(R.id.container, NewUserTwoFragment.newInstance())
                    .commitNow()
        }

    }

}
