
public enum TaskStatus {
    RUNNING("Running"),
    WAITING("Waiting"),
    COMPLETED("Completed"),
    ERROR("Error");

    private String name;
    TaskStatus(String name){
        this.name = name;
    }
}
