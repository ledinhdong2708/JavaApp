package com.example.myapplication.ui.newtrip;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

import com.example.myapplication.R;
import com.example.myapplication.databinding.FragmentNewtripBinding;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class NewTripFragment extends Fragment {

    private FragmentNewtripBinding binding;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        LayoutInflater lf = getActivity().getLayoutInflater();
        View view = lf.inflate(R.layout.fragment_newtrip, container, false);

        Button btnCreateTrip = view.findViewById(R.id.btnCreateTrip);
        btnCreateTrip.setOnClickListener(v -> addTrip());

        //Button btnCreateExpense = view.findViewById(R.id.btnCreateExpense);
        //btnCreateExpense.setOnClickListener(v -> addExpense());

        connectionClass();

        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

//    ADD TRIP
    public void addTrip(){
        EditText etTripName = getView().findViewById(R.id.etTripName);
        EditText etDetination = getView().findViewById(R.id.etDetination);
        EditText etDate = getView().findViewById(R.id.etDate);
        EditText etDescription = getView().findViewById(R.id.etDescription);
        EditText etLocation = getView().findViewById(R.id.etLocation);
        EditText etStatus = getView().findViewById(R.id.etStatus);

        String tripName = etTripName.getText().toString();
        String destination = etDetination.getText().toString();
        String date = etDate.getText().toString();
        String description = etDescription.getText().toString();
        String location = etLocation.getText().toString();
        String status = etStatus.getText().toString();

        Date dt = new Date();
        assert dt != null;

        try {
            dt=new SimpleDateFormat("dd/MM/yyyy").parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Boolean risk = ((Switch) getView().findViewById(R.id.btnRequired)).isChecked();

        if(tripName.isEmpty()){
            Toast.makeText(getActivity(), "Trip name can not be null !!", Toast.LENGTH_SHORT).show();
        } else if (destination.isEmpty()){
            Toast.makeText(getActivity(), "Destination can not be null !!", Toast.LENGTH_SHORT).show();
        } else if (date.isEmpty()){
            Toast.makeText(getActivity(), "Date can not be null", Toast.LENGTH_SHORT).show();
        } else {
            Connection connection = connectionClass();
            try{
                if (connection != null) {
                    String sqlScript = "INSERT INTO Trip VALUES (1,'"+
                            tripName + "', '" +
                            destination + "','" +
                            date + "','" +
                            description + "','" +
                            location + "','" +
                            status + "','" +
                            risk + "')";
                    Statement st = connection.createStatement();
                    st.executeQuery(sqlScript);
                    Toast.makeText(getActivity(), "Added successful !", Toast.LENGTH_SHORT).show();
                }
            } catch (Exception e ){
                Toast.makeText(getActivity(), "Add failure !", Toast.LENGTH_SHORT).show();
            }
//            Map<String, Object> trip = new HashMap<>();
//            trip.put("tripName", tripName);
//            trip.put("destination", destination);
//            trip.put("date", date);
//            trip.put("description", description);
//            trip.put("location", location);
//            trip.put("status", status);
//
//            db.collection("trip")
//                    .add(trip)
//                    .addOnSuccessListener(documentReference ->
//                            Toast.makeText(getActivity(), "Added successful !!", Toast.LENGTH_SHORT).show()
//                    )
//                    .addOnFailureListener(e ->
//                            Toast.makeText(getActivity(), "Add failure !!", Toast.LENGTH_SHORT).show()
//                    );
        }
    }


//    public void addExpense(){
//        EditText etType = getView().findViewById(R.id.etType);
//        EditText etAmount = getView().findViewById(R.id.etAmount);
//        EditText etTime = getView().findViewById(R.id.etTime);
//        EditText etComment = getView().findViewById(R.id.etComment);
//        EditText etTripId = getView().findViewById(R.id.etComment);
//
//        String type = etType.getText().toString();
//        String amount = etAmount.getText().toString();
//        String time = etTime.getText().toString();
//        String comment = etComment.getText().toString();
//        String tripId = etTripId.getText().toString();
//
//        Date dt = new Date();
//        assert dt != null;
//
//        try {
//            dt=new SimpleDateFormat("dd/MM/yyyy").parse(time);
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }
//
//        if(type.isEmpty()){
//            Toast.makeText(getActivity(), "Type can not be null !!", Toast.LENGTH_SHORT).show();
//        } else if (amount.isEmpty()){
//            Toast.makeText(getActivity(), "Amount can not be null !!", Toast.LENGTH_SHORT).show();
//        } else if (time.isEmpty()){
//            Toast.makeText(getActivity(), "Time can not be null", Toast.LENGTH_SHORT).show();
//        } else {
//            Connection connection = connectionClass();
//            try{
//                if (connection != null) {
//                    String sqlScript = "INSERT INTO Expense VALUES (1,'"+
//                            type + "', " +
//                            amount + ",'" +
//                            time + "','" +
//                            comment + "','" +
//                            tripId + "')";
//                    Statement st = connection.createStatement();
//                    st.executeQuery(sqlScript);
//                    Toast.makeText(getActivity(), "Add expense successful !", Toast.LENGTH_SHORT).show();
//                }
//            } catch (Exception e ){
//                Toast.makeText(getActivity(), "Add expense failure !", Toast.LENGTH_SHORT).show();
//            }
//        }
//    }

    @SuppressLint("NewApi")
    public Connection connectionClass() {
        Connection con = null;
        String ip = "192.168.0.100", port = "1433", username = "sa", password = "123456", database = "JavaApp";
        StrictMode.ThreadPolicy tp = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(tp);
        try {
            Class.forName("net.sourceforge.jtds.jdbc.Driver");
            String connectionUrl = "jdbc:jtds:sqlserver://" + ip + ":" + port + ";databasename=" + database + ";user=" + username + ";password=" + password + ";";
            con = DriverManager.getConnection(connectionUrl);
        } catch (Exception exception) {
            Log.e("Error", exception.getMessage());
        }
        return con;
    }
}
