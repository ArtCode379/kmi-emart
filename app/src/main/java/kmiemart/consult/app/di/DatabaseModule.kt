package kmiemart.consult.app.di

import androidx.room.Room
import kmiemart.consult.app.data.database.KMSTMDatabase
import org.koin.dsl.module

private const val DB_NAME = "kmstm_db"

val databaseModule = module {
    single {
        Room.databaseBuilder(
            context = get(),
            klass = KMSTMDatabase::class.java,
        name = DB_NAME
        ).build()
    }

    single { get<KMSTMDatabase>().bookingDao()}

}