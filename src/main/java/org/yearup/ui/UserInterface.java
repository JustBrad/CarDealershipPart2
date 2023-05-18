package org.yearup.ui;

import org.yearup.ColorCodes;
import org.yearup.managers.ContractDataManager;
import org.yearup.managers.DealershipFileManager;
import org.yearup.models.Dealership;
import org.yearup.models.SalesContract;
import org.yearup.models.Vehicle;

import java.awt.*;
import java.util.ArrayList;
import java.util.Scanner;

public class UserInterface
{
    // Variables
    private Dealership dealership;
    DealershipFileManager fileManager;
    ContractDataManager contractManager = new ContractDataManager("contracts.csv");
    private static Scanner scanner = new Scanner(System.in);

    // Constructor
    public UserInterface()
    {

    }

    // Load dealership
    private void init()
    {
        fileManager = new DealershipFileManager("inventory.csv");
        this.dealership = fileManager.getDealership();
        System.out.println("\nTravelling to " + dealership.getName() + "...");
    }

    // Methods
    public void display()
    {
        init();

        while(true)
        {
            printTitle("WELCOME TO " + dealership.getName().toUpperCase());
            System.out.println("What would you like to do?");
            System.out.println();
            System.out.println(ColorCodes.YELLOW + "1) Search Vehicle by Price");
            System.out.println("2) Search Vehicle by Make/Model");
            System.out.println("3) Search Vehicle by Year");
            System.out.println("4) Search Vehicle by Color");
            System.out.println("5) Search Vehicle by Mileage");
            System.out.println("6) Search Vehicle by Type");
            System.out.println(ColorCodes.CYAN + "7) List All Vehicles");
            System.out.println(ColorCodes.GREEN + "8) Add a Vehicle");
            System.out.println(ColorCodes.RED + "9) Remove a Vehicle");
            System.out.println(ColorCodes.ORANGE + "10) Buy/Lease a Vehicle");
            System.out.println(ColorCodes.PURPLE + "0) Exit" + ColorCodes.RESET);
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
                case 0:
                    System.exit(0);
                    break;
                case 1:
                    // Price
                    processGetByPriceRequest();
                    break;
                case 2:
                    // Make/Model
                    processGetByMakeModelRequest();
                    break;
                case 3:
                    // Year
                    processGetByYearRequest();
                    break;
                case 4:
                    // Color
                    processGetByColorRequest();
                    break;
                case 5:
                    // Mileage
                    processGetByMileageRequest();
                    break;
                case 6:
                    // Type
                    processGetByVehicleTypeRequest();
                    break;
                case 7:
                    // List all
                    processGetAllVehiclesRequest();
                    break;
                case 8:
                    // Add vehicle
                    processAddVehicleRequest();
                    break;
                case 9:
                    // Remove vehicle
                    processRemoveVehicleRequest();
                    break;
                case 10:
                    // Buy/Lease vehicle
                    processBuyLeaseRequest();
                    break;
            }

        }
    }

    public void printTitle(String title)
    {
        System.out.println("\n" + ColorCodes.BLACK_BACKGROUND + "---------- " + title.toUpperCase() +  " ----------" + ColorCodes.RESET + "\n");
    }

    public void printGreenMessage(String message)
    {
        System.out.println("\n" + ColorCodes.BLACK_BACKGROUND + ColorCodes.GREEN + message + ColorCodes.RESET + "\n");
    }

    public void printRedMessage(String message)
    {
        System.out.println("\n" + ColorCodes.BLACK_BACKGROUND + ColorCodes.RED + message + ColorCodes.RESET + "\n");
    }

    public void printInvalid()
    {
        System.out.println("\n" + ColorCodes.BLACK_BACKGROUND + ColorCodes.RED + "INVALID INPUT" + ColorCodes.RESET + "\n");
    }

    public void printLabels()
    {
        System.out.println(ColorCodes.BLACK_BACKGROUND + " VIN    YEAR    MAKE                 MODEL                TYPE       COLOR      MILES         PRICE   " + ColorCodes.RESET);
        System.out.println("------------------------------------------------------------------------------------------------------");
    }

    public void printEntry(Vehicle v)
    {
        switch(v.getColor())
        {
            case "BLUE":
                System.out.printf("%-7d %-7d %-20s %-20s %-10s" + ColorCodes.CYAN + " %-10s " + ColorCodes.RESET + "%-10d $ %9.2f\n", v.getVin(), v.getYear(), v.getMake(), v.getModel(), v.getVehicleType(), v.getColor(), v.getOdometer(), v.getPrice());
                System.out.println("------------------------------------------------------------------------------------------------------");
                break;
            case "RED":
                System.out.printf("%-7d %-7d %-20s %-20s %-10s" + ColorCodes.RED + " %-10s " + ColorCodes.RESET + "%-10d $ %9.2f\n", v.getVin(), v.getYear(), v.getMake(), v.getModel(), v.getVehicleType(), v.getColor(), v.getOdometer(), v.getPrice());
                System.out.println("------------------------------------------------------------------------------------------------------");
                break;
            case "PURPLE":
                System.out.printf("%-7d %-7d %-20s %-20s %-10s" + ColorCodes.PURPLE + " %-10s " + ColorCodes.RESET + "%-10d $ %9.2f\n", v.getVin(), v.getYear(), v.getMake(), v.getModel(), v.getVehicleType(), v.getColor(), v.getOdometer(), v.getPrice());
                System.out.println("------------------------------------------------------------------------------------------------------");
                break;
            case "YELLOW":
                System.out.printf("%-7d %-7d %-20s %-20s %-10s" + ColorCodes.YELLOW + " %-10s " + ColorCodes.RESET + "%-10d $ %9.2f\n", v.getVin(), v.getYear(), v.getMake(), v.getModel(), v.getVehicleType(), v.getColor(), v.getOdometer(), v.getPrice());
                System.out.println("------------------------------------------------------------------------------------------------------");
                break;
            case "GREEN":
                System.out.printf("%-7d %-7d %-20s %-20s %-10s" + ColorCodes.GREEN + " %-10s " + ColorCodes.RESET + "%-10d $ %9.2f\n", v.getVin(), v.getYear(), v.getMake(), v.getModel(), v.getVehicleType(), v.getColor(), v.getOdometer(), v.getPrice());
                System.out.println("------------------------------------------------------------------------------------------------------");
                break;
            case "ORANGE":
            case "BROWN":
                System.out.printf("%-7d %-7d %-20s %-20s %-10s" + ColorCodes.ORANGE + " %-10s " + ColorCodes.RESET + "%-10d $ %9.2f\n", v.getVin(), v.getYear(), v.getMake(), v.getModel(), v.getVehicleType(), v.getColor(), v.getOdometer(), v.getPrice());
                System.out.println("------------------------------------------------------------------------------------------------------");
                break;
            case "BLACK":
                System.out.printf("%-7d %-7d %-20s %-20s %-10s" + ColorCodes.BLACK + " %-10s " + ColorCodes.RESET + "%-10d $ %9.2f\n", v.getVin(), v.getYear(), v.getMake(), v.getModel(), v.getVehicleType(), v.getColor(), v.getOdometer(), v.getPrice());
                System.out.println("------------------------------------------------------------------------------------------------------");
                break;
            case "GRAY":
            case "GREY":
            case "SILVER":
                System.out.printf("%-7d %-7d %-20s %-20s %-10s" + ColorCodes.WHITE + " %-10s " + ColorCodes.RESET + "%-10d $ %9.2f\n", v.getVin(), v.getYear(), v.getMake(), v.getModel(), v.getVehicleType(), v.getColor(), v.getOdometer(), v.getPrice());
                System.out.println("------------------------------------------------------------------------------------------------------");
                break;
            default:
                System.out.printf("%-7d %-7d %-20s %-20s %-10s %-10s %-10d $ %9.2f\n", v.getVin(), v.getYear(), v.getMake(), v.getModel(), v.getVehicleType(), v.getColor(), v.getOdometer(), v.getPrice());
                System.out.println("------------------------------------------------------------------------------------------------------");
                break;
        }
    }

    public void displayVehicles(ArrayList<Vehicle> vehicles)
    {
        int count = 0;
        System.out.println();
        printLabels();
        for(Vehicle v : vehicles)
        {
            printEntry(v);
            count++;
        }

        // Print how many found
        if(vehicles.size() > 1)
        {
            printGreenMessage("FOUND " + vehicles.size() + " VEHICLES");
        }
        else if(vehicles.size() == 1)
        {
            printGreenMessage("FOUND 1 VEHICLE");
        }
        else
        {
            printRedMessage("NO VEHICLES FOUND");
        }
    }

    public void processBuyLeaseRequest()
    {
        // SALE / LEASE
        String buyLease = "";
        Vehicle v;

        // Display choices to user
        processGetAllVehiclesRequest();
        printTitle("BUY / LEASE A VEHICLE");

        // Get DATE
        System.out.print("Enter a date: ");
        String date = scanner.nextLine().strip();

        // Get VIN
        while(true)
        {
           try
           {
               System.out.print("Which vehicle are you interested in (VIN)? ");
               int vin = Integer.parseInt(scanner.nextLine().strip());

               // Make sure VIN is in dealership
               if (dealership.getVehicleByVin(vin) != null)
               {
                   // Display selection
                   v = dealership.getVehicleByVin(vin);
                   printTitle("YOU SELECTED");
                   System.out.println("------------------------------------------------------------------------------------------------------");
                   printEntry(v);
                   break;
               } else
               {
                   printRedMessage("VIN NOT FOUND");
               }
           }
           catch(Exception e)
           {
               printInvalid();
           }
        }

        // Get NAME & EMAIL
        System.out.print("Enter customer name: ");
        String customerName = scanner.nextLine().toUpperCase().strip();
        System.out.print("Enter customer email: ");
        String customerEmail = scanner.nextLine().toUpperCase().strip();


        // Get BUY / LEASE
        while(true)
        {
            System.out.print("Enter BUY or LEASE: ");
            buyLease = scanner.nextLine().toUpperCase().strip();

            if(buyLease.equalsIgnoreCase("BUY") || buyLease.equalsIgnoreCase("LEASE"))
            {
                break;
            }
            else
            {
                printInvalid();
            }
        }

        // Handle SALE
        if(buyLease.equalsIgnoreCase("BUY"))
        {
            // Get isFinanced
            boolean isFinanced;

            while(true)
            {
                System.out.print("Are you financing this vehicle? (Y/N) ");
                String finance = scanner.nextLine().toUpperCase().strip();
                switch(finance)
                {
                    case "Y":
                        isFinanced = true;
                        break;
                    case"N":
                        isFinanced = false;
                        break;
                    default:
                        printInvalid();
                        continue;
                }
                break;
            }


            SalesContract salesContract = new SalesContract(date, customerName, customerEmail, v, isFinanced);
            contractManager.addContract(salesContract);
            contractManager.saveContracts();
            dealership.remove(v);
            printGreenMessage(customerName + " BOUGHT THE " + v.getYear() + " " + v.getMake() + " " + v.getModel() + "!");
        }
    }

    public void processGetByPriceRequest()
    {
        printTitle("SEARCH BY PRICE");
        double min;
        double max;
        while(true)
        {
            try
            {
                System.out.print("Enter minimum price: ");
                min = Double.parseDouble(scanner.nextLine().strip());
                break;
            }
            catch(Exception e)
            {
                printInvalid();
            }
        }
        while(true)
        {
            try
            {
                System.out.print("Enter maximum price: ");
                max = Double.parseDouble(scanner.nextLine().strip());
                break;
            }
            catch(Exception e)
            {
                printInvalid();
            }
        }
        displayVehicles(dealership.getVehiclesByPrice(min, max));
    }

    public void processGetByMakeModelRequest()
    {
        printTitle("SEARCH BY MAKE/MODEL");
        System.out.print("Enter make: ");
        String make = scanner.nextLine().strip();
        System.out.print("Enter model: ");
        String model = scanner.nextLine().strip();
        displayVehicles(dealership.getVehiclesByMakeModel(make, model));
    }

    public void processGetByYearRequest()
    {
        printTitle("SEARCH BY YEAR");
        int min;
        int max;
        while(true)
        {
            try
            {
                System.out.print("Enter minimum year: ");
                min = Integer.parseInt(scanner.nextLine().strip());
                break;
            }
            catch(Exception e)
            {
                printInvalid();
            }
        }
        while(true)
        {
            try
            {
                System.out.print("Enter maximum year: ");
                max = Integer.parseInt(scanner.nextLine().strip());
                break;
            }
            catch(Exception e)
            {
                printInvalid();
            }
        }
        displayVehicles(dealership.getVehiclesByYear(min, max));
    }

    public void processGetByColorRequest()
    {
        printTitle("SEARCH BY COLOR");
        System.out.print("Enter color: ");
        String color = scanner.nextLine().strip();
        displayVehicles(dealership.getVehiclesByColor(color));
    }

    public void processGetByMileageRequest()
    {
        printTitle("SEARCH BY MILEAGE");
        int min;
        int max;
        while(true)
        {
            try
            {
                System.out.print("Enter minimum miles: ");
                min = Integer.parseInt(scanner.nextLine().strip());
                break;
            }
            catch(Exception e)
            {
                printInvalid();
            }
        }
        while(true)
        {
            try
            {
                System.out.print("Enter maximum miles: ");
                max = Integer.parseInt(scanner.nextLine().strip());
                break;
            }
            catch(Exception e)
            {
                printInvalid();
            }
        }
        displayVehicles(dealership.getVehiclesByMileage(min, max));
    }

    public void processGetByVehicleTypeRequest()
    {
        printTitle("SEARCH BY VEHICLE TYPE");
        System.out.print("Enter type: ");
        String type = scanner.nextLine().strip();
        displayVehicles(dealership.getVehiclesByType(type));
    }

    public void processGetAllVehiclesRequest()
    {
        displayVehicles(dealership.getAllVehicles());
    }

    public void processAddVehicleRequest()
    {
        printTitle("ADD A VEHICLE");
        int vin;
        int year;
        int odometer;
        double price;

        while(true)
        {
            try
            {
                System.out.print("Enter VIN: ");
                vin = Integer.parseInt(scanner.nextLine().strip());
                break;
            } catch (Exception e)
            {
                printInvalid();
            }
        }
        while(true)
        {
            try
            {
                System.out.print("Enter year: ");
                year = Integer.parseInt(scanner.nextLine().strip());
                break;
            }
            catch(Exception e)
            {
                printInvalid();
            }
        }
        System.out.print("Enter make: ");
        String make = scanner.nextLine().strip();
        System.out.print("Enter model: ");
        String model = scanner.nextLine().strip();
        System.out.print("Enter type: ");
        String type = scanner.nextLine().strip();
        System.out.print("Enter color: ");
        String color = scanner.nextLine().strip();
        while(true)
        {
            try
            {
                System.out.print("Enter mileage: ");
                odometer = Integer.parseInt(scanner.nextLine().strip());
                break;
            }
            catch(Exception e)
            {
                printInvalid();
            }
        }
        while(true)
        {
            try
            {
                System.out.print("Enter price: $");
                price = Double.parseDouble(scanner.nextLine().strip());
                break;
            }
            catch(Exception e)
            {
                printInvalid();
            }
        }

        Vehicle vehicle = new Vehicle(vin, year, make.toUpperCase(), model.toUpperCase(), type.toUpperCase(), color.toUpperCase(), odometer, price);
        dealership.addVehicle(vehicle);
        fileManager.saveDealership(dealership);

        printGreenMessage("VEHICLE ADDED");
    }

    public void processRemoveVehicleRequest()
    {
        // Display so user can see VINs
        processGetAllVehiclesRequest();
        printTitle("REMOVE A VEHICLE");
        int vin;

        while(true)
        {
            try
            {
                System.out.print("Enter VIN to remove: ");
                vin = Integer.parseInt(scanner.nextLine().strip());
                break;
            }
            catch(Exception e)
            {
                printInvalid();
            }
        }

        for(Vehicle v : dealership.getAllVehicles())
        {
            if(v.getVin() == vin)
            {
                dealership.remove(v);
                fileManager.saveDealership(dealership);

                printGreenMessage("VEHICLE REMOVED");
                return;
            }
        }
        printRedMessage("VIN NOT FOUND");
    }

}
