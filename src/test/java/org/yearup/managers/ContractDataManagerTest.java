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
        assertNotEquals(contractDataManager.getContractInventory(), null, "because contracts arraylist should be loaded.");
    }

    @Test
    public void saveContracts_should_overwriteAllContractsInFile()
    {
        ContractDataManager contractDataManager = new ContractDataManager("contracts.csv");
        contractDataManager.saveContracts();
    }
}