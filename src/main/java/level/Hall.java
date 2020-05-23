package level;;

public class Hall extends AnyData{
    int place; //кількість місць
    int VIPplace; //кількість VIPмісць

    public Hall(String name, int place, int VIPplace) {
        this.name = name;
        this.place = place;
        this.VIPplace = VIPplace;
    }

    @Override
    Dlg showDialog (boolean b) {
        DlgHall h = new DlgHall(this);
        h.setEditable(b);
        h.setVisible(true);
        return h;
    }

    @Override
    Dlg showSonDialog() {
        DlgFilm f = new DlgFilm();
        f.setVisible(true);
        return f;
    }
}
