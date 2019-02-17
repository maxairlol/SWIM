package com.example.haircutbe;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class ReservationListAdapter extends BaseAdapter {
    private Context context;
    private List<Reservation> reservationList;

    public ReservationListAdapter(Context context, List<Reservation> reservationList) {
        this.context = context;
        this.reservationList = reservationList;
    }

    @Override
    public int getCount() {
        return reservationList.size();
    }

    @Override
    public Object getItem(int position) {
        return reservationList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    /* private view holder class */
    private class ViewHolder {
        TextView salon_name;
        TextView service_name;
        TextView date;
        TextView month;
        TextView time;
    }

    @SuppressLint("SetTextI18n")
    @Override
    public View getView(int position, View cView, ViewGroup parent) {
        ViewHolder holder = null;
        if (cView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
            cView = inflater.inflate(R.layout.item_reservation_list, null);
        }
        holder = new ViewHolder();
        holder.salon_name = (TextView) cView.findViewById(R.id.tvSalonName);
        holder.service_name = (TextView) cView.findViewById(R.id.tvServiceName);
        holder.month = (TextView) cView.findViewById(R.id.tvMonth);
        holder.date = (TextView) cView.findViewById(R.id.tvDate);
        holder.time = (TextView) cView.findViewById(R.id.tvDuration);

        Reservation reservation = reservationList.get(position);
        holder.salon_name.setText(reservation.getSalon_name());
        holder.service_name.setText(reservation.getService_name());
        holder.month.setText(reservation.getMonthName());
        holder.date.setText(Integer.toString(reservation.getDayFromDate()));
        holder.time.setText(reservation.timeToString());



        return cView;
    }
}
