

package com.example.mcs_uts_final;

import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class TicketAdapter extends RecyclerView.Adapter<TicketAdapter.TicketViewHolder> {

    private Cursor cursor;

    public TicketAdapter(Cursor cursor) {
        this.cursor = cursor;
    }

    @NonNull
    @Override
    public TicketViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Inflate layout for each item in RecyclerView
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_ticket, parent, false);
        return new TicketViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TicketViewHolder holder, int position) {
        // Move the cursor to the correct position
        if (!cursor.moveToPosition(position)) {
            return;
        }

        // Get column indices for nama and quantity
        int namaIndex = cursor.getColumnIndex(DatabaseTicketHelper.COLUMN_NAMA);
        int quantityIndex = cursor.getColumnIndex(DatabaseTicketHelper.COLUMN_QUANTITY);

        // Check if column indices are valid
        if (namaIndex != -1 && quantityIndex != -1) {
            // Retrieve data from the cursor
            String nama = cursor.getString(namaIndex);
            int quantity = cursor.getInt(quantityIndex);

            // Set the actual data to TextViews
            holder.tvNama.setText(nama);
            holder.tvQuantity.setText(String.valueOf(quantity));
        }
    }

    @Override
    public int getItemCount() {
        // Return the total number of items in the cursor
        return cursor.getCount();
    }

    // ViewHolder class to hold the views for each item
    static class TicketViewHolder extends RecyclerView.ViewHolder {
        TextView tvNama, tvQuantity;

        public TicketViewHolder(@NonNull View itemView) {
            super(itemView);

            // Initialize TextViews from the layout
            tvNama = itemView.findViewById(R.id.tvNama);
            tvQuantity = itemView.findViewById(R.id.tvQuantity);
        }
    }
}
