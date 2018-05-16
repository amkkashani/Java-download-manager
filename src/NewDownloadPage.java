import com.sun.org.apache.bcel.internal.generic.NEW;

import javax.management.remote.JMXAddressable;
import javax.management.remote.JMXServiceURL;
import javax.print.DocFlavor;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class NewDownloadPage {
    JFrame jframe =new JFrame();
    JFileChooser jFileChooser;

    public NewDownloadPage(){
        //JOptionPane j = new JOptionPane();
        JPanel surface=new JPanel();
        JLabel upLeft= new JLabel();
        upLeft.setIcon(new ImageIcon("newdowonload.JPG"));
        JPanel upRith=new JPanel();
        JLabel uperst=new JLabel(new ImageIcon("new download.jpg"));
        upRith.setLayout(new GridLayout(3,1,20,10));
        JPanel center=new JPanel();
        jFileChooser=new JFileChooser();           //**********************
        jFileChooser.setFileSelectionMode( JFileChooser.DIRECTORIES_ONLY);
        System.out.println(UIManager.getLookAndFeel());
        try {
            ((JPanel)((JPanel)jFileChooser.getComponent(3)).getComponent(3)).setVisible(false);
        }catch (Exception ex){

        }
        //((JButton)((JPanel)jFileChooser.getComponent(5)).getAccessibleContext().getAccessibleChild(2)).setVisible(false);
        JPanel down=new JPanel(new GridLayout(1,2));
        JButton ok=new JButton("ok");
        JButton cancel=new JButton("cancel");
        down.add(ok);
        down.add(cancel);
        ok.addActionListener(new clic());
        cancel.addActionListener(new clic());

        center.add(jFileChooser);
        JTextField jTextField1=new JTextField("https://");
        //jTextField1.setPreferredSize(new Dimension(40,30));
        JTextField jTextField2=new JTextField();
        JTextField jTextField3=new JTextField();
        jTextField3.setEditable(false);
        upRith.add(jTextField1);
        upRith.add(jTextField2);
        upRith.add(jTextField3);
        JPanel up=new JPanel();
//        j.setVisible(true);
        jframe.setMinimumSize(new Dimension(500,700));
        jframe.setResizable(false);
        jframe.setLocationRelativeTo(null);
        surface.setLayout(new BorderLayout());
        up.setLayout(new BorderLayout(10,0));
        up.add(uperst,BorderLayout.NORTH);
        up.add(upLeft,BorderLayout.WEST);
        up.add(upRith,BorderLayout.CENTER);
        surface.add(down,BorderLayout.SOUTH);
        surface.add(up,BorderLayout.NORTH);
        surface.add(center,BorderLayout.CENTER);
        jframe.setContentPane(surface);
        jframe.setVisible(true);
    }
    class clic implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            String dastoor=((JButton)e.getSource()).getText();
            System.out.println(dastoor);

            if(dastoor=="cancel"){
                jframe.setVisible(false); //you can't see me!
                jframe.dispose(); //Destroy the JFrame object
            }
            else if (dastoor=="ok"){
                try {
                    System.out.println((jFileChooser.getSelectedFile().getAbsolutePath()));
                }
                catch (NullPointerException e1){
                    System.out.println("آدرس وارد نشد؟");
                    jframe.setVisible(false); //you can't see me!
                    jframe.dispose(); //Destroy the JFrame object
                }
                }
        }
    }
}
