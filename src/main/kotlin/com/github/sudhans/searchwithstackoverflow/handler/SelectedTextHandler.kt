package com.github.sudhans.searchwithstackoverflow.handler

import com.github.sudhans.searchwithstackoverflow.util.LogUtil
import com.intellij.openapi.actionSystem.DataContext
import com.intellij.openapi.editor.Caret
import com.intellij.openapi.editor.Editor
import com.intellij.openapi.editor.actionSystem.EditorWriteActionHandler
import com.intellij.openapi.util.TextRange
import java.awt.Desktop
import java.net.URI
import java.net.URL
import java.net.URLEncoder

class SelectedTextHandler(private val baseUrl: String): EditorWriteActionHandler() {

    override fun executeWriteAction(editor: Editor, caret: Caret?, dataContext: DataContext?) {
        val document = editor.document
        if (!document.isWritable) {
            return
        }

        val selectionModel = editor.selectionModel

        val charsRange = TextRange(selectionModel.selectionStart, selectionModel.selectionEnd)

        val searchText = document.text.substring(charsRange.startOffset, charsRange.endOffset)

        if (searchText.isEmpty()) return

        try {
            val encodedUrl = URLEncoder.encode(searchText.replace("\n".toRegex(), ""), "UTF-8")
            openURI(URL(baseUrl + encodedUrl).toURI())
        } catch (e: Exception) {
            LogUtil.writeToEventLog(e.localizedMessage)
        }

    }

    companion object {
        fun openURI(uri: URI?) {
            val desktop = if (Desktop.isDesktopSupported()) Desktop.getDesktop() else null
            if (desktop != null && desktop.isSupported(Desktop.Action.BROWSE)) {
                try {
                    desktop.browse(uri)
                } catch (e: Exception) {
                   LogUtil.writeToEventLog(e.localizedMessage)
                }
            }
        }
    }
}