package com.icerockdev.android_app

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.icerockdev.android_app.databinding.ActivityMainBinding
import dev.icerock.library.TestViewModel
import dev.icerock.moko.mvvm.desc.StringDesc
import dev.icerock.moko.mvvm.dispatcher.eventsDispatcherOnMain
import dev.icerock.moko.mvvm.getViewModel

class MainActivity : AppCompatActivity(), TestViewModel.EventsListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding: ActivityMainBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_main)

        val viewModel = getViewModel { TestViewModel(eventsDispatcherOnMain()) }

        binding.viewModel = viewModel
        binding.lifecycleOwner = this

        viewModel.eventsDispatcher.bind(
            lifecycleOwner = this,
            listener = this
        )
    }

    override fun showAlert(text: StringDesc) {
        Toast.makeText(this, text.toString(this), Toast.LENGTH_SHORT).show()
    }
}
