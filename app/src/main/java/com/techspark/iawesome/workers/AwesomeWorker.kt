package com.techspark.iawesome.workers

import android.content.Context
import android.preference.PreferenceManager
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.techspark.iawesome.R
import com.techspark.iawesome.util.AwesomeNotification
import com.techspark.iawesome.database.AwesomeDatabase
import com.techspark.iawesome.database.AwesomeModel
import com.techspark.iawesome.util.AwesomeTime
import kotlin.random.Random


class AwesomeWorker(context: Context, workerParams: WorkerParameters) : Worker(context, workerParams) {

    companion object {
        const val GENDER_KEY = "gender"
    }

    override fun doWork(): Result {

        //Don't do anything if gender hasn't been set yet
        if (!PreferenceManager.getDefaultSharedPreferences(applicationContext).contains(GENDER_KEY))
            return Result.success()

        //Don't do anything if it's past night time
        if (AwesomeTime.getTime() == AwesomeTime.NIGHTTIME)
            return Result.success()

        val awesomeModel = addNewMessage()
        AwesomeNotification.showNotification(
            applicationContext,
            "IAwesome",
           awesomeModel.msg
        )
        return Result.success()
    }


    /**
     * Inserts a new message to the database with current date and time
     */
    private fun addNewMessage(): AwesomeModel {
        val awesomeModel = AwesomeModel()
        awesomeModel.msg = getRandomMessage()
        AwesomeDatabase.getInstance(applicationContext).awesomeDao.insert(awesomeModel)
        return awesomeModel
    }

    /**
     * Generates Random message based on time of day
     */
    private fun getRandomMessage(): String {

        val time = AwesomeTime.getTime()

        //For morning or evenings we have fixed messages
        if (time == AwesomeTime.MORNING || time == AwesomeTime.EVENING)
            return getPlaceholderMessage(time)

        //Otherwise either show a full messages or a placeholder message randomly
        if (Random.nextBoolean())
            return getFullMessage()

        return getPlaceholderMessage(time)


    }

    /**
     * Generates a random message based on gender
     */
    private fun getFullMessage(): String {
        val gender = PreferenceManager.getDefaultSharedPreferences(applicationContext).getString(GENDER_KEY, "")
        val messageArray =
            if (gender == "Male") applicationContext.resources.getStringArray(R.array.messages_full_male)
            else applicationContext.resources.getStringArray(R.array.messages_full_female)

        return messageArray[Random.nextInt(messageArray.size)]
    }

    /**
     * Generate a random message based on gender and @param time
     *
     */
    private fun getPlaceholderMessage(time: AwesomeTime): String {
        val res = applicationContext.resources
        val placeHolderArray = res.getStringArray(R.array.messages_placeholder)
        val gender = PreferenceManager.getDefaultSharedPreferences(applicationContext).getString(GENDER_KEY, "")
        val subjectArray =
            if (gender == "Male") res.getStringArray(R.array.messages_subject_male) else res.getStringArray(R.array.messages_subject_female)


        return when (time) {

            //fixed message for mornings
            AwesomeTime.MORNING -> String.format(placeHolderArray[0], subjectArray[Random.nextInt(subjectArray.size)])

            //fixed message for evenings
            AwesomeTime.EVENING -> String.format(placeHolderArray[1], subjectArray[Random.nextInt(subjectArray.size)])

            else -> String.format(
                placeHolderArray[Random.nextInt(2, placeHolderArray.size)],
                subjectArray[Random.nextInt(subjectArray.size)]
            )
        }

    }


}