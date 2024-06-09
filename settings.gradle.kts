rootProject.name = "blog"

listOf(
        "exam-retry",
        "exam-module",
        "data-business",
        "benchmark",
        "benchmark-def",
        "mqtt",
).forEach {
    include(it)
    project(":$it").projectDir = File("$rootDir/modules/$it")
}