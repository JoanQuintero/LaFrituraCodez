package com.lafrituracodez.letsdeal;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import com.google.firebase.database.*;

public class PostActivity extends AppCompatActivity implements ValueEventListener {
	private DatabaseReference database;
	private DatabaseReference post_ref;

	private EditText text1;
	private EditText text2;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_post);
		Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
		setSupportActionBar(toolbar);

		FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
		fab.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
						.setAction("Action", null).show();
			}
		});
		database = FirebaseDatabase.getInstance().getReference();
		post_ref = database.child("users");

		text1 = (EditText)findViewById(R.id.editText_titleInput);
		text2 = (EditText)findViewById(R.id.editText_descInput);

	}

	@Override
	public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
		String value = dataSnapshot.getValue(String.class);
		Toast.makeText(this, value, Toast.LENGTH_SHORT).show();
	}

	@Override
	public void onCancelled(@NonNull DatabaseError databaseError) {
		Toast.makeText(this, "Failed to read value", Toast.LENGTH_SHORT).show();
	}

	public void sendData(View view) {
		Toast.makeText(this, text1.getText().toString() + " " + text2.getText().toString(), Toast.LENGTH_SHORT).show();

		Post post = new Post(text1.getText().toString(), text2.getText().toString(), "Book test", "I am a test");

		database.child("users").child(text1.getText().toString()).setValue(post);



	}

	private void createPost(String title, String description, String user_id, int price){
		Post post  = new Post(title, description, user_id, price);
	}
}
