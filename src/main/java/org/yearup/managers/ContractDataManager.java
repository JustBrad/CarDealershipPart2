package org.yearup.managers;

import org.yearup.models.*;

import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.rmi.dgc.Lease;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Scanner;

public class ContractDataManager
{
    private String fileName;
    private FileWriter fileWriter = null;

    public ContractDataManager(String fileName)
    {
        this.fileName = fileName;
        loadContracts();
    }

    // Read through CSV and return ArrayList of contracts
    public ArrayList<Contract> loadContracts()
    {
        FileInputStream fileStream = null;
        Scanner scanner = null;
        ArrayList<Contract> listOfContracts = new ArrayList<>();

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
                    listOfContracts.add(salesContract);
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
                    listOfContracts.add(leaseContract);
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
        return listOfContracts;
    }


    public void saveContracts(ArrayList<Contract> contractsToSave)
    {
        // Format decimals
        DecimalFormat decimalFormat = new DecimalFormat("#0.00");

        try
        {
            // Create file writer
            fileWriter = new FileWriter(fileName);

            for(Contract contract : contractsToSave)
            {
                if (contract instanceof SalesContract salesContract)
                {
                    String date = salesContract.getDate();
                    String name = salesContract.getCustomerName();
                    String email = salesContract.getCustomerEmail();
                    int vin = salesContract.getVehicleSold().getVin();
                    int year = salesContract.getVehicleSold().getYear();
                    String make = salesContract.getVehicleSold().getMake();
                    String model = salesContract.getVehicleSold().getModel();
                    String type = salesContract.getVehicleSold().getVehicleType();
                    String color = salesContract.getVehicleSold().getColor();
                    int odometer = salesContract.getVehicleSold().getOdometer();
                    double price = salesContract.getVehicleSold().getPrice();
                    double salesTax = salesContract.getSalesTax();
                    double recordingFee = salesContract.getRecordingFee();
                    double processingFee = salesContract.getProcessingFee();
                    double totalCost = salesContract.getTotalPrice();
                    boolean isFinanced = salesContract.isFinanced();
                    String yesNo;
                    if (isFinanced)
                        yesNo = "YES";
                    else
                        yesNo = "NO";
                    double monthlyPayment = salesContract.getMonthlyPayment();

                    fileWriter.write("SALE|" + date + "|" + name + "|" + email + "|" +
                            vin + "|" + year + "|" + make + "|" + model + "|" + type + "|" + color + "|" + odometer + "|" + decimalFormat.format(price) + "|" +
                            decimalFormat.format(salesTax) + "|" + decimalFormat.format(recordingFee) + "|" + decimalFormat.format(processingFee) + "|" + decimalFormat.format(totalCost) + "|" + yesNo + "|" + decimalFormat.format(monthlyPayment) + "\n");
                } else if (contract instanceof LeaseContract leaseContract)
                {
                    String date = leaseContract.getDate();
                    String name = leaseContract.getCustomerName();
                    String email = leaseContract.getCustomerEmail();
                    int vin = leaseContract.getVehicleSold().getVin();
                    int year = leaseContract.getVehicleSold().getYear();
                    String make = leaseContract.getVehicleSold().getMake();
                    String model = leaseContract.getVehicleSold().getModel();
                    String type = leaseContract.getVehicleSold().getVehicleType();
                    String color = leaseContract.getVehicleSold().getColor();
                    int odometer = leaseContract.getVehicleSold().getOdometer();
                    double price = leaseContract.getVehicleSold().getPrice();
                    double expectedEndingValue = leaseContract.getExpectedEndingValue();
                    double leaseFee = leaseContract.getLeaseFee();
                    double totalCost = leaseContract.getTotalPrice();
                    double monthlyPayment = leaseContract.getMonthlyPayment();

                    fileWriter.write("LEASE|" + date + "|" + name + "|" + email + "|" +
                            vin + "|" + year + "|" + make + "|" + model + "|" + type + "|" + color + "|" + odometer + "|" + decimalFormat.format(price) + "|" +
                            decimalFormat.format(expectedEndingValue) + "|" + decimalFormat.format(leaseFee) + "|" + decimalFormat.format(totalCost) + "|" + decimalFormat.format(monthlyPayment) + "\n");
                }
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

