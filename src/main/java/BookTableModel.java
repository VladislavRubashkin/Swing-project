import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;

public class BookTableModel extends AbstractTableModel {

    private int columnCount = 4;
    private ArrayList<String[]> list;

    public BookTableModel() {
        list = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            list.add(new String[getColumnCount()]);
        }
    }

    @Override
    public int getRowCount() {
        return list.size();
    }

    @Override
    public int getColumnCount() {
        return columnCount;
    }

    @Override
    public String getColumnName(int columnIndex) {
        switch (columnIndex) {
            case 0:
                return "id";
            case 1:
                return "title";
            case 2:
                return "isbn";
            case 3:
                return "description";
        }
        return "";
    }

    @Override
    public Object getValueAt(int i, int i1) {
        String[] str = list.get(i);
        String result = str[i1];
        return result;
    }

    public void addDateToTable(String[] row) {
        String[] rowTable = new String[getColumnCount()];
        System.arraycopy(row, 0, rowTable, 0, rowTable.length);
        list.add(rowTable);
    }


}
