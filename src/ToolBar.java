import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * this class is toolbar of the main fram
 */
public class ToolBar {
    JToolBar toolBar = new JToolBar();

    public ToolBar(JToolBar tool) {
        toolBar = tool;
        JButton start = new JButton();
        start.setIcon(new ImageIcon("start.jpg"));
        start.setToolTipText("start");
        JButton plus = new JButton();
        plus.setIcon(new ImageIcon("plus.JPG"));
        plus.setToolTipText("new queue");
        JButton pause = new JButton();
        pause.setIcon(new ImageIcon("pause.JPG"));
        pause.setToolTipText("pause");
        JButton remove = new JButton();
        remove.setIcon(new ImageIcon("remove.JPG"));
        remove.setToolTipText("remover");
        remove.addActionListener(new click());
        JButton seting = new JButton();
        seting.setIcon(new ImageIcon("setting.JPG"));
        seting.setToolTipText("setting");
        JButton newDownload = new JButton();
        newDownload.setIcon(new ImageIcon("new.jpg"));
        newDownload.setToolTipText("new");
        newDownload.addActionListener(new click());
        toolBar.add(newDownload);
        toolBar.add(plus);
        toolBar.add(start);
        toolBar.add(pause);
        toolBar.add(remove);
        toolBar.add(seting);
        seting.addActionListener(new click());
    }

    public JToolBar getToolBar() {
        return toolBar;
    }

    class click implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            String operator = ((JButton) e.getSource()).getToolTipText().toString();
            if (operator.equals("setting")) {
                //System.out.println("salam");
                SettingPage settingPage = new SettingPage();
            } else if (operator.equals("new")) {
                NewDownloadPage newDownloadPage = new NewDownloadPage();
            } else if (operator.equals("remover")) {
                if (FormDownload.selectedName.equals("")) {
                    System.out.println("*****************" +
                            "you dont choose" +
                            "*****************");
                } else {
                    new DataBase().remove(FormDownload.selectedName.toString());
                }

            }
        }
    }
}
