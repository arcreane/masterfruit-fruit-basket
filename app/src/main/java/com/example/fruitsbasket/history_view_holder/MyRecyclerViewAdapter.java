package com.example.fruitsbasket.history_view_holder;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.fruitsbasket.R;

import java.util.ArrayList;
import java.util.List;

public class MyRecyclerViewAdapter extends RecyclerView.Adapter<MyRecyclerViewAdapter.ViewHolder> {

    private List<String> mData;
    private ArrayList<Bitmap> pieces;
    private LayoutInflater mInflater;
    private ItemClickListener mClickListener;
    private RecyclerViewOnTouchListener touchListener;

    // data is passed into the constructor
    public MyRecyclerViewAdapter(Context context, ArrayList<Bitmap> data) {
        this.mInflater = LayoutInflater.from(context);
        //this.mData = data;
        this.pieces = data;
    }


    // inflates the row layout from xml when needed
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
//        View view = mInflater.inflate(R.layout.recycler_main, parent, false);
        View view = mInflater.inflate(R.layout.recycler_main2, parent, false);
//        View view;
//        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_main2);
        return new ViewHolder(view);
    }

    // binds the data to the TextView in each row
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
//        String animal = mData.get(position);
//        holder.myTextView.setText(animal);
        Bitmap imageViewPiece = pieces.get(position);
        ImageView imageview = holder.itemViewPiece;
        imageview.setTag(position);
        imageview.setImageBitmap(imageViewPiece);

    }

    // total number of rows
    @Override
    public int getItemCount() {
        return pieces.size();//mData.size();
    }

    // stores and recycles views as they are scrolled off screen
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView myTextView;
        ImageView itemViewPiece;

        ViewHolder(View itemView) {
            super(itemView);
            this.itemViewPiece = (ImageView) itemView.findViewById(R.id.fruit_img1);

//            myTextView = itemView.findViewById(R.id.fruit_text);
            itemView.setOnClickListener(this);
        }
//        public MyAdapter(ArrayList<Bitmap> pieces, RecyclerViewOnTouchListener touchListener){
//            this.pieces = pieces;
//            this.touchListener = touchListener;
//        }

        @Override
        public void onClick(View view) {
            if (mClickListener != null) mClickListener.onItemClick(view, getAdapterPosition());
        }
    }

    // convenience method for getting data at click position
    public Bitmap getItem(int id) {
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
