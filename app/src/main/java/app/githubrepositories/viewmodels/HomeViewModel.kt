package app.githubrepositories.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.switchMap
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import app.githubrepositories.data.DataRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val useCase: DataRepository) : ViewModel() {

    private val currentQuery = MutableLiveData(DEFAULT_QUERY)

    companion object {
        private const val DEFAULT_QUERY = "kotlin"
    }

    val repositoriesList = currentQuery.switchMap { currentQuery ->
        useCase.getRepositories(currentQuery).cachedIn(viewModelScope)
    }
}