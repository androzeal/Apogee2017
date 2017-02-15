package bitspilani.dvm.apogee2017;


import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collections;


/**
 * A simple {@link Fragment} subclass.
 */
public class Tab1 extends android.support.v4.app.Fragment {
    ArrayList<EventModelFB> eventList;
    ListView tabList;
    String[] name;
    String[] location;
    String[] time;
    String[] desc;
    String day;
    int dayN;
    DatabaseReference mDatabase;
    ValueEventListener vel;

    public Tab1()
    {

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mDatabase = Utils.getDatabase().getReference();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        //Getting data from the Bundle
        day=getArguments().getString("date");
        dayN = getArguments().getInt("day");
        View view = inflater.inflate(R.layout.fragment_tab1, container, false);

        tabList = (ListView) view.findViewById(R.id.tabList);

        tabList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                showDescription(position);
            }
        });

        vel = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                eventList = new ArrayList<>();
                long num = dataSnapshot.child("events").child(String.valueOf(dayN)).getChildrenCount();
                for(int i = 1; i <= num; i++){
                    EventModelFB event = dataSnapshot.child("events").child(String.valueOf(dayN)).child(String.valueOf(i)).getValue(EventModelFB.class);
                    eventList.add(event);
                }

                name=new String[eventList.size()];
                location=new String[eventList.size()];
                time=new String[eventList.size()];
                desc=new String[eventList.size()];

                Collections.sort(eventList);

                for (int i = 0; i < eventList.size(); i++)
                {
                    name[i]=eventList.get(i).getName();
                    location[i]=eventList.get(i).getLocation();
                    desc[i]=eventList.get(i).getDesc();
                    time[i]= eventList.get(i).getStart();
                }

                if (eventList.size() == 0) {
                    Log.d("Neo", "No Data Received1");

                } else {
                    if(getActivity() != null){
                        ListAdapter custom = new CustomList(getActivity(), name, location, time);
                        tabList.setAdapter(custom);
                    }
                }


            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        };

        mDatabase.addValueEventListener(vel);


        // Inflate the layout for this fragment
        return view;
    }

    @Override
    public void onStop() {
        super.onStop();
        if(vel != null){
            mDatabase.removeEventListener(vel);
        }
    }

    public void showDescription(int position)
    {
        Intent i=new Intent(getActivity(),EventDetail.class);
        i.putExtra("name",name[position]);
        i.putExtra("location",location[position]);
        i.putExtra("time",time[position]);
        i.putExtra("desc",desc[position]);
        i.putExtra("date",day);
        startActivity(i);
    }

    }

