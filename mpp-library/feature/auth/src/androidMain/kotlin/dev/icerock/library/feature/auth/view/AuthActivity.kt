/*
 * Copyright 2019 IceRock MAG Inc. Use of this source code is governed by the Apache 2.0 license.
 */

package dev.icerock.library.feature.auth.view

import android.util.Log
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import dev.icerock.library.feature.auth.BR
import dev.icerock.library.feature.auth.R
import dev.icerock.library.feature.auth.databinding.ActivityAuthBinding
import dev.icerock.library.feature.auth.di.authModule
import dev.icerock.library.feature.auth.presentation.AuthViewModel
import dev.icerock.moko.mvvm.MvvmEventsActivity
import dev.icerock.moko.mvvm.createViewModelFactory
import dev.icerock.moko.mvvm.dispatcher.eventsDispatcherOnMain
import dev.icerock.moko.resources.desc.StringDesc

class AuthActivity :
    MvvmEventsActivity<ActivityAuthBinding, AuthViewModel, AuthViewModel.EventsListener>(),
    AuthViewModel.EventsListener {

    override val layoutId: Int = R.layout.activity_auth
    override val viewModelClass: Class<AuthViewModel> = AuthViewModel::class.java
    override val viewModelVariableId: Int = BR.viewModel

    override fun viewModelFactory(): ViewModelProvider.Factory {
        return createViewModelFactory {
            authModule.factory.createAuthViewModel(
                eventsDispatcher = eventsDispatcherOnMain()
            )
        }
    }

    override fun showError(message: StringDesc) {
        Toast.makeText(this, message.toString(this), Toast.LENGTH_SHORT).show()
    }

    override fun routeToCompleteAuth() {
        Log.d("MPP", "route to complete!")
    }
}