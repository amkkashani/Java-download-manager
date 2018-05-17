import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class FormDownload {
    static String selectedName="";
    public JButton surface = new JButton();
    public String address;
    public String setupLocation;
    public int size;
    public String fileName;
    public int speed;//به احتمال قوی بعدا ای را بصورت استاتیک از جای دیگری دریافت خواهیم کرد

    public FormDownload(String fileName,String address,String setupLocation, int size, int speed, int improve) {
        surface.addMouseListener(new controling());
        this.address=address;
        this.setupLocation=setupLocation;
        this.fileName = fileName;
        this.size = size;
        this.speed = speed;
        surface.setBackground(new Color(250, 50, 60));
        surface.setLayout(new BorderLayout(5, 5));
        JPanel up = new JPanel();
        up.setLayout(new GridLayout(1, 3));
        JLabel name = new JLabel("file name:" + fileName);
        JLabel sizee = new JLabel("size:" + size);
        JLabel speedd = new JLabel("speed:" + speed);
        up.add(name);
        up.add(sizee);
        up.add(speedd);
        JProgressBar jProgressBar = new JProgressBar();
        jProgressBar.setStringPainted(true);
        jProgressBar.setValue(improve/size);
        surface.add(up, BorderLayout.NORTH);
        surface.add(jProgressBar, BorderLayout.CENTER);
    }

    public JButton getSurface() {
        return surface;
    }
    class controling implements MouseListener{

        @Override
        public void mouseClicked(MouseEvent e) {
            if(selectedName.equals("")){
                ((JButton)e.getSource()).setBackground(new Color(255,255,2));
                selectedName=fileName;
                System.out.println("با باتن دانلود برخورد کرد");
                System.out.println(setupLocation);
            }
            else {
                DataBase.updateProcesing(1);
                ((JButton)e.getSource()).setBackground(new Color(255,255,2));
                selectedName=fileName;
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
    public void painterToRed(){
        surface.setBackground(new Color(250, 50, 60));
    }
}
