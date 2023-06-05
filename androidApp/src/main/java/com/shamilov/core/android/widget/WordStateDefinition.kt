package com.shamilov.core.android.widget

import android.content.Context
import androidx.datastore.core.CorruptionException
import androidx.datastore.core.DataStore
import androidx.datastore.core.Serializer
import androidx.datastore.dataStore
import androidx.datastore.dataStoreFile
import androidx.glance.state.GlanceStateDefinition
import kotlinx.serialization.SerializationException
import kotlinx.serialization.json.Json
import java.io.File
import java.io.InputStream
import java.io.OutputStream

/**
 * @author Shamilov on 03.06.2023
 */
internal object WordStateDefinition : GlanceStateDefinition<WordState> {

    private const val WORD_DATA_STORE_NAME = "widget.data_store"

    private val Context.datastore by dataStore(WORD_DATA_STORE_NAME, WordSerializer)

    override suspend fun getDataStore(context: Context, fileKey: String): DataStore<WordState> {
        return context.datastore
    }

    override fun getLocation(context: Context, fileKey: String): File {
        return context.dataStoreFile(WORD_DATA_STORE_NAME)
    }

    object WordSerializer : Serializer<WordState> {
        override val defaultValue: WordState = WordState.Loading

        override suspend fun readFrom(input: InputStream): WordState = try {
            Json.decodeFromString(
                WordState.serializer(),
                input.readBytes().decodeToString()
            )
        } catch (e: SerializationException) {
            throw CorruptionException("Could not read word data: ${e.message}")
        }

        override suspend fun writeTo(t: WordState, output: OutputStream) {
            output.use {
                it.write(Json.encodeToString(WordState.serializer(), t).encodeToByteArray())
            }
        }
    }
}