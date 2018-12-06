package com.lafrituracodez.letsdeal;


import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Locale;

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

			 itemView.setOnClickListener(this);
//			itemView.setOnLongClickListener((View.OnLongClickListener) this); //Find this method as OnLongClick()
		}

		void bindItem(Post currentBook) {

			// TODO: Upload a default key+file.
			textTitle.setText(currentBook.getTitle());
			textInfo.setText(currentBook.getDesc());
			String price_str = String.format(Locale.US, "$%.2f", currentBook.getPrice());
			textPrice.setText(price_str);

			String current_key = currentBook.getKey();
			Picasso.get().load("https://firebasestorage.googleapis.com/v0/b/lafrituracodez.appspot.com/o/images%2F" + current_key + "?alt=media").into(imageViewBook);

		}

		@Override
		public void onClick(View view) {

			Post currentBook = postData.get(getAdapterPosition());

			Intent intent = new Intent(context, DetailsActivity.class);
			intent.putExtra("title", currentBook.getTitle());
			intent.putExtra("desc", currentBook.getDesc());
			intent.putExtra("price", currentBook.getPrice());
			intent.putExtra("key", currentBook.getKey());

			context.startActivity(intent);
		}

		@Override
		public boolean onLongClick(View v) {
			editItem();
			return true;
		}

		public void editItem() {

			AlertDialog.Builder edit = new AlertDialog.Builder(context);
			edit.setPositiveButton(
					"Remove", new DialogInterface.OnClickListener() {
						@Override
						public void onClick(DialogInterface dialog, int which) {
						}
					});

			edit.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
				@Override
				public void onClick(DialogInterface alertSet, int which) {
					alertSet.dismiss();
				}
			});

			edit.show();
		}
	}


}
