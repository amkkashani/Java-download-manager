import javafx.scene.input.DataFormat;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Sorts {
    private JMenu sorts = new JMenu("sort");

    public Sorts() {
        JMenuItem byName = new JMenuItem("sort by name");
        byName.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            DataBase.sortName();
        }
    });
        JMenuItem byNameReverse = new JMenuItem("sort by name reverse");
        byNameReverse.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
//                System.out.println("salam");
                DataBase.sortNameReverce();
            }
        });

        JMenuItem byDate = new JMenuItem("sort by date");
        byDate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DataBase.sortDate();
            }
        });
        JMenuItem byDateReverce = new JMenuItem("sort by date Reverse");
        byDateReverce.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DataBase.sortDateReverce();
            }
        });

        sorts.setFont(new Font("fff", 50, 25));
        JMenuItem bySize = new JMenuItem("sort by size");
        bySize.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DataBase.sortSize();
            }
        });
        JMenuItem bySizeReverse = new JMenuItem("sort by size Reverse");
        bySizeReverse.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DataBase.sortSizeReverce();
            }
        });

        sorts.add(byName);
        sorts.add(byNameReverse);
        sorts.add(byDate);
        sorts.add(byDateReverce);
        sorts.add(bySize);
        sorts.add(bySizeReverse);

    }

    public JMenu getSorts() {
        return sorts;
    }
}
