package com.example.ktorlog.feature.home.component.tv

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.ktorlog.R
import com.example.ktorlog.feature.component.EmptyStateCard
import com.example.ktorlog.feature.component.FilmCard
import com.example.ktorlog.feature.home.HomeViewModel
import com.example.ktorlog.theme.Neutral50
import com.example.ktorlog.util.Constant
import com.example.ktorlog.util.Route

@Composable
fun OnTheAir(navController: NavController, viewModel: HomeViewModel) {

    val isLoading by viewModel.isLoading
    val onTheAirShowsList by viewModel.onTheAirShowsList

    Column(
        Modifier.fillMaxWidth()
    ) {
        Row(
            Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                stringResource(R.string.txt_upcoming),
                color = Neutral50,
                fontWeight = FontWeight.Bold
            )

            Row(
                modifier = Modifier.clickable {
                    navController.navigate(
                        Route.SeeAllScreen(
                            type = Constant.TV,
                            category = Constant.ON_THE_AIR
                        )
                    )
                },
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Text(
                    stringResource(R.string.txt_see_all),
                    color = Neutral50,
                )
                Icon(
                    Icons.AutoMirrored.Filled.KeyboardArrowRight,
                    contentDescription = "",
                    tint = Neutral50
                )
            }
        }

        Spacer(Modifier.height(8.dp))
        LazyRow(
            Modifier.fillMaxWidth(),
        ) {
            if (onTheAirShowsList.isEmpty() || isLoading) {
                items(2) {
                    EmptyStateCard(
                        Modifier
                            .width(150.dp)
                            .height(250.dp)
                            .padding(end = 16.dp)
                    )
                }
            } else {
                items(onTheAirShowsList) {
                    FilmCard(
                        Modifier
                            .width(150.dp)
                            .height(250.dp)
                            .wrapContentSize(Alignment.Center)
                            .padding(end = 16.dp),
                        onClick = {
                            navController.navigate(Route.FilmDetailScreen(Constant.TV, it.id))
                        },
                        imagePath = it.poster_path
                    )
                }
            }
        }
    }
}