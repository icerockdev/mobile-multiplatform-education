package dev.icerock.library

import dev.icerock.moko.mvvm.livedata.LiveData
import dev.icerock.moko.mvvm.livedata.MutableLiveData
import dev.icerock.moko.mvvm.livedata.map
import dev.icerock.moko.mvvm.viewmodel.ViewModel

/*
 * Copyright 2019 IceRock MAG Inc. Use of this source code is governed by the Apache 2.0 license.
 */

class TestViewModel: ViewModel() {
    private val _counter: MutableLiveData<Int> = MutableLiveData(0)
    val counter: LiveData<String> = _counter.map { it.toString() }

    fun onUpButtonPressed() {
        _counter.value = _counter.value + 1
    }
}