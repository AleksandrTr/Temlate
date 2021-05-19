import javax.swing.*;

public class FieldTitle extends JLabel {

    JLabel Title;

    FieldTitle(JTextField I,JPanel J, String Title_Text)
    {

       Title = new JLabel(Title_Text);

        Title.setBounds(J.getBounds().x,J.getBounds().y - 20,J.getWidth(),20);

    }


}
