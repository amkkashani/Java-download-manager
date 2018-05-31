import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataOutputStream;

/**
 * this class is toolbar of the main fram
 */
public class ToolBar {
    JToolBar toolBar = new JToolBar();
    public static Thread threadQueue;

    public ToolBar(JToolBar tool) {
        toolBar = tool;
        JButton start = new JButton();
        start.setIcon(new ImageIcon("start.jpg"));
        start.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(FormDownload.buttenSelected.equals("processing")){
                for(FormDownload temp: DataBase.process){
                    if(temp.fileName.equals(FormDownload.selectedName)){
                        if(temp.isActivity()==2){
                            System.out.println("این قبلا دانلود شده");
                            return;
                        }
                        temp.downloadClient=new DownloadClient(temp);
                        temp.downloadClient.execute();
                    }
                }
                }else{
                    if(FormQueue.getActivityQueue()==0){
                        FormQueue.setActivityQueue(1);
                            for (int i = 1; i <= DataBase.queue.size(); i++) {
                                for (FormQueue formQueue : DataBase.queue) {
                                    if (formQueue.getArange() == i) {

                                        formQueue.downloadClientQueue = new DownloadClientQueue(DataBase.queue);
                                        formQueue.downloadClientQueue.execute();
                                    }
                                }
                            }

                    }
                    else{
                        Object[] options = {"ok"};
                        JOptionPane.showOptionDialog(null, "صف داره دانلود می کنه نمی توانید دوباره شروع کنید", "کیلیک اضافه", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.ERROR_MESSAGE, null, options, options[0]);
                        System.out.println("صف در حال دانلود است");
                    }
                }
            }
        });
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
