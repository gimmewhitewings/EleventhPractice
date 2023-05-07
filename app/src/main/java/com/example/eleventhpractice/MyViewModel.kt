package com.example.eleventhpractice

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MyViewModel : ViewModel() {
    private var _isStarted = MutableLiveData<Boolean>(false)
    val isStarted: LiveData<Boolean>
        get() = _isStarted

    private var _progress = MutableLiveData<Int>(0)
    val progress: LiveData<Int>
        get() = _progress

    fun startThread() {
        val thread = Thread {
            for (i in 0..100) {
                Thread.sleep(400)
                _progress.postValue(i)
            }
        }
        thread.start()
        _isStarted.value = true
    }
}