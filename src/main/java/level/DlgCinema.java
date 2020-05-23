package level;;

import javax.swing.*;
import java.awt.*;

public class DlgCinema extends Dlg{
    JLabel labelAddress;
    JTextField textFieldAdress;

    public DlgCinema (Object data) {
        this();
        Cinema c = (Cinema)data;
        textFieldName.setText(c.name);
        textFieldAdress.setText(c.adress);
    }

    @Override
    public Object createObject() throws Exception {
        if(!okey) return null;
        String name = textFieldName.getText();
        String adress = textFieldAdress.getText();
        return new Cinema(name, adress);
    }

    public DlgCinema() {
        setTitle("Cinema");
        setBounds(100,100,100,100);

        GridLayout layout = new GridLayout(2,1);
        layout.setVgap(20);
        form.setLayout(layout);
        labelAddress = new JLabel("Address",JLabel.CENTER);
        labelAddress = new JLabel("Time",JLabel.CENTER);
        labelAddress.setFont(new Font("Imprint MT Shadow", Font.BOLD, 18));
        form.add(labelAddress);
        textFieldAdress = new JTextField();
        form.add(textFieldAdress);
    }
}
