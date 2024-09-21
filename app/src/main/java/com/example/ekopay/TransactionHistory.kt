package com.example.ekopay

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ekopay.ui.theme.Black1
import com.example.ekopay.ui.theme.Green1

@Composable
@Preview(showBackground = true, showSystemUi = true)
fun TransactionHistoryScreen() {
    var selectedTab by remember { mutableStateOf("RECENT") }
    var searchQuery by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        Text(
            text = "HISTORY",
            modifier = Modifier.padding(16.dp),
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold
        )

        TotalSavingsCard()

        SearchBar(
            placeholder = "Search transactions",
            initialQuery = searchQuery,
            onQueryChange = { newQuery ->
                searchQuery = newQuery
                // Handle the search query change
                println("Transaction search query: $newQuery")
            }
        )

        TransactionTabs(
            selectedTab = selectedTab,
            onTabSelected = { newTab ->
                selectedTab = newTab
            }
        )

        TransactionList(tabType = selectedTab, searchQuery = searchQuery)
    }
}

@Composable
fun TotalSavingsCard() {
    Card(
        modifier = Modifier
            .height(150.dp)
            .fillMaxWidth()
            .padding(16.dp),
        shape = RoundedCornerShape(8.dp),

    ) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .fillMaxSize()
                .background(
                    Black1,
                    shape = RoundedCornerShape(12.dp)
                )
        ){
            Image(
                painter = painterResource(id = R.drawable.frame_3),
                contentDescription = "Green Credit Card Background",
                contentScale = ContentScale.FillBounds,
                modifier = Modifier.fillMaxWidth()
            )
        Row(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text("TOTAL SAVINGS", color = Color.White)
                Text("₹5000", color = Green1, fontSize = 24.sp, fontWeight = FontWeight.Bold)
            }
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text("GREEN CREDITS", color = Color.White)
                Text("15 GC", color = Green1, fontSize = 24.sp, fontWeight = FontWeight.Bold)
            }
        }
    }
}
    }

@Composable
fun SearchBar(
    placeholder: String = "Search transactions",
    initialQuery: String = "",
    onQueryChange: (String) -> Unit = {}
) {
    var searchQuery by remember { mutableStateOf(TextFieldValue(initialQuery)) }



        TextField(
            leadingIcon = {
                IconButton(onClick = {/*TODO*/}){
                    Icon(
                        imageVector = Icons.Default.Search,
                        contentDescription = "Search"
                    )
                }
            },
            value = searchQuery,
            onValueChange = { newQuery ->
                searchQuery = newQuery
                onQueryChange(newQuery.text)
            },
            placeholder = {
                Text(
                    text = placeholder,
                    color = Color.Gray,
                    fontSize = 16.sp
                )
            },
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Search
            ),
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .clip(RoundedCornerShape(12.dp))
                .background(Color(0xFFF1F1F1)),
            colors = TextFieldDefaults.colors(
                disabledContainerColor = Color.Transparent,
                focusedContainerColor = Color.Transparent,
                unfocusedContainerColor = Color.Transparent,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent
            )
        )
    }

@Composable
fun TransactionTabs(
    selectedTab: String,
    onTabSelected: (String) -> Unit
) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
            .background(
                color = Black1,
                shape = RoundedCornerShape(6.dp)
            )
    ) {
        TabButton("RECENT", selectedTab == "RECENT") { onTabSelected("RECENT") }
        TabButton("DEBIT", selectedTab == "DEBIT") { onTabSelected("DEBIT") }
        TabButton("CREDIT", selectedTab == "CREDIT") { onTabSelected("CREDIT") }
    }
}


@Composable
fun TabButton(text: String, isSelected: Boolean, onClick: () -> Unit) {
    Button(
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(
            containerColor = if (isSelected) Green1 else Black1,
            contentColor = Color.White
        ),
        modifier = Modifier.height(36.dp),
        shape = RoundedCornerShape(6.dp)
    ) {
        Text(text)
    }
}

@Composable
fun TransactionList(tabType: String, searchQuery: String) {
    val allTransactions = listOf(
        Transaction("METRO", "JUN 15, 2024 7:10 AM", -16, 0.022),
        Transaction("Sisiphus", "JUN 15, 2024 8:30 AM", 256, 0.00),
        Transaction("Atmos", "JUN 15, 2024 12:8 PM", -699, 0.2),
        Transaction("Bamboo Studio", "JUN 14, 2024 10:12 PM", -146, 0.5),
        Transaction("METRO", "JUN 14, 2024 8:18 PM", -2616, 0.8)
    )

    val filteredTransactions = allTransactions
        .filter {
            when (tabType) {
                "DEBIT" -> it.amount < 0
                "CREDIT" -> it.amount > 0
                else -> true
            }
        }
        .filter {
            it.name.contains(searchQuery, ignoreCase = true) ||
                    it.date.contains(searchQuery, ignoreCase = true)
        }

    LazyColumn {
        item { Text("Today", modifier = Modifier.padding(16.dp)) }
        items(filteredTransactions.filter { it.date.contains("JUN 15") }) { transaction ->
            TransactionItem(transaction)
        }
        item { Text("Yesterday", modifier = Modifier.padding(16.dp)) }
        items(filteredTransactions.filter { it.date.contains("JUN 14") }) { transaction ->
            TransactionItem(transaction)
        }
    }
}


@Composable
fun TransactionItem(transaction: Transaction) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .size(40.dp)
                .background(
                    if (transaction.amount < 0) Green1 else Black1,
                    RoundedCornerShape(8.dp)
                )
        )
        Column(modifier = Modifier
            .weight(1f)
            .padding(start = 16.dp)) {
            Text(transaction.name, fontWeight = FontWeight.Bold)
            Text(transaction.date, fontSize = 12.sp, color = Color.Gray)
        }
        Column(horizontalAlignment = Alignment.End) {
            Text(
                if (transaction.amount > 0) "+ ₹${transaction.amount}" else "- ₹${-transaction.amount}",
                color = if (transaction.amount > 0) Black1 else Color.Red
            )
            Text("${transaction.greenCredits} GC", fontSize = 12.sp, color = Green1)
        }
    }
}

data class Transaction(
    val name: String,
    val date: String,
    val amount: Int,
    val greenCredits: Double
)