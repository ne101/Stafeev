package com.example.stafeev.presentaion.fragments

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.example.stafeev.R
import com.example.stafeev.databinding.FragmentMovieDetailInfoBinding
import com.example.stafeev.databinding.FragmentMovieListBinding
import com.example.stafeev.presentaion.MovieApp
import com.example.stafeev.presentaion.viewModels.MovieDetailInfoViewModel
import com.example.stafeev.presentaion.viewModels.MovieListViewModel
import com.example.stafeev.presentaion.viewModels.ViewModelFactory
import javax.inject.Inject

class MovieDetailInfoFragment : Fragment() {

    private val args by navArgs<MovieDetailInfoFragmentArgs>()
    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private lateinit var viewModel: MovieDetailInfoViewModel

    private val component by lazy {
        (requireActivity().application as MovieApp).component
    }

    private var _binding: FragmentMovieDetailInfoBinding? = null
    private val binding: FragmentMovieDetailInfoBinding
        get() = _binding ?: throw RuntimeException("FragmentMovieDetailInfoBinding == null")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding =  FragmentMovieDetailInfoBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this, viewModelFactory)[MovieDetailInfoViewModel::class.java]
        observe()
        addToolBar()
    }

    private fun observe() {
        viewModel.getMovie(args.movie.id)
        viewModel.movie.observe(viewLifecycleOwner) { movieEntity ->
            binding.tvName.text = movieEntity.name
            binding.tvDescription.text = movieEntity.description
            Glide.with(requireContext())
                .load(movieEntity.posterUrl)
                .into(binding.imageView)
            val genres = movieEntity.genres.map { it.genre }
            val genresText = genres.joinToString(", ")
            binding.tvGenre.text = requireContext().getString(R.string.genre, genresText)
            val countries = movieEntity.countries.map { it.country }
            val countriesText = countries.joinToString(", ")
            binding.tvCountry.text = requireContext().getString(R.string.country, countriesText)
        }
    }

    private fun addToolBar() {
        binding.customToolbar.searchView.visibility = View.INVISIBLE
        binding.customToolbar.tvScreenName.visibility = View.INVISIBLE
        binding.customToolbar.ivBack.setOnClickListener {
            findNavController().popBackStack()
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        component.inject(this)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}