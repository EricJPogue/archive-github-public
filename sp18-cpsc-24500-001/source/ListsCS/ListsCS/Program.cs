using System;
using System.Collections;

namespace ListsCS {
    class Program {
        // Note on .NET (C#) ArrayList Thread Safety: Any public static members of this type are thread safe. Any instance 
        // members are not guaranteed to be thread safe.
        static ArrayList myArrayList = new ArrayList();

        static void Main(string[] args) {
            System.Console.WriteLine("Starting application...");

            myArrayList.Add(5);
            myArrayList.Add(15);
            myArrayList.Add(10005);
            myArrayList.Add(52);
            myArrayList.Add(5321);
            myArrayList.Add(12);
            myArrayList.Add(-5);
            myArrayList.Add(-10005);

            //Note: Needed to comment out following line in order to be able to Sort() without an exception being thrown.
            //myArrayList.Add("Eric");

            myArrayList.Sort();

            foreach (object obj in myArrayList) {
                Console.WriteLine(obj);
            }

            Console.WriteLine("\nClosing application... Press any key to continue.");
            Console.ReadKey();
        }
    }
}
