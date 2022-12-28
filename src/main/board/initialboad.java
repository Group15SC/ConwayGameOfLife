package com.example.model;

import java.util.Scanner;
import grid;
import cell;
import cellstatus;

    public class initialBoard { //puts cells on the board


        //randomly choose one point in the leftside of the board
        public static void main(String[] args) {
            Scanner input = new Scanner(System.in);
            int 2 = input.nextInt();
            print Matrix(2);

            private static void printMatrix( int 2) {
                for (int i=1; i<=15;i++){
                    System.out.print((int))( Math.random ( ) * 2 ) );
                }
        }



       switch(grid.getcellstatus){  //format: square, Boat, loaf, ship

            case SQUARE:
                maxtrix[row][col] = new Cell(row, col, cellstatus.RED)//square
                maxtrix[row][col] = new Cell(row - 1, col + 1, cellstatus.RED)
                maxtrix[row][col] = new Cell(row - 1, col, cellstatus.RED)
                maxtrix[row][col] = new Cell(row, col + 1, cellstatus.RED)
                maxtrix[row][col] = new Cell(row, col + 15, cellstatus.BLUE)//symmetric
                maxtrix[row][col] = new Cell(row - 1, col + 16, cellstatus.BLUE)
                maxtrix[row][col] = new Cell(row - 1, col + 15, cellstatus.BLUE)
                maxtrix[row][col] = new Cell(row, col + 16, cellstatus.BLUE)
                break;

            case BOAT:
                maxtrix[row][col] = new Cell(row, col, cellstatus.RED) //boat
                maxtrix[row][col] = new Cell(row - 1, col, cellstatus.RED)
                maxtrix[row][col] = new Cell(row - 1, col + 1, cellstatus.RED)
                maxtrix[row][col] = new Cell(row, col + 2, cellstatus.RED)
                maxtrix[row][col] = new Cell(row + 1, col + 1, cellstatus.RED)
                maxtrix[row][col] = new Cell(row, col + 15, cellstatus.BLUE)//symmetric
                maxtrix[row][col] = new Cell(row - 1, col + 15, cellstatus.BLUE)
                maxtrix[row][col] = new Cell(row - 1, col + 16, cellstatus.BLUE)
                maxtrix[row][col] = new Cell(row, col + 17, cellstatus.BLUE)
                maxtrix[row][col] = new Cell(row + 1, col + 16, cellstatus.BLUE);
                break;


            case LOAF:
               maxtrix[row][col] = new Cell(row, col, cellstatus.RED)//loaf
               maxtrix[row][col] = new Cell(row + 1, col, cellstatus.RED)
               maxtrix[row][col] = new Cell(row - 1, col + 1, cellstatus.RED)
               maxtrix[row][col] = new Cell(row - 1, col + 2, cellstatus.RED)
               maxtrix[row][col] = new Cell(row + 2, col + 1, cellstatus.RED)
               maxtrix[row][col] = new Cell(row + 1, col + 2, cellstatus.RED)
               maxtrix[row][col] = new Cell(row, col + 3, cellstatus.RED)
               maxtrix[row][col] = new Cell(row, col + 15, cellstatus.BLUE)//symmetric
               maxtrix[row][col] = new Cell(row + 1, col + 15, cellstatus.BLUE)
               maxtrix[row][col] = new Cell(row - 1, col + 16, cellstatus.BLUE)
               maxtrix[row][col] = new Cell(row - 1, col + 17, cellstatus.BLUE)
               maxtrix[row][col] = new Cell(row + 2, col + 16, cellstatus.BLUE)
               maxtrix[row][col] = new Cell(row + 1, col + 17, cellstatus.BLUE)
               maxtrix[row][col] = new Cell(row, col + 18, cellstatus.BLUE);
            break;

            case SHIP:
                maxtrix[row][col] = new Cell(row, col, cellstatus.RED) //SHIP
                maxtrix[row][col] = new Cell(row - 1, col, cellstatus.RED)
                maxtrix[row][col] = new Cell(row - 1, col + 1, cellstatus.RED)
                maxtrix[row][col] = new Cell(row, col + 2, cellstatus.RED)
                maxtrix[row][col] = new Cell(row + 1, col + 1, cellstatus.RED)
                maxtrix[row][col] = new Cell(row + 1, col + 2, cellstatus.RED)
                maxtrix[row][col] = new Cell(row, col + 15, cellstatus.BLUE)//symmetric
                maxtrix[row][col] = new Cell(row - 1, col + 15, cellstatus.BLUE)
                maxtrix[row][col] = new Cell(row - 1, col + 16, cellstatus.BLUE)
                maxtrix[row][col] = new Cell(row, col + 17, cellstatus.BLUE)
                maxtrix[row][col] = new Cell(row + 1, col + 16, cellstatus.BLUE)
                maxtrix[row][col] = new Cell(row + 1, col + 17, cellstatus.BLUE);
                break;

        }

    }







