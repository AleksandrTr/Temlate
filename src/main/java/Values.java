import javax.swing.*;

public class Values {

    //Stress components
    public double S_X;
    public double S_Y;
    public double S_XY;

    //Material allowables
    public double F_TU;
    public double F_SU;

    public void SetStress (double SX,double SY, double SXY)
    {
        //Giving numbers
       // String SX = JOptionPane.showInputDialog(null,"Input SX");
       // String SY = JOptionPane.showInputDialog(null,"Input SY");
        //String SXY = JOptionPane.showInputDialog(null,"Input SXY");

         S_X = SX;
         S_Y = SY;
         S_XY = SXY;

    }


    public void SetAllowables (double FTU, double FSU)
    {
        //Giving allowables
       // String FTU = JOptionPane.showInputDialog(null,"F_TU");
        // String FSU = JOptionPane.showInputDialog(null,"F_SU");

        F_TU = FTU;
        F_SU = FSU;
    }

    public double MainTensionStress(double S_X, double S_Y, double S_XY)
    {
        double S_1 = Math.round((S_X + S_Y + Math.sqrt(Math.pow(S_X - S_Y, 2) + 4 * Math.pow(S_XY, 2)))/2);
        double S_2 = Math.round((S_X + S_Y - Math.sqrt(Math.pow(S_X - S_Y, 2) + 4 * Math.pow(S_XY, 2)))/2);

        return Math.max(S_1, S_2);

    }

    public double MainShearStress(double S_X, double S_Y, double S_XY)
    {
        double S_1 = Math.round((S_X + S_Y + Math.sqrt(Math.pow(S_X - S_Y, 2) + 4 * Math.pow(S_XY, 2))) / 2);
        double S_2 = Math.round((S_X + S_Y - Math.sqrt(Math.pow(S_X - S_Y, 2) + 4 * Math.pow(S_XY, 2))) / 2);

        double S_12;

        if (S_1 > S_2)
        {
            S_12 = Math.round((S_1 - S_2) / 2);
        } else
        {
            S_12 = Math.round((S_2 - S_1) / 2);
        }
        return S_12;
    }
}
