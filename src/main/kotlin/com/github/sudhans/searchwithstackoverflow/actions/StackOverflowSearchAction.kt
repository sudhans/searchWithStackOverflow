package com.github.sudhans.searchwithstackoverflow.actions

import com.github.sudhans.searchwithstackoverflow.util.STACKOVERFLOW_SEARCH_URL
import com.intellij.ide.BrowserUtil
import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.actionSystem.CommonDataKeys
import com.intellij.openapi.util.text.StringUtil

class StackOverflowSearchAction : AnAction() {

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
        return selectedText
    }

    override fun update(e: AnActionEvent) {
        e.presentation.isEnabledAndVisible = StringUtil.isNotEmpty(getSelectedText(e))
    }
}