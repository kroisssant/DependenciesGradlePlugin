# Dependency From File

This simple gradle plugins allows the user to create a file with all depedendencies for a project similar to the requierments.txt used by python.

# Getting started 
Clone this repository with this command
```txt
git clone https://github
```
After that enter the project file
```txt
cd 
```
After that you can publish the plugin to your mavelLocal repository with the following command
```txt
./gradlew publishToMavenLocal
```
Now that the plugin is added to your local maven repository we can use it in another project.\
This can be done by adding the following lines to the build.gradle and to the settings.gradle
```
settings.gradle

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
```
build.gradle

plugins {
    id("com.kroissant.dependencyFromFile") version "1.0"
}
```
This is all you need to do to use this plugin. \
After you sync the gradle you will notice a new file
**deps.txt**
In this file you can add your dependencies.
This can be done using one of two file types. \
### **JSON**
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
### **PROPERTIES**
```properties
name: configurationName group version
```

## Extension
This plugin has an extention where you can change the default path of the deps.txt file \
Also you can determine if the file has the propertie format or the json format.\
This are optional.