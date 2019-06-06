public class ABCTask {
    static Object mon = new Object();
    static Character letter = 'C';

    public static void main(String[] args) {

        /*
            1. Создать три потока, каждый из которых выводит определенную букву (A, B и C)
            5 раз (порядок – ABСABСABС). Используйте wait/notify/notifyAll.
        */

        Thread t1 = new Thread(() -> {

            for (int i = 0; i < 5; i++) {
                synchronized (mon) {
                    while (!letter.equals('C')){
                        try {
                            mon.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    letter = 'A';
                    System.out.print(letter);
                    mon.notifyAll();
                }

            }
        });

        Thread t2 = new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                synchronized (mon) {
                    while (!letter.equals('A')){
                        try {
                            mon.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    letter = 'B';
                    System.out.print(letter);
                    mon.notifyAll();
                }

            }
        });

        Thread t3 = new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                synchronized (mon) {
                    while (!letter.equals('B')){
                        try {
                            mon.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    letter = 'C';
                    System.out.print(letter);
                    mon.notifyAll();
                }
            }
        });

        t1.start();
        t2.start();
        t3.start();

        /*
            2. На серверной стороне сетевого чата реализовать управление потоками через ExecutorService.

        */
    }
}
