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
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.rounded.Menu
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Divider
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
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
//                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    NavDrawer()
//                    MyBottomAppBar()
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
            Scaffold (){
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

@Preview
@Composable
fun NavDrawerPreview() {
    MyBottomAppBar()
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MyBottomAppBar() {
    val navigationController = rememberNavController()
    val context = LocalContext.current.applicationContext
    val selectedItem = remember { //will use it to store the selected item and change the icon color when selected
        mutableStateOf(Icons.Default.Home) //default selected icon
    }

    Scaffold(bottomBar = {
        BottomAppBar(
            containerColor = Green1,
            modifier = Modifier.fillMaxWidth(),
            content  = {
                //Home icon
                IconButton(onClick = {
                    selectedItem.value = Icons.Default.Home
                    navigationController.navigate(Screens.Home_Bottom.screen_name){
                        popUpTo(id = 0)
                    }
                },
                    modifier = Modifier.weight(1f)
                ) {
                    Icon(imageVector = Icons.Default.Home,
                        contentDescription = "Home",
                        tint = if (selectedItem.value == Icons.Default.Home) Color.White else Color.DarkGray,
                        modifier = Modifier.size(30.dp)
                    )
                }

                //Search icon
                IconButton(onClick = {
                    selectedItem.value = Icons.Default.Search
                    navigationController.navigate(Screens.Search_Bottom.screen_name){
                        popUpTo(id = 0)
                    }
                },
                    modifier = Modifier.weight(1f)
                ) {
                    Icon(imageVector = Icons.Default.Search,
                        contentDescription = "Search",
                        tint = if (selectedItem.value == Icons.Default.Search) Color.White else Color.DarkGray,
                        modifier = Modifier.size(30.dp)
                    )
                }

                //Floating action button
                Box(modifier = Modifier
                    .weight(1f)
                    .padding(16.dp),
                    contentAlignment = Alignment.Center
                ){
                    FloatingActionButton(onClick = {
                        Toast.makeText(context, "Open Bottom sheet", Toast.LENGTH_SHORT).show()
                    }
                    ) {
                        Icon(imageVector = Icons.Default.Add, contentDescription = "Add", tint = Green1)
                    }
                }

                //Notifications icon
                IconButton(onClick = {
                    selectedItem.value = Icons.Default.Notifications
                    navigationController.navigate(Screens.Notifications_Bottom.screen_name){
                        popUpTo(id = 0)
                    }
                },
                    modifier = Modifier.weight(1f)
                ) {
                    Icon(imageVector = Icons.Default.Notifications,
                        contentDescription = "Notifications",
                        tint = if (selectedItem.value == Icons.Default.Notifications) Color.White else Color.DarkGray,
                        modifier = Modifier.size(30.dp)
                    )
                }

                //Profile icon
                IconButton(onClick = {
                    selectedItem.value = Icons.Default.Person
                    navigationController.navigate(Screens.Profile_Bottom.screen_name){
                        popUpTo(id = 0)
                    }
                },
                    modifier = Modifier.weight(1f)
                ) {
                    Icon(imageVector = Icons.Default.Person,
                        contentDescription = "Profile",
                        tint = if (selectedItem.value == Icons.Default.Person) Color.White else Color.DarkGray,
                        modifier = Modifier.size(30.dp)
                    )
                }


            }
        )
    }) {
        NavHost(navController = navigationController, startDestination = Screens.Home_Bottom.screen_name) {
            composable(Screens.Home_Bottom.screen_name){ home_bottom()}
            composable(Screens.Search_Bottom.screen_name){ search_bottom()}
            composable(Screens.Notifications_Bottom.screen_name){ notification_bottom()}
            composable(Screens.Profile_Bottom.screen_name){ profile_bottom()}
        }
    }
}