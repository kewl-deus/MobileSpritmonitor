package de.dengot.spritmonitor.fragment;

import android.support.v4.app.Fragment;

public abstract class BasicFormFragment extends Fragment{

    @SuppressWarnings("unchecked")
    protected <V> V findView(int id){
        return (V) getActivity().findViewById(id);
    }
}
