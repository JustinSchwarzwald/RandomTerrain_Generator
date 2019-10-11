
/*
 * Author:      Justin Schwarzwald
 * Date:        6/02/2018
 * Assignment:  Assignment 2
 * Description: Program asks user if they would like to create a strip of land [1xm] or an island [mxn].
 *              For the strip of land the program will generate first element randomly,
 *              and the following elements are then based on the probability of the previous element.
 *              For the island the first row is generated the same as the strip of land,
 *              The following rows the elements are based on a coin flip,
 *              where it either bases the probability on the previous element or the above element,
 *              unless its the first element in the row, in that case it automatically bases it on the above element.
 *              After the arrays are created, it will prompt user if they would like to save the array to the file.
 *              
 */

import java.util.Scanner;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class Schwarzwald_Justin_TerrainMaker
{

    public static void main(String[] args)
    {
        // Declaring all objects and variables
        int userChoice = 0; // Selects option or to quit with -1
        int lengthOfLand = 0; // Size of the strip for option 1
        char[] completeLandArray; // The array for land strip
        char[][] completeIslandArray; // The 2D array for the island
        int islandWidth;
        int islandHeight;
        boolean notValidEntry; // Condition variable for checking if response is yes or no
        String userWriteToFileInput; // Used for Yes or No response for writing to file

        Scanner userInput = new Scanner(System.in);

        System.out.println("Welcome to the terrain generator.");

        // Loops for user choosing strip, island or to quit - this loop will end when user decides to quit "-1"
        do
        {
            // Setting condition to make sure only values 1, 2 and -1 are entered, will continue looping otherwise
            while (userChoice != 1 && userChoice != 2 && userChoice != 3 && userChoice != -1)
            {
                // Shows the main menu
                mainMenu();

                // Checks if the user entered a number
                if (userInput.hasNextInt())
                {
                    // Sets variable userChoice to number, if a number was given
                    userChoice = userInput.nextInt();
                    // Checks if number was a 1, 2, 3 or -1
                    if (userChoice != 1 && userChoice != 2 && userChoice != 3 && userChoice != -1)
                    {
                        // If userInput not a 1, 2, 3 or -1, statement and loop occur
                        System.out.println("\nYou need to enter a value 1, 2, or 3 to continue, or -1 to Exit\n");
                    }
                }
                // If a number wasn't entered the following runs
                else
                {
                    // Reset the scanner
                    userInput.next();
                    // Tells user to input either a 1, 2, or 3 if they didn't
                    System.out.println("\nYou need to enter a value of 1, 2, or 3 to continue, or -1 to Exit\n");
                }
            } // End of while loop once 1, 2, 3 or -1 is entered

            // Runs if user selects 1, creates a strip of land
            if (userChoice == 1)
            {
                // Resets variable lengthOfLand to default 0 for loop case
                lengthOfLand = 0;

                // Asks user users how big they want the land to be
                System.out.println("\nGenerating a single strip of land.");
                System.out.print("How long do you want the land to be?  >> ");

                // Loops until user enters a number greater than 0
                while (lengthOfLand < 1)
                    // Checks if the user entered a number
                    if (userInput.hasNextInt())
                    {
                        // Sets variable lengthOfLand to input if a number was given
                        lengthOfLand = userInput.nextInt();
                        // Checks if number is greater than 0
                        if (lengthOfLand < 1)
                        {
                            // Tells user to input a number greater than 0
                            System.out.println("\nYou need to enter a value greater than 0 to continue\n");
                            System.out.print("How long do you want the land to be?  >> ");
                        }
                    }
                    // If a number wasn't entered the following runs
                    else
                    {
                        // Reset the scanner
                        userInput.next();
                        // Tells user to input a number greater than 0
                        System.out.println("\nYou need to enter a value greater than 0 to continue\n");
                        System.out.print("How long do you want the land to be?  >> ");
                    }

                System.out.println("\nGenerating ... ... ...\n");
                System.out.println("Your Land:\n");

                // Runs getCharArray method and stores the completed array to variable
                completeLandArray = getCharArray(lengthOfLand);
                System.out.println(completeLandArray);

                // Scanner clear buffer
                userInput.nextLine();

                // Ask user if they want to save output
                System.out.println("\nWould you like to save the output?");
                System.out.println("Type Y for Yes, or N for No\n");
                System.out.print(">> ");

                // Resetting default for loop case
                notValidEntry = true;

                // Loops until user enters yes or no
                while (notValidEntry)
                {
                    // Takes in the users input and stores as a string
                    userWriteToFileInput = userInput.nextLine().toUpperCase();

                    // Checks if value is (y, Y, Yes, YES, or yes)
                    if (userWriteToFileInput.equals("Y") || userWriteToFileInput.equals("YES"))
                    {

                        // Asks user for output file name if entered yes
                        System.out.print("\nPlease enter name you would like to save file as: ");

                        // Passes the completed land array and file output name to method which writes array to file
                        writeToFile(completeLandArray, userInput.nextLine());

                        // Changes the condition to false to end loop
                        notValidEntry = false;
                    }
                    else if (userWriteToFileInput.contentEquals("N") || userWriteToFileInput.contentEquals("NO"))
                    {
                        // Changes the condition to false to end loop
                        notValidEntry = false;
                    }
                    // Condition stays true if yes or no was not entered, shows below error message
                    else
                    {
                        System.out.println("\nThat was not a vaild entry");
                        System.out.println("Type Y for Yes, or N for No\n");
                        System.out.print(">> ");
                    }
                }
                // Once finished resets userChoice to default 0 for looping
                userChoice = 0;
            }
            // Runs if user input a 2, creates an island array
            else if (userChoice == 2)
            {
                // Resets Height and Width of island to 0 for loop case
                islandHeight = 0;
                islandWidth = 0;

                System.out.println("\nGenerating an Island.");
                System.out.print("What would you like the height of the island to be?  >> ");

                // Resetting scanner
                userInput.nextLine();

                // Loop to check value entered is a number greater than 0
                while (islandHeight < 1)
                {
                    if (userInput.hasNextInt())
                    {
                        // Sets variable islandHeight to input if a number was given
                        islandHeight = userInput.nextInt();
                        // Checks if number is greater than 0
                        if (islandHeight < 1)
                        {
                            // Tells user to input a number greater than 0
                            System.out.println("\nYou need to enter a value greater than 0 to continue\n");
                            System.out.print("What would you like the height of the island to be?  >> ");
                        }
                    }
                    // If a number wasn't entered the following runs
                    else
                    {
                        // Reset the scanner
                        userInput.next();
                        // Tells user to input a number greater than 0
                        System.out.println("\nYou need to enter a value greater than 0 to continue\n");
                        System.out.print("What would you like the height of the island to be?  >> ");
                    }
                } // Ends loop once value greater than 0 was entered

                System.out.print("What would you like the width of the island to be?  >> ");

                // Loops through until number greater than 0 is entered
                while (islandWidth < 1)
                {
                    if (userInput.hasNextInt())
                    {
                        // Sets variable islandWidth to input if a number was given
                        islandWidth = userInput.nextInt();
                        // Checks if number is greater than 0
                        if (islandWidth < 1)
                        {
                            // Tells user to input a number greater than 0
                            System.out.println("\nYou need to enter a value greater than 0 to continue\n");
                            System.out.print("What would you like the Width of the island to be?  >> ");
                        }
                    }
                    // If a number wasn't entered the following runs
                    else
                    {
                        // Reset the scanner
                        userInput.next();
                        // Tells user to input a number greater than 0
                        System.out.println("\nYou need to enter a value greater than 0 to continue\n");
                        System.out.print("What would you like the Width of the island to be?  >> ");
                    }
                }

                System.out.println("\nGenerating ... ... ...\n");
                System.out.println("Your Land:\n");

                // Returns complete 2D array passing in the height and width to getCharArray method
                completeIslandArray = getCharArray(islandHeight, islandWidth);
                for (int i = 0; i < completeIslandArray.length; i++)
                    System.out.println(completeIslandArray[i]);

                // Scanner clear buffer
                userInput.nextLine();

                // Ask user if they want to save output
                System.out.println("\nWould you like to save the output?");
                System.out.println("Type Y for Yes, or N for No\n");
                System.out.print(">> ");

                // Resetting default for loop case
                notValidEntry = true;

                // Loops until user enters yes or no
                while (notValidEntry)
                {
                    // Takes in the users input and stores as a string
                    userWriteToFileInput = userInput.nextLine().toUpperCase();

                    // Checks if value is (y, Y, Yes, YES, or yes)
                    if (userWriteToFileInput.equals("Y") || userWriteToFileInput.equals("YES"))
                    {

                        // Asks user for output file name if entered yes
                        System.out.print("\nPlease enter name you would like to save file as: ");

                        // Passes the completed Island array and file output name to method which writes array to file
                        writeToFile(completeIslandArray, userInput.nextLine());

                        // Changes the condition to false to end loop
                        notValidEntry = false;
                    }
                    else if (userWriteToFileInput.contentEquals("N") || userWriteToFileInput.contentEquals("NO"))
                    {
                        // Changes the condition to false to end loop if no was entered
                        notValidEntry = false;
                    }
                    // Condition stays true if yes or no was not entered, shows below error message
                    else
                    {
                        System.out.println("\nThat was not a vaild entry");
                        System.out.println("Type Y for Yes, or N for No\n");
                        System.out.print(">> ");
                    }
                } // Ends loop if yes or no is entered

                // Resets userChoice for loop case
                userChoice = 0;
            } // End of choice 2

            // Runs if choice three is selected extra credit 2D array
            else if (userChoice == 3)
            {
                // Resets Height and Width of island to 0 for loop case
                islandHeight = 0;
                islandWidth = 0;

                System.out.println("\nGenerating an Island.");
                System.out.print("What would you like the height of the island to be?  >> ");

                // Resetting scanner
                userInput.nextLine();

                // Loop to check value entered is a number greater than 0
                while (islandHeight < 1)
                {
                    if (userInput.hasNextInt())
                    {
                        // Sets variable islandHeight to input if a number was given
                        islandHeight = userInput.nextInt();
                        // Checks if number is greater than 0
                        if (islandHeight < 1)
                        {
                            // Tells user to input a number greater than 0
                            System.out.println("\nYou need to enter a value greater than 0 to continue\n");
                            System.out.print("What would you like the height of the island to be?  >> ");
                        }
                    }
                    // If a number wasn't entered the following runs
                    else
                    {
                        // Reset the scanner
                        userInput.next();
                        // Tells user to input a number greater than 0
                        System.out.println("\nYou need to enter a value greater than 0 to continue\n");
                        System.out.print("What would you like the height of the island to be?  >> ");
                    }
                } // Ends loop once value greater than 0 was entered

                System.out.print("What would you like the width of the island to be?  >> ");

                // Loops through until number greater than 0 is entered
                while (islandWidth < 1)
                {
                    if (userInput.hasNextInt())
                    {
                        // Sets variable islandWidth to input if a number was given
                        islandWidth = userInput.nextInt();
                        // Checks if number is greater than 0
                        if (islandWidth < 1)
                        {
                            // Tells user to input a number greater than 0
                            System.out.println("\nYou need to enter a value greater than 0 to continue\n");
                            System.out.print("What would you like the Width of the island to be?  >> ");
                        }
                    }
                    // If a number wasn't entered the following runs
                    else
                    {
                        // Reset the scanner
                        userInput.next();
                        // Tells user to input a number greater than 0
                        System.out.println("\nYou need to enter a value greater than 0 to continue\n");
                        System.out.print("What would you like the Width of the island to be?  >> ");
                    }
                }

                System.out.println("\nGenerating ... ... ...\n");
                System.out.println("Your Land:\n");

                // Passes Height and Width to get a completed array returned
                completeIslandArray = getCharArrayExtraCredit(islandHeight, islandWidth);
                for (int i = 0; i < completeIslandArray.length; i++)
                    System.out.println(completeIslandArray[i]);

                // Scanner clear buffer
                userInput.nextLine();

                // Ask user if they want to save output
                System.out.println("\nWould you like to save the output?");
                System.out.println("Type Y for Yes, or N for No\n");
                System.out.print(">> ");

                // Resetting default for loop case
                notValidEntry = true;

                // Loops until user enters yes or no
                while (notValidEntry)
                {
                    // Takes in the users input and stores as a string
                    userWriteToFileInput = userInput.nextLine().toUpperCase();

                    // Checks if value is (y, Y, Yes, YES, or yes)
                    if (userWriteToFileInput.equals("Y") || userWriteToFileInput.equals("YES"))
                    {

                        // Asks user for output file name if entered yes
                        System.out.print("\nPlease enter name you would like to save file as: ");

                        // Passes the completed Island array and file output name to method which writes array to file
                        writeToFile(completeIslandArray, userInput.nextLine());

                        // Changes the condition to false to end loop
                        notValidEntry = false;
                    }
                    else if (userWriteToFileInput.contentEquals("N") || userWriteToFileInput.contentEquals("NO"))
                    {
                        // Changes the condition to false to end loop if no was entered
                        notValidEntry = false;
                    }
                    // Condition stays true if yes or no was not entered, shows below error message
                    else
                    {
                        System.out.println("\nThat was not a vaild entry");
                        System.out.println("Type Y for Yes, or N for No\n");
                        System.out.print(">> ");
                    }
                } // Ends loop if yes or no is entered

                // Resets userChoice for loop case
                userChoice = 0;

            }
            // Runs if -1 is entered to quit program
            else
            {
                System.out.println("\nYou have quit the program");

            }

            System.out.println(""); // Formatting

        } while (userChoice != -1); // Ends loop and quits program

        userInput.close(); // Closes scanner

    } // End of main

    /**
     * Shows the main menu
     */
    private static void mainMenu()
    {
        System.out.println("Please select an option:");
        System.out.println("1  - Single Strip of Land");
        System.out.println("2  - Island");
        System.out.println("3  - Island > ExtraCredit");
        System.out.println("-1 - Exit");
        System.out.print("\n>> ");
    }

    /**
     * Generates a random terrain based on probability given, use when last element in array was Grass.
     * Probability: 45% Grass, 30% Hill, 15% Water, 10% Desert, 0% Mountain
     * 
     * @return char of terrain generated
     */
    private static char getNextFromGrass()
    {
        double diceRoll = Math.random();

        // Probability 45% Grass, 30% Hill, 15% Water, 10% Desert, 0% Mountain
        final double grassProbability = .45;
        final double hillProbability = .30;
        final double waterProbability = .15;
        // final double desertProbability = .10; remaining percentage
        // final double mountainProbability = 0; mountain not possible
        
        // Checks against random number and compares to the probabilities
        if (diceRoll <= grassProbability)
            return 'g';
        else if (diceRoll <= grassProbability + hillProbability)
            return 'h';
        else if (diceRoll <= grassProbability + hillProbability + waterProbability)
            return 'w';
        else
            return 'd';
    }

    /**
     * Generates a random terrain based on probability given, use when last element in array was Hill.
     * Probability: 25% Grass, 45% Hill, 10% Water, 0% Desert, 20% Mountain
     * 
     * @return char of terrain generated
     */
    private static char getNextFromHill()
    {
        double diceRoll = Math.random();

        // Probability 25% Grass, 45% Hill, 10% Water, 0% Desert, 20% Mountain
        final double grassProbability = .25;
        final double hillProbability = .45;
        final double waterProbability = .10;
        // final double desertProbability = 0; desert not possible
        // final double mountainProbability = .20; mountain remaining percentage

        // Checks against random number and compares to the probabilities
        if (diceRoll <= grassProbability)
            return 'g';
        else if (diceRoll <= grassProbability + hillProbability)
            return 'h';
        else if (diceRoll <= grassProbability + hillProbability + waterProbability)
            return 'w';
        else
            return 'm';

    }

    /**
     * Generates a random terrain based on probability given, use when last element in array was Mountain.
     * Probability: 0% Grass, 25% Hill, 15% Water, 5% Desert, 55% Mountain
     * 
     * @return char of terrain generated
     */
    private static char getNextFromMountain()
    {
        double diceRoll = Math.random();

        // Probability 0% Grass, 25% Hill, 15% Water, 5% Desert, 55% Mountain
        // final double grassProbability = 0; water not possible
        final double hillProbability = .25;
        final double waterProbability = .15;
        final double desertProbability = .05;
        // final double mountainProbablity = .55; mountain remaining percentage

        // Checks against random number and compares to the probabilities
        if (diceRoll <= hillProbability)
            return 'h';
        else if (diceRoll <= hillProbability + waterProbability)
            return 'w';
        else if (diceRoll <= hillProbability + waterProbability + desertProbability)
            return 'd';
        else
            return 'm';
    }

    /**
     * Generates a random terrain based on probability given, use when last element in array was Desert.
     * Probability: 30% Grass, 0% Hill, 0% Water, 65% Desert, 5% Mountain
     * 
     * @return char of terrain generated
     */
    private static char getNextFromDesert()
    {
        double diceRoll = Math.random();

        // Probability 30% Grass, 0% Hill, 0% Water, 65% Desert, 5% Mountain
        final double grassProbability = .30;
        // final double hillProbability = 0; hill not possible
        // final double waterProbability = 0; water not possible
        final double desertProbability = .65;
        // final double mountainProbablity = .5; mountain remaining percentage

        // Checks against random number and compares to the probabilities
        if (diceRoll <= grassProbability)
            return 'g';
        else if (diceRoll <= grassProbability + desertProbability)
            return 'd';
        else
            return 'm';
    }

    /**
     * Generates a random terrain based on probability given, use when last element in array was Water.
     * Probability: 30% Grass, 15% Hill, 50% Water, 0% Desert, 5% Mountain
     * 
     * @return char of terrain generated
     */
    private static char getNextFromWater()
    {
        double diceRoll = Math.random();

        // Probability 30% Grass, 15% Hill, 50% Water, 0% Desert, 5% Mountain
        final double grassProbability = .30;
        final double hillProbability = .15;
        final double waterProbability = .50;
        // final double desertProbability = 0; water not possible
        // final double mountainProbablity = .05; mountain remaining percentage

        // Checks against random number and compares to the probabilities
        if (diceRoll <= grassProbability)
            return 'g';
        else if (diceRoll <= grassProbability + hillProbability)
            return 'h';
        else if (diceRoll <= grassProbability + hillProbability + waterProbability)
            return 'w';
        else
            return 'm';
    }

    /**
     * Takes in char of terrain from the left of array
     * runs method corresponding to terrain to return probability based terrain.
     * 
     * @param neighborsChar (char to the left of the array)
     * @return char (new generated terrain-based on probabilities of terrain to left)
     */
    private static char GenerateNeighbor(char neighborsChar)
    {
        if (neighborsChar == 'g')
            return getNextFromGrass();
        else if (neighborsChar == 'h')
            return getNextFromHill();
        else if (neighborsChar == 'm')
            return getNextFromMountain();
        else if (neighborsChar == 'd')
            return getNextFromDesert();
        else
            return getNextFromWater();

    }


    /**
     * Takes in a length and generates a one dimensional array based on probabilities of the previous element in array
     * 
     * @param lengthOfArray (Pass in the length of array - greater than 0)
     * @return A completed, probability based, one dimensional array of terrain
     */
    private static char[] getCharArray(int lengthOfArray)
    {
        double landNumberSeed = Math.random();

        // Initializing land strip array with length passed in 
        char[] landArray = new char[lengthOfArray];
        
        // Even chance of generating first element in array
        if (landNumberSeed <= .20)
            landArray[0] = 'g';
        else if (landNumberSeed <= .40)
            landArray[0] = 'h';
        else if (landNumberSeed <= .60)
            landArray[0] = 'w';
        else if (landNumberSeed <= .80)
            landArray[0] = 'd';
        else
            landArray[0] = 'm';

        // For every element after element 1, passes the element from the left into GenerateNeighbor method and adds its return value to array
        for (int i = 1; i < landArray.length; i++)
        {
            landArray[i] = GenerateNeighbor(landArray[i - 1]);
        }

        // Returns the completed array
        return landArray;

    }

    /**
     * Takes in a height and width and generates a 2D array based on probabilities.
     * Uses CoinFip mechanic to determine if terrain should be generated based on above or left terrain
     * 
     * @param heightOfArray
     * @param widthOfArray
     * @return A completed, probability based, 2D array of terrain
     */
    private static char[][] getCharArray(int heightOfArray, int widthOfArray)
    {
        double coinFlip;

        // Initializing island 2D array with Height passed in as number of rows
        char[][] islandArray = new char[heightOfArray][];
        // Sets row 0 by calling getCharArray method
        islandArray[0] = getCharArray(widthOfArray);

        // Starting at row 1 till end of rows
        for (int i = 1; i < islandArray.length; i++)
        {
            // Starting at first element loops to the last element
            for (int j = 0; j < islandArray[0].length; j++)
            {
                if (j == 0) // Runs when it is on the first element in the row
                {
                    // Sets width of row and generates the first element based on the element above
                    islandArray[i] = new char[widthOfArray];
                    islandArray[i][0] = GenerateNeighbor(islandArray[i - 1][0]);
                }
                else // Runs if its not the first element of the row
                {
                    // Flips a coin to see if the element should be generated based on the element from above or to the left 
                    coinFlip = Math.random();

                    if (coinFlip < .50)
                        islandArray[i][j] = GenerateNeighbor(islandArray[i - 1][j]); // Above

                    else
                        islandArray[i][j] = GenerateNeighbor(islandArray[i][j - 1]); // Left
                }
            }
        }
        
        return islandArray; // Returns completed 2D array
    }

    /**
     * Takes in a height and width and generates a 2D array based on probabilities.
     * Uses the getCharArray method from part 1 to generate each row in the array
     * 
     * @param heightOfArray
     * @param widthOfArray
     * @return A completed, probability based, 2D array of terrain
     */
    private static char[][] getCharArrayExtraCredit(int heightOfArray, int widthOfArray)
    {
        // Initializing island 2D array with Height passed in as number of rows
        char[][] islandArray = new char[heightOfArray][];

        // For each row the row is generated by getCharArray used in part 1 and passed in
        for (int i = 0; i < islandArray.length; i++)
        {
            islandArray[i] = getCharArray(widthOfArray);
        }
        
        // Returns completed array
        return islandArray;

    }

    /**
     * Takes in one dimensional array and a file name.
     * Writes the array to the file.
     * 
     * @param arrayToWrite (Completed one dimensional array)
     * @param fileName (What the file should be called)
     */
    private static void writeToFile(char[] arrayToWrite, String fileName)
    {
        // Initializing print writer
        PrintWriter outFile = null;

        try
        {
            // Sets the output file to the name provided by user passed in
            outFile = new PrintWriter(fileName);
        }
        catch (FileNotFoundException e)
        {
            // Prints if error is made
            e.printStackTrace();
        }
        
        // Writes the array to the file specified
        outFile.print(arrayToWrite);
        System.out.println("The file has been saved.");
        outFile.close(); // Closes the writer
    }

    /**
     * Takes in a 2D array and a file name.
     * Writes the array to the file.
     * 
     * @param arrayToWrite (Completed one dimensional array)
     * @param fileName (What the file should be called)
     */
    private static void writeToFile(char[][] arrayToWrite, String fileName)
    {
        // Initializing print writer
        PrintWriter outFile = null;

        try
        {
            // Sets the output file to the name provided by user passed in
            outFile = new PrintWriter(fileName);
        }
        catch (FileNotFoundException e)
        {
            // Prints if error is made
            e.printStackTrace();
        }

        // Writes each row to the file
        for (int i = 0; i < arrayToWrite.length; i++)
            outFile.println(arrayToWrite[i]);

        System.out.println("The file has been saved.");
        outFile.close(); // Closes the writer
    }

}
