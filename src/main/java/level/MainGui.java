package level;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.TreeModel;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Enumeration;
import java.util.SplittableRandom;

public class MainGui implements Serializable{
    public JFrame frame;
    private JButton addButton;
    private JButton removeButton;
    private JButton editButton;
    private JTree tree1;
    private JPanel MainPanel;
    private JPanel panel1;

    private JMenuBar menuBar = new JMenuBar();
    private JMenu menu = new JMenu("File");
    private JMenu operations = new JMenu("Operations");
    private JMenuItem menuItem_save = new JMenuItem("Store");
    private JMenuItem menuItem_open = new JMenuItem("Restore");
    private JMenuItem menuItem_director = new JMenuItem("The oldest premiere search");
    private JMenuItem menuItem_genre = new JMenuItem("Search for the genre");
    private JMenuItem menuItem_rating = new JMenuItem("rating counting");




    public MainGui() throws IOException {
        initialize();
    }

    /**
     * Initilize the contens of the frame
     */
    private void initialize() throws IOException {
        frame = new JFrame();
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setBounds(200, 200, 600, 400);
        frame.setMinimumSize(new Dimension(600, 400));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("РГР \"Кінотеатр\"");
        frame.setContentPane(MainPanel);
        onWindowOpen();

        menuBar.add(menu);
        menuBar.add(operations);
        menu.add(menuItem_save);
        menu.add(menuItem_open);
        operations.add(menuItem_director);
        operations.add(menuItem_genre);
        operations.add(menuItem_rating);
        frame.setJMenuBar(menuBar);

        tree1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                onMouseClicked(e);
            }
        });
        addButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                onAdd();
            }
        });
        removeButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                onDelete();
            }
        });
        editButton.addActionListener(e -> onEdit());
        menuItem_save.addActionListener(e -> onStore());
        menuItem_open.addActionListener(e -> onRestore());
        menuItem_director.addActionListener(e -> onDate());
        menuItem_genre.addActionListener(e -> onGenre());
        menuItem_rating.addActionListener(e -> onRating());
    }

    protected TreeModel getStartModel() throws Exception {
        //Створюємо об'єкти вузлів дерева
        Cinema c = new Cinema("Multiplex", "Пр. Миру 53");
        Hall h = new Hall("Зала 1", 25, 10);
        Film f = new Film("Алладін", "Пригоди", "Гай Річі", 102, 7, "01-05-2020","20-05-2020");
        Session s = new Session("Сеанс 1", "12:00", 60,100);

        //Створюємо вузли дерева
        DefaultMutableTreeNode root = new DefaultMutableTreeNode(c);
        DefaultMutableTreeNode hNod = new DefaultMutableTreeNode(h);
        DefaultMutableTreeNode fNod = new DefaultMutableTreeNode(f);
        DefaultMutableTreeNode sNod = new DefaultMutableTreeNode(s);

        //Зв'язуємо вузли між собою
        root.add(hNod);
        hNod.add(fNod);
        fNod.add(sNod);
        //Отримуємо та повертаємо створену модель
        return (new JTree(root)).getModel();
    }

    protected void onWindowOpen() {
        try {
            tree1.setModel(getStartModel());
            for(int i = 0; i < tree1.getRowCount(); i++ )
                tree1.expandRow(i);
        } catch (Exception e1) {
            e1.printStackTrace();
        }
    }

    /**
     * ПОпередня обробка посилання на вибраний метод
     */
    private DefaultMutableTreeNode getSelectedNode() {
        //Отримує посилання на вибраний вузол дерева
        //Якщо вузол не вибрано, посилання дорівнює null
        Object selectedNode = tree1.getLastSelectedPathComponent();
        if(selectedNode == null) {
            JOptionPane.showMessageDialog(tree1, "Node was not selected", frame.getTitle(), JOptionPane.ERROR_MESSAGE);
            return null;
        }
        //Приведемо посилання типу вузла
        return (DefaultMutableTreeNode) selectedNode;
    }

    /**
     * Розгортання всіх вікон
     */
    private void expendAll() {
        for (int i = 0; i < tree1.getRowCount(); i++) {
            tree1.expandRow(i);
        }
    }

    /**
     * Видалення вузла
     */
    private void selectNode(DefaultMutableTreeNode node) {
        int n = 0;
        DefaultMutableTreeNode root = (DefaultMutableTreeNode) tree1.getModel().getRoot();
        Enumeration<DefaultMutableTreeNode> enm = root.children();
        while (enm.hasMoreElements()) {
            DefaultMutableTreeNode nod = enm.nextElement();
            if(nod == null) {
                tree1.setSelectionRow(n);
            }
            n++;
        }
    }

    /**
     * Перегляд вмісту вузла
     */
    private void onMouseClicked(MouseEvent e) {
        if (e.getClickCount() != 3 || e.getButton() != MouseEvent.BUTTON3) {
            return;
        }
        DefaultMutableTreeNode node = getSelectedNode();
        if(node == null) {
            return;
        }
        AnyData data = (AnyData) node.getUserObject();

        Dlg dlg = data.showDialog(false);
        ((JDialog)dlg).dispose();
    }

    /**
     * Додовання вузла
     */
    protected void onAdd() {
        DefaultMutableTreeNode parent = getSelectedNode();
        if(parent == null) return;
        AnyData parentData = (AnyData) parent.getUserObject();
        Dlg dlg = parentData.showSonDialog();
        if(dlg == null) return;
        Object obj;
        try {
            obj = dlg.createObject();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(tree1, e.getMessage(), frame.getTitle(), JOptionPane.ERROR_MESSAGE);
            return;
        }
        ((JDialog)dlg).dispose();
        if(obj == null) return;
        DefaultMutableTreeNode newSon = new DefaultMutableTreeNode(obj);
        parent.add(newSon);
        tree1.updateUI();
        expendAll();
    }

    private void onDelete() {
        DefaultMutableTreeNode node = getSelectedNode();
        if(node == null) return;
        node.removeFromParent();
        tree1.setSelectionPath(null);
        tree1.updateUI();
    }

    private void onEdit() {
        DefaultMutableTreeNode node = getSelectedNode();
        if(node == null) return;
        AnyData data = (AnyData) node.getUserObject();
        Dlg dlg = data.showDialog(true);
        Object obj;
        try {
            obj = dlg.createObject();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(tree1, e.getMessage(),frame.getTitle(),JOptionPane.ERROR_MESSAGE);
            return;
        }
        ((JDialog)dlg).dispose();
        if(dlg == null) return;
        node.setUserObject(obj);
        tree1.updateUI();
    }

    /**
     * Реалізація збереження у файл
     */
    private void onStore() {
        if (tree1.getModel() == null) return;
        JFileChooser fileChooser = new JFileChooser("Серіалізація моделі дерева");
        fileChooser.showSaveDialog(frame);
        try {
            File f = fileChooser.getSelectedFile();
            String fName = f.getAbsolutePath();
            FileOutputStream fileStream = new FileOutputStream(fName);
            ObjectOutputStream out = new ObjectOutputStream(fileStream);
            out.writeObject(tree1.getModel());
            out.close();
        } catch (Exception e1) {
            JOptionPane.showMessageDialog(tree1, "Помилка відкриття файлу", "Збереження дерева у файлі", JOptionPane.ERROR_MESSAGE);
            return;
        }
        tree1.setModel((new JTree()).getModel());
    }

    /**
     * Реалізації відновлення з файлу
     */
    private void onRestore() {
        FileDialog fileDialog = new FileDialog(frame);
        fileDialog.setMode(FileDialog.LOAD);
        fileDialog.setVisible(true);
        String dr = fileDialog.getDirectory();
        String fn = fileDialog.getFile();
        if (dr == null || fn == null) return;
        String fName = dr + fn;
        try {
            ObjectInputStream in = new ObjectInputStream(new FileInputStream(fName));
            TreeModel model = (TreeModel) in.readObject();
            tree1.setModel(model);
            in.close();
        } catch (Exception e1) {
            JOptionPane.showMessageDialog(tree1, "Помилка десеріалізації дерева", "Десеріалізація", JOptionPane.ERROR_MESSAGE);
            return;
        }
        expendAll();
    }

    /**
     * Пошук фільму найстаршого за ейтингом
     */
    private void onDate() {
        DefaultMutableTreeNode root = (DefaultMutableTreeNode) tree1.getModel().getRoot();
        Enumeration<DefaultMutableTreeNode> enmHall = root.children();
        String  max_date_String = "00-00-0000";
        Date max_date = null;
        try {
            max_date = new SimpleDateFormat("dd-MM-yyyy").parse(max_date_String);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        DefaultMutableTreeNode oldest = null;
        while(enmHall.hasMoreElements()) {
            DefaultMutableTreeNode operator = enmHall.nextElement();
            Enumeration<DefaultMutableTreeNode> enmFilm = operator.children();
            while (enmFilm.hasMoreElements()) {
                DefaultMutableTreeNode operator2 = enmFilm.nextElement();
                Object data = operator2.getUserObject();
                if (!(data instanceof Film)) continue;
                String pr = ((Film) data).getPremiere();
                Date date = null;
                try {
                    date = new SimpleDateFormat("dd-MM-yyyy").parse(pr);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                if (date.after(max_date)) {
                    max_date = date;
                    oldest = operator2;
                }
            }
        }
        selectNode(oldest);
        ((AnyData) oldest.getUserObject()).showDialog(false);
    }

    /**
     * ПОшук фільму за фанром
     */
    private void onGenre() {
        ImageIcon  icon = null;
        String[] genr= {
                "Пригоди",
                "Містика",
                "Фентезі",
                "Жахи",
                "Мультфільм",
                "Фантастика",
                "Боєвик"
        };
        Object result = JOptionPane.showInputDialog(
                tree1,
                "Вибір жанру :",
                "Выбор жанру",
                JOptionPane.QUESTION_MESSAGE,
                icon, genr, genr[0]);
        if(result == null) {
            return;
        }
        int count = 0;
        boolean flag = false;
        String genre_input = result.toString();
        String answer = "Фільми, що мають жанр " + genre_input + ":";
        System.out.println(genre_input);
        DefaultMutableTreeNode root = (DefaultMutableTreeNode) tree1.getModel().getRoot();
        Enumeration<DefaultMutableTreeNode> enmHall = root.children();
        while(enmHall.hasMoreElements()) {
            DefaultMutableTreeNode operator = enmHall.nextElement();
            Enumeration<DefaultMutableTreeNode> enmFilm = operator.children();
            while (enmFilm.hasMoreElements()) {
                DefaultMutableTreeNode operator2 = enmFilm.nextElement();
                Object data = operator2.getUserObject();
                if (!(data instanceof Film)) continue;
                if(((Film)data).genre == genre_input) {
                    count ++;
                    flag = true;
                    answer += "\n" + count + ". " + ((Film)data).name;
                }
            }
        }
        if(flag) {
            answer += "\nЗагальна кількість: " + count;
        } else {
            answer = "Не існує фільмів, що мають жанр " + genre_input;
        }
        JOptionPane.showMessageDialog(tree1,
                answer,
                "Пошук за жанром", JOptionPane.INFORMATION_MESSAGE);
    }

    /**
     * Підрахунок та виведення фільмів кожного рейтингу
     */
    private void onRating() {
        DefaultMutableTreeNode root = (DefaultMutableTreeNode) tree1.getModel().getRoot();
        Enumeration<DefaultMutableTreeNode> enmHall = root.children();
        int[] mas = new int[] {0,0,0,0,0,0,0,0,0,0};
        String[] name_film = new String[10];
        for (int i = 0; i < 10; i++)
            name_film[i] = "";
        while(enmHall.hasMoreElements()) {
            DefaultMutableTreeNode operator = enmHall.nextElement();
            Enumeration<DefaultMutableTreeNode> enmFilm = operator.children();
            while (enmFilm.hasMoreElements()) {
                DefaultMutableTreeNode operator2 = enmFilm.nextElement();
                Object data = operator2.getUserObject();
                if (!(data instanceof Film)) continue;
                int rat = ((Film) data).rating;
                String name = ((Film) data).name;
                mas[rat - 1]++;
                name_film[rat - 1] += name + ", ";
            }
        }

        String result = "Кількість фільмів кожного рейтингу та їх назви:";
        for (int i = 0; i < 10; i++) {
            if (mas[i] > 0) {
                result += "\nРейтинг "  + (i+1) + " мають фільми:\n     " + name_film[i] + "\n     їх кількість: " + mas[i];
            } else  {
                result += "\nРейтинг "  + (i+1) + " не має жодний фільм.";
            }
        }
        JOptionPane.showMessageDialog(tree1,
                result,
                "Рейнинг фільмів", JOptionPane.INFORMATION_MESSAGE);
    }
}
