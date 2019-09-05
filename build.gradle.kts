import java.net.URI

allprojects {
    repositories {
        google()
        jcenter()

        maven { url = URI("https://dl.bintray.com/icerockdev/moko") }
    }
}

tasks.register("clean", Delete::class).configure {
    delete(rootProject.buildDir)
}
