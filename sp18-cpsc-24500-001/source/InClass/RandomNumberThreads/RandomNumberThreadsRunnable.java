import java.util.Random;
import java.time.LocalTime;
import java.time.Duration;  

class GetRandomNumbers implements Runnable {
    private String threadName;
    private Random myRandom;
    private long timesFound;
    public long getTimesFound() { return timesFound; }
    public void setTimesFound(long timesFoundIn) { timesFound = timesFoundIn; }

    private long timesToLookFor1024;
    public long getTimesToLookFor1024() { return timesToLookFor1024; }
    public void setTimesToLookFor1024(long timesToLookFor1024In) { timesToLookFor1024 = timesToLookFor1024In; }

    GetRandomNumbers() {
        assert false:"Unexpected call to default constructor.";
    }

    GetRandomNumbers(String theadNameIn, long timesToLookFor1024In) {
        threadName = theadNameIn;
        myRandom = new Random();
        setTimesFound(0);
        setTimesToLookFor1024(timesToLookFor1024In);
    }

    public int GetNumberBetween(int min, int max) {
        return min + myRandom.nextInt(max-min);
    }    

    public void Find1024() {
        for (long i=0; i<timesToLookFor1024; i++) { // 1,000,000,000
            long random = GetNumberBetween(1,2000000); // 2,000,000
            if (random == 1024) {
                timesFound++;
                System.out.format("RandomNumber:%d Found:%d Name:%s\n",random, timesFound, threadName);
            }
        }
    }

    public void run() {
        Find1024();
    }
}

public class RandomNumberThreadsRunnable {
    public static void main(String[] args) {
        System.out.println("Starting RandomNumbes!!!");

        final long searchTimes = 250000000;
        GetRandomNumbers r1 = new GetRandomNumbers("1", searchTimes);
        GetRandomNumbers r2 = new GetRandomNumbers("2", searchTimes);

        Thread t1 = new Thread(r1);
        Thread t2 = new Thread(r2);

        t1.start();
        t2.start();

        try {
            t1.join();
            t2.join();
        } catch (Exception e) {
            System.out.println("Exception!!!");
        }

    }
}