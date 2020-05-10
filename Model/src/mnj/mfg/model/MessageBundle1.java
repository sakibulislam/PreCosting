package mnj.mfg.model;

import java.util.ListResourceBundle;

public class MessageBundle1 extends ListResourceBundle {
    private static final Object[][] sMessageStrings = new String[][] {
  { "MNJ_POC_L_UniqComp","Please enter a Unique Combination of Wash,Colour,StyleNo and FOB."}
  
  };

    /**Return String Identifiers and corresponding Messages in a two-dimensional array.
     */
    protected Object[][] getContents() {
        return sMessageStrings;
    }
}
