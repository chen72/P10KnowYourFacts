package sg.edu.rp.c347.p10_knowyourfacts;


import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.crazyhitty.chdev.ks.rssmanager.Channel;


/**
 * A simple {@link Fragment} subclass.
 */
public class Fg1 extends Fragment {

    Button btn1;
    int a = 0;


    public Fg1() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.fragment_fg1, container, false);

        btn1 = (Button) view.findViewById(R.id.btnC1);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(a==0){
                    view.setBackgroundResource(R.color.skyblue);
                    a=1;
                }else{
                    view.setBackgroundResource(R.color.green);
                    a=0;
                }


            }
        });


        return view;
    }



}
