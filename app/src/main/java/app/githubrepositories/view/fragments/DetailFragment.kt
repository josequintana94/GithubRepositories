package app.githubrepositories.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import app.githubrepositories.databinding.FragmentDetailBinding
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailFragment : Fragment() {

    private var _binding: FragmentDetailBinding? = null
    private val binding get() = _binding!!

    val args: DetailFragmentArgs by navArgs()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding) {
            repositoryNameTextView.text = args.repository.name
            repositoryOwnerNameTextView.text = args.repository.owner?.login
            repositoryDescriptionTextView.text = args.repository.description
            repositoryForksTextView.text = args.repository.forks.toString()

            Glide.with(root.context)
                .load(args.repository.owner?.avatar_url)
                .apply(RequestOptions.bitmapTransform(RoundedCorners(14)))
                .into(repositoryOwnerAvatarImageView)
        }

    }
}