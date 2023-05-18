package org.yearup.models;

public abstract class Contract
{
    private String date;
    private String customerName;
    private String customerEmail;
    private Vehicle vehicleSold;
    private double totalPrice;
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
        totalPrice = vehicleSold.getPrice();
        return totalPrice;
    }

    public abstract double getMonthlyPayment();

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
