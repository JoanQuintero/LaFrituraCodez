package com.lafrituracodez.letsdeal;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.*;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;

public class PostActivity extends AppCompatActivity implements ValueEventListener {

	private static final int REQUEST_IMAGE_CAPTURE = 1;

	private DatabaseReference database;
	private StorageReference storageRef;
	private GoogleSignInAccount account;
	private ImageView image;
	private Bitmap bitmap;

	private Integer notificationID = 100;
	private boolean imageSet = false;
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
		storageRef = FirebaseStorage.getInstance().getReference();
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

	public boolean sendData(View view) {
		final String u_id;
		final String key;

		String author = author_input.getText().toString();
		String title = title_input.getText().toString();
		String desc = desc_input.getText().toString();


		try {
			u_id = account.getId();
			key = database.child("posts").push().getKey();
		} catch (NullPointerException e) {
			e.printStackTrace();
			sendToast("Something went wrong. Sorry!");
			return true;
		}
		Double price;
		try {
			price = Double.parseDouble(price_input.getText().toString());
		} catch (NumberFormatException e) {
			e.printStackTrace();
			price = 0.0;
		}
		Post post = new Post(u_id, author, title, desc, price);

		uploadImage(bitmap);
		database.child("posts")
				.child(key).setValue(post)
				.addOnSuccessListener(new OnSuccessListener<Void>() {
					@Override
					public void onSuccess(Void aVoid) {
						UserPost userPost = new UserPost(u_id, key);
						database.child("user-posts/").push().setValue(userPost);
					}
				})
				.addOnFailureListener(new OnFailureListener() {
					@Override
					public void onFailure(@NonNull Exception e) {
						Toast.makeText(getBaseContext(), "Post Failed. Check values and retry!", Toast.LENGTH_SHORT).show();
					}
				});
		return true;
	}


	private void sendToast(String message) {
		Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
	}

	public void createIntent(View view) {
		Intent pickIntent = new Intent();
		pickIntent.setType("image/*");
		pickIntent.setAction(Intent.ACTION_GET_CONTENT);

		Intent takePhotoIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

		String pickTitle = "Select or take a new Picture"; // Or get from strings.xml
		Intent chooserIntent = Intent.createChooser(pickIntent, pickTitle);
		chooserIntent.putExtra
				(
						Intent.EXTRA_INITIAL_INTENTS,
						new Intent[]{takePhotoIntent}
				);

		startActivityForResult(chooserIntent, REQUEST_IMAGE_CAPTURE);
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
			imageSet = true;
			Uri selectedImage = data.getData();
			try {
				bitmap = BitmapFactory.decodeStream(getContentResolver().openInputStream(selectedImage));
				image = findViewById(R.id.imageView_coverPreview);
				image.setImageBitmap(bitmap);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
				sendToast("There was an error when retrieveing image.");
			}
		}
	}

	private boolean uploadImage(Bitmap bitmap) {
		if (!imageSet) {
			sendToast("Choose an image to upload");
			return true;
		}
		final NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this);
		final NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(this, "CHANNEL 9");
		mBuilder.setContentTitle("Picture Download")
				.setContentText("Download in progress")
				.setSmallIcon(R.drawable.ic_upload)
				.setPriority(NotificationCompat.PRIORITY_LOW);

		int PROGRESS_MAX = 100;
		int PROGRESS_CURRENT = 0;
		mBuilder.setProgress(PROGRESS_MAX, PROGRESS_CURRENT, false);
		notificationManager.notify(notificationID, mBuilder.build());

		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
		byte[] data = baos.toByteArray();

		StorageReference imagesRef = storageRef.child("images");
		UploadTask uploadTask = imagesRef.putBytes(data);
		uploadTask
				.addOnFailureListener(new OnFailureListener() {
					@Override
					public void onFailure(Exception e) {

					}
				}).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
			@Override
			public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
				mBuilder.setContentText("Download complete")
						.setProgress(0, 0, false);
				notificationManager.notify(notificationID, mBuilder.build());
				finish();
			}
		}).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
			@Override
			public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
				double progress = (100.0 * taskSnapshot.getBytesTransferred()) / taskSnapshot.getTotalByteCount();
				mBuilder.setProgress(100, (int) progress, false);
				notificationManager.notify(notificationID, mBuilder.build());
				sendToast("Post created.");
			}
		});
		return true;
	}

}
