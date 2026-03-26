package kmiemart.consult.app.data.model

import androidx.annotation.DrawableRes

data class ServiceModel(
    val id: Int,
    val name: String,
    val description: String,
    val price: String,
    val duration: String,
    val category: String,
    @field:DrawableRes val imageRes: Int,
    val features: List<String> = emptyList(),
)
