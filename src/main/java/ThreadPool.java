import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class ThreadPool {

    private final BlockingQueue<Task> queue;
    private int max_capacity;
    private List<MyThread> myThreads;

    public ThreadPool(int capacity) {
        this.max_capacity = 2 * capacity;
        myThreads = new ArrayList<>();
        queue = new LinkedBlockingQueue<>(max_capacity);
        for (int i = 0; i < capacity; i++) {
            MyThread myThread = new MyThread(queue);
            myThreads.add(myThread);
            Thread thread = new Thread(myThread);
            thread.start();
        }
    }

    public void Terminate(){
        for (MyThread myThread : myThreads){
            myThread.terminate();
        }
    }

    public void execute(Task task){
        if (this.queue.size() == max_capacity){
            System.out.println("No more room for additional tasks");
            return;
        }
        synchronized (ThreadPool.class) {
            task.setStatus(TaskStatus.WAITING);
                queue.add(task);
            System.out.println("Task Added");
        }
    }
}
