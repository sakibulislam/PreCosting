package view.backing.bean;

import javax.faces.event.ActionEvent;

import oracle.adf.view.rich.event.PopupFetchEvent;

public class sizeBreakdown {
    public sizeBreakdown() {
    }

    public void sizeBreakdownPopUpFetchListener(PopupFetchEvent popupFetchEvent) {
        System.out.println("============== in  sizeBreakdownPopUpFetchlistener method ");
    }

    public void sizeBreakdownPopUpFetchPreAct(ActionEvent actionEvent) {
      
        System.out.println("============== in  sizeBreakdownPopUpFetchPreAct action list method ");
    }


    public String sizeBreakDownPopupPreAct() {
       
        System.out.println("============== in  sizeBreakdownPopUpFetchPreAct method ");
        return null;
    }
}
