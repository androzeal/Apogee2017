package bitspilani.dvm.apogee2017;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
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

public class SearchResults extends Fragment {

    String search_key;
    String[] name;
    ListView category_list;
    String[] location;
    String[] time;
    String[] desc;
    ArrayList<EventModelFB> eventList;
    String [] day;
    DatabaseReference mDatabase;
    ValueEventListener val;

    public SearchResults()
    {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view=inflater.inflate(R.layout.fragment_searchresults, container, false);

        search_key=getArguments().getString("key");
        category_list = (ListView) view.findViewById(R.id.category_list);

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
                        if (event.getName().contains(search_key)){
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
                    ListAdapter custom = new CustomList(getActivity(), name, location, time);
                    category_list.setAdapter(custom);
                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        };

        mDatabase.addValueEventListener(val);

        return view;
    }

    @Override
    public void onStop() {
        super.onStop();
        if(val != null){
            mDatabase.removeEventListener(val);
        }
    }

    public void showDescription(int position)
    {
        Intent i=new Intent(getActivity(),EventDetail.class);
        i.putExtra("name",name[position]);
        i.putExtra("location",location[position]);
        i.putExtra("time",time[position]);
        i.putExtra("desc",desc[position]);
        i.putExtra("date",day[position]);
        startActivity(i);
    }
}
