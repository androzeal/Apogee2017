package bitspilani.dvm.apogee2017;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.TextView;

/**
 * Created by Nitish on 10/17/2015.
 */
public class CustomList extends ArrayAdapter<String> implements SHARED_CONSTANTS {

    private LayoutInflater mInflater;
    private final Context context;

    String[] eventLocation;
    String[] eventinTime;
    int t=-1;
    int pos=0;
    public CustomList(Context context, String[] item, String[] eventLocation, String[] eventinTime) {
        super(context,R.layout.custom_schedule, item);
        this.eventLocation=eventLocation;
        this.eventinTime=eventinTime;
        this.context=context;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        String str= getItem(position);
        final Holder holder;

       final Animation anim= AnimationUtils.loadAnimation(getContext(), R.anim.fade_in);

        if(convertView==null)
        {
            mInflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView=mInflater.inflate(R.layout.custom_schedule,parent,false);
            holder = new Holder();
            holder.name=(TextView) convertView.findViewById(R.id.name);
            holder.location=(TextView) convertView.findViewById(R.id.location);
            holder.inTime=(TextView) convertView.findViewById(R.id.inTime);
            convertView.setTag(holder);
        }
        else
        {
            holder=(Holder) convertView.getTag();
        }

        holder.name.setText(str);
        try {
            holder.location.setText(eventLocation[position]);
            holder.inTime.setText(eventinTime[position]);
        }
        catch (NullPointerException e)
        {
            Log.d("Results","Results is opened");
        }

        pos = position;

        if(t<pos)
            convertView.startAnimation(anim);

        t=position;
        return convertView;

    }

    public class Holder
    {
        public TextView name;
        public TextView location;
        public TextView inTime;
    }
}
