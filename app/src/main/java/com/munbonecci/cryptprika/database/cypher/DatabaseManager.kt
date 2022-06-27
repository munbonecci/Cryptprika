package com.munbonecci.cryptprika.database.cypher

import android.app.Application
import androidx.room.Room
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.munbonecci.cryptprika.database.CryptprikaDatabase
import com.munbonecci.cryptprika.database.favorites.FavoritesDao
import net.sqlcipher.database.SQLiteDatabase
import net.sqlcipher.database.SupportFactory

object DataBaseManager {
    private const val dbName = "c_t_database"
    private lateinit var passWord: String

    private var _dbDecrypted: CryptprikaDatabase? = null
    val dbDecrypted: CryptprikaDatabase
        get() = _dbDecrypted ?: throw IllegalStateException("Db is not initialized")

    val favoritesDao: FavoritesDao
        get() = dbDecrypted.favoritesDao()

    @JvmStatic
    fun init(application: Application) {
        buildDatabase(application)
        passWord = PassUtils.getUuid(application.applicationContext)
        val state = SQLCipherUtils.getDatabaseState(application, dbName)
        if (state == SQLCipherUtils.State.UNENCRYPTED) migrate(application)
        createInstance(application)
    }

    private fun migrate(application: Application) {
        runCatching {
            SQLCipherUtils.encrypt(
                application.applicationContext,
                application.applicationContext.getDatabasePath(dbName),
                passWord.toByteArray()
            )
        }.onFailure {
            it.printStackTrace()
        }
    }

    private val MIGRATION_1_2: Migration = object : Migration(1, 2) {
        override fun migrate(database: SupportSQLiteDatabase) {
            database.execSQL("CREATE TABLE IF NOT EXISTS `Favorites` (`uuid` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `id` TEXT NOT NULL, `name` TEXT NOT NULL, `image` TEXT NOT NULL, `creationDate` TEXT NOT NULL , `modificationDate` TEXT NOT NULL)")
        }
    }

    private fun buildDatabase(application: Application) = Room.databaseBuilder(
        application.applicationContext,
        CryptprikaDatabase::class.java,
        dbName
    ).fallbackToDestructiveMigration().addMigrations(MIGRATION_1_2, MIGRATION_1_2).build()

    private fun createInstance(application: Application) {
        if (_dbDecrypted == null) {
            val builder = Room.databaseBuilder(
                application.applicationContext,
                CryptprikaDatabase::class.java, dbName
            )

            val factory =
                SupportFactory(SQLiteDatabase.getBytes(passWord.toCharArray()), null, false)
            builder.openHelperFactory(factory)
            _dbDecrypted =
                builder.fallbackToDestructiveMigration().addMigrations(MIGRATION_1_2, MIGRATION_1_2)
                    .build()
        }
    }
}