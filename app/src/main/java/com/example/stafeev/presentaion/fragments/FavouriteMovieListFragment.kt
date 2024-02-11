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
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.stafeev.R
import com.example.stafeev.databinding.FragmentFavouriteMovieListBinding
import com.example.stafeev.databinding.FragmentMovieListBinding
import com.example.stafeev.domain.entities.MovieEntity
import com.example.stafeev.presentaion.MovieApp
import com.example.stafeev.presentaion.adapter.MovieAdapter
import com.example.stafeev.presentaion.adapter.OnMovieClick
import com.example.stafeev.presentaion.viewModels.FavouriteMovieListViewModel
import com.example.stafeev.presentaion.viewModels.MovieListViewModel
import com.example.stafeev.presentaion.viewModels.ViewModelFactory
import javax.inject.Inject


class FavouriteMovieListFragment : Fragment(), OnMovieClick {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private lateinit var viewModel: FavouriteMovieListViewModel

    private val component by lazy {
        (requireActivity().application as MovieApp).component
    }

    private var _binding: FragmentFavouriteMovieListBinding? = null
    private val binding: FragmentFavouriteMovieListBinding
        get() = _binding ?: throw RuntimeException("FragmentFavouriteMovieListBinding == null")

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentFavouriteMovieListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this, viewModelFactory)[FavouriteMovieListViewModel::class.java]
        val adapter = MovieAdapter(this)
        binding.rvMovie.adapter = adapter
        addToolBar(adapter)
        observe(adapter)
        launchMovieListScreen()
    }

    private fun addToolBar(adapter: MovieAdapter) {
        binding.customToolbar.tvScreenName.text = "Избранное"
        binding.customToolbar.ivBack.visibility = View.GONE
        binding.customToolbar.searchView.setOnCloseListener {
            binding.customToolbar.tvScreenName.visibility = View.VISIBLE
            false
        }
        binding.customToolbar.searchView.setOnSearchClickListener {
            binding.customToolbar.tvScreenName.visibility = View.INVISIBLE
        }
        binding.customToolbar.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
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
        if (isNetworkAvailable(requireContext())) {
            findNavController().navigate(
                FavouriteMovieListFragmentDirections
                    .actionFavouriteMovieListFragmentToMovieDetailInfoFragment(
                        movieEntity
                    )
            )
        } else {
                Toast.makeText(requireContext(), "Ошибка подключения!", Toast.LENGTH_SHORT).show()
        }
    }
    private fun isNetworkAvailable(context: Context): Boolean {
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkInfo = connectivityManager.activeNetworkInfo
        return networkInfo != null && networkInfo.isConnected
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

    private fun observe(adapter: MovieAdapter) {
        viewModel.favouriteMovieList.observe(viewLifecycleOwner) {
            adapter.submitList(it)
        }
    }

    private fun launchMovieListScreen() {
        binding.buttonPop.setOnClickListener {
            findNavController().navigate(R.id.action_favouriteMovieListFragment_to_movieListFragment)
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