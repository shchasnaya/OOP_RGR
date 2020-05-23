package level;

import java.text.SimpleDateFormat;

;

public class Film extends AnyData{
    String genre; //жанр
    String director; //режисер
    int duration; //тривалість
    int rating; //рейинг
    String premiere; //дата прем'єри
    String dateFinish; //дата завершення показу

    public Film(String name, String genre, String director, int duration, int rating, String premiere, String dateFinish) {
        this.name = name;
        this.genre = genre;
        this.director = director;
        this.duration = duration;
        this.rating = rating;
        this.premiere = premiere;
        this.dateFinish = dateFinish;
    }

    @Override
    Dlg showDialog (boolean b) {
        DlgFilm f = new DlgFilm(this);
        f.setEditable(b);
        f.setVisible(true);
        return f;
    }

    @Override
    Dlg showSonDialog() {
        DlgSession s = new DlgSession();
        s.setVisible(true);
        return s;
    }

    public String getPremiere() {
        return premiere;
    }
}
