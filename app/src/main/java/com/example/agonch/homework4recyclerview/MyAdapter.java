package com.example.agonch.homework4recyclerview;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

// example from https://developer.android.com/training/material/lists-cards.html#Dependencies

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyFooHolder> {
    List<String> mDataset;  // this data is displayed using TextView widgets
    View.OnClickListener onClickDeleteListener;

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class MyFooHolder extends RecyclerView.ViewHolder {

        public TextView mTextView;
        public ImageView mImageView;
        public MyFooHolder(View v) {
            super(v);
            mTextView = (TextView) v.findViewById(R.id.line_1_text);
            mImageView = (ImageView) v.findViewById(R.id.trash_can_id);
        }
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public MyAdapter(List<String> myDataset) {

        mDataset = myDataset;
        onClickDeleteListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyFooHolder fooHolder = (MyFooHolder) v.getTag();
                // ^ instead of using findViewById(...)
                int pos = fooHolder.getAdapterPosition(); // returns adapter position
                mDataset.remove(pos);
                notifyItemRemoved(pos);
            }
        };
    }

    // Create new views (invoked by the layout manager)
    @Override
    public MyFooHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // create a new view
        System.out.println("onCreateViewHolder");
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_foo, parent, false);
        return new MyFooHolder(v);
    }

    // Replace the contents of a view (invoked by the layout manager)
    // Updates the ViewHolder to reflect the item at position
    @Override
    public void onBindViewHolder(MyFooHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        System.out.println("onBindViewHolder");
        holder.mTextView.setText(mDataset.get(position));
        holder.mImageView.setTag(holder); // so that later don't have to use findViewById()
        holder.mImageView.setOnClickListener(onClickDeleteListener);
        System.out.println("SET " + holder.mTextView.getText());
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mDataset.size();
    }
}