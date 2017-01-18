// Copyright (c) 2017 Art & Logic, Inc. All Rights Reserved.

package com.slaterama.alpc1;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

/**
 * @class AMainActivity
 * @brief The main activity for the ALPC1 app. Maintains a {@link ViewPager}
 * of Fragments used to encode and decode values.
 */
public class AMainActivity extends AppCompatActivity {

   // Values used to populate the MainAdapter
   private static final int ENCODE_FRAGMENT = 0;
   private static final int DECODE_FRAGMENT = 1;
   private static final int NUM_ITEMS = 2;

   private ViewPager fViewPager;

   @Override
   protected void onCreate(Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
      setContentView(R.layout.activity_main);
      fViewPager = (ViewPager) findViewById(R.id.pager);
      MainAdapter adapter = new MainAdapter(getSupportFragmentManager());
      fViewPager.setAdapter(adapter);
      fViewPager.setCurrentItem(ENCODE_FRAGMENT);
   }

   @Override
   public boolean onCreateOptionsMenu(Menu menu) {
      getMenuInflater().inflate(R.menu.menu_main, menu);
      return true;
   }

   @Override
   public boolean onOptionsItemSelected(MenuItem item) {
      switch (item.getItemId()) {
         case R.id.menu_encode:
            fViewPager.setCurrentItem(ENCODE_FRAGMENT);
            return true;
         case R.id.menu_decode:
            fViewPager.setCurrentItem(DECODE_FRAGMENT);
            return true;
         default:
            return super.onOptionsItemSelected(item);
      }
   }

   /**
    * An adapter that maintains the encode/decode fragments.
    */
   private static class MainAdapter extends FragmentPagerAdapter {
      MainAdapter(FragmentManager fm) {
         super(fm);
      }

      @Override
      public Fragment getItem(int position) {
         switch (position) {
            case DECODE_FRAGMENT:
               return ADecodeFragment.newInstance();
            case ENCODE_FRAGMENT:
            default:
               return AEncodeFragment.newInstance();
         }
      }

      @Override
      public int getCount() {
         return NUM_ITEMS;
      }
   }
}
