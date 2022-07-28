/* 
Name:Elizabeth Pursell
Date: 4/7/2022
CSE 007 Spring 2022
Produce TicTacToe program that will simulate a game of Tic-Tac-Toe
*/
import java.util.Scanner;       //import classes
public class TicTacToe {
    public static void main(String [] args){
        //create scanner, variables, and arrays
        Scanner myScan = new Scanner(System.in);
        char [] [] boardArray = {{'1', '2', '3'}, {'4', '5', '6'}, {'7', '8', '9'}};
        boolean play = true;
        boolean tie = false;
        int turnCounter = 0;
        char marker = 'a';

        displayBoard(boardArray);

        //repeats loop until winner or tie
        do{
            if(turnCounter % 2 == 0){   //if player 1's turn, use O
                marker = 'O';
            }
            else{               //if player 2's turn, use X
                marker = 'X';
            }
            System.out.println("It is " + marker + "'s turn!");
            placeMarker(myScan, marker, boardArray);
            displayBoard(boardArray);
            tie = checkTie(boardArray);
            play = checkWinner(boardArray);
            if(!play){
                tie = false;    //overrides tie if a winner found
            }
            if (tie){
                play = false;       //breaks from loop if tie
            }
            turnCounter++;      //counts the number of turns to find whose turn it is
        }while (play);

        //prints prompts based off results
        if(tie){
            System.out.println("There has been a tie!");
        }
        else if(marker == 'O'){
            System.out.println("O is the winner!");
        }
        else if (marker == 'X'){
            System.out.println("X is the winner!");
        }
    }

    //prints game board with current marker placements
    public static void displayBoard(char [] [] list){
        for(int row = 0; row < list.length; row++){
            for(int col = 0; col < list[row].length; col++){
                System.out.print(list[row][col] + "    ");
            }
            System.out.println();
        }
    }

    //gets user's desired position and inserts into game board, checks to make sure input is valid
    public static void placeMarker(Scanner myScan, char token, char [] [] list){
        boolean correct = false;
        char position = 'a';
        do{
            System.out.println("Enter position for marker (1-9): ");
            String temp = myScan.nextLine();
            char tempChar = temp.charAt(0);
            if(temp.length() != 1){         //checks to make sure only one character was inputted
                correct = false;
                System.out.println("Invalid Input: Enter a number 1-9.");
            }
            else{
                if(tempChar >= '1' && tempChar <= '9'){     //checks to make sure char in range
                    position = tempChar;
                    for(int row = 0; row < list.length; row++){
                        for(int index = 0; index < list[row].length; index++){
                            if(position == list[row][index]){
                                list[row][index] = token;       //puts marker in desired position
                                correct = true;
                                break;
                            }
                        }
                    }
                    if(!correct){
                        correct = false;
                        System.out.println("Invalid Input: Position Already Chosen.");      //comes out of loop and prompts if position already taken
                    }
                }
                else{
                    correct = false;
                    System.out.println("Invalid Input: Enter a number 1-9.");
                }
            }
        }while(!correct);
    }

    //checks to see if board is filled, which usually means tie
    public static boolean checkTie(char [] [] list){
        for(int row = 0; row < list.length; row++){
            for(int index = 0; index < list[row].length; index++){
                if(list[row][index] != 'X' && list[row][index] != 'O'){
                    return false;
                }
            }
        }
        return true;
    }
    //checks all possibilities to win
    public static boolean checkWinner(char [] [] list){
        for(int row = 0; row < list.length; row++){
            for(int index = 0; index < list.length; index++){
                if(row == 0){
                    if(index == 0){
                        if(list[row][index] == list[row][index + 1] && list[row][index] == list[row][index + 2]){
                            return false;
                        }
                        else if(list[row][index] == list[row + 1][index] && list[row][index] == list[row + 2][index]){
                            return false;
                        }
                        else if(list[row][index] == list[row + 1][index + 1] && list[row][index] == list[row + 2][index + 2]){
                            return false;
                        }
                    }
                    else if(index == 1){
                        if(list[row][index] == list[row + 1][index] && list[row][index] == list[row + 2][index]){
                            return false;
                        }
                    }
                    else if(index == 2){
                        if(list[row][index] == list[row + 1][index] && list[row][index] == list[row + 2][index]){
                            return false;
                        }
                    }
                }
                else if(row == 1){
                    if(index == 0){
                        if(list[row][index] == list[row][index + 1] && list[row][index] == list[row][index + 2]){
                            return false;
                        }
                    }
                }
                else if(row == 2){
                    if(index == 0){
                        if(list[row][index] == list[row][index + 1] && list[row][index] == list[row][index + 2]){
                            return false;
                        }
                        if(list[row][index] == list[row - 1][index + 1] && list[row][index] == list[row - 2][index + 2]){
                            return false;
                        }
                    }
                }
            }
        }
        return true;
    }
}
