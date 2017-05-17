package Model;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Created by NSD on 17.05.17.
 */


@DatabaseTable
public class Tests {



    public Tests(){}

    @DatabaseField(generatedId = true)
    private String id;

    @DatabaseField
    private String testJSON;

    @DatabaseField
    private long parentCourseId;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTestJSON() {
        return testJSON;
    }

    public void setTestJSON(String testJSON) {
        this.testJSON = testJSON;
    }

    public long getParentCourseId() {
        return parentCourseId;
    }

    public void setParentCourseId(long parentCourseId) {
        this.parentCourseId = parentCourseId;
    }
}
