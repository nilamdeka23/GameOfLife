# Game Of Life
A native Android Game inspired by "The Game of Life".
###### One can download a working signed apk of this app from [here](https://github.com/nilamdeka23/GameOfLife/blob/master/app/app-release.apk?raw=true)

### What is "The Game of Life"?
The Game of Life, also known simply as Life, is a cellular automaton devised by the British mathematician John Horton Conway.
The "game" originally is a zero-player game, meaning that its evolution is determined by its initial state, requiring no further input. 
One interacts with the Game of Life by creating an initial configuration and observing how it evolves.


### How does this game differ?
It is not a zero-player game, as the player has more than the freedom of creating an intial configuration as the player may choose to interact with
the game board using touch/tap on any evolution stage and thus affect the outcome of the next subsequent evolution.


### What are the evolution rules?
1. Any live cell with fewer than two live neighbours dies, as if caused by underpopulation.
2. Any live cell with two or three live neighbours lives on to the next generation.
3. Any live cell with more than three live neighbours dies, as if by overpopulation.
4. Any dead cell with exactly three live neighbours becomes a live cell, as if by reproduction.


### How to play this game?
1. The player is presented with a grid of 14 by 14 rectangular cells.
2. Player can touch/tap on each cell to render them live(solid red circle) or dead(empty cell).
3. Player can click "Next" button to generate the next evolution for the input cell population.
4. Subsequently, player can modify the next generation or continue clicking next to generate higher degrees of evolution.
5. The player can click on the "Reset" button anytime to clear GameBoard to reset the game.


### Implementation Details
To maintain a good quality and maintainibility of code, I have used the classical MVC Android Achitecture.

I created three main packages: Model, View and Controller, segregating the view and game logic and keeping the controller("The glue") lean.
Custom View using Android 2D graphics packagages to implement the GameBoard View.


### Sample GamePlay
![Alt Text](https://github.com/nilamdeka23/GameOfLife/blob/master/Screenshots/GameOfLife.gif)
##### I tried to follow the Material Design guidelines in the available time.

