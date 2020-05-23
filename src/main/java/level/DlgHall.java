package level;;

import javax.swing.*;
import java.awt.*;

public class DlgHall extends Dlg{
    JLabel labelPlace;
    JLabel labelVIPPlace;
    JTextField textFieldPlace;
    JTextField textFieldVIPPlace;

    @Override
    public Object createObject() throws Exception {
        if(!okey) {
            return null;
        }
        String name = textFieldName.getText();
        int place = Integer.parseInt(textFieldPlace.getText());
        int VIPplace = Integer.parseInt(textFieldVIPPlace.getText());
        return new Hall(name, place, VIPplace);
    }

    public DlgHall (Object data) {
        this();
        Hall h = (Hall) data;
        textFieldName.setText(h.name);
        textFieldPlace.setText(String.valueOf(h.place));
        textFieldVIPPlace.setText(String.valueOf(h.VIPplace));
    }

    public DlgHall() {
        setTitle("Hall");
        setBounds(100,100,350,225);
        setMinimumSize(new Dimension(350,225));
        GridLayout layout = new GridLayout(3,2);
        layout.setVgap(20);
        layout.setHgap(20);
        form.setLayout(layout);
        labelPlace = new JLabel("Count place",JLabel.CENTER);
        labelPlace.setFont(new Font("Imprint MT Shadow", Font.BOLD, 18));
        labelPlace.setForeground(new Color(76,12,4));
        form.add(labelPlace);
        textFieldPlace = new JTextField();
        form.add(textFieldPlace);
        labelVIPPlace = new JLabel("Count VIP place",JLabel.CENTER);
        labelVIPPlace.setFont(new Font("Imprint MT Shadow", Font.BOLD, 18));
        labelVIPPlace.setForeground(new Color(76,12,4));
        form.add(labelVIPPlace);
        textFieldVIPPlace = new JTextField();
        form.add(textFieldVIPPlace);
    }

}
