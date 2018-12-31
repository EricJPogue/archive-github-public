import java.util.Random;
import java.time.LocalTime;
import java.time.Duration;

/*
class GetPrimeNumbers {
    GetPrimeNumbers() {}

    public boolean IsPrime(int numberToCheck) {
       return true;
    }
}
*/
class GetRandomNumbers extends Thread {
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
        super(theadNameIn);
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
                System.out.format("RandomNumber:%d Found:%d Name:%s\n",random, timesFound, getName());
            }
        }
    }

    public void run() {
        Find1024();
    }
}

public class RandomNumbersThreads {
    public static void main(String[] args) {
        System.out.println("Starting RandomNumbes!!!");

        final long adjustedTimesToSearch = 1000000000/4;
        GetRandomNumbers myGetRandomNumbers1 = new GetRandomNumbers("One", adjustedTimesToSearch);
        GetRandomNumbers myGetRandomNumbers2 = new GetRandomNumbers("Two",adjustedTimesToSearch);
        GetRandomNumbers myGetRandomNumbers3 = new GetRandomNumbers("Three",adjustedTimesToSearch);
        GetRandomNumbers myGetRandomNumbers4 = new GetRandomNumbers("Four",adjustedTimesToSearch);
        
        LocalTime startTime = LocalTime.now();
        myGetRandomNumbers1.start();
        myGetRandomNumbers2.start();
        myGetRandomNumbers3.start();
        myGetRandomNumbers4.start();

        try {
            myGetRandomNumbers1.join();
            myGetRandomNumbers2.join();
            myGetRandomNumbers3.join();
            myGetRandomNumbers4.join();
        } catch (Exception e) {
            System.out.println("Exception!!!");
        }
        LocalTime endTime = LocalTime.now();

        Duration duration = Duration.between(startTime,endTime);
        System.out.format("\nTotal Duration: %s\n", duration);

        // Display the total times 1024 was found and how many times it was found on each thread.
        long totalTimesFound = myGetRandomNumbers1.getTimesFound() + myGetRandomNumbers2.getTimesFound() + 
            myGetRandomNumbers3.getTimesFound() + myGetRandomNumbers4.getTimesFound();
        System.out.format("Total Found: %d \nT1:%d, T2:%d, T3:%d, T4:%d", totalTimesFound, 
            myGetRandomNumbers1.getTimesFound(), myGetRandomNumbers2.getTimesFound(), 
            myGetRandomNumbers3.getTimesFound(), myGetRandomNumbers4.getTimesFound());
    }
}