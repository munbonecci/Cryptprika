package com.munbonecci.cryptprika.database.cypher

import android.content.Context
import net.sqlcipher.database.SQLiteDatabase
import net.sqlcipher.database.SQLiteStatement
import java.io.File
import java.io.FileNotFoundException
import java.io.IOException

object SQLCipherUtils {
    /**
     * The detected state of the database, based on whether we can open it
     * without a passphrase.
     */
    enum class State {
        DOES_NOT_EXIST, UNENCRYPTED, ENCRYPTED
    }

    /**
     * Determine whether or not this database appears to be encrypted, based
     * on whether we can open it without a passphrase.
     *
     * @param context a Context
     * @param dbName the name of the database, as used with Room, SQLiteOpenHelper,
     * etc.
     * @return the detected state of the database
     */
    fun getDatabaseState(context: Context, dbName: String?): State? {
        SQLiteDatabase.loadLibs(context)
        return getDatabaseState(context.getDatabasePath(dbName))
    }

    /**
     * Determine whether or not this database appears to be encrypted, based
     * on whether we can open it without a passphrase.
     *
     * NOTE: You are responsible for ensuring that net.sqlcipher.database.SQLiteDatabase.loadLibs()
     * is called before calling this method. This is handled automatically with the
     * getDatabaseState() method that takes a Context as a parameter.
     *
     * @param dbPath a File pointing to the database
     * @return the detected state of the database
     */
    private fun getDatabaseState(dbPath: File): State? {
        if (dbPath.exists()) {
            var db: SQLiteDatabase? = null
            return try {
                db = SQLiteDatabase.openDatabase(
                    dbPath.absolutePath, "",
                    null, SQLiteDatabase.OPEN_READONLY
                )
                db.version
                State.UNENCRYPTED
            } catch (e: Exception) {
                State.ENCRYPTED
            } finally {
                db?.close()
            }
        }
        return State.DOES_NOT_EXIST
    }

    @Throws(IOException::class)
    fun encrypt(context: Context, originalFile: File, passphrase: ByteArray?) {
        SQLiteDatabase.loadLibs(context)
        if (originalFile.exists()) {
            val newFile: File = File.createTempFile(
                "sqlcipherutils", "tmp",
                context.cacheDir
            )
            var db = SQLiteDatabase.openDatabase(
                originalFile.absolutePath,
                "", null, SQLiteDatabase.OPEN_READWRITE
            )
            val version = db.version

            db.close()
            db = SQLiteDatabase.openDatabase(
                newFile.absolutePath, passphrase,
                null, SQLiteDatabase.OPEN_READWRITE, null, null
            )

            val st: SQLiteStatement = db.compileStatement("ATTACH DATABASE ? AS plaintext KEY ''")
            st.bindString(1, originalFile.absolutePath)
            st.execute()
            db.rawExecSQL("SELECT sqlcipher_export('main', 'plaintext')")
            db.rawExecSQL("DETACH DATABASE plaintext")
            db.version = version

            st.close()
            db.close()
            originalFile.delete()
            newFile.renameTo(originalFile)
        } else {
            throw FileNotFoundException(originalFile.absolutePath.toString() + " not found")
        }
    }
}