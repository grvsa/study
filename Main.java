package ru.geekbrains.java3.lesson4;

public class Main {
    private static char currentLetter = 'C';
    private static Object lock = new Object();
    public static void main(String[] args) {
	// write your code here
        Thread threadA = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 5; i++) {
                    synchronized (lock){
                        if (currentLetter == 'C'){
                            currentLetter = 'A';
                            System.out.printf("" + currentLetter);
                            lock.notifyAll();
                        }else{
                            i--;
                            try {
                                lock.wait();
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }
            }
        });
        Thread threadB = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 5; i++) {
                    synchronized (lock){
                        if (currentLetter == 'A'){
                            currentLetter = 'B';
                            System.out.printf("" + currentLetter);
                            lock.notifyAll();
                        }else{
                            i--;
                            try {
                                lock.wait();
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }
            }
        });
        Thread threadC = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 5; i++) {
                    synchronized (lock){
                        if (currentLetter == 'B'){
                            currentLetter = 'C';
                            System.out.printf("" + currentLetter);
                            lock.notifyAll();
                        }else{
                            i--;
                            try {
                                lock.wait();
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }
            }
        });

        threadA.start();
        threadB.start();
        threadC.start();

        try {
            threadA.join();
            threadB.join();
            threadC.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
