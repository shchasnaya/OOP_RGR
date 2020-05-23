package level;;

import com.sun.org.apache.xpath.internal.objects.XNumber;
import com.toedter.calendar.JDateChooser;
import javax.swing.*;
import java.awt.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DlgFilm extends Dlg{
    JLabel labelGenre;
    JLabel labelDirector;
    JLabel labelDuration;
    JLabel labelRating;
    JLabel labelPremiere;
    JLabel labelDateFinish;
    JTextField textFieldGenre;
    JTextField textFieldDirector;
    JTextField textFieldDuration;
    JDateChooser dateChooser_DateFinish;
    JDateChooser dateChooser_premiere;
    JSpinner spinner_rating;
    JComboBox comboBox_genre;

    public DlgFilm() {
        setTitle("Film");
        setBounds(100,100,300,380);
        setMinimumSize(new Dimension(300,380));
        GridLayout layout = new GridLayout(7,2);
        layout.setVgap(10);
        layout.setHgap(20);
        form.setLayout(layout);
        DateFormat df = new SimpleDateFormat("dd-MM-yyyy");
        labelGenre = new JLabel("Genre",JLabel.CENTER);
        labelGenre.setFont(new Font("Imprint MT Shadow", Font.BOLD, 18));
        labelGenre.setForeground(new Color(76,12,4));
        form.add(labelGenre);
        String[] items = {
                "Пригоди",
                "Містика",
                "Фентезі",
                "Жахи",
                "Мультфільм",
                "Фантастика",
                "Боєвик"
        };
        comboBox_genre = new JComboBox(items);
        form.add(comboBox_genre);
        labelDirector = new JLabel("Director",JLabel.CENTER);
        labelDirector.setFont(new Font("Imprint MT Shadow", Font.BOLD, 18));
        labelDirector.setForeground(new Color(76,12,4));
        form.add(labelDirector);
        textFieldDirector = new JTextField();
        form.add(textFieldDirector);
        labelDuration = new JLabel("Duration",JLabel.CENTER);
        labelDuration.setFont(new Font("Imprint MT Shadow", Font.BOLD, 18));
        labelDuration.setForeground(new Color(76,12,4));
        form.add(labelDuration);
        textFieldDuration = new JTextField();
        form.add(textFieldDuration);
        labelRating = new JLabel("Rating",JLabel.CENTER);
        labelRating.setFont(new Font("Imprint MT Shadow", Font.BOLD, 18));
        labelRating.setForeground(new Color(76,12,4));
        form.add(labelRating);
        SpinnerModel numbers = new SpinnerNumberModel(5, 1, 10, 1);
        spinner_rating = new JSpinner(numbers);
        //textFieldRating = new JTextField();
        form.add(spinner_rating);
        labelPremiere = new JLabel("Premiere",JLabel.CENTER);
        labelPremiere.setFont(new Font("Imprint MT Shadow", Font.BOLD, 18));
        labelPremiere.setForeground(new Color(76,12,4));
        form.add(labelPremiere);
        dateChooser_premiere = new com.toedter.calendar.JDateChooser();
        dateChooser_premiere.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        form.add(dateChooser_premiere);
        labelDateFinish = new JLabel("DateFinish",JLabel.CENTER);
        labelDateFinish.setFont(new Font("Imprint MT Shadow", Font.BOLD, 18));
        labelDateFinish.setForeground(new Color(76,12,4));
        form.add(labelDateFinish);
        dateChooser_DateFinish = new com.toedter.calendar.JDateChooser();
        dateChooser_DateFinish.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        form.add(dateChooser_DateFinish);
    }

    @Override
    public Object createObject() throws Exception {
        if(!okey) {
            return null;
        }
        DateFormat df = new SimpleDateFormat("dd-MM-yyyy");
        String name = textFieldName.getText();
        String genre = (String) comboBox_genre.getSelectedItem();
        String director = textFieldDirector.getText();
        int duration = Integer.parseInt(textFieldDuration.getText());
        int rating = Integer.parseInt(spinner_rating.getValue().toString());
        String premiere = df.format(dateChooser_premiere.getDate());
        String dateFinish = df.format(dateChooser_DateFinish.getDate());
        return new Film(name, genre, director, duration, rating, premiere, dateFinish);
    }

    public DlgFilm (Object data) {
        this();
        Film f = (Film) data;
        textFieldName.setText(f.name);
        comboBox_genre.setSelectedItem(f.genre);
        textFieldDirector.setText(f.director);
        textFieldDuration.setText(String.valueOf(f.duration));
        spinner_rating.setValue(f.rating);
        Date date_finish = null;
        try {
            date_finish = new SimpleDateFormat("dd-MM-yyyy").parse(f.dateFinish);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Date date_premiere = null;
        try {
            date_premiere = new SimpleDateFormat("dd-MM-yyyy").parse(f.premiere);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        dateChooser_premiere.setDate(date_premiere);
        dateChooser_DateFinish.setDate(date_finish);
    }

}
