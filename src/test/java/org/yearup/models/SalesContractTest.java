package org.yearup.models;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SalesContractTest
{
    @Test
    public void getMonthlyPayment_should_return_moreThan416_sinceOriginalPriceWas20000()
    {
        boolean inRange;
        Vehicle v = new Vehicle(12345, 2019, "honda", "civic", "coupe", "red", 10000, 20000);
        SalesContract myContract = new SalesContract("01/01/2020", "Customer", "customer@gmail.com", v, true);
        if(myContract.getMonthlyPayment() > 416 && myContract.getMonthlyPayment() < 550)
        {
            inRange = true;
        }
        else
        {
            inRange = false;
        }

        assertTrue(inRange, "because 20,000 / 48 = 416.67, so with interest it should be higher");
    }
}