import javax.net.ssl.HttpsURLConnection;
import javax.swing.*;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;


public class DownloadClient extends SwingWorker<Integer, Integer> implements Serializable {
    private static ArrayList<FormDownload> saf = new ArrayList<>();
    public static int numberDownloadNow = 0;
    FormDownload formDownload;
    File file;
    private int lastTime;
    private int now;
    private int speed;

    public static void addToqueue(FormDownload formQueue) {
        saf.add(formQueue);
    }

    public DownloadClient(FormDownload formDownload) {
        this.formDownload = formDownload;
        formDownload.setupLocation = formDownload.setupLocation + "\\" + formDownload.fileName;
        file = new File(formDownload.setupLocation);
    }

    @Override
    protected Integer doInBackground() throws Exception {
        URL url = null;
        try {
            url = new URL(formDownload.address);
        } catch (Exception e) {
            System.out.println("دادش اینا چیه داری ورودی می دی");
            return null;
        }
        HttpURLConnection httpURLConnection;
        if (url.getProtocol().equals("http")) {
            httpURLConnection = (HttpURLConnection) url.openConnection();
        } else if (url.getProtocol().equals("https")) {
            httpURLConnection = (HttpsURLConnection) url.openConnection();
        } else {
            System.out.println("این دیگه چیه!!!");
            return null;
        }
        formDownload.setActivity(1);
        long size = httpURLConnection.getContentLength();
        httpURLConnection.connect();
        formDownload.setSizefile(size);
        BufferedInputStream bufferedInputStream = new BufferedInputStream(url.openStream());
        BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(file));
        byte[] buffer = new byte[2048];
        int read = 0;
        long isReaded = 0;
        formDownload.size = size;
        do {
            if (formDownload.isActivity() == 3) {
                byte[] fake = new byte[2];
                read = bufferedInputStream.read(fake, 0, 2);
                bufferedOutputStream.write(fake, 0, read);
                isReaded += read;
                Thread.sleep(1001);
                if (isReaded == size) {
                    break;
                }
            }
            if(formDownload.mustDelet==true){
                file.delete();
                formDownload.mustDelet=false;
                formDownload.setActivity(0);
                return null;
            }
            read = bufferedInputStream.read(buffer, 0, 2048);
            bufferedOutputStream.write(buffer, 0, read);
            isReaded += read;
            System.out.println(isReaded +"/"+size);
            System.out.println(isReaded*100/size);
            formDownload.jProgressBar.setValue((int) (isReaded * 100 / size));
        }
        while (isReaded != size);
        bufferedOutputStream.close();
        saf.remove(formDownload);
        numberDownloadNow--;
        if (saf.size() != 0) {
            saf.get(0).downloadClient = new DownloadClient(saf.get(0));
            saf.get(0).downloadClient.execute();
            numberDownloadNow++;
        }
        return null;
    }

    @Override
    protected void process(List<Integer> chunks) {
        super.process(chunks);
    }
}
