package level;;

import java.io.Serializable;

public abstract class AnyData implements Serializable {
    protected String name;

    @Override
    public String toString() {
        return name;
    }

    /*
     * Повертає і активізовує діалог для роботи з даними вузла
     */
    abstract Dlg showDialog (boolean b);
    /*
     * Повертає і активізовує діалог для роботи з даними спадкоємця
     */
    abstract Dlg showSonDialog();
}
