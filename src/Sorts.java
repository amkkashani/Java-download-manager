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
        JMenuItem byDate = new JMenuItem("sort by date");
        byDate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DataBase.sortDate();
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

        sorts.add(byName);
        sorts.add(byDate);
        sorts.add(bySize);

    }

    public JMenu getSorts() {
        return sorts;
    }
}
