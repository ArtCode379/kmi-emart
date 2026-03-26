package kmiemart.consult.app.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import kmiemart.consult.app.data.dao.BookingDao
import kmiemart.consult.app.data.database.converter.Converters
import kmiemart.consult.app.data.entity.BookingEntity

@Database(
    entities = [BookingEntity::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(Converters::class)
abstract class KMSTMDatabase : RoomDatabase() {

    abstract fun bookingDao(): BookingDao
}

