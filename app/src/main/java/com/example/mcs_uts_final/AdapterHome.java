package com.example.mcs_uts_final;

// Import library yang diperlukan
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.imageview.ShapeableImageView;
import java.util.ArrayList;

// Deklarasi kelas AdapterHome yang menggextend RecyclerView.Adapter<MyViewHolder>
public class AdapterHome extends RecyclerView.Adapter<AdapterHome.MyViewHolder> {

    // Variabel untuk menyimpan konteks dan daftar berita
    Context context;
    ArrayList<News> newsArrayList;

    // Konstruktor untuk AdapterHome
    public AdapterHome(Context context, ArrayList<News> newsArrayList) {
        this.context = context;
        this.newsArrayList = newsArrayList;
    }

    // Override method untuk membuat tampilan item dalam RecyclerView
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Menginflate layout item RecyclerView
        View v = LayoutInflater.from(context).inflate(R.layout.recylerviewex, parent, false);
        return new MyViewHolder(v);
    }

    // Override method untuk menghubungkan data dengan tampilan item
    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        // Mendapatkan objek News pada posisi tertentu
        News news = newsArrayList.get(position);

        // Menetapkan teks heading ke TextView
        holder.Nama.setText(news.heading);

        // Menetapkan gambar ke ShapeableImageView
        holder.shapeableImageView.setImageResource(news.titleimage);
    }

    // Override method untuk mendapatkan jumlah item dalam daftar
    @Override
    public int getItemCount() {
        return newsArrayList.size();
    }

    // Kelas nested MyViewHolder untuk merepresentasikan tampilan setiap item RecyclerView
    public static class MyViewHolder extends RecyclerView.ViewHolder {

        // Variabel untuk menampung elemen UI
        ShapeableImageView shapeableImageView;
        TextView Nama;

        // Konstruktor MyViewHolder
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            // Inisialisasi elemen UI berdasarkan ID
            shapeableImageView = itemView.findViewById(R.id.ShapeImageView);
            Nama = itemView.findViewById(R.id.textView6);
        }
    }
}
