package com.workfort.pstuian.workmanager.worker

import android.content.Context
import android.graphics.BitmapFactory
import android.net.Uri
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import androidx.work.workDataOf
import com.workfort.pstuian.appconstant.Const
import com.workfort.pstuian.workmanager.util.ImageUtil
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okio.FileNotFoundException
import okio.IOException
import java.io.File
import java.io.FileOutputStream


/**
 *  ****************************************************************************
 *  * Created by : arhan on 19 Oct, 2021 at 2:48 PM.
 *  * Email : ashik.pstu.cse@gmail.com
 *  *
 *  * This class is for:
 *  * 1. Compress an given image
 *  * 2. If successful, the data contains the file name.
 *  * 3. The file should be found under File(context.cacheDir, fileName)
 *  ****************************************************************************
 */

class ImageCompressorWorker(
    private val context: Context, workerParams: WorkerParameters
): CoroutineWorker(context, workerParams) {
    override suspend fun doWork(): Result {
        val uriStr = inputData.getString(Const.Key.URI)
        val tempFile = inputData.getString(Const.Key.NAME)
        val maxSize = inputData.getInt(Const.Key.MAX_SIZE, 200)
        if(uriStr.isNullOrEmpty() || tempFile.isNullOrEmpty() || maxSize <= 0) {
            return Result.failure()
        }

        return withContext(Dispatchers.IO) {
            compress(context, uriStr, tempFile, maxSize)
        }
    }

    private fun compress(
        context: Context,
        uriStr: String,
        tempFileName: String,
        maxSize: Int
    ): Result {
        val contentResolver = context.contentResolver
        try {
            val uri = Uri.parse(uriStr)
            val tempFile = File(context.cacheDir, tempFileName)
            contentResolver.openInputStream(uri)?.use { inputStream ->
                val bitmap = BitmapFactory.decodeStream(inputStream)
                val compressed = ImageUtil.compress(bitmap, maxSize)
                FileOutputStream(tempFile).use { outputStream ->
                    outputStream.write(compressed)
                    outputStream.flush()
                }
            }
            val data = workDataOf(Const.Key.NAME to tempFileName)
            return if(tempFile.length() > 0) Result.success(data) else Result.failure()
        } catch (e: FileNotFoundException) {
            e.printStackTrace()
            return Result.failure()
        } catch (e: IOException) {
            e.printStackTrace()
            return Result.failure()
        } catch (e: NullPointerException) {
            e.printStackTrace()
            return Result.failure()
        }
    }
}