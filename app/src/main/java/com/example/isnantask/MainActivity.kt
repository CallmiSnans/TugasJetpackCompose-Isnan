package com.example.isnantask

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            isnantaskapp()
        }
    }
}

@Preview
@Composable
fun isnantaskapp() {
    val navController = rememberNavController()

    Box(modifier = Modifier.fillMaxSize()) {
        isnantaskAppScreen()

        // Add blurred background
        Box(
            modifier = Modifier
                .fillMaxSize()
                .graphicsLayer {
                    alpha = 0.4f // Adding transparency for the blur effect
                }
                .background(Brush.verticalGradient(listOf(Color.Gray.copy(alpha = 0.5f), Color.Transparent)))
        )

        // Bottom Navigation Bar
        Box(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(16.dp)
                .background(Color.White.copy(alpha = 0.5f), shape = RoundedCornerShape(24.dp))
        ) {
            BottomNavigationBar(navController = navController)
        }
    }
}

@Composable
fun isnantaskAppScreen() {
    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF1C1C1C))
            .padding(16.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp)
                .clip(RoundedCornerShape(50))
                .background(Color(0xFFFFA500))
                .clickable {

                }
                .padding(16.dp)
        ) {
            Text(
                "List Film",
                color = Color.White,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.align(Alignment.Center)
            )
        }

        Text(
            text = "Film Populer",
            fontSize = 18.sp,
            fontWeight = FontWeight.SemiBold,
            color = Color.White,
            modifier = Modifier.padding(bottom = 8.dp)
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .horizontalScroll(rememberScrollState())
                .padding(bottom = 16.dp)
        ) {
            listOf(
                R.drawable.avengers, R.drawable.blade_runner_2049,
                R.drawable.darkknight, R.drawable.fight_club,
                R.drawable.godfather, R.drawable.gladiator,
                R.drawable.goodfellas, R.drawable.green_mile,
                R.drawable.inception, R.drawable.inglorious_basterds
            ).forEach { image ->
                Image(
                    painter = painterResource(id = image),
                    contentDescription = null,
                    modifier = Modifier
                        .size(100.dp)
                        .padding(end = 8.dp)
                        .clickable {
                            val intent = Intent(context, DetailMovieActivity::class.java)
                            context.startActivity(intent)
                        }
                )
            }
        }


        Text(
            text = "Film Rekomendasi",
            fontSize = 18.sp,
            fontWeight = FontWeight.SemiBold,
            color = Color.White,
            modifier = Modifier.padding(bottom = 8.dp)
        )
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp)
        ) {
            items(10) { index ->
                val images = listOf(
                    R.drawable.interstellar, R.drawable.joker,
                    R.drawable.jurassic_park, R.drawable.lion_king,
                    R.drawable.parasite, R.drawable.prestige,
                    R.drawable.saving_private_ryan, R.drawable.se7en,
                    R.drawable.shawshank_redemption, R.drawable.spirited_away
                )
                val image = images[index]
                Image(
                    painter = painterResource(id = image),
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(150.dp)
                        .padding(4.dp)
                        .clickable {
                            val intent = Intent(context, DetailMovieActivity::class.java)
                            context.startActivity(intent)
                        }
                )
            }
        }

        Box(
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(top = 16.dp)
                .clip(RoundedCornerShape(50))
                .background(Color(0xFFFFA500))
                .clickable {

                }
                .padding(16.dp)
        ) {
            Text(
                "?",
                color = Color.Black,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.align(Alignment.Center)
            )
        }
    }
}

@Composable
fun BottomNavigationBar(navController: NavController) {
    val context = LocalContext.current

    NavigationBar(
        containerColor = Color(0xFF1C1C1C).copy(alpha = 0.5f), // Dark gray with transparency
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
    ) {
        NavigationBarItem(
            icon = {
                Icon(
                    imageVector = Icons.Default.Person,
                    contentDescription = "About",
                    tint = Color(0xFFFFA500), // Orange color for the profile icon
                    modifier = Modifier.size(40.dp) // Increased icon size
                )
            },
            label = {
                Text(
                    text = "Profile",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = Color(0xFFFFA500) // Orange color for the profile label
                )
            },
            selected = false, // Adjust based on the active route if necessary
            onClick = {
                // Start ProfileActivity
                context.startActivity(Intent(context, About::class.java))
            }
        )
    }
}


@Preview
@Composable
fun PreviewisnantaskApp() {
    isnantaskapp()
}
