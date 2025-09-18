package com.example.ekopay.feature.learning.ui

import GeminiTextField
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@Composable
@Preview(showBackground = true, showSystemUi = true)
fun LearningScreen() {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        item {
            Text(
                text = "Learn more",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold
            )
        }

        item{
            GeminiTextField()
        }

        item {
            Spacer(modifier = Modifier.height(16.dp))
        }

        item {
            FAQ()
        }

        item {
            Spacer(modifier = Modifier.height(16.dp))
        }

        item {
            VideoTutorials()
        }
    }
}








@Composable
fun FAQ() {
    Column(
        modifier = Modifier
            .wrapContentSize()
            .padding(16.dp)
    ) {
        FAQItem(question = "What is green credit?", answer = "Green credit refers to credits that are given to individuals or organizations for engaging in environmentally friendly practices.")
        Spacer(modifier = Modifier.height(8.dp))
        FAQItem(question = "How can I earn green credits?", answer = "You can earn green credits by participating in various eco-friendly activities, such as recycling, using renewable energy sources, and reducing carbon footprints.")
        Spacer(modifier = Modifier.height(8.dp))
        Button(
            onClick = { /* Handle more action */ },
            shape = RoundedCornerShape(12.dp),
            colors = ButtonDefaults.buttonColors(  (Color(0xFFF1F1F1))
        ),
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Text("more",
                color = Color.Gray,)
        }
    }
}

@Composable
fun FAQItem(question: String, answer: String) {
    var expanded by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .clip(
                    RoundedCornerShape(
                        topStart = 12.dp,
                        topEnd = 12.dp,
                        bottomStart = if (expanded) 0.dp else 12.dp,
                        bottomEnd = if (expanded) 0.dp else 12.dp
                    )
                )
                .background(Color.Black)
                .clickable { expanded = !expanded }
                .padding(16.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = question,
                    color = Color.White,
                    fontSize = 16.sp
                )
                Icon(
                    imageVector = if (expanded) Icons.Filled.KeyboardArrowUp else Icons.Filled.KeyboardArrowDown,
                    contentDescription = if (expanded) "Collapse" else "Expand",
                    tint = Color.White
                )
            }
        }

        AnimatedVisibility(visible = expanded) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(
                        color = Color.White,
                        shape = RoundedCornerShape(bottomStart = 12.dp, bottomEnd = 12.dp)
                    )
                    .padding(16.dp)
            ) {
                Text(
                    text = answer,
                    color = Color.Black,
                    fontSize = 14.sp
                )
            }
        }
    }
}

@Composable
fun VideoTutorials() {
    Column {
        Text("Video Tutorials", fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.height(8.dp))
        LazyRow(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            val tutorialTitles = listOf(
                "How to submit an eco brick?",
                "10 budget friendly green alternatives",
                "How to sub eco brick?",
                "Eco-friendly home improvements",
                "Recycling tips and tricks"
            )

//            items(tutorialTitles) { title ->
//                VideoTutorialItem(title)
//            }
        }
    }
}

//@Composable
//fun VideoTutorialItem(title: String) {
//    Column(
//        modifier = Modifier
//            .width(140.dp)
//            .height(200.dp)
//            .clip(RoundedCornerShape(12.dp))
//            .background(Green1)
//            .padding(8.dp),
//        horizontalAlignment = Alignment.CenterHorizontally,
//        verticalArrangement = Arrangement.SpaceBetween
//    ) {
//        Box(
//            modifier = Modifier
//                .weight(1f)
//                .fillMaxWidth(),
//            contentAlignment = Alignment.Center
//        ) {
//            Icon(
//                ImageVector.vectorResource(id = R.drawable.baseline_play_arrow_24),
//                contentDescription = "Play",
//                tint = White1,
//                modifier = Modifier.size(54.dp)
//            )
//        }
//        Spacer(modifier = Modifier.height(4.dp))
//        Text(
//            text = title,
//            color = Color.Black,
//            style = MaterialTheme.typography.bodyMedium,
//            maxLines = 2
//        )
//    }
//}
//
//
