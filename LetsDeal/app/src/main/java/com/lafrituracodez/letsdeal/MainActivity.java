package com.lafrituracodez.letsdeal;

import android.content.Intent;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.AppCompatDelegate;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.firebase.database.*;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, ChildEventListener {


	private final static String TAG = "MainActivity";

	private static ArrayList<Post> postData;

	private GoogleSignInAccount account;
	private DatabaseReference mDatabase;
	private RecyclerView recyclerView;

	private PostAdapter postAdapter;

	private TextView viewRecents;

	private int recents_gridColCount = 2;

	// fix this before moving on

	@Override
	protected void onStart() {
		super.onStart();
		if (GoogleSignIn.getLastSignedInAccount(this) == null) {
			sendToLogin();
		} else {
			updateUI();
		}
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM);
		setContentView(R.layout.activity_drawer);
		Toolbar toolbar = findViewById(R.id.toolbar);
		setSupportActionBar(toolbar);

		DrawerLayout drawer = findViewById(R.id.drawer_layout);
		ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
				this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
		drawer.addDrawerListener(toggle);
		toggle.syncState();

		NavigationView navigationView = findViewById(R.id.nav_view);
		navigationView.setNavigationItemSelectedListener(this);

		recyclerView = findViewById(R.id.recycler_view);

		//recyclerView.setLayoutManager(new GridLayoutManager(this, gridColCount)); // LOOK HERE FOR FINAL PROJECT !

		LinearLayoutManager layoutManager
				= new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);


		recyclerView.setLayoutManager(layoutManager);

		postData = new ArrayList<>();  // LOOK HERE FOR FINAL PROJECT !
		postAdapter = new PostAdapter(this, postData);  // LOOK HERE FOR FINAL PROJECT !
		recyclerView.setAdapter(postAdapter);  // LOOK HERE FOR FINAL PROJECT !
		loadLibraryData();

		mDatabase = FirebaseDatabase.getInstance().getReference().child("posts");
		mDatabase.addChildEventListener(this);

		viewRecents = findViewById(R.id.recentPost_viewall);
		viewRecents.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				recyclerView.getRecycledViewPool().clear();
				recyclerView.setLayoutManager(
						new GridLayoutManager(getApplicationContext(), recents_gridColCount));
			}
		});
	}

	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		account = GoogleSignIn.getLastSignedInAccount(this);
		sendToast("Welcome " + (account != null ? account.getDisplayName() : "!"));
	}

	@Override
	public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
		// Handle navigation view item clicks here.
		Intent intent;
		switch (menuItem.getItemId()) {
			case R.id.nav_account:
				intent = new Intent(this, AccountActivity.class);
				startActivity(intent);
				break;
			case R.id.nav_add:
				intent = new Intent(this, PostActivity.class);
				startActivity(intent);
				break;
			case R.id.nav_search:
				intent = new Intent(this, PostActivity.class);
				startActivity(intent);
				break;
		}
		DrawerLayout drawer = findViewById(R.id.drawer_layout);
		drawer.closeDrawer(GravityCompat.START);
		return true;

	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}

		return super.onOptionsItemSelected(item);
	}

	@Override
	public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
		Log.d(TAG, "onChildAdded:" + dataSnapshot.getKey());
		Post post = dataSnapshot.getValue(Post.class);
		postData.add(post);
		postAdapter.notifyItemChanged(postData.size());

	}

	@Override
	public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

	}

	@Override
	public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

	}

	@Override
	public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

	}

	@Override
	public void onCancelled(@NonNull DatabaseError databaseError) {

	}

	private void loadLibraryData() {    // LOOK HERE FOR FINAL PROJECT !
		postData.clear();

		TypedArray libraryImages = getResources().obtainTypedArray(R.array.book_images);
		String[] libraryTitles = getResources().getStringArray(R.array.book_names);
		String[] libraryDesc = getResources().getStringArray(R.array.book_description);
		String[] libraryPrices = getResources().getStringArray(R.array.book_prices);
		// Post constructor: (String uid, String author, String title, String desc, double price, int resource) {

		for (int i = 0; i < libraryImages.length(); i++) {
			Post currentPost = new Post(
					"12",
					"John Doe",
					libraryTitles[i],
					libraryDesc[i],
					Double.parseDouble(libraryPrices[i]),
					libraryImages.getResourceId(i, 0)
			);

			postData.add(currentPost);

		}
		postAdapter.notifyDataSetChanged();
		libraryImages.recycle();

	}

	private void sendToast(String message) {
		Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
	}

	private void sendToLogin() {
		Intent intent = new Intent(this, LoginActivity.class);
		startActivityForResult(intent, LoginActivity.RC_SUCCESS_SIGN_IN);
	}

	public void sendToPost(View v) {
		Intent intent = new Intent(this, PostActivity.class);
		startActivity(intent);
	}

	private void updateUI() {
		GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(this);

		NavigationView navigationView = findViewById(R.id.nav_view);
		View headerView = navigationView.getHeaderView(0);

		TextView userName = headerView.findViewById(R.id.textView_userName);
		TextView userProfile = headerView.findViewById(R.id.textView_userEmail);

		userName.setText(account != null ? account.getDisplayName() : "!");
		userProfile.setText(account.getEmail());

	}

}
