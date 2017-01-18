// Copyright (c) 2017 Art & Logic, Inc. All Rights Reserved.

package com.slaterama.alpc1;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

/**
 * @class AEncodeFragment
 * @brief A {@link android.support.v4.app.Fragment} that presents a form for
 * encoding an integer value to a hexadecimal string.
 */
public class AEncodeFragment extends ABaseFragment {

   /**
    * Returns a new instance of this fragment.
    */
   public static AEncodeFragment newInstance() {
      return new AEncodeFragment();
   }

   private EditText fEntryEdit;

   @Nullable
   @Override
   public View onCreateView(
       LayoutInflater inflater,
       @Nullable ViewGroup container,
       @Nullable Bundle savedInstanceState) {
      return inflater.inflate(R.layout.fragment_encode, container, false);
   }

   @Override
   public void onViewCreated(View view, Bundle savedInstanceState) {
      super.onViewCreated(view, savedInstanceState);
      fEntryEdit = (EditText) view.findViewById(R.id.entry_edit);
      fEntryEdit.setOnEditorActionListener(this);
   }

   /**
    * Displays the encoded value, or a message if an error occurred.
    */
   @Override
   public void onProcess() {
      try {
         int value = Integer.valueOf(fEntryEdit.getText().toString());
         String encodedValue = AUtils.encode(value);
         fResultText.setTextColor(Color.BLACK);
         fResultText.setText(encodedValue);
      } catch (IllegalArgumentException e) {
         fResultText.setTextColor(Color.RED);
         fResultText.setText(R.string.validate_integer_message);
      }
   }
}
