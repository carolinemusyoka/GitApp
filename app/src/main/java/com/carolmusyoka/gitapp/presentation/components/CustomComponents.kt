package com.carolmusyoka.gitapp.presentation.components

import androidx.annotation.Dimension
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.ExperimentalUnitApi
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.Dimension.Companion.fillToConstraints
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.carolmusyoka.gitapp.R
import com.carolmusyoka.gitapp.data.model.*
import com.carolmusyoka.gitapp.data.uistates.UserUiState
import com.carolmusyoka.gitapp.navigation.DashDestinations
import com.carolmusyoka.gitapp.presentation.theme.lightblack
import com.carolmusyoka.gitapp.presentation.theme.lightbox
import com.carolmusyoka.gitapp.presentation.viewmodel.UserViewModel
import com.carolmusyoka.gitapp.util.UserEvent


@Composable
fun CustomSearch(
    state: UserUiState,
    userViewModel: UserViewModel,
){
    val localFocusManager = LocalFocusManager.current
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Start,
        modifier = Modifier
            .fillMaxWidth()
            .height(90.dp)
            .padding(top = 30.dp)
    ) { TextField(
        value = state.searchString,
        shape = RoundedCornerShape(12.dp),
        label = { Text("Search") },
        singleLine = true,
        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search),
        keyboardActions = KeyboardActions(
            onSearch = {
                // if the search string is empty, validation error shows
                if (state.searchString.isBlank()) {
                    userViewModel.onEvent(
                        UserEvent.SetValidationError(
                            "Search can't be empty"
                        )
                    )
                } else {
                    userViewModel.onEvent(UserEvent.SearchUser(state.searchString))
                    userViewModel.onEvent(UserEvent.SetSearchText(state.searchString))
                }
                localFocusManager.clearFocus()
            }
        ),
        onValueChange = {
            // users can't search the string that has more than 150 chars
            if (it.length <= 150) {
                userViewModel.onEvent(UserEvent.SetSearchText(it))
            } },
        leadingIcon = {
            Icon(
                imageVector = Icons.Outlined.Search,
                contentDescription = null,
                tint = lightblack,
            )
        },
        trailingIcon = {
            if (state.searchString.isNotEmpty()) {
                IconButton(
                    onClick = {
                        userViewModel.onEvent(
                            UserEvent.ClearSearchText
                        )
                        localFocusManager.clearFocus()
                    }) {
                    Icon(
                        imageVector = Icons.Outlined.Close,
                        contentDescription = null
                    )
                }
            }
        },
        placeholder = {
            Text(text = "Search user")
        },
        modifier = Modifier
            .weight(0.85f),
        colors = TextFieldDefaults.textFieldColors(
            backgroundColor = lightbox,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
        ))
    }
}

@Composable
fun LoadingLottieAnimation(){
    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.loading))
    val progress by animateLottieCompositionAsState(
        composition = composition,
        iterations = Int.MAX_VALUE,
        isPlaying = true
    )
    Surface(
        color = Color.White,
        modifier = Modifier.fillMaxSize()
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            LottieAnimation(
                composition = composition,
                progress = progress,
                modifier = Modifier.height(300.dp)
            )

        }
    }
}

@OptIn(ExperimentalUnitApi::class, ExperimentalCoilApi::class, ExperimentalMaterialApi::class)
@Composable
fun ProfileDetailsScreen(
    userResponse: GetUserResponse,
    navController: NavController
) {

    // Card
    Card(
        modifier = Modifier
            .size(
                //width, fill the max width of the screen in dp units
                width = 400.dp,
                height = 400.dp,
            )
            .padding(15.dp),
        shape = RoundedCornerShape(12.dp),
        elevation = 5.dp,
        onClick = {
            navController.navigate(DashDestinations.UserDetail.createRoute(userResponse.login?:""))
        }
    ){
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(15.dp),
            horizontalAlignment = Alignment.Start
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 15.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Image(
                    modifier = Modifier
                        .height(100.dp)
                        .width(100.dp)
                        .clip(CircleShape),
                    painter = rememberImagePainter(userResponse.avatar_url),
                    contentScale = ContentScale.Fit,
                    contentDescription = "User Avatar"
                )
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 15.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    Column(modifier = Modifier.fillMaxWidth()) {
                        Text(
                            text = userResponse.name ?: "NA",
                            style = MaterialTheme.typography.h5,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.padding(top = 1.dp)
                        )
                        Text(
                            text = userResponse.login ?: "NA",
                            style = MaterialTheme.typography.body1,
                            modifier = Modifier.padding(top = 5.dp)
                        )
                    }
                }
            }
            Spacer(modifier = Modifier.height(5.dp))
            Text(
                text = userResponse.bio ?: "NA",
                fontWeight = FontWeight.Medium,
                overflow = TextOverflow.Ellipsis,
                fontSize = TextUnit(value = 14F, type = TextUnitType.Sp),
                modifier = Modifier.padding(5.dp)
            )
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(Icons.Outlined.People, contentDescription = "Icon Company")
                Spacer(modifier = Modifier.width(7.dp))
                Text(
                    text = (userResponse.followers.toString() + " followers"),
                    maxLines = 1,
                    fontWeight = FontWeight.W500,
                    overflow = TextOverflow.Ellipsis,
                    fontSize = TextUnit(value = 14F, type = TextUnitType.Sp),
                )
                Spacer(modifier = Modifier.width(5.dp))
                Text(
                    text = (". " + userResponse.following.toString() + " following"),
                    maxLines = 1,
                    fontWeight = FontWeight.W500,
                    overflow = TextOverflow.Ellipsis,
                    fontSize = TextUnit(value = 14F, type = TextUnitType.Sp),
                )
            }
            Spacer(modifier = Modifier.height(10.dp))
            userResponse.company.let {
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(Icons.Outlined.AccountBox, contentDescription = "Icon Company")
                    Spacer(modifier = Modifier.width(7.dp))
                    Text(
                        text = userResponse.company.toString() ?: "NA",
                        maxLines = 1,
                        fontWeight = FontWeight.W500,
                        overflow = TextOverflow.Ellipsis,
                        fontSize = TextUnit(value = 14F, type = TextUnitType.Sp),
                    )
                }
            }
            userResponse.location.let {

            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(Icons.Outlined.LocationOn, contentDescription = "Icon Location")
                Spacer(modifier = Modifier.width(7.dp))
                Text(
                    text = userResponse.location ?: "NA",
                    maxLines = 1,
                    fontWeight = FontWeight.W500,
                    overflow = TextOverflow.Ellipsis,
                    fontSize = TextUnit(value = 14F, type = TextUnitType.Sp),
                )
            }
        }
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(Icons.Outlined.Email, contentDescription = "Icon Company")
                Spacer(modifier = Modifier.width(7.dp))
                Text(
                    text = userResponse.email.toString(),
                    maxLines = 1,
                    fontWeight = FontWeight.W500,
                    overflow = TextOverflow.Ellipsis,
                    fontSize = TextUnit(value = 14F, type = TextUnitType.Sp),
                )
            }
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(Icons.Outlined.Web, contentDescription = "Icon Company")
                Spacer(modifier = Modifier.width(7.dp))
                Text(
                    text = "@${userResponse.twitter_username}",
                    maxLines = 1,
                    fontWeight = FontWeight.W500,
                    overflow = TextOverflow.Ellipsis,
                    fontSize = TextUnit(value = 14F, type = TextUnitType.Sp),
                )
            }

            Spacer(modifier = Modifier.height(10.dp))
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(Icons.Outlined.Code, contentDescription = "Icon Company")
                Spacer(modifier = Modifier.width(15.dp))
                Text(
                    text = (userResponse.public_repos.toString() + " Repositories"),
                    maxLines = 1,
                    fontWeight = FontWeight.W500,
                    overflow = TextOverflow.Ellipsis,
                    fontSize = TextUnit(value = 14F, type = TextUnitType.Sp),
                )
                Spacer(modifier = Modifier.width(35.dp))
                Text(
                    text = (userResponse.public_gists.toString() + " Gists"),
                    maxLines = 1,
                    fontWeight = FontWeight.W500,
                    overflow = TextOverflow.Ellipsis,
                    fontSize = TextUnit(value = 14F, type = TextUnitType.Sp),
                )
            }
        }
    }
}

@OptIn(ExperimentalUnitApi::class, ExperimentalCoilApi::class)
@Composable
fun FollowersCard(
    followersResponse: GetUserFollowersResponseItem,
) {
    Card(
        modifier = Modifier.fillMaxWidth()
            .height(150.dp)
            .padding(10.dp),
        shape = RoundedCornerShape(10.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight().padding(10.dp),
            horizontalAlignment = Alignment.Start
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Image(
                    modifier = Modifier
                        .height(100.dp)
                        .width(100.dp)
                        .clip(CircleShape),
                    painter = rememberImagePainter(followersResponse.avatar_url),
                    contentScale = ContentScale.Fit,
                    contentDescription = "User Avatar"
                )
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 15.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    Column(modifier = Modifier.fillMaxWidth()) {
                        Text(
                            text = followersResponse.login ?: "NA",
                            style = MaterialTheme.typography.h5,
                            fontWeight = FontWeight.SemiBold,
                            modifier = Modifier.padding(top = 1.dp)
                        )
                    }
                }
            }
        }
    }

}

@Preview
@Composable
fun ProfileDetailsScreenPreview() {
    ProfileDetailsScreen(
        userResponse = GetUserResponse(
            avatar_url = "https://avatars3.githubusercontent.com/u/17098281?v=4",
            bio = "I am a software engineer and a full stack developer. I am passionate about building products that people love to use.",
            blog = "https://",
            company = "",
            created_at = "2020-01-01T00:00:00Z",
            email = null,
            events_url = "https://api.github.com/users/octocat/events{/privacy}",
            followers = 0,
            followers_url = "https://api.github.com/users/octocat/followers",
            following = 0,
            following_url = "https://api.github.com/users/octocat/following{/other_user}",
            gists_url = "https://api.github.com/users/octocat/gists{/gist_id}",
            gravatar_id = "",
            hireable = true,
            html_url = "",
            id = 0,
            location = "Nairobi, Kenya",
            login = "",
            name = "",
            node_id = "",
            organizations_url = "https://api.github.com/users/octocat/orgs",
            public_gists = 0,
            public_repos = 0,
            received_events_url = "https://api.github.com/users/octocat/received_events",
            repos_url = "https://api.github.com/users/octocat/repos",
            site_admin = true,
            starred_url = "https://api.github.com/users/octocat/starred{/owner}{/repo}",
            subscriptions_url = "https://api.github.com/users/octocat/subscriptions",
            twitter_username = "",
            type = "User",
            updated_at = "2020-01-01T00:00:00Z",
            url = "https://api.github.com/users/octocat"),
        navController = rememberNavController()
    )
}

@Preview
@Composable
fun PreviewFollowers(){
    FollowersCard(followersResponse =
        GetUserFollowersResponseItem(
            avatar_url = "https://avatars3.githubusercontent.com/u/17098281?v=4",
            events_url = "https://api.github.com/users/octocat/events{/privacy}",
            followers_url = "https://api.github.com/users/octocat/followers",
            following_url = "https://api.github.com/users/octocat/following{/other_user}",
            gists_url = "https://api.github.com/users/octocat/gists{/gist_id}",
            gravatar_id = "",
            html_url = "",
            id = 0,
            login = "Carol Musyoka",
            node_id = "",
            organizations_url = "https://api.github.com/users/octocat/orgs",
            received_events_url = "https://api.github.com/users/octocat/received_events",
            repos_url = "https://api.github.com/users/octocat/repos",
            site_admin = true,
            starred_url = "https://api.github.com/users/octocat/starred{/owner}{/repo}",
            subscriptions_url = "https://api.github.com/users/octocat/subscriptions",
            type = "User",
            url = "https://api.github.com/users/octocat"
        )
    )

}

