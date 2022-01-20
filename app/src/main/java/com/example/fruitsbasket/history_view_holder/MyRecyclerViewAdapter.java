package com.example.fruitsbasket.history_view_holder;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.example.fruitsbasket.GamePlay;
import com.example.fruitsbasket.ImageSet;
import com.example.fruitsbasket.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MyRecyclerViewAdapter extends RecyclerView.Adapter<MyRecyclerViewAdapter.ViewHolder> {

    private ArrayList<ImageSet> pieces;
    private LayoutInflater mInflater;
    private ItemClickListener mClickListener;
    private RecyclerViewOnTouchListener touchListener;

    // data is passed into the constructor
    public MyRecyclerViewAdapter(Context context, ArrayList<ImageSet> data) {
        this.mInflater = LayoutInflater.from(context);
        this.pieces = data;
    }


    // inflates the row layout from xml when needed
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.fruit_set_display, parent, false);
        return new ViewHolder(view);
    }

    // binds the data to the ImageViews on each row
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        ImageSet imageViewPiece = pieces.get(position);
        ImageView imageview = holder.itemViewPiece1;
        imageview.setTag(position);

        holder.itemViewPiece1.setImageBitmap(imageViewPiece.getImage1());
        holder.itemViewPiece2.setImageBitmap(imageViewPiece.getImage2());
        holder.itemViewPiece3.setImageBitmap(imageViewPiece.getImage3());
        holder.itemViewPiece4.setImageBitmap(imageViewPiece.getImage4());
        holder.itemHint1.setText(imageViewPiece.getCheck()[0]);
        holder.itemHint1.setText(imageViewPiece.getCheck()[1]);
        holder.itemHint1.setText(imageViewPiece.getCheck()[2]);
        holder.itemHint1.setText(imageViewPiece.getCheck()[3]);
    }

//    public void addImageSet(ViewHolder holder, ImageSet newSet, int position){
//        this.pieces.add(newSet);
//    }

    // total number of rows
    @Override
    public int getItemCount() {
        return pieces.size();
    }

    // stores and recycles views as they are scrolled off screen
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView itemViewPiece1;
        ImageView itemViewPiece2;
        ImageView itemViewPiece3;
        ImageView itemViewPiece4;
        TextView itemHint1;
        TextView itemHint2;
        TextView itemHint3;
        TextView itemHint4;

        ViewHolder(View itemView) {
            super(itemView);
            this.itemViewPiece1 = (ImageView) itemView.findViewById(R.id.fruit1);
            this.itemViewPiece2 = (ImageView) itemView.findViewById(R.id.fruit2);
            this.itemViewPiece3 = (ImageView) itemView.findViewById(R.id.fruit3);
            this.itemViewPiece4 = (ImageView) itemView.findViewById(R.id.fruit4);
            this.itemHint1 = (TextView) itemView.findViewById(R.id.hint1);
            this.itemHint2 = (TextView) itemView.findViewById(R.id.hint2);
            this.itemHint3 = (TextView) itemView.findViewById(R.id.hint3);
            this.itemHint4 = (TextView) itemView.findViewById(R.id.hint4);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (mClickListener != null) mClickListener.onItemClick(view, getAdapterPosition());
        }
    }

    // convenience method for getting data at click position
    public ImageSet getItem(int id) {
        return pieces.get(id);
    }

    // allows clicks events to be caught
    public void setClickListener(ItemClickListener itemClickListener) {
        this.mClickListener = itemClickListener;
    }

    // parent activity will implement this method to respond to click events
    public interface ItemClickListener {
        void onItemClick(View view, int position);
    }
}
