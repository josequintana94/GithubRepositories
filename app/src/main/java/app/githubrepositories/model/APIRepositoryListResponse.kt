package app.githubrepositories.model

import com.google.gson.annotations.SerializedName

data class APIRepositoryListResponse(
    @SerializedName("total_count")
    val totalCount: Int,
    @SerializedName("items")
    val items: List<APIRepositoryResult>
)

data class APIRepositoryResult(
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
)
