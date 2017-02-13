package bitspilani.dvm.apogee2017;

import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * Created by vaibhav on 13/02/17.
 */

public class EventModelFB implements Comparable<EventModelFB>{

    String name;
    String location;
    String start;
    String date;
    String desc;
    String category;

    public EventModelFB() {
    }

    public EventModelFB(String name, String location, String start, String date, String desc, String category) {
        this.name = name;
        this.location = location;
        this.start = start;
        this.date = date;
        this.desc = desc;
        this.category=category;
    }


    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public void setLocation(String location) { this.location = location; }

    public String getLocation() { return this.location; }

    public void setStart(String start) { this.start = start; }

    public String getStart() { return this.start; }

    public void setDate(String date) { this.date = date; }

    public String getDate() { return this.date; }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getDesc() {
        return this.desc;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    @Override
    public int compareTo(EventModelFB o) {
        SimpleDateFormat sdf = new SimpleDateFormat("hh:mm a dd MMM");
        try {
            return (sdf.parse(this.getStart() + " " + this.getDate()).compareTo(sdf.parse(o.getStart() + " " + o.getDate())));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return 0;
    }
}
