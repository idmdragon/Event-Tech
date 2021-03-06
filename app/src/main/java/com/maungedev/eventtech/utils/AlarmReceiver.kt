package com.maungedev.eventtech.utils

import android.app.AlarmManager
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.media.RingtoneManager
import android.os.Build
import android.util.Log
import android.widget.Toast
import androidx.core.app.NotificationCompat
import androidx.core.content.ContextCompat
import com.maungedev.eventtech.R
import com.maungedev.eventtech.constant.ExtraNameConstant
import com.maungedev.eventtech.constant.PageNameConstant
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

class AlarmReceiver : BroadcastReceiver() {

    companion object {
        const val EVENT_TIME = "event_time"
        const val EVENT_NAME = "event_name"
        const val EVENT_UID = "event_uid"

        private const val ID_ONETIME = 100
        private const val DATE_FORMAT = "yyyy-MM-dd"
        private const val TIME_FORMAT = "HH:mm"
    }


    override fun onReceive(context: Context, intent: Intent) {
        val message = String.format(
            "1 Jam kedepan %s akan di mulai", intent.getStringExtra(
                EVENT_NAME
            ) as String
        )
        val title = "Event Tech Reminder"
        val eventUid = intent.getStringExtra(EVENT_UID) as String
        val notifId = ID_ONETIME

        showAlarmNotification(context, title, message, notifId, eventUid)
    }

    fun setOneTimeAlarm(
        context: Context,
        date: String,
        time: String,
        eventName: String,
        eventUid: String
    ) {
        if (isDateInvalid(date, DATE_FORMAT) || isDateInvalid(time, TIME_FORMAT)) return
        val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
        val intent = Intent(context, AlarmReceiver::class.java)
        intent.putExtra(EVENT_NAME, eventName)
        intent.putExtra(EVENT_TIME, time)
        intent.putExtra(EVENT_UID, eventUid)

        val reminderMillis = DateConverter.convertDateAndTimesToMillis(date, time)
        val pendingIntent =
            PendingIntent.getBroadcast(context, convertLongToInt(reminderMillis), intent, 0)
        alarmManager.set(AlarmManager.RTC_WAKEUP, reminderMillis, pendingIntent)
    }

    fun cancelAlarm(
        context: Context,
        date: String,
        time: String,
    ) {
        val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
        val intent = Intent(context, AlarmReceiver::class.java)
        val reminderMillis = DateConverter.convertDateAndTimesToMillis(date, time)
        val pendingIntent =
            PendingIntent.getBroadcast(context, convertLongToInt(reminderMillis), intent, 0)
        pendingIntent.cancel()
        alarmManager.cancel(pendingIntent)
    }

    private fun convertLongToInt(value: Long): Int {
        return ((value / 1000 / 60).toInt())
    }

    private fun isDateInvalid(date: String, format: String): Boolean {
        return try {
            val df = SimpleDateFormat(format, Locale.getDefault())
            df.isLenient = false
            df.parse(date)
            false
        } catch (e: ParseException) {
            true
        }
    }

    private fun showAlarmNotification(
        context: Context,
        title: String,
        message: String,
        notifId: Int,
        eventUid: String
    ) {
        val channelId = "Channel_1"
        val channelName = "AlarmManager channel"
        val notificationManagerCompat =
            context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        val notificationIntent = Intent(
            context,
            Class.forName(PageNameConstant.DETAIL_PAGE)
        ).putExtra(ExtraNameConstant.EVENT_UID, eventUid)
        val notificationPendingIntent = PendingIntent.getActivity(context, 0, notificationIntent, 0)
        val alarmSound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)
        val builder = NotificationCompat.Builder(context, channelId)
            .setSmallIcon(R.drawable.ic_logo_mini)
            .setContentTitle(title)
            .setContentText(message)
            .setColor(ContextCompat.getColor(context, android.R.color.transparent))
            .setVibrate(longArrayOf(1000, 1000, 1000, 1000, 1000))
            .setSound(alarmSound)
            .setContentIntent(notificationPendingIntent)


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                channelId,
                channelName,
                NotificationManager.IMPORTANCE_DEFAULT
            )
            channel.enableVibration(true)
            channel.vibrationPattern = longArrayOf(1000, 1000, 1000, 1000, 1000)
            builder.setChannelId(channelId)
            notificationManagerCompat.createNotificationChannel(channel)
        }
        val notification = builder.build()
        notificationManagerCompat.notify(notifId, notification)
    }
}