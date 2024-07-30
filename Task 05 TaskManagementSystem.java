public class TaskManagementSystem {

    public static void main(String[] args) {
        SinglyLinkedList taskList = new SinglyLinkedList();

        // Add tasks
        taskList.addTask(new Task(1, "Task 1", "Pending"));
        taskList.addTask(new Task(2, "Task 2", "In Progress"));
        taskList.addTask(new Task(3, "Task 3", "Completed"));

        // Traverse tasks
        System.out.println("Traversing tasks:");
        taskList.traverseTasks();

        // Search for a task
        System.out.println("Searching for task with ID 2:");
        Task task = taskList.searchTask(2);
        if (task != null) {
            System.out.println("Found: " + task);
        } else {
            System.out.println("Task not found.");
        }

        // Delete a task
        System.out.println("Deleting task with ID 1:");
        boolean isDeleted = taskList.deleteTask(1);
        if (isDeleted) {
            System.out.println("Task deleted successfully.");
        } else {
            System.out.println("Task not found.");
        }

        // Traverse tasks again
        System.out.println("Traversing tasks after deletion:");
        taskList.traverseTasks();
    }
}

class Task {
    private int taskId;
    private String taskName;
    private String status;

    public Task(int taskId, String taskName, String status) {
        this.taskId = taskId;
        this.taskName = taskName;
        this.status = status;
    }

    public int getTaskId() {
        return taskId;
    }

    public String getTaskName() {
        return taskName;
    }

    public String getStatus() {
        return status;
    }

    @Override
    public String toString() {
        return "Task [taskId=" + taskId + ", taskName=" + taskName + ", status=" + status + "]";
    }
}

class Node {
    Task task;
    Node next;

    Node(Task task) {
        this.task = task;
        this.next = null;
    }
}

class SinglyLinkedList {
    private Node head;

    public SinglyLinkedList() {
        this.head = null;
    }

    // Add a task to the end of the list
    public void addTask(Task task) {
        Node newNode = new Node(task);
        if (head == null) {
            head = newNode;
        } else {
            Node current = head;
            while (current.next != null) {
                current = current.next;
            }
            current.next = newNode;
        }
    }

    // Search for a task by taskId
    public Task searchTask(int taskId) {
        Node current = head;
        while (current != null) {
            if (current.task.getTaskId() == taskId) {
                return current.task;
            }
            current = current.next;
        }
        return null;
    }

    // Traverse and print all tasks
    public void traverseTasks() {
        Node current = head;
        while (current != null) {
            System.out.println(current.task);
            current = current.next;
        }
    }

    // Delete a task by taskId
    public boolean deleteTask(int taskId) {
        if (head == null) return false;
        if (head.task.getTaskId() == taskId) {
            head = head.next;
            return true;
        }

        Node current = head;
        while (current.next != null) {
            if (current.next.task.getTaskId() == taskId) {
                current.next = current.next.next;
                return true;
            }
            current = current.next;
        }
        return false;
    }
}
