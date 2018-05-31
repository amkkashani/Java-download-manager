import javax.net.ssl.HttpsURLConnection;
import javax.swing.*;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class DownloadClientQueue extends SwingWorker<Integer,Integer>  implements Serializable{
    private ArrayList<FormQueue> queues ;
    private File file;
    public DownloadClientQueue(ArrayList<FormQueue> queues){
        this.queues=queues;
    }

    @Override
    protected Integer doInBackground() throws Exception {
        System.out.println(queues.size()+"این سایز صفه");
        for(int i=1 ; i<=queues.size();i++) {
            System.out.println("****************************"+i+"********************");
            for(FormQueue formDownload:queues) {
                System.out.println(i + "***"+formDownload.getArange());
                if(formDownload.getArange()==i) {
                    if(formDownload.isActivity()==2){
                        continue;
                    }
                    formDownload.setActivity(1);
                    formDownload.setupLocation=formDownload.setupLocation+"//"+formDownload.fileName;
                    file=new File(formDownload.setupLocation);
                    URL url = new URL(formDownload.address);
                    HttpURLConnection httpURLConnection;
                    if (url.getProtocol().equals("http")) {
                        httpURLConnection = (HttpURLConnection) url.openConnection();
                    } else if (url.getProtocol().equals("https")) {
                        httpURLConnection = (HttpsURLConnection) url.openConnection();
                    } else {
                        System.out.println("این دیگه چیه");
                        return null;
                    }
                    int size = httpURLConnection.getContentLength();
                    httpURLConnection.connect();
                    BufferedInputStream bufferedInputStream = new BufferedInputStream(url.openStream());
                    BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(file));
                    byte[] buffer = new byte[2048];
                    int read = 0;
                    int isReaded = 0;
                    formDownload.size = size;
                    do {
                        read = bufferedInputStream.read(buffer, 0, 2048);
                        bufferedOutputStream.write(buffer, 0, read);
                        isReaded += read;
                        System.out.println(isReaded * 100 / size);
                        System.out.println(size);
                        System.out.println(isReaded);
                        formDownload.jProgressBar.setValue(isReaded * 100 / size);


//                        System.out.println(isReaded + "........................");
//                        System.out.println(size);
//                        System.out.println("................................");
//                        System.out.println(size+"isredaded"+isReaded);
                    }
                    while (isReaded!=size);

                }
            }
        }
        FormQueue.setActivityQueue(0);
        return null;
    }
}
