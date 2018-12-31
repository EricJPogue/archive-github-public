using System;
using System.Net;
using System.Net.Sockets;
using System.Text;

namespace ShapeDrawDataClient {
    class Program {
        static void ProcessRequest(string request) {
            Console.WriteLine("Request: " + request);

            // Verify that the last charactor in the request is a semi-colon.
            if (request.LastIndexOf(";") != (request.Length - 1)) {
                Console.WriteLine("Error: All requests must end with a semi-colon (';')\n");
                return;
            }

            try {
                byte[] bytes = new byte[1024 * 64];

                // Establish the remote endpoint for the socket.  
                int port = 11001;
                IPHostEntry ipHostInfo = Dns.Resolve(Dns.GetHostName());
                IPAddress ipAddress = ipHostInfo.AddressList[0];
                IPEndPoint remoteEP = new IPEndPoint(ipAddress, port);

                Socket sender = new Socket(AddressFamily.InterNetwork,
                    SocketType.Stream, ProtocolType.Tcp);

                // Connect the socket to the remote endpoint. Catch any errors.  
                sender.Connect(remoteEP);

                // Send the data through the socket.  
                int bytesSent = sender.Send(Encoding.ASCII.GetBytes(request));

                // Receive the response from the remote device.  
                int bytesRec = sender.Receive(bytes);

                Console.WriteLine(Encoding.ASCII.GetString(bytes, 0, bytesRec));
                Console.WriteLine("... received from {0}\n", sender.RemoteEndPoint.ToString());

                // Release the socket.  
                sender.Shutdown(SocketShutdown.Both);
                sender.Close();
            } catch (Exception e) {
                Console.WriteLine("Exception: {0}", e.ToString());
            }
        }

        static void Main(string[] args) {
            Console.WriteLine("Starting application... valid commands include:");
            Console.WriteLine("    end; - closes client application");
            Console.WriteLine("    hello; - tests connection with server which returns 'Hello World!!!");
            Console.WriteLine("    close; - closes remote server");
            Console.WriteLine("    get-shapes; - gets InternetShapes");
            Console.WriteLine("    get-shapesandcolors; - gets InternetShapes including their colors\n");

            string lineIn = Console.ReadLine();
            while (lineIn != "end;") {
                ProcessRequest(lineIn);
                lineIn = Console.ReadLine();
            }
        }
    }
}
