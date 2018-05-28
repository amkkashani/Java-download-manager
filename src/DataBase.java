import javax.print.DocFlavor;
import javax.xml.crypto.Data;
import java.awt.*;
import java.io.*;
import java.text.Normalizer;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;

/**
 * this class is the logic and functions that not related to gui
 */
public class DataBase {
    private static String pathDifault = "C:\\Users\\Lenovo\\Documents";
    public static ArrayList<FormDownload> deleted = new ArrayList<FormDownload>();
    public static ArrayList<FormDownload> process = new ArrayList<FormDownload>();
    public static ArrayList<FormQueue> queue = new ArrayList<FormQueue>();
    public static ArrayList<String> avoided = new ArrayList<String>();

    public DataBase(){
    }
    public static void sortName(){
        FormDownload temp;
        for(int i=1;i<process.size();i++){
            for(int j=0;j<process.size()-i;j++){
                if(process.get(j).fileName.compareTo(process.get(j+1).fileName)>0){
                    temp=process.get(j);
                    process.set(j,process.get(j+1));
                    process.set(j+1,temp);
                }
            }
        }
        updateProcesing(1);
    }
    public static void sortDate(){
        FormDownload temp;
        for(int i=1;i<process.size();i++){
            for(int j=0;j<process.size()-i;j++){
                if(process.get(j).time.compareTo(process.get(j+1).time)>0){
                    temp=process.get(j);
                    process.set(j,process.get(j+1));
                    process.set(j+1,temp);
                }
            }
        }
        updateProcesing(1);
    }
    public static void sortSize(){
        FormDownload temp;
        for(int i=1;i<process.size();i++){
            for(int j=0;j<process.size()-i;j++){
                if(process.get(j).size-process.get(j+1).size>0){
                    temp=process.get(j);
                    process.set(j,process.get(j+1));
                    process.set(j+1,temp);
                }
            }
        }
        updateProcesing(1);
    }

    public void add(FormDownload formDownload) {
        process.add(formDownload);
    }

    public void remove(String fileName) {
        int i = 0;
        Iterator<FormDownload> iter = process.iterator();
        FormDownload formDownload;
        while (iter.hasNext()) {
            formDownload = iter.next();
            if (formDownload.fileName.equals(FormDownload.selectedName)) {
                if (deleted.size() >= 15) {
                    deleted.remove(0);
                }
                deleted.add(formDownload);
                iter.remove();
                System.out.println("deleted");
                updateProcesing(1);
                if (process.size() >= 7) {
                    MyFrame.processing.setLayout(new GridLayout(process.size() - 1, 1));
                }
                return;
            }
        }
        if (FormDownload.buttenSelected.equals("processing")) {
            return;
        }
        Iterator<FormQueue> iterator = queue.iterator();
        while (iterator.hasNext()) {
            FormQueue formQueue = iterator.next();
            if (formQueue.fileName.equals(FormDownload.selectedName)) {
                iterator.remove();
                System.out.println("deleted");
                updateQueue();
                if (queue.size() >= 7) {
                    MyFrame.queues.setLayout(new GridLayout(queue.size() - 1, 1));
                }
                for (FormQueue another : queue) {
                    if (formQueue.getArange() < another.getArange()) {
                        another.setjTextField(another.getArange() - 1);
                    }
                }
                return;
            }
        }
        System.out.println("not found");
    }

    public static String getPathDifault() {
        return pathDifault;
    }

    public static void setPathDifault(String pathDifault) {
        DataBase.pathDifault = pathDifault;
    }

    //اکه 1 باشه توی proces خروجی می دهد
    public static void updateProcesing(int kind) {
//        ((FormDownload)MyFrame.processing.getComponent()).fileName;
        try {
            MyFrame.processing.removeAll();
        } catch (Exception e) {

        }
        for (FormDownload formDownload : process) {

//            formDownload.getSurface().setBackground(Color.yellow);
            if (FormDownload.selectedName.equals(formDownload.fileName)) {
                formDownload.painterToRed();
            }
            MyFrame.processing.add(formDownload.getSurface());
            System.out.println(formDownload.fileName);
            formDownload.getSurface().setVisible(true);

        }
        if (kind == 1) {
            MyFrame.processing.setVisible(true);
            new Left().getQueues().doClick();
            new Left().getProcessing().doClick();
        }

    }

    public static void updateQueue() {
        System.out.println("salam");
        try {
            MyFrame.queues.removeAll();
        } catch (Exception e) {

        }
        for (FormQueue formQueue : queue) {
            if (formQueue.fileName.equals(FormQueue.selectedName)) {
                formQueue.painterToRed();
            }
            MyFrame.queues.add(formQueue.getSurface());
            System.out.println(formQueue.fileName);
            formQueue.getSurface().setVisible(true);
        }
        new Left().getProcessing().doClick();
        new Left().getQueues().doClick();
    }

    public static void replace(int number) {
        Integer temp = 0;
        for (FormQueue formQueue : queue) {
            if (FormQueue.selectedName.equals(formQueue.fileName)) {
                temp = formQueue.getArange();
                for (FormQueue form : queue) {
                    if (form.getArange() == number) {
                        form.setjTextField(temp);
                    }
                }
                formQueue.setjTextField(number);
                return;

            }
        }
        System.out.println("یافت نشد");

    }

    public static void writeAvoidedSite() throws IOException {
        FileWriter out = new FileWriter(new File("data\\filter.jdm"));
        for (String matn : avoided) {
            out.write(matn);
            out.write("\n");
        }
        out.close();
    }

    public static void readAvoidedStite() {
        try {
            BufferedReader in = new BufferedReader(new FileReader("data\\filter.jdm"));
            String string = in.readLine();
            while (string != null) {
                if (!string.equals("")) {
                    avoided.add(string);
                    System.out.println(string);
                }
                string = in.readLine();
            }
        } catch (FileNotFoundException e) {
            System.out.println("داداش اون بخشی که باید از روش سایت های محدود شده رو می خوندم پیدا  نکردم!!!!!!!!");
        } catch (IOException e) {
            System.out.println("معضلی در خواندن فایل های مصدود شد وجود دارد");
            e.printStackTrace();
        }
    }

    public static boolean  removeAvoided(String temp) {
        for (String matn : avoided) {
            if (temp.equals(matn)) {
                avoided.remove(temp);
                System.out.println("پیداش کردم پاکش کردم");
                return true;
            }
        }
        return false;
    }

    //این تابه چک می کنند که میسر وارد شده آیا محدود شده است یا خیر که اگر محدود شده باشد true بر می گرداند
    public static Boolean checkAvoided(String temp) {
        for (String matn : avoided) {
            if (temp.contains(matn)) {
                return true;
            }
        }
        return false;
    }

    public static void outWriteProces() {
        try {
            //int i=0;
            FileOutputStream file = new FileOutputStream("data\\list.jdm");
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(file);
            for (FormDownload formDownload : process) {
                objectOutputStream.writeObject(formDownload);
            }
            //objectOutputStream.write(8888888);
            file.close();
            objectOutputStream.close();

        } catch (IOException e) {
            System.out.println("اون فایل که می خواستم توش procces رو بریزم نتونستم");
            e.printStackTrace();
        }
    }

    public static void inputReadProces() {
        try {
            FormDownload temp;
            FileInputStream file = new FileInputStream("data\\list.jdm");
            ObjectInputStream objectInputStream = new ObjectInputStream(file);
            temp = (FormDownload) objectInputStream.readObject();
            while (temp != null) {
                //System.out.println("*****************************************");
                process.add(temp);
                System.out.println(process.size());
                temp = (FormDownload) objectInputStream.readObject();

            }
            file.close();
            objectInputStream.close();
        } catch (FileNotFoundException e) {
            System.out.println("فایلی که پروسس رو ازش می خوندم پیدا نکردم");
//        } catch (IOException e) {
//            System.out.println("معضلی در خواندن کلاس های پروسس وجوددارد");
//        } catch (ClassNotFoundException e) {
//            System.out.println("ببین آدرس فایل پروسس پیدا شده فقط نمی تونم بخونمش !!!!!!");
//        }
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("معضلی در خواندن کلاس های پروسس وجوددارد");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            System.out.println("ببین آدرس فایل پروسس پیدا شده فقط نمی تونم بخونمش !!!");
        }
    }

    public static void writeOutQueu() {
        try {
            FileOutputStream fileOutputStream = new FileOutputStream("data\\list2.jdm");
            ObjectOutputStream out = new ObjectOutputStream(fileOutputStream);
            for (FormQueue formQueue : queue) {
                out.writeObject(formQueue);
            }
            out.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void readQueueFile() {
        FileInputStream file = null;
        try {
            FormQueue temp;
            file = new FileInputStream("data\\list2.jdm");
            ObjectInputStream in = new ObjectInputStream(file);
            temp = (FormQueue) in.readObject();
            while (temp != null) {
                queue.add(temp);
                temp = (FormQueue) in.readObject();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    public static void writeDeleted() {
        try {
            ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("data\\removed.jdm"));
            for (FormDownload temp : deleted) {
                out.writeObject(temp);
            }
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void readDeleted() {
        try {
            ObjectInputStream in = new ObjectInputStream(new FileInputStream("data\\removed.jdm"));
            FormDownload temp = (FormDownload) in.readObject();
            while (temp != null) {
                deleted.add(temp);
                temp = (FormDownload) in.readObject();
            }
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
