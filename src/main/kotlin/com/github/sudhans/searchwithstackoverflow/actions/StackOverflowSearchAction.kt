package com.github.sudhans.searchwithstackoverflow.actions

import com.github.sudhans.searchwithstackoverflow.util.STACKOVERFLOW_SEARCH_URL
import com.intellij.ide.BrowserUtil
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.actionSystem.CommonDataKeys
import com.intellij.openapi.project.DumbAwareAction
import com.intellij.openapi.util.text.StringUtil
import com.intellij.terminal.JBTerminalWidget

class StackOverflowSearchAction : DumbAwareAction() {

    override fun actionPerformed(e: AnActionEvent) {
        e.project?.let { project ->
            if (project.isDisposed || !project.isInitialized) {
                return
            }
            getSelectedText(e)?.let {
                BrowserUtil.browse(String.format(STACKOVERFLOW_SEARCH_URL, it))
            }

        }
    }

    private fun getSelectedText(e: AnActionEvent): String? {
        var selectedText: String? = ""
        val editor = CommonDataKeys.EDITOR.getData(e.dataContext)
        editor?.let {
            selectedText = it.selectionModel.selectedText
            selectedText = if (StringUtil.isEmptyOrSpaces(selectedText)) {
                ""
            } else {
                selectedText?.trim()
            }
        }
        selectedText?.let {
            if (it.isEmpty()) {
                selectedText = e.getData(JBTerminalWidget.SELECTED_TEXT_DATA_KEY)
            }
        }

        return selectedText
    }

    override fun update(e: AnActionEvent) {
        e.presentation.isEnabledAndVisible = getSelectedText(e).isNullOrBlank().not()
    }
}