package net.qamar.ssotwithdaggerexample.data.model

import android.os.Parcel
import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.Json

@Entity
class Movie() : Parcelable {
    @PrimaryKey
    lateinit var imdbID: String

    @field:Json(name ="Title")
    var title: String? = null

    @field:Json(name = "Year")
    var year: String? = null

    @field:Json(name = "Type")
    var type: String? = null

    @field:Json(name = "Poster")
    var poster: String? = null

    @field:Json(name = "Rated")
    var rated: String? = null

    @field:Json(name = "Released")
    var released: String? = null

    @field:Json(name = "Genre")
    var genre: String? = null

    @field:Json(name = "Director")
    var director: String? = null

    @field:Json(name = "Country")
    var country: String? = null

    @field:Json(name = "Awards")
    var awards: String? = null
    var imdbRating: String? = null

    @field:Json(name = "BoxOffice")
    var boxOffice: String? = null

    @field:Json(name = "Website")
    var website: String? = null

    constructor(parcel: Parcel) : this() {
        imdbID = parcel.readString().toString()
        title = parcel.readString()
        year = parcel.readString()
        type = parcel.readString()
        poster = parcel.readString()
        rated = parcel.readString()
        released = parcel.readString()
        genre = parcel.readString()
        director = parcel.readString()
        country = parcel.readString()
        awards = parcel.readString()
        imdbRating = parcel.readString()
        boxOffice = parcel.readString()
        website = parcel.readString()
    }

    override fun toString(): String {
        return "Movie(imdbID='$imdbID', title=$title, year=$year, type=$type, poster=$poster, rated=$rated, released=$released, genre=$genre, director=$director, country=$country, awards=$awards, imdbRating=$imdbRating, boxOffice=$boxOffice, website=$website)"
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(imdbID)
        parcel.writeString(title)
        parcel.writeString(year)
        parcel.writeString(type)
        parcel.writeString(poster)
        parcel.writeString(rated)
        parcel.writeString(released)
        parcel.writeString(genre)
        parcel.writeString(director)
        parcel.writeString(country)
        parcel.writeString(awards)
        parcel.writeString(imdbRating)
        parcel.writeString(boxOffice)
        parcel.writeString(website)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Movie> {
        override fun createFromParcel(parcel: Parcel): Movie {
            return Movie(parcel)
        }

        override fun newArray(size: Int): Array<Movie?> {
            return arrayOfNulls(size)
        }
    }

}