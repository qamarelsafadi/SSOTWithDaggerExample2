package net.qamar.ssotwithdaggerexample.data.model
import com.squareup.moshi.Json
import net.qamar.ssotwithdaggerexample.data.model.Movie
import java.util.*

class SearchResult() {

    @field:Json(name ="Search")
   var search: ArrayList<Movie>? = null

    @field:Json(name = "totalResults")
    var totalResults = 0

    @field:Json(name = "Response")
    var isResponse : String?=null

    override fun toString(): String {
        return "SearchResult(search=$search, totalResults=$totalResults, response=$isResponse )"
    }

}