// Copyright (c) 2017 Art & Logic, Inc. All Rights Reserved.

package com.slaterama.alpc1;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

/**
 * @class ADecodeFragment
 * @brief A {@link android.support.v4.app.Fragment} that presents a form for
 * decoding a pair of hexadecimal bytes to an integer value.
 */
public class ADecodeFragment extends ABaseFragment {

   /**
    * Returns a new instance of this fragment.
    */
   public static ADecodeFragment newInstance() {
      return new ADecodeFragment();
   }

   private EditText fHiByteEdit;
   private EditText fLoByteEdit;

   @Nullable
   @Override
   public View onCreateView(
       LayoutInflater inflater,
       @Nullable ViewGroup container,
       @Nullable Bundle savedInstanceState) {
      return inflater.inflate(R.layout.fragment_decode, container, false);
   }

   @Override
   public void onViewCreated(View view, Bundle savedInstanceState) {
      super.onViewCreated(view, savedInstanceState);
      fHiByteEdit = (EditText) view.findViewById(R.id.hi_byte_edit);
      fLoByteEdit = (EditText) view.findViewById(R.id.lo_byte_edit);
      fLoByteEdit.setOnEditorActionListener(this);
   }

   /**
    * Ensures that the supplied EditText is not empty, and also pads
    * the value with a leading zero if necessary.
    * @param editText The {@link EditText} whose value to check.
    */
   private String checkValue(EditText editText)
       throws IllegalArgumentException {
      Editable value = editText.getText();
      if (TextUtils.isEmpty(value))
         throw new IllegalArgumentException();
      String valueFormatted = String.format("%2s", value).replace(' ', '0');
      if (!TextUtils.equals(value, valueFormatted))
         editText.setText(valueFormatted);
      return valueFormatted;
   }

   /**
    * Displays the decoded value, or a message if an error occurred.
    */
   @Override
   public void onProcess() {
      try {
         String hiByte = checkValue(fHiByteEdit);
         String loByte = checkValue(fLoByteEdit);
         int decodedValue = AUtils.decode(hiByte, loByte);
         fResultText.setTextColor(Color.BLACK);
         fResultText.setText(String.valueOf(decodedValue));
      } catch (IllegalArgumentException e) {
         fResultText.setTextColor(Color.RED);
         fResultText.setText(R.string.validate_hex_string_message);
      }
   }
}
