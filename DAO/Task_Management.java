package DAO;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


import DTO.*;

public class TaskManagementSystem {
    private List<User> users;
    private List<Task> tasks;

    public TaskManagementSystem() {
        this.users = new ArrayList<>();
        this.tasks = new ArrayList<>();
    }
    //this method is used for registration of new user.
    public void registerUser(Scanner scanner) {
        System.out.print("Enter username: ");
        String username = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();
        User newUser = new User(username, password);
        users.add(newUser);
        System.out.println("User registered successfully!");
    }
    /*this method provides login access to the user. If the user or password
    is incorrect then it will tell invalid username/password*/
    public User loginUser(Scanner scanner) {
        System.out.print("Enter username: ");
        String username = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();
        for (User user : users) {
            if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
                System.out.println("User logged in successfully!");
                return user;
            }
        }
        System.out.println("Invalid username or password!");
        return null;
    }
    
    /*this method works once the user is logged in and this method is 
     used to create new tasks.*/
    public void createTask(Scanner scanner, User user) {
        System.out.print("Enter task title: ");
        String title = scanner.nextLine();
        System.out.print("Enter task description: ");
        String description = scanner.nextLine();
        System.out.print("Enter task due date: ");
        String dueDate = scanner.nextLine();
        Task newTask = new Task(title, description, dueDate, user);
        tasks.add(newTask);
        System.out.println("Task created successfully!");
    }
    
    /*this method is used to update the given task and here user can assign 
     the task to another user. */
    public void updateTask(Scanner scanner, Task task, User user) {
        System.out.print("Enter updated task title: ");
        String title = scanner.nextLine();
        System.out.print("Enter updated task description: ");
        String description = scanner.nextLine();
        System.out.print("Enter updated task due date: ");
        String dueDate = scanner.nextLine();
        System.out.print("Enter username to assign the task (leave blank to keep current assignment): ");
        String assignedUsername = scanner.nextLine();

        if (!assignedUsername.isEmpty()) {
            User assignedUser = getUserByUsername(assignedUsername);
            if (assignedUser == null) {
                System.out.println("User not found. Task assignment unchanged.");
                return;
            }
            task.setAssignedUser(assignedUser);
        }

        task.setTitle(title);
        task.setDescription(description);
        task.setDueDate(dueDate);
        System.out.println("Task updated successfully!");
    }

    /*this method is used to ukeep an update about the current task whether it is
     completed*/
    public void markTaskAsComplete(Task task) {
        task.setComplete(true);
        System.out.println("Task marked as complete!");
    }

    /*this method is used to ukeep an update about the current task whether it is
    not completed*/
    public void markTaskAsIncomplete(Task task) {
        task.setComplete(false);
        System.out.println("Task marked as incomplete!");
    }
    
    /*this method is used to delete the created task */
    public void deleteTask(Task task) {
        tasks.remove(task);
        System.out.println("Task deleted successfully!");
    }
    
    /*this method is used to search the assigned task*/
    public List<Task> searchTasks(String keyword) {
        List<Task> searchResults = new ArrayList<>();
        for (Task task : tasks) {
            if (task.getTitle().contains(keyword) || task.getDescription().contains(keyword)
                    || task.getAssignedUser().getUsername().contains(keyword)) {
                searchResults.add(task);
            }
        }
        return searchResults;
    }
    
    /*the below methods are used to filter the given task based on
     different condition            */
    public List<Task> filterTasksByCompletion(boolean isComplete) {
        List<Task> filteredTasks = new ArrayList<>();
        for (Task task : tasks) {
            if (task.isComplete() == isComplete) {
                filteredTasks.add(task);
            }
        }
        return filteredTasks;
    }

    public List<Task> filterTasksByDueDate(String dueDate) {
        List<Task> filteredTasks = new ArrayList<>();
        for (Task task : tasks) {
            if (task.getDueDate().equals(dueDate)) {
                filteredTasks.add(task);
            }
        }
        return filteredTasks;
    }

    private User getUserByUsername(String username) {
        for (User user : users) {
            if (user.getUsername().equals(username)) {
                return user;
            }
        }
        return null;
    }
}

