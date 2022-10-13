# Android MVVM Base Clean Architecture Application using Android Jetpack Components

# Highlights

1. MVVM Architectural pattern
2. Unit test demonstration using JUnit
3. UI test demonstration using Espresso
4. Gradle scripts code coverage

The application has been built with **online support**. It has been designed using **Android
Architecture components**.

The whole application is built based on the MVVM architectural pattern.

# Application Architecture

![alt text](https://cdn2.scalablepath.com/_next/image?url=https%3A%2F%2Fcdn-blog.scalablepath.com%2Fuploads%2F2021%2F12%2Fmvvm-reactive-architecture-1024x937.png&w=1200&q=75)



The main advantage of using MVVM, there is no two way dependency between ViewModel and Model unlike
MVP. Here the view can observe the datachanges in the viewModel as we are using LiveData which is
lifecycle aware. The viewModel to view communication is achieved through observer pattern (basically
observing the state changes of the data in the viewModel and UI updated automatically on configuration changes).

# Screenshots

<img src="/Screenshots/Tab-3.png" width="400" height="615" alt="Tab-Home"/>
<img src="/Screenshots/Tab-5.png" width="400" height="615" alt="Tab-Details"/>
<img src="/Screenshots/Mobile-1.png" width="346" height="615" alt="Mobile-Home"/>
<img src="/Screenshots/Mobile-2.png" width="346" height="615" alt="Mobile-Details"/>

# Programming Practices Followed

a) Android Architectural Components <br/>
b) Hilt for Dependency Injection <br/>
c) MVVM <br/>
d) Retrofit with Okhttp <br/>
f) JUnit for Unit testing and Espresso for UI testing <br/>


# How to build ?

First make sure you have to JDK install on your machine to build <br/>

Open terminal and type the below command to generate debug build <br/>

```  ./gradlew assembleDebug ```

Open terminal and type the below command to generate release build <br/>

```  ./gradlew assembleRelease ```


# How to generate code coverage report ?

Open terminal and type the following command

``` ./gradlew clean jacocoTestReport```

The coverage report will be generated on the following path.

``` app/build/reports ```
