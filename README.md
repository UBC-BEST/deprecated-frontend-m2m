# 👋 the mobile and frontend repo for m2m. 
[![Android Actions Status](https://github.com/UBC-BEST/frontend-m2m/workflows/Android%20CI/badge.svg)](https://github.com/UBC-BEST/frontend-m2m/actions)

## Getting Started - General 

### Building the proejct
Enter the project and build: 

windows
```
cd app
gradlew build clean
```

macos 
```
cd app
gradle build
```

For more information you can look here: https://docs.gradle.org/current/userguide/command_line_interface.html

### Connecting to Firebase 
When you clone this project, you will see that there are two `google-services.json` files in the `app/android` folder and `app/shared` folder. You need to replace those with the real `google-services.json` file that you must download from Firebase Console.

Go to Firebase Console and login with the M2M gmail. Ask Lucy or Roshan for assistance. Make sure when you make commits you do not commit the real `google-services.json` file.

## More Info On Getting Started 
The project above is based on this [tutorial](https://play.kotlinlang.org/hands-on/Networking%20and%20Data%20Storage%20with%20Kotlin%20Multiplatfrom%20Mobile/09_Creating_the_iOS_application) with some modifications. 

If you have not already, please read the [docs](https://github.com/UBC-BEST/m2m-docs) first then come back here! 

For those interested in mobile development:
- [General App Architecture](https://developer.android.com/jetpack/guide)
- [PLEASE READ: Getting started with Kotlin MMP](https://kotlinlang.org/docs/mobile/create-first-app.html)

For those interested in Unity (game) development - we don't have to use Unity. Since we are short on time feel free to experiment with any game engine you would like to:
- [Setting up Unity](https://medium.com/@razvan_57516/how-to-embed-unity-3d-in-a-native-android-app-5d030673bbf4)
- [Guide to C#](https://learn.unity.com/course/unity-c-survival-guide?_ga=2.63986025.775820160.1603564067-1572617516.1603424551&signup=true)

# User Research/Design Process 
- check our slack channel for our UIUX designs!

# Frontend Development and Architecture 
We will try to follow the [MVC pattern](https://en.wikipedia.org/wiki/Model%E2%80%93view%E2%80%93controller), or Model-view-Controller pattern. 

## Why use Model-view-Controller?
- This design pattern divides business logic into 3 interconnected elements. 
- This limits the user from seeing the internal structure of the application and it also seperates components from each other in that when we make a change in one part of the code we don't have to go chasing for errors in other portions. 
- When executed correctly, if we were is make a change in the model, we would not have to make changes in the view or the controller. 
- This way our code is not prone to dependencies and is able to adopt to changes quickly and robustly. 

## Code Styles 
- If you want to read the official documentation on code style for Kotlin, click [here](https://kotlinlang.org/docs/reference/kotlin-doc.html).
- use tags whenever possibile!

### Class
- for each class, make a new file 
- at the top of the file, make a comment about what the class represents. An example looks like 
```

 /**
 * A group of *members*.
 *
 * This class has no useful logic; it's just a documentation example.
 *
 * @param T the type of a member in this group.
 * @property name the name of this group.
 * @constructor Creates an empty group.
 */
 ```

### Method 
For a method, your comment should focus on these three things:
- MODIFIES: what object being mutated or is anything being mutated at all?
- EFFECTS: what is this method doing? Is it returning anything? 
- REQUIRES: are there any restraints on the parameters?
Usually you can combine **MODIFIES** and **EFFECTS** into one sentence and if your method's parameters don't have any constraints then don't add obvious information. 
```
    /**
     * Adds a [member] to this group.
     * @return the new size of the group.
     */

     fun add(member: T): Int { ... }
