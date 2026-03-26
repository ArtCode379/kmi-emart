package kmiemart.consult.app.ui.composable.screen.bookings

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import kmiemart.consult.app.R
import kmiemart.consult.app.ui.composable.shared.KMSTMContentWrapper
import kmiemart.consult.app.ui.composable.shared.KMSTMEmptyView
import kmiemart.consult.app.ui.state.BookingUiState
import kmiemart.consult.app.ui.state.DataUiState
import kmiemart.consult.app.ui.theme.AccentBorder
import kmiemart.consult.app.ui.theme.MutedText
import kmiemart.consult.app.ui.theme.OnSurface
import kmiemart.consult.app.ui.theme.Primary
import kmiemart.consult.app.ui.viewmodel.BookingViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun BookingsScreen(
    modifier: Modifier = Modifier,
    viewModel: BookingViewModel = koinViewModel(),
) {
    val bookingsState by viewModel.bookingsState.collectAsState()

    var canceledBookingNumber by remember { mutableStateOf("") }
    var shouldShowDialog by remember { mutableStateOf(false) }

    BookingsContent(
        bookingsState = bookingsState,
        modifier = modifier,
        onCancelBookingButtonClick = { bookingNumber ->
            canceledBookingNumber = bookingNumber
            shouldShowDialog = true
        },
    )

    if (shouldShowDialog) {
        AlertDialog(
            onDismissRequest = { shouldShowDialog = false },
            title = {
                Text(
                    text = stringResource(R.string.cancel_booking_dialog_title),
                    color = OnSurface,
                )
            },
            text = {
                Text(
                    text = stringResource(R.string.clear_card_dialog_text),
                    color = MutedText,
                )
            },
            confirmButton = {
                TextButton(
                    onClick = {
                        viewModel.cancelBooking(canceledBookingNumber)
                        shouldShowDialog = false
                    },
                ) {
                    Text("Confirm", color = Color(0xFFE53E3E))
                }
            },
            dismissButton = {
                TextButton(onClick = { shouldShowDialog = false }) {
                    Text("Keep", color = Primary)
                }
            },
            containerColor = kmiemart.consult.app.ui.theme.Surface,
        )
    }
}

@Composable
private fun BookingsContent(
    bookingsState: DataUiState<List<BookingUiState>>,
    modifier: Modifier = Modifier,
    onCancelBookingButtonClick: (bookingNumber: String) -> Unit,
) {
    Column(modifier = modifier) {
        KMSTMContentWrapper(
            dataState = bookingsState,
            dataPopulated = {
                BookingsPopulated(
                    bookings = (bookingsState as DataUiState.Populated).data,
                    onCancelBookingButtonClick = onCancelBookingButtonClick,
                )
            },
            dataEmpty = {
                KMSTMEmptyView(
                    primaryText = stringResource(R.string.bookings_state_empty_primary_text),
                    modifier = Modifier.fillMaxSize(),
                )
            },
        )
    }
}

@Composable
private fun BookingsPopulated(
    bookings: List<BookingUiState>,
    modifier: Modifier = Modifier,
    onCancelBookingButtonClick: (bookingNumber: String) -> Unit,
) {
    LazyColumn(
        modifier = modifier.fillMaxSize(),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp),
    ) {
        items(bookings, key = { it.bookingNumber }) { booking ->
            BookingCard(
                booking = booking,
                onCancelClick = { onCancelBookingButtonClick(booking.bookingNumber) },
            )
        }
    }
}

@Composable
private fun BookingCard(
    booking: BookingUiState,
    onCancelClick: () -> Unit,
) {
    Card(
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = kmiemart.consult.app.ui.theme.Surface.copy(alpha = 0.8f),
        ),
        border = BorderStroke(1.dp, AccentBorder),
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Text(
                    text = booking.serviceName,
                    style = MaterialTheme.typography.titleMedium,
                    color = OnSurface,
                    modifier = Modifier.weight(1f),
                )
                Surface(
                    shape = RoundedCornerShape(12.dp),
                    color = Primary.copy(alpha = 0.2f),
                ) {
                    Text(
                        text = "Confirmed",
                        style = MaterialTheme.typography.labelSmall,
                        color = Primary,
                        modifier = Modifier.padding(horizontal = 10.dp, vertical = 4.dp),
                    )
                }
            }
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = stringResource(R.string.booking_number, booking.bookingNumber),
                style = MaterialTheme.typography.bodySmall,
                color = MutedText,
            )
            Text(
                text = stringResource(
                    R.string.booking_customer,
                    booking.customerFirstName,
                    booking.customerLastName,
                ),
                style = MaterialTheme.typography.bodySmall,
                color = MutedText,
            )
            Text(
                text = booking.timestamp,
                style = MaterialTheme.typography.bodySmall,
                color = MutedText,
            )
            Spacer(modifier = Modifier.height(12.dp))
            TextButton(
                onClick = onCancelClick,
                colors = ButtonDefaults.textButtonColors(contentColor = Color(0xFFE53E3E)),
            ) {
                Text("Cancel Booking")
            }
        }
    }
}
