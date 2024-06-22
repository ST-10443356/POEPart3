/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package javaapplication21;

import javax.swing.JOptionPane;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Task {
    private String status;
    private String developerFirstName;
    private String developerLastName;
    private int taskNumber;
    private String taskName;
    private String taskDescription;
    private String taskId;
    private int duration;

    public Task(String status, String developerFirstName, String developerLastName, int taskNumber, String taskName, String taskDescription, int duration) {
        this.status = status;
        this.developerFirstName = developerFirstName;
        this.developerLastName = developerLastName;
        this.taskNumber = taskNumber;
        this.taskName = taskName;
        this.taskDescription = taskDescription;
        this.taskId = createTaskID();
        this.duration = duration;
    }

    public boolean checkTaskDescription() {
        if (this.taskDescription.length() > 50) {
            JOptionPane.showMessageDialog(null, "Please enter a task description of less than 50 characters.");
            return false;
        } else {
            JOptionPane.showMessageDialog(null, "Task successfully captured.");
            return true;
        }
    }

    public String createTaskID() {
        return taskName.substring(0, 2).toUpperCase() + ":" + taskNumber + ":" + developerLastName.substring(Math.max(developerLastName.length() - 3, 0)).toUpperCase();
    }

    public String printTaskDetails() {
        return String.format("Status: %s\nDeveloper: %s %s\nTask Number: %d\nTask Name: %s\nTask Description: %s\nTask ID: %s\nDuration: %d hours",
                this.status, this.developerFirstName, this.developerLastName, this.taskNumber, this.taskName, this.taskDescription, this.taskId, this.duration);
    }

    public int getDuration() {
        return this.duration;
    }

    public String getStatus() {
        return this.status;
    }

    public String getDeveloperFullName() {
        return this.developerFirstName + " " + this.developerLastName;
    }

    public String getTaskName() {
        return this.taskName;
    }

    public String getTaskId() {
        return this.taskId;
    }
}

public class POEPart3 {
    List<Task> tasks = new ArrayList<>();
    private int totalTasks;

    public static String userName;
    public static String password;
    public static String firstName;
    public static String lastName;
    public static String registeredUserName;
    public static String registeredPassword;

    public POEPart3(int totalTasks) {
        this.totalTasks = totalTasks;
    }

    public void addTask(Task task) {
        if (task.checkTaskDescription()) {
            tasks.add(task);
        }
    }

    public int returnTotalHours() {
        int totalHours = 0;
        for (Task task : tasks) {
            totalHours += task.getDuration();
        }
        return totalHours;
    }

    public void displayAllTasks() {
        StringBuilder allTasksDetails = new StringBuilder();
        for (Task task : tasks) {
            allTasksDetails.append(task.printTaskDetails()).append("\n\n");
        }
        JOptionPane.showMessageDialog(null, allTasksDetails.toString());
    }

    public void displayWelcomeMessage() {
        JOptionPane.showMessageDialog(null, "Welcome to EasyKanban");
    }

    public static boolean checkUserName(String userName) {
        return userName.contains("_") && userName.length() == 5;
    }

    public static boolean checkPasswordComplexity(String password) {
        return password.matches("(?=.*[0-9])(?=.*[A-Z])(?=.*[!@#$%^&*]).{8,}");
    }

    public static void registerUser() {
        boolean result = checkUserName(userName);
        if (result) {
            System.out.println("Username successfully captured.");
        } else {
            System.out.println("Username is not correctly formatted, please ensure that your username contains an underscore and is no more than 5 characters.");
        }

        boolean resultP = checkPasswordComplexity(password);
        if (resultP) {
            System.out.println("Password successfully captured.");
        } else {
            System.out.println("Password is not correctly formatted, please ensure that the password contains at least 8 characters, a capital letter, a number, and a special character.");
        }
    }

    public static boolean loginUser() {
        return registeredPassword.equals(password) && registeredUserName.equals(userName);
    }

    public static void returnLoginStatus() {
        if (loginUser()) {
            System.out.println("Welcome " + firstName + ", " + lastName + " it is great to see you again");
        } else {
            System.out.println("Username or password incorrect, please try again.");
        }
    }

    public void displayDoneTasks() {
        StringBuilder doneTasks = new StringBuilder();
        for (Task task : tasks) {
            if ("Done".equalsIgnoreCase(task.getStatus())) {
                doneTasks.append(task.printTaskDetails()).append("\n\n");
            }
        }
        JOptionPane.showMessageDialog(null, doneTasks.toString());
    }

    public void displayLongestTask() {
        if (tasks.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No tasks available.");
            return;
        }
        Task longestTask = tasks.get(0);
        for (Task task : tasks) {
            if (task.getDuration() > longestTask.getDuration()) {
                longestTask = task;
            }
        }
        JOptionPane.showMessageDialog(null, "Longest Task:\n" + longestTask.printTaskDetails());
    }

    public void searchTaskByName(String taskName) {
        for (Task task : tasks) {
            if (task.getTaskName().equalsIgnoreCase(taskName)) {
                JOptionPane.showMessageDialog(null, task.printTaskDetails());
                return;
            }
        }
        JOptionPane.showMessageDialog(null, "Task with name '" + taskName + "' not found.");
    }

    public void searchTasksByDeveloper(String developerName) {
        StringBuilder developerTasks = new StringBuilder();
        for (Task task : tasks) {
            if (task.getDeveloperFullName().equalsIgnoreCase(developerName)) {
                developerTasks.append(task.printTaskDetails()).append("\n\n");
            }
        }
        if (developerTasks.length() > 0) {
            JOptionPane.showMessageDialog(null, developerTasks.toString());
        } else {
            JOptionPane.showMessageDialog(null, "No tasks found for developer '" + developerName + "'.");
        }
    }

    public void deleteTask(String taskName) {
        for (Task task : tasks) {
            if (task.getTaskName().equalsIgnoreCase(taskName)) {
                tasks.remove(task);
                JOptionPane.showMessageDialog(null, "Task '" + taskName + "' successfully deleted.");
                return;
            }
        }
        JOptionPane.showMessageDialog(null, "Task with name '" + taskName + "' not found.");
    }

    public void displayReport() {
        displayAllTasks();
    }

    public static void main(String[] args) {
        userName = JOptionPane.showInputDialog(null, "Enter Username");
        password = JOptionPane.showInputDialog(null, "Enter Password");

        if (checkUserName(userName) && checkPasswordComplexity(password)) {
            POEPart3 manager = new POEPart3(0);
            manager.displayWelcomeMessage();

            Scanner scanner = new Scanner(System.in);
            System.out.print("Enter the number of tasks you wish to enter: ");
            int numTasks = scanner.nextInt();
            scanner.nextLine();
            manager = new POEPart3(numTasks);

            while (true) {
                System.out.println("1. Add tasks");
                System.out.println("2. Show report");
                System.out.println("3. Display tasks with status 'Done'");
                System.out.println("4. Display task with the longest duration");
                System.out.println("5. Search task by name");
                System.out.println("6. Search tasks by developer");
                System.out.println("7. Delete task by name");
                System.out.println("8. Quit");
                System.out.print("Select an option: ");
                int option = scanner.nextInt();
                scanner.nextLine();

                if (option == 1) {
                    for (int i = 0; i < numTasks; i++) {
                        System.out.print("Enter task name: ");
                        String taskName = scanner.nextLine();

                        System.out.print("Enter developer first name: ");
                        String devFirstName = scanner.nextLine();

                        System.out.print("Enter developer last name: ");
                        String devLastName = scanner.nextLine();

                        System.out.print("Enter task description: ");
                        String taskDescription = scanner.nextLine();

                        System.out.print("Enter task duration in hours: ");
                        int duration = scanner.nextInt();
                        scanner.nextLine();

                        System.out.print("Enter task status (TO DO, DONE, DOING): ");
                        String status = scanner.nextLine();

                        Task task = new Task(status, devFirstName, devLastName, i, taskName, taskDescription, duration);
                        manager.addTask(task);
                    }

                    manager.displayAllTasks();

                } else if (option == 2) {
                    manager.displayReport();
                    } else if (option == 3) {
                    manager.displayDoneTasks();

                } else if (option == 4) {
                    manager.displayLongestTask();

                } else if (option == 5) {
                    System.out.print("Enter task name to search: ");
                    String taskName = scanner.nextLine();
                    manager.searchTaskByName(taskName);

                } else if (option == 6) {
                    System.out.print("Enter developer name to search: ");
                    String developerName = scanner.nextLine();
                    manager.searchTasksByDeveloper(developerName);

                } else if (option == 7) {
                    System.out.print("Enter task name to delete: ");
                    String taskName = scanner.nextLine();
                    manager.deleteTask(taskName);

                } else if (option == 8) {
                    System.out.println("Total hours: " + manager.returnTotalHours());
                    break;
                } else {
                    System.out.println("Invalid option. Please try again.");
                }
            }
            scanner.close();
        } else {
            System.out.println("Invalid username or password.");
        }
    }
}

               