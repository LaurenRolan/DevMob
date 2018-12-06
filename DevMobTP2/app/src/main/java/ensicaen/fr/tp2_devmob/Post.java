package ensicaen.fr.tp2_devmob;

import java.io.Serializable;

public class Post implements Serializable {
    public int userId;
    public int id;
    public String title;
    public String body;

    @Override
    public String toString() {
        return title;
    }
}
