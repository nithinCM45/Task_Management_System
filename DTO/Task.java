package DTO;

 public class Task {
    private String title;
    private String description;
    private String dueDate;
    private User assignedUser;
    private boolean isComplete;
    /* constructor to assign the given attributes */
    public Task(String title, String description, String dueDate, User assignedUser) {
        this.title = title;
        this.description = description;
        this.dueDate = dueDate;
        this.assignedUser = assignedUser;
        this.isComplete = false;
    }
    
    // Getters and setters to access private data members
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDueDate() {
        return dueDate;
    }

    public void setDueDate(String dueDate) {
        this.dueDate = dueDate;
    }

    public User getAssignedUser() {
        return assignedUser;
    }

    public void setAssignedUser(User assignedUser) {
        this.assignedUser = assignedUser;
    }

    public boolean isComplete() {
        return isComplete;
    }

    public void setComplete(boolean complete) {
        isComplete = complete;
    }
}
