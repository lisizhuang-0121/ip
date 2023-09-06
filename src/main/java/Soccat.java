import java.util.Scanner;

public class Soccat {

    static Task[] tasks = new Task[100];

    public static void addTask(String command, String type) {
        String task = command.replace(type, "").strip();
        int taskIndex = Task.getTaskCount();
        switch(type) {
        case "todo":
            tasks[taskIndex] = new Todo(task);
            break;
        case "deadline":
            String[] deadlineTokens = task.split(" /by ");
            tasks[taskIndex] = new Deadline(deadlineTokens[0], deadlineTokens[1]);
            break;
        case "event":
            String[] eventTokens = task.split(" /");
            String from = eventTokens[1].replace("from ", "");
            String by = eventTokens[2].replace("to ", "");
            tasks[taskIndex] = new Event(eventTokens[0], from, by);
            break;
        }
        System.out.println("Got it. I've added this task: ");
        System.out.println("\t" + tasks[taskIndex]);
        System.out.println("Now you have " + Task.getTaskCount() + " tasks in the list.");
    }

    public static void listTasks() {
        int taskCount = Task.getTaskCount();
        if (taskCount == 0) {
            System.out.println("Great, You have no tasks for now!");
            return;
        }
        for(int i=0; i<taskCount; i++) {
            System.out.print(i+1);
            System.out.println(". " + tasks[i]);
        }
    }

    public static void toggleDone(String taskNumber, boolean isDone) {
        try {
            int taskId = Integer.parseInt(taskNumber);
            if (taskId > Task.getTaskCount() || taskId <= 0) {
                System.out.println("Task out of range!");
                return;
            }
            tasks[taskId-1].setDone(isDone);
            System.out.println(tasks[taskId-1]);
        } catch (NumberFormatException e) {
            System.out.println("Invalid task number!");
        }
    }

    public static void processCommand(String line) {
        Scanner textIn = new Scanner(System.in);
        String command = null;
        while (true) {
            command = textIn.nextLine();
            if (command.equals("bye")) {
                System.out.println(line);
                break;
            }
            String[] tokens = command.split(" ");
            System.out.println(line);
            switch (tokens[0]) {
            case "list": listTasks();
                break;
            case "todo":
            case "deadline":
            case "event": addTask(command, tokens[0]);
                break;
            case "mark": toggleDone(tokens[1], true);
                break;
            case "unmark": toggleDone(tokens[1], false);
                break;
            default:
                System.out.println("Sorry, I could not recognize your command.");
                break;
            }
        }
    }

    public static void main(String[] args) {
        String line = "____________________________________________________________";
        System.out.println(line + "\nHello! I'm soccat!\nWhat can I do for you?\n" + line);
        processCommand(line);
        System.out.println("Bye. Hope to see you again soon!\n" + line);
    }
}
