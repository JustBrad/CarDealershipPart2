package org.yearup.managers;

import org.yearup.models.Dealership;
import org.yearup.models.Vehicle;

import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class DealershipFileManager
{
    private String fileName;
    private FileWriter fileWriter = null;

    public DealershipFileManager(String fileName)
    {
        this.fileName = fileName;
    }

    public Dealership getDealership()
    {
        Dealership dealership = null;
        FileInputStream fileStream = null;
        Scanner scanner = null;
        String dealerName;
        String address;
        String phone;
        int vin;
        int year;
        String make;
        String model;
        String vehicleType;
        String color;
        int odometer;
        double price;

        try
        {
            fileStream = new FileInputStream(fileName);
            scanner = new Scanner(fileStream);
            int lineCount = 0;

            // Loop through file
            while(scanner.hasNextLine())
            {
                String line = scanner.nextLine();
                String[] columns = line.split("\\|");

                // First line has Dealership info
                if(lineCount == 0)
                {
                    dealerName = columns[0];
                    address = columns[1];
                    phone = columns[2];
                    dealership = new Dealership(dealerName.toUpperCase(), address.toUpperCase(), phone);
                }
                // Rest are vehicles
                else
                {
                    vin = Integer.parseInt(columns[0]);
                    year = Integer.parseInt(columns[1]);
                    make = columns[2];
                    model = columns[3];
                    vehicleType = columns[4];
                    color = columns[5];
                    odometer = Integer.parseInt(columns[6]);
                    price = Double.parseDouble(columns[7]);

                    Vehicle vehicle = new Vehicle(vin, year, make.toUpperCase(), model.toUpperCase(), vehicleType.toUpperCase(), color.toUpperCase(), odometer, price);
                    dealership.addVehicle(vehicle);
                }

                lineCount++;
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
        return dealership;
    }

    public void saveDealership(Dealership dealership)
    {
        ArrayList<Vehicle> vehicles = dealership.getAllVehicles();

        try
        {
            // Create file writer
            fileWriter = new FileWriter(fileName);

            // Write first line (Dealership info)
            fileWriter.write(dealership.getName().toUpperCase() + "|" + dealership.getAddress().toUpperCase() + "|" + dealership.getPhone() + "\n");

            // Write all vehicles
            for(Vehicle v : vehicles)
            {
                fileWriter.write(v.getVin() + "|" + v.getYear() + "|" + v.getMake().toUpperCase() + "|" + v.getModel().toUpperCase() + "|" + v.getVehicleType().toUpperCase() + "|" + v.getColor().toUpperCase() + "|" + v.getOdometer() + "|" + v.getPrice() + "\n");
            }
        }
        catch(IOException e)
        {
            System.out.println(e.getMessage());
        }
        finally
        {
            if(fileWriter != null)
            {
                try
                {
                    fileWriter.close();
                }
                catch(Exception e)
                {
                    System.out.println(e.getMessage());
                }
            }
        }
    }
}
