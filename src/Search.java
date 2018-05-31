import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class Search {
    /**
     * this class just for search in download items
     */
    private JFrame frame = new JFrame();
    private JPanel surface = new JPanel();
    private JPanel up = new JPanel();
    private JTextArea center = new JTextArea();
    private JTextField textField;
    int lastSearchNumber =0;

    public Search() {
        frame.setContentPane(surface);
        frame.setLocation(500, 200);
        surface.setLayout(new BorderLayout());
        frame.setResizable(false);
        frame.setSize(300, 75);
        //این بخش طراحی قسمت search را انجام می دهد
        up.setLayout(new BorderLayout());
        textField = new JTextField("enter your name or url");
        JButton label = new JButton(new ImageIcon("search.png"));
        label.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                searching(textField.getText());
            }
        });
        up.add(label, BorderLayout.EAST);
        up.add(textField, BorderLayout.CENTER);
        surface.add(up, BorderLayout.NORTH);
//        center.setText("adfasdfasdfasdfa");
        surface.add(center, BorderLayout.CENTER);
        frame.setVisible(true);

    }

    private void searching(String text) {
        ArrayList<FormDownload> temp = DataBase.process;
        center.setText("");
        frame.setSize(frame.getWidth(),76);
        lastSearchNumber=0;
// center.removeAll();
//        center.setLayout(new GridLayout(center.getHeight() + 1, 1));
        for (FormDownload formDownload : temp) {
            if (formDownload.fileName.contains(text) || formDownload.address.equals(text)) {
//                JTextField textLeft = new JTextField(formDownload.fileName);
//                JTextField textRith = new JTextField(formDownload.address);
//                center.setLayout(new GridLayout(center.getHeight() + 1, 1));
                center.setText(center.getText()+"name:"+formDownload.fileName+"     "+"url:"+formDownload.address+"\n");
                lastSearchNumber ++;
                frame.setSize(300, frame.getHeight() + 15);

            }
        }
        for (FormDownload formDownload : DataBase.queue) {
            if (formDownload.fileName.contains(text) || formDownload.address.equals(text)) {
                center.setText(center.getText()+"name:"+formDownload.fileName+"     "+"url:"+formDownload.address+"\n");
//                JButton find = new JButton();
//                find.setLayout(new GridLayout(1,2));
//                JTextArea textLeft = new JTextArea(formDownload.fileName);
//                JTextArea textRith = new JTextArea(formDownload.address);
//                find.add(textLeft);
//                find.add(textRith);
//                center.setLayout( GridLayout(center.getHeight() + 1, 1));
//                center.add(find);
                frame.setSize(300, frame.getHeight() + 15);
                lastSearchNumber ++;

            }
//            center.setLayout(new GridLayout(center.getHeight() + 1, 1));
//            surface.add(center, BorderLayout.CENTER);
//            frame.pack();

        }
    }
}
