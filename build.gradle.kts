// Top-level build file where you can add configuration options common to all sub-projects/modules.
buildscript {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
    dependencies {
        classpath("com.github.megatronking.stringfog:gradle-plugin:4.0.1")
        classpath("com.github.megatronking.stringfog:xor:4.0.1")
    }
}
plugins {
    id("com.android.application") version "8.2.0-rc01" apply false
    id("org.jetbrains.kotlin.android") version "1.9.10" apply false
}