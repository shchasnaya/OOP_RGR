package level;;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/*
 * Батьківський діалог Dialog - загальні функції для усіх діалогів
 */
public abstract class Dlg extends JDialog {
    protected boolean okey;
    protected JLabel labelName;
    protected  JPanel contentPane;
    private JPanel button;
    protected JTextField textFieldName;
    protected JButton buttonOK;
    protected JButton buttonCancel;
    protected JPanel form;

    public Dlg() {
        setMinimumSize(new Dimension(300, 200));
        setPreferredSize(new Dimension(400, 300));
        setModal(true);
        setBounds(100,100,100,100);
        GridLayout layout = new GridLayout(2,1);
        layout.setVgap(20);
        form.setLayout(layout);
        labelName = new JLabel("Name",JLabel.CENTER);
        labelName.setFont(new Font("Imprint MT Shadow", Font.BOLD, 18));
        labelName.setForeground(new Color(76,12,4));
        form.add(labelName);
        textFieldName = new JTextField();
        form.add(textFieldName, BorderLayout.NORTH);
        setContentPane(contentPane);

        buttonOK.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                onOK();
            }
        });
        buttonCancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        });
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onCancel();
            }
        });

        contentPane.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
    }

    private void onOK() {
        okey = true;
        setVisible(false);
        dispose();
    }

    private void onCancel() {
        okey = false;
        setVisible(false);
        dispose();
    }

    /*
     * Абстрактний метод createObject для повернення відповідних об’єктів у класах-спадкоємцях
     */
    public abstract Object createObject() throws Exception;

    /*
     * Метод для визначення можливості редагувати дані у діалогах
     */
    public void setEditable(boolean b) {
        buttonOK.setVisible(b);
        if(b)
            buttonCancel.setText("Cancel");
        else
            buttonCancel.setText("Exit");
        for(Component i: form.getComponents())
            i.setEnabled(b);
    }

}
