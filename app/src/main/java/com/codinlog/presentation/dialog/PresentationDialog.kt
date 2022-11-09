package com.codinlog.presentation

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.Display
import androidx.lifecycle.ViewModelProvider
import com.codinlog.presentation.dialog.BasePresentationDialog
import com.codinlog.presentation.dialog.collectionOnVisible
import com.codinlog.presentation.screen.FirstScreen
import com.codinlog.presentation.screen.SecondScreen

/**
 * @description TODO
 *
 * @author kouqurong / codinlog@foxmail.com
 * @date 2022/11/9
 */
private const val TAG = "PresentationDialog"

class PresentationDialog(context: Context, display: Display) :
    BasePresentationDialog(context, display) {

    private lateinit var mAppViewModel: ApplicationViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mAppViewModel = ViewModelProvider(this,ApplicationViewModelFactory())[ApplicationViewModel::class.java]


        collectionOnVisible {
            mAppViewModel.presentationScreenFlow.collect {
                showPresentationScreen(it)
            }
        }
    }

    private fun showPresentationScreen(state: PresentationScreenRoute) {
        Log.d(TAG,state.toString())
        when (state) {
            is PresentationScreenRoute.FirstScreen -> {
                setContentView(FirstScreen(context, container))
            }
            is PresentationScreenRoute.SecondScreen -> {
                setContentView(SecondScreen(context, container))
            }
            is PresentationScreenRoute.RemoteScreen -> {
                setRemoteViews(state.view)
            }
        }
    }

}