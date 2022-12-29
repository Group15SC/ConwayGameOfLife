//package main.ui;
//
//import java.util.Scanner;
//
//public class Input {
//
//    private final Scanner scanner = new Scanner(System.in);
//
//    // ask for the name of each player
//    public String getPlayerName(){
//        System.out.println("Please enter your name: ");
//        String name = scanner.nextLine();
//        while(name.length()<1){
//            System.out.println("Name cannot be empty!");
//            name = scanner.nextLine();
//        }
//        return name;
//    }
//
//    // ask for the color the player wants to take
//    public String getPlayerColor(){
//        System.out.println("Please pick a color from red and blue to represent your side.");
//        System.out.println("Enter R to choose red as your color. Enter B if you prefer blue.");
//        while(!scanner.hasNext("[RB]")){
//            System.out.println("You can only choose one color between red and blue!");
//            scanner.next();
//        }
//        String color = scanner.next();
//        return color;
//    }
//
//}
