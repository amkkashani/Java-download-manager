import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ToolBar {
    JToolBar toolBar = new JToolBar();

    public ToolBar(JToolBar tool) {
        toolBar = tool;
        JButton start = new JButton();
        start.setIcon(new ImageIcon("start.jpg"));
        start.setToolTipText("start");
        JButton plus = new JButton();
        plus.setIcon(new ImageIcon("plus.JPG"));
        plus.setToolTipText("add new task or new download");
        JButton pause = new JButton();
        pause.setIcon(new ImageIcon("pause.JPG"));
        pause.setToolTipText("pause");
        JButton remove = new JButton();
        remove.setIcon(new ImageIcon("remove.JPG"));
        remove.setToolTipText("remover");
        JButton seting = new JButton();
        seting.setIcon(new ImageIcon("setting.JPG"));
        seting.setToolTipText("setting");
        JButton newDownload = new JButton();
        newDownload.setIcon(new ImageIcon("new.jpg"));
        newDownload.setToolTipText("new");
        toolBar.add(newDownload);
        toolBar.add(plus);
        toolBar.add(start);
        toolBar.add(remove);
        toolBar.add(pause);
        toolBar.add(seting);
        seting.addActionListener(new click());
    }

    public JToolBar getToolBar() {
        return toolBar;
    }

    class click implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (((JButton) e.getSource()).getToolTipText().equals("setting")) {
                System.out.println("salam");
                SettingPage settingPage = new SettingPage();
            }
        }
    }
}
