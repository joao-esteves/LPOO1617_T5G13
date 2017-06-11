# LPOO1617_T5G13


## Build
Open project in Android Studio with Gradle.


## Game Installation
In desktop, execute desktop-1.0.jar.

## Development Documentation


### UML

![](https://github.com/Joao611/LPOO1617_T5G13/blob/master/Diagrams/ClassDiagramGIMP.png)


### Design Patterns

#### Singleton
For single instances of each Screen: GameView and MainMenu.

#### Factory
PieceFactory. To randomly pick one of the Piece types, re-using each.


### Major Difficulties


### Lessons Learned


### Time Spent
Around 120 hours.


## User Manual

Game opens in the Main Menu:

<img src="https://github.com/Joao611/LPOO1617_T5G13/blob/master/Pictures/Main_Menu.png" width="300">

+ "Play" either continues an ongoing game or starts a new one if there isn't one running already.
+ "New Game" creates a new game.
+ "Login with FB" opens the browser to login to Facebook.

The game starts as such:

<img src="https://github.com/Joao611/LPOO1617_T5G13/blob/master/Pictures/Game_Start.png" width="300">

+ "Menu" goes back to the Main Menu without quitting the game.

When the game is lost, by laying blocks to the top, a Dialog appears with the option to restart the game>

<img src="https://github.com/Joao611/LPOO1617_T5G13/blob/master/Pictures/Screenshot_2017-06-11-23-19-46.png" width="300">

### Controls

+ Click or touch the left half of the screen to move the piece to the left, click or touch the right half to move it right.
+ On phone, rotate the phone left or right to rotate the piece and shake it forwards and backwards to drop the piece harder.

### Score

+ When a line is completed and cleared, the top left counter is incremented.
