import javax.swing.*;
import java.awt.*;

/**
 * this class handle deleted download from gui
 */
public class Deleted extends DataBase {
    public Deleted() {
        JFrame jFrame = new JFrame();
        jFrame.setLocation(50, 50);

        JPanel surface = new JPanel();

        jFrame.setSize(new Dimension(500, 700));
        jFrame.setContentPane(surface);
        surface.setLayout(new GridLayout(15, 1));
        for (FormDownload temp : deleted) {
            JButton jButton = new JButton("name:" + temp.fileName + "       url:" + temp.address);
            surface.add(jButton);
        }
        jFrame.setVisible(true);
    }
}
