# meteoroids
Clone of the classic Asteroids game.

* <a href="#whats-happening">What's happening?</a>
* <a href="#how-to-play">How to play</a>
* <a href="#running-meteoroids-from-command-line-linux---easy">Running Meteoroids (easy)</a>
* <a href="#running-meteoroids-from-command-line">Running Meteoroids (advanced)</a>
* <a href="#importing-project-to-eclipse-netbeans-etc-ide">Importing project to Eclipse, Netbeans, etc. (IDE)</a>
* <a href="#troubleshooting">Troubleshooting</a>
* <a href="#known-issues">Known issues</a>

## What's happening?

You must protect Earth from flying meteoroids! Destroy all the meteoroids before they hit the ground.

<img src="https://github.com/valtteripyyhtia/meteoroids/blob/master/doc/Meteoroids_Play_Level8.png" alt="Save Earth! - Meteoroids" width="800px" />

<img src="https://github.com/valtteripyyhtia/meteoroids/blob/master/doc/Meteoroids_Play_Level4.png" alt="Jupiter needs your help!" width="800px" />

## How to play

Try to keep planets alive as long as you can by shooting all the meteoroids.

Key       | Action
----------| -------------
UP        | Accelerate
DOWN      | Slow down
LEFT      | Steer left
RIGHT     | Steer right
SPACE     | Fire
LEFT CTRL | Change weapon
P         | Pause game
ESC       | Main menu

## Running Meteoroids from command line (Linux - easy)

This is the easiest way to build and run Meteoroids when you are in Linux environment.
This has been tested also on Windows when using GitBash (unix like shell).

Run these Linux scripts from <code>Meteoroids/</code> folder:

<code>
./libinstall
</code>

<code>
./cleanbuild
</code>

...and then you can start the program with:

<code>
./run
</code>

Happy shooting!

## Running Meteoroids from command line

1. Install slick-util library:
  <code>
  mvn install:install-file -Dfile=lib/slick-util.jar -DgroupId=slick-util -DartifactId=slick-util -Dversion=1.0 -Dpackaging=jar
  </code>

2. Clean and compile project with Maven:
  <code>
  mvn clean:clean && mvn compile && mvn nativedependencies:copy
  </code>

3. ***Sometimes needed when failing to copy native dependencies to the target/natives directory:***
  <code>mvn package</code>

4. Set up MAVEN_OPTS environment variable (use unix version ALSO in Windows when using GitBash in unix mode):
  
  <code>
  **windows:** set MAVEN_OPTS="-Djava.library.path=target/natives"
  </code>

  <code>
  **unix:** export MAVEN_OPTS=-Djava.library.path=target/natives
  </code>
    
5. Run Meteoroids:
  <code>mvn compile exec:java -Dexec.mainClass=meteoroids.Meteoroids.Main</code>

More information on setting up LWJGL project with Maven: http://wiki.lwjgl.org/wiki/Setting_Up_LWJGL_with_Maven

## Importing project to Eclipse, Netbeans, etc. (IDE)

Use IDE's own import Maven project tool and import the Meteoroids folder. Add <code>-Djava.library.path=target/natives</code> to the project's VM options.

**Eclipse:**
"Run" -> "Run configurations..." -> "Java Application" -> "Main" -> "Arguments" -> "VM arguments:"

**NetBeans:**
Project Properties -> "Run" -> "VM Options:"

## Troubleshooting

Problems starting the application?

Try this:

<code>
mvn clean:clean
&&
mvn compile
&&
mvn nativedependencies:copy
&&
mvn package
&&
export MAVEN_OPTS=-Djava.library.path=target/natives
&&
mvn compile exec:java -Dexec.mainClass=meteoroids.Meteoroids.Main
</code>

After this, the program should start. Hope this works!

## Known issues

- graphics are still in testing phase and some graphical glitches appear
  - some objects are drawn wrong when OpenGL version > 3.0
- <code>mvn package</code> does not include native libraries
- tests run with <code>mvn test</code> doesn't work if native libraries are needed (works well when using IDE)
