package com.freemimp.ui

import android.app.Activity
import android.net.Uri
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.freemimp.details.ui.ComicsDetailsScreen
import com.freemimp.main.ui.ComicsListNavigationConstants
import com.freemimp.main.ui.ComicsListScreen
import androidx.hilt.navigation.compose.hiltViewModel
import com.freemimp.details.ui.ComicsDetailsViewModel
import com.freemimp.main.ui.ComicsListViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(navHostController: NavHostController = rememberNavController()) {
    val context = LocalContext.current
    Scaffold(modifier = Modifier.fillMaxSize()) { paddingValues ->
        Column(modifier = Modifier
            .padding(paddingValues)
            .fillMaxSize()) {
            TopAppBar(title = { Text(text = "Marvel Super App") },
                modifier = Modifier,
                navigationIcon = {
                    IconButton(onClick = {
                       if (!navHostController.popBackStack()) {
                           (context as? Activity)?.finish()
                       }
                    }
                    ) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Back button"
                        )
                    }
                }
            )
            NavHost(
                navController = navHostController,
                startDestination = ComicsListNavigationConstants.MainList.name
            ) {
                composable(ComicsListNavigationConstants.MainList.name) {
                    ComicsListScreen(
                        hiltViewModel<ComicsListViewModel>(),
                        navHostController
                    )
                }
                composable(
                    "${ComicsListNavigationConstants.Details.name}/{imageUrl}",
                    arguments = listOf(navArgument("imageUrl") {
                        type = NavType.StringType
                    })
                ) {
                    val encodedImageUrl = it.arguments?.getString("imageUrl")
                    val imageUrl = Uri.decode(encodedImageUrl)

                    ComicsDetailsScreen(
                        hiltViewModel<ComicsDetailsViewModel>(),
                        imageUrl
                    )
                }
            }
        }
    }
}
