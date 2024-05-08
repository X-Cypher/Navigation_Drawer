package com.example.navigationdrawer

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.rounded.Menu
import androidx.compose.material3.Divider
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarColors
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.navigationdrawer.ui.theme.Green1
import com.example.navigationdrawer.ui.theme.NavigationDrawerTheme
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NavigationDrawerTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                        NavDrawer()
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun NavDrawer() {
    val  navigationController = rememberNavController()
    val coroutineScope = rememberCoroutineScope()
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val context = LocalContext.current.applicationContext

    ModalNavigationDrawer(
        drawerState = drawerState,
        gesturesEnabled = true,
        drawerContent = {
                ModalDrawerSheet { //Navigation drawer UI
                        Box(modifier = Modifier //header green color wala box
                            .background(Green1)
                            .fillMaxWidth()
                            .height(100.dp)) {
                        }

                    Divider() //green box and drawer items ke beech ek highlight(line) bna deta hai

                    //make nav drawer items
                    NavigationDrawerItem(
                        label = { Text(text = "Home", color = Green1) },
                        selected = false,
                        icon = { Icon(imageVector = Icons.Default.Home, contentDescription = "Home icon")},
                        onClick = {
                            coroutineScope.launch {
                                drawerState.close() //close the drawer
                            }
                            navigationController.navigate(Screens.Home.screen_name){
                                popUpTo(id = 0) //backstack will be cleared by doing this
                            }
                        }
                    )

                    NavigationDrawerItem(
                        label = { Text(text = "Profile", color = Green1) },
                        selected = false,
                        icon = { Icon(imageVector = Icons.Default.Person, contentDescription = "Profile Icon")},
                        onClick = {
                            coroutineScope.launch {
                                drawerState.close()
                            }
                            navigationController.navigate(Screens.Profile.screen_name){
                                popUpTo(id = 0) //backstack will be cleared by doing this
                            }
                        }
                    )

                    NavigationDrawerItem(
                        label = { Text(text = "Settings", color = Green1) },
                        selected = false,
                        icon = { Icon(imageVector = Icons.Default.Settings, contentDescription = "Settings icon")},
                        onClick = {
                            coroutineScope.launch {
                                drawerState.close()
                            }
                            navigationController.navigate(Screens.Settings.screen_name){
                                popUpTo(id = 0) //backstack will be cleared by doing this
                            }
                        }
                    )

                    NavigationDrawerItem(
                        label = { Text(text = "Logout", color = Green1) },
                        selected = false,
                        icon = { Icon(imageVector = Icons.Default.ExitToApp, contentDescription = "Logout icon")},
                        onClick = {
                            coroutineScope.launch {
                                drawerState.close()
                            }
                            Toast.makeText(context, "Logout", Toast.LENGTH_SHORT).show()
                        }
                    )
                }
        }
    ) {
            Scaffold {
                    val coroutineScope = rememberCoroutineScope()
                    TopAppBar(
                        title = { Text(text = "WhatsApp") },
                        colors = TopAppBarDefaults.topAppBarColors(containerColor = Green1, titleContentColor = Color.White, navigationIconContentColor = Color.White),
                        navigationIcon = {
                            IconButton(onClick = {
                                coroutineScope.launch {
                                    drawerState.open()
                                }
                            }) {
                                Icon(Icons.Rounded.Menu, contentDescription = "Menu")
                            }
                        }
                    )

                //Navigation host : Controller of navigation
                NavHost(navController = navigationController, startDestination = Screens.Home.screen_name) {
                        composable(Screens.Home.screen_name) {
                            Home()
                        }
                        composable(Screens.Profile.screen_name) {
                            Profile()
                        }
                        composable(Screens.Settings.screen_name) {
                            Settings()
                        }
                }
            }
    }
}