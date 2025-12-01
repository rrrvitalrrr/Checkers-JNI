import javax.swing.*;
import java.awt.*;

public class TicTacToeView extends JFrame {
    private final JTable table;
    private final JButton buttonRestart;
    private final JLabel gameLabel;

    public TicTacToeView() {
        setTitle("Tic-Tac-Toe 5x5");
        setSize(715, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JLayeredPane jLayeredPane = new JLayeredPane();
        jLayeredPane.setPreferredSize(new Dimension(715, 700));

        table = new JTable();
        table.setCellSelectionEnabled(false);
        table.setFocusable(true);
        table.setRowHeight(110);
        table.setFont(new Font("Arial", Font.PLAIN, 40));
        table.setTableHeader(null);

        // Apply custom cell renderer
        table.setDefaultRenderer(Object.class, new MyCellRenderer());

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(0, 80, 700, 700);
        jLayeredPane.add(scrollPane, JLayeredPane.DEFAULT_LAYER);

        buttonRestart = new JButton("Restart");
        buttonRestart.setBounds(288, 670, 120, 40);
        jLayeredPane.add(buttonRestart, JLayeredPane.PALETTE_LAYER);

        gameLabel = new JLabel("Tic-Tac-Toe GAME", SwingConstants.CENTER);
        gameLabel.setFont(new Font("Serif", Font.BOLD, 30));
        gameLabel.setForeground(Color.BLACK);
        gameLabel.setBounds(200, 16, 300, 40);
        jLayeredPane.add(gameLabel, JLayeredPane.PALETTE_LAYER);

        add(jLayeredPane);
        setVisible(true);
    }


    public JTable getTable() {
        return table;
    }

    public JButton getButtonRestart() {
        return buttonRestart;
    }
}
