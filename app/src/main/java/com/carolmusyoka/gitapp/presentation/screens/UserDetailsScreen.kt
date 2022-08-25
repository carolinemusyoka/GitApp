package com.carolmusyoka.gitapp.presentation.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.ExperimentalUnitApi
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import com.carolmusyoka.gitapp.data.model.GetUserResponse

@OptIn(ExperimentalUnitApi::class, ExperimentalCoilApi::class)
@Composable
fun UserDetailsScreen(userResponse: GetUserResponse) {
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