//package com.example.model;
//
//import java.util.ArrayList;
//import java.util.Scanner;
//import grid;
//import main.board.Cell;
//import main.board.CellStatus;
//import main.board.Grid;
//
//import static jdk.internal.org.jline.reader.impl.DefaultParser.Bracket.SQUARE;
//
//public class initialBoard { //puts cells on the board
//
//        Grid grid;
//        //randomly choose one point in the leftside of the board
////        public static void main(String[] args) {
////            Scanner input = new Scanner(System.in);
////            int 2 = input.nextInt();
////            print Matrix(2);
////
////            private static void printMatrix( int 2) {
////                for (int i=1; i<=15;i++){
////                    System.out.print((int))( Math.random ( ) * 2 ) );
////                }
////        }
//
//        ArrayList<Cell> square = new ArrayList<>();
//
//
//
//
//
//
//       switch(grid.getcellstatus){  //format: square, Boat, loaf, ship
//
//            case SQUARE:
//                gird[row][col] = new Cell(row, col, CellStatus.RED);//square
//                maxtrix[row][col] = new Cell(row - 1, col + 1, CellStatus.RED);
//                maxtrix[row][col] = new Cell(row - 1, col, CellStatus.RED);
//                maxtrix[row][col] = new Cell(row, col + 1, CellStatus.RED);
//                maxtrix[row][col] = new Cell(row, col + 15, CellStatus.BLUE);//symmetric
//                maxtrix[row][col] = new Cell(row - 1, col + 16, CellStatus.BLUE);
//                maxtrix[row][col] = new Cell(row - 1, col + 15, CellStatus.BLUE);
//                maxtrix[row][col] = new Cell(row, col + 16, CellStatus.BLUE);
//                break;
//
//            case BOAT:
//                maxtrix[row][col] = new Cell(row, col, CellStatus.RED); //boat
//                maxtrix[row][col] = new Cell(row - 1, col, CellStatus.RED);
//                maxtrix[row][col] = new Cell(row - 1, col + 1, CellStatus.RED);
//                maxtrix[row][col] = new Cell(row, col + 2, CellStatus.RED);
//                maxtrix[row][col] = new Cell(row + 1, col + 1, CellStatus.RED);
//                maxtrix[row][col] = new Cell(row, col + 15, CellStatus.BLUE);//symmetric
//                maxtrix[row][col] = new Cell(row - 1, col + 15, CellStatus.BLUE);
//                maxtrix[row][col] = new Cell(row - 1, col + 16, CellStatus.BLUE);
//                maxtrix[row][col] = new Cell(row, col + 17, CellStatus.BLUE);
//                maxtrix[row][col] = new Cell(row + 1, col + 16, CellStatus.BLUE);
//                break;
//
//
//            case LOAF:
//               maxtrix[row][col] = new Cell(row, col, CellStatus.RED);//loaf
//               maxtrix[row][col] = new Cell(row + 1, col, CellStatus.RED);
//               maxtrix[row][col] = new Cell(row - 1, col + 1, CellStatus.RED);
//               maxtrix[row][col] = new Cell(row - 1, col + 2, CellStatus.RED);
//               maxtrix[row][col] = new Cell(row + 2, col + 1, CellStatus.RED);
//               maxtrix[row][col] = new Cell(row + 1, col + 2, CellStatus.RED);
//               maxtrix[row][col] = new Cell(row, col + 3, CellStatus.RED);
//               maxtrix[row][col] = new Cell(row, col + 15, CellStatus.BLUE);//symmetric
//               maxtrix[row][col] = new Cell(row + 1, col + 15, CellStatus.BLUE);
//               maxtrix[row][col] = new Cell(row - 1, col + 16, CellStatus.BLUE);
//               maxtrix[row][col] = new Cell(row - 1, col + 17, CellStatus.BLUE);
//               maxtrix[row][col] = new Cell(row + 2, col + 16, CellStatus.BLUE);
//               maxtrix[row][col] = new Cell(row + 1, col + 17, CellStatus.BLUE);
//               maxtrix[row][col] = new Cell(row, col + 18, CellStatus.BLUE);
//            break;
//
//            case SHIP:
//                maxtrix[row][col] = new Cell(row, col, CellStatus.RED); //SHIP
//                maxtrix[row][col] = new Cell(row - 1, col, CellStatus.RED);
//                maxtrix[row][col] = new Cell(row - 1, col + 1, CellStatus.RED);
//                maxtrix[row][col] = new Cell(row, col + 2, CellStatus.RED);
//                maxtrix[row][col] = new Cell(row + 1, col + 1, CellStatus.RED);
//                maxtrix[row][col] = new Cell(row + 1, col + 2, CellStatus.RED);
//                maxtrix[row][col] = new Cell(row, col + 15, CellStatus.BLUE);//symmetric
//                maxtrix[row][col] = new Cell(row - 1, col + 15, CellStatus.BLUE);
//                maxtrix[row][col] = new Cell(row - 1, col + 16, CellStatus.BLUE);
//                maxtrix[row][col] = new Cell(row, col + 17, CellStatus.BLUE);
//                maxtrix[row][col] = new Cell(row + 1, col + 16, CellStatus.BLUE);
//                maxtrix[row][col] = new Cell(row + 1, col + 17, CellStatus.BLUE);
//                break;
//        }
//
//    }
//
//
//
//
//
//
//
