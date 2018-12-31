using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;

namespace DrawShapes {
    public partial class Form1 : Form {
        public Form1() {
            InitializeComponent();
        }

        private void DrawStuff_Click(object sender, EventArgs e) {
            DrawOvalAndRectangle();
            DrawIt2();
        }

        private void DrawOvalAndRectangle() {
            System.Drawing.Graphics graphics = this.CreateGraphics();
            System.Drawing.Rectangle rectangle = new System.Drawing.Rectangle(
                50, 100, 150, 150);
            graphics.DrawEllipse(System.Drawing.Pens.Black, rectangle);
            graphics.DrawRectangle(System.Drawing.Pens.Red, rectangle);
        }

        private void DrawIt2() {
            System.Drawing.Graphics graphics = this.CreateGraphics();
            System.Drawing.Rectangle rectangle = new System.Drawing.Rectangle(
                150, 200, 250, 350);
            graphics.DrawEllipse(System.Drawing.Pens.Red, rectangle);
            graphics.DrawRectangle(System.Drawing.Pens.Black, rectangle);
        }
    }
}
