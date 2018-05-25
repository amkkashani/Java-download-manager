import com.sun.deploy.panel.JavaPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

public class SettingPage {
    private JFrame jFrame = new JFrame("setting");
    private JPanel surface = new JPanel();
    private JFileChooser jFileChooser;
    public static int numberOfDownloadInSameTime;
    public static String lastLookAndFeel="nimbus";

    public SettingPage() {
//        System.out.println(numberOfDownloadInSameTime);
        jFrame.setMinimumSize(new Dimension(500, 500));
        jFrame.setContentPane(surface);
        jFrame.setLocationRelativeTo(null);
        jFrame.setResizable(false);
        JLabel photo = new JLabel(new ImageIcon("settingss.jpg"));
        surface.setLayout(new BorderLayout());
        JTextField numberDownload = new JTextField();
        numberDownload.setText(String.valueOf(numberOfDownloadInSameTime));

        //**************************برای save شدن تعداد دانلود های مجاز باید یک بار از روی لیبل مربوط به آن با موس عبور کنید و بر روی آن کلیک کنید******************
        photo.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {

            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {
                if(!numberDownload.getText().equals("")) {
                    numberOfDownloadInSameTime = Integer.parseInt(numberDownload.getText());
                    System.out.println(numberOfDownloadInSameTime + "این عدد ثبت شد");
                }
            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });
        jFileChooser = new JFileChooser();
        jFileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        JPanel up = new JPanel();
        JPanel down = new JPanel(new GridLayout(1, 3));
        JButton nimbus = new JButton("nimbus");
        JButton windows = new JButton("windows");
        JButton metal = new JButton("metal");
        metal.addActionListener(new look());
        nimbus.addActionListener(new look());
        windows.addActionListener(new look());
        up.setLayout(new BorderLayout());
        up.add(new JLabel("number of download in same time :"), BorderLayout.WEST);
        up.add(photo, BorderLayout.NORTH);
        up.add(numberDownload, BorderLayout.CENTER);
        surface.add(up, BorderLayout.NORTH);
        surface.add(jFileChooser, BorderLayout.CENTER);
        ((JButton) ((JPanel) ((JPanel) jFileChooser.getComponent(3)).getComponent(3)).getComponent(1)).setText("Accept");
        ((JButton) ((JPanel) ((JPanel) jFileChooser.getComponent(3)).getComponent(3)).getComponent(0)).setVisible(false);
        ((JButton) ((JPanel) ((JPanel) jFileChooser.getComponent(3)).getComponent(3)).getComponent(1)).addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jFrame.dispatchEvent(new WindowEvent(jFrame, WindowEvent.WINDOW_CLOSING));
                System.out.println(jFileChooser.getCurrentDirectory().getPath());
                DataBase.setPathDifault(jFileChooser.getCurrentDirectory().getPath());
            }
        });
        metal.addActionListener(new look());
        JPanel extraDown = new JPanel(new BorderLayout());
        JPanel extraDeleted = new JPanel();
        extraDeleted.setLayout(new GridLayout(1, 2));
        JButton extra = new JButton("advance option");
        extra.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                /*
                در این قسمت بخش  ادونس باز می شود
                 */
                AdvanceSetting advanceSetting = new AdvanceSetting();
            }
        });
        JButton deleted = new JButton("deleted");
        deleted.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Deleted deleted1 = new Deleted();
            }
        });
        extraDeleted.add(deleted);
        extraDeleted.add(extra);
        extraDown.add(extraDeleted, BorderLayout.NORTH);
        down.add(nimbus);
        down.add(windows);
        down.add(metal);
        extraDown.add(down, BorderLayout.CENTER);
        surface.add(extraDown, BorderLayout.SOUTH);
        jFrame.setVisible(true);
    }

    class look implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (((JButton) e.getSource()).getText().equals("metal")) {
                try {
                    UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
                    SwingUtilities.updateComponentTreeUI(jFrame);
                    SwingUtilities.updateComponentTreeUI(MyFrame.frame);
                    lastLookAndFeel="metal";
                } catch (ClassNotFoundException e1) {
                    e1.printStackTrace();
                } catch (InstantiationException e1) {
                    e1.printStackTrace();
                } catch (IllegalAccessException e1) {
                    e1.printStackTrace();
                } catch (UnsupportedLookAndFeelException e1) {
                    e1.printStackTrace();
                }
            } else if (((JButton) e.getSource()).getText().equals("nimbus")) {
//                System.out.println("salam");
                try {
                    UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
                    SwingUtilities.updateComponentTreeUI(jFrame);
                    SwingUtilities.updateComponentTreeUI(MyFrame.frame);
                    lastLookAndFeel="nimbus";
                } catch (ClassNotFoundException e1) {
                    e1.printStackTrace();
                } catch (InstantiationException e1) {
                    e1.printStackTrace();
                } catch (IllegalAccessException e1) {
                    e1.printStackTrace();
                } catch (UnsupportedLookAndFeelException e1) {
                    e1.printStackTrace();
                }
            } else if (((JButton) e.getSource()).getText().equals("windows")) {
                try {
                    UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
                    SwingUtilities.updateComponentTreeUI(jFrame);
                    SwingUtilities.updateComponentTreeUI(MyFrame.frame);
                    lastLookAndFeel="windows";
                } catch (ClassNotFoundException e1) {
                    e1.printStackTrace();
                } catch (InstantiationException e1) {
                    e1.printStackTrace();
                } catch (IllegalAccessException e1) {
                    e1.printStackTrace();
                } catch (UnsupportedLookAndFeelException e1) {
                    e1.printStackTrace();
                }
            }
            try {
                ((JButton) ((JPanel) ((JPanel) jFileChooser.getComponent(3)).getComponent(3)).getComponent(1)).setText("Accept");
                ((JButton) ((JPanel) ((JPanel) jFileChooser.getComponent(3)).getComponent(3)).getComponent(0)).setText("back");
                ((JButton) ((JPanel) ((JPanel) jFileChooser.getComponent(3)).getComponent(3)).getComponent(1)).addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        jFrame.dispatchEvent(new WindowEvent(jFrame, WindowEvent.WINDOW_CLOSING));
                        System.out.println(jFileChooser.getCurrentDirectory().getPath());
                        DataBase.setPathDifault(jFileChooser.getCurrentDirectory().getPath());

                    }
                });
            } catch (Exception e1) {

            }


        }
    }
    public static void writeCondition(){
        try {
            FileWriter out =new FileWriter(new File("data\\settings.jdm"));
            out.write(lastLookAndFeel+"\n"+numberOfDownloadInSameTime+"\n"+DataBase.getPathDifault()+"\n");
            out.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void readCondition(){
        try {
            BufferedReader input = new BufferedReader(new FileReader("data\\settings.jdm"));
            lastLookAndFeel=input.readLine();
            numberOfDownloadInSameTime=Integer.parseInt(input.readLine());
            DataBase.setPathDifault(input.readLine());
//            System.out.println(lastLookAndFeel+"******"+numberOfDownloadInSameTime+"************"+DataBase.getPathDifault()+"***");
            input.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void controLookAndFeel(String text){
        switch (text){
            case"nimbus":{
                try {
                  System.out.println("اومد تو");
                    UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                } catch (InstantiationException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (UnsupportedLookAndFeelException e) {
                    e.printStackTrace();
                }
                break;
            }
            case"windows":{
                try {
                    UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                } catch (InstantiationException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (UnsupportedLookAndFeelException e) {
                    e.printStackTrace();
                }
                break;
            }
            case"metal":{
                try {
                    System.out.println("وارد متال شد");
                    UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                } catch (InstantiationException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (UnsupportedLookAndFeelException e) {
                    e.printStackTrace();
                }
                break;
            }
            //SwingUtilities.updateComponentTreeUI(jFrame);
//            SwingUtilities.updateComponentTreeUI(MyFrame.frame);

        }
    }
}
