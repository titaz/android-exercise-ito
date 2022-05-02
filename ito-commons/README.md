# ITO Commons

A library with the mostly used common functionality that *might* be used within the app. Contains various snippets, android context related stuff. 

Contributions and MR's are **very welcome**!

## How to install
There are more ways to include the library in the project. Here are a couple of them.

### Including as a separate module
- Why? - Easy to edit, contribute and add functionality for other devs. (Please refer to #contributing section)
1. Register the project as a submodule. Run a command - `git submodule add git@gitlab.ito.lt:ito-android/ito-commons.git`
2. Register the app within `settings.gradle` in your project:
```
include ':app',
        ':ito-commons'
project(":ito-commons").projectDir = file("ito-commons/lib")
```
3. Include it into your project by updating `app/build.gradle`:
```
dependencies {
  ...
    implementation project(":ito-commons")
  ...
}
```

4. Include `versions.gradle` from the sample app - `ito-commons/versions.gradle` to your root project and provide the correct versions your project is using
5. Update the root `build.gradle` class paths to make everything compatible. It should look similar like this:

```
buildscript {
    apply from: "versions.gradle"
    repositories {
        google()
        jcenter()
        mavenCentral()
    }
    dependencies {
        classpath "com.android.tools.build:gradle:$versions.androidGradlePlugin"
        classpath "org.jetbrains.kotlin:kotlin-gradle-plugin:$versions.kotlin"
    }
}
```

6. Sync everything withing IDE
7. Library is ready for usage!

### Troubleshooting

- Might be a case when integrating, you'll get this error: `Plugin with id 'kotlin-android-extensions' not found.`
  - To fix this, you need to add `kotlin` classpath in your root `build.gradle` file: 

```
classpath "org.jetbrains.kotlin:kotlin-gradle-plugin:$kotlin_version"
```

### Including as a library in a jar
- Why? - Just wanna use the library, harder to contribute to it back.

/// Build is not available yet ///

## Functionality summary
- Closing keyboard
- Returning device screen

## Contributing
Whenever you find something that you feel like it could bring use to your fellow devs, all MR's or requests are welcome here. 

What to expect:
- Common functionality / snippets, like string formatting, minor android thingies that are not available within the basic package or are not easy enough to be used. 

The library should not include: 
- Masive views (or probably any views in that sense, as this feels as view's should have their own libraries)
- Functionality that is already available in common android / Kotlin API

## How to contribute
1. If you have the app as a separate module, your can edit the library in place with the app you're building
2. Go to `ito-commons` directory
3. Now under the `ito-commons`, git will recognize it as a separate git repository, so for contribution, checkout a new branch - `git checkout -b feature/new_cool_feature`
4. Commit
5. Push new branch to `origin`
6. Make a MR for the contribution to be reviewed
