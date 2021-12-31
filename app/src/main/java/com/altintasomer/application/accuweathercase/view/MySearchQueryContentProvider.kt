package com.altintasomer.application.accuweathercase.view

import android.app.SearchManager
import android.content.SearchRecentSuggestionsProvider
import android.content.ContentResolver
import android.content.UriMatcher
import android.database.Cursor
import android.net.Uri
import android.os.CancellationSignal
import android.database.MatrixCursor

import android.provider.BaseColumns
import javax.xml.validation.Schema


class MySearchQueryContentProvider : SearchRecentSuggestionsProvider() {
    private lateinit var matcher: UriMatcher
    private val SUGGESTIONS_CODE = 5

    init {

        matcher = UriMatcher(UriMatcher.NO_MATCH);

        matcher.addURI(AUTHORITY, SearchManager.SUGGEST_URI_PATH_QUERY, SUGGESTIONS_CODE);
        setupSuggestions(AUTHORITY, MODE);
    }

    companion object {
        const val AUTHORITY =
            "com.altintasomer.application.accuweathercase.view.MySearchQueryContentProvider"
        const val MODE: Int = SearchRecentSuggestionsProvider.DATABASE_MODE_QUERIES
    }

    /*override fun query(
        uri: Uri,
        projection: Array<out String>?,
        selection: String?,
        selectionArgs: Array<out String>?,
        sortOrder: String?,
        cancellationSignal: CancellationSignal?
    ): Cursor? {

        var code = matcher.match(uri)
        when (code)
        {
             SUGGESTIONS_CODE ->
            if (selectionArgs == null || selectionArgs.size == 0 || selectionArgs[0].trim().length == 0)
            {
                return super.query(uri, projection, selection, selectionArgs, sortOrder)
            }
            else
            {
                val resolver = getContext()?.getContentResolver();
                return getCursorForSearch(resolver, selectionArgs[0])
            }


        }

        return super.query(uri, projection, selection, selectionArgs, sortOrder, cancellationSignal)
    }

    fun getCursorForSearch(resolver: ContentResolver?, search: String?): Cursor? {
        val columns = arrayOf(
            BaseColumns._ID,
            SearchManager.SUGGEST_COLUMN_TEXT_1,
            SearchManager.SUGGEST_COLUMN_QUERY,
            SearchManager.SUGGEST_COLUMN_TEXT_2
        )
        val ret = MatrixCursor(columns)

        return ret
    }
*/
}