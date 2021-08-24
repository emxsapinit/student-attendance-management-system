package studentattendance;

import java.awt.Color;
import static java.awt.Color.black;
import java.awt.Component;
import java.awt.Font;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

public abstract class SimpleHeaderRenderer extends JLabel implements TableCellRenderer {
 
    public SimpleHeaderRenderer() {
        setFont(new Font("Tahoma", Font.BOLD, 14));
        setForeground(Color.darkGray);
    //    setBackground(Color.darkGray);
    //    setBorder(1);
        setBorder(BorderFactory.createEtchedBorder());
    }
     
    @Override
    public Component getTableCellRendererComponent(JTable table, Object value,
            boolean isSelected, boolean hasFocus, int row, int column) {
        setText(value.toString());
        return this;
    }

    private void setBorder(int i) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
 
}