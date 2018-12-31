// Eric Pogue
// Week 7
// InternetShapeDrawList

using System;
using System.Drawing;
using System.Windows.Forms;
using System.Xml;
using System.Collections;

namespace InternetShapeDrawLite {
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

        public Rectangle ToRectangle() {
            return new Rectangle(positionX, positionY, positionX+width, positionY+height);
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

    public partial class ShapesView : Form {
        private EJPShapeModel model;

        public ShapesView() {
            InitializeComponent();
            model = new EJPShapeModel();
            model.SetModelFromXML("http://www.epogue.info/CPSC-24500/Week07/InternetShapeDraw.xml");
        }

        private void Form1_Load(object sender, EventArgs e) {
        }

        protected override void OnPaint(PaintEventArgs e) {
            // Call the OnPaint method of the base class.  
            base.OnPaint(e);
            e.Graphics.DrawString("EJP Hello World!!!", Font, new SolidBrush(ForeColor), ClientRectangle);

            foreach (Object obj in model) {
                EJPShape shape = (EJPShape)obj;
                Rectangle rect = shape.ToRectangle();

                if (shape.type == "oval") {
                    EJPDrawOval(e, rect);
                } else {
                    EJPDrawRectangle(e, rect);
                }
            }
        }

        private void EJPDrawRectangle(PaintEventArgs e, Rectangle rect) {
            e.Graphics.DrawRectangle(Pens.Red, rect);
        }

        private void EJPDrawOval(PaintEventArgs e, Rectangle rect) {
            e.Graphics.DrawEllipse(Pens.Black, rect);
        }
    }
}
