## issues i came across:
- sqldelight is causing errors 
```
sqldelight {
    database("AppDatabase") {
        packageName = "com.jetbrains.handson.kmm.shared.cache"
    }
}
```
- solution: add 
```
if (requested.id.id == "com.squareup.sqldelight") {
    useModule("com.squareup.sqldelight:gradle-plugin:1.4.3")
}
``` 
to settings.gradle.kts
