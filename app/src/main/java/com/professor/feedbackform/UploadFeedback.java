package com.professor.feedbackform;

import android.database.SQLException;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

public class UploadFeedback extends AppCompatActivity {

    Spinner spinner;
    EditText commentEdit, subjectEdit;
    Button submitButton;
    RadioGroup qOneGroup, qTwoGroup, qThreeGroup, qFourGroup, qFiveGroup, qSixGroup, qSevenGroup, qEightGroup, qNineGroup, qTenGroup;
    RadioButton qOneButton, qTwoButton, qThreeButton, qFourButton, qFiveButton, qSixButton, qSevenButton, qEightButton, qNineButton, qTenButton;
    DBadapterComment dbc;

    String getusername, statusString, ansOne, ansTwo, ansThree, ansFour, ansFive, ansSix, ansSeven, ansEight, ansNine, ansTen;
    ArrayList<String> ratingArrayList = new ArrayList<>();

   /* String[] statusArray={"Good","Average","Bad","Communication skills","Doubt clearance","Usage of board","Impartial",
            "Punctuality","Subject knowledge"};*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_upload_feedback);

        dbc = new DBadapterComment(UploadFeedback.this);

        getusername = getIntent().getStringExtra("fusername");

       /* spinner = (Spinner) findViewById(R.id.status);

        ArrayAdapter<String> sAdapter=new ArrayAdapter<String>(UploadFeedback.this,android.R.layout.simple_list_item_1,statusArray);
        spinner.setAdapter(sAdapter);*/

       /* spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                statusString = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });*/


        commentEdit = findViewById(R.id.comment);
        subjectEdit = findViewById(R.id.subject);
        qOneGroup = findViewById(R.id.rgOne);
        qTwoGroup = findViewById(R.id.rgTwo);
        qThreeGroup = findViewById(R.id.rgThree);
        qFourGroup = findViewById(R.id.rgFour);
        qFiveGroup = findViewById(R.id.rgFive);
        qSixGroup = findViewById(R.id.rgSix);
        qSevenGroup = findViewById(R.id.rgSeven);
        qEightGroup = findViewById(R.id.rgEight);
        qNineGroup = findViewById(R.id.rgNine);
        qTenGroup = findViewById(R.id.rgTen);

        submitButton = findViewById(R.id.submit);

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                    String commentString = commentEdit.getText().toString();
                    String subjectString = subjectEdit.getText().toString();

                    int qOneId = qOneGroup.getCheckedRadioButtonId();
                    int qTwoId = qTwoGroup.getCheckedRadioButtonId();
                    int qThreeId = qThreeGroup.getCheckedRadioButtonId();
                    int qFourId = qFourGroup.getCheckedRadioButtonId();
                    int qFiveId = qFiveGroup.getCheckedRadioButtonId();
                    int qSixId = qSixGroup.getCheckedRadioButtonId();
                    int qSevenId = qSevenGroup.getCheckedRadioButtonId();
                    int qEightId = qEightGroup.getCheckedRadioButtonId();
                    int qNineId = qNineGroup.getCheckedRadioButtonId();
                    int qTenId = qTenGroup.getCheckedRadioButtonId();

                    // find the radiobutton by returned id
                    qOneButton = findViewById(qOneId);
                    qTwoButton = findViewById(qTwoId);
                    qThreeButton = findViewById(qThreeId);
                    qFourButton = findViewById(qFourId);
                    qFiveButton = findViewById(qFiveId);
                    qSixButton = findViewById(qSixId);
                    qSevenButton = findViewById(qSevenId);
                    qEightButton = findViewById(qEightId);
                    qNineButton = findViewById(qNineId);
                    qTenButton = findViewById(qTenId);

                    ratingArrayList.add("Passion and enthusiasm to teach");
                    ratingArrayList.add("Rating: " + qOneButton.getText().toString());
                    ratingArrayList.add("Subject knowledge");
                    ratingArrayList.add("Rating: " + qTwoButton.getText().toString());
                    ratingArrayList.add("Clarity and emphasis on concept");
                    ratingArrayList.add("Rating: " + qThreeButton.getText().toString());
                    ratingArrayList.add("Motivation and inspiring the student");
                    ratingArrayList.add("Rating: " + qFourButton.getText().toString());
                    ratingArrayList.add("Creating interest in subject");
                    ratingArrayList.add("Rating: " + qFiveButton.getText().toString());
                    ratingArrayList.add("Quality of illustrative examples");
                    ratingArrayList.add("Rating: " + qSixButton.getText().toString());
                    ratingArrayList.add("Regularity, punctuality and uniform coverage of syllabus");
                    ratingArrayList.add("Rating: " + qSevenButton.getText().toString());
                    ratingArrayList.add("Discipline and control over the class");
                    ratingArrayList.add("Rating: " + qEightButton.getText().toString());
                    ratingArrayList.add("Promoting student thinking");
                    ratingArrayList.add("Rating: " + qNineButton.getText().toString());
                    ratingArrayList.add("Encouraging and student interaction");
                    ratingArrayList.add("Rating: " + qTenButton.getText().toString());

                    if (subjectString.length() > 0 && commentString.length() > 0 && ratingArrayList.size() > 9) {
                        dbc.open();
                        long id = dbc.insertcomment(getusername, "No status", subjectString, "" + ratingArrayList, commentString);
                        dbc.close();


                        if (id > 0) {
                            Toast.makeText(UploadFeedback.this, "Comment uploaded successfully", Toast.LENGTH_LONG).show();
                        }
                    } else {
                        Toast.makeText(UploadFeedback.this, "Please verify all the details", Toast.LENGTH_LONG).show();
                    }

            }

        });

    }

    @Override
    protected void onStart() {
        super.onStart();
        ratingArrayList.clear();
    }
}
