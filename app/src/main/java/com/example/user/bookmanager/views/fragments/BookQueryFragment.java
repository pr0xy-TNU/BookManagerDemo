package com.example.user.bookmanager.views.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.user.bookmanager.R;

/**
 * Created by user on 15.12.17.
 */

public class BookQueryFragment extends Fragment {

  @Nullable
  @Override
  public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
      Bundle savedInstanceState) {
    View tempView = inflater.inflate(R.layout.book_fragment, container, false);

    return tempView;
  }


}
