package bitspilani.dvm.apogee2017;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Path;
import android.graphics.Rect;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class ContactUs extends Fragment implements SHARED_CONSTANTS {

    Bitmap[] drw;
    ImageView callImage;
    ImageView call;
    ImageView mail;

    CoordinatorLayout coordinatorLayout;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.activity_contact_us, container, false);

        coordinatorLayout=(CoordinatorLayout)getActivity().findViewById(R.id.coordinatorLayout);

        drw=new Bitmap[CONTACT_PIC.length];
        for(int i=0;i<CONTACT_PIC.length;i++)
        {
            Bitmap pics= BitmapFactory.decodeResource(getResources(), CONTACT_PIC[i]);
            pics=getRoundedShape(pics);
            drw[i]=pics;
        }

        ListView contactList=(ListView) view.findViewById(R.id.contactList);
        ListAdapter custom= new ContactAdapter(getActivity() ,CONTACT_HEAD,CONTACT_NAME,drw);
        contactList.setAdapter(custom);

//        Toast.makeText(getActivity(),"Click on a person to call or send an email to him", Toast.LENGTH_LONG).show();

        Snackbar.make(coordinatorLayout, "Click on a person to call or send an email to him", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show();

        final Dialog dialog = new Dialog(getActivity());

//        dialog.setTitle("Contact");



        contactList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {

                dialog.setContentView(R.layout.contact_layout);
                dialog.getWindow().setGravity(Gravity.BOTTOM);
                dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
                callImage=(ImageView) dialog.findViewById(R.id.callImage);
                call=(ImageView) dialog.findViewById(R.id.call);
                mail=(ImageView) dialog.findViewById(R.id.mail);

                callImage.setImageBitmap(drw[position]);
                call.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent call = new Intent(Intent.ACTION_DIAL);
                        call.setData(Uri.parse("tel:" + CONTACT_NUMBER[position]));
                        startActivity(call);
                    }
                });

                mail.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent mail = new Intent(Intent.ACTION_VIEW);
                        mail.setType("plain/text");
                        mail.setClassName("com.google.android.gm", "com.google.android.gm.ComposeActivityGmail");
                        mail.putExtra(Intent.EXTRA_EMAIL, new String[]{CONTACT_EMAIL[position]});
                        mail.putExtra(Intent.EXTRA_SUBJECT, "");
                        mail.putExtra(Intent.EXTRA_TEXT, "\n\n\n\n\n\n\n\nSent from the official APOGEE 2016 App");

                        try {
                            startActivity(Intent.createChooser(mail, "Send mail...(Preferably GMail) ;)"));
                        } catch (android.content.ActivityNotFoundException ex) {
                            Toast.makeText(getActivity(), "There are no email clients installed.", Toast.LENGTH_SHORT).show();
                        }

                    }
                });
                dialog.show();

//                dialog.setMessage("How would you like to contact " + CONTACT_NAME[position]);
//
//                dialog.setButton2("Call", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                        Intent call = new Intent(Intent.ACTION_DIAL);
//                        call.setData(Uri.parse("tel:" + CONTACT_NUMBER[position]));
//                        startActivity(call);
//                    }
//                });
//
//                dialog.setButton("Email", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                        Intent mail = new Intent(Intent.ACTION_VIEW);
//                        mail.setType("plain/text");
//                        mail.setClassName("com.google.android.gm", "com.google.android.gm.ComposeActivityGmail");
//                        mail.putExtra(Intent.EXTRA_EMAIL, new String[]{CONTACT_EMAIL[position]});
//                        mail.putExtra(Intent.EXTRA_SUBJECT, "");
//                        mail.putExtra(Intent.EXTRA_TEXT, "\n\n\n\n\n\n\n\nSent from the official APOGEE 2016 App");
//
//                        try {
//                            startActivity(Intent.createChooser(mail, "Send mail...(Preferably GMail) ;)"));
//                        } catch (android.content.ActivityNotFoundException ex) {
//                            Toast.makeText(getActivity(), "There are no email clients installed.", Toast.LENGTH_SHORT).show();
//                        }
//                    }
//                });


            }
        });
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
