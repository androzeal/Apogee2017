package bitspilani.dvm.apogee2017;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;


/**
 * A simple {@link Fragment} subclass.
 */
public class Events extends Fragment implements SHARED_CONSTANTS {



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view=inflater.inflate(R.layout.fragment_events, container, false);

        GridView eventsGrid=(GridView) view.findViewById(R.id.eventsGrid);
        eventsGrid.setAdapter(new EventsAdapter(getActivity(),EVENT_CATEGORIES,CATEGORY_PICS));

        eventsGrid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent i=new Intent(getActivity(),CategoryList.class);
                i.putExtra("category",CATEGORY_CODE[position]);
                i.putExtra("position",position);
                startActivity(i);
            }
        });

        return view;
    }

}
