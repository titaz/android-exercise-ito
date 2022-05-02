# Cooking app

In this exercise applicant should implement a new feature into an existing sample app.

App uses older type of architecture. If you feel more comfortable, you can use MVVM instead of MVP.


## Point of exercise

The point of the exercise is to figure out how you would perform in more real case scenarios. Working with the app will include

-   Using Git/ handling repository
-   Having app with one architecture, and figuring out how to move to new architecture (if you feel the need for it)
    -   This comes optional, as in real case scenario, **you / team** would be the one deciding, if you need to move new features with new architecture
-   Having older app assembled using RxJava (this is more common for older apps, newer ones uses coroutines)
-   Having bugs in the app, that you need to solve along the way
-   Having app assembled with some kind of dependency injection


## Sample application

A simple application with three screens.

-   Recipes screen - a screen that loads and displays a list of cooking recipes. Cooking recipes have three difficulties easy, normal and hard.
-   Recipe screen - a screen that displays recipe, photo and allows user to mark this recipe as prepared. When user marks recipe as prepared, he specifies if he was successful or not.
-   Attempted recipes screen - a screen that shows recipes that the user has already tried to prepare.


## Requirements

1.  Fix a crash in attempted recipes screen. **1 point**
2.  Implement table view sections for recipes based on their difficulty. **3 points**
3.  Implement cooking recommendations feature into the application (more info in below section) **5 points**


## Cooking recommendations feature

-   There should be a button in the application that shows a recommended recipe to the user when clicked;
-   Application should select a recommended recipe based on these criteria:
    -   If use has never tried any recipe before, app should recommend one of the low difficulty recipes;
    -   If user has prepared any recipes before, app should determine his skill level based on the last several tries and recommend a recipe that matches user&rsquo;s skill level.


## Some recommendations for the exercise

-   Do it as a task in a real project;
-   Use design patterns if you think you need to;
-   Unit test if you think you need to;
-   Write comments if you think you need to;
