import com.sun.org.apache.bcel.internal.generic.INSTANCEOF;
import sun.security.jca.GetInstance;

import javax.net.ssl.HttpsURLConnection;
import javax.swing.*;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.Timer;

public class DownloadClient extends SwingWorker<Integer, Integer> implements Serializable{
    FormDownload formDownload;
    File file;
    public DownloadClient(FormDownload formDownload) {
        this.formDownload = formDownload;
        formDownload.setupLocation=formDownload.setupLocation+"//"+formDownload.fileName;
        file=new File(formDownload.setupLocation);
    }

    @Override
    protected Integer doInBackground() throws Exception {

//        URL url = new URL(formDownload.address);
//        HttpURLConnection httpURLConnection;
//        if (url.getProtocol().equals("http")) {
//            httpURLConnection = (HttpURLConnection) url.openConnection();
//        } else if (url.getProtocol().equals("https")) {
//            httpURLConnection = (HttpsURLConnection) url.openConnection();
//        } else {
//            System.out.println("این دیگه چیه");
//            return null;
//        }
//        int size = httpURLConnection.getContentLength();
//        httpURLConnection.connect();
//        BufferedInputStream bufferedInputStream = new BufferedInputStream(url.openStream());
//        BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(file));
//        byte []buffer = new byte[2048];
//        int read = 0;
//        int isReaded=0;
//        formDownload.size=size;
//        do{
//
//
//            read = bufferedInputStream.read(buffer,0,2048);
//            bufferedOutputStream.write(buffer,0,read);
//            isReaded += read;
//            formDownload.jProgressBar.setValue(isReaded*100/size);
//
//            System.out.println(isReaded + "........................");
//            System.out.println(size);
//            System.out.println("................................");
//        }
//        while (read != -1);



        //////////////////////////////////////////
        for(double i=0;i<=100.2;i= i+0.0000001){
            formDownload.jProgressBar.setValue((int) i);
        }
        formDownload.setActivity(2);
        try {
            FormQueue formQueue= (FormQueue)formDownload;
            ToolBar.threadQueue.start();
            System.out.println("ستارت زدم تو صف که بعدی شروع کنه");

        }
        catch (Exception e){

        }
        return null;
    }

    @Override
    protected void process(List<Integer> chunks) {
        super.process(chunks);
    }
}
