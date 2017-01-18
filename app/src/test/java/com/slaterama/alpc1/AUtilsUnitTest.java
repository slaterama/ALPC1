package com.slaterama.alpc1;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class AUtilsUnitTest {
   @Test
   public void encoding_isCorrect() throws Exception {
      assertEquals(AUtils.encode(0), "4000");
      assertEquals(AUtils.encode(-8192), "0000");
      assertEquals(AUtils.encode(8191), "7F7F");
      assertEquals(AUtils.encode(2048), "5000");
      assertEquals(AUtils.encode(-4096), "2000");
   }

   @Test
   public void decoding_isCorrect() throws Exception {
      assertEquals(AUtils.decode("40", "00"), 0);
      assertEquals(AUtils.decode("00", "00"), -8192);
      assertEquals(AUtils.decode("7F", "7F"), 8191);
      assertEquals(AUtils.decode("50", "00"), 2048);
      assertEquals(AUtils.decode("0A", "05"), -6907);
      assertEquals(AUtils.decode("55", "00"), 2688);
   }
}