package sg.edu.rp.c347.p10_knowyourfacts;


import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;


/**
 * A simple {@link Fragment} subclass.
 */
public class Fg3 extends Fragment {

    Button btn3;
    int a = 0;

    ImageView iv;

    public Fg3() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.fragment_fg3, container, false);

        iv = (ImageView) view.findViewById(R.id.imageView);
        btn3 = (Button) view.findViewById(R.id.btnC3);

        String imageUrl = "https://78.media.tumblr.com/59174c6ecd883dab416b59676c30b2fe/tumblr_pcaw1cXox31roqv59o1_500.png";
        Picasso.with(view.getContext()).load(imageUrl).into(iv);

        btn3.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceAsColor")
            @Override
            public void onClick(View v) {
                if (a == 0) {
                    view.setBackgroundResource(R.color.skyblue);
                    a=1;
                } else {
                    view.setBackgroundResource(R.color.green);
                    a=0;
                }


            }
        });


        return view;
    }

}
