package bitspilani.dvm.apogee2017;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collections;

public class CategoryList extends Activity implements SHARED_CONSTANTS {

    ArrayList<EventModelFB> eventList;
    String[] name;
    ListView category_list;
    String[] location;
    String[] time;
    String[] desc;
    String [] day;
    DatabaseReference mDatabase;
    ValueEventListener val;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_list);

        category_list = (ListView) findViewById(R.id.category_list);
        final Bundle details=getIntent().getExtras();

        category_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                showDescription(position);
            }
        });

        mDatabase = Utils.getDatabase().getReference();

        val = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                eventList = new ArrayList<>();

                for(int j = 1; j <= 4; j++){
                    long num = dataSnapshot.child("events").child(String.valueOf(j)).getChildrenCount();
                    for(int i = 1; i <= num; i++){
                        EventModelFB event = dataSnapshot.child("events").child(String.valueOf(j)).child(String.valueOf(i)).getValue(EventModelFB.class);
                        if (event.getCategory().equals(details.getString("category"))){
                            eventList.add(event);
                        }
                    }
                }

                name=new String[eventList.size()];
                location=new String[eventList.size()];
                time=new String[eventList.size()];
                desc=new String[eventList.size()];
                day=new String[eventList.size()];

                Collections.sort(eventList);

                for (int i = 0; i < eventList.size(); i++)
                {
                    name[i]=eventList.get(i).getName();
                    location[i]=eventList.get(i).getLocation();
                    desc[i]=eventList.get(i).getDesc();
                    time[i]= eventList.get(i).getStart();
                    day[i]=eventList.get(i).getDate();
                }

                if (eventList.size() == 0) {
                    Log.d("Neo", "No Data Received1");

                } else {
                    ListAdapter custom = new CustomList(getBaseContext(), name, location, time);
                    category_list.setAdapter(custom);
                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        };

        mDatabase.addValueEventListener(val);

        try{
            android.app.ActionBar a=getActionBar();
            a.setTitle(EVENT_CATEGORIES[details.getInt("position")]);
        }
        catch (NullPointerException e)
        {
            Log.d("Nullpointer","Occurred");
        }

    }

    @Override
    protected void onStop() {
        super.onStop();
        if(val != null){
            mDatabase.removeEventListener(val);
        }
    }

    public void showDescription(int position)
    {
        Intent i=new Intent(this,EventDetail.class);
        i.putExtra("name",name[position]);
        i.putExtra("location",location[position]);
        i.putExtra("time",time[position]);
        i.putExtra("desc",desc[position]);
        i.putExtra("date",day[position]);
        startActivity(i);
    }
}
