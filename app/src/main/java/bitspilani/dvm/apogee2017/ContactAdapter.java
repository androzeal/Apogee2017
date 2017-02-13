package bitspilani.dvm.apogee2017;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by Nitish on 9/3/2015.
 */
public class ContactAdapter extends ArrayAdapter<String> implements SHARED_CONSTANTS {
    private LayoutInflater mInflater;
    private final Context context;
    int t=0;
    String[] name;
    Bitmap [] pic;

    public ContactAdapter(Context context, String[] item, String[] name, Bitmap [] pic)
    {
        super(context,R.layout.custom_contact, item);
        this.context=context;
        this.name=name;
        this.pic=pic;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        final Holder holder;
        String str=getItem(position);
        final Animation anim= AnimationUtils.loadAnimation(getContext(), R.anim.fade_in);
        if(convertView==null)
        {
            mInflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView=mInflater.inflate(R.layout.custom_contact,parent,false);
            holder = new Holder();
            holder.head=(TextView) convertView.findViewById(R.id.contact_head);
            holder.details=(TextView) convertView.findViewById(R.id.contact_name);
            holder.pic=(ImageView) convertView.findViewById(R.id.contact_pic);
            convertView.setTag(holder);
        }
        else
        {
            holder=(Holder) convertView.getTag();
        }

        holder.head.setText(str);
        holder.details.setText(name[position]);
        holder.pic.setImageBitmap(pic[position]);

        if(t<position) {
            convertView.startAnimation(anim);
        }

        t=position;
        return convertView;
    }

    public class Holder
    {
        public TextView head;
        public TextView details;
        public ImageView pic;
    }
}
