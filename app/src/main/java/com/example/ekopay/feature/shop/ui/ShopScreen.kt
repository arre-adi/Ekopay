package com.example.ekopay.feature.shop.ui

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.ekopay.R
import com.example.ekopay.ui.theme.Green1


@Composable
fun ShoppingScreen(navController: NavHostController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = "Marketplace",
            style = MaterialTheme.typography.headlineMedium
        )
        Spacer(modifier = Modifier.height(16.dp))
        ProductGrid(navController)
    }
}

@Composable
fun ProductGrid(navController: NavHostController) {
    val products = listOf(
        Product("Organic Soap", "Cosmetic", 50, 0.2, R.drawable.img_product_soap),
        Product("Apples", "Homegrown", 200, 0.5, R.drawable.img_product_apple),
        Product("Candles", "Home Decor", 100, 0.1, R.drawable.img_product_candle),
        Product("Organic Soap", "Cosmetic", 45, 0.3, R.drawable.img_product_soap),
        Product("Apples", "Organic", 80, 0.4, R.drawable.img_product_apple)
    )

    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        contentPadding = PaddingValues(8.dp)
    ) {
        items(products) { product ->
            ProductCard(product, navController)
        }
    }
}

@Composable
fun ProductCard(product: Product, navController: NavHostController) {
    Card(
        modifier = Modifier
            .padding(8.dp)
            .clickable {
                navController.navigate("productCardFinal")
            }
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
                    Text(text = "₹${product.price}", fontWeight = FontWeight.Bold)
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
    val greenCredits: Double,
    val imageRes: Int
)