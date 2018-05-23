import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.IOException;

public class DownloadMenue {
    JMenu jMenu = new JMenu("Download");

    public DownloadMenue() {
        jMenu.setFont(new Font("fff", 50, 25));

        JMenuItem item1 = new JMenuItem("New download");
        item1.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F4, ActionEvent.ALT_MASK));
        item1.setMnemonic(KeyEvent.VK_N);
        item1.addActionListener(new functionDownload());

        JMenuItem item2 = new JMenuItem("Pause");
        item2.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P, ActionEvent.ALT_MASK));
        item2.setMnemonic(KeyEvent.VK_P);
        item2.addActionListener(new functionDownload());

        JMenuItem item3 = new JMenuItem("Resume");
        item3.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_R, ActionEvent.ALT_MASK));
        item3.setMnemonic(KeyEvent.VK_R);
        item3.addActionListener(new functionDownload());

        JMenuItem item4 = new JMenuItem("Cancle");
        item4.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, ActionEvent.ALT_MASK));
        item4.setMnemonic(KeyEvent.VK_C);
        item4.addActionListener(new functionDownload());

        JMenuItem item5 = new JMenuItem("Remove");
        item5.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_DELETE, ActionEvent.ALT_MASK));
        item5.setMnemonic(KeyEvent.VK_R);
        item5.addActionListener(new functionDownload());

        JMenuItem item6 = new JMenuItem("Setting");
        item6.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, ActionEvent.ALT_MASK));
        item6.setMnemonic(KeyEvent.VK_S);
        item6.addActionListener(new functionDownload());

        JMenuItem item7 = new JMenuItem("Exit");
        item7.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_E, ActionEvent.CTRL_MASK));
        item7.setMnemonic(KeyEvent.VK_E);
        item7.addActionListener(new functionDownload());

        JMenuItem item8=new JMenuItem("switch");
        item8.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S,ActionEvent.CTRL_MASK));
        item8.setMnemonic(KeyEvent.VK_S);
        item8.addActionListener(new functionDownload());

        jMenu.add(item1);
        jMenu.add(item2);
        jMenu.add(item3);
        jMenu.add(item4);
        jMenu.add(item5);
        jMenu.add(item6);
        jMenu.add(item8);
        jMenu.add(item7);

    }

    public JMenu getJMenu() {
        return jMenu;
    }

    class functionDownload implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            String operator= ((JMenuItem) e.getSource()).getText();
            if (operator .equals("Exit") ) {
                try {
                    DataBase.writeAvoidedSite();
                } catch (IOException e1) {
                    e1.printStackTrace();
                    System.out.println("داداش این فایله که باید برای ریختن سایت های محدود شده پیدا می کردم رو پیدا نکردم");
                }
                System.exit(0);
                //System.out.println("salam");
            } else if (operator.equals("New download")) {
                NewDownloadPage newDownloadPage = new NewDownloadPage();
            }else if(operator.equals("Remove")){
                if(FormDownload.selectedName.equals("")){
                    System.out.println("*****************" +
                            "you dont choose" +
                            "*****************");
                }
                else{
                    new DataBase().remove(FormDownload.selectedName.toString());
                }

            }
            else if(operator.equals("Setting")){
                SettingPage settingPage=new SettingPage();
            }
            else if(operator.equals("switch")){
                if(FormDownload.selectedName.equals("processing")||FormDownload.buttenSelected.equals("")){
                    System.out.println("این امکان برای گزینه انتخاب شده وجود ندارد");
                    return;
                }
                Replace replace=new Replace();

            }

        }
    }
}
