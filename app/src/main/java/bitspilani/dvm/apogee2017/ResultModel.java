package bitspilani.dvm.apogee2017;

import java.util.Date;

/**
 * Created by jai on 20/10/15.
 */
public class ResultModel implements Comparable<ResultModel> {

    private String id;
    private String name;
    private Date updatedAt;
    private String result;

    public ResultModel(){}

    public ResultModel(String id, String name, Date updatedAt, String result) {
        this.id = id;
        this.name = name;
        this.updatedAt = updatedAt;
        this.result = result;
    }

    public void setID(String id) { this.id = id; }

    public String getID() { return this.id; }

    public void setName(String name) { this.name = name; }

    public String getName() { return this.name; }

    public void setUpdatedAt(Date updatedAt) { this.updatedAt = updatedAt; }

    public Date getUpdatedAt() { return  this.updatedAt; }

    public void setResult(String result) { this.result = result; }

    public String getResult() { return this.result; }

    @Override
    public int compareTo(ResultModel d){
        return (d.getUpdatedAt()).compareTo(this.getUpdatedAt());
    }
}
