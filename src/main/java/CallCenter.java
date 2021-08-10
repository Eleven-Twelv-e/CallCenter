import java.util.concurrent.ConcurrentLinkedQueue;

public class CallCenter {
    final int MAX_CALL = 30;
    final int CALL_DELAY = 1000;
    final int DELAY_OPERATOR = 5000;
    final int TALK = 4000;


    static ConcurrentLinkedQueue<String> queue = new ConcurrentLinkedQueue<>();

    public void call() {
        for (int i = 0; i < MAX_CALL; i++) {
            System.out.println("Поступил звонок " + i);
            queue.add("Звонок " + i);
            try {
                Thread.sleep(CALL_DELAY);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void operatorReaction() {
        String queuePool;
        while (true) {
            queuePool = queue.poll();
            if (queuePool != null) {
                System.out.println(Thread.currentThread().getName() + " обработал: " + queuePool);
                try {
                    Thread.sleep(TALK);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            if (queue.size() == 0) {
                try {
                    Thread.sleep(DELAY_OPERATOR);
                    if (queue.size() == 0) {
                        System.out.println(Thread.currentThread().getName() + " ушел домой");
                        break;
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
