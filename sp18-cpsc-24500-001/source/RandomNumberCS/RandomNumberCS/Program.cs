using System;

namespace RandomNumberCS {
    class GetRandomNumbers {
        private Random myRandom;

        public GetRandomNumbers() {
            myRandom = new Random();
        }

        public int GetNumberBetween(int min, int max) {
            return min + myRandom.Next(min, max);
        }

        public void Find1024() {
            long timesToLookFor1024 = 1000000000;
            long timesFound = 0;

            for (long i = 0; i < timesToLookFor1024; i++) {
                int random = GetNumberBetween(1, 2000000);
                if (random == 1024) {
                    timesFound++;
                    Console.WriteLine("RandomNumber:{0} Found:{1}", random, timesFound);
                }
            }
        }
    }
    
    class Program {
        static void Main(string[] args) {
            System.Console.WriteLine("Starting application...");

            DateTime StartTime = DateTime.Now.ToLocalTime();

            GetRandomNumbers myGetRandomNumbers = new GetRandomNumbers();
            myGetRandomNumbers.Find1024();

            DateTime EndTime = DateTime.Now.ToLocalTime();
            TimeSpan myTimeSpan = EndTime.Subtract(StartTime);
            Console.WriteLine( myTimeSpan);

            Console.WriteLine("\nClosing application... Press any key to continue.");
            Console.ReadKey();
        }
    }
}
