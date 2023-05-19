package org.yearup.models;

public class LeaseContract extends Contract
{
    private double originalPrice = super.getVehicleSold().getPrice();
    private String type;

    public LeaseContract(String date, String customerName, String customerEmail, Vehicle vehicleSold)
    {
        super(date, customerName, customerEmail, vehicleSold);
        this.type = "LEASE";
    }

    @Override
    public double getTotalPrice()
    {
        return (originalPrice - getExpectedEndingValue()) + getLeaseFee();
    }

    @Override
    public double getMonthlyPayment()
    {
        return calculateMonthlyPayment(getTotalPrice(), 0.04, 36);
    }

    public double getExpectedEndingValue()
    {
        return originalPrice / 2;
    }

    public double getLeaseFee()
    {
        return originalPrice * 0.07;
    }

    public String getType()
    {
        return type;
    }
}
