package com.altintasomer.application.accuweathercase.view

import android.content.ContentResolver
import android.content.Context
import android.content.SearchRecentSuggestionsProvider
import android.provider.SearchRecentSuggestions
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject


class SearchRecentSuggestionsLimited @Inject constructor(
    @ApplicationContext context: Context,
    private val authority: String =  MySearchQueryContentProvider.AUTHORITY,
    private val mode : Int = SearchRecentSuggestionsProvider.DATABASE_MODE_QUERIES,
    private val limit : Int = 5
) : SearchRecentSuggestions(context, authority, mode) {

    override fun truncateHistory(cr: ContentResolver?, maxEntries: Int) {
        super.truncateHistory(cr, limit)
    }
}