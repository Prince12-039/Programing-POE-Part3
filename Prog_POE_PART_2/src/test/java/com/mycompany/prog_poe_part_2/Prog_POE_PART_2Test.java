/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.mycompany.prog_poe_part_2;

import org.junit.Test;
import static org.junit.Assert.*;


/**
 *
 * @author RC_Student_lab
 */
public class Prog_POE_PART_2Test {

public class ProgPOETest {

    // Test Data
    String developer1FirstName = "Robyn";
    String developer1LastName = "Harrison";
    String task1Name = "Login Feature";
    String task1Description = "Create Login to authenticate users";
    int task1Duration = 8; // hours

    String developer2FirstName = "Mike";
    String developer2LastName = "Smith";
    String task2Name = "Add Task Feature";
    String task2Description = "Create Add Task feature to add task users";
    int task2Duration = 10; // hours

    @Test
    public void testTaskDescriptionLength() {
        // Test for Task 1 - should succeed
        String result = validateTaskDescription(task1Description);
        assertEquals("Task successfully captured.", result);
        
        // Test for invalid description
        String longDescription = "This is a very long description that exceeds fifty characters for testing purposes.";
        String failureResult = validateTaskDescription(longDescription);
        assertEquals("Please enter a task description of less than 50 characters.", failureResult);
    }

    @Test
    public void testGenerateTaskIDs() {
        // Generate Task IDs based on provided data
        String taskId1 = generateTaskID(developer1FirstName, developer1LastName, task1Name, 0);
        assertEquals("AD:1:BYN", taskId1);

        String taskId2 = generateTaskID(developer2FirstName, developer2LastName, task2Name, 1);
        assertEquals("CR:0:IKE", taskId2);

        // Loop through remaining task IDs as described
        String[] expectedTaskIds = {
            "CR:1:ARD",
            "CR:2:THA",
            "CR:3:ND"
        };

        for (int i = 0; i < expectedTaskIds.length; i++) {
            String generatedId = generateTaskID("DevName" + (i + 2), "LastName" + (i + 2), "Task" + (i + 2), i + 2);
            assertEquals(expectedTaskIds[i], generatedId);
        }
    }

    @Test
    public void testTotalHoursAccumulation() {
        int[] durations = {10, 12, 55, 11, 1};
        int totalHours = accumulateTotalHours(durations);
        assertEquals(89, totalHours); // Total should be 89

        // Additional test for Task 1 and Task 2
        int[] additionalDurations = {8, 10}; // Hours from Task 1 and Task 2
        int additionalTotalHours = accumulateTotalHours(additionalDurations);
        assertEquals(18, additionalTotalHours); // Total should be 18
    }

    // Sample helper methods to support the tests
    private String validateTaskDescription(String description) {
        if (description.length() <= 50) {
            return "Task successfully captured.";
        } else {
            return "Please enter a task description of less than 50 characters.";
        }
    }

    private String generateTaskID(String firstName, String lastName, String taskName, int taskNumber) {
        // Example logic for generating task ID
        String initials = firstName.charAt(0) + "" + lastName.charAt(0); // Simplified logic for initials
        return initials.toUpperCase() + ":" + taskNumber + ":" + taskName.substring(0, 3).toUpperCase();
    }

    private int accumulateTotalHours(int[] durations) {
        int total = 0;
        for (int duration : durations) {
            total += duration;
        }
        return total;
    }
}
}