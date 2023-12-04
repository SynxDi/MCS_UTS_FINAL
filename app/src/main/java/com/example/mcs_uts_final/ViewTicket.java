package com.example.mcs_uts_final;

import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class ViewTicket extends Fragment {

    private RecyclerView recyclerView;
    private TicketAdapter ticketAdapter;
    private TextView tvEmpty;

    private DatabaseTicketHelper dbHelper;

    public ViewTicket() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_view_ticket, container, false);

        recyclerView = view.findViewById(R.id.recyclerView);
        tvEmpty = view.findViewById(R.id.tvEmpty);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        dbHelper = new DatabaseTicketHelper(getContext());
        Button btnClear= view.findViewById(R.id.clearButton);
        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteAllTickets();
                Toast.makeText(getContext(), "Clearing Data!", Toast.LENGTH_SHORT).show();
            }
        });

        // Get all tickets from the database
        Cursor cursor = dbHelper.getAllTickets();

        if (cursor != null && cursor.getCount() > 0) {
            // Data is available, show it in RecyclerView
            recyclerView.setVisibility(View.VISIBLE);
            tvEmpty.setVisibility(View.GONE);

            ticketAdapter = new TicketAdapter(cursor);
            recyclerView.setAdapter(ticketAdapter);
        } else {
            // No data available, show an empty state message
            recyclerView.setVisibility(View.GONE);
            tvEmpty.setVisibility(View.VISIBLE);
        }


        return view;
    }
    private void deleteAllTickets() {
        dbHelper.deleteAllTickets();
    }
}
