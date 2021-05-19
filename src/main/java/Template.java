import java.awt.*;
import java.io.*;
import javax.swing.*;
import java.awt.event.*;


public class Template {

    JFrame Window;

    JPanel Text;

    Values Data;

    JButton LaunchInput;
    JButton LaunchCalcuate;

    JTextField SX_Field;
    JTextField SY_Field;
    JTextField SXY_Field;
    JTextField FTU_Field;
    JTextField FSU_Field;
    JTextField MainTesnion_Field;
    JTextField MainShear_Field;

    GridLayout Layout;


    //FieldTitle SX_Label;
   // FieldTitle SY_Label;
   // FieldTitle SXY_Label;
   // FieldTitle FTU_Label;
   // FieldTitle FSU_Label;
   // FieldTitle MainTesnion_Label;


    public static void main(String[] args) throws IOException {


    Template test = new Template();
    test.Start();

    }

    public void Start ()
    {
        Window = new JFrame();
        Window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        LaunchInput = new JButton("Input");
        LaunchInput.addActionListener(new LaunchInputListener());

        LaunchCalcuate = new JButton("Calculate");
        LaunchCalcuate.addActionListener(new LaunchCalculateListener());

        Data = new Values();

        Text = new JPanel(new FlowLayout(FlowLayout.LEFT));

        SX_Field = new JTextField("SX:",10);
        SY_Field = new JTextField("SY:",10);
        SXY_Field = new JTextField("SXY:",10);
        FTU_Field = new JTextField("FTU:",10);
        FSU_Field = new JTextField("FSU:",10);
        MainTesnion_Field = new JTextField(10);
        MainShear_Field = new JTextField(10);

        SX_Field.setBounds(10,10,10,10);
        SY_Field.setBounds(10,40,10,10);


        Window.getContentPane().add(BorderLayout.SOUTH,LaunchCalcuate);
        Window.getContentPane().add(BorderLayout.NORTH,LaunchInput);

        Layout = new GridLayout(7,1,10,30);
        Text.setLayout(Layout);

        Text.add(SX_Field);
        Text.add(SY_Field);
        Text.add(SXY_Field);
        Text.add(FTU_Field);
        Text.add(FSU_Field);
        Text.add(MainTesnion_Field);
        Text.add(MainShear_Field);
        Window.getContentPane().add(BorderLayout.CENTER,Text);


        Window.setSize(350,500);
        Window.setVisible(true);
    }

    class LaunchInputListener implements ActionListener
    {
       @Override
        public void actionPerformed(ActionEvent e)
        {
            double SX = Double.parseDouble(SX_Field.getText());
            double SY = Double.parseDouble(SY_Field.getText());
            double SXY = Double.parseDouble(SXY_Field.getText());
            double FTU = Double.parseDouble(FTU_Field.getText());
            double FSU = Double.parseDouble(FSU_Field.getText());
            Data.SetAllowables(FTU,FSU);
            Data.SetStress(SX,SY,SXY);
        }
    }

    class LaunchCalculateListener implements ActionListener
    {

        @Override
        public void actionPerformed(ActionEvent e)
        {

            double TensionStress = Data.MainTensionStress(Data.S_X,Data.S_Y, Data.S_XY);
            double ShearStress = Data.MainShearStress(Data.S_X,Data.S_Y, Data.S_XY);


            MainTesnion_Field.setText(Double.toString(TensionStress));
            MainShear_Field.setText(Double.toString(ShearStress));

            


        }
    }


}
