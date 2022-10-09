package app.githubrepositories.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class RepositoryOwnerModel(
    @SerializedName("login")
    val login: String,
    @SerializedName("avatar_url")
    val avatar_url: String
) : Serializable