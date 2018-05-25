import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Replace {
    private JFrame jFrame;
    private JTextField jTextField;
    public Replace(){
        jFrame=new JFrame("switch");
        JPanel jPanel=new JPanel();
        jFrame.setLocationRelativeTo(null);
        jFrame.setContentPane(jPanel);
        jFrame.setMinimumSize(new Dimension(400,300));
        jPanel.setLayout(new BorderLayout());
        JButton jButton=new JButton("شماره را وارد کنید و سپس این دکمه را فشار دهید");
        jButton.setPreferredSize(new Dimension(100,200));
        jTextField=new JTextField();
        jPanel.add(jButton,BorderLayout.NORTH);
        jPanel.add(jTextField,BorderLayout.CENTER);
        jButton.addActionListener(new func());
        jFrame.setVisible(true);
    }
    class func implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            DataBase.replace(Integer.parseInt(jTextField.getText()));
            jFrame.setVisible(false);
            jFrame.dispose();
        }
    }
}
