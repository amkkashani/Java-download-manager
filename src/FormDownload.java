import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.DataOutputStream;
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
    public JLabel sizefile = new JLabel("size");
    public JButton surface = new JButton();
    public Calendar time;
    public String address;
    public String setupLocation;
    public long size;
    public String fileName;
    public boolean mustDelet;
    protected JPanel up;
    JProgressBar jProgressBar;
    public DownloadClient downloadClient=null;
    private int activity=0;
    //0 بی کار
    //1 در حال دانلود
    // 2 تمام شده
    //3 پاز شده
    public int isActivity() {
        return activity;
    }

    public void setActivity(int activity) {
        this.activity = activity;
    }

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
        JLabel sizee =sizefile;
        JLabel speedd = new JLabel("speed:" + speed);
        up.add(name);
        up.add(sizee);
        up.add(speedd);
        jProgressBar = new JProgressBar();
        jProgressBar.setStringPainted(true);
        jProgressBar.setValue(improve * 100 / size);
        surface.add(up, BorderLayout.NORTH);
        //surface.addMouseListener(new controling());
        surface.add(jProgressBar, BorderLayout.CENTER);
        ///////////////////////////*******************************for testing
//        DownloadClient downloadClient = new DownloadClient(this);
//        downloadClient.execute();
    }

    public JButton getSurface() {
        return surface;
    }

    class controling implements MouseListener, Serializable {
        @Override
        public void mouseClicked(MouseEvent e) {
//            System.out.println("//////////////////"+buttenSelected+"///////////////////////");
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
                surface2.add(sizefile);
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
//                System.out.println(setupLocation);

            } else {
                selectedName = fileName;
                if (buttenSelected.equals("queues")) {

                    DataBase.updateProcesing(1);
                    DataBase.updateQueue();
//                    System.out.println("در صف زدی");
                } else {
//                    System.out.println("در پروسس زده شد");
                    DataBase.updateQueue();
                    DataBase.updateProcesing(1);
                }
//                System.out.println("کلیک نوع دوم با موفقیت انحام شد");

            }
//            DataBase.updateQueue();
//            DataBase.updateProcesing(1);
//            System.out.println("//////////////////"+buttenSelected+"///////////////////////");
            if(buttenSelected =="queues"){
                new Left().getQueues().doClick();
//                System.out.println(buttenSelected+"****************************************");
            }
            else{
//                System.out.println("*******************************");
                new Left().getProcessing().doClick();
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
    public void setjProgressBar(int value){
        jProgressBar.setValue(value);

    }
    public void setSizefile(long size){
        sizefile.setText("size"+size);
    }
}
