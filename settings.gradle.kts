rootProject.name = "blog"

listOf(
    "exam-retry",
    "exam-module",
    "data-business",
    "benchmark",
    "benchmark-def",
    "mqtt",
    "influx-v3",
).forEach {
    include(it)
    project(":$it").projectDir = File("$rootDir/modules/$it")
}