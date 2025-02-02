import com.squareup.moshi.FromJson
import com.squareup.moshi.ToJson
import java.text.ParseException
import java.text.SimpleDateFormat
import java.time.OffsetDateTime
import java.time.format.DateTimeFormatter
import java.util.*

class DateJsonAdapter {

    private val dateFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.getDefault())

    @FromJson
    fun fromJson(dateString: String): Date {
        return try {
            dateFormat.parse(dateString) ?: throw ParseException("Invalid date format", 0)
        } catch (e: ParseException) {
            throw RuntimeException("Error parsing date: $dateString", e)
        }    }

    @ToJson
    fun toJson(date: Date): String {
        return dateFormat.format(date)
    }
}