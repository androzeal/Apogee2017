package bitspilani.dvm.apogee2017;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by Nitish on 10/19/2015.
 */
public class ProfAdapter extends ArrayAdapter<String> {

    private LayoutInflater mInflater;
    private final Context context;
    Bitmap[] ProfPic;
    String [] prof_work;

    public ProfAdapter(Context context, String[] item,String[] prof_work, Bitmap[] ProfPic)
    {
        super(context,R.layout.custom_prof, item);
        this.context=context;
        this.ProfPic=ProfPic;
        this.prof_work=prof_work;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        String str= getItem(position);
        final Holder holder;

        if(convertView==null)
        {
            mInflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView=mInflater.inflate(R.layout.custom_prof,parent,false);
            holder = new Holder();
            holder.profName=(TextView) convertView.findViewById(R.id.profName);
            holder.prof_work=(TextView) convertView.findViewById(R.id.prof_work);
            holder.ProfPicture=(ImageView) convertView.findViewById(R.id.ProfPicture);
            convertView.setTag(holder);
        }
        else
        {
            holder=(Holder) convertView.getTag();
        }

        holder.profName.setText(str);
        if(!(prof_work==null))
        {
            holder.prof_work.setText(prof_work[position]);
        }
        else
        {
            holder.prof_work.setText(" ");
        }

        holder.ProfPicture.setImageBitmap(ProfPic[position]);

        return convertView;
    }
    public class Holder {
        public TextView profName;
        public TextView prof_work;
        public ImageView ProfPicture;
    }
}
