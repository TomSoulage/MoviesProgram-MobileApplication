package com.example.androidproject.model

import android.content.Context
import android.os.Handler
import android.os.Looper
import com.example.androidproject.App
import com.example.androidproject.ViewMovieContract
import com.example.androidproject.dao.Movie
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class ViewMovieModelImpl(private val context: Context?) : ViewMovieContract.ViewMovieModel {
    override fun loadMovies(listener: ViewMovieContract.ViewMovieModel.OnMoviesLoadedListener) {
        GlobalScope.launch {
            val movies = (context?.applicationContext as App).getMovieDao().getMovies()
            Handler(Looper.getMainLooper()).post{
                listener.onMoviesLoaded(movies)
            }
        }
    }

    override fun updateMovie(
        movie: Movie,
        listener: ViewMovieContract.ViewMovieModel.OnMovieUpdatedListener
    ) {
        GlobalScope.launch {
            (context?.applicationContext as App).getMovieDao().update(movie)
            Handler(Looper.getMainLooper()).post{
                listener.onMovieUpdated(movie)
            }
        }
    }
}