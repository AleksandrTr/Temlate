import java.awt.*;
import java.io.*;
import javax.swing.*;
import java.awt.event.*;


public class Template {

    JFrame Window;

    JMenuBar Menu;
    JMenu Main;
    JMenuItem Save;
    JMenuItem Exit;

    JPanel Text;

    Values Data;

    JButton LaunchInput;
    JButton LaunchCalculate;
    JButton SaveData;

    JTextField SX_Field;
    JTextField SY_Field;
    JTextField SXY_Field;
    JTextField FTU_Field;
    JTextField FSU_Field;
    JTextField MainTension_Field;
    JTextField MainShear_Field;
    JTextField MS_Tension_Field;
    JTextField MS_Shear_Field;

    GridLayout Layout;

    JLabel SX_Label;
    JLabel SY_Label;
    JLabel SXY_Label;
    JLabel FTU_Label;
    JLabel FSU_Label;
    JLabel MainTension_Label;
    JLabel MainShear_Label;
    JLabel MS_Tension_Label;
    JLabel MS_Shear_Label;

    public static void main(String[] args) throws IOException {

    Template test = new Template();
    test.Start();

    }

    public void Start ()
    {
        Window = new JFrame();
        Window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Menu = new JMenuBar();
        Main = new JMenu("Main");
        Save = new JMenuItem("Save");
        Save.addActionListener(new SaveListener());
        Exit = new JMenuItem("Exit");
        Exit.addActionListener(new ExitListener());

        Menu.add(Main);
        Main.add(Save);
        Main.add(Exit);
        Window.setJMenuBar(Menu);

        LaunchInput = new JButton("Input");
        LaunchInput.addActionListener(new LaunchInputListener());

        LaunchCalculate = new JButton("Calculate");
        LaunchCalculate.addActionListener(new LaunchCalculateListener());

        Data = new Values();

        Text = new JPanel(new FlowLayout(FlowLayout.LEFT));

        SX_Field = new JTextField(10);
        SY_Field = new JTextField(10);
        SXY_Field = new JTextField(10);
        FTU_Field = new JTextField(10);
        FSU_Field = new JTextField(10);
        MainTension_Field = new JTextField(10);
        MainShear_Field = new JTextField(10);
        MS_Tension_Field = new JTextField(10);
        MS_Shear_Field = new JTextField(10);

        SX_Label = new JLabel("SX component:");
        SY_Label = new JLabel("SY component:");
        SXY_Label = new JLabel("SXY component:");
        FTU_Label = new JLabel("FTU allowable:");
        FSU_Label = new JLabel("FSU allowable:");
        MainTension_Label = new JLabel("Main Tension:");
        MainShear_Label = new JLabel("Main Shear:");
        MS_Tension_Label = new JLabel("MS Tension:");
        MS_Shear_Label = new JLabel("MS Shear:");

        SX_Field.setBounds(10,10,10,10);
        SY_Field.setBounds(10,40,10,10);

        Window.getContentPane().add(BorderLayout.SOUTH,LaunchCalculate);
        Window.getContentPane().add(BorderLayout.NORTH,LaunchInput);


        Layout = new GridLayout(9,1,30,30);
        Text.setLayout(Layout);

        Text.add(SX_Label);
        Text.add(SX_Field);
        Text.add(SY_Label);
        Text.add(SY_Field);
        Text.add(SXY_Label);
        Text.add(SXY_Field);
        Text.add(FTU_Label);
        Text.add(FTU_Field);
        Text.add(FSU_Label);
        Text.add(FSU_Field);
        Text.add(MainTension_Label);
        Text.add(MainTension_Field);
        Text.add(MainShear_Label);
        Text.add(MainShear_Field);
        Text.add(MS_Tension_Label);
        Text.add(MS_Tension_Field);
        Text.add(MS_Shear_Label);
        Text.add(MS_Shear_Field);

        Window.getContentPane().add(BorderLayout.CENTER,Text);

        Window.setSize(400,600);
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

            double MS_Tension = Data.MS(TensionStress, Data.F_TU);
            double MS_Shear = Data.MS(ShearStress, Data.F_SU);

            MainTension_Field.setText(String.format("%.2f",TensionStress));
            MainShear_Field.setText(String.format("%.2f",ShearStress));

            MS_Tension_Field.setText(String.format("%.2f",MS_Tension));
            MS_Shear_Field.setText(String.format("%.2f",MS_Shear));

        }
    }

    class SaveListener implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {
            //add file chooser
            try {
                FileOutputStream inFile = new FileOutputStream("OutputData");
                ObjectOutputStream inObject = new ObjectOutputStream(inFile);
                inObject.writeObject(Data);
                inObject.close();
            }
            catch (Exception ex)
            {
                ex.printStackTrace();
            }


        }
    }

    class ExitListener implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            System.exit(0);
        }
    }

}
