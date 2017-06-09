package Model;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Created by NSD on 08.06.17.
 */


@DatabaseTable
public class Pushcare {
    @DatabaseField(generatedId = true)
    private long id;

    @DatabaseField
    private long userID;

    @DatabaseField
    private long courseBeBuy;


    public long getCourseBeBuy() {
        return courseBeBuy;
    }

    public void setCourseBeBuy(long courseBeBuy) {
        this.courseBeBuy = courseBeBuy;
    }

    public long getUserID() {
        return userID;
    }

    public void setUserID(long userID) {
        this.userID = userID;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
