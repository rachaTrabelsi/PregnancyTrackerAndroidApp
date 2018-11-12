package com.esprit.pregnancytracker.Fragments;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.TransitionDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.esprit.pregnancytracker.R;
import com.esprit.pregnancytracker.utils.ValuesClass;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link WaterFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link WaterFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class WaterFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    Double value=0.0;
    TextView edtwaterValue ;
    Button btnplus,btnmoins;

    private OnFragmentInteractionListener mListener;

    public WaterFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment WaterFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static WaterFragment newInstance(String param1, String param2) {
        WaterFragment fragment = new WaterFragment();

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_water, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
//        ((ImcActivity)getActivity()).aa=edtwaterValue.toString();
        //Setup the data


        //Run the Method

        super.onViewCreated(view, savedInstanceState);
        edtwaterValue = (TextView) getView().findViewById(R.id.edtwater);
        btnplus = (Button)view.findViewById(R.id.btnplus);
        btnmoins = (Button)view.findViewById(R.id.btnmoins);
        final ImageView cupwater = (ImageView) view.findViewById(R.id.cupwater);
        btnplus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cupwater.setImageResource(R.drawable.transition);
                ((TransitionDrawable)cupwater.getDrawable()).startTransition(3000) ;
                value = value+ 0.25;
                edtwaterValue.setText(String.valueOf(value));
            }
        });
        btnmoins.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (value > 0.0) {
                    cupwater.setImageResource(R.drawable.transition);
                    ((TransitionDrawable)cupwater.getDrawable()).startTransition(3000) ;
                    value = value - 0.25;
                    edtwaterValue.setText(String.valueOf(value));
                }
                if (value == 0.0){
                    cupwater.setImageResource(R.drawable.emptyglass);
                }
            }
        });
        edtwaterValue.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                ValuesClass.waterVal = edtwaterValue.getText().toString();

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                ValuesClass.waterVal = edtwaterValue.getText().toString();

            }

            @Override
            public void afterTextChanged(Editable editable) {
                ValuesClass.waterVal = edtwaterValue.getText().toString();

            }
        });
        //PACK DATA
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Bundle bundle) {
        if (mListener != null) {
            mListener.onFragmentInteraction(bundle);
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        mParam1 = edtwaterValue.getText().toString();
    }
    private OnFragmentInteractionListener context;
    @Override
    public void onAttach(Activity context) {
        super.onAttach(context);
        this.context = (OnFragmentInteractionListener)context;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Bundle bundle);

    }
}
