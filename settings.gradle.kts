rootProject.name = "blog"

listOf(
    "exam-retry",
    "exam-module",
    "data-business"
).forEach {
    include(it)
    project(":$it").projectDir = File("$rootDir/modules/$it")
}