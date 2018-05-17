import com.sun.deploy.panel.JavaPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;

public class SettingPage {
    JFrame jFrame = new JFrame("setting");
    JPanel surface = new JPanel();
    JFileChooser jFileChooser;
    public SettingPage() {
        jFrame.setMinimumSize(new Dimension(500, 500));
        jFrame.setContentPane(surface);
        jFrame.setLocationRelativeTo(null);
        jFrame.setResizable(false);
        JLabel photo = new JLabel(new ImageIcon("settingss.jpg"));
        surface.setLayout(new BorderLayout());
        JTextField numberDownload = new JTextField();
        jFileChooser = new JFileChooser();
        jFileChooser.setFileSelectionMode( JFileChooser.DIRECTORIES_ONLY);
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
        ((JButton)((JPanel)((JPanel)jFileChooser.getComponent(3)).getComponent(3)).getComponent(1)).setText("Accept");
        ((JButton)((JPanel)((JPanel)jFileChooser.getComponent(3)).getComponent(3)).getComponent(0)).setVisible(false);
        ((JButton)((JPanel)((JPanel)jFileChooser.getComponent(3)).getComponent(3)).getComponent(1)).addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jFrame.dispatchEvent(new WindowEvent(jFrame, WindowEvent.WINDOW_CLOSING));

            }
        });
        metal.addActionListener(new look());
        down.add(nimbus);
        down.add(windows);
        down.add(metal);
        surface.add(down, BorderLayout.SOUTH);
        jFrame.setVisible(true);
    }

    class look implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if(((JButton)e.getSource()).getText().equals("metal")){
                try {
                    UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
                    SwingUtilities.updateComponentTreeUI(jFrame);
                    SwingUtilities.updateComponentTreeUI(MyFrame.frame);
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
            else if(((JButton)e.getSource()).getText().equals("nimbus")){
                System.out.println("salam");
                try {
                    UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
                    SwingUtilities.updateComponentTreeUI(jFrame);
                    SwingUtilities.updateComponentTreeUI(MyFrame.frame);
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
            else if(((JButton)e.getSource()).getText().equals("windows")) {
                try {
                    UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
                    SwingUtilities.updateComponentTreeUI(jFrame);
                    SwingUtilities.updateComponentTreeUI(MyFrame.frame);
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
            try{
                ((JButton)((JPanel)((JPanel)jFileChooser.getComponent(3)).getComponent(3)).getComponent(1)).setText("Accept");
                ((JButton)((JPanel)((JPanel)jFileChooser.getComponent(3)).getComponent(3)).getComponent(0)).setText("back");
            }
            catch(Exception e1) {

            }


        }
    }
}
