package com.kroissant

import org.gradle.api.provider.Property
import org.gradle.internal.enterprise.test.FileProperty
import java.io.File

open class GradleExtensions {
     var dependencyFile: File? = null
     var fileType: String = "auto"

}