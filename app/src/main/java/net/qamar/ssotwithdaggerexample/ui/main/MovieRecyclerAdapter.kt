package net.qamar.ssotwithdaggerexample.ui.main


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import net.qamar.ssotwithdaggerexample.R
import net.qamar.ssotwithdaggerexample.data.model.Movie
import java.util.*


class MovieRecyclerAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private var movies: List<Movie> = ArrayList()
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RecyclerView.ViewHolder {
        val view: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.layout_movies_list_item, parent, false)
        return PostViewHolder(view)
    }

    override fun onBindViewHolder(
        holder: RecyclerView.ViewHolder,
        position: Int
    ) {
        (holder as PostViewHolder).bind(movies[position])
    }

    override fun getItemCount(): Int {
        return movies.size
    }

    fun setMovies(movies: List<Movie>) {
        this.movies = movies
        notifyDataSetChanged()
    }

    inner class PostViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {
        var title: TextView = itemView.findViewById(R.id.title)
        fun bind(movie: Movie) {
            title.text = movie.title
        }

    }
}






