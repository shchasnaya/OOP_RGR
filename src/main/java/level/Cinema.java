package level;;

public class Cinema extends AnyData {
    String adress;

    public Cinema(String name, String adress) throws Exception {
        this.name = name;
        this.adress = adress;
    }

    @Override
    Dlg showDialog (boolean b) {
        DlgCinema c = new DlgCinema(this);
        c.setEditable(b);
        c.setVisible(true);
        return c;
    }

    @Override
    Dlg showSonDialog() {
        DlgHall h = new DlgHall();
        h.setVisible(true);
        return h;
    }

    public String getAdress() {
        return adress;
    }
}
