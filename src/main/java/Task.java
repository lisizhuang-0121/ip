public class Task {
    private String name;
    private Boolean isDone;
    private static int taskCount = 0;
    public Task(String name){
        this.name = name;
        this.isDone = false;
        taskCount++;
    }

    public String getName() {
        return name;
    }

    public Boolean getDone() {
        return isDone;
    }

    public void setDone(Boolean done) {
        isDone = done;
    }

    public static int getTaskCount() {
        return taskCount;
    }
}