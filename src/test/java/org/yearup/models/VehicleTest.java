package org.yearup.models;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class VehicleTest
{
    @Test
    public void getColor_should_return_colorInAllCaps()
    {
        // Arrange
        Vehicle v = new Vehicle(12345, 2000, "Honda", "Civic", "COUPE", "blue", 230000, 2100);
        String expected = "BLUE";

        // Act
        String actual = v.getColor();

        // Assert
        assertEquals(expected, actual, "because color should return in ALL CAPS");
    }
}