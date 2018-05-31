import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Left {
    private  JPanel mainPanel ;
    private   JButton processing;
    private   JButton queues;

    public JButton getProcessing() {
        return processing;
    }

    public void setProcessing(JButton processing) {
        this.processing = processing;
    }

    public JButton getQueues() {
        return queues;
    }

    public void setQueues(JButton queues) {
        this.queues = queues;
    }

    public Left(){
       // UIManager.setLookAndFeel();
        JPanel panelup = new JPanel();
        JPanel choices = new JPanel();
        choices.setLayout(new GridLayout(4,1));
        panelup.setLayout(new BorderLayout());
        mainPanel=new JPanel();
        mainPanel.setLayout( new BorderLayout());
        ImageIcon lion = new ImageIcon("KDM.jpg");
        JLabel lionlabel = new JLabel (lion);
        panelup.add(lionlabel , BorderLayout . NORTH);
        mainPanel.add(panelup,BorderLayout.NORTH);
        mainPanel.setBackground(new Color(250,200,100));
        mainPanel.setPreferredSize(new Dimension(180,200));


        processing = new JButton("processing");
        processing.setBackground(Color.GRAY);
        processing.addActionListener(new page());
        processing.setBorderPainted(false);


        JButton completed = new JButton("completed");
        completed.setBackground(Color.orange);
        completed.addActionListener(new page());
        completed.setBorderPainted(false);

        queues = new JButton("queues");
        queues.setBackground(Color.lightGray);
        queues.addActionListener(new page());
        queues.setBorderPainted(false);


        JButton defult = new JButton("default");
        defult.setBackground(Color.RED);
        defult.addActionListener(new page());
        defult.setBorderPainted(false);


        panelup.add(choices,BorderLayout.SOUTH);

        choices.add(processing);
        choices.add(completed);
        choices.add(queues);
        choices.add(defult);

    }

    public JPanel getPanel() {
        return mainPanel;
    }
    class page implements ActionListener
    {

        @Override
        public void actionPerformed(ActionEvent e) {
            if(((JButton)e.getSource()).getText().equals("queues")){

//                MyFrame.defult.setVisible(false);
//                MyFrame.queues.setVisible(false);
//                MyFrame.completed.setVisible(false);
                MyFrame.proccessingScroll.setVisible(false);
                MyFrame.center.add(MyFrame.queues, BorderLayout.CENTER);
                MyFrame.queues.setVisible(true);
                FormDownload.buttenSelected="queues";

            }

             else if(((JButton)e.getSource()).getText().equals("processing")){

//                MyFrame.defult.setVisible(false);
//                MyFrame.queues.setVisible(false);
//                MyFrame.completed.setVisible(false);
                MyFrame.queues.setVisible(false);
                MyFrame.center.add(MyFrame.proccessingScroll, BorderLayout.CENTER);
                MyFrame.proccessingScroll.setVisible(true);
                FormDownload.buttenSelected="processing";
//                MyFrame.proccessingScroll.setVisible(true);

            }
        }
    }
}
