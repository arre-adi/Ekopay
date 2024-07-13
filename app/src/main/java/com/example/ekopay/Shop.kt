package com.example.ekopay

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ekopay.ui.theme.Green1

@Composable @Preview(showBackground = true, showSystemUi = true)
fun ShoppingScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        Text(
            text = "Marketplace",
            modifier = Modifier.padding(16.dp),
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold
        )
        SearchBar(
            placeholder = "Search items",
            initialQuery = "",
            onQueryChange = { newQuery ->
                println("Search query: $newQuery")
            }
        )
        ProductGrid()
    }
}

@Composable
fun ProductGrid() {
    val products = listOf(
        Product("Organic Soap", "Cosmetic", 5, 2, R.drawable.img_soap),
        Product("Apples", "Homegrown", 20, 5, R.drawable.imgapple),
        Product("Candles", "Home Decor", 10, 1, R.drawable.img_candle4),
        Product("Organic Soap", "Cosmetic", 5, 2, R.drawable.img_soap),
        Product("Apples", "Homegrown", 20, 5, R.drawable.imgapple)
    )

    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        contentPadding = PaddingValues(8.dp)
    ) {
        items(products) { product ->
            ProductCard(product)
        }
    }
}

@Composable
fun ProductCard(product: Product) {
    Card(
        modifier = Modifier
            .padding(8.dp)
            .shadow(2.dp, RoundedCornerShape(8.dp))
            .fillMaxWidth(),
        shape = RoundedCornerShape(8.dp)
    ) {
        Column (
            Modifier.background(Color.White)
        ){
            Image(
                painter = painterResource(id = product.imageRes),
                contentDescription = product.name,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(150.dp)
                    .clip(RoundedCornerShape(topStart = 8.dp, topEnd = 8.dp)),
                contentScale = ContentScale.Crop
            )
            Column(modifier = Modifier.padding(8.dp)) {
                Text(text = product.name, fontWeight = FontWeight.Bold)
                Text(text = product.category, color = Color.Gray)
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(text = "$${product.price}", fontWeight = FontWeight.Bold)
                    Text(text = "${product.greenCredits}GC", color = Green1)
                }
            }
        }
    }
}




data class Product(
    val name: String,
    val category: String,
    val price: Int,
    val greenCredits: Int,
    val imageRes: Int
)