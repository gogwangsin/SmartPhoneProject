pluginManagement {
    repositories {
        google {
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
            }
        }
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "Stealth"
include(":app")
include("a2dg")
// Stealth 안드로이드 프로젝트에서 app 모듈 뿐 아니라 a2dg 모듈도 나오게 한다.
// 이걸 하기 전에는 a2dg 모듈이 안나오므로 a2dg/build.gradle.kts 파일을 수정할 수 없는데
// Project View를 Android -> Project로 하면 볼 수 있다
 