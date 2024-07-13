package com.example.ekopay

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
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
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ekopay.ui.theme.Black1
import com.example.ekopay.ui.theme.Green1
import com.example.ekopay.ui.theme.White1


@Composable @Preview(showBackground = true, showSystemUi = true)
fun LearningScreen() {

        Column(
            modifier = Modifier
                .padding(16.dp)
        ) {
            Text(
                text = "Learn more",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(8.dp))
            SearchBar(
                placeholder = "ask questions",
                initialQuery = "",
                onQueryChange = { newQuery ->
                    println("ask question: $newQuery")
                }
            )
            Spacer(modifier = Modifier.height(16.dp))
            FAQ()
            Spacer(modifier = Modifier.height(16.dp))
            VideoTutorials()
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
            .background(Black1,
                shape = RoundedCornerShape(12.dp))
            .padding(16.dp)
            .clickable { expanded = !expanded }
    ) {
        Text(
            text = question,
            color = Color.White,
            style = MaterialTheme.typography.bodyLarge,
            )
        AnimatedVisibility(visible = expanded) {
            Text(
                text = answer,
                color = Color.White,
                fontSize = 16.sp,
                modifier = Modifier.padding(top = 8.dp)
            )
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

            items(tutorialTitles) { title ->
                VideoTutorialItem(title)
            }
        }
    }
}

@Composable
fun VideoTutorialItem(title: String) {
    Column(
        modifier = Modifier
            .width(140.dp)
            .height(200.dp)
            .clip(RoundedCornerShape(12.dp))
            .background(Green1)
            .padding(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Box(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth(),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                ImageVector.vectorResource(id = R.drawable.baseline_play_arrow_24),
                contentDescription = "Play",
                tint = White1,
                modifier = Modifier.size(54.dp)
            )
        }
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = title,
            color = Color.Black,
            style = MaterialTheme.typography.bodyMedium,
            maxLines = 2
        )
    }
}
