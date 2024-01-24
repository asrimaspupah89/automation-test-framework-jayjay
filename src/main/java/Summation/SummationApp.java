package Summation;

import java.util.Scanner;  // Import the Scanner class

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class SummationApp {
    public static void main(String[] args) {
        Scanner scanInput = new Scanner(System.in);  // Create a Scanner object
        int bil1, bil2, result;
        Summation sumModule = new Summation();

        System.out.println("Hello and welcome to Summation.Summation App");
        System.out.println("Input 2 buah bilangan: ");
        bil1 = scanInput.nextInt();
        bil2 = scanInput.nextInt();

        result = sumModule.sum(bil1, bil2);
        System.out.println("\nHasil penjumlahan " + bil1 + " dan " + bil2 + " adalah " + result);
    }
}