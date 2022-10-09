package app.githubrepositories.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.paging.LoadState
import androidx.recyclerview.widget.LinearLayoutManager
import app.githubrepositories.databinding.FragmentHomeBinding
import app.githubrepositories.model.RepositoryModel
import app.githubrepositories.view.adapter.RepositoryLoadStateAdapter
import app.githubrepositories.view.adapter.RepositoryPagingAdapter
import app.githubrepositories.viewmodels.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private val viewModel: HomeViewModel by viewModels()
    private val repositoryAdapter: RepositoryPagingAdapter = RepositoryPagingAdapter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.repositoriesList.observe(viewLifecycleOwner) {
            repositoryAdapter.submitData(viewLifecycleOwner.lifecycle, it)
            binding.homeProgressBar.visibility = View.GONE
            showList()
        }
        repositoryAdapter.addLoadStateListener { combinedLoadStates ->
            with(binding) {
                homeProgressBar.isVisible = combinedLoadStates.source.refresh is LoadState.Loading
                repositoriesListRecyclerView.isVisible =
                    combinedLoadStates.source.refresh is LoadState.NotLoading
                emptyListTextView.isVisible = combinedLoadStates.source.refresh is LoadState.Error

                if (combinedLoadStates.source.refresh is LoadState.Error) {
                    repositoriesListRecyclerView.isVisible = false
                    emptyListTextView.isVisible = true
                }

                if (combinedLoadStates.source.refresh is LoadState.NotLoading
                    && combinedLoadStates.append.endOfPaginationReached && repositoryAdapter.itemCount < 1
                ) {
                    repositoriesListRecyclerView.isVisible = false
                    emptyListTextView.isVisible = true
                }
            }
        }
    }

    private fun showList() {
        with(binding.repositoriesListRecyclerView) {
            this.layoutManager = LinearLayoutManager(context)
            this.setHasFixedSize(true)
            this.adapter = repositoryAdapter.withLoadStateHeaderAndFooter(
                header = RepositoryLoadStateAdapter { repositoryAdapter.retry() },
                footer = RepositoryLoadStateAdapter { repositoryAdapter.retry() }
            )

            repositoryAdapter.setOnItemCallback(object :
                RepositoryPagingAdapter.OnItemClickCallback {
                override fun onItemClicked(data: RepositoryModel) {
                    val action = HomeFragmentDirections.actionHomeFragmentToDetailFragment(data)
                    findNavController().navigate(action)
                }
            })
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}