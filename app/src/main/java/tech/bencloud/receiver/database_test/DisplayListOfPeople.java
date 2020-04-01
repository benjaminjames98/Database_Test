package tech.bencloud.receiver.database_test;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class DisplayListOfPeople extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_list_of_people);

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            String[] peopleStringArray = bundle.getStringArray(MainActivity.INTENT_PEOPLE_STRING_ARRAY);
            StringBuilder builder = new StringBuilder();
            for (String s : peopleStringArray)
                builder.append(s).append("\n");

            TextView display_view = findViewById(R.id.display_text);
            display_view.setText(builder.toString());
        }

    }
}
