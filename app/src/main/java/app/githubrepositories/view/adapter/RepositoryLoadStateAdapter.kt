package app.githubrepositories.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.RecyclerView
import app.githubrepositories.databinding.LoadStateFooterBinding

class RepositoryLoadStateAdapter(private val retry: () -> Unit) :
    LoadStateAdapter<RepositoryLoadStateAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, loadState: LoadState): ViewHolder {
        val binding =
            LoadStateFooterBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    inner class ViewHolder(private val binding: LoadStateFooterBinding) :
        RecyclerView.ViewHolder(binding.root) {
        init {
            binding.footerRetryButton.setOnClickListener {
                retry.invoke()
            }
        }

        fun bind(loadState: LoadState) {
            with(binding) {
                footerProgressBar.isVisible = loadState is LoadState.Loading
                footerRetryButton.isVisible = loadState !is LoadState.Loading
                footerErrorTextView.isVisible = loadState !is LoadState.Loading
            }
        }
    }

    override fun onBindViewHolder(holder: ViewHolder, loadState: LoadState) {
        holder.bind(loadState)
    }
}