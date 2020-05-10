package view.backing.bean;


import java.io.BufferedReader;

import java.io.IOException;
import java.io.InputStreamReader;

import java.util.HashMap;
import java.util.StringTokenizer;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIViewRoot;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.event.ValueChangeEvent;

import javax.servlet.http.HttpSession;

import oracle.adf.model.BindingContext;

import oracle.adf.model.binding.DCBindingContainer;
import oracle.adf.model.binding.DCDataControl;
import oracle.adf.model.binding.DCIteratorBinding;
import oracle.adf.view.rich.component.rich.data.RichColumn;
import oracle.adf.view.rich.component.rich.data.RichTable;
import oracle.adf.view.rich.component.rich.input.RichInputListOfValues;
import oracle.adf.view.rich.component.rich.input.RichInputText;

import oracle.adf.view.rich.component.rich.input.RichSelectItem;
import oracle.adf.view.rich.component.rich.input.RichSelectOneRadio;
import oracle.adf.view.rich.component.rich.layout.RichPanelHeader;
import oracle.adf.view.rich.component.rich.output.RichOutputText;
import oracle.adf.view.rich.context.AdfFacesContext;

import oracle.adf.view.rich.event.DialogEvent;
import oracle.adf.view.rich.event.LaunchPopupEvent;
import oracle.adf.view.rich.event.PopupCanceledEvent;

import oracle.adf.view.rich.event.PopupFetchEvent;

import oracle.adfinternal.view.faces.model.binding.FacesCtrlHierNodeBinding;

import oracle.binding.BindingContainer;
import oracle.binding.OperationBinding;

import oracle.jbo.ApplicationModule;
import oracle.jbo.Row;
import oracle.jbo.RowSetIterator;
import oracle.jbo.ViewObject;
import oracle.jbo.domain.Number;

import org.apache.myfaces.trinidad.model.UploadedFile;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;

import java.io.OutputStream;
import java.io.OutputStreamWriter;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

import java.util.HashMap;
import java.util.StringTokenizer;

import javax.el.ELContext;
import javax.el.ExpressionFactory;

import javax.el.MethodExpression;

import javax.faces.application.Application;
import javax.faces.context.FacesContext;

import javax.faces.event.ValueChangeEvent;

import oracle.adf.view.rich.component.rich.data.RichTable;
import oracle.adf.view.rich.component.rich.input.RichInputText;

import oracle.adf.view.rich.component.rich.output.RichOutputText;

import view.backing.bean.Main;

import javax.el.ValueExpression;

import javax.faces.application.FacesMessage;

import mnj.mfg.model.services.AppModuleImpl;

import oracle.adf.model.BindingContext;
import oracle.adf.view.rich.component.rich.input.RichSelectOneRadio;
import oracle.adf.view.rich.event.DialogEvent;
import oracle.adf.view.rich.event.PopupCanceledEvent;
import oracle.adf.view.rich.event.PopupFetchEvent;

import oracle.binding.BindingContainer;
import oracle.binding.OperationBinding;

import oracle.adf.model.DataControl;

import oracle.adf.model.binding.DCBindingContainer;
import oracle.adf.model.binding.DCDataControl;
import oracle.adf.model.binding.DCIteratorBinding;
import oracle.adf.view.rich.context.AdfFacesContext;

import oracle.adfinternal.view.faces.model.binding.FacesCtrlHierNodeBinding;

import oracle.binding.OperationBinding;

import oracle.jbo.ApplicationModule;
import oracle.jbo.Key;
import oracle.jbo.Row;
import oracle.jbo.RowSet;
import oracle.jbo.RowSetIterator;
import oracle.jbo.ViewObject;
import oracle.jbo.domain.Number;

import org.apache.myfaces.trinidad.model.UploadedFile;

public class PopUpBean {

    Main myBean;
    public RichTable updateTable;
    public RichInputText headerId;
    public RichTable bpOHTable;
    public RichTable bpoLinetable;
    public RichInputText bpoQtyLineBind;
    public RichInputText blncLineBind;
    public ViewObject LineBPOVo;
    public RichOutputText tbAQty_Bind;
    public RichTable sizeTab;
    private RichOutputText totalBpoQuantity;
    private RichPanelHeader createBPO;


    public PopUpBean() {
        
        BindingContext bindingContext = BindingContext.getCurrent();
        DCDataControl dc =
            bindingContext.findDataControl("AppModuleDataControl"); //
        ApplicationModule am = dc.getApplicationModule();
        LineBPOVo = am.findViewObject("MnjPrecostCreateBpoDVO1");
        
    }


    public BindingContainer getBindings() {
        return BindingContext.getCurrent().getCurrentBindingsEntry();
    }

    public void editPopupFetchListener(PopupFetchEvent popupFetchEvent) {
        
        DCBindingContainer bindings =(DCBindingContainer)BindingContext.getCurrent().getCurrentBindingsEntry();
               
        DCIteratorBinding roleIter = bindings.findIteratorBinding("UpdateOrderInterface1Iterator");

        ViewObject obj = roleIter.getViewObject();
        obj.clearCache();
        obj.executeQuery();
       

        
    }

    public void editDialogListener(DialogEvent dialogEvent) {
        if (dialogEvent.getOutcome().name().equals("ok")) {

//            BindingContext bctx = BindingContext.getCurrent();
//            BindingContainer bindings = bctx.getCurrentBindingsEntry();
//            OperationBinding operationBinding =
//                bindings.getOperationBinding("Commit");
//            operationBinding.execute();
             am.getDBTransaction().commit();
            createOrder();


        } else if (dialogEvent.getOutcome().name().equals("cancel")) {
            BindingContainer bindings = getBindings();

        }
    }

    public String createOrder() {
        
        
        BindingContext bctx = BindingContext.getCurrent();
        BindingContainer bindings = bctx.getCurrentBindingsEntry();
        OperationBinding operationBinding = bindings.getOperationBinding("createOrder");

        operationBinding.getParamsMap().put("headerId", getHeaderId().getValue());


        //invoke method
        operationBinding.execute();
        
        if (!operationBinding.getErrors().isEmpty()) {
           
         
        }
        //optional
        Object methodReturnValue = operationBinding.getResult();
        String message = null;
        if (methodReturnValue != null) {
            message = methodReturnValue.toString();
        } else {
            message = "Failed! .";
        }
        FacesMessage fm = new FacesMessage(message);
        fm.setSeverity(FacesMessage.SEVERITY_INFO);
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, fm);
        return null;
        
        
    }


    public void editPopupCancelListener(PopupCanceledEvent popupCanceledEvent) {
        BindingContainer bindings = getBindings();

    }


    public void editDialogListenerHistory(DialogEvent dialogEvent) {
        if (dialogEvent.getOutcome().name().equals("ok")) {
            am.getDBTransaction().commit();
//            BindingContext bctx = BindingContext.getCurrent();
//            BindingContainer bindings = bctx.getCurrentBindingsEntry();
//            OperationBinding operationBinding =
//                bindings.getOperationBinding("Commit");
//            operationBinding.execute();
//            try {
//                Thread.sleep(1000);
//            } catch (InterruptedException e) {
//                ;
//            }
            System.out.println("Ok called");
            updateOrder();

        } else if (dialogEvent.getOutcome().name().equals("cancel")) {
            BindingContainer bindings = getBindings();

        }
    }

    public String updateOrder() {
        BindingContext bctx = BindingContext.getCurrent();
        BindingContainer bindings = bctx.getCurrentBindingsEntry();
        OperationBinding operationBinding =
            bindings.getOperationBinding("updateOrder");
        operationBinding.getParamsMap().put("headerId",
                                            getUpdateID().getValue());

        System.out.println("Id -->" + getUpdateID().getValue());
        //invoke method
        operationBinding.execute();
        if (!operationBinding.getErrors().isEmpty()) {
            System.out.println("if errors-->");
            // List errors = operationBinding.getErrors();
        }
        //optional
        Object methodReturnValue = operationBinding.getResult();
        String message = null;
        if (methodReturnValue != null) {
            message = methodReturnValue.toString();
        } else {
            message = "Failed! .";
        }
        FacesMessage fm = new FacesMessage(message);
        fm.setSeverity(FacesMessage.SEVERITY_INFO);
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, fm);
        return null;
    }


    //    public Main getMyBeanInstance() {
    //        FacesContext fctx = FacesContext.getCurrentInstance();
    //        Application application = fctx.getApplication();
    //        ExpressionFactory expressionFactory =
    //            application.getExpressionFactory();
    //        ExpressionFactory factory = application.getExpressionFactory();
    //        ELContext context = fctx.getELContext();
    //        ValueExpression createValueExpression =
    //            expressionFactory.createValueExpression(context,
    //                                                    "#{requestScope.ManagedBean}",
    //                                                    Main.class);
    //        Main pageFlowScopeInstance =
    //            (Main)createValueExpression.getValue(context);
    //        return pageFlowScopeInstance;
    //    }

    public void setMyBean(Main myBean) {
        this.myBean = myBean;
    }

    public Main getMyBean() {
        return myBean;
    }

    public Number getUpdateID() {

        oracle.adf.view.rich.component.UIXTable table = getUpdateTable();
        // Get the Selected Row key set iterator
        java.util.Iterator selectionIt = table.getSelectedRowKeys().iterator();
        Number historyId = new Number(0);
        while (selectionIt.hasNext()) {
            Object rowKey = selectionIt.next();
            table.setRowKey(rowKey);
            int index = table.getRowIndex();
            FacesCtrlHierNodeBinding row =
                (FacesCtrlHierNodeBinding)table.getRowData(index);
            Row selectedRow = row.getRow();
            historyId = (Number)selectedRow.getAttribute("HistId");

       

        }
        return historyId;
    }


    public void setUpdateTable(RichTable updateTable) {
        this.updateTable = updateTable;
    }

    public RichTable getUpdateTable() {
        return updateTable;
    }


    public String generateSaleOrder() {
        
        trimsBPOs();
        
//        BindingContext bctx = BindingContext.getCurrent();
//        BindingContainer bindings = bctx.getCurrentBindingsEntry();
//        OperationBinding operationBinding =
//            bindings.getOperationBinding("Commit");
//        operationBinding.execute();
         am.getDBTransaction().commit();
        createOrder();
        
        ViewObject lineVo= am.getCreateSaleOrderLinesVO1();
        refreshQueryKeepingCurrentRow(lineVo );
        
        return null;
    }

    public void setHeaderId(RichInputText headerId) {
        this.headerId = headerId;
    }

    public RichInputText getHeaderId() {
        return headerId;
    }

    public void setBpOHTable(RichTable bpOHTable) {
        this.bpOHTable = bpOHTable;
    }

    public RichTable getBpOHTable() {
        return bpOHTable;
    }

    public void setBpoLinetable(RichTable bpoLinetable) {
        this.bpoLinetable = bpoLinetable;
    }

    public RichTable getBpoLinetable() {
        return bpoLinetable;
    }

    public void setBpoQtyLineBind(RichInputText bpoQtyLineBind) {
        this.bpoQtyLineBind = bpoQtyLineBind;
    }

    public RichInputText getBpoQtyLineBind() {
        return bpoQtyLineBind;
    }

    public void setBlncLineBind(RichInputText blncLineBind) {
        this.blncLineBind = blncLineBind;
    }

    public RichInputText getBlncLineBind() {
        return blncLineBind;
    }






    public void bpoQtyActionListener(ValueChangeEvent valueChangeEvent) {
        // Add event code here...
        
        double bpoQty = getBPOQty(); 
        double sumBPoQty = getBPOTotalQty(MyMath.numeric3(valueChangeEvent.getNewValue()));
        
        blncLineBind.setValue(MyMath.toNumber(bpoQty - sumBPoQty));
        AdfFacesContext.getCurrentInstance().addPartialTarget(blncLineBind);
        
        
        
        
        tbAQty_Bind.setValue(getTBATotalQty());
        AdfFacesContext.getCurrentInstance().addPartialTarget(tbAQty_Bind);

        
    }
    
    public double getBPOQty() {

        oracle.adf.view.rich.component.UIXTable table = getBpOHTable();
        // Get the Selected Row key set iterator
        java.util.Iterator selectionIt = table.getSelectedRowKeys().iterator();
        double bpoQty = 0.0;
        while (selectionIt.hasNext()) {
            Object rowKey = selectionIt.next();
            table.setRowKey(rowKey);
            int index = table.getRowIndex();
            FacesCtrlHierNodeBinding row =
                (FacesCtrlHierNodeBinding)table.getRowData(index);
            Row selectedRow = row.getRow();


            try {
                bpoQty =
    Double.parseDouble(selectedRow.getAttribute("BpoQty").toString());
            } catch (Exception e) {
                ;
            }

        }
        return bpoQty;
    }
    
    public String getLineId() {

        oracle.adf.view.rich.component.UIXTable table = getBpOHTable();
        // Get the Selected Row key set iterator
        java.util.Iterator selectionIt = table.getSelectedRowKeys().iterator();
        String LineId = null;
        while (selectionIt.hasNext()) {
            Object rowKey = selectionIt.next();
            table.setRowKey(rowKey);
            int index = table.getRowIndex();
            FacesCtrlHierNodeBinding row =
                (FacesCtrlHierNodeBinding)table.getRowData(index);
            Row selectedRow = row.getRow();

           LineId= selectedRow.getAttribute("LineId").toString();

        }
        return LineId;
    }
    
    
    public double getBPOTotalQty(double currentLay) {

        RowSetIterator it = LineBPOVo.createRowSetIterator("tt");
        Row currentRow = LineBPOVo.getCurrentRow();
        double total = 0.0;
        while (it.hasNext()) {
            Row r = it.next();
            if (currentLay > -1 && r == currentRow) {
                total = total + currentLay;
                continue;
            }
            total = total + MyMath.numeric3(r.getAttribute("BpoQty"));
        }
        it.closeRowSetIterator();
        return total;
    }

    public String createLineOrder() {
        
           Main m1 = new Main();
           Boolean checkTotalChildBpoQty;
           
        checkTotalChildBpoQty =m1.isBpoQtyLessthanTbaQty();
        if(checkTotalChildBpoQty){
            
//            BindingContext bctx = BindingContext.getCurrent();
//            BindingContainer bindings = bctx.getCurrentBindingsEntry();
//            OperationBinding operationBinding =
//                bindings.getOperationBinding("Commit");
//            operationBinding.execute();
//            //createOrderLine();
         am.getDBTransaction().commit();
            createTBAOrder();
            
            ViewObject ChildBpoVo = am.getMnjPrecostCreateBpoDVO1();
            refreshQueryKeepingCurrentRow(ChildBpoVo ) ;
        }
        else{
            FacesMessage fm = new FacesMessage("BPO Qty cann't be greater than TBA Qty");
            fm.setSeverity(FacesMessage.SEVERITY_INFO);
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, fm);
          //  AdfFacesContext.getCurrentInstance().addPartialTarget(subCostingTable);
        }
        
          
       
        return null;
    }
    
    public String createOrderLine() {
        
    
        Main m1=new Main();
        
        Boolean checkChildBopQty;
        checkChildBopQty = m1.isBpoQtyLessthanTbaQty();
        if ( checkChildBopQty ){
        
            
            System.out.println("Hedaer id Bean --------->"+getHeaderId().getValue());
            BindingContext bctx = BindingContext.getCurrent();
            BindingContainer bindings = bctx.getCurrentBindingsEntry();
            OperationBinding operationBinding =
                bindings.getOperationBinding("createOrderLines");

            operationBinding.getParamsMap().put("headerId",
                                                getHeaderId().getValue());
            operationBinding.getParamsMap().put("lineId",getLineId());


            //invoke method
            operationBinding.execute();
            if (!operationBinding.getErrors().isEmpty()) {
                System.out.println("if errors-->");
                // List errors = operationBinding.getErrors();
            }
            //optional
            Object methodReturnValue = operationBinding.getResult();
            String message = null;
            if (methodReturnValue != null) {
                message = methodReturnValue.toString();
            } else {
                message = "Failed! .";
            }
            FacesMessage fm = new FacesMessage(message);
            fm.setSeverity(FacesMessage.SEVERITY_INFO);
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, fm);
        }
        else{
            
            FacesMessage fm = new FacesMessage("BPO Qty cann't be greater than TBA Qty");
            fm.setSeverity(FacesMessage.SEVERITY_INFO);
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, fm);
          //  AdfFacesContext.getCurrentInstance().addPartialTarget(subCostingTable);
            
            
        }
        
        
              
        return null;
        
      
        
        
       
    }
    


    public String createTBAOrder() {
        
        System.out.println("Hedaer id Bean --------->"+getHeaderId().getValue());
        BindingContext bctx = BindingContext.getCurrent();
        BindingContainer bindings = bctx.getCurrentBindingsEntry();
        OperationBinding operationBinding =
            bindings.getOperationBinding("createTBAOrder");
     operationBinding.getParamsMap().put("headerId",
                                            getHeaderId().getValue());


        //invoke method
        operationBinding.execute();
        if (!operationBinding.getErrors().isEmpty()) {
            System.out.println("if errors-->");
            // List errors = operationBinding.getErrors();
        }
        //optional
        Object methodReturnValue = operationBinding.getResult();
        String message = null;
        if (methodReturnValue != null) {
            message = methodReturnValue.toString();
        } else {
            message = "Failed! .";
        }
        FacesMessage fm = new FacesMessage(message);
        fm.setSeverity(FacesMessage.SEVERITY_INFO);
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, fm);
        return null;
    }



    public String UpdateBPO_SaleOrder() {
        trimsBPOs();
//        BindingContext bctx = BindingContext.getCurrent();
//        BindingContainer bindings = bctx.getCurrentBindingsEntry();
//        OperationBinding operationBinding =
//            bindings.getOperationBinding("Commit");
//        operationBinding.execute();
         am.getDBTransaction().commit();
        UpdateBPO();
        return null;
    }


    public String UpdateBPO() {
        
        System.out.println("Hedaer id Bean --------->"+getHeaderId().getValue());
        BindingContext bctx = BindingContext.getCurrent();
        BindingContainer bindings = bctx.getCurrentBindingsEntry();
        OperationBinding operationBinding =
            bindings.getOperationBinding("UpdateBPO");

        operationBinding.getParamsMap().put("headerId",
                                            getHeaderId().getValue());


        //invoke method
        operationBinding.execute();
        if (!operationBinding.getErrors().isEmpty()) {
            System.out.println("if errors-->");
            // List errors = operationBinding.getErrors();
        }
        //optional
        Object methodReturnValue = operationBinding.getResult();
        String message = null;
        if (methodReturnValue != null) {
            message = methodReturnValue.toString();
        } else {
            message = "Failed! .";
        }
        FacesMessage fm = new FacesMessage(message);
        fm.setSeverity(FacesMessage.SEVERITY_INFO);
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, fm);
        return null;
    }



    public String CancelBPO() {
        
        System.out.println("Hedaer id Bean --------->"+getHeaderId().getValue());
        BindingContext bctx = BindingContext.getCurrent();
        BindingContainer bindings = bctx.getCurrentBindingsEntry();
        OperationBinding operationBinding =
            bindings.getOperationBinding("CancelBPO");

        operationBinding.getParamsMap().put("headerId",
                                            getHeaderId().getValue());


        //invoke method
        operationBinding.execute();
        if (!operationBinding.getErrors().isEmpty()) {
            System.out.println("if errors-->");
            // List errors = operationBinding.getErrors();
        }
        //optional
        Object methodReturnValue = operationBinding.getResult();
        String message = null;
       /* if (methodReturnValue != null) {
            message = methodReturnValue.toString();
        } else {
            message = "Failed! .";
        } */
        
       message = "Order Cancelled Successfully..";
        FacesMessage fm = new FacesMessage(message);
        fm.setSeverity(FacesMessage.SEVERITY_INFO);
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, fm);
        return null;
    }
 
   

    public String CancelBPO_SaleOrder() {
        
//        BindingContext bctx = BindingContext.getCurrent();
//        BindingContainer bindings = bctx.getCurrentBindingsEntry();
//        OperationBinding operationBinding =
//            bindings.getOperationBinding("Commit");
//        operationBinding.execute();
         am.getDBTransaction().commit();
        CancelBPO();
        return null;
    }


    public double getTBATotalQty() {

        BindingContext bindingContext = BindingContext.getCurrent();
        DCDataControl dc =
            bindingContext.findDataControl("AppModuleDataControl"); //
        ApplicationModule am = dc.getApplicationModule();
        ViewObject findViewObject =am.findViewObject("MnjPrecostCreateBpoDVO1"); //ImpSaleContractDetailEOView1
            
        RowSetIterator it = findViewObject.createRowSetIterator("tt");
        double val = 0.00, total = 0.00;
        while (it.hasNext()) {

            Row r = it.next();
            try {
                val = Double.parseDouble(r.getAttribute("BpoQty").toString());
            } catch (Exception e) {
                ;
            }

            total = total + val;
        } //end of while loop
        it.closeRowSetIterator();
        //return roundTo2(total);
        return total;
        
    }


    public void setTbAQty_Bind(RichOutputText tbAQty_Bind) {
        this.tbAQty_Bind = tbAQty_Bind;
    }

    public RichOutputText getTbAQty_Bind() {
        return tbAQty_Bind;
    }
    
    public void fileUploadMarker(ValueChangeEvent valueChangeEvent) {
        // Add event code here...
        //System.out.println("Roll Upload");
        
        // Delete Temporaty Table
        //ClearTempUploadBlock();
            
        UploadedFile file = (UploadedFile)valueChangeEvent.getNewValue();
        try {
            System.out.println("Level 1");
            ClearTempUploadBlock();
            parseFile(file.getInputStream());
            System.out.println("Level 2");
            
            save();
            
            UploadBPO_Block();
            System.out.println("Level 3");
            ViewObject createbpoDetVO=am.getMnjPrecostCreateBpoDVO1();
            createbpoDetVO.executeQuery();
            AdfFacesContext.getCurrentInstance().addPartialTarget(bpoLinetable);
            am.getDBTransaction().commit();
            
            //save();
              
        } 
        catch (IOException e) {
            remove_POC_Upload();
            // save();                 
                              }
    }
    
    /**
     * Generic Method to execute operation
     * */
    public OperationBinding executeOperation(String operation) {
        OperationBinding createParam =
            getBindingsCont().getOperationBinding(operation);
        return createParam;
    }

    /*****Generic Method to Get BindingContainer**/
    public BindingContainer getBindingsCont() {
        return BindingContext.getCurrent().getCurrentBindingsEntry();
    }



    public void UploadBPO_Block(){
            OperationBinding ob= executeOperation("UploadBPO_Block");
            ob.execute();
               // AdfFacesContext.getCurrentInstance().addPartialTarget(bpoLinetable);
            save();
             
            }



    public void save(){
//            OperationBinding ob= executeOperation("Commit");
//            ob.execute();
            am.getDBTransaction().commit();
            }
    
    public void parseFile(java.io.InputStream file) {
        int vHeaderId=0,vLineId=0;
            
        /********************************/
        oracle.adf.view.rich.component.UIXTable table = getBpOHTable();
        java.util.Iterator selectionIt = table.getSelectedRowKeys().iterator();
        while (selectionIt.hasNext()) {
                    Object rowKey = selectionIt.next();
                    table.setRowKey(rowKey);
                
        int index = table.getRowIndex();
        FacesCtrlHierNodeBinding row =(FacesCtrlHierNodeBinding)table.getRowData(index);
        Row selectedRow = row.getRow();
        vHeaderId=Integer.parseInt(selectedRow.getAttribute("HeaderId").toString());
        vLineId=Integer.parseInt(selectedRow.getAttribute("LineId").toString());
         //System.out.println("vHeaderId-------->"+vHeaderId);
        //System.out.println("vLineId-------->"+vLineId);
        }
        
        /********************************/
        
        System.out.println("Parse File --->" + file);

        BufferedReader reader =  new BufferedReader(new InputStreamReader(file));
        String strLine = "";
        StringTokenizer st = null;
        int lineNumber = 0, tokenNumber = 0;
        Row hrw = null, lineRow = null;
        HashMap map = new HashMap();
        BindingContext bindingContext = BindingContext.getCurrent();
        DCDataControl dc =  bindingContext.findDataControl("AppModuleDataControl");
        ApplicationModule am = dc.getApplicationModule();
        ViewObject lineVo = am.findViewObject("MNJPOC_Bpo_UploadVO1");
        try {
            while ((strLine = reader.readLine()) != null) {
                lineNumber++;
                st = new StringTokenizer(strLine, ",");
            //    System.out.println("Line No --->" + lineNumber);
                if (lineNumber > 1) {
                    hrw = lineVo.createRow();
                    hrw.setNewRowState(Row.STATUS_INITIALIZED);
                    lineVo.insertRow(hrw);
                  //  System.out.println("Level 123----------->"+lineNumber);
                    
                }

                while (st.hasMoreTokens()) {
                    //display csv values
                    tokenNumber++;

                    String theToken = st.nextToken();
                       
                    if (lineNumber > 1) {

                        //System.out.println("Level 1 --->"+lineNumber+"--------"+tokenNumber+" ---------"+theToken);
                                             
                        switch (tokenNumber) {
                        case 1:
                           // System.out.println("Level 2 --->" + vHeaderId+" ---------"+theToken);
                           
                            hrw.setAttribute("BpoNo",theToken);
                            hrw.setAttribute("HeaderId",vHeaderId);
                            hrw.setAttribute("LineId",vLineId);
                            
                          // System.out.println("Level 3 --->" + tokenNumber+" ---------"+theToken);
                               break ;
                         case 2:
                            System.out.println("Line No Case --->" + tokenNumber + theToken);
                             hrw.setAttribute("BpoQty",theToken);
                             System.out.println("Line No Case --->" + tokenNumber + theToken);
                             break ;
                        
                            case 3:
                                  System.out.println("DeliveryStartDate --->" + theToken);
                                   hrw.setAttribute("DeliveryStartDate",castToJBODate(theToken));
                                  
                                   break ;
                        
                        case 4:
                            hrw.setAttribute("Classific", theToken);
                            break ;
                        
                        case 5:
                                hrw.setAttribute("DeliveryTerms", theToken);
                                break ;
                        case 6:
                            hrw.setAttribute("PoType", theToken);
                            break ;
                       
//                       case 7:
//                            hrw.setAttribute("ReplaceBpo",theToken);
//                            break ;
//                       case 8:
//                             hrw.setAttribute("Country", theToken);
//                             break ;
//                       case 9:
//                             hrw.setAttribute("ShipMode", theToken);
//                             break ;
//                     
//                    case 10:
//                            hrw.setAttribute("DeliveryEndDate",castToJBODate(theToken));
//                            break ;
//                    case 11:
//                            hrw.setAttribute("BpoStatus", theToken);
//                            break ;
                        }
                        //System.out.println("Before --->" + vMarkerefficiency);
                        //System.out.println("Before 1 --->" + vYds);
                    }
                }
                tokenNumber = 0;
            }
            map = null;
            reader = null;

        } catch (Exception e) {
            FacesContext fctx = FacesContext.getCurrentInstance();
            fctx.addMessage(null,
                            new FacesMessage(FacesMessage.SEVERITY_ERROR, "Data Error in Uploaded file",
                                             e.getMessage()));
        }

    } 

   
    public void ClearTempUploadBlock() {
        
        BindingContext bctx = BindingContext.getCurrent();
        BindingContainer bindings = bctx.getCurrentBindingsEntry();
        OperationBinding operationBinding =
            bindings.getOperationBinding("ClearTempUploadBlock");
        operationBinding.execute();
        if (!operationBinding.getErrors().isEmpty()) {
            System.out.println("if errors-->");
            // List errors = operationBinding.getErrors();
        }
        
        }
    
    
    
    public void remove_POC_Upload() {
                
         BindingContext bindingContext = BindingContext.getCurrent();
        DCDataControl dc =
            bindingContext.findDataControl("AppModuleDataControl"); //
        ApplicationModule am = dc.getApplicationModule();
        ViewObject findViewObject =am.findViewObject("MNJPOC_Bpo_UploadVO1"); //ImpSaleContractDetailEOView1
            
        RowSetIterator it = findViewObject.createRowSetIterator("tt");
        double val = 0.00, total = 0.00;
        while (it.hasNext()) {

            try {
                  it.removeCurrentRow();
                } catch (Exception e) {
                ;
            }
         } //end of while loop
        it.closeRowSetIterator();  
    }


    public void GetTotalQty_TBA(ActionEvent actionEvent) {
        // Add event code here...
    System.out.println("TBA BPO Sum Qty------>>");
    
        // Add event code here...
        System.out.println(" Total BPO Qty------------>"+getBPOTotalQty_TBA());
        tbAQty_Bind.setValue(getBPOTotalQty_TBA());
        AdfFacesContext.getCurrentInstance().addPartialTarget(tbAQty_Bind);
        System.out.println(" Total BPO Qty 2------------>"+tbAQty_Bind.getValue().toString());
        
        }


        
        public double getBPOTotalQty_TBA() {

        System.out.println("Level 1");
        BindingContext bindingContext = BindingContext.getCurrent();
        DCDataControl dc =
            bindingContext.findDataControl("AppModuleDataControl"); //
        ApplicationModule am = dc.getApplicationModule();
        ViewObject findViewObject =am.findViewObject("MnjPrecostCreateBpoDVO1"); //ImpSaleContractDetailEOView1
        
        System.out.println("Level 2");
                 
        RowSetIterator it = findViewObject.createRowSetIterator("tt");
        double val = 0.00, total = 0.00;
        while (it.hasNext()) {

            Row r = it.next();
            try {
                val = Double.parseDouble(r.getAttribute("BpoQty").toString());
            } catch (Exception e) {
                it.closeRowSetIterator();
                
            }

            total = total + val;
        } //end of while loop
        it.closeRowSetIterator();
        //return roundTo2(total);
        System.out.println("Level 3 ---->"+total);
        
        return total;
        
        }
        
        
    
    //---------------------------------------------csv upload-------created by farabi---23.10.2017------------------------------------------------------------

    public ApplicationModule getAppM(){
        DCBindingContainer bindingContainer =
            (DCBindingContainer)BindingContext.getCurrent().getCurrentBindingsEntry();
        //BindingContext bindingContext = BindingContext.getCurrent();
        DCDataControl dc =
            bindingContainer.findDataControl("AppModuleDataControl"); // Name of application module in datacontrolBinding.cpx
        AppModuleImpl appM = (AppModuleImpl)dc.getDataProvider();
        return appM;
    }
    AppModuleImpl am = (AppModuleImpl)this.getAppM();
    

    public void bpoUpload(ValueChangeEvent valueChangeEvent) {
      // Add event code here...
          UploadedFile file = (UploadedFile)valueChangeEvent.getNewValue();
          try {
             // clearSizeBreakDownTable();
              parseFile2(file.getInputStream());
              AdfFacesContext.getCurrentInstance().addPartialTarget(bpOHTable);
              am.getDBTransaction().commit();
          } catch (IOException e) {
          // TODO
          }
      }
      
      public void clearSizeBreakDownTable(){
          
          am.getDBTransaction().commit();
          
          ViewObject vo =  am.getCreateSaleOrderLinesVO1();
          RowSetIterator it = vo.createRowSetIterator("bpo upload csv");
          while(it.hasNext()){
              it.next().remove();
          }
          vo.executeEmptyRowSet();
         it.closeRowSetIterator();
          
      }
      
      public void parseFile2(java.io.InputStream file) {
          BufferedReader reader = new BufferedReader(new InputStreamReader(file));
          String strLine = "";
          StringTokenizer st = null;
          int lineNumber = 0, tokenNumber = 0;
          Row rw = null;
           
          ViewObject vo =  am.getCreateSaleOrderLinesVO1();      

          //read comma separated file line by line
          try
          {
              //while(it.hasNext()){
                  while ((strLine = reader.readLine()) != null)
                  {
                      lineNumber++;
                      // create a new row skip the header (header has linenumber 1)
                      if (lineNumber>1) {
                          rw = MakeLinesClone();     
                      }
                       
                      //break comma separated line using ","
                      //st = new StringTokenizer(strLine, ",");
                      //while (st.hasMoreTokens()){
    
                    String[] csvCols = strLine.split(",");   
                        
                    for (;tokenNumber < csvCols.length ; tokenNumber++)
                    {
                          //display csv values
                          //tokenNumber++;
                          //String theToken = st.nextToken();
    
                          String theToken = csvCols[tokenNumber];
    
                          System.out.println("Line # " + lineNumber + ", Token # " +
                          tokenNumber +
                          ", Token : " + theToken);
                          
                          if (lineNumber>1){
                              // set Attribute Values
                              switch (tokenNumber) {
    //                                      case 0: rw.setAttribute("WashName", theToken);
    //                                              break;
    //                                      case 1: rw.setAttribute("Colour", theToken);
    //                                              break;
    //                                      case 2: rw.setAttribute("FobWitoutComm", theToken);
    //                                              rw.setAttribute("FobWithComm", theToken);
    //                                              rw.setAttribute("FobWithSmsSample", theToken);
    //                                              break;
                                  case 0: rw.setAttribute("BpoNo", theToken);
                                          break;
                                  case 1: rw.setAttribute("BpoQty", theToken);
                                          break;
                                  case 2: rw.setAttribute("DeliveryStartDate", castToJBODate(theToken));
                                          break;
                                  case 3: rw.setAttribute("Clasfic", theToken);
                                          break;
                                  case 4: rw.setAttribute("Incoterms", theToken);
                                          break;
                                  case 5: rw.setAttribute("PoType", theToken);
                                          break;
//                                 case 7: rw.setAttribute("ReplaceBpo", theToken);
//                                          break;
//                                  case 7: rw.setAttribute("Country", theToken);
//                                        break;
//                                  case 8: rw.setAttribute("ShipMode", theToken);
//                                          break;
//                                
//                                  case 9: rw.setAttribute("DeliveryEndDate", castToJBODate(theToken));
//                                          break;
                                         
                              }
                          }
                      }
                      //reset token number
                      tokenNumber = 0;
                  }
             // }
          }
          catch (IOException e) {
          // TODO add more
             
          }
          catch (Exception e) {
          FacesContext fctx = FacesContext.getCurrentInstance();
          fctx.addMessage( null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
          "Data Error in Uploaded file", e.getMessage()));
          }
      }
      
    public Row MakeLinesClone() {

    ViewObject cloneVo = am.getCreateSaleOrderLinesVO1();

    Row currRow = cloneVo.getCurrentRow();
    Row cloneRow = am.getClonLine(cloneVo);

    cloneRow.setAttribute("CloneFlag", "Y");

    try {
        cloneRow.setAttribute("HeaderNumber",
                              currRow.getAttribute("HeaderNumber"));
    } catch (Exception e) {
        ;
    }
    try {
        cloneRow.setAttribute("FabricDesc",
                              currRow.getAttribute("FabricDesc"));
    } catch (Exception e) {
        ;
    }

    try {
        cloneRow.setAttribute("WashName",
                              currRow.getAttribute("WashName"));
    } catch (Exception e) {
        ;
    }
    try {
        cloneRow.setAttribute("Colour", currRow.getAttribute("Colour"));
    } catch (Exception e) {
        ;
    }
    try {
        cloneRow.setAttribute("FobWitoutComm",
                              currRow.getAttribute("FobWitoutComm"));
    } catch (Exception e) {
        ;
    }
    try {
        cloneRow.setAttribute("FobWithComm",
                              currRow.getAttribute("FobWithComm"));
    } catch (Exception e) {
        ;
    }
    try {
        cloneRow.setAttribute("FobWithSmsSample",
                              currRow.getAttribute("FobWithSmsSample"));
    } catch (Exception e) {
        ;
    }
    try {
        cloneRow.setAttribute("CloneRowId",
                              currRow.getAttribute("LineId"));
    } catch (Exception e) {
        ;
    }
    try {
        cloneRow.setAttribute("ItemId", currRow.getAttribute("ItemId"));
    } catch (Exception e) {
        ;
    }
    try {
        cloneRow.setAttribute("RequestId",
                              currRow.getAttribute("RequestId"));
    } catch (Exception e) {
        ;
    }

    try {
        cloneRow.setAttribute("ChildStyleNo",
                              currRow.getAttribute("ChildStyleNo"));
    } catch (Exception e) {
        ;
    }

    return cloneRow;
    }
      
    public void exportToCSV(FacesContext facesContext,
        OutputStream outputStream) throws IOException {
        // Add event code here...
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(outputStream,"UTF-8"));
        
        writer.write("BPO/TBA No");writer.write(",");
        writer.write("BPO/TBA Qty");writer.write(",");
        writer.write("Delivery start date");writer.write(",");
        writer.write("Classification");writer.write(",");
        writer.write("Delivery term");writer.write(",");
        writer.write("PO type");writer.write(",");
       // writer.write("Replace BPO");writer.write(",");
       // writer.write("Country");writer.write(",");
       // writer.write("Ship Mode");writer.write(",");
       // writer.write("Delivery end date");writer.write(",");
        writer.newLine();
        writer.flush();
        writer.close();
    }
    
    public void exportToCSV2(FacesContext facesContext,
        OutputStream outputStream) throws IOException {
        // Add event code here...
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(outputStream,"UTF-8"));
        
        writer.write("BPO No");writer.write(",");
        writer.write("BPO Qty");writer.write(",");
        writer.write("Delivery start date");writer.write(",");
        writer.write("Classification");writer.write(",");
        writer.write("Delivery term");writer.write(",");
        writer.write("PO type");writer.write(",");
        writer.write("Replace BPO");writer.write(",");
//        writer.write("Country");writer.write(",");
//        writer.write("Ship Mode");writer.write(","); 
//        writer.write("Delivery end date");writer.write(",");
        //writer.write("BPO Status");writer.write(",");

        writer.newLine();
        writer.flush();
        writer.close();
    }

    public void sizeUpload(ValueChangeEvent valueChangeEvent) {
        // Add event code here...
        // Add event code here...
         UploadedFile file = (UploadedFile)valueChangeEvent.getNewValue();
         try {
             clearSizeTable();
             parseFile3(file.getInputStream());
             AdfFacesContext.getCurrentInstance().addPartialTarget(sizeTab);
             am.getDBTransaction().commit();
         } catch (IOException e) {
         // TODO
         }
    }
    
    public void clearSizeTable(){
              
              am.getDBTransaction().commit();
              
              ViewObject vo =  am.getCustMnjOntSoObinsline_DetailVO1();
              RowSetIterator it = vo.createRowSetIterator("aa");
              while(it.hasNext()){
                  it.next().remove();
              }
              vo.executeEmptyRowSet();
             it.closeRowSetIterator();
              
          }
          
          public void parseFile3(java.io.InputStream file) {
              BufferedReader reader = new BufferedReader(new InputStreamReader(file));
              String strLine = "";
              StringTokenizer st = null;
              int lineNumber = 0, tokenNumber = 0;
              Row rw = null;
               
              ViewObject vo = am.getCustMnjOntSoObinsline_DetailVO1();
              
              //read comma separated file line by line
              try
              {
                  while ((strLine = reader.readLine()) != null)
                  {
                      lineNumber++;
                      // create a new row skip the header (header has linenumber 1)
                      if (lineNumber>1) {
                          rw = vo.createRow();
                          rw.setNewRowState(Row.STATUS_INITIALIZED);
                          vo.insertRow(rw);
                      }
                       
                      //break comma separated line using ","
                      //st = new StringTokenizer(strLine, ",");
                      //while (st.hasMoreTokens()){

                    String[] csvCols = strLine.split(",");   
                        
                    for (;tokenNumber < csvCols.length ; tokenNumber++)
                    {
                          //display csv values
                          //tokenNumber++;
                          //String theToken = st.nextToken();

                          String theToken = csvCols[tokenNumber];

                          System.out.println("Line # " + lineNumber + ", Token # " +
                          tokenNumber +
                          ", Token : " + theToken);
                          
                          if (lineNumber>1){
                              // set Attribute Values
                              switch (tokenNumber) {
                                  case 0: rw.setAttribute("InseamSizeConcat", theToken);
                                          break;
                                  case 1: rw.setAttribute("SizeQty", theToken);
                                          break;
                                         
                              }
                          }
                      }
                      //reset token number
                      tokenNumber = 0;
                  }
              }
              catch (IOException e) {
              // TODO add more
                  FacesContext fctx = FacesContext.getCurrentInstance();
                  fctx.addMessage(sizeTab.getClientId(fctx), new FacesMessage(FacesMessage.SEVERITY_ERROR,
                  "Content Error in Uploaded file", e.getMessage()));
              }
              catch (Exception e) {
              FacesContext fctx = FacesContext.getCurrentInstance();
              fctx.addMessage( null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
              "Data Error in Uploaded file", e.getMessage()));
              }
          }

    public void exportToCSV3(FacesContext facesContext,
                             OutputStream outputStream) throws IOException{
        // Add event code here...
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(outputStream,"UTF-8"));
        
        writer.write("*Size");writer.write(",");
        writer.write("*Qty");writer.write(",");

        writer.newLine();
        writer.flush();
        writer.close();
    }

    public void setSizeTab(RichTable sizeTab) {
        this.sizeTab = sizeTab;
    }

    public RichTable getSizeTab() {
        return sizeTab;
    }
    
    public void refreshQueryKeepingCurrentRow(ViewObject viewObject )  {
        
        
         Row currentRow;
         Key currentRowKey;
         
         // added on 7.jan.18 to handle exception if view object has no current row
        try{
           currentRow = viewObject.getCurrentRow();
           currentRowKey = currentRow.getKey();
        }
        catch(Exception e){
            return;
        }
        
     
       
      
       int rangePosOfCurrentRow = viewObject.getRangeIndexOf(currentRow);
       int rangeStartBeforeQuery = viewObject.getRangeStart();
       viewObject.executeQuery();
       viewObject.setRangeStart(rangeStartBeforeQuery);
       /*
        * In 10.1.2, there is this convenience method we could use
        * instead of the remaining lines of code
        *
        *  findAndSetCurrentRowByKey(currentRowKey,rangePosOfCurrentRow);
        *  
        */
       
         
       Row[] rows = viewObject.findByKey(currentRowKey, 1);
       if (rows != null && rows.length == 1)
       {
          viewObject.scrollRangeTo(rows[0], rangePosOfCurrentRow);
          viewObject.setCurrentRowAtRangeIndex(viewObject.getRangeIndexOf(rows[0]));
       }
       
               
     }


    public void setTotalBpoQuantity(RichOutputText totalBpoQuantity) {
        this.totalBpoQuantity = totalBpoQuantity;
    }

    public RichOutputText getTotalBpoQuantity() {
        return totalBpoQuantity;
    }

    public void SLT_ALL(ActionEvent actionEvent) {
        // Add event code here...
        ViewObject selectCreateSaleOrderView = am.getCreateSaleOrderLinesVO1();
        Row[] r = selectCreateSaleOrderView.getAllRowsInRange();
        
        for (Row row : r) {
            row.setAttribute("Flag", "Y");
            System.out.println("OK");
        }
         AdfFacesContext.getCurrentInstance().addPartialTarget(bpOHTable);
    }

    public void DSLT_ALL(ActionEvent actionEvent) {
        // Add event code here...
        ViewObject selectCreateSaleOrderView = am.getCreateSaleOrderLinesVO1();
        Row[] r = selectCreateSaleOrderView.getAllRowsInRange();
        
        for (Row row : r) {
            row.setAttribute("Flag", "N");
            System.out.println("OK");
        }
         AdfFacesContext.getCurrentInstance().addPartialTarget(bpOHTable);
    }

    public void slt_all_bpo(ActionEvent actionEvent) {
        // Add event code here...
        
        ViewObject selectCreateBpoView = am.getMnjPrecostCreateBpoDVO1();
        Row[] r = selectCreateBpoView.getAllRowsInRange();
        
        for (Row row : r) {
            row.setAttribute("FlagD", "Y");
            System.out.println("OK");
        }
         AdfFacesContext.getCurrentInstance().addPartialTarget(bpoLinetable);
    }

    public void deSeltAllbpo(ActionEvent actionEvent) {
        // Add event code here...
        ViewObject selectCreateBpoView = am.getMnjPrecostCreateBpoDVO1();
        Row[] r = selectCreateBpoView.getAllRowsInRange();
        
        for (Row row : r) {
            row.setAttribute("FlagD", "N");
            System.out.println("OK");
        }
         AdfFacesContext.getCurrentInstance().addPartialTarget(bpoLinetable);
        }
    
    /**
    *Converts a String to oracle.jbo.domain.Date
    * @param String
    * @return oracle.jbo.domain.Date
    */
    public oracle.jbo.domain.Date castToJBODate(String aDate){
              DateFormat formatter;
              java.util.Date date; 
     
              if(aDate!=null){
     
                try {
     
                  formatter = new SimpleDateFormat("dd-MMM-yyyy");
                  date = formatter.parse(aDate);
                  java.sql.Date sqlDate = new java.sql.Date(date.getTime());
                  oracle.jbo.domain.Date jboDate = new oracle.jbo.domain.Date(sqlDate);
                
                  System.out.println("#### Date: ####" + jboDate);
                    return jboDate;
                   } catch (Exception e) {
     
                            e.printStackTrace();
                    }
     
             }
     
             return null;
    }


    public void setCreateBPO(RichPanelHeader createBPO) {
        this.createBPO = createBPO;
    }

    public RichPanelHeader getCreateBPO() {
        return createBPO;
    }
    
    private void trimsBPOs() {
        ViewObject saleOrderLine =am.getCreateSaleOrderLinesVO1();
        String bpo="",detBpo="";
        
        
        System.out.println("Method called ======================>");
                RowSetIterator it = saleOrderLine.createRowSetIterator("tt");
                Row currentRow = saleOrderLine.getCurrentRow();
                int count =0;
                while (it.hasNext()) {
                    count++;
                    Row r = it.next();
                  
                    
                        
                        try{
                          bpo=r.getAttribute("BpoNo").toString().trim();
                           r.setAttribute("BpoNo",bpo);
                        }
                        catch(Exception e){
                            ;
                        } 
                        
                        
                    //trims bpo for each line's detail  *==========================//
                    RowSet child = (RowSet)r.getAttribute("MnjPrecostCreateBpoDVO");
                               Row[] rr = child.getAllRowsInRange();
                    System.out.println("number of details bpo line:"+rr.length);
                    
                    for (Row ro : rr) {
                        String dbpo="";
                        try{
                          dbpo=ro.getAttribute("BpoNo").toString().trim();
                           ro.setAttribute("BpoNo",dbpo);
                        }
                        catch(Exception e){
                            ;
                        } 
                        
                    }


                  //  trimDetailsBPO()
                   

                   
                    
                }
                it.closeRowSetIterator();
                System.out.println("total return from method " + count);
              


    }
    
    
}


