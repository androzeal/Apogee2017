package bitspilani.dvm.apogee2017;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class EventDetail extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_detail);

        Bundle details=getIntent().getExtras();

        TextView eventName=(TextView) findViewById(R.id.eventName);
        TextView eventTime=(TextView) findViewById(R.id.eventTime);
        TextView eventLocation=(TextView) findViewById(R.id.eventLocation);
        TextView eventDetails=(TextView) findViewById(R.id.eventDetails);

        eventName.setText(details.getString("name"));
        eventTime.setText(details.getString("date")+" "+details.getString("time"));
        eventLocation.setText(details.getString("location"));
        eventDetails.setText(details.getString("desc"));
//        ActionBar a=getActionBar();
//        a.setHomeButtonEnabled(true);


    }
}
