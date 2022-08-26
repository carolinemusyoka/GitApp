package com.carolmusyoka.gitapp.presentation.components

import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExpandLess
import androidx.compose.material.icons.filled.ExpandMore
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.outlined.*
import androidx.compose.material.icons.rounded.ArrowBackIosNew
import androidx.compose.material.icons.rounded.Bookmark
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
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
import com.google.accompanist.flowlayout.FlowRow


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
                modifier = Modifier.height(400.dp).width(400.dp)
            )

        }
    }
}

@Composable
fun ErrorLottieAnimation(){
    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.error))
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

@Composable
fun BookmarkLottieAnimation(){
    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.bookmark))
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
                height = 450.dp,
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
                        text = userResponse.company ?: "NA",
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
                    text = userResponse.email ?: "NA",
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
                    text = "@${userResponse.twitter_username ?: "NA"}",
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

@OptIn(ExperimentalCoilApi::class)
@Composable
fun FollowersCard(
    followersResponse: GetUserFollowersResponseItem,
) {
    Card(
        modifier = Modifier
            .width(350.dp)
            .height(100.dp)
            .padding(10.dp),
        shape = RoundedCornerShape(10.dp),
        elevation = 10.dp
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(10.dp),
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
                            style = MaterialTheme.typography.body1,
                            fontWeight = FontWeight.SemiBold,
                            modifier = Modifier.padding(top = 1.dp)
                        )
                    }
                }
            }
        }
    }

}

@OptIn(ExperimentalUnitApi::class, ExperimentalCoilApi::class)
@Composable
fun FollowingCard(
    followingResponse: GetUserFollowingResponseItem,
) {
    Card(
        modifier = Modifier
            .width(350.dp)
            .height(100.dp)
            .padding(10.dp),
        shape = RoundedCornerShape(10.dp),
        elevation = 10.dp
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(10.dp),
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
                    painter = rememberImagePainter(followingResponse.avatar_url),
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
                            text = followingResponse.login ?: "NA",
                            style = MaterialTheme.typography.body1,
                            fontWeight = FontWeight.SemiBold,
                            modifier = Modifier.padding(top = 1.dp)
                        )
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalCoilApi::class, ExperimentalUnitApi::class, ExperimentalMaterialApi::class)
@Composable
fun RepositoryCard(
    repositoriesResponseItem: GetUserRepositoriesResponseItem,
) {
    var expanded by remember { mutableStateOf(false) }
    val extraPadding by animateDpAsState(
        if (expanded) 40.dp else 0.dp,
        animationSpec = spring(
            dampingRatio = Spring.DampingRatioMediumBouncy,
            stiffness = Spring.StiffnessLow
        )
    )
    Card(
        modifier = Modifier.padding(vertical = 4.dp, horizontal = 8.dp),
        shape = RoundedCornerShape(10.dp),
        elevation = 15.dp
    ) {
        Row(modifier = Modifier.padding(24.dp)) {
            Column(modifier = Modifier
                .weight(1f)
                .padding(bottom = extraPadding.coerceAtLeast(0.dp))
            ) {
                Text(
                    modifier = Modifier.padding(top = 8.dp),
                    text = repositoriesResponseItem.name,
                    style = MaterialTheme.typography.body1.copy(
                        fontWeight = FontWeight.Bold
                    ))
                if (expanded){
                    Spacer(modifier = Modifier.height(8.dp))
                    // description that wraps its content
                    Text(
                        text = repositoriesResponseItem.description ?: "NA",
                        style = MaterialTheme.typography.body2,
                        maxLines = 10,
                        overflow = TextOverflow.Ellipsis
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_star),
                             contentDescription = "Star Icon")
                        Spacer(modifier = Modifier.width(7.dp))
                        Text(
                            text =  repositoriesResponseItem.stargazers_count.toString(),
                            maxLines = 1,
                            fontWeight = FontWeight.W500,
                            overflow = TextOverflow.Ellipsis,
                            fontSize = TextUnit(value = 14F, type = TextUnitType.Sp),
                        )
                        Spacer(modifier = Modifier.width(20.dp))
                        Icon(
                            painter = painterResource(id = R.drawable.ic_git_icon),
                            contentDescription = "Star Icon")
                        Spacer(modifier = Modifier.width(7.dp))
                        Text(
                            text =  repositoriesResponseItem.forks.toString(),
                            maxLines = 1,
                            fontWeight = FontWeight.W500,
                            overflow = TextOverflow.Ellipsis,
                            fontSize = TextUnit(value = 14F, type = TextUnitType.Sp),
                        )
                        Spacer(modifier = Modifier.width(20.dp))
                        Icon(
                            Icons.Filled.Visibility,
                            contentDescription = "Watchers")
                        Spacer(modifier = Modifier.width(7.dp))
                        Text(
                            text =  repositoriesResponseItem.watchers_count.toString(),
                            maxLines = 1,
                            fontWeight = FontWeight.W500,
                            overflow = TextOverflow.Ellipsis,
                            fontSize = TextUnit(value = 14F, type = TextUnitType.Sp),
                        )
                    }
                    // list of chips
                    FlowRow(
                        mainAxisSpacing = 5.dp,

                    ) {
                        for (chip in repositoriesResponseItem.topics) {
                            Chip(
                                onClick = { /* Do something! */ },
                                colors = ChipDefaults.chipColors(
                                    backgroundColor = Color(0xff9ed1e1),
                                    contentColor = Color.Black
                                )
                            ) {
                                Text(chip)
                            }
                        }
                    }
                }
            }
            IconButton(onClick = { expanded = !expanded }
            ) {
                Icon(
                    imageVector = if (expanded) Icons.Filled.ExpandLess else Icons.Filled.ExpandMore,
                    contentDescription = if (expanded) {
                        "Collapse"
                    } else {
                        "Expand"
                    }
                )
            }
        }
    }
}

@Composable
fun CustomTopBar(
    modifier: Modifier = Modifier,
    icon: ImageVector? = null,
    title: String? = null,
    pressBack: () -> Unit = {},
    onClick: () -> Unit = {},
) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier.fillMaxWidth()
    ) {
        IconButtonMenu(icon = Icons.Rounded.ArrowBackIosNew, onClick = { pressBack() })
        if (title != null) {
            Text(
                text = title,
                style = MaterialTheme.typography.h6,
            )
        }
        icon?.let { IconButtonMenu(icon = it, onClick = onClick ) }
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun IconButtonMenu(icon: ImageVector, onClick: () -> Unit= {}) {
    Surface(
        onClick = onClick,
        shape = MaterialTheme.shapes.medium,
        color = MaterialTheme.colors.surface
    ) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier.size(44.dp)
        ) {
            Icon(
                icon,
                contentDescription = null,
                tint = MaterialTheme.colors.onSurface,
                modifier = Modifier.size(20.dp)
            )
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
            login = "carolinemusyoka",
            name = "Carol Musyoka",
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

@Preview
@Composable
fun RepositoryPreview(){
    RepositoryCard(repositoriesResponseItem =
        GetUserRepositoriesResponseItem(
            allow_forking = true,
            archive_url = "",
            archived = false,
            assignees_url = "",
            blobs_url = "",
            branches_url = "",
            clone_url = "",
            collaborators_url = "",
            comments_url = "",
            commits_url = "",
            compare_url = "",
            contents_url = "",
            contributors_url = "",
            created_at = "",
            default_branch = "",
            deployments_url = "",
            description = " ",
            disabled = false,
            downloads_url = "",
            events_url = "",
            fork = false,
            forks = 10,
            forks_count = 10,
            forks_url = "",
            full_name = "",
            git_commits_url = "",
            git_refs_url = "",
            git_tags_url = "",
            git_url = "",
            has_downloads = false,
            has_issues = false,
            has_pages = false,
            has_projects = false,
            has_wiki = false,
            homepage  = "",
            hooks_url = "",
            html_url = "",
            id = 1,
            is_template = false,
            issue_comment_url = "",
            issue_events_url = "",
            issues_url = "",
            keys_url = "",
            labels_url = "",
            language = "",
            languages_url = "",
            license = License(
                key = "",
                name = "",
                node_id = "",
                spdx_id = "",
                url = ""
            ),
            merges_url = "",
            milestones_url = "",
            mirror_url = "",
            name = "2048TheGame",
            node_id = "",
            notifications_url = "",
            open_issues = 0,
            open_issues_count = 0,
            owner = Owner(
                avatar_url = "",
                events_url = "",
                followers_url = "",
                following_url = "",
                gists_url = "",
                gravatar_id = "",
                html_url = "",
                id = 0,
                login = "",
                node_id = "",
                organizations_url = "",
                received_events_url = "",
                repos_url = "",
                site_admin = false,
                starred_url = "",
                subscriptions_url = "",
                type = "",
                url = ""
            ),
            private = false,
            pulls_url = "",
            pushed_at = "",
            releases_url = "",
            size = 0,
            ssh_url = "",
            stargazers_count = 30,
            stargazers_url = "",
            statuses_url = "",
            subscribers_url = "",
            subscription_url = "",
            svn_url = "",
            tags_url = "",
            teams_url = "",
            topics = listOf<String>(),
            trees_url = "",
            updated_at = "",
            url = "",
            visibility = "",
            watchers = 0,
            watchers_count = 0,
            web_commit_signoff_required = false
        )
    )
}

