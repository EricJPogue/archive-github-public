namespace DrawShapes {
    partial class Form1 {
        /// <summary>
        /// Required designer variable.
        /// </summary>
        private System.ComponentModel.IContainer components = null;

        /// <summary>
        /// Clean up any resources being used.
        /// </summary>
        /// <param name="disposing">true if managed resources should be disposed; otherwise, false.</param>
        protected override void Dispose(bool disposing) {
            if (disposing && (components != null)) {
                components.Dispose();
            }
            base.Dispose(disposing);
        }

        #region Windows Form Designer generated code

        /// <summary>
        /// Required method for Designer support - do not modify
        /// the contents of this method with the code editor.
        /// </summary>
        private void InitializeComponent() {
            this.DrawStuff = new System.Windows.Forms.Button();
            this.SuspendLayout();
            // 
            // DrawStuff
            // 
            this.DrawStuff.Location = new System.Drawing.Point(12, 453);
            this.DrawStuff.Name = "DrawStuff";
            this.DrawStuff.Size = new System.Drawing.Size(75, 23);
            this.DrawStuff.TabIndex = 0;
            this.DrawStuff.Text = "Draw";
            this.DrawStuff.UseVisualStyleBackColor = true;
            this.DrawStuff.Click += new System.EventHandler(this.DrawStuff_Click);
            // 
            // Form1
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(6F, 13F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.ClientSize = new System.Drawing.Size(1193, 488);
            this.Controls.Add(this.DrawStuff);
            this.Name = "Form1";
            this.Text = "DrawShapes";
            this.ResumeLayout(false);

        }

        #endregion

        private System.Windows.Forms.Button DrawStuff;
    }
}

