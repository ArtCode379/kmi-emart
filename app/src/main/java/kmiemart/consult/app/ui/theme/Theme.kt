package kmiemart.consult.app.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable

private val KMIEMartColorScheme = darkColorScheme(
    primary = Primary,
    secondary = Secondary,
    background = Background,
    surface = Surface,
    onPrimary = OnPrimary,
    onSecondary = OnPrimary,
    onBackground = OnBackground,
    onSurface = OnSurface,
    onSurfaceVariant = MutedText,
    outline = DividerColor,
    surfaceVariant = Surface,
    tertiary = GradientEnd,
)

@Composable
fun ServiceSkeletonTheme(
    darkTheme: Boolean = true,
    dynamicColor: Boolean = false,
    content: @Composable () -> Unit,
) {
    MaterialTheme(
        colorScheme = KMIEMartColorScheme,
        typography = Typography,
        content = content,
    )
}
