# Arkanoid Game

A fun Java remake of the classic Arkanoid game. Control the paddle to bounce the ball and break all the blocks across different levels. Features smooth animations, keyboard controls, and level selection.

---
![Arkanoid GIF](https://github.com/user-attachments/assets/bcb6909c-f68f-41bc-bb01-1d5bb24b5f57)

## How to Run

### Prerequisites

- Java JDK installed (version 8 or above)
- [Apache Ant](https://ant.apache.org/) installed (optional, for build automation)
- `biuoop-1.4.jar` library file placed in the project root directory

### Running with Apache Ant

1. Open a terminal/command prompt in the project root directory.
2. Compile the project:
   ```bash
   ant compile
   ```
3. Run the game:
   ```bash
   ant run
   ```
4. To run specific levels, pass their numbers as arguments:
   ```bash
   ant run -Dargs="1 3"
   ```

### Running Manually (without Ant)

1. Compile the Java source files:
   ```bash
   javac -d bin -cp biuoop-1.4.jar src/*.java
   ```
3. Run the main class:
   ```bash
   java -cp bin;biuoop-1.4.jar Arkanoid
   ```
4. To run specific levels, add their numbers as arguments:
   ```bash
   java -cp bin;biuoop-1.4.jar Arkanoid 1 3
   ```
## Project Structure

- src/ - Java source code
- bin/ - Compiled class files
- biuoop-1.4.jar - External library dependency
- build.xml - Ant build script
