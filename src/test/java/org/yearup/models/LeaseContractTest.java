package org.yearup.models;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LeaseContractTest
{
    @Test
    public void getMonthlyPayment_should_return_moreThan555_sinceOriginalPriceWas20000()
    {
        boolean inRange;
        Vehicle v = new Vehicle(12345, 2019, "honda", "civic", "coupe", "red", 10000, 20000);
        LeaseContract myContract = new LeaseContract("01/01/2020", "Customer", "customer@gmail.com", v);

        System.out.println(myContract.getMonthlyPayment());
        if(myContract.getMonthlyPayment() > 555 && myContract.getMonthlyPayment() < 700)
        {
            inRange = true;
        }
        else
        {
            inRange = false;
        }

        assertTrue(inRange, "because 20,000 / 36 = 555.56, so with interest it should be higher");
    }
}