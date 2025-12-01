import javax.swing.table.AbstractTableModel;

public class TicTacToeModel extends AbstractTableModel {

    // Native methods implemented in C++
    public native boolean makeMove(int row, int col);
    public native boolean checkWin();
    public native boolean checkDraw();
    public native void resetBoard();
    public native boolean isXTurn();
    public native char getBoardValue(int row, int col);

    public TicTacToeModel() {
        resetBoard();  // Initialize the board at the start
    }

    @Override
    public int getRowCount() {
        return 5;
    }

    @Override
    public int getColumnCount() {
        return 5;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        return getBoardValue(rowIndex, columnIndex);
    }

    public void reset() {
        resetBoard();
        fireTableDataChanged();
    }
}
