import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;

/**
 * this class just for avoid sites for download
 */

public class AdvanceSetting {
    JFrame jFrame = new JFrame();
    JPanel surface = new JPanel();

    public AdvanceSetting() {
        jFrame.setContentPane(surface);
        surface.setLayout(new GridLayout(1, 1));
        JPanel first = new JPanel();
//        jFrame.setSize(500, 700);
        jFrame.setLocation(710, 300);
        first.setLayout(new BorderLayout());
        JTextField site = new JTextField();
        JButton adding = new JButton("sites must avoid");
        JButton remove = new JButton("remove");
        //مکانی برای نوشتن سایت های ممنوعه
        JTextArea jTextArea = new JTextArea();
        jFrame.setSize(500, 150 + DataBase.avoided.size() * 18);
        jTextArea.setText(viewOfTextArea());
        remove.setBackground(new Color(233, 230, 33));
        remove.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (DataBase.removeAvoided(site.getText())) {
                    jTextArea.setText(viewOfTextArea());
                    jFrame.setSize(500, jFrame.getHeight() - 15);
                }

            }
        });
        first.add(jTextArea, BorderLayout.NORTH);
        first.add(adding, BorderLayout.WEST);
        first.add(remove, BorderLayout.SOUTH);
        first.add(site, BorderLayout.CENTER);
        adding.setBackground(new Color(135, 130, 235));
        surface.add(first);
        adding.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (site.getText().equals("")) {
                    System.out.println("داداش خالی داری میزنی");
                } else {
                    DataBase.avoided.add(site.getText());
                    System.out.println("با موقیت انجام شد");
                    jTextArea.setText(jTextArea.getText() + site.getText() + "\n");
                    jFrame.setSize(500, jFrame.getHeight() + 15);
                }
            }
        });
        jFrame.setVisible(true);
    }

    private String viewOfTextArea() {
        String avoided = "";
        ArrayList<String> temp = DataBase.avoided;
        for (String text : temp) {
            avoided += text;
            avoided += "\n";
        }
        return avoided;

    }
}
