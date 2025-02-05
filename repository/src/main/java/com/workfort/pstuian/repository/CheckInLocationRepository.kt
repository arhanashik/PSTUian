package com.workfort.pstuian.repository

import com.workfort.pstuian.model.CheckInLocationEntity
import com.workfort.pstuian.networking.domain.CheckInLocationApiHelper


class CheckInLocationRepository(
    private val authRepo: AuthRepository,
    private val helper: CheckInLocationApiHelper,
) {
    suspend fun getAll(page: Int) = helper.getAll(page)
    suspend fun get(id: Int) = helper.get(id)
    suspend fun search(query: String, page: Int) = helper.search(query, page)
    suspend fun insert(
        name: String,
        details: String? = "",
        imageUrl: String? = "",
        link: String? = "",
    ): CheckInLocationEntity {
        val userIdAndType = authRepo.getUserIdAndType()

        return helper.insert(
            userIdAndType.first,
            userIdAndType.second,
            name,
            details,
            imageUrl,
            link,
        ) ?: throw Exception("Failed")
    }
}