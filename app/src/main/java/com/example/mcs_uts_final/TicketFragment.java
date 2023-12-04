package com.example.mcs_uts_final;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

public class TicketFragment extends Fragment {

    private EditText etNama, etNoTelepon, etQuantity;
    private Button btnBuyTicket;

    private DatabaseTicketHelper dbHelper;
    private SQLiteDatabase database;

    public TicketFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_ticket, container, false);

        // Inisialisasi elemen UI
        etNama = view.findViewById(R.id.editTextTextPersonName);
        etNoTelepon = view.findViewById(R.id.editTextPhoneNumber);
        etQuantity = view.findViewById(R.id.editTextNumber);
        btnBuyTicket = view.findViewById(R.id.button);

        // Inisialisasi database dan helper
        dbHelper = new DatabaseTicketHelper(getContext());
        database = dbHelper.getWritableDatabase();

        // Mengatur OnClickListener untuk tombol Buy Ticket
        btnBuyTicket.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buyTicket();
            }
        });

        // Mengatur OnClickListener untuk tombol View Tickets
        Button btnViewTickets = view.findViewById(R.id.btnViewTickets);
        btnViewTickets.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openViewTicketFragment();
            }
        });

        return view;
    }

    // Metode untuk membuka fragment ViewTicket
    private void openViewTicketFragment() {
        Log.d("TicketFragment", "Opening ViewTicket");
        ViewTicket viewTicketFragment = new ViewTicket();
        getActivity().getSupportFragmentManager().beginTransaction()
                .replace(R.id.container, viewTicketFragment)
                .addToBackStack(null)
                .commit();
    }

    // Metode untuk membeli tiket
    private void buyTicket() {
        String nama = etNama.getText().toString().trim();
        String noTelepon = etNoTelepon.getText().toString().trim();
        String quantityStr = etQuantity.getText().toString().trim();

        // Memastikan semua input terisi
        if (nama.isEmpty() || noTelepon.isEmpty() || quantityStr.isEmpty()) {
            Toast.makeText(getContext(), "Harap isi semua informasi tiket.", Toast.LENGTH_SHORT).show();
            return;
        }

        // Mengonversi jumlah tiket ke tipe data int
        int quantity = Integer.parseInt(quantityStr);

        // Memasukkan data tiket ke dalam database
        ContentValues values = new ContentValues();
        values.put(DatabaseTicketHelper.COLUMN_NAMA, nama);
        values.put(DatabaseTicketHelper.COLUMN_NO_TELEPON, noTelepon);
        values.put(DatabaseTicketHelper.COLUMN_QUANTITY, quantity);

        // Menyimpan data tiket
        long newRowId = database.insert(DatabaseTicketHelper.TABLE_TICKET, null, values);

        // Menangani hasil penyimpanan tiket
        if (newRowId != -1) {
            Toast.makeText(getContext(), "Berhasil membeli tiket", Toast.LENGTH_SHORT).show();
            // Mengosongkan input setelah pembelian berhasil
            etNama.setText("");
            etNoTelepon.setText("");
            etQuantity.setText("");
        } else {
            Toast.makeText(getContext(), "Pembelian tiket gagal. Coba lagi!", Toast.LENGTH_SHORT).show();
        }
    }
}
