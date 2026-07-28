# The-Battle-Cats-Unite-Editor
A save editor for the switch game "The Battle Cats Unite!".

You need to have Java installed on your PC in order to run the program. Currently you can edit the values ​​of the Cat Food, Rare tickets, Cat Tickets, Platinum Tickets, XP, Catfruits and Seeds and also unlock all the Units and complete Enemy Guide. 

I tested it in the 1.0.7 Japanese version and in the 1.0.0  Southeast Asia version (Catfruits, Seeds, Units and Enemies unlock only tested in 1.0.7 Japanese). 1.0.8 also works. 

NOTE: if you create a save in the 1.0.0 Japanese version Platinum Tickets won't work with the editor, even when you update the game.

MAKE SURE TO CREATE A BACKUP BEFORE CHANGING ANYTHING.

## Building

The project requires JDK 21 or newer. Make sure `JAVA_HOME` points to that JDK
before running Maven.

Build the Windows standalone JAR:

```powershell
mvn clean package
```

The result is:

```text
target/The-Battle-Cats-Unite-Editor-V1.2.1-standalone-windows.jar
```

This JAR includes JavaFX and its Windows native libraries. It can be run with a
standard Java 21+ runtime that does not include JavaFX:

```powershell
java -jar target/The-Battle-Cats-Unite-Editor-V1.2.1-standalone-windows.jar
```

The artifact is Windows-specific because JavaFX includes native,
platform-specific libraries.
