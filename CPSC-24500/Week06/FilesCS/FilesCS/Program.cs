using System;
using System.IO;

namespace FilesCS {
    class Program {
        static void Main(string[] args) {
            System.Console.WriteLine("Starting FilesCS...");

            Console.WriteLine("You entered the following {0} command line arguments:", args.Length);
            for (int i = 0; i < args.Length; i++) {
                Console.WriteLine("{0}", args[i]);
            }

            // Text file in "current" folder.
            String textFileName = args[0];
            String[] lines = { "First line", "Second line", "Third line", "Fourth line" };
            System.IO.File.WriteAllLines(textFileName, lines);

            // Write the string array to a new file named "WriteLines.txt" stored in the Documents folder.
            string mydocpath = Environment.GetFolderPath(Environment.SpecialFolder.MyDocuments);
            using (StreamWriter outputFile = new StreamWriter(mydocpath + @"\WriteLines.txt")) {
                foreach (string line in lines)
                    outputFile.WriteLine(line);
            }

            // Binary file writing and reading.
            String binaryFileName = args[1];
            FileStream F = new FileStream(binaryFileName, FileMode.Create, FileAccess.ReadWrite);
            for (int i = 0; i <= 20; i++) {
                F.WriteByte((byte)i);
            }

            F.Position = 0;
            for (int i = 0; i <= 20; i++) {
                Console.Write(F.ReadByte() + " ");
            }
            F.Close();
            Console.WriteLine();

            Console.WriteLine("\nEnd of FilesCS... Press and key to continue.");
            Console.ReadKey();
        }
    }
}
