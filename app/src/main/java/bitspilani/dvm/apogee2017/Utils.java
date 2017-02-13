package bitspilani.dvm.apogee2017;

import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by Vaibhav on 14-02-2017.
 */

public class Utils {
    private static FirebaseDatabase mDatabase;

    public static FirebaseDatabase getDatabase() {
        if (mDatabase == null) {
            mDatabase = FirebaseDatabase.getInstance();
            mDatabase.setPersistenceEnabled(true);
        }
        return mDatabase;
    }

}