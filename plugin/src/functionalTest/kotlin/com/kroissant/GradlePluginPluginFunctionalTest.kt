/*
 * This source file was generated by the Gradle 'init' task
 */
package com.kroissant

import java.io.File
import kotlin.test.assertTrue
import kotlin.test.Test
import org.gradle.testkit.runner.GradleRunner
import org.junit.jupiter.api.io.TempDir

class GradlePluginPluginFunctionalTest {

    @field:TempDir
    lateinit var projectDir: File

    private val buildFile by lazy { projectDir.resolve("build.gradle") }
    private val settingsFile by lazy { projectDir.resolve("settings.gradle") }
    private val depFile by lazy {projectDir.resolve("deps.txt")}
    // https://github.com/gradle/kotlin-dsl-samples/issues/1268
    @Test
    fun `can run task auto`() {
        // Set up the test build
        depFile.writeText("""[{
            "configurationName":"implementation"
            "name":"sad"
            "version":"1"
            }]
        """.trimMargin())
        settingsFile.writeText("")
        buildFile.writeText("""
            plugins {
                id('com.kroissant.dependencyFromFile')
                id('java')
            }
            DependencyFromFileConfig {
                dependencyFile=file('${projectDir.path}/deps.txt')
            }
        """.trimIndent())

        // Run the build
        val runner = GradleRunner.create()
        runner.forwardOutput()

        runner.withPluginClasspath()
        runner.withArguments("dependencyFromFile")
        runner.withProjectDir(projectDir)
        val result = runner.build()

        // Verify the result
        assertTrue(result.output.contains("data"))
    }
}