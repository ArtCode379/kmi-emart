package kmiemart.consult.app.ui.composable.screen.settings

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
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
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import kmiemart.consult.app.R
import kmiemart.consult.app.ui.theme.Background
import kmiemart.consult.app.ui.theme.DividerColor
import kmiemart.consult.app.ui.theme.GradientEnd
import kmiemart.consult.app.ui.theme.GradientStart
import kmiemart.consult.app.ui.theme.MutedText
import kmiemart.consult.app.ui.theme.OnSurface
import kmiemart.consult.app.ui.theme.Primary

@Composable
fun SettingsScreen(modifier: Modifier = Modifier) {
    SettingsScreenContent(modifier = modifier)
}

@Composable
fun SettingsScreenContent(modifier: Modifier = Modifier) {
    val context = LocalContext.current
    var darkModeEnabled by remember { mutableStateOf(true) }
    var notificationsEnabled by remember { mutableStateOf(true) }

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(Background)
            .verticalScroll(rememberScrollState())
            .padding(16.dp),
    ) {
        Text(
            text = "ABOUT",
            style = MaterialTheme.typography.labelSmall,
            color = MutedText,
            modifier = Modifier.padding(vertical = 8.dp),
        )

        SettingsItem(
            icon = Icons.Filled.Person,
            label = stringResource(R.string.settings_screen_company_label),
            trailing = {
                Text(
                    text = stringResource(R.string.company_name),
                    color = MutedText,
                    style = MaterialTheme.typography.bodyMedium,
                )
            },
        )
        HorizontalDivider(color = DividerColor)
        SettingsItem(
            icon = Icons.Filled.Info,
            label = stringResource(R.string.settings_screen_version_label),
            trailing = {
                Text(
                    text = stringResource(R.string.app_version),
                    color = MutedText,
                    style = MaterialTheme.typography.bodyMedium,
                )
            },
        )

        Spacer(modifier = Modifier.height(24.dp))

        Text(
            text = "SUPPORT",
            style = MaterialTheme.typography.labelSmall,
            color = MutedText,
            modifier = Modifier.padding(vertical = 8.dp),
        )

        SettingsItem(
            icon = Icons.Filled.Email,
            label = "Visit Website",
            onClick = {
                val intent = Intent(
                    Intent.ACTION_VIEW,
                    Uri.parse(context.getString(R.string.customer_support_link)),
                )
                context.startActivity(intent)
            },
        )

        Spacer(modifier = Modifier.height(24.dp))

        Text(
            text = "PREFERENCES",
            style = MaterialTheme.typography.labelSmall,
            color = MutedText,
            modifier = Modifier.padding(vertical = 8.dp),
        )

        SettingsItem(
            icon = Icons.Filled.Star,
            label = "Dark Mode",
            trailing = {
                Switch(
                    checked = darkModeEnabled,
                    onCheckedChange = { darkModeEnabled = it },
                    colors = SwitchDefaults.colors(checkedTrackColor = Primary),
                )
            },
        )
        HorizontalDivider(color = DividerColor)
        SettingsItem(
            icon = Icons.Filled.Notifications,
            label = "Notifications",
            trailing = {
                Switch(
                    checked = notificationsEnabled,
                    onCheckedChange = { notificationsEnabled = it },
                    colors = SwitchDefaults.colors(checkedTrackColor = Primary),
                )
            },
        )
    }
}

@Composable
private fun SettingsItem(
    icon: ImageVector,
    label: String,
    modifier: Modifier = Modifier,
    onClick: (() -> Unit)? = null,
    trailing: @Composable (() -> Unit)? = null,
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .then(if (onClick != null) Modifier.clickable(onClick = onClick) else Modifier)
            .padding(vertical = 14.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Icon(
            imageVector = icon,
            contentDescription = label,
            tint = Primary,
            modifier = Modifier
                .size(40.dp)
                .background(
                    brush = Brush.linearGradient(
                        colors = listOf(
                            GradientStart.copy(alpha = 0.15f),
                            GradientEnd.copy(alpha = 0.15f),
                        ),
                    ),
                    shape = RoundedCornerShape(15.dp),
                )
                .padding(8.dp),
        )
        Spacer(modifier = Modifier.width(16.dp))
        Text(
            text = label,
            style = MaterialTheme.typography.bodyLarge,
            color = OnSurface,
            modifier = Modifier.weight(1f),
        )
        trailing?.invoke()
    }
}
