package Model;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Created by NSD on 17.05.17.
 */

@DatabaseTable
public class Subcourse {


    public Subcourse(){}

    @DatabaseField(generatedId = true)
    private long id;
    @DatabaseField
    private String headerText;
    @DatabaseField
    private String base64Content;
    @DatabaseField
    private long parentCourse;


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getHeaderText() {
        return headerText;
    }

    public void setHeaderText(String headerText) {
        this.headerText = headerText;
    }

    public String getBase64Content() {
        return base64Content;
    }

    public void setBase64Content(String base64Content) {
        this.base64Content = base64Content;
    }

    public long getParentCourse() {
        return parentCourse;
    }

    public void setParentCourse(long parentCourse) {
        this.parentCourse = parentCourse;
    }
}
