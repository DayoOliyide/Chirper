# Chirper

[![CI Status](http://img.shields.io/travis/DayoOliyide/Chirper.svg?style=flat)](https://travis-ci.org/DayoOliyide/Chirper)

A simple console based social networking application.

By simple, it just runs locally and multiple/different users use a single shared console.

## Pre-requisite
You need Java 8 installed on your system

## Installing

- Git clone this project i.e ``` git clone https://github.com/DayoOliyide/Chirper.git ```
- Once cloned, at the root of the project build the application by running the following command

   For Linux users run
```
./gradlew jar
```

   For Windows users run
```
gradlew.bat jar
```
- You should now end up with a jar in the ./builds/lib directory

## Usage
Users are created automatically, so you don't need to create them.
### To Start
Type  ``` java -jar build/libs/Chirper-1.0-SNAPSHOT.jar ``` to bring up the console

*Just hit the return key to exit*

### To Post
`[username] -> [message]`

>\> Batman -> The Bat signal is on!

>\> Robin -> Holy Armadillo Batman! What do we do??!


### To Read a user's Timeline (posts)
`[username]`

>\> Robin

>Holy Armadillo Batman! What do we do??! (5 seconds ago)

>\> Batman

>The Bat signal is on! (10 seconds ago)


### To follow (subscribe) to another user's posts
`[follower-username] follows [followee-username]`

>\> Alfred follows Batman

>\> Alfred follows Robin

>\> Alfred -> Another long night I take it Master Wayne


### To read a combined timeline of the user and the users subscriptions
`[username] wall`

>\> Alfred wall

>Alfred - Another long night I take it Master Wayne (45 seconds ago)

>Robin - Holy Armadillo Batman! What do we do??! (30 seconds ago)

>Batman - The Bat signal is on! (25 seconds ago)

## License
Do whatever you want