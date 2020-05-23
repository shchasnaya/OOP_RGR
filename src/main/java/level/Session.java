package level;;

public class Session extends AnyData {
    String time; //час
    int ticket; //ціна квитка
    int VIPticket; //ціна VIP витка

    public Session(String name, String time, int ticket, int VIPticket) {
        this.name = name;
        this.time = time;
        this.ticket = ticket;
        this.VIPticket = VIPticket;
    }

    @Override
    Dlg showDialog (boolean b) {
        DlgSession s = new DlgSession(this);
        s.setEditable(b);
        s.setVisible(true);
        return s;
    }

    @Override
    Dlg showSonDialog() {
        return null;
    }

    public String getTime() {
        return time;
    }
}
