package org.yearup.ui;

import org.yearup.ColorCodes;

import java.util.Scanner;

public class AdminUserInterface extends UserInterface
{
    private static final Scanner scanner = new Scanner(System.in);

    public void displayAdminMenu()
    {
        while(true)
        {
            printTitle("ADMIN MENU");
            System.out.println("What would you like to do?");
            System.out.println();
            System.out.println(ColorCodes.YELLOW + "1) List All Contracts");
            System.out.println(ColorCodes.PURPLE + "0) Back" + ColorCodes.RESET);
            System.out.println();

            int option;
            while(true)
            {
                try
                {
                    System.out.print("Enter an option: ");
                    option = Integer.parseInt(scanner.nextLine().strip());
                    break;
                }
                catch(Exception e)
                {
                    printInvalid();
                }
            }

            switch(option)
            {
                case 0 ->
                {
                    return;
                }
                case 1 ->
                {
                    // Display All Contracts
                }
                default ->
                {
                    printInvalid();
                }
            }
        }
    }
}
