using System;

namespace HelloWorldCS {
    class Program {
        static void Main(string[] args) {
            Console.WriteLine("Starting application...");

            Console.WriteLine("You entered the following {0} command line arguments:", args.Length);
            for (int i = 0; i < args.Length; i++) {
                Console.WriteLine("{0}", args[i]);
            }

            // Write code here.

            Console.WriteLine("\nClosing application... Press any key to continue.");
            Console.ReadKey();
        }
    }
}
