package com.github.sudhans.searchwithstackoverflow.services

import com.intellij.openapi.project.Project
import com.github.sudhans.searchwithstackoverflow.MyBundle

class MyProjectService(project: Project) {

    init {
        println(MyBundle.message("projectService", project.name))
    }
}
