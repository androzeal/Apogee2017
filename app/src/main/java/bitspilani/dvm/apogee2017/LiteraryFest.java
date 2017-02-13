package bitspilani.dvm.apogee2017;


import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Path;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.wunderlist.slidinglayer.SlidingLayer;


/**
 * A simple {@link Fragment} subclass.
 */
public class LiteraryFest extends Fragment implements SHARED_CONSTANTS  {

    SlidingLayer mSlidingLayer;
    Bitmap[] drw;
    ListView profList;
    TextView slider_name;
    TextView slider_work;
    TextView slider_description;
    ImageView slider_pic;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_think_again, container, false);

        profList=(ListView) view.findViewById(R.id.ProfList);
        slider_name=(TextView) getActivity().findViewById(R.id.slider_name);
        slider_work=(TextView) getActivity().findViewById(R.id.slider_work);
        slider_description=(TextView) getActivity().findViewById(R.id.slider_description);
        slider_pic=(ImageView) getActivity().findViewById(R.id.slider_pic);
        drw=new Bitmap[LITERARY_SPEAKER_PIC.length];
        mSlidingLayer = (SlidingLayer) getActivity().findViewById(R.id.slidingLayer2);
        mSlidingLayer.setChangeStateOnTap(true);
        for(int i=0;i<LITERARY_SPEAKER_PIC.length;i++)
        {
            Bitmap pics= BitmapFactory.decodeResource(getResources(), LITERARY_SPEAKER_PIC[i]);
            pics=getRoundedShape(pics);
            drw[i]=pics;
        }


        profList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                slider_pic.setImageBitmap(drw[position]);
                slider_name.setText(LITERARY_SPEAKER_NAME[position]);
                slider_work.setText("  ");
                slider_description.setText(LITERARY_SPEAKER_DESCRIPTION[position]);
                mSlidingLayer.openLayer(true);
            }
        });
        ListAdapter custom = new ProfAdapter(getActivity(),LITERARY_SPEAKER_NAME,null,drw);
        profList.setAdapter(custom);


        return view;
    }
    public Bitmap getRoundedShape(Bitmap scaleBitmapImage) {
        int targetWidth = scaleBitmapImage.getWidth();
        int targetHeight = scaleBitmapImage.getHeight();
        Bitmap targetBitmap = Bitmap.createBitmap(targetWidth,
                targetHeight, Bitmap.Config.ARGB_8888);

        Canvas canvas = new Canvas(targetBitmap);
        Path path = new Path();
        path.addCircle(((float) targetWidth - 1) / 2,
                ((float) targetHeight - 1) / 2,
                (Math.min(((float) targetWidth),
                        ((float) targetHeight)) / 2),
                Path.Direction.CCW);

        canvas.clipPath(path);
        Bitmap sourceBitmap = scaleBitmapImage;
        canvas.drawBitmap(sourceBitmap,
                new Rect(0, 0, sourceBitmap.getWidth(),
                        sourceBitmap.getHeight()),
                new Rect(0, 0, targetWidth, targetHeight), null);
        return targetBitmap;
    }
}
