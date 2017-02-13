package bitspilani.dvm.apogee2017;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * Created by Nitish on 10/17/2015.
 */
public class CustomFont2 extends TextView {

    public CustomFont2(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    public CustomFont2(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public CustomFont2(Context context) {
        super(context);
        init();
    }

    private void init() {
        Typeface tf = Typeface.createFromAsset(getContext().getAssets(),
                "fonts/lanenar.ttf");
        setTypeface(tf);
    }
}
