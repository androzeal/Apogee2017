package bitspilani.dvm.apogee2017;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by HP on 2/18/2016.
 */
public class EventsAdapter  extends ArrayAdapter<String> {

    private static LayoutInflater mInflater;
    private final Context context;
    int []event_icon;
    int t=-1;
    int pos=0;
    public EventsAdapter(Context context,String [] event_name,int [] event_icon)
    {
        super(context,R.layout.custom_events,event_name);
        this.context=context;
        this.event_icon=event_icon;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        Animation anim =AnimationUtils.loadAnimation(getContext(),R.anim.slide_up);
        String str= getItem(position);
        final Holder holder;

        if(convertView==null)
        {
            mInflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView=mInflater.inflate(R.layout.custom_events,parent,false);
            holder=new Holder();
            holder.category_name=(TextView) convertView.findViewById(R.id.category_name);

            holder.category_icon=(ImageView) convertView.findViewById(R.id.category_icon);
            holder.category_shade=(ImageView) convertView.findViewById(R.id.category_shade);
            convertView.setTag(holder);
        }
        else
        {
            holder=(Holder) convertView.getTag();
        }

        holder.category_name.setText(str);
        holder.category_icon.setImageResource(event_icon[position]);

        pos = position;

        if(t<pos) {
            holder.category_name.startAnimation(anim);
            holder.category_shade.startAnimation(anim);
        }
        t=position;
        return convertView;
    }

    public class Holder
    {
        public TextView category_name;
        public ImageView category_icon;
        public ImageView category_shade;
    }
}
