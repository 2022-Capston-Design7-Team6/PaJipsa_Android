package com.capstone.patech_android.data.api

import com.capstone.patech_android.data.request.HarvestRequest
import com.capstone.patech_android.data.request.RecordRequest
import com.capstone.patech_android.data.response.PreviewResponse
import retrofit2.http.*

interface CameraService {
    @GET("/getphotos/{plant}")
    suspend fun getPreview(
        @Path("plant") plantId: Int,
    ): PreviewResponse

    @POST("/harvest/")
    suspend fun postHarvest(
        @Body body: HarvestRequest
    )

    @POST("/photos/")
    suspend fun postRecord(
        @Body body: RecordRequest
    )
}