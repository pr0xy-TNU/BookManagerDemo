package com.example.user.bookmanager.views.fragments;

import android.app.Fragment;
import android.app.LoaderManager.LoaderCallbacks;
import android.content.Loader;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.user.bookmanager.R;

/**
 * Created by user on 15.12.17.
 */

public class BookFragment extends Fragment implements LoaderCallbacks{

  @Nullable
  @Override
  public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
      Bundle savedInstanceState) {
    View tempView = inflater.inflate(R.layout.book_fragment, container, false);

    return tempView;
  }


  @Override
  public Loader onCreateLoader(int id, Bundle args) {
    return null;
  }

  @Override
  public void onLoadFinished(Loader loader, Object data) {

  }

  @Override
  public void onLoaderReset(Loader loader) {

  }
}
