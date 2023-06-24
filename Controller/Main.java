package Controller;
import java.util.List; 
import java.util.Scanner;

import DAO.TaskManagementSystem;
import DTO.Task;
import DTO.User;

public class Main {
    public static void main(String[] args) {
        TaskManagementSystem system = new TaskManagementSystem();
        Scanner scanner = new Scanner(System.in);
        /*the user will be null initially if he assigns a name then will be
        re initialized*/
        User currentUser = null;
        
        /*while condition as there are multiple conditions like register,
         login and exit*/
      
        while (true) {
            System.out.println("=== Task Management System ===");
            System.out.println("1. Register");
            System.out.println("2. Login");
            System.out.println("3. Exit");
            System.out.print("Enter your choice: ");
            int choice = Integer.parseInt(scanner.nextLine());

            switch (choice) {
                case 1:
                    system.registerUser(scanner);
                    break;
                case 2:
                    currentUser = system.loginUser(scanner);
                    if (currentUser == null) {
                        continue;
                    }
                    break;
                case 3:
                    System.out.println("Exiting...");
                    scanner.close();
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice!");
                    continue;
            }

            /* similar to above another while condition as there are multiple conditions 
            */
            while (true) {
                System.out.println("\n=== Task Management ===");
                System.out.println("1. Create Task");
                System.out.println("2. Update Task");
                System.out.println("3. Mark Task as Complete");
                System.out.println("4. Mark Task as Incomplete");
                System.out.println("5. Delete Task");
                System.out.println("6. Search Tasks");
                System.out.println("7. Filter Tasks");
                System.out.println("8. Logout");
                System.out.print("Enter your choice: ");
                int taskChoice = Integer.parseInt(scanner.nextLine());

                switch (taskChoice) {
                	//creation of tasks
                    case 1:
                        system.createTask(scanner, currentUser);
                        break;
                        //updation of task
                        
                    case 2:
                        System.out.print("Enter task title to update: ");
                        String updateTitle = scanner.nextLine();
                        List<Task> updateTasks = system.searchTasks(updateTitle);
                        if (updateTasks.isEmpty()) {
                            System.out.println("Task not found!");
                        } else {
                            Task updateTask = updateTasks.get(0);
                            system.updateTask(scanner, updateTask, currentUser);
                        }
                        break;
                        
                        //task completion updation
                    case 3:
                        System.out.print("Enter task title to mark as complete: ");
                        String completeTitle = scanner.nextLine();
                        List<Task> completeTasks = system.searchTasks(completeTitle);
                        if (completeTasks.isEmpty()) {
                            System.out.println("Task not found!");
                        } else {
                            Task completeTask = completeTasks.get(0);
                            system.markTaskAsComplete(completeTask);
                        }
                        break;
                        
                      //task incompletion updation
                    case 4:
                        System.out.print("Enter task title to mark as incomplete: ");
                        String incompleteTitle = scanner.nextLine();
                        List<Task> incompleteTasks = system.searchTasks(incompleteTitle);
                        if (incompleteTasks.isEmpty()) {
                            System.out.println("Task not found!");
                        } else {
                            Task incompleteTask = incompleteTasks.get(0);
                            system.markTaskAsIncomplete(incompleteTask);
                        }
                        break;
                        
                        //task deletion 
                    case 5:
                        System.out.print("Enter task title to delete: ");
                        String deleteTitle = scanner.nextLine();
                        List<Task> deleteTasks = system.searchTasks(deleteTitle);
                        if (deleteTasks.isEmpty()) {
                            System.out.println("Task not found!");
                        } else {
                            Task deleteTask = deleteTasks.get(0);
                            system.deleteTask(deleteTask);
                        }
                        break;
                        
                        //task searching case
                    case 6:
                        System.out.print("Enter keyword to search tasks: ");
                        String searchKeyword = scanner.nextLine();
                        List<Task> searchResults = system.searchTasks(searchKeyword);
                        if (searchResults.isEmpty()) {
                            System.out.println("No matching tasks found!");
                        } else {
                            System.out.println("Search Results:");
                            for (Task task : searchResults) {
                                System.out.println("Title: " + task.getTitle());
                                System.out.println("Description: " + task.getDescription());
                                System.out.println("Due Date: " + task.getDueDate());
                                System.out.println("Assigned User: " + task.getAssignedUser().getUsername());
                                System.out.println("Complete: " + (task.isComplete() ? "Yes" : "No"));
                                System.out.println();
                            }
                        }
                        break;
                        
                        //task filter case multiple choice again here
                    case 7:
                        System.out.println("=== Filter Tasks ===");
                        System.out.println("1. Filter by Completion Status");
                        System.out.println("2. Filter by Due Date");
                        System.out.print("Enter your choice: ");
                        int filterChoice = Integer.parseInt(scanner.nextLine());

                        switch (filterChoice) {
                            case 1:
                                System.out.print("Enter completion status (true/false): ");
                                boolean isComplete = Boolean.parseBoolean(scanner.nextLine());
                                List<Task> filteredTasksByCompletion = system.filterTasksByCompletion(isComplete);
                                if (filteredTasksByCompletion.isEmpty()) {
                                    System.out.println("No matching tasks found!");
                                } else {
                                    System.out.println("Filtered Tasks:");
                                    for (Task task : filteredTasksByCompletion) {
                                        System.out.println("Title: " + task.getTitle());
                                        System.out.println("Description: " + task.getDescription());
                                        System.out.println("Due Date: " + task.getDueDate());
                                        System.out.println("Assigned User: " + task.getAssignedUser().getUsername());
                                        System.out.println("Complete: " + (task.isComplete() ? "Yes" : "No"));
                                        System.out.println();
                                    }
                                }
                                break;
                            case 2:
                                System.out.print("Enter due date: ");
                                String dueDate = scanner.nextLine();
                                List<Task> filteredTasksByDueDate = system.filterTasksByDueDate(dueDate);
                                if (filteredTasksByDueDate.isEmpty()) {
                                    System.out.println("No matching tasks found!");
                                } else {
                                    System.out.println("Filtered Tasks:");
                                    for (Task task : filteredTasksByDueDate) {
                                        System.out.println("Title: " + task.getTitle());
                                        System.out.println("Description: " + task.getDescription());
                                        System.out.println("Due Date: " + task.getDueDate());
                                        System.out.println("Assigned User: " + task.getAssignedUser().getUsername());
                                        System.out.println("Complete: " + (task.isComplete() ? "Yes" : "No"));
                                        System.out.println();
                                    }
                                }
                                break;
                            default:
                                System.out.println("Invalid choice!");
                                break;
                        }
                        break;
                    case 8:
                        currentUser = null;
                        System.out.println("Logged out!");
                        break;
                    default:
                        System.out.println("Invalid choice!");
                        break;
                }

                if (currentUser == null) {
                    break;
                }
            }
        }
    }
}
