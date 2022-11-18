package com.example.myapplication.ui.home;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.arch.lifecycle.ViewModelProvider;
import android.widget.Toast;

import com.example.myapplication.R;
import com.example.myapplication.databinding.FragmentHomeBinding;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class HomeFragment extends Fragment {


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        LayoutInflater lf = getActivity().getLayoutInflater();
        View view = lf.inflate(R.layout.fragment_home, container, false);

        ImageView imgReset = view.findViewById(R.id.imgReset);
        imgReset.setOnClickListener(v -> resetClicked());

        connectionClass();

        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

//    RESET
    public void resetClicked(){
        Connection connection = connectionClass();
        try{
            if (connection != null) {
                String script = "DELETE FROM Trip";
                Statement st = connection.createStatement();
                st.executeUpdate(script);
                Toast.makeText(getActivity(), "Reset successful !", Toast.LENGTH_SHORT).show();
            }
        } catch (Exception e ){
            Toast.makeText(getActivity(), "Reset failure !", Toast.LENGTH_SHORT).show();
        }
    }

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