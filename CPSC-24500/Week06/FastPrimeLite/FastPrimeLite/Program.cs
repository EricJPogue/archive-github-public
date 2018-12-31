using System;
using System.Collections;
using System.Threading;
using System.IO;

namespace FastPrimeLite {
    class GetPrimeNumbersLite {
        private static ArrayList primeArrayList = new ArrayList();
        private int min, max;
        private string threadNumber;

        public GetPrimeNumbersLite(string threadNumberIn, int minIn, int maxIn) {
            threadNumber = threadNumberIn;
            min = minIn;
            max = maxIn;
        }
        
        private bool IsPrimeNumber(int number) {
            if (number == 1) return false;
            if (number == 2) return true;

            if (number % 2 == 0) return false;

            for (int i = 3; i < number; i += 2) {
                if (number % i == 0) return false;
            }
            return true;
        }

        public void FindPrimesInRange() {
            for (int i = min; i <= max; i++) {
                if (IsPrimeNumber(i)) {
                    primeArrayList.Add(i);
                    Console.WriteLine("ThreadNumber:{0} Prime Number:{1}", threadNumber, i);
                }
            }
        }

        static public void SortList() {
            primeArrayList.Sort();
        }

        static public void WriteListToFile() {
            string mydocpath = Environment.GetFolderPath(Environment.SpecialFolder.MyDocuments);
            using (StreamWriter outputFile = new StreamWriter(mydocpath + @"\FastPrimeOutput.txt")) {
                foreach (object obj in primeArrayList)
                    outputFile.WriteLine(obj);
            }
        }
    }

    class Program {
        static void Main(string[] args) {
            System.Console.WriteLine("Starting application...");

            Console.WriteLine("You entered the following {0} command line arguments:", args.Length);
            for (int i = 0; i < args.Length; i++) {
                Console.WriteLine("{0}", args[i]);
            }

            // Get the start time.
            DateTime StartTime = DateTime.Now.ToLocalTime();

            // Write your command line validation code here.
            if (args.Length != 2) {
                Console.WriteLine("This application requires two parameters...");
            }

            int min = Convert.ToInt32(args[0]);
            int max = Convert.ToInt32(args[1]);

            // Break ranges up into three equally size ranges. 
            int range = max - min;
            int max1 = min + (range / 3);
            int max2 = min + (2 * range / 3);
            int max3 = max;

            int min1 = min;
            int min2 = max1 + 1;
            int min3 = max2 + 1;

            // Create three instances of GetPrimeNumbersLite, create three ThreadStarts, create three Threads,
            // Strart Threads, and finally Join threads back to main thread.
            GetPrimeNumbersLite pNL1 = new GetPrimeNumbersLite("1", min1, max1);
            GetPrimeNumbersLite pNL2 = new GetPrimeNumbersLite("2", min2, max2);
            GetPrimeNumbersLite pNL3 = new GetPrimeNumbersLite("3", min3, max3);

            ThreadStart tS1 = new ThreadStart(pNL1.FindPrimesInRange);
            ThreadStart tS2 = new ThreadStart(pNL2.FindPrimesInRange);
            ThreadStart tS3 = new ThreadStart(pNL3.FindPrimesInRange);

            Thread t1 = new Thread(tS1);
            Thread t2 = new Thread(tS2);
            Thread t3 = new Thread(tS3);

            t1.Start();
            t2.Start();
            t3.Start();

            t1.Join();
            t2.Join();
            t3.Join();

            GetPrimeNumbersLite.SortList();
            GetPrimeNumbersLite.WriteListToFile();

            GetPrimeNumbersLite primeNumbers = new GetPrimeNumbersLite("0", min, max);
            primeNumbers.FindPrimesInRange();

            // Get the end time and duration.
            DateTime EndTime = DateTime.Now.ToLocalTime();
            TimeSpan myTimeSpan = EndTime.Subtract(StartTime);
            Console.WriteLine("Duration:{0}", myTimeSpan);

            Console.WriteLine("\nClosing application... Press any key to continue.");
            Console.ReadKey();
        }
    }
}
