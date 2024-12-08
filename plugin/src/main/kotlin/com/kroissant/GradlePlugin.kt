package com.kroissant

import com.kroissant.models.DependencyModel
import kotlinx.serialization.SerializationException
import kotlinx.serialization.json.Json
import org.gradle.api.Plugin
import org.gradle.api.Project
import java.io.File
import java.util.*

class GradlePlugin: Plugin<Project> {

    override fun apply(project: Project) {
        val extensions = project.extensions.create("DependencyFromFileConfig", GradleExtensions::class.java)

        project.afterEvaluate {
            val fileType = extensions.fileType
            val inputFile: File = extensions.dependencyFile?: File("${project.projectDir}/deps.txt")

            if(inputFile.createNewFile()) {
                println("File created!")
            }

            val deps: List<DependencyModel> = when (fileType) {
                "json" -> {
                    jsonToDeps(inputFile)
                }
                "properties" -> {
                    propertiesToDeps(inputFile)
                }
                else -> try {
                    jsonToDeps(inputFile)
                } catch (x: SerializationException){
                    propertiesToDeps(inputFile)
                }
            }

            for (dep in deps) {
                println(dep.configurationName+ " " + dep.name)
                project.dependencies.add(dep.configurationName, dep.group +":" + dep.name + ":" + dep.version)
            }
        }

    }

    private fun jsonToDeps(inputFile: File) = Json.decodeFromString<List<DependencyModel>>(inputFile.readText())
    private fun propertiesToDeps(inputFile: File): List<DependencyModel>{
        val deps:MutableList<DependencyModel> = mutableListOf()
        val properties = Properties()
        properties.load(inputFile.reader())
        for (prop in properties.toMap()) {
            val configAndVersion = prop.value.toString().split(" ")
            deps.add(DependencyModel(configAndVersion[0], prop.key.toString(), configAndVersion[1],configAndVersion[2]))
        }
        return deps
    }
}

