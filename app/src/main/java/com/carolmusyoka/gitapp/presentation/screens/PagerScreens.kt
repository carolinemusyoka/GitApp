package com.carolmusyoka.gitapp.presentation.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.carolmusyoka.gitapp.presentation.components.FollowersCard
import com.carolmusyoka.gitapp.presentation.viewmodel.FollowersViewModel
import com.carolmusyoka.gitapp.presentation.viewmodel.UserViewModel
import java.lang.reflect.Modifier

@Composable
fun FollowersScreen(
    followersViewModel: FollowersViewModel = hiltViewModel(),
    userViewModel: UserViewModel = hiltViewModel()
) {


    Column(
        modifier = androidx.compose.ui.Modifier
            .fillMaxSize()
            .background(Color.Gray)
            .wrapContentSize(Alignment.Center)
    ) {
        Text(
            text = "Music View",
            fontWeight = FontWeight.Bold,
            color = Color.White,
            modifier = androidx.compose.ui.Modifier.align(Alignment.CenterHorizontally),
            textAlign = TextAlign.Center,
            fontSize = 25.sp
        )
    }

//    LazyColumn {
//        when{
//            followers.value.isLoading -> {
//
//            }
//            followers.value.data != null ->{
//                items(followers.value.data!!.size){ follower ->
//                    followers.value.data?.get(follower)?.let {
//                        FollowersCard(it)
//                    }
//                }
//            }
//            followers.value.error ->{
//
//            }
//        }
//    }
}

@Composable
fun FollowingScreen() {
    TODO()
}
@Composable
fun RepositoriesScreen() {
    TODO()
}
@Composable
fun GistsScreen() {
    TODO()
}