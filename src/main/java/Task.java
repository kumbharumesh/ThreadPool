
public abstract class Task {
    private TaskStatus status;

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    private String errorMessage;

    public abstract void execute() throws InterruptedException;

    public void setStatus(TaskStatus status){
        this.status = status;
    }

    public TaskStatus getStatus(){
        return this.status;
    }

}
