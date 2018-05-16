import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

public class Help {
    JMenu jMenu=new JMenu("Help");
    public Help(){
        JMenuItem item=new JMenuItem("about");
        item.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_H , ActionEvent.CTRL_MASK));
        item.setMnemonic(KeyEvent.VK_A);
        item.addActionListener(new clic());
        jMenu.setFont(new Font("fff",50,25));
        jMenu.add(item);
    }

    public JMenu getjMenu() {
        return jMenu;
    }
}
 class clic implements ActionListener{

     @Override
     public void actionPerformed(ActionEvent e) {
         JFrame jFrame=new JFrame("help");
         jFrame.setLocation(300,300);
         jFrame.setMinimumSize(new Dimension(1200, 750));
        // jFrame.setPreferredSize(new Dimension(300,300));
         JPanel jPanel=new JPanel();
         jPanel.setPreferredSize(new Dimension(300,300));

         jPanel.setLayout(new BorderLayout());
         JLabel jLabel=new JLabel();
         jLabel.setIcon(new ImageIcon("help.JPG"));
         jPanel.add(jLabel,BorderLayout.CENTER);
         jFrame.setContentPane(jPanel);
         jFrame.setResizable(false);
         jFrame.setVisible(true);

     }
 }
