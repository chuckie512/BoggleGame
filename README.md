# BoggleGame
A simple boggle game, modified from a class project.


This is run simply by 
java MyBoggle [boardName]

This game requires a board1.txt & a dictionary.txt to be in the same directory.

The dictionary.txt contains all the valid words for the game.
board1.txt is a text file that contains the 16 letters that will be the board.

The default game board is board1.txt but can be specified by comandline arguments

This game was made to used a DLB Trie to store the dictionary, speeding up search times drasticly from an array.

Compile this program by 
javac MyBoggle.java
