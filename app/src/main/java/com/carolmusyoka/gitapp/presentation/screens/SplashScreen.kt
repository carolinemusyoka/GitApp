package com.carolmusyoka.gitapp.presentation.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.*
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.produceState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.carolmusyoka.gitapp.R
import kotlinx.coroutines.delay

@SuppressLint("ProduceStateDoesNotAssignValue")
@Composable
fun SplashScreen(
    navToHomeScreen: () -> Unit,
) {
    val composition by rememberLottieComposition(
        LottieCompositionSpec.RawRes(R.raw.git_logo))
    val progress by animateLottieCompositionAsState(
        composition = composition,
        iterations = Int.MAX_VALUE,
        isPlaying = true
    )
    Surface (
        color = Color.White,
        modifier = Modifier.fillMaxSize()
    ) {
        Row(
           verticalAlignment = Alignment.CenterVertically,
           horizontalArrangement = Arrangement.Center
        ){
            LottieAnimation(
                composition = composition,
                progress = progress,
                modifier = Modifier.height(300.dp)
            )
        }

    }

    produceState(initialValue = -1){
        delay(3000)
        navToHomeScreen()
    }
}

@Preview
@Composable
fun SplashScreenPreview() {
    SplashScreen(navToHomeScreen = {})
}