package com.example.eleventhpractice.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.eleventhpractice.databinding.FragmentThreadBinding
import java.util.Calendar

class ThreadFragment : Fragment() {

    private lateinit var binding: FragmentThreadBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentThreadBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.button.setOnClickListener {
            Thread {
                repeat(30) {
                    val calendar = Calendar.getInstance()
                    val hour = calendar.get(Calendar.HOUR_OF_DAY)
                    val minute = calendar.get(Calendar.MINUTE)
                    val second = calendar.get(Calendar.SECOND)
                    val timeText = "$hour:$minute:$second"
                    binding.textView.post {
                        binding.textView.text = timeText
                    }
                    Thread.sleep(1000)
                }
            }.start()
        }
    }
}