# Frontend and Mobile
[![Andriod Actions Status](https://github.com/UBC-BEST/frontend-m2m/workflows/Android%20CI/badge.svg)](https://github.com/UBC-BEST/frontend-m2m/actions)

If you have not already, please read the [docs](https://github.com/UBC-BEST/m2m-docs) first then come back here! 
- [General App Architecture](https://developer.android.com/jetpack/guide)
- [Setting up Unity](https://medium.com/@razvan_57516/how-to-embed-unity-3d-in-a-native-android-app-5d030673bbf4)
- [PLEASE READ: Getting started with Kotlin MMP](https://kotlinlang.org/docs/mobile/create-first-app.html)
- [Guide to C#](https://learn.unity.com/course/unity-c-survival-guide?_ga=2.63986025.775820160.1603564067-1572617516.1603424551&signup=true)

# User Research/Design Process 
[Wireframes and Prototypes](https://www.justinmind.com/blog/whats-the-difference-between-wireframes-and-prototypes/#:~:text=To%20break%20it%20down%2C%20website,more%20visual%20detail%20and%20interaction.&text=Read%20on%20for%20more%20on,web%20or%20mobile%20design%20process.)
- In the coming weeks, our designer will produce low-fi skethces, wireframe and prototype. We will iterate on their original designs.

# Frontend Development and Architecture 
![mvc](/photos/mvc.png "mvc")
We will be following the [MVC pattern](https://en.wikipedia.org/wiki/Model%E2%80%93view%E2%80%93controller), or Model-view-Controller pattern. 

## Why use Model-view-Controller?
- This design pattern divides business logic into 3 interconnected elements. 
- This limits the user from seeing the internal structure of the application and it also seperates components from each other in that when we make a change in one part of the code we don't have to go chasing for errors in other portions. 
- When executed correctly, if we were is make a change in the model, we would not have to make changes in the view or the controller. 
- This way our code is not prone to dependencies and is able to adopt to changes quickly and robustly. 

### Model
The model represents the data of the application and is independent of the user interface. It has **DIRECT** accesses to the data. The model deals with actions such as acssessing data from the database or changing the data in some way. So far, our model has these classes: 

<table>
	<tbody>
		<tr>
			<td>Class</td>
			<td>Attributes</td>
			<td>Associated Methods</td>
		</tr>
		<tr>
			<td rowspan="2">User</td>
			<td>personal data such as name</td>
			<td>getUserData(), updateUserData()</td>
		</tr>
		<tr>
			<td>uid/token/credential</td>
			<td>login(), logout(), signUp()</td>
		</tr>
		<tr>
			<td rowspan="2">Game</td>
			<td>score</td>
			<td>getScore(), generateScore()</td>
		</tr>
		<tr>
			<td>data</td>
			<td>updateData(), getData()</td>
		</tr>
		<tr>
			<td rowspan="2">Sensor</td>
			<td>data</td>
			<td>updateData(), getData()</td>
		</tr>
		<tr>
			<td></td>
			<td></td>
		</tr>
		<tr>
			<td rowspan="2">Networking</td>
			<td>data</td>
			<td>updateData(), syncData()</td>
		</tr>
		<tr>
			<td></td>
			<td></td>
		</tr>
	</tbody>
</table>

### More info on each class
#### User
This class is mainly going to be used for authenticating the user into the app.

#### Game
Since we will be creating many games, we will be extending our games to this super class. The super Game class will be generic enough to represent all games but specific enough to still be a class of its own. 

#### Sensor
This class will be dealing with the therapy glove's data. Right now we don't have much information on the therapy glove so don't worry about this class until we have more information.

#### Networking
This class will deal with connecting to the server or backend, mainly dealing with data processing. Networking could also fit into controller but we will leave it in model for now. 

These are not our finalized classes or methods. You may be wondering why these classes are super vague. We want to be able to take advantage of inheritance and by making our classes vague enough so that we can extend other classses, we can reduce the amount of code we need to write. 

### View 
This can simply be thought of as what the user sees, or the user interface. All this component does is display information. There should be almost no logic in dealing with data processing or manipulation. Our screens will be the views. 

### Controller 
When the user interacts with the user interface, the controller either directs these actions to the view or the model. For instance, if the user is playing a game and clicks on a button, the user data will be sent to the model and the change in the appearance of the button will be sent to the view. 

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
```
