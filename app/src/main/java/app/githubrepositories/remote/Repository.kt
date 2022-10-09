package app.githubrepositories.remote

import androidx.lifecycle.LiveData
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.liveData
import app.githubrepositories.model.RepositoryModel
import app.githubrepositories.data.DataRepository
import app.githubrepositories.remote.network.ApiService
import javax.inject.Inject

class Repository @Inject constructor(
    private val apiService: ApiService
) : DataRepository {

    override fun getRepositories(query: String): LiveData<PagingData<RepositoryModel>> {
        return Pager(
            config = PagingConfig(
                pageSize = 20,
                maxSize = 100,
                enablePlaceholders = false,
            ), pagingSourceFactory = { RemoteDataSource(apiService, query) }
        ).liveData
    }
}