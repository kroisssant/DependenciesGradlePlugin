package com.kroissant

import org.gradle.api.Plugin
import org.gradle.api.Project

class GradlePluginPlugin: Plugin<Project> {

    override fun apply(project: Project) {
        val extensions = project.extensions.create("DependencyFromFileConfig", GradleExtensions::class.java)

        project.tasks.register("dependencyFromFile", FileTask::class.java) {
            task ->
            run {
                task.inputFile = extensions.dependencyFile
                task.downloadMode = extensions.installMode
                task.fileType = extensions.fileType

            }
        }
    }
}

