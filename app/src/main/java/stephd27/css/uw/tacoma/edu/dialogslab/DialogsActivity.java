package stephd27.css.uw.tacoma.edu.dialogslab;

import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class DialogsActivity extends AppCompatActivity {

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



}
