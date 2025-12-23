package org.example;

public class Counter {
    /**
     * public class Counter {
     *
     *     // equivalent of NUMCPUS
     *     private static final int NUMCPUS = 4;
     *
     *     // global count
     *     private int global;
     *
     *     // global lock
     *     private final Object glock = new Object();
     *
     *     // local count (per cpu)
     *     private final int[] local = new int[NUMCPUS];
     *
     *     // local locks (per cpu)
     *     private final Object[] llock = new Object[NUMCPUS];
     *
     *     // update frequency
     *     private int threshold;
     *
     *     // ---------------- INIT ----------------
     *     public Counter(int threshold) {
     *         this.threshold = threshold;
     *         this.global = 0;
     *
     *         for (int i = 0; i < NUMCPUS; i++) {
     *             local[i] = 0;
     *             llock[i] = new Object(); // initialize each local lock
     *         }
     *     }
     */
    int global;

    final Object globalLock = new Object();

    final int num_of_cpu = 4;

    final int[] local = new int[num_of_cpu];

    Object[] obj = new Object[num_of_cpu];

    int threshold ;

    public Counter(int _threshold){
        this.threshold = _threshold;
        this.global = 0;

        for(int i = 0 ; i < num_of_cpu ; i++){
            obj[i] = new Object();
            local[i] = 0;
        }

    }

    public  void update(int threadId , int amount){
        int cpu = threadId % num_of_cpu;

        synchronized (obj[cpu]){
            local[cpu] += amount;

            if(local[cpu] > threshold){
                synchronized (globalLock){
                    global += local[cpu];
                }
                local[cpu] = 0;
            }

        }


    }

    public  int get(){
        for(int i = 0 ; i < num_of_cpu ; i++){
            global += local[i];
        }
        return  global;
//        synchronized (globalLock){
//            for(int i = 0 ; i < num_of_cpu ; i++){
//                global += local[i];
//            }
//            return  global;
//        }
    }




















}
