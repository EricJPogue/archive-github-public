using System;
using System.Net;

namespace DownloadXML {
    class Program {
        static void Main(string[] args) {
            Console.WriteLine("Starting application...");

            Console.WriteLine("You entered the following {0} command line arguments:", args.Length);
            for (int i = 0; i < args.Length; i++) {
                Console.WriteLine("{0}", args[i]);
            }

            WebClient client = new WebClient();
            string address = args[0];
            string reply = client.DownloadString(address);
            Console.WriteLine("Address: {0}", address);
            Console.WriteLine("XML Content:");
            Console.WriteLine(reply);

            Console.WriteLine("\nClosing application... Press any key to continue.");
            Console.ReadKey();
        }
    }
}
