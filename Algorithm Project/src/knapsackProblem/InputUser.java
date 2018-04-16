/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package knapsackProblem;

import java.util.Scanner;
import static knapsackProblem.Knapsack.isDoub;
import static knapsackProblem.Knapsack.isInt;

/**
 *
 * @author harsh
 */
public class InputUser {

    
    
    public void getInput(Knapsack knapsack) {

        String userInput;

        Scanner sc = new Scanner(System.in);

        System.out.println("Enter total number of items for knapsack: ");
        userInput = sc.nextLine();
        
        if (isInt(userInput)) {
            knapsack.totalItems = Integer.parseInt(userInput);
        }
        else {
            System.out.println("User input is not a number, exiting...");
            System.exit(1);
        }

        for(int i = 0; i < knapsack.totalItems; i++) {
            System.out.println("Please give input for Value of Item " + (i + 1) + ": ");
            userInput = sc.nextLine();
            if (isDoub(userInput)) { 
                knapsack.itemValueArrayList.add(Double.parseDouble(userInput));
            }
            else {
                System.out.println("User input is not a number, exiting...");
                System.exit(1);
            }

            System.out.println("Please give input for weight of Item " + (i + 1) + ": ");
            userInput = sc.nextLine();
            if (isDoub(userInput)) {
                knapsack.itemWeightArrayList.add(Double.parseDouble(userInput));
            }
            else {
                System.out.println("User input is not a number, exiting...");
                System.exit(1);
            }            
        }

        System.out.println("Please enter total capacity of knapsack ");
        userInput = sc.nextLine();
        if (isInt(userInput)) {
            knapsack.totalCapacityOfKnapsack = Integer.parseInt(userInput);
        }
        else {
            System.out.println("User input is not a number, exiting...");
            System.exit(1);
        }

        System.out.println("Please enter total population size ");
        userInput = sc.nextLine();
        if (isInt(userInput)) {
            knapsack.totalPopulationSize = Integer.parseInt(userInput);
        }
        else {
            System.out.println("User input is not a number, exiting...");
            System.exit(1);
        }

        System.out.println("Please enter total number of Generations ");
        userInput = sc.nextLine();
        if (isInt(userInput)) {
            knapsack.totalMaxGeneration = Integer.parseInt(userInput);
        }
        else {
            System.out.println("User input is not a number, exiting...");
            System.exit(1);
        }

        System.out.println("Please Enter prob for cross: ");
        userInput = sc.nextLine();
        if (isDoub(userInput)) {
            knapsack.probabilityOfCrossover = Double.parseDouble(userInput);
        }
        else {
            System.out.println("User input is not a number, exiting...");
            System.exit(1);
        }

        System.out.println("Please enter prob for mutation: ");
        userInput = sc.nextLine();
        if (isDoub(userInput)) {
            knapsack.probabilityOfMutation = Double.parseDouble(userInput);
        }
        else {
            System.out.println("User input is not a number, exiting...");
            System.exit(1);
        }

    }
}
