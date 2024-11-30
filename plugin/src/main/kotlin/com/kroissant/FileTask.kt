package com.kroissant

import com.kroissant.models.DependencyModel
import kotlinx.serialization.SerializationException
import kotlinx.serialization.json.Json
import org.gradle.api.DefaultTask
import org.gradle.api.tasks.Input
import org.gradle.api.tasks.InputFile
import org.gradle.api.tasks.TaskAction
import java.io.File
import java.util.*


abstract class FileTask: DefaultTask() {
    @get:InputFile
    abstract var inputFile: File
    @get:Input
    abstract var fileType: String
    @get:Input
    abstract var downloadMode: String

    private fun jsonToDeps() = Json.decodeFromString<List<DependencyModel>>(inputFile.readText())
    private fun propertiesToDeps() {
        val props = Properties()
        props.load(inputFile.reader())

        println(props.toMap())

    }


    @TaskAction
    fun action() {
        val deps: List<DependencyModel> = when (fileType) {
            "json" -> {
                jsonToDeps()
            }
            "properties" -> {
                propertiesToDeps()
                emptyList()
            }
            else -> try {
                jsonToDeps()
            } catch (x: SerializationException){
                propertiesToDeps()
                emptyList()
            }
        }
        for (dep in deps) {
            project.dependencies.add(dep.configurationName, dep.name+":"+dep.version)
        }
    }
}