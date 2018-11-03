/**
 * By: Marcos Gil
 * 
 * This is a program that simulates Tic Tac Toe
 * 
 * Using DrJava: Hit the compile button to compile, then in the 
 * Interactions window, type
 * 
 *  java TicTacToe
 * 
 * Using cmd.exe (windows) or terminal (mac)
 * Compilation: javac TicTacToe.java
 * Usage      : java TicTacToe
 *
 */

import java.util.Scanner;
import java.util.Random;

public class TicTacToe{
  
  public static void main(String[] args){
    
    int whoseTurn = initGame();
    String[][] boardContents = new String[3][3];
    boardContents = createBoard(boardContents);

    while (checkForWinner(boardContents) == false){

      if (boardFull(boardContents) == true){
        
        drawBoard(boardContents);
        System.out.println("The game has ended in a draw!");
        return;
      }

      if (whoseTurn == 1){
        
        drawBoard(boardContents);
        putPosition(getPosition(), whoseTurn, boardContents);
        whoseTurn = 0;

      } else {

        System.out.println("The Computers move is:");
        putPosition(getPositionComputer(), whoseTurn, boardContents);
        whoseTurn = 1;
      }
    }

    indicateWinner(boardContents);
  }

  /* Purpose: Deciding who goes first and explaining how to play to the user
     In: N/A
     In/Out: N/A
     Out: int which indicates who gets the first turn
  */
  public static int initGame(){

    Random startingNum = new Random();
    int startingPlayer = startingNum.nextInt(2);

    System.out.println("When it is your turn, you will be asked for a number from 1-9, represnting these positions on the board");
    System.out.println(1 + "|" + 2 + "|" + 3);
    System.out.println("-+-+-");
    System.out.println(4 + "|" + 5 + "|" + 6);
    System.out.println("-+-+-");
    System.out.println(7 + "|" + 9 + "|" + 9);

    if (startingPlayer == 1){

      System.out.println("You will play as 'X' and the Computer will play as 'O'. You have been randomly selected to go first");
      return 1;
    
    } else {
      
      System.out.println("You will play as 'X' and the Computer will play as 'O'. The Computer has been randomly selected to go first");
      return 0;
    }
  }
  
  /* Purpose: Filling the 2-dimensional array that is passed in with spaces so that it looks clean when printing the board
     In: N/A
     In/Out: board
     Out: N/A
  */
  public static String[][] createBoard(String board[][]){
    
    /* Filling 2 dimenional array of strings with spaces and returning it */
    for (int row = 0; row < 3; row++){
      
      for (int column = 0; column < 3; column++){
        
        board[row][column] = " ";
      }
    }
    
    return board;
  }
  
  /* Purpose: Printing contents of the board
     In: board
     In/Out: N/A
     Out: N/A
  */
  public static void drawBoard(String board[][]){
    
    /* Draws the board in the console */
    System.out.println(board[0][0] + "|" + board[0][1] + "|" + board[0][2]);
    System.out.println("-+-+-");
    System.out.println(board[1][0] + "|" + board[1][1] + "|" + board[1][2]);
    System.out.println("-+-+-");
    System.out.println(board[2][0] + "|" + board[2][1] + "|" + board[2][2]);
  }
  
  /* Purpose: Get the users next desired move on the board and returning that location in terms of the values for our 2 dimensional array
     In: N/A
     In/Out: N/A
     Out: string
  */
  public static String getPosition(){

    Scanner userInput = new Scanner(System.in);
    System.out.println("It is now your turn. Please enter a number from 1-9 for your move on the board. ");
    int userIntInput = userInput.nextInt();

    while (userIntInput > 9 || userIntInput < 1){

      System.out.println("That is not a valid input represnting a spot on the board. Please enter a number from 1-9.");
      userIntInput = userInput.nextInt();
    }
    
    if (userIntInput == 1){ return "0,0"; }
    else if (userIntInput == 2){ return "0,1"; }
    else if (userIntInput == 3){ return "0,2"; }
    else if (userIntInput == 4){ return "1,0"; }
    else if (userIntInput == 5){ return "1,1"; } 
    else if (userIntInput == 6){ return "1,2"; }
    else if (userIntInput == 7){ return "2,0"; }
    else if (userIntInput == 8){ return "2,1"; }
    else { return "2,2"; }
  }
  
  /* Purpose: Marking the users/computers move on the board
     In: position, decidePlayer
     In/Out: board
     Out: N/A
  */
  public static String[][] putPosition(String position, int decidePlayer, String board[][]){

    boolean valid = isMoveValid(position, board);

    while (!valid){
      
      if (decidePlayer == 1){
        
        System.out.println("That spot has already been played on. Please choose another spot.");
        position = getPosition();
      
      } else {
        
        position = getPositionComputer();
      }

      valid = isMoveValid(position, board);
    }
    
    if (decidePlayer == 1){
      
      if (position == "0,0"){ board[0][0] = "X"; }
      else if (position == "0,1"){ board[0][1] = "X"; }
      else if (position == "0,2"){ board[0][2] = "X"; }
      else if (position == "1,0"){ board[1][0] = "X"; }
      else if (position == "1,1"){ board[1][1] = "X"; }
      else if (position == "1,2"){ board[1][2] = "X"; }
      else if (position == "2,0"){ board[2][0] = "X"; }
      else if (position == "2,1"){ board[2][1] = "X"; }
      else if (position == "2,2"){ board[2][2] = "X"; }
    }
    
    if (decidePlayer == 0){
      
      if (position == "0,0"){ board[0][0] = "O"; }
      else if (position == "0,1"){ board[0][1] = "O"; }
      else if (position == "0,2"){ board[0][2] = "O"; }
      else if (position == "1,0"){ board[1][0] = "O"; }
      else if (position == "1,1"){ board[1][1] = "O"; }
      else if (position == "1,2"){ board[1][2] = "O"; }
      else if (position == "2,0"){ board[2][0] = "O"; }
      else if (position == "2,1"){ board[2][1] = "O"; }
      else if (position == "2,2"){ board[2][2] = "O"; }
    }
    
    return board;
  }
  
  /* Purpose: Randoming a move for the computer and returning it's location in the 2 dimensional array
     In: N/A
     In/Out: N/A
     Out: string
  */
  public static String getPositionComputer(){
    
    Random computerPosition = new Random();
    int computerPositionNumber = computerPosition.nextInt(9) + 1;

    if (computerPositionNumber == 1){ return "0,0"; }
    else if (computerPositionNumber == 2){ return "0,1"; }
    else if (computerPositionNumber == 3){ return "0,2"; }
    else if (computerPositionNumber == 4){ return "1,0"; }
    else if (computerPositionNumber == 5){ return "1,1"; } 
    else if (computerPositionNumber == 6){ return "1,2"; }
    else if (computerPositionNumber == 7){ return "2,0"; }
    else if (computerPositionNumber == 8){ return "2,1"; }
    else { return "2,2"; }
  }
  
  /* Purpose: Deciding if the desired move is valid given the board in its current state
     In: potentialMove, board
     In/Out: N/A
     Out: boolean
  */
  public static boolean isMoveValid(String potentialMove, String board[][]){
    
    if (potentialMove == "0,0"){
      if (board[0][0] == " "){ return true; }
    }
    
    if (potentialMove == "0,1"){
      if (board[0][1] == " "){ return true; }
    }
    
    if (potentialMove == "0,2"){
      if (board[0][2] == " "){ return true; }
    }
    
    if (potentialMove == "1,0"){
      if (board[1][0] == " "){ return true; }
    }
    
    if (potentialMove == "1,1"){
      if (board[1][1] == " "){ return true; }
    }
    
    if (potentialMove == "1,2"){
      if (board[1][2] == " "){ return true; }
    }
    
    
    if (potentialMove == "2,0"){
      if (board[2][0] == " "){ return true; }
    }
    
    if (potentialMove == "2,1"){
      if (board[2][1] == " "){ return true; }
    }
    
    if (potentialMove == "2,2"){
      if (board[2][2] == " "){ return true; }
    }

    return false;
  }

  /* Purpose: Checking all combinations of winning lines to see if someone has one the game
     In: board
     In/Out: N/A
     Out: boolean
  */
  public static boolean checkForWinner(String board[][]){
    
    if (board[0][0] == board[0][1] && board[0][0] == board [0][2] && (board[0][0] == "X" || board[0][0] == "O")){
      return true;
    }
    else if (board[1][0] == board[1][1] && board[1][0] == board [1][2] && (board[1][0] == "X" || board[1][0] == "O")){
       return true;
    }
    else if (board[2][0] == board[2][1] && board[2][0] == board [2][2] && (board[2][0] == "X" || board[2][0] == "O")){
       return true;
    }
    else if (board[0][0] == board[1][0] && board[0][0] == board [2][0] && (board[0][0] == "X" || board[0][0] == "O")){
       return true;
    }
    else if (board[0][1] == board[1][1] && board[0][1] == board [2][1] && (board[0][1] == "X" || board[0][1] == "O")){
       return true;
    }
    else if (board[0][2] == board[1][2] && board[0][2] == board [2][2] && (board[0][2] == "X" || board[0][2] == "O")){
       return true;
    }
    else if (board[0][0] == board[1][1] && board[0][0] == board [2][2] && (board[0][0] == "X" || board[0][0] == "O")){
       return true;
    }
     else if (board[0][2] == board[1][1] && board[0][2] == board [2][0] && (board[0][2] == "X" || board[0][2] == "O")){
       return true;
    }
    else{
      return false;
    }
  }
  
  /* Purpose: Given there is a winner, we use this to see who won and return the corresponding letter 'X' or 'O'
     In: board
     In/Out: N/A
     Out: string
  */
  public static String checkWhoWon(String board[][]){
    
    if (board[0][0] == board[0][1] && board[0][0] == board [0][2] && (board[0][0] == "X" || board[0][0] == "O")){
      if (board[0][0] == "X"){ return "X"; }
      else { return "O"; }
    }
    
    else if (board[1][0] == board[1][1] && board[1][0] == board [1][2] && (board[1][0] == "X" || board[1][0] == "O")){
      if (board[1][0] == "X"){ return "X"; }
      else { return "O"; }
    }
    
    else if (board[2][0] == board[2][1] && board[2][0] == board [2][2] && (board[2][0] == "X" || board[2][0] == "O")){
      if (board[2][0] == "X"){ return "X"; }
      else { return "O"; }
    }
    
    else if (board[0][0] == board[1][0] && board[0][0] == board [2][0] && (board[0][0] == "X" || board[0][0] == "O")){
      if (board[0][0] == "X"){ return "X"; }
      else { return "O"; }
    }
    
    else if (board[0][1] == board[1][1] && board[0][1] == board [2][1] && (board[0][1] == "X" || board[0][1] == "O")){
      if (board[0][1] == "X"){ return "X"; }
      else { return "O"; }
    }
    
    else if (board[0][2] == board[1][2] && board[0][2] == board [2][2] && (board[0][2] == "X" || board[0][2] == "O")){
      if (board[0][2] == "X"){ return "X"; }
      else { return "O"; }
    }
    
    else if (board[0][0] == board[1][1] && board[0][0] == board [2][2] && (board[0][0] == "X" || board[0][0] == "O")){
      if (board[0][0] == "X"){ return "X"; }
      else { return "O"; }
    }
     
    else {
      if (board[0][0] == "X"){ return "X"; }
      else { return "O";}
    }
  }

  /* Purpose: Printing the board after someone has one the game and printing out the winner
     In: board
     In/Out: N/A
     Out: N/A
  */
  public static void indicateWinner(String board[][]){

    drawBoard(board);
    System.out.println(checkWhoWon(board) + " has won the game!");
  }

  /* Purpose: Checking if there are any valid moves left, and if not then the board is full and no one has won the game so we have a draw
     In: board
     In/Out: N/A
     Out: boolean
  */
  public static boolean boardFull(String board[][]){

      if (board[0][0] == " ") { return false; }
      else if (board[0][1] == " ") { return false; }
      else if (board[0][2] == " ") { return false; }
      else if (board[1][0] == " ") { return false; }
      else if (board[1][1] == " ") { return false; }
      else if (board[1][2] == " ") { return false; }
      else if (board[2][0] == " ") { return false; }
      else if (board[2][1] == " ") { return false; }
      else if (board[2][2] == " ") { return false; }
      else { return true; }
  }
}
