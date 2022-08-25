package com.carolmusyoka.gitapp.presentation.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.ExperimentalUnitApi
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import com.carolmusyoka.gitapp.data.model.GetUserResponse
import com.carolmusyoka.gitapp.presentation.components.FollowersCard
import com.carolmusyoka.gitapp.presentation.components.FollowingCard
import com.carolmusyoka.gitapp.presentation.components.RepositoryCard
import com.carolmusyoka.gitapp.presentation.viewmodel.FollowersViewModel
import com.carolmusyoka.gitapp.presentation.viewmodel.FollowingViewModel
import com.carolmusyoka.gitapp.presentation.viewmodel.RepositoryViewModel
import com.carolmusyoka.gitapp.presentation.viewmodel.UserViewModel
import com.google.accompanist.pager.*
import kotlinx.coroutines.launch

@OptIn(ExperimentalUnitApi::class, ExperimentalCoilApi::class, ExperimentalPagerApi::class)
@Composable
fun UserDetailsScreen(userResponse: GetUserResponse,
                      repositoryViewModel: RepositoryViewModel = hiltViewModel(),
                      followersViewModel: FollowersViewModel = hiltViewModel(),
                      followingViewModel: FollowingViewModel = hiltViewModel()) {
    val tabs = listOf(PagerItem.Followers, PagerItem.Following, PagerItem.Repositories)
    val pagerState = rememberPagerState()
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
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
            Tabs(tabs = tabs, pagerState = pagerState)
            TabsContent(tabs = tabs, pagerState = pagerState, repositoryViewModel, followersViewModel,  followingViewModel, userResponse.login ?: "")
        }
}

@OptIn(ExperimentalPagerApi::class)
@Composable
fun TabsContent(tabs: List<PagerItem>,
                pagerState: PagerState,
                repositoryViewModel: RepositoryViewModel,
                followersViewModel: FollowersViewModel,
                followingViewModel: FollowingViewModel,
                username: String) {
    val followers = remember{
        followersViewModel.getFollowers(username)
        followersViewModel.followers
    }.collectAsState()
    val following = remember{
        followingViewModel.getFollowing(username)
        followingViewModel.following
    }.collectAsState()
    val repositories = remember{
        repositoryViewModel.getRepositories(username)
        repositoryViewModel.repositories
    }.collectAsState()

    HorizontalPager(state = pagerState, count = tabs.size) { page ->
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            if (page == 0){
                LazyColumn {
                    when{
                        followers.value.isLoading -> {

                        }
                        followers.value.data != null ->{
                            items(followers.value.data!!.size){ follower ->
                                followers.value.data?.get(follower)?.let {
                                    FollowersCard(it)
                                }
                            }
                        }
                        followers.value.error ->{

                        }
                    }
                }
            }
            if (page == 1){
                LazyColumn {
                    when {
                        following.value.isLoading -> {

                        }
                        following.value.data != null -> {
                            items(following.value.data!!.size) { userfollowing ->
                                following.value.data?.get(userfollowing)?.let {
                                    FollowingCard(it)
                                }
                            }
                        }
                        following.value.error -> {
                        }
                    }
                }
            }
            if (page == 2){
                LazyColumn {
                    when {
                        repositories.value.isLoading -> {

                        }
                        repositories.value.data != null -> {
                            items(repositories.value.data!!.size) { repository ->
                                repositories.value.data?.get(repository)?.let {
                                    RepositoryCard(it)
                                }
                            }
                        }
                        repositories.value.error -> {
                        }
                    }
                }
            }
        }
    }
}
//        tabs[page].screen


@OptIn(ExperimentalPagerApi::class, ExperimentalUnitApi::class)
@Composable
fun Tabs(tabs: List<PagerItem>, pagerState: PagerState) {
    val scope = rememberCoroutineScope()
    TabRow(selectedTabIndex = pagerState.currentPage,
        backgroundColor = Color.White,
        contentColor = Color.Black,
        indicator = { tabPositions ->
            TabRowDefaults.Indicator(
                Modifier.pagerTabIndicatorOffset(pagerState, tabPositions),
                color = Color.Black,
            )
        }
    ) {
        tabs.forEachIndexed { index, tab ->
            Tab(
                text = { Text(text = tab.title ,maxLines = 1,
                    fontWeight = FontWeight.Bold,
                    overflow = TextOverflow.Ellipsis,
                    fontSize = TextUnit(value = 12F, type = TextUnitType.Sp)) },
                selected = pagerState.currentPage == index,
                onClick = {
                    scope.launch {
                        pagerState.animateScrollToPage(index)
                    }
                },
            )
        }
    }
}

@Preview
@Composable
fun PreviewDetailScreen(){
    UserDetailsScreen(userResponse = GetUserResponse(
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
            public_gists = 60,
            public_repos = 100,
            received_events_url = "https://api.github.com/users/octocat/received_events",
            repos_url = "https://api.github.com/users/octocat/repos",
            site_admin = true,
            starred_url = "https://api.github.com/users/octocat/starred{/owner}{/repo}",
            subscriptions_url = "https://api.github.com/users/octocat/subscriptions",
            twitter_username = "",
            type = "User",
            updated_at = "2020-01-01T00:00:00Z",
            url = "https://api.github.com/users/octocat")
    )
}

@OptIn(ExperimentalPagerApi::class)
@Preview(showBackground = true)
@Composable
fun TabsPreview() {
    val tabs = listOf(
        PagerItem.Followers,
        PagerItem.Following,
        PagerItem.Repositories
    )
    val pagerState = rememberPagerState()
    Tabs(tabs = tabs, pagerState = pagerState)
}

sealed class PagerItem(var title:String) {
    object Followers: PagerItem("Followers")
    object Following: PagerItem("Following")
    object Repositories: PagerItem("Repos")
}