package com.example.eleventhpractice.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.eleventhpractice.databinding.FragmentAsyncTaskBinding


class AsyncTaskFragment : Fragment() {

    private lateinit var binding: FragmentAsyncTaskBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentAsyncTaskBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var clicks = 0
        binding.clickButton.setOnClickListener {
            binding.clicksTextView.text = "Clicks: ${++clicks}"
        }
    }


}