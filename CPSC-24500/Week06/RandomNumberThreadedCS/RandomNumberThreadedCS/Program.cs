using System;
using System.Threading;

namespace ConsoleApp1 {
    class GetRandomNumbers {
        private Random myRandom;

        private long timesToLookFor1024;
        public long getTimesToLookFor1024() { return timesToLookFor1024; }
        public void setTimesToLookFor1024(long timesToLookFor1024In) { timesToLookFor1024 = timesToLookFor1024In; }

        private int threadNumber;
        public void setThreadNumber(int threadNumberIn) { threadNumber = threadNumberIn; }
        // Auto-Implemented Properties [https://msdn.microsoft.com/en-us/library/bb384054.aspx]
        // public int threadNumber { get; set; }

        public GetRandomNumbers() {
            setThreadNumber(0);
            myRandom = new Random();
        }

        // C# Constructor Chaining
        public GetRandomNumbers(int threadNumberIn, long timesToLookFor1024In) : this() {
            setThreadNumber(threadNumberIn);
            setTimesToLookFor1024(timesToLookFor1024In);
        }

        public int GetNumberBetween(int min, int max) {
            return min + myRandom.Next(min, max);
        }

        public void Find1024() {
            long timesFound = 0;

            for (long i = 0; i < timesToLookFor1024; i++) {
                int random = GetNumberBetween(1, 2000000);
                if (random == 1024) {
                    timesFound++;
                    Console.WriteLine("RandomNumber:{0} Found:{1} ThreadNumber:{2}", random, timesFound, threadNumber);

                }
            }
        }
    }

    class Program {
        static void Main(string[] args) {
            System.Console.WriteLine("Hello, World!");

            Console.WriteLine("You entered the following {0} command line arguments:", args.Length);
            for (int i = 0; i < args.Length; i++) {
                Console.WriteLine("{0}", args[i]);
            }

            DateTime StartTime = DateTime.Now.ToLocalTime();

            long adjustedTimesToSearch = 1000000000 / 4;
            GetRandomNumbers myGetRandomNumbers1 = new GetRandomNumbers(1, adjustedTimesToSearch);
            GetRandomNumbers myGetRandomNumbers2 = new GetRandomNumbers(2, adjustedTimesToSearch);
            GetRandomNumbers myGetRandomNumbers3 = new GetRandomNumbers(3, adjustedTimesToSearch);
            GetRandomNumbers myGetRandomNumbers4 = new GetRandomNumbers(4, adjustedTimesToSearch);

            ThreadStart threadStart1 = new ThreadStart(myGetRandomNumbers1.Find1024);
            ThreadStart threadStart2 = new ThreadStart(myGetRandomNumbers2.Find1024);
            ThreadStart threadStart3 = new ThreadStart(myGetRandomNumbers3.Find1024);
            ThreadStart threadStart4 = new ThreadStart(myGetRandomNumbers4.Find1024);

            Thread thread1 = new Thread(threadStart1);
            Thread thread2 = new Thread(threadStart2);
            Thread thread3 = new Thread(threadStart3);
            Thread thread4 = new Thread(threadStart4);

            thread1.Start();
            thread2.Start();
            thread3.Start();
            thread4.Start();

            thread1.Join();
            thread2.Join();
            thread3.Join();
            thread4.Join();

            DateTime EndTime = DateTime.Now.ToLocalTime();
            TimeSpan myTimeSpan = EndTime.Subtract(StartTime);

            Console.WriteLine("{0}", myTimeSpan);
            Console.WriteLine("Press [Enter] to continue.");
            Console.ReadLine();
        }
    }
}
