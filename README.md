# OwO Java

Would you like to teach your imaginary catgirl waifu how to code,
but she has trouble with understanding programming languages?
Then you should definitely try this compiler plugin for Java. 

> **Note**  
> This compiler plugin is only compatible with *javac*. Other compilers, such as *ecj*, will not work. 
> While this plugin should work correctly with any OpenJDK version since 11, it has only been tested with OpenJDK 17.
 
## Building

First, make sure you have OpenJDK 11 or newer (or any OpenJDK fork).

In order to build this compiler plugin, just run this command  
`./gradlew build`  (or `gradlew build` on Windows)  
The compiled plugin can be then found in `build/libs/`

## Usage
In order to use the compiler plugin, use this command:  
`javac -J--add-exports=jdk.compiler/com.sun.tools.javac.api=ALL-UNNAMED -J--add-exports=jdk.compiler/com.sun.tools.javac.parser=ALL-UNNAMED -J--add-exports=jdk.compiler/com.sun.tools.javac.util=ALL-UNNAMED -cp owojava.jar -Xplugin:OwoPlugin Example.java`

The exports are currently required.

## How does it work?

This compiler plugin manipulates with `com.sun.tools.javac.parser.Tokens` and `com.sun.tools.javac.util.Names`. 
By manipulating with these classes, OwO Java Plugin is able to replace the original keywords with more OwO variants. 
In the following table you can see the old and the new keywords (keywords which are not mentioned stayed the same):

| Original Keyword | OwO keyword  |     | Original Keyword | OwO keyword     |
|------------------|--------------|-----|------------------|-----------------|
| `abstract`       | `abstwact`   |     | `strictfp`       | `stwictfp`      |
| `boolean`        | `boowean`    |     | `super`          | `supew`         |
| `break`          | `bweak`      |     | `synchronized`   | `synchwonyized` |
| `char`           | `chaw`       |     | `throw`          | `thwow`         |
| `class`          | `cwass`      |     | `throws`         | `thwows`        |
| `continue`       | `continyue`  |     | `transient`      | `twansient`     |
| `default`        | `defauwt`    |     | `try`            | `twy`           |
| `double`         | `doubwe`     |     | `volatile`       | `vowatiwe`      |
| `else`           | `ewse`       |     | `while`          | `whiwe`         |
| `enum`           | `enyum`      |     | `true`           | `twue`          |
| `final`          | `finyaw`     |     | `false`          | `fawse`         |
| `finally`        | `finyawwy`   |     | `null`           | `nyuww`         |
| `float`          | `fwoat`      |     | `var`            | `vaw`           |
| `for`            | `fow`        |     | `exports`        | `expowts`       |
| `implements`     | `impwements` |     | `module`         | `moduwe`        |
| `import`         | `impowt`     |     | `provides`       | `pwovides`      |
| `interface`      | `intewface`  |     | `requires`       | `wequiwes`      |
| `long`           | `wong`       |     | `transitive`     | `twansitive`    |
| `native`         | `nyative`    |     | `yield`          | `yiewd`         |
| `new`            | `nyew`       |     | `record`         | `wecowd`        |
| `private`        | `pwivate`    |     | `sealed`         | `seawed`        |
| `orotected`      | `pwotected`  |     | `permits`        | `pewmits`       |
| `public`         | `pubwic`     |     | `non`            | `nyon`          |
| `return`         | `wetuwn`     |     | `short`          | `showt`         |

## Future Plans

* [ ] Hack the module system, so we do not have to use `--add-exports`
* [ ] Manipulate with imports and name resolution so the standard library can be OwO'ified as well.

## License

This project is licensed under the [MIT License](LICENSE)

























