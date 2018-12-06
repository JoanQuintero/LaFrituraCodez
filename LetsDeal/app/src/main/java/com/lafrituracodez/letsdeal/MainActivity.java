package com.lafrituracodez.letsdeal;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
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

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, ChildEventListener, View.OnClickListener {

	private final static String TAG = "MainActivity";

	private static ArrayList<Post> recentPostData;
	private static ArrayList<Post> userPostData;

	private GoogleSignInAccount account;
	private RecyclerView recyclerView3;
	private Boolean gridLayoutEnabled = false;

	private DatabaseReference mDatabase;
	private LinearLayoutManager linearLayoutManager;
	private GridLayoutManager gridLayoutManager;

	private PostAdapter recentPostAdapter;
	private PostAdapter userPostAdapter;

	private TextView viewRecents;
	private RecyclerView recyclerView_RecentPosts;
	private RecyclerView recyclerView_UserPosts;
	private NavigationView navigationView;

	private int recents_gridColCount = 2;

	@Override
	protected void onStart() {
		super.onStart();
		assertLogin();
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM);
		setContentView(R.layout.activity_drawer);
		Toolbar toolbar = findViewById(R.id.toolbar);
		setSupportActionBar(toolbar);

		account = GoogleSignIn.getLastSignedInAccount(this);
		assertLogin();
		updateUI();

		DrawerLayout drawer = findViewById(R.id.drawer_layout);
		ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
				this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
		drawer.addDrawerListener(toggle);
		toggle.syncState();

		NavigationView navigationView = findViewById(R.id.nav_view);
		navigationView.setNavigationItemSelectedListener(this);

		recyclerView_RecentPosts = findViewById(R.id.recyclerView_recentPost);

		linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
		gridLayoutManager = new GridLayoutManager(this, recents_gridColCount);

		recyclerView_RecentPosts.setLayoutManager(linearLayoutManager);
		recentPostData = new ArrayList<>();
		recentPostAdapter = new PostAdapter(this, recentPostData);
		recyclerView_RecentPosts.setAdapter(recentPostAdapter);

		LinearLayoutManager las
				= new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);

		recyclerView_UserPosts = findViewById(R.id.recyclerView_UserPosts);
		recyclerView_UserPosts.setLayoutManager(las);
		userPostData = new ArrayList<>();
		userPostAdapter = new PostAdapter(this, userPostData);
		recyclerView_UserPosts.setAdapter(userPostAdapter);

		mDatabase = FirebaseDatabase.getInstance().getReference().child("posts");
		mDatabase.addChildEventListener(this);

		viewRecents = findViewById(R.id.textView_recentPostHelper);
		viewRecents.setOnClickListener(this);
	}

	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (account == null) {
			sendToLogin();
		}
		account = GoogleSignIn.getLastSignedInAccount(this);
		sendToast("Welcome " + (account != null ? account.getDisplayName() : "!"));

	}

	@Override
	public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
		Intent intent;
		switch (menuItem.getItemId()) {
			case R.id.nav_home:
				break;
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
		return false;
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
		post.setKey(dataSnapshot.getKey());

		recentPostData.add(post);
		recentPostAdapter.notifyItemChanged(recentPostData.size());

		if (post.getUid().equals(account.getId())) {
			userPostData.add(post);
			userPostAdapter.notifyDataSetChanged();
		}

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

	private void sendToast(String message) {
		Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
	}

	private void assertLogin() {
		if (GoogleSignIn.getLastSignedInAccount(this) == null) {
			sendToLogin();
		}
	}

	private void sendToLogin() {
		Intent intent = new Intent(this, LoginActivity.class);
		startActivityForResult(intent, LoginActivity.RC_SUCCESS_SIGN_IN);
	}

	private void updateUI() {
		navigationView = findViewById(R.id.nav_view);
		View headerView = navigationView.getHeaderView(0);

		TextView userName = headerView.findViewById(R.id.textView_userName);
		TextView userProfile = headerView.findViewById(R.id.textView_userEmail);

		userName.setText(account != null ? account.getDisplayName() : "!");
		userProfile.setText(account != null ? account.getEmail() : "!");
	}

	@Override
	public void onBackPressed() {
		new AlertDialog.Builder(this)
				.setTitle("Exiting App")
				.setMessage("Are you sure you want to exit?")
				.setNegativeButton(android.R.string.no, null)
				.setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {

					public void onClick(DialogInterface arg0, int arg1) {
						MainActivity.super.onBackPressed();
						finish();
					}
				}).create().show();
	}

	@Override
	public void onClick(View v) {
		if (v == viewRecents) {
			if (!gridLayoutEnabled) {
				recyclerView_RecentPosts.setLayoutManager(gridLayoutManager);
				viewRecents.setText("Go Back");
			} else {
				recyclerView_RecentPosts.setLayoutManager(linearLayoutManager);
				viewRecents.setText("See all");
			}
			gridLayoutEnabled = !(gridLayoutEnabled);
		}
	}
}
