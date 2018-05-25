import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.Calendar;

/**
 * shows the shape of downloads that add to program
 */

public class FormDownload implements Serializable {
    public static String selectedName = "";
    public static String buttenSelected = "";
    public JButton surface = new JButton();
    public Calendar time;
    public String address;
    public String setupLocation;
    public int size;
    public String fileName;
    protected JPanel up;
    public int speed;//به احتمال قوی بعدا ای را بصورت استاتیک از جای دیگری دریافت خواهیم کرد

    public FormDownload(String fileName, String address, String setupLocation, int size, int speed, int improve,Calendar time) {
        this.time=time;
        surface.addMouseListener(new controling());
        this.address = address;
        this.setupLocation = setupLocation;
        this.fileName = fileName;
        this.size = size;
        this.speed = speed;
        surface.setBackground(new Color(250, 50, 60));
        surface.setLayout(new BorderLayout(5, 5));
        up = new JPanel();
        up.setLayout(new GridLayout(1, 4));
        JLabel name = new JLabel("file name:" + fileName);
        JLabel sizee = new JLabel("size:" + size);
        JLabel speedd = new JLabel("speed:" + speed);
        up.add(name);
        up.add(sizee);
        up.add(speedd);
        JProgressBar jProgressBar = new JProgressBar();
        jProgressBar.setStringPainted(true);
        jProgressBar.setValue(improve * 100 / size);
        surface.add(up, BorderLayout.NORTH);
        //surface.addMouseListener(new controling());
        surface.add(jProgressBar, BorderLayout.CENTER);
    }

    public JButton getSurface() {
        return surface;
    }

    class controling implements MouseListener, Serializable {
        @Override
        public void mouseClicked(MouseEvent e) {
            if (e.getModifiers() == MouseEvent.BUTTON3_MASK) {
                System.out.println("You right clicked on the button");
                ///****************************************************************
                JFrame details = new JFrame();
                JPanel surface2 = new JPanel();
                details.setResizable(false);
                details.setLocationRelativeTo(MyFrame.processing);
                details.setMinimumSize(new Dimension(300, 200));
                details.setContentPane(surface2);
                surface2.setLayout(new GridLayout(5, 1));
                surface2.add(new JLabel("url:" + address));
                surface2.add(new JLabel("name:" + fileName));
                surface2.add(new JLabel("location setup:" + setupLocation));
                surface2.add(new JLabel("time start:" + time.get(Calendar.HOUR)+":"+time.get(Calendar.MINUTE)+":"+time.get(Calendar.SECOND)));
                surface2.add(new JLabel("size:" + size));
                details.setVisible(true);
                return;
            }
            if (selectedName.equals(fileName)) {
                try {
                    Desktop.getDesktop().open(new File(setupLocation));
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
            if (selectedName.equals("")) {
                ((JButton) e.getSource()).setBackground(new Color(255, 255, 2));
                selectedName = fileName;
                System.out.println("با باتن دانلود برخورد کرد");
                System.out.println(setupLocation);
            } else {
                if (buttenSelected.equals("queues")) {

                    DataBase.updateProcesing(1);
                    DataBase.updateQueue();
                } else {
                    System.out.println("در صف زده شد");
                    DataBase.updateQueue();
                    DataBase.updateProcesing(1);
                }
                ((JButton) e.getSource()).setBackground(new Color(255, 255, 2));
                selectedName = fileName;
                System.out.println("کلیک نوع دوم با موفقیت انحام شد");

            }

        }

        @Override
        public void mousePressed(MouseEvent e) {

        }

        @Override
        public void mouseReleased(MouseEvent e) {

        }

        @Override
        public void mouseEntered(MouseEvent e) {

        }

        @Override
        public void mouseExited(MouseEvent e) {

        }
    }

    public void painterToRed() {
        surface.setBackground(new Color(250, 50, 60));
    }
}
