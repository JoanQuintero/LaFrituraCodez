package com.lafrituracodez.letsdeal;

import android.content.res.TypedArray;
import android.os.Bundle;
import android.support.design.bottomappbar.BottomAppBar;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
	private static ArrayList<Library> libraryListData;
	//private ArrayList<Library> item; // new
	BottomAppBar bottomAppBar; //bottom appBar code
	private int selected = -1;
	private RecyclerView recyclerView;
	private LibraryAdapter libraryAdapter;
	private FloatingActionButton addItems; // Will need to go back and use this FAB to add items
	// private TextView aRandomfact;
	// private ImageView expandRandom;

	//protected static final String I_AM_HOME= "com.example.I_AM_HOME";
	private int gridColCount = 1;

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


		RecyclerView myList = findViewById(R.id.recycler_view);

//        myList.setLayoutManager(layoutManager);

		libraryListData = new ArrayList<>();  // LOOK HERE FOR FINAL PROJECT !
		libraryAdapter = new LibraryAdapter(this, libraryListData);  // LOOK HERE FOR FINAL PROJECT !
		myList.setAdapter(libraryAdapter);  // LOOK HERE FOR FINAL PROJECT !
		loadLibraryData();

		bottomAppBar = findViewById(R.id.bottom_appbar); // BottomAppBarCode
		bottomAppBar.replaceMenu(R.menu.bottom_menu); // BottomAppBarCode

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

}
