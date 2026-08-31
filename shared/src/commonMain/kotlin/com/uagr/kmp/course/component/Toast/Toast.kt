package com.uagr.kmp.course.component.Toast

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.tarifchakder.ktoast.ToastDefaults
import com.tarifchakder.ktoast.ToastHost
import com.tarifchakder.ktoast.ToastState
import com.uagr.kmp.course.theme.Dimens

@Composable
fun Toast(
    modifier : Modifier,
    toastState: ToastState
){
    Box {
        ToastHost(
            modifier = modifier.fillMaxSize().padding(Dimens.height16),
            alignment = Alignment.BottomCenter,
            hostState = toastState,
            transitionSpec = { ToastDefaults.SLIDE }
        )
    }
}
