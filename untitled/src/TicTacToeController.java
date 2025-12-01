import javax.swing.*;
import java.awt.event.*;

public class TicTacToeController {
    private final TicTacToeModel model;
    private final TicTacToeView view;

    public TicTacToeController(TicTacToeModel model, TicTacToeView view) {
        this.model = model;
        this.view = view;

        view.getTable().setModel(model);
        setupListeners();
    }

    private void setupListeners() {
        view.getTable().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                System.out.println("mouse pressed " + model.isXTurn() + " (at the start)");
                int row = view.getTable().rowAtPoint(e.getPoint());
                int col = view.getTable().columnAtPoint(e.getPoint());

                if (model.makeMove(row, col)) {
                    System.out.println("made move, so " + model.isXTurn() + " to let the \"O\" go");
                    model.fireTableDataChanged();  // Refresh table
                    if (model.checkWin()) {
                        System.out.println("wins " + (model.isXTurn() ? "O" : "X"));
                        JOptionPane.showMessageDialog(view, "Player " + (model.isXTurn() ? "O" : "X") + " wins!");
                        model.reset();
                    } else if (model.checkDraw()) {
                        JOptionPane.showMessageDialog(view, "Draw! Try again.");
                        model.reset();
                    }
                } else {
                    JOptionPane.showMessageDialog(view, "Invalid move. Try again.");
                }
                System.out.println("placed " + (model.isXTurn() ? "O" : "X"));
            }
        });
        view.getTable().addKeyListener(new KeyListener() {
            @Override
            public void keyPressed(KeyEvent keyBoard) {
                if (keyBoard.getKeyCode() == KeyEvent.VK_ENTER) {
                    System.out.println("enter pressed" + model.isXTurn() + " (at the start)");
                    if (model.makeMove(view.getTable().getSelectedRow(), view.getTable().getSelectedColumn())) {
                        model.fireTableDataChanged();
                        System.out.println("made move, so " + model.isXTurn() + " to let the \"O\" go");
                        if(model.checkWin()){
                            System.out.println("wins " + (model.isXTurn() ? "O" : "X"));
                            JOptionPane.showMessageDialog(null, (model.isXTurn() ? "O" : "X") + " wins!");
                            model.reset();        // Reset backend(c++)
                        } else if (model.checkDraw()){
                            JOptionPane.showMessageDialog(null,   "Draw, try again!");
                            model.reset();        // Reset backend (c++)
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Cannot place here. Place is already taken.");
                    }
                    System.out.println("placed " + (model.isXTurn() ? "O" : "X"));
                }
            }
            @Override public void keyTyped(KeyEvent e) {}
            @Override public void keyReleased(KeyEvent e) {}
        });

        view.getButtonRestart().addActionListener(e -> {
            model.reset();
            model.fireTableDataChanged();
        });
    }
}
