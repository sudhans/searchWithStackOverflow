package com.github.sudhans.searchwithstackoverflow.util

import com.intellij.notification.Notification
import com.intellij.notification.NotificationType
import com.intellij.notification.Notifications

object LogUtil {

    fun writeToEventLog(message: String) {
        val notification = Notification(" ", " ", "Search: $message", NotificationType.INFORMATION)
        Notifications.Bus.notify(notification)
        notification.balloon?.hide()
    }
}