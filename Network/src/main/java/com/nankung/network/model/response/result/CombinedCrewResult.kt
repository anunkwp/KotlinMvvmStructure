package com.nankung.network.model.response.result

import androidx.room.Entity
import com.google.gson.annotations.SerializedName


@Entity(tableName = "combined_crew", primaryKeys = ["id"])
data class CombinedCrewResult(

	@SerializedName("overview")
	val overview: String? = "",

	@SerializedName("original_language")
	val originalLanguage: String? = "",

	@SerializedName("original_title")
	val originalTitle: String? = "",

	@SerializedName("video")
	val video: Boolean? = false,

	@SerializedName("title")
	val title: String? = "",

	@SerializedName("poster_path")
	val posterPath: String? = "",

	@SerializedName("backdrop_path")
	val backdropPath: String? = "",

	@SerializedName("media_type")
	val mediaType: String? = "",

	@SerializedName("release_date")
	val releaseDate: String? = "",

	@SerializedName("credit_id")
	val creditId: String? = "",

	@SerializedName("popularity")
	val popularity: Double? = 0.0,

	@SerializedName("vote_average")
	val voteAverage: Double? = 0.0,

	@SerializedName("id")
	val id: Int? = 0,

	@SerializedName("department")
	val department: String? = "",

	@SerializedName("job")
	val job: String? = "",

	@SerializedName("adult")
	val adult: Boolean? = false,

	@SerializedName("vote_count")
	val voteCount: Int? = 0
)
{
	override fun toString(): String {
		return "\n overview='$id'," +
				"\n overview='$overview'," +
				"\n originalLanguage='$originalLanguage'\n"+
				"\n originalTitle='$originalTitle'\n"+
				"\n video='$video'\n"+
				"\n title='$title'\n"+
				"\n posterPath='$posterPath'\n"+
				"\n backdropPath='$backdropPath'\n"+
				"\n mediaType='$mediaType'\n"+
				"\n releaseDate='$releaseDate'\n"+
				"\n creditId$creditId'\n"+
				"\n popularity='$popularity'\n"+
				"\n voteAverage='$voteAverage'\n"+
				"\n job='$job'\n"+
				"\n adult='$adult'\n"+
				"\n voteCount='$voteCount'\n"
	}

}