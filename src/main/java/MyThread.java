import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

public class MyThread implements Runnable {

    private BlockingQueue<Task> queue;
    private volatile boolean running;

    public MyThread(BlockingQueue<Task> queue){
        this.queue = queue;
        this.running = true;
    }

    public void terminate(){
        running = false;
    }

    @Override
    public void run() {

        while (running) {
            try {
                Task task = queue.poll(100, TimeUnit.MICROSECONDS);
                if (task != null) {
                    task.setStatus(TaskStatus.RUNNING);
                    System.out.println(Thread.currentThread().getId() + "Starting Task");
                    try {
                        task.execute();
                        task.setStatus(TaskStatus.COMPLETED);
                    } catch (Exception excp) {
                        task.setErrorMessage(excp.getMessage());
                        task.setStatus(TaskStatus.ERROR);
                    }
                }
            } catch (InterruptedException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
