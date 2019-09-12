package dev.icerock.library

import dev.icerock.moko.mvvm.desc.StringDesc
import dev.icerock.moko.mvvm.desc.desc
import dev.icerock.moko.mvvm.dispatcher.EventsDispatcher
import dev.icerock.moko.mvvm.livedata.LiveData
import dev.icerock.moko.mvvm.livedata.MutableLiveData
import dev.icerock.moko.mvvm.livedata.map
import dev.icerock.moko.mvvm.livedata.mergeWith
import dev.icerock.moko.mvvm.viewmodel.ViewModel

/*
 * Copyright 2019 IceRock MAG Inc. Use of this source code is governed by the Apache 2.0 license.
 */

class TestViewModel(
    val eventsDispatcher: EventsDispatcher<EventsListener>
) : ViewModel() {

    private val _counter: MutableLiveData<Int> = MutableLiveData(0)
    val counter: LiveData<StringDesc> = _counter.map {
        if (it % 2 == 0) {
            MR.strings.even
        } else {
            MR.strings.odd
        }.desc()
    }

    val firstName: MutableLiveData<String> = MutableLiveData("")
    val lastName: MutableLiveData<String> = MutableLiveData("")

    val name: LiveData<String> = firstName.mergeWith(lastName) { firstName, lastName ->
        listOf(firstName, lastName).filter { it.isNotEmpty() }.joinToString(" ")
    }

    fun onUpButtonPressed() {
        println("before set value: ${_counter.value}")
        _counter.value = _counter.value + 1
        println("after set value: ${_counter.value}")

        if ((_counter.value % 10) == 0) {
            eventsDispatcher.dispatchEvent { showAlert(MR.strings.every_ten.desc()) }
        }

        println("before post value: ${_counter.value}")
        _counter.postValue(_counter.value + 1)
        println("after post value: ${_counter.value}")
    }

    interface EventsListener {
        fun showAlert(text: StringDesc)
    }
}