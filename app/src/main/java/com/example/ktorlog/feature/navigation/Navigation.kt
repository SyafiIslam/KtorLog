package com.example.ktorlog.feature.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.example.ktorlog.feature.collection.CollectionScreen
import com.example.ktorlog.feature.detail.FilmDetailScreen
import com.example.ktorlog.feature.home.HomeScreen
import com.example.ktorlog.feature.search.SearchScreen
import com.example.ktorlog.feature.see_all.SeeAllScreen
import com.example.ktorlog.util.Route

@Composable
fun Navigation(navController: NavHostController) {

    NavHost(navController = navController, startDestination = Route.HomeScreen) {
        composable<Route.HomeScreen> {
            HomeScreen(navController)
        }

        composable<Route.FilmDetailScreen> {
            val args= it.toRoute<Route.FilmDetailScreen>()
            FilmDetailScreen(navController, args.type, args.id)
        }

        composable<Route.SeeAllScreen> {
            val args= it.toRoute<Route.SeeAllScreen>()
            SeeAllScreen(navController, args.type, args.category)
        }

        composable<Route.CollectionScreen> {
            val args= it.toRoute<Route.CollectionScreen>()
            CollectionScreen(navController, args.type)
        }
        composable<Route.SearchScreen> {
            SearchScreen(navController)
        }
    }
}