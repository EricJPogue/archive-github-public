using System;
using System.Net;
using System.Net.Sockets;
using System.Text;
using System.Xml;
using System.Collections;

namespace ShapeDrawDataServerLite {
    class EJPShape {
        // C# Auto-Implement Properties
        public int positionX { get; set; } = 0;
        public int positionY { get; set; } = 0;
        public int width { get; set; } = 0;
        public int height { get; set; } = 0;
        public string type { get; set; } = "";

        public EJPShape() {
        }

        private static int StringToInt(string stringIn) {
            return Convert.ToInt32(stringIn);
        }

        public void SetPropertyFromXML(string elementName, string elementText) {
            if (elementName == "type") {
                type = elementText;
            } else if (elementName == "x") {
                positionX = StringToInt(elementText);
            } else if (elementName == "y") {
                positionY = StringToInt(elementText);
            } else if (elementName == "width") {
                width = StringToInt(elementText);
            } else if (elementName == "height") {
                height = StringToInt(elementText);
            }
        }

        public override string ToString() {
            return String.Format("Type:{0}; X:{1}; Y:{2}; Width:{3}; Height{4};",
                type, positionX, positionY, width, height);
        }

        public string toXML(string color) {
            string returnString = "<shape>\n";
            returnString += string.Format("  <type>{0}</type>\n", type);
            returnString += string.Format("  <x>{0}</x>\n", positionX);
            returnString += string.Format("  <y>{0}</y>\n", positionY);
            returnString += string.Format("  <width>{0}</width>\n", width);
            returnString += string.Format("  <height>{0}</height>\n", height);
            returnString += string.Format("  <color>{0}</color>\n", color);
            returnString += "</shape>\n";
            return returnString;
        }
    }

    class EJPShapeModel : ArrayList {
        public EJPShapeModel() {
        }

        public void SetModelFromXML(string address) {
            EJPShape xmlShape = new EJPShape();
            string elementName = "";

            XmlTextReader reader = new XmlTextReader(address);
            while (reader.Read()) {
                switch (reader.NodeType) {
                    case XmlNodeType.Element: // New node.
                        elementName = reader.Name;
                        break;

                    case XmlNodeType.Text: // New text element.
                        xmlShape.SetPropertyFromXML(elementName, reader.Value);
                        break;

                    case XmlNodeType.EndElement: // End of node.
                        if (reader.Name == "shape") { // If node was a shape, add it to the list.
                            Add(xmlShape);
                            xmlShape = new EJPShape();
                        }
                        break;
                }
            }
        }
    }

    class EJPColorModel {
        public EJPColorModel() {
        }

        public void setModelFromXML(string address) {
            // Get the XML from the Internet... like we did in EJPShapeModel
            // Parse the XML... like we did in EJPShapeModel and EJPShape
        }

        public string getColorForShape(string shape) {
            return "blue";
        }
    }

    class ShapeDrawServer {
        public ShapeDrawServer() {
        }

        public string ProcessShapes() {
            EJPShapeModel model = new EJPShapeModel();
            model.SetModelFromXML("http://www.epogue.info/CPSC-24500/Week07/InternetShapeDraw.xml");

            EJPColorModel colorModel = new EJPColorModel();
            colorModel.setModelFromXML("http://www.epogue.info/CPSC-24500/Week08/ShapeDrawColors.xml");

            string returnString = "<?xml version=\"1.0\"?>\n";
            returnString += "<shapeslist>\n";

            foreach (Object obj in model) {
                EJPShape shape = (EJPShape)obj;
                returnString += shape.toXML(colorModel.getColorForShape(shape.type));
            }

            returnString += "</shapeslist>\n";

            return returnString;
        }

        public string ProcessShapesAndColor() {
            string returnString = "<?xml version=\"1.0\"?>\n";

            // Do your stuff here by utilizing a class that you develop names "ShapeDrawDataSever"
            // and return your results in the return string.
            // 1 - Download and parse:
            //     http://www.epogue.info/CPSC-24500/Week07/InternetShapeDraw.xml
            // 2 - Download and parse:
            //     http://www.epogue.info/CPSC-24500/Week08/ShapeDrawColors.xml
            // 3 - Aggregate the resulting data into something that looks like:
            //     http://www.epogue.info/CPSC-24500/Week08/ShapeDrawDataServerResponse.xml 
            // 4 - Respond to "get-shapeandcolors;" request with well formed XML for #3.
            // 5 - Fix the Dns.Resolve() Warning message by correctly using GetHostEntry.

            return returnString;
        }

    }

    class Program {
        private static string ProcessCustomSocketCommands(string request) {
            ShapeDrawServer server = new ShapeDrawServer();
            if (request.IndexOf("get-shapes;") > -1) {
                return server.ProcessShapes();
            } else if (request.IndexOf("get-shapesandcolors;") > -1) {
                return server.ProcessShapesAndColor();
            }
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
