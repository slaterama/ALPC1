// Copyright (c) 2017 Art & Logic, Inc. All Rights Reserved.

package com.slaterama.alpc1;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.TextView;

/**
 * @class ABaseFragment
 * @brief A {@link android.support.v4.app.Fragment} that performs some
 * common logic related to both {@link ADecodeFragment} and
 * {@link AEncodeFragment}.
 */
public abstract class ABaseFragment extends Fragment
    implements TextView.OnEditorActionListener, View.OnClickListener {

   // Used for preserving state of fResultText
   private static final String KEY_RESULT_TEXT = "resultText";
   private static final String KEY_RESULT_TEXT_COLOR = "resultTextColor";

   protected TextView fResultText;
   protected Button fProcessBtn;

   @Override
   public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
      super.onViewCreated(view, savedInstanceState);
      fResultText = (TextView) view.findViewById(R.id.result_txt);
      fProcessBtn = (Button) view.findViewById(R.id.process_btn);
      fProcessBtn.setOnClickListener(this);

      // State of TextViews is not preserved on configuration change
      if (savedInstanceState != null) {
         fResultText.setText(
             savedInstanceState.getCharSequence(KEY_RESULT_TEXT));
         fResultText.setTextColor(
             savedInstanceState.getInt(KEY_RESULT_TEXT_COLOR));
      }
   }

   @Override
   public void onSaveInstanceState(Bundle outState) {
      super.onSaveInstanceState(outState);

      // State of TextViews is not preserved on configuration change
      outState.putCharSequence(KEY_RESULT_TEXT, fResultText.getText());
      outState.putInt(KEY_RESULT_TEXT_COLOR, fResultText.getCurrentTextColor());
   }

   /**
    * Process this fragment when the "Go" action is clicked from an EditText.
    */
   @Override
   public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
      if (actionId == EditorInfo.IME_ACTION_GO) {
         onProcess();
         return true;
      }
      return false;
   }

   /**
    * Process this fragment when the encode/decode button is clicked.
    */
   @Override
   public void onClick(View view) {
      onProcess();
   }

   abstract public void onProcess();
}
