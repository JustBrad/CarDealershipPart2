package org.yearup.managers;

import org.yearup.models.*;

import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.rmi.dgc.Lease;
import java.util.ArrayList;
import java.util.Scanner;

public class ContractDataManager
{
    private String fileName;
    private FileWriter fileWriter = null;

    public ContractDataManager(String fileName)
    {
        this.fileName = fileName;
    }

    public ArrayList<Contract> loadContracts()
    {
        ArrayList<Contract> contracts = new ArrayList<>();
        FileInputStream fileStream = null;
        Scanner scanner = null;

        try
        {
            fileStream = new FileInputStream(fileName);
            scanner = new Scanner(fileStream);

            // Loop through file
            while(scanner.hasNextLine())
            {
                String line = scanner.nextLine();
                String[] columns = line.split("\\|");

                if(columns[0].equalsIgnoreCase("SALE"))
                {
                    int vin = Integer.parseInt(columns[4]);
                    int year = Integer.parseInt(columns[5]);
                    String make = columns[6];
                    String model = columns[7];
                    String type = columns[8];
                    String color = columns[9];
                    int odometer = Integer.parseInt(columns[10]);
                    double price = Double.parseDouble(columns[11]);
                    Vehicle vehicle = new Vehicle(vin, year, make, model, type, color, odometer, price);

                    boolean isFinanced = Boolean.parseBoolean(columns[16]);
                    SalesContract salesContract = new SalesContract(columns[1], columns[2], columns[3], vehicle, isFinanced);
                    contracts.add(salesContract);
                }
                else if(columns[0].equalsIgnoreCase("LEASE"))
                {
                    int vin = Integer.parseInt(columns[4]);
                    int year = Integer.parseInt(columns[5]);
                    String make = columns[6];
                    String model = columns[7];
                    String type = columns[8];
                    String color = columns[9];
                    int odometer = Integer.parseInt(columns[10]);
                    double price = Double.parseDouble(columns[11]);
                    Vehicle vehicle = new Vehicle(vin, year, make, model, type, color, odometer, price);

                    LeaseContract leaseContract = new LeaseContract(columns[1], columns[2], columns[3], vehicle);
                    contracts.add(leaseContract);
                }
            }
        }
        catch (IOException e)
        {
            System.out.println(e.getMessage());
        }
        finally
        {
            try
            {
                if (fileStream != null)
                {
                    fileStream.close();
                }
            }
            catch (IOException e)
            {
                // If you can't close file
            }
            if (scanner != null)
            {
                scanner.close();
            }
        }
        return contracts;
    }
}
