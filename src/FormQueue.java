import javax.swing.*;
import java.awt.*;

public class FormQueue extends FormDownload {
    private int arange;
    private JTextField jTextField;
    public FormQueue(String fileName, String address, String setupLocation, int size, int speed, int improve,int order) {
        super(fileName, address, setupLocation, size, speed, improve);
        this.arange=order;
        JPanel upRight= new JPanel();
        JLabel jLabel=new JLabel("order");
        jTextField=new JTextField(""+arange);
        upRight.setLayout(new GridLayout());
        jTextField.setEditable(false);
        upRight.add(jLabel,BorderLayout.WEST);
        upRight.add(jTextField,BorderLayout.CENTER);
        up.add(upRight);
    }
    public void setjTextField(Integer number){
        jTextField.setText(number.toString());
        arange=number;
    }
    public int getArange(){
        return arange;
    }

}
