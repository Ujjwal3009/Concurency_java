package org.example;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) throws  InterruptedException {
        int no_thread = 8;

        Counter counter = new Counter(100);
        Thread[] th  = new Thread[no_thread];

        for(int i = 0 ; i < no_thread ; i++){
            int thread_id = i;

            th[i] = new Thread(() -> {
                for(int j = 0 ; j < 1000000 ; j++){
                    counter.update(thread_id,1);
                }

            });





        }

        for(Thread each : th) each.start();
        for(Thread each : th) each.join();
        System.out.println(counter.get());









    }
}