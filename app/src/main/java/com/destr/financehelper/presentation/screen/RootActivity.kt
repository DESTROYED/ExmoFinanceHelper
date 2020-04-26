package com.destr.financehelper.presentation.screen

import android.os.Bundle
import androidx.navigation.Navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.destr.financehelper.R
import com.destr.financehelper.presentation.common.ApiErrorsHandler
import kotlinx.android.synthetic.main.main_activity.*
import moxy.MvpAppCompatActivity

class RootActivity : MvpAppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        ApiErrorsHandler.view = bottom_navigation
        bottom_navigation.setupWithNavController(findNavController(this, R.id.navigation_fragment))
    }
}