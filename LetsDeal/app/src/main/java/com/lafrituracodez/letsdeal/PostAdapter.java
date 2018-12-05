package com.lafrituracodez.letsdeal;


import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class PostAdapter extends
		RecyclerView.Adapter<PostAdapter.PostHolder> {     // LOOK HERE FOR FINAL PROJECT !

	private int bookSelected = -1; // This is so that we can keep track of an item based either on its position or index
	private Context context; // context is well defined here and can be used anywhere, usually for intents.
	private ArrayList<Post> postData; //this is the arraylist that will have the title, info and image.

	public PostAdapter(Context ctx, ArrayList<Post> post) {
		this.context = ctx;
		this.postData = post;
	}

	@NonNull
	@Override
	public PostHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
		return new PostHolder(LayoutInflater
				.from(context)
				.inflate(R.layout.post_list, parent, false));

	}

	@Override
	public void onBindViewHolder(@NonNull PostHolder holder, int position) {
		Post currentBook = postData.get(position);
		holder.bindItem(currentBook);

	}

	@Override
	public int getItemCount() {
		return postData.size();
	}

	public Post getItem(int position) {
		return postData.get(position);
	}

	public class PostHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {
		private TextView textTitle, textInfo, textPrice;
		private ImageView imageViewBook;

		PostHolder(View itemView) {
			super(itemView);
			textInfo = itemView.findViewById(R.id.tv_info);
			textTitle = itemView.findViewById(R.id.tv_title);
			textPrice = itemView.findViewById(R.id.tv_price);
			imageViewBook = itemView.findViewById(R.id.imageView_book);

			// itemView.setOnClickListener(this); //The method that will be implemented here is created below as OnClick()
			//itemView.setOnLongClickListener((View.OnLongClickListener) this); //Find this method as OnLongClick()
		}

		void bindItem(Post currentBook) { //This method puts together the following items
			// so that they can be shown together... so basically, each cardview has its own
			// or you can also understand it by saying that each of this item correspond to each other
			// so they belong in a cardview together.

			// TODO: Upload a default key+file.
			textTitle.setText(currentBook.getTitle());
			textInfo.setText(currentBook.getDesc());
			textPrice.setText(Double.toString(currentBook.getPrice()));

			String current_key = currentBook.getKey();
			Picasso.get().load("https://firebasestorage.googleapis.com/v0/b/lafrituracodez.appspot.com/o/images%2F" + current_key + "?alt=media").into(imageViewBook);

		}

		//listeners on an item if we want to do some magic
		@Override
		public void onClick(View view) { //This is only a normal click
			// this normal click will send the user to the details activity.
			// In the details activity you are suppose to pass in the
			// Title, Description, Image, Calories and a link to an online recipe for the foodie.

			Post currentBook = postData.get(getAdapterPosition());
			// the reason why we are creating currFoodie is because we need some info from it.
			// the currFoodie that will provide this information is the item being clicked because
			// getAdapterPosition is given a value based on the click.

			bookSelected = getLayoutPosition();
			//The reason selectedI is here is because the Index of the image needs to be passed as
			// well because we need it to see which of the elements from the array_list (foodie_details)
			// will show at the Details Activity, so with the index, we can choose it without a problem.

			//Intent intent = new Intent(context, DetailsActivity.class);
			//intent.putExtra("IMAGE_ID", currFoodie.getImageId());
			//intent.putExtra("TITLE", currFoodie.getTitle());
			//intent.putExtra("INFO",currFoodie.getInfo());
			//intent.putExtra("INDEX", String.valueOf(selectedI));


			//context.startActivity(intent); //finally start the intent so we can retrieve the items.
		}

		@Override
		public boolean onLongClick(View v) { // Action: if an image is long clicked, edit Item will be called.
			editItem(); // Look for edit Item to see what is happening there.
			return true;
		}

		public void editItem() { // This is the function that will be called every time an item is
			// long clicked.
			// this method is to create an alert dialog with 2 alert dialog buttons
			// a "Remove" and a "Cancel"

			AlertDialog.Builder edit = new AlertDialog.Builder(context);
			edit.setPositiveButton(
					"Remove", new DialogInterface.OnClickListener() {
						@Override
						public void onClick(DialogInterface dialog, int which) {
							// removeItem(FoodieData.indexOf(FoodieData.get(getAdapterPosition())));
							// the remove item being called is asking for the actual position
							// FoodieData only provides finding an item's index (which is what we need)
							// but you need to let FoodieData know what Item was it that was clicked and
							// you do that by getting the getadapterposition which will provide that info
						}
					});

			edit.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
				@Override
				public void onClick(DialogInterface alertSet, int which) {
					alertSet.dismiss(); // this will dismiss the alert dialog and nothing happens.
				}
			});

			edit.show(); // it is important to show the alert dialog,
			// otherwise long click on the item will do nothing.
		}

		public void removeItem(int position) { //There's nothing that should be said about what is going on here
			// item at the position passed in will be removed and the adapter will be notified of it.
			// FoodieData.remove(position);
			// notifyItemRemoved(position);
		}

	}


}
