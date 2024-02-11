package com.example.stafeev.presentaion.fragments

import android.content.Context
import android.graphics.Color
import android.graphics.PorterDuff
import android.graphics.PorterDuffColorFilter
import android.net.ConnectivityManager
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.stafeev.R
import com.example.stafeev.databinding.FragmentMovieListBinding
import com.example.stafeev.domain.entities.MovieEntity
import com.example.stafeev.presentaion.MovieApp
import com.example.stafeev.presentaion.adapter.MovieAdapter
import com.example.stafeev.presentaion.adapter.OnMovieClick
import com.example.stafeev.presentaion.viewModels.MovieListViewModel
import com.example.stafeev.presentaion.viewModels.ViewModelFactory
import javax.inject.Inject


class MovieListFragment : Fragment(), OnMovieClick {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private lateinit var viewModel: MovieListViewModel

    private val component by lazy {
        (requireActivity().application as MovieApp).component
    }

    private var _binding: FragmentMovieListBinding? = null
    private val binding: FragmentMovieListBinding
        get() = _binding ?: throw RuntimeException("FragmentMovieListBinding == null")

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentMovieListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this, viewModelFactory)[MovieListViewModel::class.java]
        val adapter = MovieAdapter(this)
        binding.rvMovie.adapter = adapter
        addToolBar(adapter)
        observe(adapter)
        launchFavouriteMovieListScreen()
    }

    private fun addToolBar(adapter: MovieAdapter) {
        binding.customToolbar.tvScreenName.text = "Популярные"
        binding.customToolbar.ivBack.visibility = View.GONE
        binding.customToolbar.searchView.setOnCloseListener {
            binding.customToolbar.tvScreenName.visibility = View.VISIBLE
            false
        }
        binding.customToolbar.searchView.setOnSearchClickListener {
            binding.customToolbar.tvScreenName.visibility = View.INVISIBLE
        }
        binding.customToolbar.searchView.setOnQueryTextListener(object :
            SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                viewModel.filter(newText ?: "")
                return false
            }
        })

        viewModel.filterMovieList.observe(viewLifecycleOwner) {
            adapter.submitList(it)
        }
    }


    override fun onMovieCLickListener(movieEntity: MovieEntity) {
        findNavController().navigate(
            MovieListFragmentDirections
                .actionMovieListFragmentToMovieDetailInfoFragment(
                    movieEntity
                )
        )
    }

    override fun addMovieLingClickListener(movieEntity: MovieEntity) {
        viewModel.addMovieInBD(movieEntity)
    }

    override fun removeMovieLingClickListener(movieEntity: MovieEntity) {
        viewModel.removeMovieFromDB(movieEntity.id)
    }

    override fun checkMovieInDBOrNot(movieEntity: MovieEntity): Boolean {
        val favouriteMovie = viewModel.favouriteMovieList.value
        return favouriteMovie?.any { movieEntity.id == it.id } ?: false
    }

    private fun isNetworkAvailable(context: Context): Boolean {
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkInfo = connectivityManager.activeNetworkInfo
        return networkInfo != null && networkInfo.isConnected
    }

    private fun observe(adapter: MovieAdapter) {
        if (isNetworkAvailable(requireContext())) {
            binding.ivError.visibility = View.INVISIBLE
            binding.tvError.visibility = View.INVISIBLE
            viewModel.movieList.observe(viewLifecycleOwner) {
                adapter.submitList(it)
            }
        } else {
            binding.ivError.visibility = View.VISIBLE
            binding.tvError.visibility = View.VISIBLE
        }
    }

    private fun launchFavouriteMovieListScreen() {
        binding.buttonFav.setOnClickListener {
            findNavController().navigate(R.id.action_movieListFragment_to_favouriteMovieListFragment)
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