package org.yearup.models;

public class SalesContract extends Contract
{
    private static final double recordingFee = 100;
    private boolean isFinanced;
    private double originalPrice = super.getVehicleSold().getPrice();
    private double salesTax = 0.05 * originalPrice;
    private double monthlyPayment;

    public SalesContract(String date, String customerName, String customerEmail, Vehicle vehicleSold,
                         boolean isFinanced)
    {
        super(date, customerName, customerEmail, vehicleSold);
        this.isFinanced = isFinanced;
    }

    @Override
    public double getTotalPrice()
    {
        // Original + sales tax on original + recording fee + processing fee
        return originalPrice + salesTax + getRecordingFee() + getProcessingFee();
    }

    @Override
    public double getMonthlyPayment()
    {
        if(!isFinanced)
        {
            return 0;
        }
        else
        {
            // if getTotalPrice >= $10,000 then 4.25% for 48 months
            if(getTotalPrice() >= 10000)
            {
                double apr = 0.0425;
                int numberOfMonths = 48;
                return calculateMonthlyPayment(getTotalPrice(), apr, numberOfMonths);
            }
            // else then 5.25% for 24 months
            else
            {
                double apr = 0.0525;
                int numberOfMonths = 24;
                return calculateMonthlyPayment(getTotalPrice(), apr, numberOfMonths);
            }
        }
    }

    public double getProcessingFee()
    {
        // ($295 if Original price < $10,000, else $495)
        if(originalPrice < 10000)
        {
            return 295;
        }
        else
        {
            return 495;
        }
    }

    public boolean isFinanced()
    {
        return isFinanced;
    }

    public double getSalesTax()
    {
        return salesTax;
    }

    public double getRecordingFee()
    {
        return recordingFee;
    }
}
