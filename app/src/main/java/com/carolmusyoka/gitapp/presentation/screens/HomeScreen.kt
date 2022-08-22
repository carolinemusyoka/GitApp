package com.carolmusyoka.gitapp.presentation.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.ParagraphStyle
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.carolmusyoka.gitapp.presentation.components.CustomTopBar
import com.carolmusyoka.gitapp.presentation.theme.lightblack
import com.carolmusyoka.gitapp.presentation.theme.lightbox
import com.carolmusyoka.gitapp.presentation.theme.subTitleTextColor


@Composable
fun HomeScreen(
    navToProfile: () -> Unit,
    openDrawer:() -> Unit,
    navToUser: () -> Unit,
    modifier: Modifier = Modifier
) {
    val scrollState = rememberScrollState()
    Box(modifier = Modifier
        .fillMaxSize()
        .verticalScroll(scrollState)
    ){
        Column(modifier = Modifier.padding(30.dp)){

            // Custom topbar
            CustomTopBar(openDrawer)
            Spacer(modifier = Modifier.padding(10.dp))
            // Search Section
            SearchSection(navToUser)

            // Results Section
        }
    }

}

@Composable
fun SearchSection(navToUser: () -> Unit) {
    var search by remember { mutableStateOf("") }

    Column(modifier = Modifier.fillMaxWidth(), verticalArrangement = Arrangement.Top) {
        Text(
            buildAnnotatedString {
                withStyle(
                    style = ParagraphStyle(lineHeight = 30.sp)
                ) {
                    withStyle(
                        style = SpanStyle(
                            color = Color.Black,
                            fontSize = 24.sp
                        )
                    ) {
                        append("Search for\n")
                    }
                    withStyle(
                        style = SpanStyle(
                            fontWeight = FontWeight.Bold,
                            color = Color.Black,
                            fontSize = 24.sp
                        )
                    ) {
                        append("Users on GitHub")
                    }
                }
            }
        )

        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start,
            modifier = Modifier
                .fillMaxWidth()
                .height(80.dp)
                .padding(top = 30.dp)
        ){
            TextField(
                modifier = Modifier
                    .weight(0.85f),
                colors = TextFieldDefaults.textFieldColors(
                    backgroundColor = lightbox,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                ),
                value = search,
                shape = RoundedCornerShape(12.dp),
                singleLine = true,
                onValueChange = { search = it },
                placeholder = {
                    Text(
                        text = "Search for user",
                        color = subTitleTextColor,
                    ) },
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Filled.Search,
                        contentDescription = "",
                        tint = lightblack
                    )
                },
            )
            Spacer(modifier = Modifier.width(5.dp))
            Card(
                modifier = Modifier
                    .width(60.dp)
                    .padding(start = 16.dp)
                    .clickable { },
                elevation = 5.dp,
                shape = RoundedCornerShape(12.dp),

                ){
                IconButton(
                    onClick = navToUser,
                ) {
                    Icon(
                        imageVector = Icons.Filled.Search,
                        contentDescription = "search",
                        modifier = Modifier.size(20.dp, 20.dp)
                    )
                }
            }
        }

    }
}
