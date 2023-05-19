package org.yearup.ui;

import org.yearup.ColorCodes;
import org.yearup.models.*;

import java.util.ArrayList;
import java.util.Scanner;

public class AdminUserInterface extends UserInterface
{
    private static final Scanner scanner = new Scanner(System.in);
    private ArrayList<Contract> contractInventory;

    public AdminUserInterface(Dealership dealership)
    {
        contractInventory = dealership.getContractInventory();
    }

    public void displayAdminMenu()
    {
        while(true)
        {
            printTitle("ADMIN MENU");
            System.out.println("What would you like to do?");
            System.out.println();
            System.out.println(ColorCodes.YELLOW + "1) List All Contracts");
            System.out.println(ColorCodes.YELLOW + "2) List All Sales Contracts");
            System.out.println(ColorCodes.YELLOW + "3) List All Lease Contracts");
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
                    displayAllContracts();
                }
                case 2 ->
                {
                    // Display All Sales Contracts
                    displayAllSalesContracts();
                }
                case 3 ->
                {
                    // Display All Lease Contracts
                    displayAllLeaseContracts();
                }
                default ->
                {
                    printInvalid();
                }
            }
        }
    }

    @Override
    public void printLabels()
    {
        System.out.println(ColorCodes.BLACK_BACKGROUND + " TYPE   DATE         NAME                 EMAIL                VIN        YEAR       MAKE            MODEL                    TOTAL  " + ColorCodes.RESET);
        System.out.println("-------------------------------------------------------------------------------------------------------------------------------------");
    }

    public void printEntry(Contract c)
    {
        if(c.getType().equalsIgnoreCase("SALE"))
        {
            System.out.printf(ColorCodes.YELLOW + "%-7s %-12s %-20s %-20s %-10d %-10d %-15s %-20s $ %9.2f" + ColorCodes.RESET + "\n", c.getType(), c.getDate(), c.getCustomerName(), c.getCustomerEmail(), c.getVehicleSold().getVin(), c.getVehicleSold().getYear(), c.getVehicleSold().getMake(), c.getVehicleSold().getModel(), c.getTotalPrice());
        }
        else
        {
            System.out.printf(ColorCodes.CYAN + "%-7s %-12s %-20s %-20s %-10d %-10d %-15s %-20s $ %9.2f" + ColorCodes.RESET + "\n", c.getType(), c.getDate(), c.getCustomerName(), c.getCustomerEmail(), c.getVehicleSold().getVin(), c.getVehicleSold().getYear(), c.getVehicleSold().getMake(), c.getVehicleSold().getModel(), c.getTotalPrice());
        }
        System.out.println("-------------------------------------------------------------------------------------------------------------------------------------");
    }

    public void displayAllContracts()
    {
        printTitle("ALL CONTRACTS");
        printLabels();
        int count = 0;
        for(Contract contract : contractInventory)
        {
            printEntry(contract);
            count++;
        }
        printGreenMessage("FOUND " + count + " CONTRACT(S)!");
    }

    public void displayAllSalesContracts()
    {
        printTitle("ALL SALES CONTRACTS");
        printLabels();
        int count = 0;
        for(Contract contract : contractInventory)
        {
            if(contract.getType().equalsIgnoreCase("SALE"))
            {
                printEntry(contract);
                count++;
            }
        }
        printGreenMessage("FOUND " + count + " SALES CONTRACT(S)!");
    }

    public void displayAllLeaseContracts()
    {
        printTitle("ALL LEASE CONTRACTS");
        printLabels();
        int count = 0;
        for(Contract contract : contractInventory)
        {
            if(contract.getType().equalsIgnoreCase("LEASE"))
            {
                printEntry(contract);
                count++;
            }
        }
        printGreenMessage("FOUND " + count + " LEASE CONTRACT(S)!");
    }

    public ArrayList<Contract> getSalesContracts()
    {
        ArrayList<Contract> salesContracts = new ArrayList<>();

        for(Contract contract : contractInventory)
        {
            if(contract instanceof SalesContract sc)
            {
                salesContracts.add(sc);
            }
        }

        return salesContracts;
    }

    public ArrayList<Contract> getLeaseContracts()
    {
        ArrayList<Contract> leaseContracts = new ArrayList<>();

        for(Contract contract : contractInventory)
        {
            if(contract instanceof LeaseContract lc)
            {
                leaseContracts.add(lc);
            }
        }

        return leaseContracts;
    }


}
