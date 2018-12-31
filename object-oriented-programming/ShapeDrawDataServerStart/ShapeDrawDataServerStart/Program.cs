using System;
using System.Net;
using System.Net.Sockets;
using System.Text;

namespace ShapeDrawDataServerStart {
    class ShapeDrawServer {
        public ShapeDrawServer() {
        }

        public string ProcessShapes() {
            return "Processing Shapes Complete.\nline2\nline3";
        }
    }

    class Program {
        private static string ProcessCustomSocketCommands(string request) {
            if (request.IndexOf("get-shapes;") > -1) {
                ShapeDrawServer server = new ShapeDrawServer();
                return server.ProcessShapes();
            } else if (request.IndexOf("get-shapesandcolors;") > -1) {
                // Do your stuff here by utilizing a class that you develop names "ShapeDrawDataSever"
                // and return your results in the return string.
                // 1 - Download and parse:
                //     http://www.epogue.info/CPSC-24500/Week07/InternetShapeDraw.xml
                // 2 - Download and parse:
                //     http://www.epogue.info/CPSC-24500/Week08/ShapeDrawColors.xml
                // 3 - Aggregate the resulting data into something that looks like:
                //     http://www.epogue.info/CPSC-24500/Week08/ShapeDrawDataServerResponse.xml 
                // 4 - Respond to "get-shapeandcolors;" request with well formed XML for #3.
            }   // 5 - Fix the Dns.Resolve() Warning message by correctly using GetHostEntry.

            return null;
        }

        private static void ProcessSocketCommands() {
            // Establish socket listener on local IP.
            int port = 11001;
            IPHostEntry ipHostInfo = Dns.Resolve(Dns.GetHostName());
            IPAddress ipAddress = ipHostInfo.AddressList[0];
            IPEndPoint localEndPoint = new IPEndPoint(ipAddress, port);

            Socket listener = new Socket(AddressFamily.InterNetwork,
                SocketType.Stream, ProtocolType.Tcp);

            // Bind the socket and listen for incoming connections. Catch any errors.
            try {
                listener.Bind(localEndPoint);
                listener.Listen(10);

                string data = null;
                byte[] bytes = new Byte[1024 * 64];

                bool continueConnection = true;
                while (continueConnection) {
                    Console.WriteLine("Waiting for new connection...");
                    // Program is suspended while waiting for an incoming connection.  
                    data = null;
                    Socket handler = listener.Accept();

                    // An incoming connection needs to be processed.
                    bool continueCommand = true;
                    while (continueCommand) {
                        bytes = new byte[1024];
                        int bytesRec = handler.Receive(bytes);
                        data += Encoding.ASCII.GetString(bytes, 0, bytesRec);

                        Console.WriteLine("Bytes received:{1} Text received : {0}", data, bytesRec);
                        if (data.IndexOf(";") > -1) {
                            continueCommand = false;
                        }
                    }

                    string request = data;
                    Console.WriteLine("Process request: {0}", request);

                    // Handle responses for null, "hello;", "echo;", and "close;".
                    string response = null;
                    if (request == null) {
                        response = "Error processing command: " + request;
                    } else if (request.IndexOf("hello;") > -1) {
                        response = "Hello World!!!";
                    } else if (request.IndexOf("close;") > -1) {
                        response = "Shutting down ShapeDrawDataSever... Goodbye.";
                        continueConnection = false;
                    } else {
                        response = ProcessCustomSocketCommands(request);
                    }

                    handler.Send(Encoding.ASCII.GetBytes(response));

                    handler.Shutdown(SocketShutdown.Both);
                    handler.Close();
                }
            } catch (Exception e) {
                Console.WriteLine(e.ToString());
            }

        }

        static void Main(string[] args) {
            Console.WriteLine("Starting application...\n");

            ProcessSocketCommands();

            Console.WriteLine("\nClosing application... Press any key to continue.");
            Console.ReadKey();
        }
    }
}
