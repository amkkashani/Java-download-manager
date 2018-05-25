import com.sun.org.apache.bcel.internal.generic.DADD;
import com.sun.org.apache.bcel.internal.generic.NEW;

import javax.management.remote.JMXAddressable;
import javax.management.remote.JMXServiceURL;
import java.util.Calendar;
import javax.print.DocFlavor;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.xml.crypto.Data;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;

public class NewDownloadPage {
    JFrame jframe = new JFrame();
    JFileChooser jFileChooser;
    private JTextField jTextField1;
    private JTextField jTextField2;
    private JTextField jTextField3;


    public NewDownloadPage() {
        //JOptionPane j = new JOptionPane();
        JPanel surface = new JPanel();
        JLabel upLeft = new JLabel();
        upLeft.setIcon(new ImageIcon("newdowonload.JPG"));
        JPanel upRith = new JPanel();
        JLabel uperst = new JLabel(new ImageIcon("new download.jpg"));
        upRith.setLayout(new GridLayout(3, 1, 20, 10));
        JPanel center = new JPanel();
        jFileChooser = new JFileChooser();           //**********************
        jFileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        try {
            ((JPanel) ((JPanel) jFileChooser.getComponent(3)).getComponent(3)).setVisible(false);
        } catch (Exception ex) {

        }
        //((JButton)((JPanel)jFileChooser.getComponent(5)).getAccessibleContext().getAccessibleChild(2)).setVisible(false);
        JPanel down = new JPanel(new GridLayout(1, 3));
        JButton ok = new JButton("ok");
        JButton cancel = new JButton("cancel");
        JButton addQueue = new JButton("add to queue");
        down.add(ok);
        down.add(addQueue);
        down.add(cancel);
        addQueue.addActionListener(new clic());
        ok.addActionListener(new clic());
        cancel.addActionListener(new clic());

        center.add(jFileChooser);
        jTextField1 = new JTextField("https://");
        //jTextField1.setPreferredSize(new Dimension(40,30));
        jTextField2 = new JTextField();
        jTextField3 = new JTextField();
        jTextField3.setEditable(false);
        upRith.add(jTextField1);
        upRith.add(jTextField2);
        upRith.add(jTextField3);
        JPanel up = new JPanel();
//        j.setVisible(true);
        jframe.setMinimumSize(new Dimension(500, 700));
        jframe.setResizable(false);
        jframe.setLocationRelativeTo(null);
        surface.setLayout(new BorderLayout());
        up.setLayout(new BorderLayout(10, 0));
        up.add(uperst, BorderLayout.NORTH);
        up.add(upLeft, BorderLayout.WEST);
        up.add(upRith, BorderLayout.CENTER);
        surface.add(down, BorderLayout.SOUTH);
        surface.add(up, BorderLayout.NORTH);
        surface.add(center, BorderLayout.CENTER);
        jframe.setContentPane(surface);
        jframe.setVisible(true);
    }

    class clic implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {

            String dastoor = ((JButton) e.getSource()).getText();
            System.out.println(dastoor);

            if (dastoor.equals("cancel")) {
                jframe.setVisible(false); //you can't see me!
                jframe.dispose(); //Destroy the JFrame object
            }
            //این جا بررسی می شود کد آیا در بخش ممنوعه وجوددارد یا خیر
            else if (DataBase.checkAvoided(jTextField1.getText())) {
                System.out.println("با ارز تاسف استفاده از این سایت مجاز نمی باشد");
                return;
            } else if (dastoor.equals("add to queue")) {
                try {
                    DataBase.queue.add(new FormQueue(jTextField2.getText(), jTextField1.getText(), jFileChooser.getCurrentDirectory().getPath(), 50, 50, 2, DataBase.queue.size() + 1,Calendar.getInstance()));
                } catch (Exception e1) {
                    DataBase.queue.add(new FormQueue(jTextField2.getText(), jTextField1.getText(), DataBase.getPathDifault(), 50, 50, 2, DataBase.queue.size() + 1,Calendar.getInstance()));
                }
                if (DataBase.process.size() >= 7) {
                    MyFrame.processing.setLayout(new GridLayout(DataBase.process.size() + 1, 1));
                }
                DataBase.updateQueue();
                jframe.setVisible(false); //you can't see me!
                jframe.dispose(); //Destroy the JFrame object
            } else if (dastoor.equals("ok")) {

                //FormDownload.selectedName="";
                try {
                    if (DataBase.process.size() >= 7) {
                        MyFrame.processing.setLayout(new GridLayout(DataBase.process.size() + 1, 1));
                    }
                    System.out.println((jFileChooser.getSelectedFile().getAbsolutePath()));
                    Calendar time = Calendar.getInstance();
                    DataBase.process.add(new FormDownload(jTextField2.getText(), jTextField1.getText(), jFileChooser.getCurrentDirectory().getPath(), 50, 20, 45,time));
                } catch (NullPointerException e1) {
                    //*************************************************************************************************size , speed , improve دستی اد شده
                    Calendar time = Calendar.getInstance();
                    DataBase.process.add(new FormDownload(jTextField2.getText(), jTextField1.getText(), DataBase.getPathDifault(), 50, 20, 45,time));
                    System.out.println("آدرس وارد نشد؟");
                    if (DataBase.process.size() >= 7) {
                        MyFrame.processing.setLayout(new GridLayout(DataBase.process.size() + 1, 1));
                    }

                    //*************************************************************************************************size , speed , improve دستی اد شده
                }
                DataBase.updateProcesing(1);
                jframe.setVisible(false); //you can't see me!
                jframe.dispose(); //Destroy the JFrame object
            }
        }
    }
}
