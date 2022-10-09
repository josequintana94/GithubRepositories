package app.githubrepositories.remote.network

import app.githubrepositories.model.APIRepositoryListResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("/search/repositories")
    suspend fun getRepositories(
        @Query("q") query: String,
        @Query("page") page: Int,
        @Query("per_page") limit: Int,
    ): APIRepositoryListResponse
}