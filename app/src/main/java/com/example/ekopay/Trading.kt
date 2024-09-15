package com.example.ekopay

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import co.yml.charts.axis.AxisData
import androidx.compose.ui.Modifier
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import co.yml.charts.common.model.Point
import co.yml.charts.ui.linechart.LineChart
import co.yml.charts.ui.linechart.model.*
import com.example.ekopay.ui.theme.Black1
import com.example.ekopay.ui.theme.Green1
import com.example.ekopay.ui.theme.Grey2
import com.example.ekopay.ui.theme.White1


@Composable
fun GreenGradientLineChart(data: List<Pair<String, Int>>) {
    if (data.isEmpty()) {

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(300.dp),
            contentAlignment = Alignment.Center
        ) {
            Text("No data available")
        }
        return
    }

    val pointsData = data.mapIndexed { index, (_, value) ->
        Point(index.toFloat(), value.toFloat())
    }

    val xAxisData = AxisData.Builder()
        .axisStepSize(80.dp)
        .steps(data.size - 1)
        .labelData { i -> if (i < data.size) data[i].first else "" }
        .labelAndAxisLinePadding(15.dp)
        .axisLineColor(White1)
        .axisLabelColor(Green1)
        .build()

    val yAxisData = AxisData.Builder()
        .steps(5)
        .labelAndAxisLinePadding(20.dp)
        .labelData { i ->
            val max = data.maxOf { it.second }
            ((i * (max / 5))).toString()
        }
        .axisLineColor(White1)
        .axisLabelColor(White1)
        .build()

    val lineChartData = LineChartData(
        linePlotData = LinePlotData(
            lines = listOf(
                Line(
                    dataPoints = pointsData,
                    LineStyle(
                        color = Green1,
                        lineType = LineType.SmoothCurve()
                    ),
                    IntersectionPoint(color = Color.Transparent),
                    SelectionHighlightPoint(color = Color.Transparent),
                    ShadowUnderLine(
                        brush = Brush.verticalGradient(
                            listOf(
                                Green1.copy(alpha = 0.4f),
                               White1
                            )
                        ),
                        alpha = 0.5f
                    ),
                    SelectionHighlightPopUp()
                )
            )
        ),
        xAxisData = xAxisData,
        yAxisData = yAxisData,
        gridLines = GridLines(color = White1)
    )

    LineChart(
        modifier = Modifier
            .fillMaxWidth()
            .height(300.dp),
        lineChartData = lineChartData
    )
}

@Composable
fun TradingScreen(navController: NavController) {
    var amount by remember { mutableStateOf("") }
    val calculatedAmount = amount.toIntOrNull()?.times(4695) ?: 0

    val csvData = remember {
        """
        May,5000
        Jun,6942
        Jul,8691
        Aug,3900
        Sep,7408
        """.trimIndent()
    }

    val chartData = csvData.lines()
        .filter { it.isNotBlank() }
        .mapNotNull { line ->
            try {
                val (month, value) = line.split(",")
                month.trim() to value.trim().toInt()
            } catch (e: Exception) {
                null
            }
        }
    LazyColumn(

        modifier = Modifier
            .background(White1)
            .fillMaxSize()
            .padding(16.dp)
    ) {
        item {
            Text(
                text = "Trade",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold
            )
        }

        item {
            Spacer(
                modifier = Modifier
                    .padding( vertical = 10.dp)
            )
        }
        item{
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
                        Text("GREEN CREDITS", color = Color.White)
                        Text("15 GC", color = Green1, fontSize = 24.sp, fontWeight = FontWeight.Bold)
                    }
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Text("EARNINGS", color = Color.White)
                        Text("₹70425",color = Green1, fontSize = 24.sp, fontWeight = FontWeight.Bold)
                    }
                }
            }
        }

        item {
            Spacer(
                modifier = Modifier
                    .padding( vertical = 20.dp)
            )
        }
        item {
            GreenGradientLineChart(data = chartData)
        }

        item{
            Row(horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .background(
                        color = Green1,
                        shape = RoundedCornerShape(12.dp)
                    )

                    .padding(12.dp)

                    )    {
                Text(text = "1 GC",color = Black1, fontSize = 24.sp, fontWeight = FontWeight.Bold )
                Text(text = "₹4695",color = White1, fontSize = 24.sp, fontWeight = FontWeight.Bold)
            }
        }

        item {
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                OutlinedTextField(
                    value = amount,
                    onValueChange = { amount = it },
                    label = { Text("Sell Green Credits") },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 16.dp)
                        .clip(RoundedCornerShape(12.dp))
                        .background(Color(0xFFF1F1F1)),
                    colors = TextFieldDefaults.colors(
                        disabledContainerColor = Color.Transparent,
                        focusedContainerColor = Color.Transparent,
                        unfocusedContainerColor = Color.Transparent,
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent
                    ),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    trailingIcon = {
                        Text(
                            text = "₹$calculatedAmount ",
                            color = Green1,
                            fontSize = 24.sp,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.padding(end = 8.dp)
                        )
                    }
                )
            }
        }
        item {

            Button(
                onClick = {
                    if (amount.isNotEmpty()) {
                        navController.navigate("finalTrading/$amount")
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
                    .height(50.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Black1,
                    contentColor = Color.White
                ),
                shape = RoundedCornerShape(12.dp)
            ) {
                Text("Next")
            }
        }
            }
        }



@Composable
fun FinalTradingScreen(navController: NavController, amount: String) {
    val calculatedAmount = amount.toIntOrNull()?.times(4695) ?: 0

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        Spacer(modifier = Modifier.padding(100.dp))
        Text(
            text = "Amount: $amount GC",
            style = MaterialTheme.typography.bodyLarge
        )
        Text(
            text = "Trading",
            fontSize = 50.sp,
            fontWeight = FontWeight(600)
        )
        Text(
            text = "Complete",
            fontSize = 50.sp,
            fontWeight = FontWeight(600)
        )
        Text(
            text = "₹$calculatedAmount earned",
            color = Green1,
            style = MaterialTheme.typography.headlineSmall,
            fontWeight = FontWeight(600)
        )
    }
}