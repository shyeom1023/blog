rootProject.name = "blog"

listOf(
        "exam-retry",
        "exam-module",
        "data-business",
        "benchmark",
        "benchmark-def"
).forEach {
    include(it)
    project(":$it").projectDir = File("$rootDir/modules/$it")
}