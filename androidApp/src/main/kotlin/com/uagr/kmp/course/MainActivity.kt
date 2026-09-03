package com.uagr.kmp.course

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.uagr.kmp.course.core.ui.navigation.NavigationController

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
       // enableEdgeToEdge()
        super.onCreate(savedInstanceState)

        setContent {
            NavigationController()
        }
    }
}

@Preview
@Composable
fun NavigationControllerAndroidPreview() {
    NavigationController()
}