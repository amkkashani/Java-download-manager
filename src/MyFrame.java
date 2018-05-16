import javax.swing.*;
import javax.swing.text.StyledEditorKit;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

public class MyFrame {

    public static JPanel processing = new JPanel();
//    public static JPanel completed = new JPanel();
    public static JPanel queues = new JPanel();
    //public static JPanel defult = new JPanel();
    public static JPanel center = new JPanel();
    public static JFrame frame = new JFrame("KDM");

    public MyFrame() {
        setChoicesColor();
        JPanel surface = new JPanel();
        frame.setMinimumSize(new Dimension(1100, 800));
        frame.setLocationRelativeTo(null);
        //*** این بخش به طور کلی برای hide شدن پنجره است
        SystemTray systemTray = SystemTray.getSystemTray();
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Image image = toolkit.getImage("KDM.jpg");
        PopupMenu popupMenu = new PopupMenu();
        TrayIcon icon = new TrayIcon(image, "JDM Program", popupMenu);
        MenuItem openItem = new MenuItem("Open JDM");
        openItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.setVisible(true);
            }
        });
        popupMenu.add(openItem);

        MenuItem closeItem = new MenuItem("Close JDM");
        closeItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SystemTray.getSystemTray().remove(icon);
                System.exit(0);
            }
        });
        popupMenu.add(closeItem);
        icon.setImageAutoSize(true);
        try {
            systemTray.add(icon);
        } catch (AWTException e) {
            e.printStackTrace();
        }
        //****
        ImageIcon imageIcon = new ImageIcon("KDM.jpg");
        frame.setIconImage(imageIcon.getImage());
        surface.setLayout(new BorderLayout(0, 0));
        JPanel top = new JPanel(new BorderLayout());
        ImageIcon logo = new ImageIcon("lion2.jpg");
        JLabel lion = new JLabel(logo);
        lion.setPreferredSize(new Dimension(200, 200));
        JMenuBar menuBar = new JMenuBar();
        JToolBar toolBar = new JToolBar("toolbar");
        toolBar = new ToolBar(toolBar).getToolBar();
        toolBar.setRollover(true);


        menuBar.add(new DownloadMenue().jMenu);
        menuBar.add(new Help().jMenu);


        surface.add(new Left().getPanel(), BorderLayout.WEST);
        top.add(menuBar, BorderLayout.NORTH);
        top.add(toolBar, BorderLayout.CENTER);

        center.setLayout(new BorderLayout(0, 0));
        center.add(top, BorderLayout.NORTH);
        setChoicesColor();
        surface.add(center, BorderLayout.CENTER);
        surface.add(lion, BorderLayout.SOUTH);

        frame.setContentPane(surface);
        frame.pack();
        frame.setVisible(true);
    }

    private void setChoicesColor() {
        processing.setBackground(Color.gray);
        //completed.setBackground(Color.GRAY);
        queues.setBackground(Color.lightGray);
//        defult.setBackground(Color.RED);
//        MyFrame.center.add(MyFrame.completed, BorderLayout.CENTER);
        MyFrame.center.add(MyFrame.processing, BorderLayout.CENTER);
//        MyFrame.center.add(MyFrame.defult, BorderLayout.CENTER);
//        MyFrame.center.add(MyFrame.queues, BorderLayout.CENTER);
    }

}
