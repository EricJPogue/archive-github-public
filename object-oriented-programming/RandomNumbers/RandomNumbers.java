import java.util.Random;
import java.time.LocalTime;
import java.time.Duration;

class GetRandomNumbers {
    private Random myRandom;

    GetRandomNumbers() {
        myRandom = new Random();
    }

    public int GetNumberBetween(int min, int max) {
        return min + myRandom.nextInt(max-min);
    }
    
    public void Find1024() {
        long timesToLookFor1024 = 1000000000;
        long timesFound = 0;

        for (long i=0; i<timesToLookFor1024; i++) {
            int random = GetNumberBetween(1,2000000);
            if (random==1024) {
                timesFound++;
                System.out.format("RandomNumber:%,d Found:%d\n",random, timesFound);
            }
        }
    }
}

public class RandomNumbers {
    public static void main(String[] args) {
        System.out.println("ThreadedRandomNumbers Starting!");
        GetRandomNumbers myGetRandomNumbers = new GetRandomNumbers();

        LocalTime startTime = LocalTime.now(); 
        myGetRandomNumbers.Find1024();
        LocalTime endTime = LocalTime.now();

        Duration duration = Duration.between(startTime, endTime);
        System.out.println(duration);
    }
}