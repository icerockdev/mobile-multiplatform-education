package dev.icerock.library

import dev.icerock.moko.mvvm.desc.StringDesc
import dev.icerock.moko.mvvm.desc.desc
import dev.icerock.moko.mvvm.dispatcher.EventsDispatcher
import dev.icerock.moko.mvvm.livedata.*
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
    }.mapBuffered { current, new ->
        "old name: $current\nnew name: $new"
    }

    private val _costRub: MediatorLiveData<String> = MediatorLiveData("")
    val costRub: MutableLiveData<String> = _costRub
    private val _costDollar: MediatorLiveData<String> = MediatorLiveData("")
    val costDollar: MutableLiveData<String> = _costDollar

    init {
        val rubInDollar = 60.0f

        _costRub.addSource(_costDollar) { newDollarValue ->
            val dollars = newDollarValue.toFloatOrNull() ?: 0.0f
            val rubs = dollars * rubInDollar
            _costRub.value = rubs.toString()
        }
        _costDollar.addSource(_costRub) { newRubValue ->
            val rubs = newRubValue.toFloatOrNull() ?: 0.0f
            val dollars = rubs / rubInDollar
            _costDollar.value = dollars.toString()
        }
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