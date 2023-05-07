package com.example.eleventhpractice.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.example.eleventhpractice.MyViewModel
import com.example.eleventhpractice.databinding.FragmentViewModelBinding


class ViewModelFragment : Fragment() {

    private lateinit var binding: FragmentViewModelBinding
    private val viewModel: MyViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentViewModelBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val progressObserver = Observer<Int> { progress ->
            binding.linearProgressIndicator.progress = progress
            binding.statusTextView.text = "Progress: $progress"
        }


        val isStartedObserver = Observer<Boolean> { isStarted ->
            binding.startThreadButton.isEnabled = !isStarted
        }

        viewModel.progress.observe(viewLifecycleOwner, progressObserver)
        viewModel.isStarted.observe(viewLifecycleOwner, isStartedObserver)

        binding.startThreadButton.setOnClickListener {
            viewModel.startThread()
        }
    }
}