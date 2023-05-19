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
        ArrayList<Contract> listOfContracts = contractDataManager.loadContracts();

        String expectedName = "DANA WYATT";
        String actualName = listOfContracts.get(0).getCustomerName();

        assertEquals(expectedName, actualName, "because the first contract in the list belongs to DANA WYATT");
    }

    @Test
    public void saveContracts_should_overwriteContractsFile_withSpecifiedListOfContracts()
    {
        ContractDataManager contractDataManager = new ContractDataManager("contracts.csv");
        ArrayList<Contract> listOfContracts = contractDataManager.loadContracts();
        contractDataManager.saveContracts(listOfContracts);
    }
}