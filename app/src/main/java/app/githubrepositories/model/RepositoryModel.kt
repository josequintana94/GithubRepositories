package app.githubrepositories.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class RepositoryModel(
    @SerializedName("id")
    val id: Int?,
    @SerializedName("name")
    val name: String?,
    @SerializedName("full_name")
    val full_name: String?,
    @SerializedName("description")
    val description: String?,
    @SerializedName("open_issues_count")
    val open_issues_count: Int?,
    @SerializedName("forks")
    val forks: Int?,
    @SerializedName("owner")
    val owner: RepositoryOwnerModel?
) : Serializable