package com.techspark.iawesome

import android.content.Context
import androidx.work.Worker
import androidx.work.WorkerParameters

class AwesomeWorker(context: Context, workerParams: WorkerParameters) : Worker(context, workerParams){

    override fun doWork(): Result {

        return Result.success()
    }

}