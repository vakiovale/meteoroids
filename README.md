# meteoroids
Clone of the classic Asteroids game.

### Running Meteoroids from command line (Linux - quick)

Run these Linux scripts from <code>Meteoroids/</code> folder:

<code>
./cleanbuild
</code>

...and then you can start the program with:

<code>
./run
</code>

Happy shooting!

### Running Meteoroids from command line

Clean and compile project with Maven:

<code>
mvn clean:clean
mvn compile
</code>

Set up MAVEN_OPTS environment variable:

<code>
windows: set MAVEN_OPTS="-Djava.library.path=target/natives"   
   unix: export MAVEN_OPTS=-Djava.library.path=target/natives
</code>

Run Meteoroids:

<code>mvn compile exec:java -Dexec.mainClass=meteoroids.Meteoroids.Main</code>

More information on setting up LWJGL project with Maven: http://wiki.lwjgl.org/wiki/Setting_Up_LWJGL_with_Maven
