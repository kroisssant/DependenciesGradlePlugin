# Dependency From File

This simple gradle plugin allows the user to create a file with all dependencies for a project similar to the requirements.txt used by python.

# Getting started 
1. Clone this repository:
```txt
git clone https://github.com/kroisssant/DependenciesGradlePlugin
```
2. Enter the project directory:
```txt
cd ./DependenciesGradlePlugin
```
3. Publish the plugin to your mavelLocal repository:
```txt
./gradlew publishToMavenLocal
```
Now that the plugin is added to your local maven repository we can use it in another project.\
To do that you need to:
1. Add this to your `settings.gradle`:
```
pluginManagement {
    repositories {
        mavenLocal()
        gradlePluginPortal()
    }
    plugins {
        id("com.kroissant.dependencyFromFile") version "1.0"
    }

}
```
2. Add this to your `build.gradle`
```
plugins {
    id("com.kroissant.dependencyFromFile") version "1.0"
}
```
This is all you need to do to use this plugin. \
After you sync the gradle you will notice a new file `deps.txt`.\
In this file you can add your dependencies.
## Supported formats
### **JSON**
#### Format:
```json
[
  [
    {
      "configurationName":"This is the type of dependency",
      "group":"<Group of your dependency>",
      "name" : "<Dependency name>",
      "version" : "<Dependency version>"
    }
  ]
  
]
```
#### Example:
```json
[
    {
        "configurationName":"implementation",
        "group":"io.github.ollama4j",
        "name" : "ollama4j",
        "version" : "1.0.79"
    }
]
```

### **PROPERTIES**
#### Format:
```properties
name: configurationName group version
```
#### Example:
```properties
ollama4j: implementation io.github.ollama4j 1.0.79
```

## Extension
The plugin includes an extension that allows you to:
1. Change the path to the dependency file and name
2. Set the file format you will use (by default the program tries json and then properties)

These settings are optional and by default the dependency file will be named `deps.txt` and will live in the project directory.

### How to use
```kotlin
DependencyFromFileConfig {
    dependencyFile=file("Path/to/file")
    fileType=<"json"/"properties">
}
```