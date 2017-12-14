package com.example.user.bookmanager.mvp;

import android.content.Context;

/**
 * Created by user on 14.12.17.
 */

public interface MVP {

  //Допустимые операции из View к Presenter

  /**
   * Ссылка из Presenter на View
   * Presenter -> View
   */
  interface RequiredViewOps {
    Context getApplicationContext();

    Context getActivityContext();

  }

  /**
   * Ссылка из View на Presenter
   * View -> Presenter
   */
  interface PresenterOps {

  }

  /**
   * Ссылка из Model на Presenter
   * Model -> Presenter
   */
  interface RequiredPresenterOps {

    Context getApplicationContext();

    Context getActivityContext();

  }

  /**
   * Ссылка из Presenter на Model
   * Presenter -> Model
   */

  interface ModelOps {


  }

}
