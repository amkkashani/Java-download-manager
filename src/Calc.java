import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class Calc {
    private JFrame frame = new JFrame();
    public static JTextField textField;
    public char sign = '0';
    public int first = 0;
    public int second = 0;

    public Calc() {

        JPanel panel = new JPanel();
        JPanel head = new JPanel();
        JPanel under = new JPanel();
        frame.setContentPane(panel);
        frame.setLocation(500, 500);
        frame.setSize(500, 500);
        panel.setLayout(new BorderLayout());
        panel.add(under, BorderLayout.SOUTH);
        panel.add(head, BorderLayout.CENTER);
        under.setLayout(new GridLayout(4, 4, 10, 10));

        JButton b1 = new JButton();
        b1.setPreferredSize(new Dimension(20, 20));
        b1.setText("1");
        under.add(b1);
        b1.addActionListener(new OnButton());

        JButton b2 = new JButton();
        b2.setText("2");
        under.add(b2);
        b2.addActionListener(new OnButton());

        JButton b3 = new JButton();
        b3.setText("3");
        under.add(b3);
        b3.addActionListener(new OnButton());

        JButton star = new JButton();
        star.setText("*");
        under.add(star);
        //star.addActionListener( new OnButton());
        star.addMouseListener(new Mosing());


        JButton b4 = new JButton();
        b4.setText("4");
        under.add(b4);
        b4.addActionListener(new OnButton());

        JButton b5 = new JButton();
        b5.setText("5");
        under.add(b5);
        b5.addActionListener(new OnButton());

        JButton b6 = new JButton();
        b6.setText("6");
        under.add(b6);
        b6.addActionListener(new OnButton());

        JButton slash = new JButton();
        slash.setText("/");
        under.add(slash);

        JButton b7 = new JButton();
        b7.setText("7");
        under.add(b7);
        b7.addActionListener(new OnButton());

        JButton b8 = new JButton();
        b8.setText("8");
        under.add(b8);
        b8.addActionListener(new OnButton());

        JButton b9 = new JButton();
        b9.setText("9");
        under.add(b9);
        b9.addActionListener(new OnButton());

        JButton plus = new JButton();
        plus.setText("+");
        under.add(plus);
        plus.addMouseListener(new Mosing());

        //الکی

        JButton zero = new JButton();
        zero.setText("0");
        under.add(zero);
        zero.addActionListener(new OnButton());

        JButton plus2 = new JButton();
        plus2.setText("clear");
        under.add(plus2);
        plus2.addActionListener(new OnButton());

        JButton plus3 = new JButton();
        plus3.setText("-");
        under.add(plus3);
        plus3.addMouseListener(new Mosing());
        //تمام
        JButton equal = new JButton();
        equal.setBackground(Color.orange);
        equal.setText("=");
        under.add(equal);
        equal.addMouseListener(new Mosing());


        ///  وسط
        textField = new JTextField();
        textField.setPreferredSize(new Dimension(300, 50));
        // textField.setText();
        head.add(textField);


        frame.setVisible(true);
    }

    class Mosing implements MouseListener {

        @Override
        public void mouseClicked(MouseEvent e) {
            //calc.this به کلاسی که اینر هست اجازه می دهد که به پدر به طور کامل دسترسی داشته باشد
            //عادی هم بزنید قبول میکنه
            if (!((JButton) e.getSource()).getText().equals("=")) {
                Calc.this.sign = ((JButton) e.getSource()).getText().toCharArray()[0];
                System.out.println(Calc.this.sign);
                System.out.println(first + "********");
                if (first == 0) {
                    first = Integer.parseInt(Calc.textField.getText());
                } else {
                    second = Integer.parseInt(Calc.textField.getText());
                }
                Calc.textField.setText("");
            } else {
                second = Integer.parseInt(Calc.textField.getText());
                System.out.println("دارم انجام میدم");
                System.out.println(first);
                System.out.println(second);
                int hasel=0;
                if (Calc.this.sign == '+') {
                    hasel = first + second;
                   // Calc.textField.setText(Integer.toString(hasel));
                }
                if (Calc.this.sign == '-') {
                    hasel = first - second;
                    //Calc.textField.setText(Integer.toString(hasel));
                }
                Calc.textField.setText(Integer.toString(hasel));
                first=hasel;
                second=0;
            }
        }

        @Override
        public void mousePressed(MouseEvent e) {

        }

        @Override
        public void mouseReleased(MouseEvent e) {

        }

        @Override
        public void mouseEntered(MouseEvent e) {

        }

        @Override
        public void mouseExited(MouseEvent e) {

        }
    }
    class OnButton implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if(!((JButton)e.getSource()).getText().equals("clear")) {
                Calc.textField.setText(Calc.textField.getText() + ((JButton) e.getSource()).getText());
            }
            else {
                Calc.textField.setText(" ");
                first=0;
                second=0;
            }
        }
    }
}

