package com.lafrituracodez.letsdeal;

import android.content.res.TypedArray;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.bottomappbar.BottomAppBar;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {
	private static ArrayList<Library> libraryListData;
	//private ArrayList<Library> item; // new

	BottomNavigationView navigationView;

	private int selected = -1;
	private RecyclerView recyclerView;

	private LibraryAdapter libraryAdapter;
	private FloatingActionButton addItems;

	// private TextView aRandomfact;
	// private ImageView expandRandom;
	private TextView viewRecents;

	//protected static final String I_AM_HOME= "com.example.I_AM_HOME";
	private int recents_gridColCount = 2;

	public static ArrayList<Library> getVariable() {
		return libraryListData;
	}

	// fix this before moving on

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		recyclerView = findViewById(R.id.recycler_view);

		//recyclerView.setLayoutManager(new GridLayoutManager(this, gridColCount)); // LOOK HERE FOR FINAL PROJECT !

		LinearLayoutManager layoutManager
				= new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);


		//RecyclerView myList = findViewById(R.id.recycler_view);

	        recyclerView.setLayoutManager(layoutManager);

		libraryListData = new ArrayList<>();  // LOOK HERE FOR FINAL PROJECT !
		libraryAdapter = new LibraryAdapter(this, libraryListData);  // LOOK HERE FOR FINAL PROJECT !
		recyclerView.setAdapter(libraryAdapter);  // LOOK HERE FOR FINAL PROJECT !
		loadLibraryData();

		navigationView = findViewById(R.id.navigation);
		navigationView.setOnNavigationItemSelectedListener(this);
		
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

	private void loadLibraryData() {    // LOOK HERE FOR FINAL PROJECT !
		libraryListData.clear();

		TypedArray libraryImages = getResources().obtainTypedArray(R.array.book_images);
		String[] libraryTitles = getResources().getStringArray(R.array.book_names);
		String[] libraryInfos = getResources().getStringArray(R.array.book_description);
		String[] libraryPrices = getResources().getStringArray(R.array.book_prices);

		for (int i = 0; i < libraryImages.length(); i++) {
			Library currentBook = new Library(
					libraryTitles[i],
					libraryInfos[i],
					libraryPrices[i],
					libraryImages.getResourceId(i, 0));

			libraryListData.add(currentBook);

		}
		libraryAdapter.notifyDataSetChanged();
		libraryImages.recycle();

	}

	@Override
	public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
		switch (menuItem.getItemId()) {
			case R.id.action_home:
				sendToast("Home pressed");
				break;
			case R.id.action_trending:
				sendToast("Trending pressed");
				break;
			case R.id.action_search:
				sendToast("Search pressed");
				break;
			case R.id.action_cart:
				sendToast("Cart pressed");
				break;
			case R.id.action_account:
				sendToast("Account pressed");
				break;
		}
		return true;
	}
	private void sendToast(String message){
		Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
	}
}
