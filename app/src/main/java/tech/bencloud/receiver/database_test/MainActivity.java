package tech.bencloud.receiver.database_test;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    public static final String INTENT_PEOPLE_STRING_ARRAY = "00000001";

    private List<Person> listOfPeople = new ArrayList<Person>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void handleButtonPress(View v) {
        switch (v.getId()) {
            case R.id.insertRecordButton:
                insertRecord();
                break;
            case R.id.insertDefaultRecordsButton:
                insertDefaultRecords();
                break;
            case R.id.listAllRecordsButton:
                listAllRecords();
                break;
            case R.id.deleteAllRecordsButton:
                deleteAllRecords();
                break;
        }
    }

    private void insertRecord() {
        PersonDataSource dataSource = new PersonDataSource(this);
        dataSource.open();

        EditText nameEditText = findViewById(R.id.nameEditText);
        String name = nameEditText.getText().toString();
        if (name.equals("")) {
            Toast.makeText(this, "Name field cannot be empty!", Toast.LENGTH_SHORT).show();
            return;
        }

        EditText ageEditText = findViewById(R.id.ageEditText);
        String ageString = ageEditText.getText().toString();
        if (ageString.equals("")) {
            Toast.makeText(this, "Age field cannot be empty!", Toast.LENGTH_SHORT).show();
            return;
        }
        int age = Integer.parseInt(ageString);


        dataSource.insert(name, age);

        dataSource.close();
    }

    private void insertDefaultRecords() {
        PersonDataSource dataSource = new PersonDataSource(this);
        dataSource.open();

        dataSource.deleteAllPeople();

        listOfPeople.clear();
        listOfPeople.add(new Person("Al", 38));
        listOfPeople.add(new Person("Bob", 22));
        listOfPeople.add(new Person("Carol", 55));

        for (Person p : listOfPeople)
            dataSource.insert(p.getName(), p.getAge());

        dataSource.close();
    }

    public void listAllRecords() {
        PersonDataSource dataSource = new PersonDataSource(this);
        dataSource.open();

        listOfPeople = dataSource.retrieveAllPeople();

        if (listOfPeople.size() == 0) {
            System.out.println("No people in database!");
        } else {
            for (Person p : listOfPeople) {
                System.out.println(p.toString());
            }
        }

        dataSource.close();
        displayListOfPeople();
    }

    private void displayListOfPeople() {
        String[] peopleStringArray = new String[listOfPeople.size()];

        int i = 0;
        for (Person p : listOfPeople) {
            peopleStringArray[i++] = p.toString();
        }

        Intent intent = new Intent(this, DisplayListOfPeople.class);
        intent.putExtra(INTENT_PEOPLE_STRING_ARRAY, peopleStringArray);
        startActivity(intent);
    }

    private void deleteAllRecords() {
        PersonDataSource dataSource = new PersonDataSource(this);
        dataSource.open();

        dataSource.deleteAllPeople();

        dataSource.close();
    }

}