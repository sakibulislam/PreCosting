package view.backing.bean;

import javax.faces.event.ActionEvent;


import mnj.mfg.model.services.AppModuleImpl;

import oracle.adf.model.BindingContext;

import oracle.adf.model.binding.DCBindingContainer;
import oracle.adf.model.binding.DCDataControl;
import oracle.adf.view.rich.component.rich.data.RichTable;

import oracle.adf.view.rich.context.AdfFacesContext;

import oracle.binding.BindingContainer;
import oracle.binding.OperationBinding;

import oracle.jbo.Row;
import oracle.jbo.ViewObject;

public class DbObjectsCall {
    private RichTable queryTable;

    public DbObjectsCall() {
    }
    
    public AppModuleImpl getAppModuleImpl() {
        DCBindingContainer bindingContainer =
            (DCBindingContainer)BindingContext.getCurrent().getCurrentBindingsEntry();
        //BindingContext bindingContext = BindingContext.getCurrent();
        DCDataControl dc =
            bindingContainer.findDataControl("AppModuleDataControl"); // Name of application module in datacontrolBinding.cpx
        AppModuleImpl appM = (AppModuleImpl)dc.getDataProvider();
        return appM;
    }
    AppModuleImpl am =  getAppModuleImpl();

    public void CallCopy(ActionEvent actionEvent) {
        // Add event code here...
        System.out.println("Copy Precost AM 1---->>");
        OperationBinding ob = executeOperation("CopyPrecost");
        ob.getParamsMap().put("type","C");                   
        ob.execute();
        AdfFacesContext.getCurrentInstance().addPartialTarget(queryTable);
        System.out.println("Copy Precost AM 2---->>");
        removeCopiedRowInLine();
    }

    public void CallVersion(ActionEvent actionEvent) {
        // Add event code here...
        OperationBinding ob = executeOperation("CopyPrecost");
        ob.getParamsMap().put("type", "V");                   
        ob.execute();
        
        AdfFacesContext.getCurrentInstance().addPartialTarget(queryTable);
      
                       
    }
    
    /*****Generic Method to Get BindingContainer**/
        public BindingContainer getBindingsCont() {
            return BindingContext.getCurrent().getCurrentBindingsEntry();
        }

        /**
         * Generic Method to execute operation
         * */
        public OperationBinding executeOperation(String operation) {
            OperationBinding createParam = getBindingsCont().getOperationBinding(operation);
            return createParam;

        }

    public void setQueryTable(RichTable queryTable) {
        this.queryTable = queryTable;
    }

    public RichTable getQueryTable() {
        return queryTable;
    }

    public void CallAllCopy(ActionEvent actionEvent) {
        // Add event code here...
        OperationBinding ob = executeOperation("CopyAllPrecost");
        ob.getParamsMap().put("type", "A");                   
        ob.execute();
        
        AdfFacesContext.getCurrentInstance().addPartialTarget(queryTable);
        
                       

    }

    private void removeCopiedRowInLine() {
      
            
            ViewObject vo = am.getCreateSaleOrderLinesVO1();
            
            
        Row[] r = vo.getAllRowsInRange();
        
        for (Row row : r) {
            
            if (
                row.getAttribute("CloneFlag") != null &&
                row.getAttribute("CloneFlag").equals("Y")
               ) {
              //  vo.removeCurrentRow();
               row.remove();
                
                vo.executeQuery();
                System.out.println("...........................dlt........................");
            }
        }                      
        
    }
}
