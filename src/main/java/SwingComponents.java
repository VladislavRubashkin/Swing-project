import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;


public class SwingComponents implements ActionListener {
    private JTextField jTextField;
    private JButton buttonConnectionToDB;
    private JTable bookTable;
    private JScrollPane jScrollPane;
    private JFrame jFrameForTheMainWindow;
    private JFrame jFrameForThePopup;
    private BookTableModel bookTableModel;
    private JLabel label;


    public SwingComponents() {
        jFrameForTheMainWindow = new JFrame("Главное окно");
        jFrameForTheMainWindow.setSize(new Dimension(350, 200));
        jFrameForTheMainWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrameForTheMainWindow.setLocationRelativeTo(null);
        jFrameForTheMainWindow.setLayout(new GridBagLayout());

        jTextField = new JTextField(14);
        buttonConnectionToDB = new JButton("ConnectionToDB");
        label = new JLabel("Для подключения к БД введите пароль (123).");
        buttonConnectionToDB.addActionListener(this);
        bookTableModel = new BookTableModel(); // Модель для таблицы
        bookTable = new JTable(bookTableModel); // Таблица
        jScrollPane = new JScrollPane(bookTable); // Прокрутка(для таблицы)
        jScrollPane.setPreferredSize(new Dimension(400, 400)); // Задаём размеры таблицы

        jFrameForThePopup = new JFrame("Всплывающее окно");
        jFrameForThePopup.setSize(new Dimension(500, 500));
        jFrameForThePopup.setLocationRelativeTo(jFrameForTheMainWindow);
        jFrameForThePopup.setLayout(new GridBagLayout());


        jFrameForThePopup.add(jScrollPane, new GridBagConstraints(0, 0, 3, 1, 1, 1,
                GridBagConstraints.NORTH, GridBagConstraints.BOTH,
                new Insets(1, 1, 1, 1), 0, 0));

        jFrameForTheMainWindow.add(label, new GridBagConstraints(0, 0, 4, 1, 1, 1,
                GridBagConstraints.NORTH, GridBagConstraints.BOTH,
                new Insets(1, 1, 1, 1), 0, 0));

        jFrameForTheMainWindow.add(jTextField, new GridBagConstraints(1, 1, 1, 1, 1, 1,
                GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL,
                new Insets(1, 1, 1, 1), 0, 0));

        jFrameForTheMainWindow.add(buttonConnectionToDB, new GridBagConstraints(0, 1, 1, 1, 1, 1,
                GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL,
                new Insets(1, 1, 1, 1), 0, 0));


        jFrameForTheMainWindow.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        if (actionEvent.getActionCommand().equals("ConnectionToDB") && jTextField.getText().equals("123")) {
            List<String[]> result = new SQLDatabaseConnection().getBookFromDB();
            for (String[] row : result) {
                bookTableModel.addDateToTable(row);
            }
            bookTableModel.fireTableDataChanged();

            jFrameForThePopup.setVisible(true);

        }
    }

}
