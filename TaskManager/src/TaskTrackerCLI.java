import domain.Task;
import repository.TaskRepository;
import service.TaskService;

import java.util.ArrayList;
import java.util.Scanner;

public class TaskTrackerCLI {
    private static final ArrayList<Task> tasks = new ArrayList<>();
    private static final TaskRepository taskRepository = new TaskRepository(tasks, "D:\\IntelliJ IDEA\\Task_Manager\\TaskManager\\src\\tasks.json");
    private static final TaskService taskService = new TaskService(taskRepository);
    private static final Scanner scanner = new Scanner(System.in);

    public TaskTrackerCLI() {
    }

    public static void main(String[] args){

        boolean running = true;

        printUsage();
        while(running){

            String input = scanner.nextLine().trim();

            if (input.isEmpty()) continue;

            String[] inputParts = input.split("\\s+", 2);
            String command = inputParts[0].toLowerCase();
            String[] commandArgs = inputParts.length > 1 ? inputParts[1].split(" ") : new String[0];

            switch (command){
                case "add":
                    addUI(commandArgs);
                    break;
                case "update":
                    updateUI(commandArgs);
                    break;
                case "delete":
                    deleteUI(commandArgs);
                    break;
                case "mark-in-progress":
                    markInProgressUI(commandArgs);
                    break;
                case "mark-done":
                    markDoneUI(commandArgs);
                    break;
                case "list":
                    listUI(commandArgs);
                    break;
                case "stop":
                case "quit":
                case "exit":
                    running = false;
                    System.out.println("Exiting Tracker...");
                    break;
                default:
                    printUsage();
            }
        }
    }

    private static void printUsage() {
        System.out.println("Usage:");
        System.out.println("  add (task description)            -> Add a task");
        System.out.println("  update (id) (New description)     -> Update the description of a task");
        System.out.println("  delete (id)                       -> Delete a task");
        System.out.println("  mark-in-progress (id)             -> Mark a task as in-progress");
        System.out.println("  mark-done (id)                    -> Mark a task as done");
        System.out.println("  list                              -> List all the tasks");
        System.out.println("  list done                         -> List all the tasks that are done");
        System.out.println("  list todo                         -> List all the tasks that are todo");
        System.out.println("  list in-progress                  -> List all the tasks that are in-progress");
        System.out.println("  stop/quit/exit                    -> Exit the application");
    }

    private static void printTasks(){
        if (taskService.getTasks().isEmpty()){
            System.out.println("No tasks found.");
        } else {
            for (Task task : taskService.getTasks()){
                System.out.println(task);
            }
        }
    }

    private static void printByStatus(String status){
        if (taskService.getTasks().isEmpty()){
            System.out.println("No tasks found.");
        } else {
            boolean found = false;
            for (Task task : taskService.getTasks()){
                if (task.getStatus().equals(status)){
                    System.out.println(task);
                    found = true;
                }
            }
            if (!found){
                System.out.println("No tasks found.");
            }
        }
    }

    private static void addUI(String[] args){
        try{
            String description = String.join(" ", args);
            taskService.addTask(description);
            System.out.println("Adding task...");
        } catch (Exception e){
            System.out.println(e.getMessage());
        }

    }

    private static void updateUI(String[] args){
        try{
            if (args.length < 2){
                throw new Exception("Invalid number of arguments");
            }
            int id = Integer.parseInt(args[0]);
            String description = String.join(" ", args).substring(args[0].length()).trim();
            taskService.updateTask(id, description);
            System.out.println("Task updated successfully");
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    private static void listUI(String[] args){
        if (args.length == 0){
            printTasks();
        } else {
            String status = args[0].toLowerCase();
            switch (status){
                case "todo":
                case "in-progress":
                case "done":
                    printByStatus(status);
                    break;
                default:
                    System.out.println("Invalid status");
            }
        }
    }

    private static void deleteUI(String[] args){
        if (args.length == 0){
            System.out.println("Invalid number of arguments");
        }
        try{
            int id = Integer.parseInt(args[0]);
            taskService.deleteTask(id);
            System.out.println("Task deleted successfully");
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    private static void markInProgressUI(String[] args){
        if (args.length == 0){
            System.out.println("Invalid number of arguments");
        }
        try{
            int id = Integer.parseInt(args[0]);
            taskService.markAsInProgress(id);
            System.out.println("Task marked successfully");
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    private static void markDoneUI(String[] args){
        if (args.length == 0){
            System.out.println("Invalid number of arguments");
        }
        try{
            int id = Integer.parseInt(args[0]);
            taskService.markAsDone(id);
            System.out.println("Task marked successfully");
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
}
