using System;
using ShapeModelXML;

namespace DownloadAndParseXML {
    class Program {
        static void Main(string[] args) {
            ShapeModel model = new ShapeModel();
            model.SetModelFromXML("http://www.epogue.info/CPSC-24500/Week07/InternetShapeDraw.xml");
        }
    }
}
