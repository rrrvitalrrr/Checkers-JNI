import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;

class MyCellRenderer extends DefaultTableCellRenderer {
    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int col) {
        super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, col);

        setHorizontalAlignment(SwingConstants.CENTER);  // Center align text
        setFont(new Font("Arial", Font.BOLD, 46));      // Set font for X and O

        if (value.equals('X')) {
            setForeground(Color.blue);  // Color for player X
        } else if (value.equals('O')) {
            setForeground(Color.red);   // Color for player O
        } else {
            setForeground(Color.yellow); // Default color for empty cells
        }
        setText(value.toString());
        return this;
    }
}
