package app.githubrepositories.data

import app.githubrepositories.model.RepositoryModel
import app.githubrepositories.model.APIRepositoryResult

object RepositoryMapper {

    fun mapRepositoryResponseToModel(data: List<APIRepositoryResult>): List<RepositoryModel> {
        return data.map {
            with(it) {
                RepositoryModel(
                    id,
                    name,
                    full_name,
                    description,
                    open_issues_count,
                    forks,
                    owner
                )
            }
        }
    }
}