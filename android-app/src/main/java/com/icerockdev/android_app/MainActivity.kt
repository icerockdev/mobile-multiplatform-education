package com.icerockdev.android_app

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.icerockdev.android_app.databinding.ActivityMainBinding
import dev.icerock.library.TestViewModel
import dev.icerock.moko.mvvm.getViewModel

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding: ActivityMainBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_main)

        val viewModel = getViewModel { TestViewModel() }

        binding.viewModel = viewModel
        binding.lifecycleOwner = this
    }
}
