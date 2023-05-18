package org.yearup.managers;

import org.junit.jupiter.api.Test;
import org.yearup.models.Contract;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class ContractDataManagerTest
{
    @Test
    public void loadContracts_should_return_arrayListOfAllContracts()
    {
        ContractDataManager contractDataManager = new ContractDataManager("contracts.csv");
        ArrayList<Contract> contracts = contractDataManager.loadContracts();
        assertNotEquals(contracts, null, "because contracts arraylist should be loaded.");
    }
}