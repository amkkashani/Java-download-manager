import javafx.scene.input.DataFormat;

import javax.swing.*;
import javax.xml.crypto.Data;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;
import java.util.SimpleTimeZone;
import java.util.Calendar;

public class Main {

    public static void main(String[] args) {
        // غلط املاییا :)
        // Stite => Site
        // jfram => jframe
        // scrool =>  scroll
/*        Calendar cal = Calendar.getInstance();
        System.out.println(cal.get(Calendar.SECOND));
        Scanner scanner = new Scanner(System.in);
        int x= scanner.nextInt();
        Calendar cal2=Calendar.getInstance();
        System.out.println(cal2.get(Calendar.SECOND));
        System.out.println(cal2.compareTo(cal));
        x= scanner.nextInt();
*/
        DataBase.readQueueFile();
        DataBase.inputReadProces();
        DataBase.readDeleted();

        SettingPage.readCondition();
        SettingPage.controLookAndFeel(SettingPage.lastLookAndFeel);
        //System.out.println(SettingPage.lastLookAndFeel);
//        System.out.println(DataBase.process.get(0).fileName);
        DataBase.readAvoidedStite();
        FormDownload.selectedName="";
        DataBase.updateQueue();
        DataBase.updateProcesing(1);
        MyFrame myFrame = new MyFrame();
//        DataBase.sortName();
        JFrame jfram = new JFrame();
        System.out.println(DataBase.process.size());
        System.out.println(DataBase.queue.size());
    }
}
