package com.example.eleventhpractice.fragments

import android.os.AsyncTask
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.eleventhpractice.databinding.FragmentProgressBinding

class ProgressFragment : Fragment() {

    private lateinit var binding: FragmentProgressBinding
    var integers: List<Int> = listOf()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentProgressBinding.inflate(layoutInflater, container, false)
        this.integers = (1..100).toList()
        binding.button.setOnClickListener {
            ProgressTask(binding, integers).execute()
        }
        return binding.root
    }

    class ProgressTask(
        private val binding: FragmentProgressBinding,
        private val integers: List<Int>
    ) :
        AsyncTask<Void, Int, Void>() {

        @Deprecated("Deprecated in Java")
        override fun doInBackground(vararg params: Void?): Void? {
            for (i in integers) {
                publishProgress(i)
                Thread.sleep(400)
            }
            return null
        }

        @Deprecated("Deprecated in Java")
        override fun onProgressUpdate(vararg values: Int?) {
            super.onProgressUpdate(*values)
            binding.indicator.progress = values[0]?.plus(1) ?: 0
            binding.textView2.text = "Progress: ${values[0]?.plus(1)}"
        }
    }
}