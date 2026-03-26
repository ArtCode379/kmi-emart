package kmiemart.consult.app.ui.composable.screen.servicedetails

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kmiemart.consult.app.R
import kmiemart.consult.app.data.model.ServiceModel
import kmiemart.consult.app.ui.composable.shared.KMSTMContentWrapper
import kmiemart.consult.app.ui.composable.shared.KMSTMEmptyView
import kmiemart.consult.app.ui.state.DataUiState
import kmiemart.consult.app.ui.theme.Background
import kmiemart.consult.app.ui.theme.GradientEnd
import kmiemart.consult.app.ui.theme.GradientStart
import kmiemart.consult.app.ui.theme.MutedText
import kmiemart.consult.app.ui.theme.OnSurface
import kmiemart.consult.app.ui.theme.Primary
import kmiemart.consult.app.ui.theme.Secondary
import kmiemart.consult.app.ui.viewmodel.ServiceDetailsViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun ServiceDetailsScreen(
    serviceId: Int,
    modifier: Modifier = Modifier,
    viewModel: ServiceDetailsViewModel = koinViewModel(),
    onNavigateToCheckout: (serviceId: Int) -> Unit,
) {
    val serviceState by viewModel.serviceState.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.observeServiceById(serviceId)
    }

    ServiceDetailsContent(
        serviceState = serviceState,
        modifier = modifier,
        onNavigateToCheckout = onNavigateToCheckout,
    )
}

@Composable
private fun ServiceDetailsContent(
    serviceState: DataUiState<ServiceModel>,
    modifier: Modifier = Modifier,
    onNavigateToCheckout: (serviceId: Int) -> Unit,
) {
    Column(modifier = modifier) {
        KMSTMContentWrapper<ServiceModel>(
            dataState = serviceState,
            dataPopulated = {
                ServicesDetailsPopulated(
                    service = (serviceState as DataUiState.Populated).data,
                    onNavigateToCheckout = onNavigateToCheckout,
                )
            },
            dataEmpty = {
                KMSTMEmptyView(
                    primaryText = stringResource(R.string.service_details_state_empty_primary_text),
                    modifier = Modifier.fillMaxSize(),
                )
            },
        )
    }
}

@Composable
private fun ServicesDetailsPopulated(
    service: ServiceModel,
    modifier: Modifier = Modifier,
    onNavigateToCheckout: (serviceId: Int) -> Unit,
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState()),
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(280.dp),
        ) {
            Image(
                painter = painterResource(service.imageRes),
                contentDescription = service.name,
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop,
            )
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(
                        Brush.verticalGradient(
                            colors = listOf(Color.Transparent, Background.copy(alpha = 0.9f)),
                            startY = 200f,
                        )
                    ),
            )
        }

        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = service.name,
                style = MaterialTheme.typography.headlineMedium,
                color = OnSurface,
            )
            Spacer(modifier = Modifier.height(8.dp))

            Surface(
                shape = RoundedCornerShape(16.dp),
                color = Secondary.copy(alpha = 0.2f),
            ) {
                Text(
                    text = service.category,
                    style = MaterialTheme.typography.labelSmall,
                    color = Secondary,
                    modifier = Modifier.padding(horizontal = 12.dp, vertical = 6.dp),
                )
            }
            Spacer(modifier = Modifier.height(12.dp))

            Text(
                text = "${service.price} \u00B7 ${service.duration}",
                style = MaterialTheme.typography.titleMedium,
                color = Primary,
            )
            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = service.description,
                style = MaterialTheme.typography.bodyMedium,
                color = MutedText,
            )
            Spacer(modifier = Modifier.height(24.dp))

            if (service.features.isNotEmpty()) {
                Text(
                    text = "What\u2019s Included",
                    style = MaterialTheme.typography.titleMedium,
                    color = OnSurface,
                )
                Spacer(modifier = Modifier.height(12.dp))
                service.features.forEach { feature ->
                    Row(
                        modifier = Modifier.padding(vertical = 4.dp),
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        Icon(
                            imageVector = Icons.Filled.CheckCircle,
                            contentDescription = null,
                            tint = Primary,
                            modifier = Modifier.size(20.dp),
                        )
                        Spacer(modifier = Modifier.width(12.dp))
                        Text(
                            text = feature,
                            style = MaterialTheme.typography.bodyMedium,
                            color = OnSurface,
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(32.dp))

            Button(
                onClick = { onNavigateToCheckout(service.id) },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(48.dp),
                shape = RoundedCornerShape(24.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent),
                contentPadding = PaddingValues(),
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(
                            brush = Brush.linearGradient(listOf(GradientStart, GradientEnd)),
                            shape = RoundedCornerShape(24.dp),
                        ),
                    contentAlignment = Alignment.Center,
                ) {
                    Text(
                        text = stringResource(R.string.button_book_consultation_text),
                        color = Color.White,
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 16.sp,
                    )
                }
            }
        }
    }
}
