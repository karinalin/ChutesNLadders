# ChutesNLadders

This project was created by Karina Lin ('19), Olivia Saloman ('19), and Teresa Li ('19) as the final project for CS 230, Data Structures.  It can be used to simulate a one-player game of Chutes n' Ladders with an modular educational component, where the player must answer a question in order to move the number of spaces rolled.  The questions and answers are pre-loaded from a CSV file and, therefore, are modular.  The program utilizes a graph and hash table for maximum efficiency.

To user this file, clone the repository, then compile and run "CnLGUI.java."  This will start your game.

At the start of every, a user can choose their name and gamepiece.  At the beginning of each turn, a player is to click spin, then answer the question correctly, then watch their piece move up and down the game board.  Incorrectly answering a question will lead to no piece movement and a "skipped" turn.

To change the question set, create a CSV file by following the format of "additionTables.csv" and name your new csv file "additionTables.csv," replacing the old one.
