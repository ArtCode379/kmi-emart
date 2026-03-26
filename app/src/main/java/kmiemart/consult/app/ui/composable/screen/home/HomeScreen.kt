package kmiemart.consult.app.ui.composable.screen.home

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Build
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Cloud
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import kmiemart.consult.app.R
import kmiemart.consult.app.data.model.ServiceModel
import kmiemart.consult.app.ui.composable.shared.KMSTMContentWrapper
import kmiemart.consult.app.ui.composable.shared.KMSTMEmptyView
import kmiemart.consult.app.ui.state.DataUiState
import kmiemart.consult.app.ui.theme.AccentBorder
import kmiemart.consult.app.ui.theme.GradientEnd
import kmiemart.consult.app.ui.theme.GradientStart
import kmiemart.consult.app.ui.theme.MutedText
import kmiemart.consult.app.ui.theme.OnSurface
import kmiemart.consult.app.ui.theme.Primary
import kmiemart.consult.app.ui.theme.Surface
import kmiemart.consult.app.ui.viewmodel.ServiceViewModel
import org.koin.androidx.compose.koinViewModel

private data class HeroBanner(
    val title: String,
    val subtitle: String,
    val imageRes: Int,
)

private val heroBanners = listOf(
    HeroBanner("Cybersecurity Consulting", "Protect your digital assets", R.drawable.hero1),
    HeroBanner("Cloud Solutions", "Scale your infrastructure", R.drawable.hero2),
    HeroBanner("Business Optimization", "Streamline your operations", R.drawable.hero3),
)

private data class ServiceCategory(
    val name: String,
    val icon: ImageVector,
)

private val categories = listOf(
    ServiceCategory("Cybersecurity", Icons.Filled.Lock),
    ServiceCategory("Cloud", Icons.Filled.Cloud),
    ServiceCategory("Optimization", Icons.Filled.Build),
    ServiceCategory("Strategy", Icons.Filled.Star),
    ServiceCategory("Audit", Icons.Filled.CheckCircle),
    ServiceCategory("Analytics", Icons.Filled.Search),
)

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    viewModel: ServiceViewModel = koinViewModel(),
    onNavigateToServiceDetails: (serviceId: Int) -> Unit,
) {
    val servicesState by viewModel.servicesState.collectAsState()

    HomeContent(
        servicesState = servicesState,
        modifier = modifier,
        onNavigateToServiceDetails = onNavigateToServiceDetails,
    )
}

@Composable
private fun HomeContent(
    servicesState: DataUiState<List<ServiceModel>>,
    modifier: Modifier = Modifier,
    onNavigateToServiceDetails: (serviceId: Int) -> Unit,
) {
    Column(modifier = modifier) {
        KMSTMContentWrapper(
            dataState = servicesState,
            dataPopulated = {
                ServicesPopulated(
                    services = (servicesState as DataUiState.Populated).data,
                    onNavigateToServiceDetails = onNavigateToServiceDetails,
                )
            },
            dataEmpty = {
                KMSTMEmptyView(
                    primaryText = stringResource(R.string.services_state_empty_primary_text),
                    modifier = Modifier.fillMaxSize(),
                )
            },
        )
    }
}

@Composable
private fun ServicesPopulated(
    services: List<ServiceModel>,
    modifier: Modifier = Modifier,
    onNavigateToServiceDetails: (serviceId: Int) -> Unit,
) {
    LazyColumn(
        modifier = modifier.fillMaxSize(),
        contentPadding = PaddingValues(bottom = 16.dp),
    ) {
        item { HeroCarousel() }
        item { CategoriesRow() }
        item {
            Text(
                text = "Our Services",
                style = MaterialTheme.typography.titleLarge,
                color = OnSurface,
                modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp),
            )
        }
        items(services, key = { it.id }) { service ->
            ServiceCard(
                service = service,
                onClick = { onNavigateToServiceDetails(service.id) },
            )
        }
    }
}

@Composable
private fun HeroCarousel() {
    val pagerState = rememberPagerState(pageCount = { heroBanners.size })

    Column {
        HorizontalPager(
            state = pagerState,
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp),
        ) { page ->
            val banner = heroBanners[page]
            Box(modifier = Modifier.fillMaxSize()) {
                Image(
                    painter = painterResource(banner.imageRes),
                    contentDescription = banner.title,
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.Crop,
                )
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(
                            Brush.verticalGradient(
                                colors = listOf(Color.Transparent, Color.Black.copy(alpha = 0.7f)),
                                startY = 100f,
                            )
                        ),
                )
                Column(
                    modifier = Modifier
                        .align(Alignment.BottomStart)
                        .padding(16.dp),
                ) {
                    Text(
                        text = banner.title,
                        style = MaterialTheme.typography.titleLarge,
                        color = Color.White,
                        fontWeight = FontWeight.ExtraBold,
                    )
                    Text(
                        text = banner.subtitle,
                        style = MaterialTheme.typography.bodyMedium,
                        color = Color.White.copy(alpha = 0.9f),
                    )
                }
            }
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
            horizontalArrangement = Arrangement.Center,
        ) {
            repeat(heroBanners.size) { index ->
                Box(
                    modifier = Modifier
                        .padding(horizontal = 4.dp)
                        .size(if (index == pagerState.currentPage) 10.dp else 8.dp)
                        .clip(CircleShape)
                        .background(
                            if (index == pagerState.currentPage) Primary
                            else MutedText.copy(alpha = 0.3f)
                        ),
                )
            }
        }
    }
}

@Composable
private fun CategoriesRow() {
    LazyRow(
        modifier = Modifier.padding(vertical = 8.dp),
        contentPadding = PaddingValues(horizontal = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(12.dp),
    ) {
        items(categories) { category ->
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.width(72.dp),
            ) {
                Box(
                    modifier = Modifier
                        .size(56.dp)
                        .background(
                            brush = Brush.linearGradient(
                                colors = listOf(
                                    GradientStart.copy(alpha = 0.15f),
                                    GradientEnd.copy(alpha = 0.15f),
                                ),
                            ),
                            shape = RoundedCornerShape(15.dp),
                        ),
                    contentAlignment = Alignment.Center,
                ) {
                    Icon(
                        imageVector = category.icon,
                        contentDescription = category.name,
                        tint = Primary,
                        modifier = Modifier.size(28.dp),
                    )
                }
                Spacer(modifier = Modifier.height(6.dp))
                Text(
                    text = category.name,
                    style = MaterialTheme.typography.bodySmall,
                    color = OnSurface,
                    maxLines = 1,
                )
            }
        }
    }
}

@Composable
private fun ServiceCard(
    service: ServiceModel,
    onClick: () -> Unit,
) {
    Card(
        onClick = onClick,
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 6.dp),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = Surface.copy(alpha = 0.8f)),
        border = BorderStroke(1.dp, AccentBorder),
    ) {
        Row(modifier = Modifier.padding(12.dp)) {
            Image(
                painter = painterResource(service.imageRes),
                contentDescription = service.name,
                modifier = Modifier
                    .size(80.dp)
                    .clip(RoundedCornerShape(12.dp)),
                contentScale = ContentScale.Crop,
            )
            Spacer(modifier = Modifier.width(12.dp))
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = service.name,
                    style = MaterialTheme.typography.titleMedium,
                    color = OnSurface,
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = service.description,
                    style = MaterialTheme.typography.bodySmall,
                    color = MutedText,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = service.price,
                    style = MaterialTheme.typography.titleSmall,
                    color = Primary,
                )
            }
        }
    }
}
