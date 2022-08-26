package com.carolmusyoka.gitapp.presentation.screens

import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.carolmusyoka.gitapp.presentation.components.CustomTopBar
import com.carolmusyoka.gitapp.presentation.components.ProfileDetailsScreen
import com.carolmusyoka.gitapp.presentation.viewmodel.RoomViewModel
import kotlinx.coroutines.launch

@Composable
fun BookmarksScreen(
    roomViewModel: RoomViewModel = hiltViewModel(),
    navController: NavController
) {
    val bookmarkList = roomViewModel.bookmarks.observeAsState(initial = listOf())
    val coroutineScope = rememberCoroutineScope()
    val context = LocalContext.current
    Scaffold(
        Modifier.fillMaxSize(),
        topBar = {
            CustomTopBar(
                icon = Icons.Filled.Delete,
                title = "Bookmarks",
                onClick = {
                    coroutineScope.launch {
                        roomViewModel.deleteAll()
                        Toast.makeText(context, "All bookmarks deleted", Toast.LENGTH_SHORT).show()
                    }
                }
            )
        },
        content = {
            if (bookmarkList.value.isEmpty()) {
            } else {
                Column(Modifier.fillMaxSize()) {
                    LazyColumn(Modifier.fillMaxSize()) {
                        itemsIndexed(bookmarkList.value) { _, element ->
                            ProfileDetailsScreen(
                                userResponse = element,
                                navController = navController
                            )
                        }
                    }
                }
            }
        }
    )
}