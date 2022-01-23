package com.github.sudhans.searchwithstackoverflow.actions

import com.github.sudhans.searchwithstackoverflow.handler.SelectedTextHandler
import com.github.sudhans.searchwithstackoverflow.util.STACKOVERFLOW_SEARCH_URL
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.editor.actionSystem.EditorAction

class StackOverflowSearchAction : EditorAction(SelectedTextHandler(STACKOVERFLOW_SEARCH_URL)) {

//    override fun actionPerformed(e: AnActionEvent) {
//        e.project?.let { project ->
//            if (project.isDisposed || !project.isInitialized) {
//                return
//            }
//
//            val editor = CommonDataKeys.EDITOR.getData(e.dataContext)
//            editor?.let {
//                var selectedText = it.selectionModel.selectedText
//                if (StringUtil.isEmptyOrSpaces(selectedText)) {
//                    selectedText = ""
//                } else {
//                    selectedText = selectedText?.trim()
//                }
//                BrowserUtil.browse(String.format("https://stackoverflow.com/search?q=%s", selectedText))
//            }
//
//
//        }
//    }

    override fun update(e: AnActionEvent) {
        e.presentation.isEnabledAndVisible = true
    }
}