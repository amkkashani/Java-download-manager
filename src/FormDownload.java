import javax.swing.*;
import java.awt.*;

public class FormDownload {
    JButton surface=new JButton();
    int size;
    String fileName;
    int speed;//به احتمال قوی بعدا ای را بصورت استاتیک از جای دیگری دریافت خواهیم کرد
    public FormDownload(String fileName,int size,int speed,int improve){
        this.fileName=fileName;
        this.size=size;
        this.speed=speed;
        surface.setBackground(new Color(250,50,60));
        surface.setLayout(new BorderLayout(5,5));
        JPanel up =new JPanel();
        up.setLayout(new GridLayout(1,3));
        JLabel name=new JLabel("file name:"+fileName);
        JLabel sizee=new JLabel("size:"+size);
        JLabel speedd=new JLabel("speed:"+speed);
        up.add(name);
        up.add(sizee);
        up.add(speedd);
        JProgressBar jProgressBar=new JProgressBar();
        jProgressBar.setStringPainted(true);
        jProgressBar.setValue(30);
        surface.add(up,BorderLayout.NORTH);
        surface.add(jProgressBar,BorderLayout.CENTER);
    }

    public JButton getSurface() {
        return surface;
    }
}
