package lt.ito.models

data class LoginResponse(
    val loginSuccess: Boolean
)

data class FeedsResponse(
    val timelineUrl: String,
    val userUrl: String
)
