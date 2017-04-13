/**
 * Created by umesh_kumbhar on 4/5/17.
 */
public class Test {
    public static void main(String[] args) {
        ThreadPool pool = new ThreadPool(2);
        Task task1 = new Task() {
            @Override
            public void execute() throws InterruptedException {
                int i = 100;
                while(i-- > 0){
                    System.out.println(" Task 1 : " + i);
                    Thread.sleep(100);
                }
            }
        };
        pool.execute(task1);
        System.out.println(task1.getStatus());

        Task task2 = new Task() {
            @Override
            public void execute() throws InterruptedException {
                int j = 100;
                while (j-- > 0){
                    System.out.println("Task 2 : " + j);
                    Thread.sleep(100);
                }
            }
        };

        pool.execute(task2);
        task2.getStatus();

        while (task2.getStatus() != TaskStatus.COMPLETED){
            try {
                Thread.sleep(200);
                System.out.println("Checking Status");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println("Both Tasks Completed");
        pool.Terminate();
        System.out.println("Pool Terminated");
    }
}
