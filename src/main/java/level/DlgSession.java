package level;;

import javax.swing.*;
import javax.swing.text.DefaultFormatterFactory;
import javax.swing.text.MaskFormatter;
import java.awt.*;
import java.text.ParseException;

public class DlgSession extends Dlg {
    JLabel labelTime;
    JLabel labelTicket;
    JLabel labelVIPTicket;
    JTextField textFieldTime;
    JTextField textFieldTicket;
    JTextField textFieldVIPTicket;

    public DlgSession() {
        setTitle("Session");
        setBounds(100,100,325,230);
        setMinimumSize(new Dimension(325,230));
        GridLayout layout = new GridLayout(4,2);
        layout.setVgap(10);
        layout.setHgap(20);
        form.setLayout(layout);
        textFieldTime = new JTextField();
        labelTime = new JLabel("Time",JLabel.CENTER);
        labelTime.setFont(new Font("Imprint MT Shadow", Font.BOLD, 18));
        labelTime.setForeground(new Color(76,12,4));
        form.add(labelTime);
        //textFieldTime = new JTextField();
        form.add(textFieldTime);
        labelTicket = new JLabel("price place",JLabel.CENTER);
        labelTicket.setFont(new Font("Imprint MT Shadow", Font.BOLD, 18));
        labelTicket.setForeground(new Color(76,12,4));
        form.add(labelTicket);
        textFieldTicket = new JTextField();
        form.add(textFieldTicket);
        labelVIPTicket = new JLabel("price VIP place",JLabel.CENTER);
        labelVIPTicket.setFont(new Font("Imprint MT Shadow", Font.BOLD, 18));
        labelVIPTicket.setForeground(new Color(76,12,4));
        form.add(labelVIPTicket);
        textFieldVIPTicket = new JTextField();
        form.add(textFieldVIPTicket);
    }

    @Override
    public Object createObject() throws Exception {
        if (!okey) {
            return null;
        }
        String name = textFieldName.getText();
        String time = textFieldTime.getText();
        int ticket = Integer.parseInt(textFieldTicket.getText());
        int VIPticket = Integer.parseInt(textFieldVIPTicket.getText());
        return new Session(name, time, ticket, VIPticket);
    }

    public DlgSession (Object data) {
        this();
        Session s = (Session)data;
        textFieldName.setText(s.name);
        textFieldTime.setText(s.time);
        textFieldTicket.setText(String.valueOf(s.ticket));
        textFieldVIPTicket.setText(String.valueOf(s.VIPticket));

    }
}
