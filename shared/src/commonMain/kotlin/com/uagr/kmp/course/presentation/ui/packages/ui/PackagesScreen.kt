/*
 * PackagesScreen.kt
 * Copyright (c) 2026. All rights reserved
 */
package com.uagr.kmp.course.presentation.ui.packages.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.uagr.kmp.course.presentation.component.container.SafeScreenContainer
import com.uagr.kmp.course.presentation.component.container.SafeScreenContainerTest

@Composable
fun PackagesScreen() {
    SafeScreenContainer {
        PackagesContainer(
            activePackagesClick = {

            }
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun PackagesScreenPreview() {
    SafeScreenContainerTest {
        PackagesScreen()
    }
}
