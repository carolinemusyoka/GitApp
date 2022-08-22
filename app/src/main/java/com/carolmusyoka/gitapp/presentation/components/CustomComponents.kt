package com.carolmusyoka.gitapp.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Menu
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.carolmusyoka.gitapp.R


@Composable
fun CustomTopBar(
    openDrawer: () -> Unit,
){
    Row(
        modifier = Modifier.fillMaxSize(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.Top
    ){
        Card(
            modifier = Modifier
                .width(50.dp),
            elevation = 5.dp,
            shape = RoundedCornerShape(12.dp)
        ) {
            IconButton(onClick = {
                openDrawer()
            }) {
                Icon(
                    imageVector = Icons.Outlined.Menu,
                    contentDescription = ""
                )
            }
        }
        Card(
            modifier = Modifier
                .size(50.dp),
            elevation = 5.dp,
            shape = RoundedCornerShape(12.dp),
            backgroundColor = Color.Transparent
        ) {
            Image(
                painter = painterResource(id = R.drawable.octocat), contentDescription = "User",
                modifier = Modifier.size(50.dp),
            )
        }
    }
}

@Composable
fun LoadingLottieAnimation(){

}