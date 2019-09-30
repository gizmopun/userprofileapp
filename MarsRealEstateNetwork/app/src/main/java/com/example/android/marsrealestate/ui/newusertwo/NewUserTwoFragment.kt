package com.example.android.marsrealestate.ui.newusertwo

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.android.marsrealestate.R

class NewUserTwoFragment : Fragment() {

    companion object {
        fun newInstance() = NewUserTwoFragment()
    }

    private lateinit var viewModel: NewUserTwoViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.new_user_two_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(NewUserTwoViewModel::class.java)
        // TODO: Use the ViewModel
    }

}
