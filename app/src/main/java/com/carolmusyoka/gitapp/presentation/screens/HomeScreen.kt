package com.carolmusyoka.gitapp.presentation.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.ParagraphStyle
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.carolmusyoka.gitapp.presentation.components.*
import com.carolmusyoka.gitapp.presentation.viewmodel.UserViewModel


@Composable
fun HomeScreen(
    userViewModel: UserViewModel = hiltViewModel(),
    scaffoldState: ScaffoldState = rememberScaffoldState(),
    modifier: Modifier = Modifier,
    navController: NavController
) {

    val state = userViewModel.state.value

    Scaffold(
        modifier = Modifier.padding(top = 30.dp),
        scaffoldState = scaffoldState,
        topBar = {
            Column(modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp), verticalArrangement = Arrangement.Top) {
                Text(
                    buildAnnotatedString {
                        withStyle(
                            style = ParagraphStyle(lineHeight = 30.sp)
                        ) {
                            withStyle(
                                style = SpanStyle(
                                    color = Color.Black,
                                    fontSize = 29.sp
                                )
                            ) {
                                append("Search for\n")
                            }
                            withStyle(
                                style = SpanStyle(
                                    fontWeight = FontWeight.ExtraBold,
                                    color = Color.Black,
                                    fontSize = 29.sp
                                )
                            ) {
                                append("Users on GitHub")
                            }
                        }
                    }
                )
                CustomSearch(state = state, userViewModel =userViewModel )
            }
        },
        content = { padding ->
            Box(modifier = modifier.fillMaxSize()) {
                state.data?.let {
                    ProfileDetailsScreen(it, navController = navController)
                }
            }
            // Display loading state of UI
            if (state.isLoading || state.data == null) {
                Column(
                    modifier = modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Box(modifier = modifier.fillMaxSize()) {
                        LoadingLottieAnimation()
                    }
                }
            }
            if (state.error) {
                Column(
                    modifier = modifier.fillMaxSize().padding(20.dp),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Box(modifier = modifier.fillMaxSize()) {
                        ErrorLottieAnimation()
                    }
                }
            }
        }
    )
}
