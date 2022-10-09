package app.githubrepositories.remote

import androidx.paging.PagingSource
import androidx.paging.PagingState
import app.githubrepositories.data.RepositoryMapper
import app.githubrepositories.model.RepositoryModel
import app.githubrepositories.remote.network.ApiService
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject
import javax.inject.Singleton

private const val STARTING_PAGE_INDEX: Int = 1

@Singleton
class RemoteDataSource @Inject constructor(
    private val apiService: ApiService,
    private val query: String
) :
    PagingSource<Int, RepositoryModel>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, RepositoryModel> {
        return try {
            val position = params.key ?: STARTING_PAGE_INDEX
            val response = apiService.getRepositories(
                query,
                position,
                params.loadSize
            )
            val result = RepositoryMapper.mapRepositoryResponseToModel(response.items)

            LoadResult.Page(
                data = result,
                prevKey = if (position == STARTING_PAGE_INDEX) null else position - 1,
                nextKey = if (result.isEmpty()) null else position + 1
            )
        } catch (e: IOException) {
            LoadResult.Error(e)
        } catch (e: HttpException) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, RepositoryModel>): Int? {
        TODO("Not yet implemented")
    }
}