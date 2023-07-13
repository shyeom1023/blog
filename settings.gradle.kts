rootProject.name = "blog"

listOf(
    "exam-retry",
    "exam-module",
).forEach {
    include(it)
    project(":$it").projectDir = File("$rootDir/modules/$it")
}