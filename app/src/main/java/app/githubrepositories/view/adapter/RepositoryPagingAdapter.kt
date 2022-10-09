package app.githubrepositories.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import app.githubrepositories.databinding.ItemRepositoryBinding
import app.githubrepositories.model.RepositoryModel
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions

class RepositoryPagingAdapter : PagingDataAdapter<RepositoryModel, RepositoryPagingAdapter.ViewHolder>(DIFF) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            ItemRepositoryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    lateinit var onItemClickCallback: OnItemClickCallback

    fun setOnItemCallback(callback: OnItemClickCallback) {
        this.onItemClickCallback = callback
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val repository = getItem(position)
        if (repository != null) {
            holder.bind(repository)
        }
    }

    inner class ViewHolder(private val binding: ItemRepositoryBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(data: RepositoryModel) {
            with(binding) {

                Glide.with(root.context)
                    .load(data.owner?.avatar_url)
                    .apply(RequestOptions.bitmapTransform(RoundedCorners(14)))
                    .into(repositoryOwnerAvatarImageView)

                repositoryNameTextView.text = data.name
                repositoryOwnerNameTextView.text = data.owner?.login

                root.setOnClickListener {
                    onItemClickCallback.onItemClicked(data)
                }
            }
        }
    }


    companion object {
        private val DIFF = object : DiffUtil.ItemCallback<RepositoryModel>() {
            override fun areItemsTheSame(oldItem: RepositoryModel, newItem: RepositoryModel): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: RepositoryModel, newItem: RepositoryModel): Boolean {
                return oldItem == newItem
            }

        }
    }

    interface OnItemClickCallback {
        fun onItemClicked(data: RepositoryModel)
    }
}