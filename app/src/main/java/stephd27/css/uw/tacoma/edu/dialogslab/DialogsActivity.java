package stephd27.css.uw.tacoma.edu.dialogslab;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.support.v4.app.NotificationCompat;

public class DialogsActivity extends AppCompatActivity {
    private static final String CHANNEL_ID = "GenNotif";
    private static final int  NOTIF_ID = 120342;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dialogs);
    }

    public void launch(View view) {

        DialogFragment fragment = null;
        if (view.getId() == R.id.btn_fire_missiles) {
            fragment = new FireMissilesDialogFragment();
        } else if (view.getId() == R.id.btn_launch_colors) {
            fragment = new ListDialogFragment();
        } else if (view.getId() == R.id.btn_launch_toppings) {
            fragment = new MultiListDialogFragment();
        }else if (view.getId() == R.id.btn_launch_custom_dialog) {
            fragment = new CustomDialogFragment();
        }
        if (fragment != null)
            fragment.show(getSupportFragmentManager(), "launch");
    }


    public void launchNotif(View view) {
        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(this, CHANNEL_ID)
                .setSmallIcon(android.R.drawable.ic_dialog_info)
                .setContentTitle(getString(R.string.Notif_Title))
                .setContentText(getString(R.string.notif_content))
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                //Normally here you would set an intent to fire when the user clears the notif rn we're not doing that
                .setContentIntent(PendingIntent.getActivity(this, 0, new Intent(), 0))
                .setAutoCancel(true);

        //GET SYSTEM SERVICE IS A LIFE SAVER REMEMBER THIS WHEN YOU IS STUCK
        NotificationManager nManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        //Notification Channel deals with silencing specific notifs from an app
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = getString(R.string.Channel_Name);
            String description = getString(R.string.Channel_description);
            int importance = NotificationManager.IMPORTANCE_DEFAULT;

            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, name, importance);
            channel.setDescription(description);

            nManager.createNotificationChannel(channel);
        }

        nManager.notify(NOTIF_ID, mBuilder.build());
    }



}
