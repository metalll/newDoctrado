package Model;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Created by NSD on 17.05.17.
 */

@DatabaseTable
public class Course {

@DatabaseField(generatedId = true)
    private long id;
@DatabaseField
    private String headerCardText;
@DatabaseField
    private String headerText;
@DatabaseField
    private int timeToLearn;
@DatabaseField
    private int cost;
@DatabaseField
    private int avatar;
@DatabaseField
    private long authorId;
@DatabaseField
    private boolean hasTest;


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getHeaderCardText() {
        return headerCardText;
    }

    public void setHeaderCardText(String headerCardText) {
        this.headerCardText = headerCardText;
    }

    public String getHeaderText() {
        return headerText;
    }

    public void setHeaderText(String headerText) {
        this.headerText = headerText;
    }

    public int getTimeToLearn() {
        return timeToLearn;
    }

    public void setTimeToLearn(int timeToLearn) {
        this.timeToLearn = timeToLearn;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public int getAvatar() {
        return avatar;
    }

    public void setAvatar(int avatar) {
        this.avatar = avatar;
    }

    public long getAuthorId() {
        return authorId;
    }

    public void setAuthorId(long authorId) {
        this.authorId = authorId;
    }

    public boolean isHasTest() {
        return hasTest;
    }

    public void setHasTest(boolean hasTest) {
        this.hasTest = hasTest;
    }
}
