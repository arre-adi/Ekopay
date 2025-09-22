package com.example.ekopay.feature.onboarding.ui

import androidx.compose.animation.*
import androidx.compose.animation.core.*
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.pager.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.launch
import com.example.ekopay.R

// Data class for onboarding pages
data class OnboardingPage(
    val imageRes: Int,
    val title: String,
    val description: String,
    val contentDescription: String
)

// Main onboarding flow composable
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun OnboardingFlow(
    onComplete: () -> Unit,
    onSkip: () -> Unit,
    modifier: Modifier = Modifier
) {
    val pages = remember {
        listOf(
            OnboardingPage(
                imageRes = R.drawable.group_1737,
                title = "Trade Green\nSave Green",
                description = "Earn rewards by trading green credits and enjoy discounts on eco-friendly products.",
                contentDescription = "Illustration showing green trading concept"
            ),
            OnboardingPage(
                imageRes = R.drawable.frame_4057,
                title = "Track Trade\nTransform",
                description = "Manage your carbon footprint, trade credits, and unlock savings on sustainable goods.",
                contentDescription = "Illustration showing carbon tracking and transformation"
            ),
            OnboardingPage(
                imageRes = R.drawable.invest,
                title = "Eco-Friendly\nInvestments,\nReal Rewards",
                description = "Align your investments with sustainability and get exclusive discounts on green products.",
                contentDescription = "Illustration showing eco-friendly investment concept"
            )
        )
    }

    val pagerState = rememberPagerState(
        initialPage = 0,
        pageCount = { pages.size }
    )
    val coroutineScope = rememberCoroutineScope()

    Box(
        modifier = modifier
            .fillMaxSize()
            .semantics { contentDescription = "Onboarding flow" }
    ) {
        // Skip button
        AnimatedVisibility(
            visible = pagerState.currentPage < pages.size - 1,
            enter = fadeIn(),
            exit = fadeOut(),
            modifier = Modifier
                .align(Alignment.TopEnd)
                .padding(16.dp)
        ) {
            TextButton(
                onClick = onSkip,
                modifier = Modifier.semantics {
                    contentDescription = "Skip onboarding"
                }
            ) {
                Text(
                    text = "Skip",
                    color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f),
                    fontSize = 16.sp
                )
            }
        }

        // Main content
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            // Pager content
            HorizontalPager(
                state = pagerState,
                modifier = Modifier.weight(1f)
            ) { page ->
                OnboardingPageContent(
                    page = pages[page],
                    modifier = Modifier.fillMaxSize()
                )
            }

            // Bottom section with indicators and navigation
            OnboardingBottomSection(
                pagerState = pagerState,
                pages = pages,
                onNextClick = {
                    if (pagerState.currentPage < pages.size - 1) {
                        coroutineScope.launch {
                            pagerState.animateScrollToPage(pagerState.currentPage + 1)
                        }
                    } else {
                        onComplete()
                    }
                },
                onBackClick = {
                    if (pagerState.currentPage > 0) {
                        coroutineScope.launch {
                            pagerState.animateScrollToPage(pagerState.currentPage - 1)
                        }
                    }
                },
                modifier = Modifier.padding(16.dp)
            )
        }
    }
}

@Composable
private fun OnboardingPageContent(
    page: OnboardingPage,
    modifier: Modifier = Modifier
) {
    val configuration = LocalConfiguration.current
    val screenHeight = configuration.screenHeightDp.dp
    val isTablet = configuration.screenWidthDp >= 600

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 24.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Animated image with responsive sizing
        val imageSize = if (isTablet) 400.dp else minOf(screenHeight * 0.35f, 300.dp)

        AnimatedVisibility(
            visible = true,
            enter = slideInVertically(
                initialOffsetY = { -it / 2 },
                animationSpec = tween(600, easing = EaseOutCubic)
            ) + fadeIn(animationSpec = tween(600))
        ) {
            Image(
                painter = painterResource(id = page.imageRes),
                contentDescription = page.contentDescription,
                modifier = Modifier
                    .size(imageSize)
                    .clip(RoundedCornerShape(16.dp)),
                contentScale = ContentScale.Fit
            )
        }

        Spacer(modifier = Modifier.height(if (isTablet) 48.dp else 32.dp))

        // Animated title
        AnimatedVisibility(
            visible = true,
            enter = slideInVertically(
                initialOffsetY = { it / 2 },
                animationSpec = tween(600, delayMillis = 200, easing = EaseOutCubic)
            ) + fadeIn(animationSpec = tween(600, delayMillis = 200))
        ) {
            Text(
                text = page.title,
                style = MaterialTheme.typography.headlineMedium.copy(
                    fontSize = if (isTablet) 32.sp else 28.sp,
                    lineHeight = if (isTablet) 40.sp else 36.sp
                ),
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onSurface,
                textAlign = TextAlign.Center,
                modifier = Modifier.semantics {
                    contentDescription = "Title: ${page.title.replace("\n", " ")}"
                }
            )
        }

        Spacer(modifier = Modifier.height(if (isTablet) 24.dp else 16.dp))

        // Animated description
        AnimatedVisibility(
            visible = true,
            enter = slideInVertically(
                initialOffsetY = { it / 2 },
                animationSpec = tween(600, delayMillis = 400, easing = EaseOutCubic)
            ) + fadeIn(animationSpec = tween(600, delayMillis = 400))
        ) {
            Text(
                text = page.description,
                style = MaterialTheme.typography.bodyLarge.copy(
                    fontSize = if (isTablet) 18.sp else 16.sp,
                    lineHeight = if (isTablet) 26.sp else 24.sp
                ),
                fontWeight = FontWeight.Normal,
                color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.8f),
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .padding(horizontal = if (isTablet) 32.dp else 16.dp)
                    .semantics { contentDescription = "Description: ${page.description}" }
            )
        }
    }
}

@OptIn(ExperimentalFoundationApi::class, ExperimentalAnimationApi::class)
@Composable
private fun OnboardingBottomSection(
    pagerState: PagerState,
    pages: List<OnboardingPage>,
    onNextClick: () -> Unit,
    onBackClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Page indicators
        PageIndicator(
            pageCount = pages.size,
            currentPage = pagerState.currentPage,
            modifier = Modifier.padding(bottom = 24.dp)
        )

        // Navigation buttons
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Back button
            AnimatedVisibility(
                visible = pagerState.currentPage > 0,
                enter = slideInHorizontally { -it } + fadeIn(),
                exit = slideOutHorizontally { -it } + fadeOut()
            ) {
                OutlinedButton(
                    onClick = onBackClick,
                    modifier = Modifier
                        .height(56.dp)
                        .widthIn(min = 100.dp)
                        .semantics { contentDescription = "Go back to previous page" },
                    shape = RoundedCornerShape(16.dp),
                    border = BorderStroke(
                        1.dp,
                        MaterialTheme.colorScheme.outline.copy(alpha = 0.5f)
                    )
                ) {
                    Text(
                        text = "Back",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Medium
                    )
                }
            }

            // Next/Get Started button
            Button(
                onClick = onNextClick,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp)
                    .weight(1f)
                    .semantics {
                        contentDescription = if (pagerState.currentPage == pages.size - 1)
                            "Get started with the app"
                        else
                            "Continue to next page"
                    },
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    contentColor = MaterialTheme.colorScheme.onPrimary
                ),
                shape = RoundedCornerShape(16.dp),
                elevation = ButtonDefaults.buttonElevation(
                    defaultElevation = 2.dp,
                    pressedElevation = 8.dp
                )
            ) {
                AnimatedContent(
                    targetState = pagerState.currentPage == pages.size - 1,
                    transitionSpec = {
                        slideInHorizontally { if (targetState) it else -it } + fadeIn() with
                                slideOutHorizontally { if (targetState) -it else it } + fadeOut()
                    },
                    label = "Button text animation"
                ) { isLastPage ->
                    Text(
                        text = if (isLastPage) "Get Started" else "Next",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.SemiBold
                    )
                }
            }
        }
    }
}

@Composable
private fun PageIndicator(
    pageCount: Int,
    currentPage: Int,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        repeat(pageCount) { index ->
            val isSelected = index == currentPage
            val animatedWidth by animateDpAsState(
                targetValue = if (isSelected) 24.dp else 8.dp,
                animationSpec = tween(300, easing = EaseInOutCubic),
                label = "Indicator width"
            )
            val animatedColor by animateColorAsState(
                targetValue = if (isSelected)
                    MaterialTheme.colorScheme.primary
                else
                    MaterialTheme.colorScheme.outline.copy(alpha = 0.3f),
                animationSpec = tween(300),
                label = "Indicator color"
            )

            Box(
                modifier = Modifier
                    .height(8.dp)
                    .width(animatedWidth)
                    .clip(CircleShape)
                    .background(animatedColor)
                    .semantics {
                        contentDescription = "Page ${index + 1} of $pageCount${if (isSelected) ", current page" else ""}"
                    }
            )
        }
    }
}

// Usage example
@Composable
fun OnboardingExample() {
    OnboardingFlow(
        onComplete = {
            // Handle onboarding completion
            // e.g., navigate to main screen, save preference
        },
        onSkip = {
            // Handle skip action
            // e.g., navigate to main screen, track analytics
        }
    )
}