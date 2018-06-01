import javax.net.ssl.HttpsURLConnection;
import javax.swing.*;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class DownloadClientQueue extends SwingWorker<Integer, Integer> implements Serializable {
    private ArrayList<FormQueue> queues;
    private File file;

    public DownloadClientQueue(ArrayList<FormQueue> queues) {
        this.queues = queues;
    }

    @Override
    protected Integer doInBackground() throws Exception {

//        System.out.println(queues.size()+"این سایز صفه");
        for (int i = 1; i <= queues.size(); i++) {
//            System.out.println("////////////iiiiiiiiiii**************"+i);
//            System.out.println("****************************"+i+"********************");
            for (FormQueue formDownload : queues) {
//                System.out.println(i + "***" + formDownload.getArange());
                if (formDownload.getArange() == i) {
                    if (formDownload.isActivity() == 2 ||formDownload.jProgressBar.getValue()==100) {
//                        System.out.println("اومد توی کانتینیو");
                        continue;
                    }
                    formDownload.setActivity(1);
//                    System.out.println(formDownload.setupLocation+"قبلش");
                    formDownload.setupLocation = formDownload.setupLocation + "\\" + formDownload.fileName;
//                    System.out.println(formDownload.setupLocation+"بعدش");
                    file = new File(formDownload.setupLocation);
                    URL url=null;
                    try {
                        url = new URL(formDownload.address);
                    }
                    catch (Exception e){
                        System.out.println("دادش اینا چیه داری ورودی می دی");
                        continue;
                    }
                    HttpURLConnection httpURLConnection;
                    if (url.getProtocol().equals("http")) {
                        httpURLConnection = (HttpURLConnection) url.openConnection();
                    } else if (url.getProtocol().equals("https")) {
                        httpURLConnection = (HttpsURLConnection) url.openConnection();
                    } else {
                        System.out.println("این دیگه چیه");
                        return null;
                    }
                    httpURLConnection.setConnectTimeout(2000000);
                    long size = httpURLConnection.getContentLength();
                    formDownload.setSizefile(size);
                    httpURLConnection.connect();
                    BufferedInputStream bufferedInputStream = new BufferedInputStream(url.openStream());
                    BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(file));
                    byte[] buffer = new byte[2048];
                    int read = 0;
                    int isReaded = 0;
                    formDownload.size = size;
                    do {
                        if(FormQueue.getActivityQueue()==3){
                            while(FormQueue.getActivityQueue()==3){
                                byte []fake = new byte[2];
                                read = bufferedInputStream.read(fake,0,2);
                                bufferedOutputStream.write(fake,0,read);
                                isReaded+=read;
                                Thread.sleep(1000);
                                if(isReaded==size){
                                    break;
                                }
                            }
                        }
                        if(formDownload.mustDelet==true){
                            file.delete();
                            formDownload.mustDelet=false;
                            formDownload.setActivity(0);
                            continue;
                        }
                        read = bufferedInputStream.read(buffer, 0, 2048);
                        bufferedOutputStream.write(buffer, 0, read);
                        isReaded += read;
                        if(isReaded * 100 / size!=0) {
                            formDownload.jProgressBar.setValue((int) (isReaded * 100 / size));
                        }

//                        System.out.println(isReaded + "........................");
//                        System.out.println(size);
//                        System.out.println("................................");
//                        System.out.println(size+"isredaded"+isReaded);
                    }
                    while (isReaded != size);
                    bufferedOutputStream.flush();
                    bufferedOutputStream.close();
                    formDownload.setActivity(2);
                }
            }
        }
        FormQueue.setActivityQueue(0);
        return null;
    }
}
