import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataOutputStream;

/**
 * this class is toolbar of the main fraim
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
                if (FormDownload.buttenSelected.equals("processing")) {
                    for (FormDownload temp : DataBase.process) {
                        if (temp.fileName.equals(FormDownload.selectedName)) {
                            if (temp.isActivity() == 2) {
                                System.out.println("این قبلا دانلود شده");
                                return;
                            } else if (temp.isActivity() == 0) {
                                if (DownloadClient.numberDownloadNow < SettingPage.numberOfDownloadInSameTime) {
                                    temp.downloadClient = new DownloadClient(temp);
                                    temp.jProgressBar.setValue(0);
                                    temp.downloadClient.execute();
                                    DownloadClient.numberDownloadNow++;
                                } else {
                                    DownloadClient.addToqueue(temp);
                                }
                            } else if (temp.isActivity() == 3) {
                                temp.setActivity(1);
                            }
                        }
                    }
                } else {
                    if (FormQueue.getActivityQueue() == 0) {
                        FormQueue.setActivityQueue(1);
                        FormQueue.downloadClientQueue = new DownloadClientQueue(DataBase.queue);
                        FormQueue.downloadClientQueue.execute();


                    } else if (FormQueue.getActivityQueue() == 3) {
                        FormQueue.setActivityQueue(1);
                    } else {
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
        pause.addActionListener(new click());
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
        JButton cancel = new JButton();
        cancel.setIcon(new ImageIcon("refresh.png"));
        cancel.addActionListener(new click());
        cancel.setToolTipText("cancel");
        toolBar.add(newDownload);
        toolBar.add(plus);
        toolBar.add(start);
        toolBar.add(pause);
        toolBar.add(cancel);
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

            }else if(operator.equals("pause")){
                if(FormDownload.buttenSelected.equals(("processing"))){
                    for(FormDownload formDownload:DataBase.process){
                        if(formDownload.fileName.equals(FormDownload.selectedName)){
                            formDownload.setActivity(3);
                            return;
                        }
                    }
                }
                else{
                    FormQueue.setActivityQueue(3);
//                    System.out.println("این روتغییر داد");

                }
            }
            else if(operator.equals("cancel")){
                System.out.println("salam");
                if(FormDownload.buttenSelected.equals("processing")){
                    for(FormDownload formDownload:DataBase.process){
                        if(formDownload.fileName.equals(FormDownload.selectedName)){
                            formDownload.mustDelet=true;
                            formDownload.jProgressBar.setValue(0);
                        }
                    }
                }else{
                    for(FormQueue formQueue:DataBase.queue){
                        if(formQueue.fileName.equals(FormDownload.selectedName)){
                            formQueue.mustDelet=true;
                            formQueue.setjProgressBar(0);
                        }
                    }

                }
            }
        }
    }
}
