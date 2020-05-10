package view.backing.bean;

import java.io.BufferedReader;
import java.math.BigDecimal;

import java.sql.SQLException;

import java.text.DecimalFormat;
import java.io.IOException;
import java.io.InputStreamReader;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.text.DateFormat;


import java.text.SimpleDateFormat;

import java.util.HashMap;
import java.util.StringTokenizer;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIViewRoot;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.event.ValueChangeEvent;

import javax.faces.validator.ValidatorException;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import mnj.mfg.model.services.AppModuleImpl;

import oracle.adf.model.BindingContext;

import oracle.adf.model.binding.DCBindingContainer;
import oracle.adf.model.binding.DCDataControl;
import oracle.adf.model.binding.DCIteratorBinding;
import oracle.adf.view.rich.component.rich.RichForm;
import oracle.adf.view.rich.component.rich.RichPopup;
import oracle.adf.view.rich.component.rich.RichPopup.PopupHints;
import oracle.adf.view.rich.component.rich.data.RichColumn;
import oracle.adf.view.rich.component.rich.data.RichTable;
import oracle.adf.view.rich.component.rich.input.RichInputListOfValues;
import oracle.adf.view.rich.component.rich.input.RichInputText;

import oracle.adf.view.rich.component.rich.input.RichSelectItem;
import oracle.adf.view.rich.component.rich.input.RichSelectOneRadio;
import oracle.adf.view.rich.component.rich.nav.RichCommandButton;
import oracle.adf.view.rich.component.rich.output.RichOutputText;
import oracle.adf.view.rich.component.rich.output.RichPanelCollection;
import oracle.adf.view.rich.context.AdfFacesContext;

import oracle.adf.view.rich.event.DialogEvent;
import oracle.adf.view.rich.event.LaunchPopupEvent;
import oracle.adf.view.rich.event.PopupCanceledEvent;

import oracle.adf.view.rich.event.PopupFetchEvent;

import oracle.adfinternal.view.faces.model.binding.FacesCtrlHierNodeBinding;

import oracle.binding.BindingContainer;
import oracle.binding.OperationBinding;

import oracle.igf.ids.Org;

import oracle.jbo.ApplicationModule;
import oracle.jbo.Key;
import oracle.jbo.Row;
import oracle.jbo.RowSet;
import oracle.jbo.RowSetIterator;
import oracle.jbo.ViewObject;
import oracle.jbo.domain.Date;
import oracle.jbo.domain.Number;

import oracle.jdbc.OracleTypes;

import org.apache.myfaces.trinidad.model.UploadedFile;
import org.apache.myfaces.trinidad.render.ExtendedRenderKitService;
import org.apache.myfaces.trinidad.util.Service;

import utils.system;

public class Main {
    private Object otherCharge;
    private Object labTestcharge;
    private Object print;
    private Object commission;
    private RichInputText smsQty;
    private RichInputText orderQty;
    private Object fabricFinance;
    private Object washFinance;
    private RichInputText cmMerchandiser;
    private RichInputText costingMOU;
    private Object trimFinance;
    private RichInputText cm;
    private RichInputText reflectedProfit;
    private RichInputText cost;
    private RichInputText finance;
    private RichInputText profit;
    private RichInputText costPerline;
    private RichInputText producPerLine;
    private RichTable itemTable;
    private RichInputText actualUnitPrice;
    private RichInputText addPriceMOQ;
    private RichInputText finalUnitPrice;
    private RichInputText consPerPcs;
    private RichInputText wasteInPercent;
    private RichInputText wasteInQty;
    private RichInputText consWithWaste;
    private RichInputText buffer;
    private RichInputText finalCons;
    private RichInputText costPerPcs;
    private RichInputText finalCostPerPcs;
    private RichInputText addDeductCost;
    private RichInputText projectionNo;
    private RichInputListOfValues productionUnit;
    private RichOutputText totalFinalCostPerPCS;
    private RichInputText dryCost;
    private RichInputText dryProfit;
    private RichInputText dryTotal;
    private RichInputText dryManualPrice;
    private RichInputText wetCost;
    private RichInputText wetProfit;
    private RichInputText wetTotal;
    private RichInputText wetManualPrice;
    private RichOutputText grandDryTotal;
    private RichOutputText grandWetTotal;
    private RichInputText foBWithOutComm;
    private RichTable subCostingTable;
    private RichSelectOneRadio selectRadio;
    private RichInputText headerID;
    private RichInputText addChargLC;
    private RichInputText addPriceFOB;
    private RichInputText addChrgPrcnt;
    private RichInputText uomConversion;
    private RichTable othersTable;
    private RichInputText testType;
    private RichInputText cmMerchDzn;
    private RichInputText targetPrice;
    private RichInputText difference;
    private RichInputText fabFinanceCost;
    private RichInputListOfValues prefixName;
    private RichInputText prefixCode;
    private RichInputText prodCostPerLine;
    private RichInputText sampleDocNo;
    private RichOutputText totalBpoQty;
    private RichOutputText tbAQty_Bind;
    private RichTable dryDetailsTable;
    private RichTable wetDetailsTable; 
    public int bpoTableBlock=0;    //  if set to 0 then sales order block , if 1 then create bpo block
    
    public static Row currentBpoRow;
    public static String currentBpo;
    
    RichPopup p1 ;
    private RichForm createOrderForm;
    private RichColumn remarkAction;
    private RichCommandButton sizeBreakdownButton;
    private RichInputText bpoQt_value;
    private RichPopup servicePopup;
    private RichSelectOneRadio selectedServiceItem;
    private RichInputText fabricFinancePrice;
    private RichInputText cmMerchad;
    private RichInputText productivity;
    private RichInputText styleEff;
    private RichInputText samValue;
    private RichPanelCollection createBpoTab;
    private RichInputText lineBPO;
    static DecimalFormat format = new DecimalFormat("#");
    private RichInputText trimsFinance;
    private RichInputText washFinanceValue;
    private RichTable breakdownQtyTable;
    private RichInputText balanceQtyForTBA;
    private RichInputText fabricD;
    private RichInputText fabricC;

    public Main() {
        
        
    }
    
    
    
  


    public BindingContainer getBindings() {
        return BindingContext.getCurrent().getCurrentBindingsEntry();
    }

    public void setOtherCharge(Object otherCharge) {
        this.otherCharge = otherCharge;

    }

    public Object getOtherCharge() {
        return otherCharge;
    }

    public void setLabTestcharge(Object labTestcharge) {
        this.labTestcharge = labTestcharge;
    }

    public Object getLabTestcharge() {
        return labTestcharge;
    }

    public void setPrint(Object print) {
        this.print = print;
    }

    public Object getPrint() {
        return print;
    }

    public void setCommission(Object commission) {
        this.commission = commission;
    }

    public Object getCommission() {
        return commission;
    }

    public void setSmsQty(RichInputText smsQty) {
        this.smsQty = smsQty;

    }

    public RichInputText getSmsQty() {
        return smsQty;
    }

    public void setOrderQty(RichInputText orderQty) {
        this.orderQty = orderQty;
    }

    public RichInputText getOrderQty() {
        return orderQty;
    }

    public void setFabricFinance(Object fabricFinance) {
        this.fabricFinance = fabricFinance;
    }

    public Object getFabricFinance() {
        return fabricFinance;
    }

    public void setWashFinance(Object washFinance) {
        this.washFinance = washFinance;
    }

    public Object getWashFinance() {
        return washFinance;
    }

    public void setCmMerchandiser(RichInputText cmMerchandiser) {
        this.cmMerchandiser = cmMerchandiser;
    }

    public RichInputText getCmMerchandiser() {
        return cmMerchandiser;
    }

    public void setCostingMOU(RichInputText costingMOU) {
        this.costingMOU = costingMOU;
    }

    public RichInputText getCostingMOU() {
        return costingMOU;
    }

    public void setTrimFinance(Object trimFinance) {
        this.trimFinance = trimFinance;
    }

    public Object getTrimFinance() {
        return trimFinance;
    }


    public void setCm(RichInputText cm) {
        this.cm = cm;
    }

    public RichInputText getCm() {
        return cm;
    }

    public void setReflectedProfit(RichInputText reflectedProfit) {
        this.reflectedProfit = reflectedProfit;
    }

    public RichInputText getReflectedProfit() {
        return reflectedProfit;
    }

    public void setCost(RichInputText cost) {
        this.cost = cost;
    }

    public RichInputText getCost() {
        return cost;
    }

    public void setFinance(RichInputText finance) {
        this.finance = finance;
    }

    public RichInputText getFinance() {
        return finance;
    }

    public void setProfit(RichInputText profit) {
        this.profit = profit;
    }

    public RichInputText getProfit() {
        return profit;
    }

    public void setCostPerline(RichInputText costPerline) {
        this.costPerline = costPerline;
    }

    public RichInputText getCostPerline() {
        return costPerline;
    }

    public void setProducPerLine(RichInputText producPerLine) {
        this.producPerLine = producPerLine;
    }

    public RichInputText getProducPerLine() {
        return producPerLine;
    }

    public void setItemTable(RichTable itemTable) {
        this.itemTable = itemTable;
    }

    public RichTable getItemTable() {
        return itemTable;
    }

    public void setFinanceCalc(int marchand) {
        int check=marchand;

        //  try {

        double fabric = 0.00, fobVal = 0.00, trim = 0.00, finalCostPerPcsVal =
            0.00, washTotalVal = 0.00, financialCostValue = 0.00, totalCMVal =
            0.00, refelectedVal = 0, service = 0.0,wash=0.0;
        DCBindingContainer bindings =
            (DCBindingContainer)BindingContext.getCurrent().getCurrentBindingsEntry();
        DCIteratorBinding dcIteratorBindings =
            bindings.findIteratorBinding("MnjMfgPrecostingItemDView1Iterator");
        // Get all the rows of a iterator
        Row[] rows = dcIteratorBindings.getAllRowsInRange();
        for (Row row : rows) {
            
            /***************************************/
          // refreshFabricFinance(row);
            /***************************************/
            
            oracle.jbo.domain.Number prefix =
                (Number)row.getAttribute("ItemPrefix"); //ItemPrefix
            try {
                finalCostPerPcsVal =
                        Double.parseDouble(row.getAttribute("FinalCostPerPcs").toString());
            } catch (Exception e) {
                ;
            }
            // prefixVal = Integer.parseInt(prefix);
            if (prefix.intValue() >= 11 && prefix.intValue() <= 12) {
                fabric = fabric + finalCostPerPcsVal;
                finalCostPerPcsVal = 0.00;
            } else if ((prefix.intValue() > 12 && prefix.intValue() <= 36) ||prefix.intValue()==00) {
                trim = trim + finalCostPerPcsVal;
                finalCostPerPcsVal = 0.00;
            }
            else if (prefix.intValue()==55){
                service = service + finalCostPerPcsVal;
            }
        } //end of for each loop

      //  double fabricFinanceVal = MyMath.numeric3(getFabricFinance());
     //   double washFinanceVal = MyMath.numeric3(getWashFinance());
     //   double trimFinanceVal = MyMath.numeric3(getTrimFinance());

        washTotalVal = getSelectedDryTotal() + getSelectedWetTotal();
        //            financialCostValue =
        //                    ((fabric * 0.06) - (fabric * (fabricFinanceVal / 100))) +
        //                    ((trim * 0.06) - (trim * (trimFinanceVal / 100))) +
        //                    ((washTotalVal * 0.01) -
        //                     (washTotalVal * (washFinanceVal / 100)));
        /**
        
         **************************************/
//        financialCostValue =
//                ((fabric * getPOCFinanceCost("FABRIC")) - (fabric * (fabricFinanceVal / 100))) +
//                ((trim * getPOCFinanceCost("TRIMS")) - (trim * (trimFinanceVal / 100))) +
//                ((washTotalVal * getPOCFinanceCost("WASH")) -
//                 (washTotalVal * (washFinanceVal / 100)));

      
        double fabricPrice=0.00;
        double trimsPrice=0.00;
        double washPrice=0.00;
        fabricPrice=febricValue();
        trimsPrice=trimValue();
        washPrice=washValue();
        
       System.out.println("...................fabricPrice="+fabricPrice);
        
        double fabricPriceByMrch=0.00;
        try{
            fabricPriceByMrch=Double.parseDouble((getFabricFinancePrice().getValue().toString()));
        }
        catch(Exception e){
            fabricPriceByMrch=0.00;
        }
        
        double trimsPriceByMrch=0.00;
        try{
            trimsPriceByMrch=Double.parseDouble((getTrimsFinance().getValue().toString()));
        }
        catch(Exception e){
            trimsPriceByMrch=0.00;
        }
        double washPriceByMrch=0.00;
        try{
            washPriceByMrch=Double.parseDouble((getWashFinanceValue().getValue().toString()));
        }
        catch(Exception e){
            washPriceByMrch=0.00;
        }
        
        
        System.out.println("...................fabricPrice-fabricPriceByMrch="+fabricPriceByMrch);
        fabricPrice=febricValue()-fabricPriceByMrch;
        //fabric=fabric+((fabric*fabricPriceByMrch)/100);
        
        trimsPrice=trimValue()-trimsPriceByMrch;
       // trim=trim+((trim*trimsPriceByMrch)/100);
        washPrice=washValue()-washPriceByMrch;
        financialCostValue=((fabric*fabricPrice)/100)+((trim*trimsPrice)/100)+((washTotalVal*washPrice)/100);
  
  
       // totalCMVal = financialCostValue + MyMath.numeric(getCost()) + MyMath.numeric(getProfit());
       // double totalcost=0.0;
        ViewObject line=am.getMnjMfgPrecostingLView1();
       // totalcost=financialCostValue + MyMath.numeric(getCost()) ;
      //  line.getCurrentRow().setAttribute("TotalCost",totalcost);
        
        

        finance.setValue(MyMath.toNumber(MyMath.round(financialCostValue)));
        AdfFacesContext.getCurrentInstance().addPartialTarget(finance);


        refelectedVal = MyMath.numeric(getCmMerchandiser()) - MyMath.numeric(getCost()) - financialCostValue;



        System.out.println(fabric+"------------------------FOBS value print ------------>"+trim);
        double otherAdjustMent=0.0;
        try{
            otherAdjustMent=Double.parseDouble(line.getCurrentRow().getAttribute("OtherCharge").toString());
        }
        catch(Exception e){
            otherAdjustMent=0.0;
        }
        service=service+otherAdjustMent;
        
        fabric=fabric+((fabric*fabricPriceByMrch)/100);
        trim=trim+((trim*trimsPriceByMrch)/100);
        washTotalVal=washTotalVal+((washTotalVal*washPriceByMrch)/100);
        
        
        System.out.println("check value fabric="+fabric);
        System.out.println("check value trim="+trim);
        System.out.println("check value wash="+washTotalVal);
        setFOBValues(trim, fabric, washTotalVal, service, financialCostValue,check);

        System.out.println("check done");
        //        } catch (Exception e) {
        //            ;
        //        }

    }
    /***********************************
     * ********Newly added on 22-MAR-16****
     * *****************************************************************************************************/
    public void refreshFabricFinance(Row r ){
        
        int preficCode = (int)MyMath.numeric3(r.getAttribute("ItemPrefix"));
        double fabFinanceCostVal = 0.0;
        
        double costPerPc = MyMath.numeric3(r.getAttribute("CostPerPcs"));
        double addDeduct = MyMath.numeric3(r.getAttribute("AddDeductCost"));
        
        double finalCostPerPcsVal =costPerPc+addDeduct;
        
        System.out.println(preficCode+"New calculation ------------------------------->"+finalCostPerPcsVal);
        
        if(preficCode== 11 || preficCode== 12) {
            fabFinanceCostVal = finalCostPerPcsVal * (MyMath.numeric3(getFabricFinance())/100);   
            
            r.setAttribute("FabFinanceCost",fabFinanceCostVal);
            r.setAttribute("FinalCostPerPcs",(finalCostPerPcsVal+fabFinanceCostVal));
            
        }
        
        
    }
    /**************************************************************************
     * ****************************************************************************************************/

    public void setFOBValues(double trim, double fabric, double washTotalVal,
                              double service,double financialCostValue,int check) {
        
        
                format.setMinimumFractionDigits(2);
               

        ViewObject  MnjLineV=am.getMnjMfgPrecostingLView1();
        ViewObject headerVo=am.getMnjMfgPrecostingHView1();
       double otherVal =service;
        /***********************changed by MT*****************************************/
        double samValue=0.00,smvValue=0.00,factoryAvrgEff=0.00,StyleEff=0.00,newCost=0.00;
        
        try{
            StyleEff =Double.parseDouble((getStyleEff().getValue().toString())); 
        }
        catch(Exception e) {
           StyleEff=0.00;
        }
        
        try{
            samValue =Double.parseDouble((getSamValue().getValue().toString())); 
        }
        catch(Exception e) {
           samValue=0.00;
        }
        factoryAvrgEff=FactoryAvgEff();
        smvValue=SMV();
        MnjLineV.getCurrentRow().setAttribute("FactoryAvgEf", factoryAvrgEff);
        MnjLineV.getCurrentRow().setAttribute("Smv",smvValue);
        if(StyleEff != 0){
            newCost=(((samValue*smvValue)/StyleEff)*factoryAvrgEff);
        }
        
        newCost=MyMath.round(newCost);
        MnjLineV.getCurrentRow().setAttribute("CostNew",newCost);
        double totalcost=0.0;
        
      totalcost=financialCostValue+newCost;
        
        newCost=MyMath.round(newCost);
        MnjLineV.getCurrentRow().setAttribute("CostNew",newCost);
        double CM=0.00;
        try{
            CM=Double.parseDouble((getCmMerchad().getValue().toString()));    
        }
        catch(Exception e){
            CM=0.00;
        }
       
        
        double calProfit=0.00;
        double setProfit=0.00;
        double cmValue=0.00;
        calProfit=MyMath.round(CM-totalcost); 
        cmValue=totalcost+calProfit;  
        
        double setCm=0.00;
        double setManagementCm=0.00;
        if(check==1){
            int set=1;
            headerVo.getCurrentRow().setAttribute("CmCalculation",set);
            setCm=cmValue;
            
        }
        try{
            headerVo.getCurrentRow().getAttribute("CmCalculation").toString();
            double prof=0.00;
            try{
            prof =Double.parseDouble(MnjLineV.getCurrentRow().getAttribute("Profit").toString());
            }
            catch(Exception e) {
                prof=calProfit;
            }
            setCm=totalcost+prof;  
            setProfit=prof;
            setManagementCm=setCm;
        }
        catch(Exception e) {
            setCm=CM;
           setProfit=calProfit;
            setManagementCm=cmValue;
        }
        
        
        MnjLineV.getCurrentRow().setAttribute("Profit",setProfit);
        AdfFacesContext.getCurrentInstance().addPartialTarget(profit);
        
        
        
        
        MnjLineV.getCurrentRow().setAttribute("Cm",MyMath.round(setCm));
        
        MnjLineV.getCurrentRow().setAttribute("CmManagement",MyMath.round(setManagementCm));
       
       
        /***********************changed by MT*****************************************/
       
       
       
        double fob = 0.0;
        
//        if (MyMath.numeric(getProfit()) > 0 ){
//        
//             fob = trim + fabric + washTotalVal + totalCMVal + otherVal;
//        }
//        else {
//             fob = trim + fabric + washTotalVal + MyMath.numeric(getCmMerchad()) + otherVal;
//        }

         fob = trim + fabric + washTotalVal +  setCm + otherVal;

        /*************************************************************************************************/

        double commVal =0.0; // MyMath.numeric3(getCommission());
        try {
            commVal=Double.parseDouble(MnjLineV.getCurrentRow().getAttribute("CommisonPrcnt").toString());
        }
        catch(Exception e){
            commVal=0.0;
        }
        double fobWitcommVal = (fob * commVal / 100) + fob;
        double orderQtyVal = MyMath.numeric(getOrderQty());
        double smsQtyVal = MyMath.numeric3(getSMSQtyFromTable());
        double fobWithSmsVal = 0.00;
        double targetPrice = MyMath.numeric(getTargetPrice());
        
        
        try {
            if (orderQtyVal > 0)
            fobWithSmsVal =((fobWitcommVal) * orderQtyVal + (fobWitcommVal) * smsQtyVal) /orderQtyVal;
            else fobWithSmsVal = 0;
        } catch (Exception e) {
           
           fobWithSmsVal =0.0 ;
            System.out.println("Exception value --===================->"+fobWithSmsVal);
        }
    
        oracle.adf.view.rich.component.UIXTable table = getSubCostingTable();
        java.util.Iterator selectionIt = table.getSelectedRowKeys().iterator();
       

        while (selectionIt.hasNext()) {
            Object rowKey = selectionIt.next();
            table.setRowKey(rowKey);
            int index = table.getRowIndex();
            FacesCtrlHierNodeBinding row =
                (FacesCtrlHierNodeBinding)table.getRowData(index);
                Row selectedRow = row.getRow();
            selectedRow.setAttribute("FobWitoutComm",
                                     Double.parseDouble(format.format(fob)));
            if(fobWithSmsVal > fobWitcommVal){
            
            
              //  selectedRow.setAttribute("FobWithComm", MyMath.toNumber(MyMath.round(fobWithSmsVal)));
              selectedRow.setAttribute("FobWithComm", Double.parseDouble(format.format(fobWithSmsVal)));//testCase1
              
             
                
                selectedRow.setAttribute("Difference",
                                     MyMath.toNumber(Double.parseDouble(format.format(fobWithSmsVal))- targetPrice));
            }else {
                
                //selectedRow.setAttribute("FobWithComm", MyMath.toNumber(MyMath.round(fobWitcommVal)));
               selectedRow.setAttribute("FobWithComm", Double.parseDouble(format.format(fobWithSmsVal))); //testCase1
               
                selectedRow.setAttribute("Difference",
                                     MyMath.toNumber(Double.parseDouble(format.format(fobWitcommVal))- targetPrice));
        
        
            }
            
            
            selectedRow.setAttribute("Fob",
                                    Double.parseDouble(format.format(fobWitcommVal)));
            selectedRow.setAttribute("FobWithSmsSample",
                                     Double.parseDouble(format.format(fobWithSmsVal)));
            selectedRow.setAttribute("FabricCost",
                                     MyMath.toNumber(MyMath.roundTo3(fabric)));
            selectedRow.setAttribute("TrimCost",
                                     MyMath.toNumber(MyMath.roundTo3(trim)));
            selectedRow.setAttribute("WashCost",
                                     MyMath.toNumber(MyMath.roundTo3(washTotalVal)));
            selectedRow.setAttribute("Others",
                                     MyMath.roundTo3(otherVal));
            
//            if (MyMath.numeric(getProfit()) > 0 ){
//            selectedRow.setAttribute("Cm",
//                                     MyMath.toNumber(MyMath.round(totalCMVal)));
//               selectedRow.setAttribute("CmManagement",MyMath.round(totalCMVal));
//                selectedRow.setAttribute("CmMerchand",MyMath.round(totalCMVal));
//    
//            }
//            else {
//                double value=0.0;
//                selectedRow.setAttribute("Cm", MyMath.toNumber(MyMath.numeric(getCmMerchad())));
//                selectedRow.setAttribute("CmManagement",value);
//                         
//            }
        }//end of while loop
       
       
       
       
        
       
        
        
        
    }


//    public double getSelectedDryTotal() {
//
//        DCBindingContainer bindings =
//            (DCBindingContainer)BindingContext.getCurrent().getCurrentBindingsEntry();
//        DCIteratorBinding dcIteratorBindings =
//            bindings.findIteratorBinding("DryDetailVO1Iterator");
//        // Get all the rows of a iterator
//        double total = 0.00, val = 0.00, val2 = 0.0;
//        String UoM;
//        ;
//        Row[] rows = dcIteratorBindings.getAllRowsInRange();
//        for (Row row : rows) {
//            try { //ManualPrice
//                val  = Double.parseDouble(String.valueOf(row.getAttribute("Total")));
//                val2 = Double.parseDouble(String.valueOf(row.getAttribute("ManualPrice")));
//                UoM  = row.getAttribute("CostUom").toString();
//       // UoM is Added By Sabih on 07Feb2017         
//                if (UoM.equals("DOZ"))
//                {
//                val  = val/12;
//                val2 = val2/12;
//                };
//                       
//                
//            } catch (Exception e) {
//                
//                val2 = 0;
//                
//                UoM  = row.getAttribute("CostUom").toString();
//                
//                if (UoM.equals("DOZ"))
//                {
//                val  = val/12;
//                val2 = val2/12;
//                };
//                 
//            }
//            if (val > 0)
//                total += val;
//            else
//                total += val2;
//
//        } //end of for each loop
//
//        return total;
//
//    }
//
//    public double getSelectedWetTotal() {
//
//        DCBindingContainer bindings =
//            (DCBindingContainer)BindingContext.getCurrent().getCurrentBindingsEntry();
//        DCIteratorBinding dcIteratorBindings =
//            bindings.findIteratorBinding("MnjMfgPrecostingWetDView1Iterator");
//        // Get all the rows of a iterator
//
//        double total = 0.00, val = 0.00, val2 = 0.0;
//        String UoM;
//        Row[] rows = dcIteratorBindings.getAllRowsInRange();
//        for (Row row : rows) {
//
//            try { //ManualPrice
//                val  =Double.parseDouble(String.valueOf(row.getAttribute("NewTotalVal")));
//                val2 =Double.parseDouble(String.valueOf(row.getAttribute("ManualPrice")));
//                UoM  = row.getAttribute("CostUom").toString();
//                    // UoM is Added By Sabih on 07Feb2017                  
//                if (UoM.equals("DOZ"))
//                {
//                val  = val/12;
//                val2 = val2/12;
//                };
//                       
//                
//                } catch (Exception e) {
//                
//                val2 = 0;
//                
//                UoM  = row.getAttribute("CostUom").toString();
//                
//                if (UoM.equals("DOZ"))
//                {
//                val  = val/12;
//                val2 = val2/12;
//                };
//                 
//            }
//            if (val > 0)
//                total += val;
//            else
//                total += val2;
//
//        } //end of for each loop
//
//        return total;
//
//    }
    
        public double getSelectedDryTotal() {

        DCBindingContainer bindings =
            (DCBindingContainer)BindingContext.getCurrent().getCurrentBindingsEntry();
        DCIteratorBinding dcIteratorBindings =
            bindings.findIteratorBinding("DryDetailVO1Iterator");
        // Get all the rows of a iterator
        double total = 0.00, val = 0.00, val2 = 0.0;
        ;
        Row[] rows = dcIteratorBindings.getAllRowsInRange();
        for (Row row : rows) {
            try { //ManualPrice
                val =Double.parseDouble(String.valueOf(row.getAttribute("Total")));
                val2 =Double.parseDouble(String.valueOf(row.getAttribute("ManualPrice")));
            } catch (Exception e) {
                val2 = 0;
            }
            if (val > 0)
                total += val;
            else
                total += val2;

        } //end of for each loop

        return total;

    }

    public double getSelectedWetTotal() {

        DCBindingContainer bindings =
            (DCBindingContainer)BindingContext.getCurrent().getCurrentBindingsEntry();
        DCIteratorBinding dcIteratorBindings =
            bindings.findIteratorBinding("MnjMfgPrecostingWetDView1Iterator");
        // Get all the rows of a iterator

        double total = 0.00, val = 0.00, val2 = 0.0;
        Row[] rows = dcIteratorBindings.getAllRowsInRange();
        for (Row row : rows) {

            try { //ManualPrice
                val  =Double.parseDouble(String.valueOf(row.getAttribute("NewTotalVal")));
                val2 =Double.parseDouble(String.valueOf(row.getAttribute("ManualPrice")));
            } catch (Exception e) {
                val2 = 0;
            }
            if (val > 0)
                total += val;
            else
                total += val2;

        } //end of for each loop

        return total;

    }

    public void setActualUnitPrice(RichInputText actualUnitPrice) {
        this.actualUnitPrice = actualUnitPrice;
    }

    public RichInputText getActualUnitPrice() {
        return actualUnitPrice;
    }

    public void setAddPriceMOQ(RichInputText addPriceMOQ) {
        this.addPriceMOQ = addPriceMOQ;
    }

    public RichInputText getAddPriceMOQ() {
        return addPriceMOQ;
    }

    public void setFinalUnitPrice(RichInputText finalUnitPrice) {
        this.finalUnitPrice = finalUnitPrice;
    }

    public RichInputText getFinalUnitPrice() {
        return finalUnitPrice;
    }


    public void commonListener(ValueChangeEvent valueChangeEvent) {
        // Add event code here..
       
        
       
                FacesContext facesContext = FacesContext.getCurrentInstance();
                    UIViewRoot root = facesContext.getViewRoot();
                System.out.println("Component Id in common listener ---> "+valueChangeEvent.getComponent().getId());
        
                RichInputText inputText = (RichInputText)root.findComponent(valueChangeEvent.getComponent().getId());
               // inputText.setValue(valueChangeEvent);
                if(valueChangeEvent.getNewValue() == null){
               // inputText.setSubmittedValue(null);
               // inputText.resetValue();
                }
                else{
                    System.out.println("aluechnged e -> "+valueChangeEvent.getNewValue());
                    ViewObject linenumber=am.getMnjMfgPrecostingLView1();
                    try{
                       
                        linenumber.getCurrentRow().getAttribute("LineId").toString();
                        System.out.println("..........befor");
                        refreshValues();
                        System.out.println("..........after");
                    }
                    catch(Exception e) {
                        ;
                    }
                }

    }
    
    public void cmMerchDznLsitener(ValueChangeEvent valueChangeEvent) {
        // Add event code here...            
        double merchDzn = MyMath.numeric(getCmMerchDzn());
        double result = 0.0;
        if (merchDzn != 0){
            result = merchDzn /12;  
        }
        cmMerchandiser.setValue(MyMath.toNumber(result));
        AdfFacesContext.getCurrentInstance().addPartialTarget(cmMerchandiser);
    }
    
    public void setMerchValues(){
            
      //  double merchPc = MyMath.numeric(getCmMerchandiser());           
     //   double result = 0.0;       
      //  result = merchPc * 12;
      //  cmMerchDzn.setValue(MyMath.toNumber(result));
      //  AdfFacesContext.getCurrentInstance().addPartialTarget(cmMerchDzn);
            
            
    
    }


    public void refreshValues() {

        //setOthersValues();
        setMerchValues();

        double actualUnitPriceVal = MyMath.numeric(getActualUnitPrice());
              
        double addPriceMoqVal = MyMath.numeric(getAddPriceMOQ());

        double addChrgLCVal = MyMath.numeric(getAddChargLC());
        double addChrgPrcntVal = MyMath.numeric(getAddChrgPrcnt());
        double addPriceFOBVal = MyMath.numeric(getAddPriceFOB());
        double lcCalculated = 0.00;
        double actualUnitPriceCalc = 0.00;

        try {
            if (MyMath.numeric(getUomConversion()) != 0) {
                addChrgLCVal =
                        addChrgLCVal / MyMath.numeric(getUomConversion());
                actualUnitPriceCalc =
                        actualUnitPriceVal / MyMath.numeric(getUomConversion());
                addPriceFOBVal = addPriceFOBVal / MyMath.numeric(getUomConversion());
                addPriceMoqVal =addPriceMoqVal /  MyMath.numeric(getUomConversion());
            }
        } catch (Exception e) {
            ;

        }
        
        actualUnitPriceCalc =
                (actualUnitPriceCalc != 0 ? actualUnitPriceCalc : actualUnitPriceVal);

        try {
            lcCalculated =
                    (addChrgLCVal != 0 ? addChrgLCVal : ((addChrgPrcntVal) /100 *actualUnitPriceCalc));
        } catch (Exception e) {
            ;
        }

        

        double finalUnitPriceVal =
            MyMath.round(actualUnitPriceCalc + addPriceMoqVal + lcCalculated +
                         +addPriceFOBVal);

        //if (finalUnitPriceVal > 0)
        finalUnitPrice.setValue(MyMath.toNumber(MyMath.roundTo4(finalUnitPriceVal)));
        AdfFacesContext.getCurrentInstance().addPartialTarget(finalUnitPrice);

        double consperPcsVal = MyMath.numeric(getConsPerPcs());
        double wasteInPercVal = MyMath.numeric(getWasteInPercent());

        double wasteInQtyVal = consperPcsVal * (wasteInPercVal / 100);
        // if (wasteInQtyVal > 0)
        wasteInQty.setValue(MyMath.toNumber(wasteInQtyVal));
        AdfFacesContext.getCurrentInstance().addPartialTarget(wasteInQty);

        double consWithWasteVal = consperPcsVal * (1 + wasteInPercVal / 100);
        //  if (consWithWasteVal > 0)
        consWithWaste.setValue(MyMath.toNumber(consWithWasteVal));
        AdfFacesContext.getCurrentInstance().addPartialTarget(consWithWaste);

        double bufferVal = MyMath.numeric(getBuffer());
       // double finalConsVal = bufferVal + consWithWasteVal;
       double finalConsVal =  consWithWasteVal;

        // if (finalConsVal > 0)
        finalCons.setValue(MyMath.toNumber(MyMath.round(finalConsVal)));
        AdfFacesContext.getCurrentInstance().addPartialTarget(finalCons);


        double costPerPcsVal = (finalUnitPriceVal * finalConsVal)+bufferVal;
        //  if (costPerPcsVal > 0)
       // costPerPcsVal = Math.ceil((double)MyMath.roundTo3(costPerPcsVal)) ;
        costPerPcs.setValue(MyMath.toNumber(MyMath.roundUp(costPerPcsVal)));
        AdfFacesContext.getCurrentInstance().addPartialTarget(costPerPcs);

        double addDeductCostVal = MyMath.numeric(getAddDeductCost());
        double finalCostPerPcsVal = costPerPcsVal + addDeductCostVal;
        
        
        double fabFinanceCostVal =0;
        
//        if(MyMath.numeric(getPrefixCode())== 11 || MyMath.numeric(getPrefixCode())== 12) {
//            fabFinanceCostVal = finalCostPerPcsVal * (MyMath.numeric3(getFabricFinance())/100);
//        }
        fabFinanceCost.setValue(MyMath.toNumber(fabFinanceCostVal));
        AdfFacesContext.getCurrentInstance().addPartialTarget(fabFinanceCost);
        
        // refreshTotal(finalCostPerPcsVal);
        // if (finalCostPerPcsVal > 0)                   
        finalCostPerPcs.setValue(MyMath.toNumber(MyMath.roundUp(finalCostPerPcsVal + fabFinanceCostVal)));
        AdfFacesContext.getCurrentInstance().addPartialTarget(finalCostPerPcs);

   // CHANGE bY BILAL 03MAR17    double costPerLineVal = MyMath.numeric(getCostPerline());
        double costPerLineVal = MyMath.numeric(getProdCostPerLine());
        double prodPerLineVal = MyMath.numeric(getProducPerLine());
    System.out.println("........cost per line"+costPerLineVal);
    System.out.println("........prod per line"+prodPerLineVal);
        double costVal = 0.00;
        if (costPerLineVal != 0 && prodPerLineVal != 0) {

            costVal = costPerLineVal / prodPerLineVal;
        }
        // if (costVal > 0)
        cost.setValue(MyMath.toNumber(MyMath.round(costVal)));
        AdfFacesContext.getCurrentInstance().addPartialTarget(cost);


        //        double dryCostVal = MyMath.numeric(getDryCost());
        //        double dryProfitVal = MyMath.numeric(getDryProfit());
        //        double dryTotalVal = dryCostVal + dryProfitVal;
        //        // refreshGrandDryTotal(dryTotalVal);
        //        if (dryTotalVal > 0)
        //            dryTotal.setValue(MyMath.toNumber(MyMath.round(dryTotalVal)));
        //        AdfFacesContext.getCurrentInstance().addPartialTarget(dryTotal);


        //        double wetCostVal = MyMath.numeric(getWetCost());
        //        double wetProfitVal = MyMath.numeric(getWetProfit());
        //        double wetTotalVal = wetCostVal + wetProfitVal;
        //        // refreshWetGrandTotal(wetTotalVal);
        //        if (wetTotalVal > 0)
        //            wetTotal.setValue(MyMath.toNumber(MyMath.round(wetTotalVal)));
        //        AdfFacesContext.getCurrentInstance().addPartialTarget(wetTotal);

        setFinanceCalc(0);


    }


    public void setConsPerPcs(RichInputText consPerPcs) {
        this.consPerPcs = consPerPcs;
    }

    public RichInputText getConsPerPcs() {
        return consPerPcs;
    }

    public void setWasteInPercent(RichInputText wasteInPercent) {
        this.wasteInPercent = wasteInPercent;
    }

    public RichInputText getWasteInPercent() {
        return wasteInPercent;
    }

    public void setWasteInQty(RichInputText wasteInQty) {
        this.wasteInQty = wasteInQty;
    }

    public RichInputText getWasteInQty() {
        return wasteInQty;
    }

    public void setConsWithWaste(RichInputText consWithWaste) {
        this.consWithWaste = consWithWaste;
    }

    public RichInputText getConsWithWaste() {
        return consWithWaste;
    }

    public void setBuffer(RichInputText buffer) {
        this.buffer = buffer;
    }

    public RichInputText getBuffer() {
        return buffer;
    }

    public void setFinalCons(RichInputText finalCons) {
        this.finalCons = finalCons;
    }

    public RichInputText getFinalCons() {
        return finalCons;
    }

    public void setCostPerPcs(RichInputText costPerPcs) {
        this.costPerPcs = costPerPcs;
    }

    public RichInputText getCostPerPcs() {
        return costPerPcs;
    }

    public void setFinalCostPerPcs(RichInputText finalCostPerPcs) {
        this.finalCostPerPcs = finalCostPerPcs;
    }

    public RichInputText getFinalCostPerPcs() {
        return finalCostPerPcs;
    }

    public void setAddDeductCost(RichInputText addDeductCost) {
        this.addDeductCost = addDeductCost;
    }

    public RichInputText getAddDeductCost() {
        return addDeductCost;
    }

    public void setProjectionNo(RichInputText projectionNo) {
        this.projectionNo = projectionNo;
    }

    public RichInputText getProjectionNo() {
        return projectionNo;
    }

    public void setProductionUnit(RichInputListOfValues productionUnit) {
        this.productionUnit = productionUnit;
    }

    public RichInputListOfValues getProductionUnit() {
        return productionUnit;
    }




    public void productionCalculation() {
        // Add event code here...
        String unit = null;
       
            BindingContext bindingContext = BindingContext.getCurrent(); 
            DCDataControl dc = bindingContext.findDataControl("AppModuleDataControl"); //
            ApplicationModule am  = dc.getApplicationModule() ;
            ViewObject findViewObject =  am.findViewObject("MnjMfgPrecostingHView1");
            
            try {
            unit = findViewObject.getCurrentRow().getAttribute("ProductionUnit").toString();
            } catch (Exception e) {
            // TODO: Add catch code
            ;
            }  //  System.out.println("Unit----->>"+unit);
              
       // System.out.println("Unit123----->>");
        
        //System.out.println("Unit----->>"+unit);
        
        try {
         // producPerLine.setValue(MyMath.toNumber(MyMath.round(getProdPerLineFromProc(unit,getProjectionNo().getValue()))));
            prodCostPerLine.setValue(MyMath.toNumber(getProdCostUnitWise(unit)));
          //  AdfFacesContext.getCurrentInstance().addPartialTarget(producPerLine);
            AdfFacesContext.getCurrentInstance().addPartialTarget(prodCostPerLine);
        } catch (Exception e) {
            ;
        }
       // refreshValues();
    }



    public void productionListener(ValueChangeEvent valueChangeEvent) {
        // Add event code here...
        System.out.println("Unit Lsitener");
        String unit = null;
        try {
     System.out.println("Production Unit------->>"+getProductionUnit().getValue().toString());
            unit = valueChangeEvent.getNewValue().toString();
        } catch (Exception e) {
            ;
        }

        try {
           // producPerLine.setValue(MyMath.toNumber(MyMath.round(getProdPerLineFromProc(unit, getProjectionNo().getValue()))));
            prodCostPerLine.setValue(MyMath.toNumber(getProdCostUnitWise(unit)));
           // AdfFacesContext.getCurrentInstance().addPartialTarget(producPerLine);
            AdfFacesContext.getCurrentInstance().addPartialTarget(prodCostPerLine);
        } catch (Exception e) {
            ;
        }
        

      //  refreshValues();
    }

    public double getProdCostUnitWise(String unit) {

        OperationBinding operationBinding =
            executeOperation("getProdCostUnitWise");
        operationBinding.getParamsMap().put("unitName", unit);
        operationBinding.execute();
        
        Object methodReturnValue = operationBinding.getResult();
        double value = Double.parseDouble(methodReturnValue.toString());
        return value;
       
    }


    public double getProdPerLineFromProc(String name, Object projNo) {


        OperationBinding operationBinding =
            executeOperation("getCostProdPerLine");


        //        BindingContext bctx = BindingContext.getCurrent();
        //        BindingContainer bindings = bctx.getCurrentBindingsEntry();
        //        OperationBinding operationBinding =
        //            bindings.getOperationBinding("getCostProdPerLine");


        operationBinding.getParamsMap().put("name", name);
        operationBinding.getParamsMap().put("projectionNo", projNo);


        //invoke method
        operationBinding.execute();
        if (!operationBinding.getErrors().isEmpty()) {
            System.out.println("if errors-->");
            // List errors = operationBinding.getErrors();
        }
        //optional
        Object methodReturnValue = operationBinding.getResult();
        double value = Double.parseDouble(methodReturnValue.toString());
        return value;
    }

    public void setTotalFinalCostPerPCS(RichOutputText totalFinalCostPerPCS) {
        this.totalFinalCostPerPCS = totalFinalCostPerPCS;
    }

    public RichOutputText getTotalFinalCostPerPCS() {
        return totalFinalCostPerPCS;
    }

    public void refreshTotal(double finalCostPerPcsVal) {

        if (MyMath.numeric(getFinalCostPerPcs()) > finalCostPerPcsVal) {
            //totalFinalCostPerPCS.setValue(arg0);
            double newTotalVale =
                MyMath.numeric2(getTotalFinalCostPerPCS()) - (MyMath.numeric(getFinalCostPerPcs()) -
                                                              finalCostPerPcsVal);
            totalFinalCostPerPCS.setValue(MyMath.toNumber(newTotalVale));
            AdfFacesContext.getCurrentInstance().addPartialTarget(totalFinalCostPerPCS);
        } else {
            double newTotalVale =
                MyMath.numeric2(getTotalFinalCostPerPCS()) + (finalCostPerPcsVal -
                                                              MyMath.numeric(getFinalCostPerPcs()));
            totalFinalCostPerPCS.setValue(MyMath.toNumber(newTotalVale));
            AdfFacesContext.getCurrentInstance().addPartialTarget(totalFinalCostPerPCS);
        }
    } //end of method


    public void setDryCost(RichInputText dryCost) {
        this.dryCost = dryCost;
    }

    public RichInputText getDryCost() {
        return dryCost;
    }

    public void setDryProfit(RichInputText dryProfit) {
        this.dryProfit = dryProfit;
    }

    public RichInputText getDryProfit() {
        return dryProfit;
    }

    public void setDryTotal(RichInputText dryTotal) {
        this.dryTotal = dryTotal;
    }

    public RichInputText getDryTotal() {
        return dryTotal;
    }

    public void setDryManualPrice(RichInputText dryManualPrice) {
        this.dryManualPrice = dryManualPrice;
    }

    public RichInputText getDryManualPrice() {
        return dryManualPrice;
    }

    public void setWetCost(RichInputText wetCost) {
        this.wetCost = wetCost;
    }

    public RichInputText getWetCost() {
        return wetCost;
    }

    public void setWetProfit(RichInputText wetProfit) {
        this.wetProfit = wetProfit;
    }

    public RichInputText getWetProfit() {
        return wetProfit;
    }

    public void setWetTotal(RichInputText wetTotal) {
        this.wetTotal = wetTotal;
    }

    public RichInputText getWetTotal() {
        return wetTotal;
    }

    public void setWetManualPrice(RichInputText wetManualPrice) {
        this.wetManualPrice = wetManualPrice;
    }

    public RichInputText getWetManualPrice() {
        return wetManualPrice;
    }

    public void refreshGrandDryTotal(double dryTotalVal) {

        if (MyMath.numeric(getDryTotal()) > dryTotalVal) {
            //totalFinalCostPerPCS.setValue(arg0);
            double newTotalVale =
                MyMath.numeric2(getGrandDryTotal()) - (MyMath.numeric(getDryTotal()) -
                                                       dryTotalVal);
            grandDryTotal.setValue(MyMath.toNumber(newTotalVale));
            AdfFacesContext.getCurrentInstance().addPartialTarget(grandDryTotal);
        } else {
            double newTotalVale =
                MyMath.numeric2(getGrandDryTotal()) + (dryTotalVal -
                                                       MyMath.numeric(getDryTotal()));
            grandDryTotal.setValue(MyMath.toNumber(newTotalVale));
            AdfFacesContext.getCurrentInstance().addPartialTarget(grandDryTotal);
        }

    }

    public void refreshWetGrandTotal(double wetTotalVal) {

        if (MyMath.numeric(getWetTotal()) > wetTotalVal) {
            //totalFinalCostPerPCS.setValue(arg0);
            double newTotalVale =
                MyMath.numeric2(getGrandWetTotal()) - (MyMath.numeric(getWetTotal()) -
                                                       wetTotalVal);
            grandWetTotal.setValue(MyMath.toNumber(newTotalVale));
            AdfFacesContext.getCurrentInstance().addPartialTarget(grandWetTotal);
        } else {
            double newTotalVale =
                MyMath.numeric2(getGrandWetTotal()) + (wetTotalVal -
                                                       MyMath.numeric(getWetTotal()));
            grandWetTotal.setValue(MyMath.toNumber(newTotalVale));
            AdfFacesContext.getCurrentInstance().addPartialTarget(grandWetTotal);
        }

    }

    public void setGrandDryTotal(RichOutputText grandDryTotal) {
        this.grandDryTotal = grandDryTotal;
    }

    public RichOutputText getGrandDryTotal() {
        return grandDryTotal;
    }

    public void setGrandWetTotal(RichOutputText grandWetTotal) {
        this.grandWetTotal = grandWetTotal;
    }

    public RichOutputText getGrandWetTotal() {
        return grandWetTotal;
    }

    public void setFoBWithOutComm(RichInputText foBWithOutComm) {
        this.foBWithOutComm = foBWithOutComm;
    }

    public RichInputText getFoBWithOutComm() {
        return foBWithOutComm;
    }

    public void setSubCostingTable(RichTable subCostingTable) {
        this.subCostingTable = subCostingTable;
    }

    public RichTable getSubCostingTable() {
        return subCostingTable;
    }

    public String refershButton() {
        // Add event code here...
        ViewObject testlineVo=am.getMnjMfgPrecostingLView1();
        remark();
        trimStyleNoAndName();
      addStyleTrackinAndBasis();
        
        OperationBinding ob = executeOperation("Commit");
        ob.execute();
        productionCalculation();
        
        ViewObject linenumber=am.getMnjMfgPrecostingLView1();
        try{
            linenumber.getCurrentRow().getAttribute("LineId").toString();
            System.out.println("..........befor");
            refreshValues();
            setfabricDescription();
            System.out.println("..........after");
        }
        catch(Exception e) {
            ;
        }
        
        
        
        
        try{
         
            double fob=  Double.parseDouble(testlineVo.getCurrentRow().getAttribute("FobWithComm").toString());
            //double CM= Double.parseDouble( testlineVo.getCurrentRow().getAttribute("Cm").toString());
            // adjustment();
        }
        catch(Exception e){
            ;
        }

        AdfFacesContext.getCurrentInstance().addPartialTarget(subCostingTable);
        // Code For Style Tracking
        OperationBinding operationBinding = executeOperation("StyleTracking");                
        operationBinding.execute();
        // End Code For Style Tracking
        OperationBinding ob1 = executeOperation("Commit");
        ob1.execute();
        return null;
    }


    public void editPopupFetchListener(PopupFetchEvent popupFetchEvent) {
        if (popupFetchEvent.getLaunchSourceClientId().contains("cbInsert")) {
            System.out.println("Insert event called......!");

        }
    }

    public void editDialogListener(DialogEvent dialogEvent) {
        if (dialogEvent.getOutcome().name().equals("ok")) {

            callCopy(getSelectRadio().getValue().toString());


        } else if (dialogEvent.getOutcome().name().equals("cancel")) {
            BindingContainer bindings = getBindings();

        }
    }

    public void editPopupCancelListener(PopupCanceledEvent popupCanceledEvent) {
        BindingContainer bindings = getBindings();

    }


    public void setSelectRadio(RichSelectOneRadio selectRadio) {
        this.selectRadio = selectRadio;
    }

    public RichSelectOneRadio getSelectRadio() {
        return selectRadio;
    }


    public void setHeaderID(RichInputText headerID) {
        this.headerID = headerID;
    }

    public RichInputText getHeaderID() {
        return headerID;
    }

    public Number getSlectedLineID() {

        oracle.adf.view.rich.component.UIXTable table = getSubCostingTable();
        // Get the Selected Row key set iterator

        //((FOB*Comission(%))*Order Qty+(FOB*Comission(%))*Sample Qty)/Order Qty
        java.util.Iterator selectionIt = table.getSelectedRowKeys().iterator();
        Number id = new Number(0);

        while (selectionIt.hasNext()) {
            Object rowKey = selectionIt.next();
            table.setRowKey(rowKey);
            int index = table.getRowIndex();
            FacesCtrlHierNodeBinding row =
                (FacesCtrlHierNodeBinding)table.getRowData(index);
            Row selectedRow = row.getRow();
            id = (Number)selectedRow.getAttribute("LineId");
        }

        return id;

    }


    public void callCopy(String type) {

        BindingContext bctx = BindingContext.getCurrent();
        BindingContainer bindings = bctx.getCurrentBindingsEntry();
        OperationBinding operationBinding =
            bindings.getOperationBinding("copyRecords");


        operationBinding.getParamsMap().put("type", type);
        operationBinding.getParamsMap().put("headerId",
                                            getHeaderID().getValue());
        operationBinding.getParamsMap().put("lineId", getSlectedLineID());


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
        AdfFacesContext.getCurrentInstance().addPartialTarget(subCostingTable);
    }

    public String createItem() {
        BindingContext bctx = BindingContext.getCurrent();
        BindingContainer bindings = bctx.getCurrentBindingsEntry();
        OperationBinding operationBinding =
            bindings.getOperationBinding("createItems");

        operationBinding.getParamsMap().put("headerId",
                                            getHeaderID().getValue());


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

    public void setAddChargLC(RichInputText addChargLC) {
        this.addChargLC = addChargLC;
    }

    public RichInputText getAddChargLC() {
        return addChargLC;
    }

    public void setAddPriceFOB(RichInputText addPriceFOB) {
        this.addPriceFOB = addPriceFOB;
    }

    public RichInputText getAddPriceFOB() {
        return addPriceFOB;
    }

    //    public String createSubCostingLine() {
    //        BindingContainer bindings = getBindings();
    //        OperationBinding operationBinding =
    //            bindings.getOperationBinding("CreateInsert");
    //        Object result = operationBinding.execute();
    //
    //        if (!operationBinding.getErrors().isEmpty()) {
    //            return null;
    //        }
    //        return null;
    //    }

    public String sendForApproval() {
        refershButton();

        OperationBinding ob = executeOperation("Approve");
        ob.getParamsMap().put("headerId", getHeaderID().getValue());
        ob.execute();
        FacesMessage fm = new FacesMessage("Successfully Sent For Approval");
        fm.setSeverity(FacesMessage.SEVERITY_INFO);
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, fm);
        return null;
    }

    public String sendForApprovalForward() {
        // Add event code here...

        OperationBinding ob = executeOperation("ApproveNForward");
        ob.getParamsMap().put("headerId", getHeaderID().getValue());
        ob.execute();
        FacesMessage fm = new FacesMessage("Successfully Sent For Approval");
        fm.setSeverity(FacesMessage.SEVERITY_INFO);
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, fm);

        return null;
    }


    /*****Generic Method to Get BindingContainer**/
    public BindingContainer getBindingsCont() {
        return BindingContext.getCurrent().getCurrentBindingsEntry();
    }

    /**
     * Generic Method to execute operation
     * */
    public OperationBinding executeOperation(String operation) {
        OperationBinding createParam =
            getBindingsCont().getOperationBinding(operation);
        return createParam;

    }


    public void setAddChrgPrcnt(RichInputText addChrgPrcnt) {
        this.addChrgPrcnt = addChrgPrcnt;
    }

    public RichInputText getAddChrgPrcnt() {
        return addChrgPrcnt;
    }


    public void setUomConversion(RichInputText uomConversion) {
        this.uomConversion = uomConversion;
    }

    public RichInputText getUomConversion() {
        return uomConversion;
    }

    //    public void FetchLines(ActionEvent actionEvent) {
    //        // Add event code here...
    //
    //
    //        OperationBinding ob = executeOperation("fetchProjectionLines");
    //        ob.getParamsMap().put("headerNo", getProjectionNo().getValue());
    //        ob.execute();
    //
    //
    //
    //    }

    public void setOthersTable(RichTable othersTable) {
        this.othersTable = othersTable;
    }

    public RichTable getOthersTable() {
        return othersTable;
    }

    /*******************************************
     * Set others values
     * *****************************************************************/
    public void setOthersValues() {


        BindingContext bindingContext = BindingContext.getCurrent();
        DCDataControl dc =
            bindingContext.findDataControl("AppModuleDataControl"); //
        ApplicationModule am = dc.getApplicationModule();
        ViewObject findViewObject =
            am.findViewObject("MnjMfgPrecostingLView1");


        //        oracle.adf.view.rich.component.UIXTable table = getOthersTable();
        // Get the Selected Row key set iterator

        //((FOB*Comission(%))*Order Qty+(FOB*Comission(%))*Sample Qty)/Order Qty
        // RowSetIterator selectionIt = findViewObject.createRowSetIterator("a");

        try {
            Row selectedRow = findViewObject.getCurrentRow();

            //  Object id = selectedRow.getAttribute("OtherCharge");


           // setOtherCharge(selectedRow.getAttribute("OtherCharge"));

           // setLabTestcharge(selectedRow.getAttribute("LabTestCharge"));
           // setPrint(selectedRow.getAttribute("Print"));
          //  setFabricFinance(selectedRow.getAttribute("FabricFinance"));
        //    setWashFinance(selectedRow.getAttribute("WashFinance"));
         //   setTrimFinance(selectedRow.getAttribute("TrimFinance"));
          //  setCommission(selectedRow.getAttribute("CommisonPrcnt"));

            System.out.println("Others Called--->");
        } catch (Exception e) {
            ;
        }


    } //end of others value method

    public Object getSMSQtyFromTable() {

        oracle.adf.view.rich.component.UIXTable table = getSubCostingTable();
        // Get the Selected Row key set iterator

        //((FOB*Comission(%))*Order Qty+(FOB*Comission(%))*Sample Qty)/Order Qty
        java.util.Iterator selectionIt = table.getSelectedRowKeys().iterator();
        Object qty = null;
        while (selectionIt.hasNext()) {
            Object rowKey = selectionIt.next();
            table.setRowKey(rowKey);
            int index = table.getRowIndex();
            FacesCtrlHierNodeBinding row =
                (FacesCtrlHierNodeBinding)table.getRowData(index);
            Row selectedRow = row.getRow();

            qty = selectedRow.getAttribute("SmsQty");

        }
        System.out.println("SMS Qty-->" + qty);
        return qty;
    }

    public void dry(ValueChangeEvent valueChangeEvent) {
        // Add event code here...

        double dryCostVal = MyMath.numeric(getDryCost());
        double dryProfitVal = MyMath.numeric(getDryProfit());
        double dryTotalVal = dryCostVal + dryProfitVal;
        // refreshGrandDryTotal(dryTotalVal);
        // if (dryTotalVal > 0)
        dryTotal.setValue(MyMath.toNumber(MyMath.round(dryTotalVal)));
        AdfFacesContext.getCurrentInstance().addPartialTarget(dryTotal);


    }

    public void wetListener(ValueChangeEvent valueChangeEvent) {
        // Add event code here...

        double wetCostVal = MyMath.numeric(getWetCost());
        double wetProfitVal = MyMath.numeric(getWetProfit());
        double wetTotalVal = wetCostVal + wetProfitVal;
        // refreshWetGrandTotal(wetTotalVal);
        // if (wetTotalVal > 0)
        wetTotal.setValue(MyMath.toNumber(MyMath.round(wetTotalVal)));
        AdfFacesContext.getCurrentInstance().addPartialTarget(wetTotal);
    }


    public double getPOCFinanceCost(String type) {
        // Add event code here...
        double val = 0.0;
        OperationBinding ob = executeOperation("getPOCFinCost");
        ob.getParamsMap().put("type", type);
        ob.execute();

        Object methodReturnValue = ob.getResult();
        if (methodReturnValue != null) {
            val = MyMath.numeric3(methodReturnValue.toString());
        }
     
        return val;
    }

    public void setCmMerchDzn(RichInputText cmMerchDzn) {
        this.cmMerchDzn = cmMerchDzn;
    }

    public RichInputText getCmMerchDzn() {
        return cmMerchDzn;
    }


    public void setTargetPrice(RichInputText targetPrice) {
        this.targetPrice = targetPrice;
    }

    public RichInputText getTargetPrice() {
        return targetPrice;
    }

    public void setDifference(RichInputText difference) {
        this.difference = difference;
    }

    public RichInputText getDifference() {
        return difference;
    }

    public void setFabFinanceCost(RichInputText fabFinanceCost) {
        this.fabFinanceCost = fabFinanceCost;
    }

    public RichInputText getFabFinanceCost() {
        return fabFinanceCost;
    }

    public void setPrefixName(RichInputListOfValues prefixName) {
        this.prefixName = prefixName;
    }

    public RichInputListOfValues getPrefixName() {
        return prefixName;
    }

    public void setPrefixCode(RichInputText prefixCode) {
        this.prefixCode = prefixCode;
    }

    public RichInputText getPrefixCode() {
        return prefixCode;
    }

    public void setProdCostPerLine(RichInputText prodCostPerLine) {
        this.prodCostPerLine = prodCostPerLine;
    }

    public RichInputText getProdCostPerLine() {
        return prodCostPerLine;
    }

    public void setSampleDocNo(RichInputText sampleDocNo) {
        this.sampleDocNo = sampleDocNo;
        FacesContext fctx = FacesContext.getCurrentInstance();
        ExternalContext ectx = fctx.getExternalContext();
        HttpSession userSession = (HttpSession)ectx.getSession(false);
        userSession.setAttribute("sampleDocS", sampleDocNo.getValue());
        
    }

    public RichInputText getSampleDocNo() {
        return sampleDocNo;
    }

    public void LaunchPopupListener(LaunchPopupEvent launchPopupEvent) {
        // Add event code here...
        if (launchPopupEvent.getPopupType().equals
               (LaunchPopupEvent.PopupType.SEARCH_DIALOG))
            {
            
              launchPopupEvent.setLaunchPopup(false);
            }
    }
    
    
    
    public double getBPOTotalQty() {

    System.out.println("Level 1");
        BindingContext bindingContext = BindingContext.getCurrent();
        DCDataControl dc =
            bindingContext.findDataControl("AppModuleDataControl"); //
        ApplicationModule am = dc.getApplicationModule();
        ViewObject findViewObject =am.findViewObject("CreateSaleOrderLinesVO1"); //ImpSaleContractDetailEOView1
   
    System.out.println("Level 2");
                 
        RowSetIterator it = findViewObject.createRowSetIterator("tt");
        double val = 0.00, total = 0.00;
        while (it.hasNext()) {

            Row r = it.next();
            try {
                val = Double.parseDouble(r.getAttribute("BpoQty").toString());
            } catch (Exception e) {
               val=0.00 ;
            }

            total = total + val;
        } //end of while loop
        it.closeRowSetIterator();
        //return roundTo2(total);
        System.out.println("Level 3 ---->"+total);
        
        return total;
        
    }
    


    public void setTotalBpoQty(RichOutputText totalBpoQty) {
        this.totalBpoQty = totalBpoQty;
    }

    public RichOutputText getTotalBpoQty() {
        return totalBpoQty;
    }

    public void BPOQty_VC(ValueChangeEvent valueChangeEvent) {
        // Add event code here...
        double newtotal=0.00;
        double val = 0.00;
        try{
             val=Double.parseDouble(bpoQt_value.getValue().toString());
        }
        catch(Exception e) {
            val=0.00;
        }
    
      // AdfFacesContext.getCurrentInstance().addPartialTarget(bpoQt_value);  
        //System.out.println(" Total BPO Qty------------>"+getBPOTotalQty());
     ViewObject calView=am.getCreateSaleOrderLinesVO1();
        calView.getCurrentRow().setAttribute("BpoQty", val);
       
        totalBpoQty.setValue(getBPOTotalQty());
        AdfFacesContext.getCurrentInstance().addPartialTarget(totalBpoQty);
        
    }

    public void GetTotalQty(ActionEvent actionEvent) {
        // Add event code here...
        System.out.println(" Total BPO Qty------------>"+getBPOTotalQty());
        totalBpoQty.setValue(getBPOTotalQty());
        AdfFacesContext.getCurrentInstance().addPartialTarget(totalBpoQty);
        System.out.println(" Total BPO Qty 2------------>"+totalBpoQty.getValue().toString());
        
    }


    public void CopyItem_Detail_Bind(ActionEvent actionEvent) {
        // Add event code here...
        OperationBinding ob = executeOperation("CopyItemsDetail");
        ob.execute();
        AdfFacesContext.getCurrentInstance().addPartialTarget(itemTable);
        }



    public void setTbAQty_Bind(RichOutputText tbAQty_Bind) {
        this.tbAQty_Bind = tbAQty_Bind;
    }

    public RichOutputText getTbAQty_Bind() {
        return tbAQty_Bind;
    }


    public void GetTotalQty_TBA(ActionEvent actionEvent) {
        // Add event code here...
    }


    public String callAttachment() throws IOException {
      
        String doc= null;     
        BindingContext bindingContext = BindingContext.getCurrent(); 
        DCDataControl dc = bindingContext.findDataControl("AppModuleDataControl"); //
        ApplicationModule am  = dc.getApplicationModule() ;
        ViewObject findViewObject =  am.findViewObject("MnjMfgPrecostingHView1");
        
        try {
            doc = findViewObject.getCurrentRow().getAttribute("HeaderNo").toString();
        } catch (Exception e) {
            // TODO: Add catch code
            ;
        }    
      
      
    //            String newPage =
    //                "http://192.168.200.115:7003/FileUploading-ViewController-context-root/faces/view1?doc=MB&docNo="+doc;
    //            // String newPage = "http://localhost:7101/PurchaseMemo-ViewController-context-root/faces/SearchPG?headerId="+getBomId().getValue();
    //            FacesContext ctx = FacesContext.getCurrentInstance();
    //            ExtendedRenderKitService erks =
    //                Service.getService(ctx.getRenderKit(), ExtendedRenderKitService.class);
    //            String url = "window.open('" + newPage + "','_self');";
    //            erks.addScript(FacesContext.getCurrentInstance(), url);
            
            //...........chnging....................//
//        FacesContext fc = FacesContext.getCurrentInstance();
//        HttpServletResponse response = (HttpServletResponse)fc.getExternalContext().getResponse();
//        
//        response.sendRedirect("http://192.168.200.106:7003/FileUploading-ViewController-context-root/faces/view1?doc=PC&docNo="+doc);
//        fc.responseComplete();
        //...............changing end..........//
                String newPage =
                 "http://192.168.200.115:7003/FileUploading-ViewController-context-root/faces/view1?doc=CD_Invoice_No&docNo="+doc;
             // String newPage = "http://localhost:7101/PurchaseMemo-ViewController-context-root/faces/SearchPG?headerId="+getBomId().getValue();
             FacesContext ctx = FacesContext.getCurrentInstance();
             ExtendedRenderKitService erks = Service.getService(ctx.getRenderKit(), ExtendedRenderKitService.class);
             String url = "window.open('" + newPage + "','_blank','toolbar=no,location=no,menubar=no,alwaysRaised=yes,height=500,width=1100');";
             erks.addScript(FacesContext.getCurrentInstance(), url);
    
        

        return null;
    }


    //-------------------------------------------------------------------------Farabi-----------------------------------------------------------//
    
    
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

    public void itemDialogListener(DialogEvent dialogEvent) {
        // Add event code here...
        populateBundlesRec();
        AdfFacesContext.getCurrentInstance().addPartialTarget(itemTable);
        AdfFacesContext.getCurrentInstance().addPartialTarget(dryDetailsTable);
        AdfFacesContext.getCurrentInstance().addPartialTarget(wetDetailsTable);
    }
    
    public void populateBundlesRec() {

                ViewObject populatevo = am.getPOCLinesCopyVO1(); // pop up view
                // populatevo.executeQuery();
                //System.out.println("--------------------------------populateBundlesRec-----------------------------------------------");
                Row[] r = populatevo.getAllRowsInRange();
                //System.out.println(" ------------------------------populateBundlesRec after row-------------------------------------------------");
                System.out.println("Drop ------->" + r.length);
                for (Row row : r) {
                    //System.out.println("MultiSelect Check--->" + row.getAttribute("MultiSelect"));
                    //System.out.println("-------------------------------------inside for loop------------------------------------------");
                    if (
                        row.getAttribute("MultiSelect") != null &&
                        row.getAttribute("MultiSelect").equals(true)
                       ) 
                    {
                        //System.out.println("-------------------------------------inside if cond------------------------------------------");
                        //System.out.println("MultiSelect --->" + row.getAttribute("MultiSelect"));
                        String lineId = row.getAttribute("LineId").toString();
                        System.out.println("line id-------------->" +lineId);
                        populateItemDetails(lineId); /// method to populate data
                        populateWetDetails(lineId);
                        populateDryDetails(lineId);
                    }
                }
            }
            
            
            public void populateItemDetails(String lineId) {
                
                String query= "  SELECT DISTINCT\n" + 
                            "       MMPID.DETAIL_ID,\n" + 
                            "       MMPID.LINE_ID,\n" + 
                            "       (select t.value_description\n" + 
                            "         from MNJ_ITEM_PREFIX_V t\n" + 
                            "         where t.flex_value = to_char(MMPID.Item_Prefix))PREFIX_DESC,\n" + 
                            "       MMPID.REF_CODE,\n" + 
                            "       MMPID.ITEM_DESC,\n" + 
                            "       MMPID.UOM,\n" + 
                            "       MMPID.PRICE_UOM,\n" + 
                            "       MMPID.ACTUAL_UNIT_PRICE,\n" + 
                            "       MMPID.ADD_CHARGE_LC,\n" + 
                            "       MMPID.ADD_CRG_PRCNT,\n" + 
                            "       MMPID.FINAL_UNIT_PRICE,\n" + 
                            "       MMPID.CONS_PER_PCS,\n" + 
                            "       MMPID.WASTAGE_IN_PER,\n" + 
                            "       MMPID.COST_PER_PCS,\n" + 
                            "       MMPID.FINAL_COST_PER_PCS,\n" + 
                            "       MMPID.REMARKS,\n" + 
                            "       MMPID.UOM_CONV,\n" + 
                            "       MMPID.SUPPLIER_ID,\n" + 
                            "       MMPID.PAYMENT_TERM_ID,\n" + 
                            "       MMPID.DELIVERY_TERM_ID,\n" + 
                            "       MMPID.STATUS,\n" + 
                            "       MMPID.ADD_PRICE_FOB,\n" + 
                            "       MMPID.ADD_PRICE_MOQ,\n" + 
                            "       MMPID.BUFER,\n" + 
                            "       MMPID.ADD_DEDUCT_COST,\n" + 
                            "       MMPID.WASTAGE_IN_QTY,\n" + 
                            "       MMPID.CONS_WITH_WASTAGE,\n" + 
                            "       MMPID.FINAL_CONS,\n" + 
                            "       MMPID.FAB_FINANCE_COST\n" + 
                            "  FROM MNJ_MFG_PRECOSTING_ITEM_D MMPID,MNJ_MFG_PRECOSTING_L MMPL,\n" + 
                            "        QA_PLANS QP,QA_RESULTS QR\n" + 
                            "  \n" + 
                            "  WHERE MMPID.ITEM_PREFIX=QR.CHARACTER2\n" + 
                            "      AND qr.plan_id=qp.plan_id\n" + 
                            "      AND UPPER(QP.NAME) = 'SEQUENCE NO POC' \n" + 
                            "      AND MMPID.LINE_ID=MMPL.LINE_ID\n" + 
                            "      AND MMPID.LINE_ID=?\n" + 
                            "  ORDER  BY MMPID.DETAIL_ID";
                
               
                ResultSet resultSet=null;                                                                                 
                PreparedStatement createStatement= am.getDBTransaction().createPreparedStatement(query,0);
                //System.out.println("2");
                try {
                    createStatement.setString(1, lineId);
                    resultSet=createStatement.executeQuery();
                    //System.out.println("3");
                    //System.out.println("populateLinesTest ------->");
                    //System.out.println("-------------------------------------populateLinesTestRec------------------------------------------");
                    
                    ViewObject vo = am.getMnjMfgPrecostingItemDView1(); // in which you wants to populate daa
                    
                    removeData(vo);
                    //System.out.println("4");
                    
                    while(resultSet.next()){
                            // code to display the rows in the table.
                            Row linerow = vo.createRow();
                            linerow.setNewRowState(Row.STATUS_INITIALIZED);  
                            
                            linerow.setAttribute("PrefixDesc",resultSet.getString("PREFIX_DESC"));
                            //System.out.println(resultSet.getString("PREFIX_DESC"));
                            linerow.setAttribute("RefCode", resultSet.getString("REF_CODE")); 
                            linerow.setAttribute("ItemDesc",resultSet.getString("ITEM_DESC"));
                            linerow.setAttribute("Uom",resultSet.getString("UOM"));
                            linerow.setAttribute("PriceUom",resultSet.getString("PRICE_UOM"));
                            linerow.setAttribute("ActualUnitPrice",resultSet.getString("ACTUAL_UNIT_PRICE"));
                            linerow.setAttribute("AddChargeLc",resultSet.getString("ADD_CHARGE_LC"));
                            linerow.setAttribute("AddCrgPrcnt",resultSet.getString("ADD_CRG_PRCNT"));
                            linerow.setAttribute("FinalUnitPrice",resultSet.getString("FINAL_UNIT_PRICE"));
                            linerow.setAttribute("ConsPerPcs",resultSet.getString("CONS_PER_PCS"));
                            linerow.setAttribute("WastageInPer",resultSet.getString("WASTAGE_IN_PER"));
                            linerow.setAttribute("CostPerPcs",resultSet.getString("COST_PER_PCS"));
                            linerow.setAttribute("FinalCostPerPcs",resultSet.getString("FINAL_COST_PER_PCS"));
                            linerow.setAttribute("Remarks",resultSet.getString("REMARKS"));
                            linerow.setAttribute("UomConv",resultSet.getString("UOM_CONV"));   
                        
                            linerow.setAttribute("PaymentTermId",resultSet.getString("PAYMENT_TERM_ID"));  
                            linerow.setAttribute("DeliveryTermId",resultSet.getString("DELIVERY_TERM_ID"));  
                            linerow.setAttribute("Status",resultSet.getString("STATUS"));  
                            linerow.setAttribute("AddPriceFob",resultSet.getString("ADD_PRICE_FOB"));  
                            linerow.setAttribute("AddPriceMoq",resultSet.getString("ADD_PRICE_MOQ"));  
                            linerow.setAttribute("Bufer",resultSet.getString("BUFER"));  
                            linerow.setAttribute("AddDeductCost",resultSet.getString("ADD_DEDUCT_COST"));  
                            linerow.setAttribute("WastageInQty",resultSet.getString("WASTAGE_IN_QTY"));  
                            linerow.setAttribute("ConsWithWastage",resultSet.getString("CONS_WITH_WASTAGE"));  
                            linerow.setAttribute("FinalCons",resultSet.getString("FINAL_CONS"));  
                            linerow.setAttribute("FabFinanceCost",resultSet.getString("FAB_FINANCE_COST"));  
                            
                            vo.insertRow(linerow); 
                    }
    
                } catch (SQLException e) {
                    System.out.println("Exception in getting query data");
                }

            } //end of populateLines
            
            public void populateWetDetails(String lineId) {
                String query= "  SELECT DISTINCT\n" + 
                            "       MMPWD.LINE_ID, \n" + 
                            "       MMPWD.COST_AMOUNT, \n" + 
                            "       MMPWD.MANUAL_PRICE, \n" + 
                            "       MMPWD.PROCESS_ID, \n" + 
                            "       MMPWD.PROCESS_NAME, \n" + 
                            "       MMPWD.PROFIT, \n" + 
                            "       MMPWD.TOTAL,\n" + 
                            "       (MMPWD.PROFIT + MMPWD.COST_AMOUNT) NEW_TOTAL_VAL\n" + 
                            "       \n" + 
                            "  FROM MNJ_MFG_PRECOSTING_L MMPL,\n" + 
                            "       MNJ_MFG_PRECOSTING_WET_D MMPWD\n" + 
                            "  \n" + 
                            "  WHERE MMPWD.LINE_ID=MMPL.LINE_ID\n" + 
                            "        AND MMPWD.LINE_ID=?";
                
                ResultSet resultSet=null;                                                                                 
                PreparedStatement createStatement= am.getDBTransaction().createPreparedStatement(query,0);
                try {
                    createStatement.setString(1, lineId);
                    resultSet=createStatement.executeQuery();
                    
                    //System.out.println("populateLinesTest ------->");
                    //System.out.println("-------------------------------------populateLinesTestRec------------------------------------------");
                    
                    ViewObject vo = am.getMnjMfgPrecostingWetDView1(); // in which you wants to populate daa
                    removeData(vo);
                   
                    while(resultSet.next()){
                            // code to display the rows in the table.
                            Row linerow = vo.createRow();
                            linerow.setNewRowState(Row.STATUS_INITIALIZED);  
                            
                            linerow.setAttribute("ProcessName",resultSet.getString("PROCESS_NAME"));
                            //System.out.println(resultSet.getString("PROCESS_NAME"));
                            linerow.setAttribute("CostAmount", resultSet.getString("COST_AMOUNT")); 
                            linerow.setAttribute("Profit",resultSet.getString("PROFIT"));
                            linerow.setAttribute("NewTotalVal",resultSet.getString("NEW_TOTAL_VAL"));
                            linerow.setAttribute("ManualPrice",resultSet.getString("MANUAL_PRICE")); 
                            
                            vo.insertRow(linerow); 
                    }
                
                } catch (SQLException e) {
                    System.out.println("Exception in getting query data");
                }
            }
            
            public void populateDryDetails(String lineId) {
                String query= "SELECT DISTINCT\n" + 
                            "         MMPDD.LINE_ID, \n" + 
                            "       MMPDD.COST_AMOUNT,\n" + 
                            "       MMPDD.MANUAL_PRICE, \n" + 
                            "       MMPDD.PROCESS_ID, \n" + 
                            "       MMPDD.PROCESS_NAME, \n" + 
                            "       MMPDD.PROFIT, \n" + 
                            "       MMPDD.TOTAL\n" + 
                            "       \n" + 
                            "  FROM MNJ_MFG_PRECOSTING_L MMPL,\n" + 
                            "       MNJ_MFG_PRECOSTING_DRY_D MMPDD    \n" + 
                            "  \n" + 
                            "  WHERE MMPDD.LINE_ID=MMPL.LINE_ID\n" + 
                            "        AND MMPDD.LINE_ID=?";
                ResultSet resultSet=null;                                                                                 
                PreparedStatement createStatement= am.getDBTransaction().createPreparedStatement(query,0);
                try {
                    createStatement.setString(1, lineId);
                    resultSet=createStatement.executeQuery();
                    
                    //System.out.println("populateLinesTest ------->");
                    //System.out.println("-------------------------------------populateLinesTestRec------------------------------------------");
                    
                    ViewObject vo = am.getDryDetailVO1(); // in which you wants to populate daa
                    removeData(vo);
                    
                    while(resultSet.next()){
                            // code to display the rows in the table.
                            Row linerow = vo.createRow();
                            linerow.setNewRowState(Row.STATUS_INITIALIZED);  
                            
                            linerow.setAttribute("ProcessName",resultSet.getString("PROCESS_NAME"));
                            //System.out.println(resultSet.getString("PROCESS_NAME"));
                            linerow.setAttribute("CostAmount", resultSet.getString("COST_AMOUNT")); 
                            linerow.setAttribute("Profit",resultSet.getString("PROFIT"));
                            linerow.setAttribute("Total",resultSet.getString("TOTAL"));
                            linerow.setAttribute("ManualPrice",resultSet.getString("MANUAL_PRICE"));
                            
                            
                            vo.insertRow(linerow); 
                    }
                
                } catch (SQLException e) {
                    System.out.println("Exception in getting query data");
                }
            }
            
        public void removeData(ViewObject vo){
            RowSetIterator iter = vo.createRowSetIterator(null);       
            iter.reset();
            while (iter.hasNext()) {
                Row row = iter.next();
                row.remove();
                // process the row here
            }
            iter.closeRowSetIterator();
            vo.executeEmptyRowSet();  
        }
        
        public void copyItemsPopup(PopupFetchEvent popupFetchEvent) {
            // Add event code here...
              
                ViewObject popVo = am.getPOCLinesCopyVO1();    /// pop up view   
                popVo.executeQuery();
        }
               

        public void setDryDetailsTable(RichTable dryDetailsTable) {
            this.dryDetailsTable = dryDetailsTable;
        }
    
        public RichTable getDryDetailsTable() {
            return dryDetailsTable;
        }
    
        public void setWetDetailsTable(RichTable wetDetailsTable) {
            this.wetDetailsTable = wetDetailsTable;
        }
    
        public RichTable getWetDetailsTable() {
            return wetDetailsTable;
        }

    
       
    //-------------------------------------------------------------------------Farabi-----------------------------------------------------------//


    public void goToSizeDetailForm(ActionEvent actionEvent) {
        // Add event code here...
    }

    public void sizeBreakdownPopUpFetchPreAct(ActionEvent actionEvent) {
        // Add event code here...
        System.out.println("============== in  sizeBreakdownPopUpFetchPreAct method ");
        System.out.println("============== bpo block  : "+bpoTableBlock);
        bpoTableBlock= 1;
        System.out.println("============== bpo block  : "+bpoTableBlock);
        
    }

    public void sizebreakDownCallFromCreateBPOblock(ActionEvent actionEvent) {
        
       bpoTableBlock=1;
        
        
        sizeBreakdownPopUpFetch(bpoTableBlock);
        
    }

  

    public void setP1(RichPopup p1) {
        this.p1 = p1;
    }

    public RichPopup getP1() {
        return p1;
    }

    public void setCreateOrderForm(RichForm createOrderForm) {
        this.createOrderForm = createOrderForm;
    }

    public RichForm getCreateOrderForm() {
        return createOrderForm;
    }

 

    private Row getcurrentBpoRow(int bpoTableBlock) {
        /**this method returns the current row's bpo  from which the
         * size breakdown pop is called based on bpoTableBlock flag in parameter
           if flag = 0 then bpo of first block elsi if flag = 1 then   then bpo of second block  returned          */
        ViewObject vo ;
        if (bpoTableBlock==1){
           
            vo= am.getMnjPrecostCreateBpoDVO1();
            
        }else {
            vo= am.getCreateSaleOrderLinesVO1();
        }
        currentBpo = vo.getCurrentRow().getAttribute("BpoNo").toString();
        return vo.getCurrentRow();
    }

    public void createSizLineRow(ActionEvent actionEvent) {
        ViewObject sizLineVo = am.getCustMnjOntSoObinSizline_LineVO1();
        String country = "";
        String shipMode = "";
        Date deliveryStartDate = null;
        Date deliveryEndDate = null;
        String deliveryType = "";
          
        try{
            country =  currentBpoRow.getAttribute("Country").toString();
            shipMode =  currentBpoRow.getAttribute("ShipMode").toString();
            deliveryStartDate = (Date)currentBpoRow.getAttribute("DeliveryStartDate");
            deliveryEndDate = (Date)currentBpoRow.getAttribute("DeliveryEndDate");
            
           // String deliveryStartDate =  currentBpoRow.getAttribute("DeliveryStartDate").toString();
          //  String deliveryEndDate =  currentBpoRow.getAttribute("DeliveryEndDate").toString();
            deliveryType =  currentBpoRow.getAttribute("Incoterms").toString();     
        }catch(Exception e){
//            FacesMessage Message = new FacesMessage("Please fill up country, ship mode and delivery date in sales order first!");   
//             Message.setSeverity(FacesMessage.SEVERITY_ERROR);   
//             FacesContext fc = FacesContext.getCurrentInstance();   
//             fc.addMessage(null, Message);  
//             //return;
            ;
        }
        
        Row sizLine = sizLineVo.createRow();
       // sizLineVo.setCurrentRow(sizLine);
        sizLine.setAttribute("Country", country);
        sizLine.setAttribute("ShipMode", shipMode);
        sizLine.setAttribute("DeliveryDate", deliveryStartDate);
        sizLine.setAttribute("DeliveryEndDate", deliveryEndDate);
        sizLine.setAttribute("DeliveryTerm", deliveryType);
        setBuyerId();
        
    }

    public void sizeBreakdownCallFromCreateSalesOrderBlock(ActionEvent actionEvent) {
      
               bpoTableBlock=0;
             
               sizeBreakdownPopUpFetch(bpoTableBlock);
               
             
    }

    private void sizeBreakdownPopUpFetch( int bpoTableBlock) {
         
        currentBpoRow = getcurrentBpoRow(bpoTableBlock);
        
        ViewObject headerVo  = am.getMnjMfgPrecostingHView1();
        String headerId,buyerId,season,styleNo; 
        
        
         try{
            headerId=headerVo.getCurrentRow().getAttribute("HeaderId").toString();
         }
         catch(Exception e){
             return;
         }
         try{
            buyerId=headerVo.getCurrentRow().getAttribute("BuyerId").toString();
         }
         catch(Exception e){
             return;
         }
         try{
            season=headerVo.getCurrentRow().getAttribute("Season").toString();
         }
         catch(Exception e){
             return;
         }
         try{
           styleNo=headerVo.getCurrentRow().getAttribute("StyleNo").toString();
         }
         catch(Exception e){
             return;
         }
         

          System.out.println("...................................test header Id= "+ headerId);   
         System.out.println("...................................test buyerId= "+ buyerId);
         System.out.println("...................................test season= "+ season);
         System.out.println("...................................test styleNo= "+ styleNo);
         ViewObject  sizeHeaderVo=am.getsizeHeaderVO1();
         sizeHeaderVo.setWhereClause("BPO_NO = '"+currentBpo+"'"+"AND CUSTOMER_ID ='"+buyerId+"'"+"AND SEASON ='"+season+"'"+"AND (  STYLE_NAME ='"+styleNo+"'"+" OR STYLE_NO ='"+styleNo+"')");
         System.out.println("...................................test bpo No= "+ currentBpo); 
         sizeHeaderVo.executeQuery();
         //check wheather current bpo heabeen generated to standard form;
         if (sizeHeaderVo.getAllRowsInRange().length==0){
             
                  FacesMessage Message = new FacesMessage("BPO has not been generated yet. Try a few Later");   
                  Message.setSeverity(FacesMessage.SEVERITY_INFO);   
                  FacesContext fc = FacesContext.getCurrentInstance();   
                  fc.addMessage(null, Message); 
                  return ;
         }
         //popup fetch
         
         RichPopup.PopupHints ph = new  RichPopup.PopupHints();
         
         
         
         ph.add(RichPopup.PopupHints.HintTypes.HINT_ALIGN_ID, createOrderForm)
         .add(RichPopup.PopupHints.HintTypes.HINT_LAUNCH_ID, createOrderForm)
         .add(RichPopup.PopupHints.HintTypes.HINT_ALIGN, RichPopup.PopupHints.AlignTypes.ALIGN_OVERLAP);        
         
         getP1().show(ph);
    }


    public void saveSizeBreakDown(ActionEvent actionEvent) {
        
        System.out.println("=========================    in      saveSizeBreakDown");
        ViewObject sizeHeaderVo =  am.getsizeHeaderVO1();
        Row  currentSizeHeaderRow =  sizeHeaderVo.getCurrentRow();
//        ViewObject lineVo=am.getCustMnjOntSoObinSizline_LineVO1();
//        Row  currentSizeLineRow =  lineVo.getCurrentRow();
//        
        Double stnQuantity;
        Double totalLineQty;
        Double totalDetailQty;
        
        
        FacesMessage Message = new FacesMessage("Total Line Quantity and STN Quantity are not equal. ");   
             Message.setSeverity(FacesMessage.SEVERITY_INFO);   
             FacesContext fc = FacesContext.getCurrentInstance(); 
     //   Number stnQuantity = (Number)currentSizeHeaderRow.getAttribute("OrderedQuantity");
     // Number totalLineQty = (Number)currentSizeHeaderRow.getAttribute("QtyTotal");
             
    
    
      
        try{
            stnQuantity = Double.parseDouble(currentSizeHeaderRow.getAttribute("OrderedQuantity").toString());
            
            totalLineQty = Double.parseDouble(currentSizeHeaderRow.getAttribute("QtyTotal").toString());
            totalDetailQty = Double.parseDouble(currentSizeHeaderRow.getAttribute("QtyTotal").toString());
            
//            System.out.println("=====================================================================================================");
//            System.out.println("========================= ordered quantity (stn)"+stnQuantity);
//            System.out.println("========================= totalLineQty  "+totalLineQty);
        }
        catch(Exception e){
            fc.addMessage(null, Message); 
            return;
        }
         
        
      
            
        
        if(!stnQuantity.equals(totalLineQty)){
           
            fc.addMessage(null, Message); 
            
            return;
        }
        
//        OperationBinding ob1 = executeOperation("Commit");
//        ob1.execute();
        
         if(getSizeQtyCheck()==true){
            am.getDBTransaction().commit();
        }else{
            String message="Line Quantity and STN Quantity are not equal.";
            FacesMessage fm = new FacesMessage(message);
            fm.setSeverity(FacesMessage.SEVERITY_WARN);
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, fm);
        }
        
        
        
    }

    

    private void remark() {
        am.validateLines();
        String ab="check remark";
   // String re= am.validateLines();
        
//    if(re=="Same") {
//        String message ="Check remark";
//        FacesMessage fm = new FacesMessage(message);
//        fm.setSeverity(FacesMessage.SEVERITY_INFO);
//        FacesContext context = FacesContext.getCurrentInstance();
//        context.addMessage(null, fm);
//        checkremark();
//        
//    }
        
        
    }

    public void setRemarkAction(RichColumn remarkAction) {
        this.remarkAction = remarkAction;
    }

    public RichColumn getRemarkAction() {
        return remarkAction;
    }

    private void checkremark() {
    
    }

    public void deleteCopy(ActionEvent actionEvent) {
    String flag=null;
        
        ViewObject vo = am.getCreateSaleOrderLinesVO1();

      
       
      String cloneFlag;
        String bpoStatus;
        
      try{
          cloneFlag =  vo.getCurrentRow().getAttribute("CloneFlag").toString(); 
          
      }catch(Exception e){
          cloneFlag="N";
      }
        try{
            bpoStatus =  vo.getCurrentRow().getAttribute("BpoStatus").toString(); 
            
        }catch(Exception e){
            bpoStatus ="NotEntered";
        }
      
      
      if ( cloneFlag.equals("Y") && !bpoStatus.equals("ENTERED")   ) {
          vo.removeCurrentRow();
              
         //   vo.executeQuery();     // commented on 18.feb.18
           
        }
       else {
                         
           flag="BPO's which are already Generated or not cloned cann't be deleted  ";
                   FacesMessage fm = new FacesMessage(flag);
                   fm.setSeverity(FacesMessage.SEVERITY_INFO);
                   FacesContext context = FacesContext.getCurrentInstance();
                   context.addMessage(null, fm);
          
       }
     
     
//        PopUpBean p1 =new PopUpBean ();
//        
//       AdfFacesContext.getCurrentInstance().addPartialTarget( p1.bpOHTable);
        
     
    }

    public void backToPocRef(ActionEvent actionEvent) {
        
        ViewObject lineVo =am.getMnjMfgPrecostingLView1();
        //lineVo.executeQuery();
        // Add event code here...
        
      refreshQueryKeepingCurrentRow(lineVo) ;
        
        
    }

    private void refreshQueryKeepingCurrentRow(ViewObject lineVo) 
    {
        try{
            Row currentRow = lineVo.getCurrentRow();
            Key currentRowKey = currentRow.getKey();
            System.out.println(".....................line 1");
            int rangePosOfCurrentRow = lineVo.getRangeIndexOf(currentRow);
             System.out.println(".....................line 12");
            int rangeStartBeforeQuery =lineVo.getRangeStart();
             System.out.println(".....................line 13");
            lineVo.executeQuery();
            lineVo.setRangeStart(rangeStartBeforeQuery);
             System.out.println(".....................line 14");
            /*
             * In 10.1.2, there is this convenience method we could use
             * instead of the remaining lines of code
             *
             *  findAndSetCurrentRowByKey(currentRowKey,rangePosOfCurrentRow);
             *  
             */
            Row[] rows = lineVo.findByKey(currentRowKey, 15);
             System.out.println(".....................line 16");
            if (rows != null && rows.length == 1)
            {
                System.out.println(".....................line 17");
               lineVo.scrollRangeTo(rows[0], rangePosOfCurrentRow);
                System.out.println(".....................line 18");
               lineVo.setCurrentRowAtRangeIndex(lineVo.getRangeIndexOf(rows[0]));
                System.out.println(".....................line 19");
            }
            }
        catch(Exception e) {
            lineVo.executeQuery();
        }
        }


    public void setSizeBreakdownButton(RichCommandButton sizeBreakdownButton) {
        this.sizeBreakdownButton = sizeBreakdownButton;
    }

    public RichCommandButton getSizeBreakdownButton() {
        return sizeBreakdownButton;
    }

    public void deleteChildBpo(ActionEvent actionEvent) {
        
        ViewObject childBpoVo = am.getMnjPrecostCreateBpoDVO1();
       Row currentRow ;
       String bpoStatus;
       
       try{
           currentRow = childBpoVo.getCurrentRow();
       }
       catch(Exception e){
           return;
       }
       
       try{
           bpoStatus = currentRow.getAttribute("BpoStatus").toString();
       }
       catch(Exception e){
           bpoStatus ="";
       }
      
        
       if(bpoStatus.equals("ENTERED")){
           FacesMessage fm = new FacesMessage("BPO Already Generated and cann't be deleted");
           fm.setSeverity(FacesMessage.SEVERITY_INFO);
           FacesContext context = FacesContext.getCurrentInstance();
           context.addMessage(null, fm);
        //   AdfFacesContext.getCurrentInstance().addPartialTarget(subCostingTable);
           return;
           
       }
       else{
           childBpoVo.removeCurrentRow();
         
       }
       
        
       
        
      
    }

    public void saveAll(ActionEvent actionEvent) {
        // Add event code here...
    }

    public void saveAllOrders(ActionEvent actionEvent) {
        // Add event code here...
        ViewObject bpoVo = am.getCreateSaleOrderLinesVO1();
        
        
        
        Boolean checkBpoQty;
        
        //this method checks the sum of  bpo quantity of child Bpos and returns false if sum is greater than parent Tba Quantity
        // and set current bpo row  if returns false
       checkBpoQty =  isBpoQtyLessthanTbaQty();
      
      
       if(checkBpoQty==true){
         
           if(doCheckAndSave()==true){
               checkValidation();
           }else{
               String msg="Qty in TBA-Qty-Braekdwn is greater than balance Qty, Plz Adjust Qty.";
               FacesMessage fm = new FacesMessage(msg);
               fm.setSeverity(FacesMessage.SEVERITY_INFO);
               FacesContext context = FacesContext.getCurrentInstance();
               context.addMessage(null,fm);
           }
         
          // checkValidation();
          // am.getDBTransaction().commit();
       }
       else{
           this.refreshQueryKeepingCurrentRow(bpoVo);
           
           FacesMessage fm = new FacesMessage("BPO Qty cann't be greater than TBA Qty");
           fm.setSeverity(FacesMessage.SEVERITY_INFO);
           FacesContext context = FacesContext.getCurrentInstance();
           context.addMessage(null, fm);
           AdfFacesContext.getCurrentInstance().addPartialTarget(subCostingTable);
       }
    }


    public Boolean isBpoQtyLessthanTbaQty() {

        ViewObject bpoVo = am.getCreateSaleOrderLinesVO1();

        bpoVo.setRangeSize(50);
        RowSet childBpoRows;
        int totalChildBpoQty = 0;
        int ChildBpoQty = 0;
        int bpoQuantity = 0;
        Row[] allBpoRows = bpoVo.getAllRowsInRange();
        for (Row bpoRow : allBpoRows) {

            try {
                bpoQuantity =
                        Integer.parseInt(bpoRow.getAttribute("BpoQty").toString());
            } catch (Exception e) {
                bpoQuantity = 0;
            }

            childBpoRows =
                    (RowSet)bpoRow.getAttribute("MnjPrecostCreateBpoDVO");

            totalChildBpoQty = calculateTotalChildBpoQty(childBpoRows);

            if (totalChildBpoQty > bpoQuantity) {

                bpoVo.setCurrentRow(bpoRow);
                return false;

            }

        }
        return true;
    }

    private int calculateTotalChildBpoQty(RowSet childBpoRows) {
        
        int totalChildBpoQty=0;
        int ChildBpoQty=0;
        while(childBpoRows.hasNext()){
            Row childBpoRow = childBpoRows.next();
            try{
                ChildBpoQty = Integer.parseInt(childBpoRow.getAttribute("BpoQty").toString());
            }catch(Exception e){
                
                ChildBpoQty=0;
            }            
             totalChildBpoQty+= ChildBpoQty ;
            
        }
        return totalChildBpoQty;
    }


    public void setBpoQt_value(RichInputText bpoQt_value) {
        this.bpoQt_value = bpoQt_value;
    }

    public RichInputText getBpoQt_value() {
        return bpoQt_value;
    }

    public void autoPopup(ValueChangeEvent valueChangeEvent) {
        // Add event code here...
        String val=null;
        try {
            val =valueChangeEvent.getNewValue().toString();

        } catch (Exception e) {
            ;
        }
       
        if(val.equalsIgnoreCase("Service Item")){
            System.out.println("popup here");
            RichPopup.PopupHints hints = new RichPopup.PopupHints();
            getServicePopup().show(hints);
        }
    }

    public void setServicePopup(RichPopup servicePopup) {
        this.servicePopup = servicePopup;
    }

    public RichPopup getServicePopup() {
        return servicePopup;
    }

    public void setSelectedServiceItem(RichSelectOneRadio selectedServiceItem) {
        this.selectedServiceItem = selectedServiceItem;
    }

    public RichSelectOneRadio getSelectedServiceItem() {
        return selectedServiceItem;
    }

    public void serviceItemDilogListener(DialogEvent dialogEvent) {
        // Add event code here...
        ViewObject itemView=am.getMnjMfgPrecostingItemDView1();
        if (dialogEvent.getOutcome().name().equals("ok")) {
            String test =getSelectedServiceItem().getValue().toString();
           // callCopy(getSelectRadio().getValue().toString());
            itemView.getCurrentRow().setAttribute("RefCode",test);
           AdfFacesContext.getCurrentInstance().addPartialTarget(itemTable); 
        System.out.println("............................selected item "+test);
        } else if (dialogEvent.getOutcome().name().equals("cancel")) {
            BindingContainer bindings = getBindings();

        }
        
    }

    private void adjustment() {
        ViewObject testlineVo=am.getMnjMfgPrecostingLView1();
        double fob=  Double.parseDouble(testlineVo.getCurrentRow().getAttribute("FobWithComm").toString());
        double CM= Double.parseDouble( testlineVo.getCurrentRow().getAttribute("Cm").toString());
        // double print= MyMath.numeric3(getPrint());
        double val=0.0;
        try {
            val =Double.parseDouble(testlineVo.getCurrentRow().getAttribute("Print").toString());
            System.out.println("........adjust print value : "+val);

        } catch (Exception e) {
           val=0.0 ;
        }
        fob=fob+val;
        CM=CM+val;
        testlineVo.getCurrentRow().setAttribute("FobWithComm", Double.toString(fob));
        testlineVo.getCurrentRow().setAttribute("Cm", Double.toString(CM));
        AdfFacesContext.getCurrentInstance().addPartialTarget(subCostingTable);
    }

    private void checkValidation() {
        int counter=0;
        ViewObject createLineVO=am.getCreateSaleOrderLinesVO1();
        Row[] rows= createLineVO.getAllRowsInRange();
        for (Row row : rows){
           if(row.getAttribute("DeliveryStartDate")==null){
                FacesMessage fm = new FacesMessage("Error DeliveryStartDate is Blank");
               fm.setSeverity(FacesMessage.SEVERITY_INFO);
               FacesContext context = FacesContext.getCurrentInstance();
               context.addMessage(null, fm);
               
           }
           else if(row.getAttribute("Clasfic")==null){
                FacesMessage fm = new FacesMessage("Error Clasfic is Blank");
               fm.setSeverity(FacesMessage.SEVERITY_INFO);
               FacesContext context = FacesContext.getCurrentInstance();
               context.addMessage(null, fm);
               
           }
           else if(row.getAttribute("Incoterms")==null){
                FacesMessage fm = new FacesMessage("Error Delivery Terms is Blank");
               fm.setSeverity(FacesMessage.SEVERITY_INFO);
               FacesContext context = FacesContext.getCurrentInstance();
               context.addMessage(null, fm);
               //row.getAttribute("PoType").equals(null);
               
           }
           else if(row.getAttribute("PoType")==null){
                FacesMessage fm = new FacesMessage("Error PO Type is Blank");
               fm.setSeverity(FacesMessage.SEVERITY_INFO);
               FacesContext context = FacesContext.getCurrentInstance();
               context.addMessage(null, fm);
               //row.getAttribute("PoType").equals(null);
               
           }
           else{
              
             counter=counter+1;
           }
           
           
               
           }
          // #{row.bindings.GmStatus.inputValue=='Approved'? 'false':'true'}
          if(counter==rows.length){
          BpoTbaNumberCal();
            doTrimsBPOs();
           // doTrimsBPOsDet();
            checkSaleId();
            checkItemIdAndUpdate();
            
           am.getDBTransaction().commit();
           FacesMessage fm = new FacesMessage("Saved");
           fm.setSeverity(FacesMessage.SEVERITY_INFO);
           FacesContext context = FacesContext.getCurrentInstance();
           context.addMessage(null, fm);
        }
    }

    private void BpoTbaNumberCal() {  //Todo
        
        ViewObject header=am.getMnjMfgPrecostingHView1();
        ViewObject createsalOrderVO=am.getCreateSaleOrderLinesVO1();
        ViewObject createBpoDView=am.getMnjPrecostCreateBpoDVO1();
        Row[] number = createBpoDView.getAllRowsInRange();
        Row[] rows = createsalOrderVO.getAllRowsInRange();
        int bpo=0,tba=0;
        double BP=0.0,TB=0.0,BpoQ=0.0;
        String cls,status;
        for (Row row : rows){
            try{
                
                
                cls=  row.getAttribute("Clasfic").toString();
            }
            catch(Exception e) {
                cls=null;
                
            }
            try{
                status=row.getAttribute("BpoStatus").toString();
                
            }
            catch(Exception e) {
                status="NULL";
                
            }
           // System.out.println("..............................BPO QUENTITY="+status);
            try{
             BpoQ=Double.parseDouble( row.getAttribute("BpoQty").toString());
            }
            catch(Exception e) {
                BpoQ=0.0; 
            }
               // System.out.println("..............................BPO ="+BpoQty);
              if(cls.equalsIgnoreCase("BPO")) {
                bpo=bpo+1;
                if(status.equalsIgnoreCase("ENTERED")) {
                   
                     BP=BP+BpoQ;
                    //System.out.println("..............................BPO QUENTITY="+BP);
                }
              
               // row.setAttribute("BpoInfo", "Blank");
            }
            if(cls.equalsIgnoreCase("TBA")) {
                tba=tba+1;
                
                if(status.equalsIgnoreCase("ENTERED")){
                     TB= TB+BpoQ;
                }
                
                
//                RowSet child = (RowSet)row.getAttribute("MnjPrecostCreateBpoDVO");
//                int a=child.getRowCount();
//                if(a==0) {
//                    row.setAttribute("BpoInfo", "Blank");
//                }
//                else {
//                    row.setAttribute("BpoInfo", String.valueOf(a));
//                }
                
             // System.out.println("......................test "+a);
               // number_bpo_line();
            }
           // System.out.println("..............................BPO QUENTITY="+BP);
             
        }
        
        System.out.println("..............................TBA FINAL QUENTITY="+TB);
        System.out.println("..............................BPO final QUENTITY="+BP);
        
        String BPO=String.valueOf(BP);
        String TBA=String.valueOf(TB);
        StringBuilder setVal = null;
        setVal = new StringBuilder();
        //setVal.append(getValue1().getValue() + "%"+getName1().getValue()+", ");
        setVal.append(String.format("BPO="+BPO+"\n"+"TBA="+TBA));
        header.getCurrentRow().setAttribute("BpoQty",setVal.toString());
        
        
//        for (Row row : number){
//            try{
//                cls=  row.getAttribute("Classfic").toString();
//            }
//            catch(Exception e) {
//                cls=null;
//            }
//            if(cls.equalsIgnoreCase("BPO")) {
//                bpo=bpo+1;
//            }
//            if(cls.equalsIgnoreCase("TBA")) {
//                tba=tba+1;
//                
//            }
//         
//        }
//
//        System.out.println(".............................test bpo no  & tab No"+bpo+" & "+tba);
//        String BPO=String.valueOf(bpo);
//        String TBA=String.valueOf(tba);
//        header.getCurrentRow().setAttribute("Bpotba", BPO);
//        //  OperationBinding ob = executeOperation("Commit");
//        // ob.execute();
//        // System.out.println("......................test bpo value " +header.getCurrentRow().getAttribute("Bpotba"));
//        StringBuilder setVal = null;
//        setVal = new StringBuilder();
//        //setVal.append(getValue1().getValue() + "%"+getName1().getValue()+", ");
//        setVal.append(String.format("BPO="+BPO+"\n"+"TBA="+TBA));
//        header.getCurrentRow().setAttribute("Bpotba",setVal.toString());
//        // System.out.println("......................test bpo value " +header.getCurrentRow().getAttribute("Bpotba"));
    }

    private double febricValue() {
        String name=null;
        String itemType=null;
        ViewObject hvo=am.getMnjMfgPrecostingHView1();
        try {
           itemType= hvo.getCurrentRow().getAttribute("ItemPurchaseType").toString();
        } catch (Exception e) {
            itemType="Fabric"; ;
        }
        if(itemType.equalsIgnoreCase("Imported")){
            name="Fabric-Foreign";
        }else if(itemType.equalsIgnoreCase("Local")){
            name="Fabric-Local";
        }else if(itemType.equalsIgnoreCase("Fabric")){
            name="Fabric";
        }else{
            name="Fabric"; 
        }
        
        System.out.println("ItemPurchaseType is_----------------->"+itemType);
        
        System.out.println("Item name_----------------->"+name);
        double value = 0;
        
        String date=null;
        try {
            date= hvo.getCurrentRow().getAttribute("CreationDate").toString();
        } catch (Exception e) {
            ;
        }
        
        String stmt = "BEGIN :1 := mnj_get_costing_finance_price(:2,:3); end;";
        java.sql.CallableStatement cs =am.getDBTransaction().createCallableStatement(stmt, 1);
        try {
            cs.registerOutParameter(1, OracleTypes.NUMBER);
            cs.setString(2, name);
            cs.setString(3,date);
            cs.execute();
            value = cs.getDouble(1);
            cs.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("febric--------------with date---------------------price is"+value);
        return value;
        
         //end of if condition
    }

    private double trimValue() {
        String name="Trims";
        double value = 0;
        ViewObject hvo=am.getMnjMfgPrecostingHView1();
        
        String date=null;
        try {
            date= hvo.getCurrentRow().getAttribute("CreationDate").toString();
        } catch (Exception e) {
            ;
        }
        String stmt = "BEGIN :1 := mnj_get_costing_finance_price(:2,:3); end;";
        java.sql.CallableStatement cs =am.getDBTransaction().createCallableStatement(stmt, 1);
        try {
            cs.registerOutParameter(1, OracleTypes.NUMBER);
            cs.setString(2, name);
            cs.setString(3,date);
            cs.execute();
            value = cs.getDouble(1);
            cs.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("trims-----------------with date------------------price is"+value);
        return value;
    }

    private double washValue() {
        String name="Wash";
        double value = 0;
        ViewObject hvo=am.getMnjMfgPrecostingHView1();
        
        String date=null;
        try {
            date= hvo.getCurrentRow().getAttribute("CreationDate").toString();
        } catch (Exception e) {
            ;
        }
        String stmt = "BEGIN :1 := mnj_get_costing_finance_price(:2,:3); end;";
        java.sql.CallableStatement cs =am.getDBTransaction().createCallableStatement(stmt, 1);
        try {
            cs.registerOutParameter(1, OracleTypes.NUMBER);
            cs.setString(2, name);
            cs.setString(3,date);
            cs.execute();
            value = cs.getDouble(1);
            cs.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("wash-----------------with date------------------price is"+value);
        return value;
    }

    public void setFabricFinancePrice(RichInputText fabricFinancePrice) {
        this.fabricFinancePrice = fabricFinancePrice;
    }

    public RichInputText getFabricFinancePrice() {
        return fabricFinancePrice;
    }

    public void setCmMerchad(RichInputText cmMerchad) {
        this.cmMerchad = cmMerchad;
    }

    public RichInputText getCmMerchad() {
        return cmMerchad;
    }

    public void setProductivity(RichInputText productivity) {
        this.productivity = productivity;
    }

    public RichInputText getProductivity() {
        return productivity;
    }

    public void setStyleEff(RichInputText styleEff) {
        this.styleEff = styleEff;
    }

    public RichInputText getStyleEff() {
        return styleEff;
    }

    public void setSamValue(RichInputText samValue) {
        this.samValue = samValue;
    }

    public RichInputText getSamValue() {
        return samValue;
    }
    
    public double FactoryAvgEff() {
        String unitName = null;
              ViewObject hvo = am.getMnjMfgPrecostingHView1();
              try {
                  unitName= hvo.getCurrentRow().getAttribute("ProductionUnit").toString();
              } catch (Exception e) {
                  ;
              }
          String date=null;
          try {
              date= hvo.getCurrentRow().getAttribute("CreationDate").toString();
          } catch (Exception e) {
              ;
          }
           //end of if condition
        
        System.out.println(" creation date is ----------------------->"+date);
          double srno = 0;
          String stmt = "BEGIN :1 :=mnj_get_Factory_avg_eff(:2,:3); end;";
          java.sql.CallableStatement cs =am.getDBTransaction().createCallableStatement(stmt, 1);
          try {
              cs.registerOutParameter(1, OracleTypes.NUMBER);
              cs.setString(2, unitName);
              cs.setString(3,date);
              cs.execute();
              srno = cs.getDouble(1);
              cs.close();
          } catch (Exception e) {
              e.printStackTrace();
          }
          return srno;
    }
    
    public double SMV() {
        String unitName = null;
              ViewObject hvo = am.getMnjMfgPrecostingHView1();
              try {
                  unitName= hvo.getCurrentRow().getAttribute("ProductionUnit").toString();
              } catch (Exception e) {
                  ;
              }
           //end of if condition
           String date=null;
           try {
               date= hvo.getCurrentRow().getAttribute("CreationDate").toString();
           } catch (Exception e) {
               ;
           }
            //end of if condition
           
           System.out.println(" creation date is ----------------------->"+date);
          
          
          double srno = 0;
          String stmt = "BEGIN :1 := mnj_get_preccost_smv(:2,:3); end;";
          java.sql.CallableStatement cs =am.getDBTransaction().createCallableStatement(stmt, 1);
          try {
              cs.registerOutParameter(1, OracleTypes.NUMBER);
              cs.setString(2, unitName);
              cs.setString(3,date);
              cs.execute();
              srno = cs.getDouble(1);
              cs.close();
          } catch (Exception e) {
              e.printStackTrace();
          }
          return srno;
    }

    public String setBuyer() {
        // Add event code here...
        
        ViewObject saleorder=am.getCreateSaleOrderLinesVO1();
        ViewObject Header=am.getMnjMfgPrecostingHView1();
        String custId=null;
        try{
            custId=Header.getCurrentRow().getAttribute("BuyerId").toString();
        }
        catch(Exception e){
            ;
        }
        System.out.println("the set buyer for saleorder upper block is= "+custId);
       saleorder.getCurrentRow().setAttribute("BuyerId", custId);
        // sizeLineVO.executeQuery();
        
        
        return null;
    }

    public String setBuyerForBPO() {
        ViewObject saleorder=am.getMnjPrecostCreateBpoDVO1();
        ViewObject Header=am.getMnjMfgPrecostingHView1();
        String custId=null;
        try{
            custId=Header.getCurrentRow().getAttribute("BuyerId").toString();
        }
        catch(Exception e){
            ;
        }
        System.out.println("the set buyer for saleorder downblock block is= "+custId);
        saleorder.getCurrentRow().setAttribute("BuyerId", custId);
        // sizeLineVO.executeQuery();
        
        
        
        // Add event code here...
        return null;
    }

    private void setBuyerId() {
        
        
        ViewObject saleorder=am.getCustMnjOntSoObinSizline_LineVO1();
        saleorder.executeQuery();
        ViewObject Header=am.getMnjMfgPrecostingHView1();
        String custId=null;
        System.out.println("the set buyer for saleorder Sizebrkdwn  block is= "+custId);
        try{
            custId=Header.getCurrentRow().getAttribute("BuyerId").toString();
        }
        catch(Exception e){
            ;
        }
        System.out.println("the set buyer for saleorder Sizebrkdwn  block is= "+custId);
        saleorder.getCurrentRow().setAttribute("CustId",custId);
        // sizeLineVO.executeQuery();
    }


    public String SaveManagementCal() {
        
            OperationBinding ob = executeOperation("Commit");
            ob.execute();
            
            ViewObject linenumber=am.getMnjMfgPrecostingLView1();
            try{
                linenumber.getCurrentRow().getAttribute("LineId").toString();
                System.out.println("..........befor");
                setFinanceCalc(1);
                System.out.println("..........after");
            }
            catch(Exception e) {
                ;
            }
            AdfFacesContext.getCurrentInstance().addPartialTarget(subCostingTable);
          // Code For Style Tracking
            OperationBinding operationBinding = executeOperation("StyleTracking");                
            operationBinding.execute();
            // End Code For Style Tracking
            OperationBinding ob1 = executeOperation("Commit");
            ob1.execute();
        return null;
    }

    public void createBom(ActionEvent actionEvent) {
        // Add event code here...
       checkBuyer();
       //actionBomCreation();
      
        
        AdfFacesContext.getCurrentInstance().addPartialTarget(createBpoTab);
        
        
        
        
    }

    private String populateHeader() {
        //ViewObject linepopulate=am.getMnjMfgPrecostingLView1();
        ViewObject pocHeader=am.getMnjMfgPrecostingHView1();
        ViewObject BomHeader=am.getCustMnjOntBomHeaderView1();
        String ordertype="";
        Row Headerrow = BomHeader.createRow();
        Headerrow.setNewRowState(Row.STATUS_INITIALIZED);
        
        try{
            Headerrow.setAttribute("BuyerId",pocHeader.getCurrentRow().getAttribute("BuyerId").toString());
            
            int buyerId=Integer.parseInt(pocHeader.getCurrentRow().getAttribute("BuyerId").toString());
             if(buyerId==1046){

               ordertype="Order Based";
                
            }
            else{
                ordertype="Style Based";
            }
        }
        catch(Exception e){
            ;
        }
        

        
        try{
            Headerrow.setAttribute("BomDate",getDate()); 
        }
        catch(Exception e){
            ;
        }
        try{
            Headerrow.setAttribute("VersionDate",getDate()); 
        }
        catch(Exception e){
            ;
        }
        try{
            Headerrow.setAttribute("EffectiveStartDate",getDate()); 
        }
        catch(Exception e){
            ;
        }
      
        try{
            Headerrow.setAttribute("CreatedBy",pocHeader.getCurrentRow().getAttribute("CreatedBy").toString()); 
        }
        catch(Exception e){
            ;
        }
        try{
            int versionNumber=1;
            Headerrow.setAttribute("VersionNumber",versionNumber);
        }
        catch(Exception e){
            ;
        }
        
        try{
            Headerrow.setAttribute("SeasonC",pocHeader.getCurrentRow().getAttribute("Season").toString()); 
        }
        catch(Exception e){
            ;
        }
        
        try{
            Headerrow.setAttribute("StyleNoC",pocHeader.getCurrentRow().getAttribute("StyleNo").toString());
        }
        catch(Exception e){
            ;
        }
        
        try{
            Headerrow.setAttribute("CurrentStyleNo",pocHeader.getCurrentRow().getAttribute("CurrentStyleNo").toString());
        }
        catch(Exception e){
            ;
        }
        
        try{
            Headerrow.setAttribute("StyleNameNew", pocHeader.getCurrentRow().getAttribute("StyleNameNew").toString());
        }
        catch(Exception e){
            ;
        }
        try{
            Headerrow.setAttribute("StyleNameC", pocHeader.getCurrentRow().getAttribute("StyleNameNew").toString());
        }
        catch(Exception e){
            ;
        }
        
        try{
            Headerrow.setAttribute("CurrentStyleName", pocHeader.getCurrentRow().getAttribute("CurrentStyleName").toString());
        }
        catch(Exception e){
            ;
        }
        
        
        
        try{
            Headerrow.setAttribute("OrderType",ordertype);
        }
        catch(Exception e){
            ;
        }
        
        try{
            Headerrow.setAttribute("Division",pocHeader.getCurrentRow().getAttribute("Division").toString()); 
        }
        catch(Exception e){
            ;
        }
        
        try{
            Headerrow.setAttribute("OrgId",getOrg()); 
           
        }
        catch(Exception e){
            ;
        }
        
//        try{
//            Headerrow.setAttribute("Buyer",pocHeader.getCurrentRow().getAttribute("Buyer").toString());
//        }
//        catch(Exception e){
//            ;
//        }
//        
 //
       
       
       
      
      
      
     
       
      
     
        
       // AdfFacesContext.getCurrentInstance().addPartialTarget( fabricFinancePrice);
            
        
        
      BomHeader.insertRow(Headerrow);
//        OperationBinding ob = executeOperation("Commit");
//        ob.execute();
      am.getDBTransaction().commit();
       String bomNum=  Headerrow.getAttribute("BomNumber").toString();
        return bomNum;
    }

    private void populateBpoLineAll() {
        
        populateBPOLines1();
        callBPOFetch();
       
       // openBOMpage();
        
        
        
        
        
    }

    public void setCreateBpoTab(RichPanelCollection createBpoTab) {
        this.createBpoTab = createBpoTab;
    }

    public RichPanelCollection getCreateBpoTab() {
        return createBpoTab;
    }

    private void validationCheck() {
        
        
        

               ViewObject pocHeader=am.getMnjMfgPrecostingHView1();
                System.out.println("validation check");
            //   String orderType = object.toString();
           //    System.out.println(orderType);
               String orderType="",styleName="",styleNo="",season="",buyer="";
      
                  
                 // orderType = am.getCustMnjOntBomHeaderView1().getCurrentRow().getAttribute("BomId").toString();
                   
                 try{
                   styleName = pocHeader.getCurrentRow().getAttribute("StyleNameNew").toString();
                 }catch(Exception e){
                      styleName="";
                  }
                try{
                   styleNo = pocHeader.getCurrentRow().getAttribute("StyleNo").toString();
                 }catch(Exception e){
                      styleNo="";
                  }
                try{
                   season = pocHeader.getCurrentRow().getAttribute("Season").toString();
                 }catch(Exception e){
                      season="";
                  }
                try{
                   buyer = pocHeader.getCurrentRow().getAttribute("BuyerId").toString();
                    int buyerId=Integer.parseInt(buyer);
                     if(buyerId==1046){
                         orderType="Order Based";
                     
                         
                     }
                     else{
                         orderType="Style Based";
                     }
                    
                 }catch(Exception e){
                      buyer="";
                  }
                   
                   String query="select count(*) as isExist from CUST_MNJ_ONT_BOM where STYLE_NO_C =? and STYLE_NAME_NEW=? and SEASON_C=? and BUYER_ID=? and ORDER_TYPE like 'Style%Based%'";
                   String msg="Duplicate Style Based Entry with same Buyer, Style and Season found !!!";
                   ResultSet rs; 
                    try {
                        PreparedStatement createStatement= am.getDBTransaction().createPreparedStatement(query,0);
                        
                        createStatement.setString(1, styleNo);
                        createStatement.setString(2, styleName);
                        createStatement.setString(3, season);
                        createStatement.setString(4, buyer);
                       
                        rs=createStatement.executeQuery();
                        boolean check=false;
                        String result=null;
                        //rs = appM.getDBTransaction().createStatement(0).executeQuery(query);
                        if (rs.next()) {
                             result = rs.getObject(1).toString();
                            System.out.println("result = "+result);
                        }               
                        if(result.equals("0")){
                          check = true;
                           CheckBPOExist();
                          
                            
                       }
                       else{
                          // throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR,msg,null));
                          FacesMessage fm = new FacesMessage(msg);
                          fm.setSeverity(FacesMessage.SEVERITY_INFO);
                          FacesContext context = FacesContext.getCurrentInstance();
                          context.addMessage(null, fm);
                       }
                            
                        rs.close();
                } catch (SQLException e) {
                        ;
                    
                }  
             
        
        
    }

    private void actionBomCreation() {
        
        String bomNumber= populateHeader();
         populateBpoLineAll();
//         OperationBinding ob = executeOperation("Commit");
//         ob.execute();
         am.getDBTransaction().commit();
        openBOMpage(bomNumber);
//         String message = "The created BOM Number is "+bomNumber;
//        //        if (methodReturnValue != null) {
//        //            message = methodReturnValue.toString();
//        //        } else {
//        //            message = "Failed! .";
//        //        }
//         FacesMessage fm = new FacesMessage(message);
//         fm.setSeverity(FacesMessage.SEVERITY_INFO);
//         FacesContext context = FacesContext.getCurrentInstance();
//         context.addMessage(null, fm);
        
        
    }
    
    public Date getDate(){
        
        Date date = new Date();  
        
        date.getCurrentDate();
        //   System.out.println(date.getCurrentDate());
           
       // castToJBODate(adate);
           return (Date)date.getCurrentDate();
      
    }  
    
   


    public String getOrg() {
        
       
        String msg="NO ORG found!!!";
        ViewObject pocHeader=am.getMnjMfgPrecostingHView1();
        String string=null;
        String result=null;
        try{
            
            string=pocHeader.getCurrentRow().getAttribute("ProductionUnit").toString();
            System.out.println("..........unit= "+string);
           
            if(string.equalsIgnoreCase("CGL Unit1")){
                result="341";
            }
            else if(string.equalsIgnoreCase("GWL Unit1")){
                result="344";
            }
            else if(string.equalsIgnoreCase("GFL Unit1")){
                result="342";
            }
            else if(string.equalsIgnoreCase("CAL Unit1")){
                result="340";
            }
            
            
            
        }
        catch(Exception e){
            FacesMessage fm = new FacesMessage(msg);
            fm.setSeverity(FacesMessage.SEVERITY_INFO);
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, fm);
        }
        return result;
    }
    
    public void populateBPOLines1() {

       // System.out.println("Season_c = ");
       ViewObject pocHeader=am.getMnjMfgPrecostingHView1();
        System.out.println("validation check");
       //   String orderType = object.toString();
       //    System.out.println(orderType);
       String styleName="",styleNo="",season="",buyer="";
       
          
         // orderType = am.getCustMnjOntBomHeaderView1().getCurrentRow().getAttribute("BomId").toString();
           
         try{
           styleName = pocHeader.getCurrentRow().getAttribute("StyleNameNew").toString();
         }catch(Exception e){
              styleName="";
          }
        try{
           styleNo = pocHeader.getCurrentRow().getAttribute("StyleNo").toString();
         }catch(Exception e){
              styleNo="";
          }
        try{
           season = pocHeader.getCurrentRow().getAttribute("Season").toString();
         }catch(Exception e){
              season="";
          }
        try{
           buyer = pocHeader.getCurrentRow().getAttribute("BuyerId").toString();
           
            
         }catch(Exception e){
              buyer="";
          }
   
        
      //  System.out.println("Season = " +"Nvl('"+ Season+"',Season) "+" Or style_name = "+"Nvl('"+ StyleName+"',style_name) "+" Or style_no = "+"Nvl('"+ StyleNo+"',style_no)");
      ViewObject FillBPOVO=am.getFillBPOVo1();
        FillBPOVO.setWhereClause("Season = " +"Nvl('"+ season+"',Season) "+"and  Buyer_Id = " +"Nvl('"+ buyer+"',Buyer_Id) "+" And (style_name = "+"Nvl('"+ styleName+"',style_name) "+" Or style_no = "+"Nvl('"+ styleNo+"',style_no))");
        System.out.println("Season = " + season+" and  Buyer_Id = " + buyer+" And style_name = "+ styleName+" Or style_no = "+ styleNo);
        FillBPOVO.executeQuery();
        //System.out.println("--- END --");
    
    }
    
    public void callBPOFetch() {
        //System.out.println("--- START --");
        ViewObject populatevo = am.getFillBPOVo1();
    
         if (populatevo.getRowCount() == 0);
        RowSetIterator it = populatevo.createRowSetIterator("cc");
        Row r[] = populatevo.getAllRowsInRange();
        //System.out.println("callBPOFetch");
    for (Row row : r) {
            //System.out.println("loop count");
            try {
               
               
              
                
                    
                   populateBPOLines(row);
               
                

            } catch (Exception e)
            {
              ;
            }
        }

        it.closeRowSetIterator();
    } 
    
    public void populateBPOLines(Row poprow) {
       // System.out.println("Populate Lines 1 -->");
    
       
        Row linerow = createBPOLines();
          
                
                linerow.setAttribute("BpoNo",getPopulateValue(poprow, "BpoNo"));
                //System.out.println("BpoNo----"+getPopulateValue(poprow, "BpoNo"));
                //System.out.println("Category----"+getPopulateValue(poprow, "Category"));
               // linerow.setAttribute("Category",getPopulateValue(poprow, "Category")); //problem to populate in viewobject
                System.out.println("OrderQty----1");
              //  System.out.println("OrderQty----"+getPopulateValue(poprow, "OrderedQty"));
                System.out.println("OrderQty----2");
                linerow.setAttribute("OrderQty",getPopulateValue(poprow, "OrderedQty"));
               // System.out.println("OrderQty----"+getPopulateValue(poprow, "OrderedQty"));
                linerow.setAttribute("EndUserDesc",getPopulateValue(poprow, "EndUser"));
               // System.out.println("EndUserDesc----"+getPopulateValue(poprow, "EndUser"));
                linerow.setAttribute("Fit",getPopulateValue(poprow, "Fit"));
                //System.out.println("Fit----"+getPopulateValue(poprow, "Fit"));
                linerow.setAttribute("UserItemDesc",getPopulateValue(poprow,"ItemDescription"));
                //System.out.println("UserItemDesc----"+getPopulateValue(poprow,"ItemDescription"));
                linerow.setAttribute("Brand",getPopulateValue(poprow,"Brand"));
               // System.out.println("Brand----"+getPopulateValue(poprow,"Brand"));
                linerow.setAttribute("SaleOrderId",getPopulateValue(poprow,"HeaderId"));
                //System.out.println("SaleOrderId----"+getPopulateValue(poprow,"HeaderId"));
                linerow.setAttribute("Season",getPopulateValue(poprow,"Season"));
               // System.out.println("Season----"+getPopulateValue(poprow,"Season"));
                
          // populateBPOLines1();
            } 
    
    
    public String getPopulateValue(Row r, String columnName) {

        String value = null;
        try {
            value = r.getAttribute(columnName).toString();
        } catch (Exception e) {
            ;
        }
        System.out.println("return Value----"+value);
        return value;
        
    }
    public Row createBPOLines() {
        ViewObject vo = am.getMnjBomBpoLinesAllView1();
        Row row = vo.createRow();
        vo.insertRow(row);
        row.setNewRowState(Row.STATUS_INITIALIZED);
        return row;
    } //
    public void CheckBPOExist() {
        populateBPOLines1();
        ViewObject BPOExist=am.getFillBPOVo1();
        int count = BPOExist.getRowCount();
        if(count==0){
            String msg="BPO/TBA not Uploaded yet or Already Exist!!";
            FacesMessage fm = new FacesMessage(msg);
            fm.setSeverity(FacesMessage.SEVERITY_INFO);
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, fm);
        }
        else{
            actionBomCreation();
        }
    }
 
    //end of populateOrderLines method

   public void checkBuyer() {
       ViewObject pocHeader=am.getMnjMfgPrecostingHView1();
       String buyer="";
        try{
           buyer = pocHeader.getCurrentRow().getAttribute("BuyerId").toString();
             int buyerId=Integer.parseInt(buyer);
              if(buyerId==1046){
                 String msg="Please go to the BOM form to create.";
                 FacesMessage fm = new FacesMessage(msg);
                 fm.setSeverity(FacesMessage.SEVERITY_INFO);
                 FacesContext context = FacesContext.getCurrentInstance();
                 context.addMessage(null, fm);
                 
             }
             else{
                 validationCheck();
                 //CheckBPOExist();
             }
            
         }catch(Exception e){
              String msg="Buyer field is Blank!!!!";
              FacesMessage fm = new FacesMessage(msg);
              fm.setSeverity(FacesMessage.SEVERITY_INFO);
              FacesContext context = FacesContext.getCurrentInstance();
              context.addMessage(null, fm);
          }
        
    }

 
        
        public String openBOMpage(String bomNum) {
          
           
                    String newPage =
                     "http://192.168.200.115:7003/megabom2-ViewController-context-root/faces/Edit";
                 
            urlink(newPage);
        
            

            return null;
        }


    public void goToBom(ActionEvent actionEvent) {
        // Add event code here...
        String newPage =
         "http://192.168.200.115:7003/megabom2-ViewController-context-root/faces/Query";
        
        urlink(newPage);
    }
    
    public void urlink(String link){
        String newPage =link;
         
        // String newPage = "http://localhost:7101/PurchaseMemo-ViewController-context-root/faces/SearchPG?headerId="+getBomId().getValue();
        FacesContext ctx = FacesContext.getCurrentInstance();
        ExtendedRenderKitService erks = Service.getService(ctx.getRenderKit(), ExtendedRenderKitService.class);
        String url = "window.open('" + newPage + "','_blank','toolbar=no,location=no,menubar=no,alwaysRaised=yes,height=500,width=1100');";
        erks.addScript(FacesContext.getCurrentInstance(), url);
    }

    private void trimStyleNoAndName() {
        String styleName=null,styleNo=null;
        ViewObject headerVo=am.getMnjMfgPrecostingHView1();
        try{
          styleNo=headerVo.getCurrentRow().getAttribute("StyleNo").toString().trim();
            headerVo.getCurrentRow().setAttribute("StyleNo",styleNo);
        }
        catch(Exception e){
            ;
        }
        
        try{
          styleName=headerVo.getCurrentRow().getAttribute("StyleNameNew").toString().trim();
            headerVo.getCurrentRow().setAttribute("StyleNameNew",styleName);
        }
        catch(Exception e){
            ;
        }
        
       
       
        
        
    }

    private void doTrimsBPOs() {
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

   
    public void setLineBPO(RichInputText lineBPO) {
        this.lineBPO = lineBPO;
    }

    public RichInputText getLineBPO() {
        return lineBPO;
    }

    private void doTrimsBPOsDet() {
        
        ViewObject saleOrderLineDet =am.getMnjPrecostCreateBpoDVO1();
        String bpo="",detBpo="";
        
        
        System.out.println("Method called ======================>");
                RowSetIterator it = saleOrderLineDet.createRowSetIterator("tt");
                Row currentRow = saleOrderLineDet.getCurrentRow();
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
                        
                        
                    

                   

                   
                    
                }
                it.closeRowSetIterator();
                System.out.println("total return from method " + count);
              
        
    }

    private void addStyleTrackinAndBasis() {
        System.out.println("===============================x==========================");
        ViewObject header=am.getMnjMfgPrecostingHView1();
        ViewObject styleTracker=am.gettrackingVO1();
        String Tracker=null,division=null,buyer=null;
        
        try{
            buyer=header.getCurrentRow().getAttribute("Buyer").toString();
            System.out.println("=============================buyer="+buyer);
        }catch(Exception e){
            ;
        }
        styleTracker.setWhereClause("BUYER_NAME = '"+ buyer+"'");
        styleTracker.executeQuery();
        
        Row[] r = styleTracker.getAllRowsInRange();
        System.out.println("============================length="+r.length);
        for (Row row : r) {
                try{
                    Tracker=row.getAttribute("StyleTracking").toString();
                    System.out.println("=============================tracker="+Tracker);
                }catch(Exception e){
                    ;
                }
                try{
                    division=row.getAttribute("Division").toString();
                    System.out.println("=============================division="+division);
                }catch(Exception e){
                    ;
                }
            
            }
        
        
       
                

                
                
        
       
        header.getCurrentRow().setAttribute("StyleTracking",Tracker);
        header.getCurrentRow().setAttribute("Division",division);
        
        
    }

    private void checkSaleId() {
        ViewObject line=am.getCreateSaleOrderLinesVO1();
        ViewObject linedetails=am.getMnjPrecostCreateBpoDVO1();
                 Row[] r = line.getAllRowsInRange();
                String value=null;
                
        System.out.println("=============================line length="+r.length);
        for (Row row : r) {
            String statusLine=null;
            try{
               statusLine=row.getAttribute("BpoStatus").toString();
            }catch(Exception e){
                System.out.println("=============not entered & return to null=================");
                row.setAttribute("SaleLineId",value);
                row.setAttribute("SaleHeaderId",value);
                
            }
//            if(statusLine.equalsIgnoreCase("ENTERED")){
//                System.out.println("=============ENTERED=================");
//            }else{
//                System.out.println("=============not entered & return to null=================");
//                row.setAttribute("SaleLineId",value);
//                row.setAttribute("SaleHeaderId",value);
//                
//            }
           
            RowSet child = (RowSet)row.getAttribute("MnjPrecostCreateBpoDVO");
            Row[] rr = child.getAllRowsInRange();
            System.out.println("=============================detail length="+rr.length);
            for (Row dRow : rr){
                String statusDetail=null;
                
                try{
                   statusDetail=dRow.getAttribute("BpoStatus").toString();
                }catch(Exception e){
                    System.out.println("=============not entered & return to null=================");
                    dRow.setAttribute("SaleLineId",value);
                    dRow.setAttribute("SaleHeaderId",value);
                }
//                if(statusDetail.equalsIgnoreCase("ENTERED")){
//                    System.out.println("=============ENTERED=================");
//                }else{
//                    System.out.println("=============not entered & return to null=================");
//                    dRow.setAttribute("SaleLineId",value);
//                    dRow.setAttribute("SaleHeaderId",value);
//                    
//                }
                
                
            }
            
        }
        
        
        
    }

    private void checkItemIdAndUpdate() {
        
        ViewObject line=am.getCreateSaleOrderLinesVO1();
                 Row[] r = line.getAllRowsInRange();
               
                
        System.out.println("=============================line length="+r.length);
        for (Row row : r) {
        String itemId=null,headerId=null,lineId=null;
            
            try{
               
                itemId=row.getAttribute("ItemId").toString();
                System.out.println("==============================Item_id"+itemId);
            }catch(Exception e){
                System.out.println("=============enter for itemId setup=================");
                headerId=row.getAttribute("HeaderId").toString();
                      lineId=row.getAttribute("LineId").toString();
                ViewObject itemVO=am.getitemCodeVO1();
                System.out.println("==============================headerId"+headerId);
                System.out.println("==============================lineId"+lineId);
                itemVO.setWhereClause("POC_HEADER_ID = '"+ headerId +"' AND POC_LINE_ID= '"+ lineId +"' ");
                itemVO.executeQuery();
                int count = itemVO.getRowCount();
                System.out.println("==============================count "+count);
                if(count==1){
                    Row[] rr = itemVO.getAllRowsInRange();
                    String  itemValue=null;
                    for (Row ro : rr) {
                        try{
                            itemValue=ro.getAttribute("InventoryItemId").toString();
                        }catch(Exception ee){
                            
                            
                            String msg="Item Code not found!!";
                            FacesMessage fm = new FacesMessage(msg);
                            fm.setSeverity(FacesMessage.SEVERITY_INFO);
                            FacesContext context = FacesContext.getCurrentInstance();
                            context.addMessage(null, fm);
                            
                        }    
                             
                        
                        }
                System.out.println("==============================Item_id found"+itemValue);
                 row.setAttribute("ItemId",itemValue);
                }
                else{
                    String msg="Item Code problem!!";
                    FacesMessage fm = new FacesMessage(msg);
                    fm.setSeverity(FacesMessage.SEVERITY_INFO);
                    FacesContext context = FacesContext.getCurrentInstance();
                    context.addMessage(null, fm);
                }
                
                
            }
        
        
    }
 }

    public void setTrimsFinance(RichInputText trimsFinance) {
        this.trimsFinance = trimsFinance;
    }

    public RichInputText getTrimsFinance() {
        return trimsFinance;
    }

    public void setWashFinanceValue(RichInputText washFinanceValue) {
        this.washFinanceValue = washFinanceValue;
    }

    public RichInputText getWashFinanceValue() {
        return washFinanceValue;
    }

    public void addBreakdownQtyLine(ActionEvent actionEvent) {
        // Add event code here...
        ViewObject qtyVo=am.getMnjMfgPrecostingQtyDView1();
        int rowNumber=qtyVo.getRowCount();
        Row row  = qtyVo.createRow();
        qtyVo.insertRow(row);
        row.setNewRowState(Row.STATUS_INITIALIZED);
        String classfic="TBA-"+(rowNumber+1);
        row.setAttribute("Classification",classfic);    
        
      //  AdfFacesContext.getCurrentInstance().addPartialTarget(breakdownQtyTable);
        
    }

    public void setBreakdownQtyTable(RichTable breakdownQtyTable) {
        this.breakdownQtyTable = breakdownQtyTable;
    }

    public RichTable getBreakdownQtyTable() {
        return breakdownQtyTable;
    }

    public void saveTBABrkDwn(ActionEvent actionEvent) {
        // Add event code here...               
        if(doCheckAndSave()==true){
            am.getDBTransaction().commit();
        }else{
            String msg="Qty in TBA-Qty-Braekdwn is greater than balance Qty, Plz Adjust Qty.";
            FacesMessage fm = new FacesMessage(msg);
            fm.setSeverity(FacesMessage.SEVERITY_INFO);
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null,fm);
        }
        
        
       
    }

    public void dialogListenerForTBAbrkdwn(DialogEvent dialogEvent) {
        // Add event code here...
        if (dialogEvent.getOutcome().name().equals("ok")) {

            
            if(doCheckAndSave()==true){
                am.getDBTransaction().commit();
            }else{
                String msg="Qty in TBA-Qty-Braekdwn is greater than balance Qty, Plz Adjust Qty.";
                FacesMessage fm = new FacesMessage(msg);
                fm.setSeverity(FacesMessage.SEVERITY_INFO);
                FacesContext context = FacesContext.getCurrentInstance();
                context.addMessage(null,fm);
            }
        } else if (dialogEvent.getOutcome().name().equals("cancel")) {
            BindingContainer bindings = getBindings();

        }
    }
    
    public boolean doCheckAndSave(){
        ViewObject TBABalnceVO=am.getTBABalanceQtyVO1();
        ViewObject TbaBrkTableVO=am.getMnjMfgPrecostingQtyDView1();
        Row[] r =  TbaBrkTableVO.getAllRowsInRange();
        int value=0;
        int totalValue=0;
        int checkValue=0;
        try{
           //checkValue= Integer.parseInt(TBABalnceVO.getCurrentRow().getAttribute("BreakdownQty").toString());
            checkValue= Integer.parseInt( balanceQtyForTBA.getValue().toString());
           
        }catch(Exception e){
            ;
        }
        System.out.println("=============================TBA brkdown length="+r.length);
        System.out.println("=============================TBA checkValue="+checkValue);
        for (Row row : r) {
            try{
               value= Integer.parseInt(row.getAttribute("BreakdownQty").toString());
            }catch(Exception e){
                ;
            }
           totalValue=totalValue+value;
        }
        System.out.println("=============================TBA totalValue="+totalValue);
        if(totalValue>checkValue){
          return false;
        }else{
            return true;
        }
    }


    public void setBalanceQtyForTBA(RichInputText balanceQtyForTBA) {
        this.balanceQtyForTBA = balanceQtyForTBA;
    }

    public RichInputText getBalanceQtyForTBA() {
        return balanceQtyForTBA;
    }
    
    public boolean getSizeQtyCheck(){
        
       ViewObject sizeDetailsVO= am.getCustMnjOntSoObinsline_DetailVO1();
       ViewObject lineVO=am.getCustMnjOntSoObinSizline_LineVO1();
        Row[] row=sizeDetailsVO.getAllRowsInRange();
        int qty=0,totalQty=0,lineQty=0;
        
        try{
           lineQty=Integer.parseInt(lineVO.getCurrentRow().getAttribute("InseamQty").toString()) ;
        }catch(Exception e){
            ;
        }
        
        for (Row r : row) {
            try{
                qty=Integer.parseInt(r.getAttribute("SizeQty").toString()) ;
            }catch(Exception e){
                ;
            }
                totalQty=totalQty+qty;
            }
        
        System.out.println("sizeDetailsQty====================>"+totalQty);
        
        if(totalQty>lineQty){
            return false;
        }else{
            return true;
        }
        
       
    }
    private void setfabricDescription() {
        System.out.println("febric enter");
        OperationBinding ob = executeOperation("Commit");
        ob.execute();
        ViewObject FabricVo=am.getMnjMfgPrecostingItemDView1();
        FabricVo.executeQuery();
        ViewObject linevo=am.getMnjMfgPrecostingLView1();
        try{
            String feb2=null;
            String feb3=null;
            String feb4=null;
           // RowSet child = (RowSet)linevo.getCurrentRow().getAttribute("FebricView");
            Row[] r = FabricVo.getAllRowsInRange();
            System.out.println("febric clid row---------------->"+r.length);
            
            StringBuilder setVal2 = null;
            setVal2 = new StringBuilder();
            StringBuilder setVal3 = null;
            setVal3 = new StringBuilder();
            StringBuilder setVal4 = null;
            setVal4 = new StringBuilder();
            int count =0,check=0;
    //            for (Row row : r){
    //                oracle.jbo.domain.Number prefix = (Number)row.getAttribute("ItemPrefix");
    //                if ((prefix.intValue() >= 11 && prefix.intValue() <= 12)){
    //                    count++;
    //                }
    //            }
            
            for (Row row : r) {
                
                oracle.jbo.domain.Number prefix = (Number)row.getAttribute("ItemPrefix"); 
                if ((prefix.intValue() >= 11 && prefix.intValue() <= 12)){
                    check++;
                    if(check==1){
                        try{
                          feb2=row.getAttribute("RefCode").toString();
                        }
                        catch(Exception e){
                            feb2=null;
                        }
                    }
                   
                    if(check==1){
                        try{
                          feb3=row.getAttribute("ItemDesc").toString();
                        }
                        catch(Exception e){
                            feb3=null;
                        }   
                    }
                   
                    
                   // setVal2.append(String.format(feb2)); 
                   // setVal3.append(String.format(feb3)); 
    //                    if(count!=check) {
    //                        setVal2.append(String.format("/"));
    //                   // setVal3.append(String.format("/"));
    //                }
              
                
                }
               
            }
            setVal2.append(String.format(feb2));
            setVal4.append(setVal2.toString());
            setVal4.append(String.format(" & "));
            setVal4.append(feb3);
              
            
            System.out.println("......................feb cont dec string= "+setVal2.toString());
            System.out.println("......................feb cont dec string= "+setVal4.toString());
            linevo.getCurrentRow().setAttribute("FabricDesc",setVal2.toString());
            linevo.getCurrentRow().setAttribute("FabricContent",setVal4.toString());
          //  AdfFacesContext.getCurrentInstance().addPartialTarget(fabDescrip);
          AdfFacesContext.getCurrentInstance().addPartialTarget(fabricC);
            AdfFacesContext.getCurrentInstance().addPartialTarget(fabricD);
            
        }
        catch(Exception e){
            ;
        }
        
        
        
        // String TBA=String.valueOf(TB);
        
        
        
        
        
    }

    public void setFabricD(RichInputText fabricD) {
        this.fabricD = fabricD;
    }

    public RichInputText getFabricD() {
        return fabricD;
    }

    public void setFabricC(RichInputText fabricC) {
        this.fabricC = fabricC;
    }

    public RichInputText getFabricC() {
        return fabricC;
    }
}//end of class