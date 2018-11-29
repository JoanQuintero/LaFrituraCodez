package com.lafrituracodez.letsdeal;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.*;

import java.util.Random;

public class PostActivity extends AppCompatActivity implements ValueEventListener {

	private DatabaseReference database;
	private GoogleSignInAccount account;

	private TextInputEditText title_input;
	private TextInputEditText author_input;
	private TextInputEditText desc_input;
	private TextInputEditText price_input;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_post);
		Toolbar toolbar = findViewById(R.id.toolbar);
		setSupportActionBar(toolbar);

		database = FirebaseDatabase.getInstance().getReference();
		account = GoogleSignIn.getLastSignedInAccount(this);

		title_input = findViewById(R.id.editText_titleInput);
		desc_input = findViewById(R.id.editText_descInput);
		author_input = findViewById(R.id.editText_authorInput);
		price_input = findViewById(R.id.editText_priceInput);

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
		Random r = new Random();

		// TODO: Replace by actual values after Login has been integrated.
		final int post_id = r.nextInt(100);

		String author = author_input.getText().toString();
		String title = title_input.getText().toString();
		String desc = desc_input.getText().toString();
		final String u_id = account.getId();

		Double price;
		try {
			price = Double.parseDouble(price_input.getText().toString());
		} catch (NumberFormatException e) {
			e.printStackTrace();
			price = 0.0;
		}
		Post post = new Post(Integer.toString(post_id), author, title, desc, price);

		database.child("posts").push()
				.setValue(post)
				.addOnSuccessListener(new OnSuccessListener<Void>() {
					@Override
					public void onSuccess(Void aVoid) {
						Toast.makeText(getBaseContext(), "Post successful", Toast.LENGTH_SHORT).show();
						UserPost userPost = new UserPost(u_id, Integer.toString(post_id));
						database.child("user-posts/" + u_id).push().setValue(userPost);
					}
				})
				.addOnFailureListener(new OnFailureListener() {
					@Override
					public void onFailure(@NonNull Exception e) {
						Toast.makeText(getBaseContext(), "Post Failed. Check values and retry!", Toast.LENGTH_SHORT).show();
					}
				});
	}


	private void sendToast(String message) {
		Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
	}

}
