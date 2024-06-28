package com.example.shandrestaraunt;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.GridLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import java.util.Random;

public class ReserveFragment extends Fragment {

    private CalendarView calendarView;
    private Button reserveButton;
    private GridLayout floatingButtonContainer;
    private String selectedDate;
    private String selectedTime;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_reserve, container, false);

        calendarView = view.findViewById(R.id.calendarView);
        reserveButton = view.findViewById(R.id.reserveButton);
        floatingButtonContainer = view.findViewById(R.id.floatingButtonContainer);

        calendarView.setOnDateChangeListener((view1, year, month, dayOfMonth) -> {
            selectedDate = dayOfMonth + "/" + (month + 1) + "/" + year;
            generateFloatingButtons();
        });

        reserveButton.setOnClickListener(v -> showReservationDialog());

        return view;
    }

    private void generateFloatingButtons() {
        floatingButtonContainer.removeAllViews();
        Random random = new Random();
        int numberOfReservations = random.nextInt(6) + 3; // Generate between 3 and 8 reservations

        for (int i = 0; i < numberOfReservations; i++) {
            int hour = random.nextInt(12) + 1;
            int minute = random.nextInt(2) == 0 ? 0 : 5; // Minute ends with 0 or 5
            String time = String.format("%02d:%02d", hour, minute) + (hour < 12 ? " AM" : " PM");

            Button reservationButton = new Button(getContext());
            reservationButton.setText(time);
            reservationButton.setBackgroundResource(R.drawable.button_background_reserve);
            reservationButton.setTextColor(ContextCompat.getColor(getContext(), R.color.white));
            GridLayout.LayoutParams params = new GridLayout.LayoutParams();
            params.setMargins(16, 16, 16, 16);
            reservationButton.setLayoutParams(params);
            reservationButton.setPadding(16, 8, 16, 8);

            reservationButton.setOnClickListener(v -> {
                selectedTime = time;
                reserveButton.setEnabled(true);
                reserveButton.setText("Reserve");
                reserveButton.setBackgroundResource(R.drawable.button_background_reserve_select);
            });

            floatingButtonContainer.addView(reservationButton);
        }
    }

    private void showReservationDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle("Enter Your Details");

        final View customLayout = getLayoutInflater().inflate(R.layout.dialog_reservation, null);
        builder.setView(customLayout);

        builder.setPositiveButton("Reserve", (dialog, which) -> {
            EditText nameEditText = customLayout.findViewById(R.id.nameEditText);
            EditText phoneEditText = customLayout.findViewById(R.id.phoneEditText);
            String name = nameEditText.getText().toString();
            String phone = phoneEditText.getText().toString();

            String confirmationMessage = "RESERVED!\nName: " + name + "\nPhone: " + phone + "\nDate: " + selectedDate + "\nTime: " + selectedTime;
            new AlertDialog.Builder(getContext())
                    .setTitle("Reservation Confirmed")
                    .setMessage(confirmationMessage)
                    .setPositiveButton("OK", null)
                    .show();
        });

        builder.setNegativeButton("Cancel", null);

        AlertDialog dialog = builder.create();
        dialog.show();
    }
}
