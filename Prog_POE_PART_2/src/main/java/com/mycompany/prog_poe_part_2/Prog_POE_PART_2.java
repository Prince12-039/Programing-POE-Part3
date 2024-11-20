/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.prog_poe_part_2;


import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;

/**
 *
 * @author RC_Student_lab
 */
public class Prog_POE_PART_2 {  static String FirstName;
    static String LastName;
    static String registeredUsername;
    static String registeredPassword;
    static String username;
    static String password;
    
    static int TaskNumber = 0;
    static final String[] statusOptions = {"To Do", "Doing", "Done"};
    static List<Task> tasks = new ArrayList<>();
    
    public static void main(String[] args) {
      
        register();
        
        login();
        
        ADDTask();
        
        boolean exitMenu = false; // Control for menu loop
        while (!exitMenu) {
            String menuInput = JOptionPane.showInputDialog(
                "Please select an option:\n" +
                "1. Add Task\n" +
                "2. Display Task Report\n" +
                "3. Display Completed Tasks\n" +
                "4. Display Longest Task\n" +
                "5. Search Task by Name\n" +
                "6. Search Tasks by Developer\n" +
                "7. Delete Task\n" +
                "8. Quit");

            switch (menuInput) {
                case "1":
                     ADDTask();
                    break;
                case "2":
                    displayReport();
                    break;
                case "3":
                    displayCompletedTasks();
                    break;
                case "4":
                    displayLongestTask();
                    break;
                case "5":
                    searchTaskByName();
                    break;
                case "6":
                    searchTasksByDeveloper();
                    break;
                case "7":
                    deleteTask();
                    break;
                case "8":
                    exitMenu = true;
                    JOptionPane.showMessageDialog(null, "Thank you for using the Easy KanBan System!");
                    break;
                default:
                    JOptionPane.showMessageDialog(null, "Invalid option. Please select a valid option.");
            }
        }
    }
        
public static void register() {

     boolean validusername = false;
     boolean validpassword = false;
     boolean RegisterSuccess = false;
     
       FirstName = JOptionPane.showInputDialog("Please enter your first Name");
       LastName = JOptionPane.showInputDialog("please enter your last Name");
     
      while (!validusername){
       registeredUsername = JOptionPane.showInputDialog("Please enter your username");
        if(registeredUsername.contains("_") && registeredUsername.length() <= 5){
         JOptionPane.showMessageDialog(null,"Username captured successfully");
         validusername = true;
        }else{
        JOptionPane.showMessageDialog(null,"username is invalid, please ensure that your usernme contains"
                + "an underscore(_) and is less than 5 characters");
        continue;
        }
        while(!validpassword){
       registeredPassword = JOptionPane.showInputDialog("please enter your password");
          if (registeredPassword!= null &&registeredPassword.length()>=8 &&
              registeredPassword.matches(".*[A-Z]*.")&&
               registeredPassword.matches(".*[0-9]*.") &&
               registeredPassword.matches(".*[!@#$%^&*()]*.")){
         JOptionPane.showMessageDialog(null,"password successfully captured");
         validpassword = true;
        }else{
        JOptionPane.showMessageDialog(null,"Password is not correctly formatted, please ensure that the password contains atleast 8 characters"
        + ", a capital letter, a number and a special character");
        continue;
        }
          if (!RegisterSuccess) {
            JOptionPane.showMessageDialog(null, 
            "Registered successfully! welcome " + FirstName + " " + LastName + "\n" + ", It is great to see you.");
            RegisterSuccess = true;
        } else {
            JOptionPane.showMessageDialog(null, "Unsuccessfully registered! Invalid username or password.");
        }
    }
      
        JOptionPane.showMessageDialog( null,
                "First Name :" + FirstName + 
                "\n" + "Last Name : " + LastName +
                "\n" + "Username : " + registeredUsername +
                "\n" + "password : " + registeredPassword);
        
       
        }
     }
   public static void login()  {
        boolean validusername = false;
        boolean validpassword = false;
        boolean loginSuccess = false;
        
        while (!validusername){
        username = JOptionPane.showInputDialog("Please enter your registered username");
        if(username.equals(registeredUsername)){
         JOptionPane.showMessageDialog(null,"Username captured successfully");
         validusername = true;
        }else{
        JOptionPane.showMessageDialog(null,"username is invalid, please ensure that your usernme contains"
                + "an underscore(_) and is less than 5 characters");
        continue;
        }
        while(!validpassword){
        password = JOptionPane.showInputDialog("please enter your registered password");
         if(password.equals(registeredPassword)){
         JOptionPane.showMessageDialog(null,"password successfully captured");
         validpassword = true;
        }else{
        JOptionPane.showMessageDialog(null,"Password is not correctly formatted, please ensure that the password contains atleast 8 characters"
        + ", a capital letter, a number and a special character");
        continue;
        }
          if (!loginSuccess) {
            JOptionPane.showMessageDialog(null, "Welcome " + FirstName + " " + LastName + 
            "\n " + ",It is great to see you, again.");
            loginSuccess = true;
        } else {
            JOptionPane.showMessageDialog(null, "Username or Password incorrect, please try again.");
        }
    }  
}
    }
   
    public static void ADDTask() {
        String taskName = JOptionPane.showInputDialog(null, "Please enter your task name.");
        String taskDescription = JOptionPane.showInputDialog(null, "Please describe your task.");
        int taskDuration = Integer.parseInt(JOptionPane.showInputDialog(null, "Please enter the task duration in hours."));

        int selectedOption = JOptionPane.showOptionDialog(null, "Select the task status:", "Task Status",
            JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, statusOptions, statusOptions[0]);
        String taskStatus = selectedOption != -1 ? statusOptions[selectedOption] : "To Do";

        TaskNumber++;
        String taskId = generateTaskID(FirstName, LastName, taskName, TaskNumber);
        String developerDetails = FirstName + " " + LastName;

        Task task = new Task(taskId, taskName, taskDescription, taskDuration, taskStatus, developerDetails);
        tasks.add(task);

        JOptionPane.showMessageDialog(null, "Task added successfully!\n" + task);
    }

    public static void displayReport() {
        StringBuilder report = new StringBuilder("Task Report:\n");
        for (Task task : tasks) {
            report.append(task).append("\n");
        }
        JOptionPane.showMessageDialog(null, report.toString());
    }

    public static void displayCompletedTasks() {
        StringBuilder report = new StringBuilder("Completed Tasks:\n");
        for (Task task : tasks) {
            if ("Done".equals(task.getStatus())) {
                report.append(task).append("\n");
            }
        }
        JOptionPane.showMessageDialog(null, report.toString());
    }

    public static void displayLongestTask() {
        if (tasks.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No tasks found.");
            return;
        }

        int maxDuration = tasks.stream()
            .mapToInt(Task::getDuration)
            .max()
            .orElse(0);

        StringBuilder result = new StringBuilder("Tasks with Longest Duration:\n");
        tasks.stream()
            .filter(task -> task.getDuration() == maxDuration)
            .forEach(task -> result.append(task).append("\n"));

        JOptionPane.showMessageDialog(null, result.toString());
    }

    public static void searchTaskByName() {
        String taskName = JOptionPane.showInputDialog(null, "Enter the task name to search:");
        for (Task task : tasks) {
            if (task.getName().equalsIgnoreCase(taskName)) {
                JOptionPane.showMessageDialog(null, "Task Found:\n" + task);
                return;
            }
        }
        JOptionPane.showMessageDialog(null, "Task not found.");
    }

    public static void searchTasksByDeveloper() {
        String developerName = JOptionPane.showInputDialog(null, "Enter the developer name:");
        StringBuilder result = new StringBuilder("Tasks by " + developerName + ":\n");
        for (Task task : tasks) {
            if (task.getDeveloper().equalsIgnoreCase(developerName)) {
                result.append(task).append("\n");
            }
        }
        JOptionPane.showMessageDialog(null, result.toString());
    }

    public static void deleteTask() {
        String taskName = JOptionPane.showInputDialog(null, "Enter the task name to delete:");
        tasks.removeIf(task -> task.getName().equalsIgnoreCase(taskName));
        JOptionPane.showMessageDialog(null, "Task deleted successfully.");
    }

    public static String generateTaskID(String firstName, String lastName, String taskName, int taskNumber) {
        return firstName.substring(0, 1).toUpperCase() + lastName.substring(0, 1).toUpperCase() + "-" + taskName.toUpperCase() + "-" + taskNumber;
    }

    static class Task {
        private final String id;
        private final String name;
        private final String description;
        private final int duration;
        private final String status;
        private final String developer;

        public Task(String id, String name, String description, int duration, String status, String developer) {
            this.id = id;
            this.name = name;
            this.description = description;
            this.duration = duration;
            this.status = status;
            this.developer = developer;
        }

        public String getName() { return name; }
        public int getDuration() { return duration; }
        public String getStatus() { return status; }
        public String getDeveloper() { return developer; }

        @Override
        public String toString() {
            return "Task ID: " + id + ", Name: " + name + ", Description: " + description +
                   ", Duration: " + duration + " hours, Status: " + status + ", Developer: " + developer;
        }
    }
}