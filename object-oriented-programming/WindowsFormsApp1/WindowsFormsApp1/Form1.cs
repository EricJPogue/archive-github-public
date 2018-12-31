using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;

namespace WindowsFormsApp1 {
    public partial class Form1 : Form {
        public Form1() {
            InitializeComponent();
        }

        private void Form1_Load(object sender, EventArgs e) {

        }

        protected override void OnPaint(PaintEventArgs e) {
            // Call the OnPaint method of the base class.  
            base.OnPaint(e);
            e.Graphics.DrawString("EJP Hello World!!!", Font, new SolidBrush(ForeColor), ClientRectangle);

            foreach (Object obj in model) {
                //ShapeParmFromXML shape = (ShapeParmFromXML)obj;
                Rectangle rect = shape.ToRectangle();


            }


        }
    }
}
