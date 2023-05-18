package org.yearup.models;

public abstract class Contract
{
    private String date;
    private String customerName;
    private String customerEmail;
    private Vehicle vehicleSold;
    private double monthlyPayment;

    public Contract(String date, String customerName, String customerEmail, Vehicle vehicleSold)
    {
        this.date = date;
        this.customerName = customerName;
        this.customerEmail = customerEmail;
        this.vehicleSold = vehicleSold;
    }

    public double getTotalPrice()
    {
        return vehicleSold.getPrice();
    }

    public abstract double getMonthlyPayment();

    // Make sure apr is already divided by 100 ... (4.25% would be apr = 0.0425)
    public static double calculateMonthlyPayment(double total, double apr, int numberOfMonths) {
        double monthlyInterest = apr / 12;
        return (total * monthlyInterest * Math.pow(1 + monthlyInterest, numberOfMonths)) / (Math.pow(1 + monthlyInterest, numberOfMonths) - 1);
    }

    public String getDate()
    {
        return date;
    }

    public String getCustomerName()
    {
        return customerName;
    }

    public String getCustomerEmail()
    {
        return customerEmail;
    }

    public Vehicle getVehicleSold()
    {
        return vehicleSold;
    }
}
