package com.example.facebooksdk.fragments

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.facebooksdk.viewModels.HelloWordViewModel
import com.example.facebooksdk.R


class FragmentHelloWord : Fragment() {

    companion object {
        fun newInstance() = FragmentHelloWord()
    }

    private lateinit var viewModel: HelloWordViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.hello_word_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(HelloWordViewModel::class.java)
        // TODO: Use the ViewModel
    }

}
