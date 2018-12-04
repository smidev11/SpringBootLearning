package com.tech.report;

import java.io.File;
import java.io.FileOutputStream;
import java.math.BigInteger;
import java.util.List;

import org.apache.poi.xwpf.model.XWPFHeaderFooterPolicy;
import org.apache.poi.xwpf.usermodel.ParagraphAlignment;
import org.apache.poi.xwpf.usermodel.TableRowAlign;
import org.apache.poi.xwpf.usermodel.UnderlinePatterns;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.apache.poi.xwpf.usermodel.XWPFTableCell;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;
import org.apache.xmlbeans.impl.xb.xmlschema.SpaceAttribute.Space;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTFldChar;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTJc;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTP;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTPPr;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTR;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSectPr;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTString;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTblWidth;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTcPr;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTText;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTVMerge;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STFldCharType;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STJc;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STMerge;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STTblWidth;

import com.tech.entity.Degreasing;
import com.tech.entity.GenRef;
import com.tech.entity.Job;
import com.tech.pojo.ProductAndApplication;
import com.tech.pojo.SurfacePrepration;



public class CreateReport {
	
	
	public void createReport(Job job,String fileName){
		try{
		
			XWPFDocument document = new XWPFDocument();
			FileOutputStream out = new FileOutputStream(new File(fileName));
			
			
			XWPFParagraph para = document.createParagraph();
	        para.setAlignment(ParagraphAlignment.CENTER);
	        para.createRun();
	        XWPFRun r = para.createRun();
		    r.setBold(true);
		    r.setFontSize(14);
		  
		    r.setUnderline(UnderlinePatterns.THICK);
		    r.setText("Coating Logs");
	      
	      
	       //Create genref table
		    GenRef genRef = job.getGenref();
		    if(genRef != null){
		    	createTableGenRef(document,genRef);
		    }
		  
		    //createTableApplicationAndProduct(document);
		    //Tabe for Surface Preparation
		    List<Degreasing> deg = job.getDegreasing();
		    String methodName = "";
		    if(deg.size() > 0){
		    	 	String method = deg.get(0).getMethoddegreasing();
				    
				    if(method == "1"){
				    	methodName = "Acetone/MEK";
				    }else if(method == "2"){
				    	methodName = "WAP(Degreasing Chemical)";
				    }else if(method == "3"){
				    	methodName = "Thermal Degreasing";
				    }else if(method == "4"){
				    	methodName = "Alkaline Bath";
				    }else{
				    	 methodName = method;
				    }
			}
		   
		    try{
		    	createTableSurfacePreparation(document,methodName);	
		    }catch(Exception e){
		    	e.printStackTrace();
		    }
	        
	        
	        
	        CTSectPr sectPr = document.getDocument().getBody().addNewSectPr();
	  	  XWPFHeaderFooterPolicy policy = new XWPFHeaderFooterPolicy(document, sectPr);
	        
	  	  // create header
	  		CTP ctpHeader = CTP.Factory.newInstance();
	  		CTR ctrHeader = ctpHeader.addNewR();
	  		CTText ctHeader = ctrHeader.addNewT();
	  		String headerText = "This is header";
	  		ctHeader.setStringValue(headerText);	
	  		XWPFParagraph headerParagraph = new XWPFParagraph(ctpHeader, document);
	  		XWPFParagraph[] parsHeader = new XWPFParagraph[1];
	  		parsHeader[0] = headerParagraph;
	  		policy.createHeader(XWPFHeaderFooterPolicy.DEFAULT, parsHeader);
	        
	        
	     	  // create footer
	     	  			//XWPFHeaderFooterPolicy policy = doc.getHeaderFooterPolicy();
  			CTP ctpFooter = CTP.Factory.newInstance();
  			XWPFParagraph[] parsFooter;
  			// add style (s.th.)
  			CTPPr ctppr = ctpFooter.addNewPPr();
  			CTString pst = ctppr.addNewPStyle();
  			pst.setVal("style21");
  			CTJc ctjc = ctppr.addNewJc();
  			ctjc.setVal(STJc.LEFT);
  			ctppr.addNewRPr();
  	
  			// Add in word "Page "   
  			CTR ctr = ctpFooter.addNewR();
  			CTText t = ctr.addNewT();
  			t.setStringValue("Page");
  			t.setSpace(Space.PRESERVE);
  			
  			// add everything from the footerXXX.xml you need
  			
  			ctr = ctpFooter.addNewR();
  			ctr.addNewRPr();
  			CTFldChar fch = ctr.addNewFldChar();
  			fch.setFldCharType(STFldCharType.BEGIN);
	     	  			
     	  	ctr = ctpFooter.addNewR();
  			ctr.addNewInstrText().setStringValue(" PAGE ");
  	
  			ctpFooter.addNewR().addNewFldChar().setFldCharType(STFldCharType.SEPARATE);
  	
  			ctpFooter.addNewR().addNewT().setStringValue("1");
  	
  			ctpFooter.addNewR().addNewFldChar().setFldCharType(STFldCharType.END);
  	
  			XWPFParagraph footerParagraph = new XWPFParagraph(ctpFooter, document);
  	
  			parsFooter = new XWPFParagraph[1];
  	
  			parsFooter[0] = footerParagraph;
  	
  			policy.createFooter(XWPFHeaderFooterPolicy.DEFAULT, parsFooter);
	     	  			
	     	  
	  	
	        document.write(out);
	        out.close();
	        System.out.println("create_table.docx written successully");
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	
   public static void main(String[] args)throws Exception {

      //Blank Document
      XWPFDocument document = new XWPFDocument();
        
      //Write the Document in file system
      FileOutputStream out = new FileOutputStream(new File("D:\\del\\pdf\\create_table_31.docx"));
     
      
      //Create Coating log 
      XWPFParagraph para = document.createParagraph();
      para.setAlignment(ParagraphAlignment.CENTER);
      para.createRun();
      XWPFRun r = para.createRun();
	  r.setBold(true);
	  r.setFontSize(14);
	  
	  r.setUnderline(UnderlinePatterns.THICK);
	  r.setText("Coating Logs");
      
      
      //Create genref table
      //createTableGenRef(document);
      //createTableApplicationAndProduct(document);
      //createTableSurfacePreparation(document);
      
      
      CTSectPr sectPr = document.getDocument().getBody().addNewSectPr();
	  XWPFHeaderFooterPolicy policy = new XWPFHeaderFooterPolicy(document, sectPr);
      
	  // create header
		CTP ctpHeader = CTP.Factory.newInstance();
		CTR ctrHeader = ctpHeader.addNewR();
		CTText ctHeader = ctrHeader.addNewT();
		String headerText = "This is header";
		ctHeader.setStringValue(headerText);	
		XWPFParagraph headerParagraph = new XWPFParagraph(ctpHeader, document);
		XWPFParagraph[] parsHeader = new XWPFParagraph[1];
		parsHeader[0] = headerParagraph;
		policy.createHeader(XWPFHeaderFooterPolicy.DEFAULT, parsHeader);
      
      
   	  // create footer
   	  			//XWPFHeaderFooterPolicy policy = doc.getHeaderFooterPolicy();
   	  			CTP ctpFooter = CTP.Factory.newInstance();
   	  			XWPFParagraph[] parsFooter;
   	  			// add style (s.th.)
   	  			CTPPr ctppr = ctpFooter.addNewPPr();
   	  			CTString pst = ctppr.addNewPStyle();
   	  			pst.setVal("style21");
   	  			CTJc ctjc = ctppr.addNewJc();
   	  			ctjc.setVal(STJc.LEFT);
   	  			ctppr.addNewRPr();
   	  	
   	  			// Add in word "Page "   
   	  			CTR ctr = ctpFooter.addNewR();
   	  			CTText t = ctr.addNewT();
   	  			t.setStringValue("Page");
   	  			t.setSpace(Space.PRESERVE);
   	  			
   	  			// add everything from the footerXXX.xml you need
   	  			
   	  			ctr = ctpFooter.addNewR();
   	  			ctr.addNewRPr();
   	  			CTFldChar fch = ctr.addNewFldChar();
   	  			fch.setFldCharType(STFldCharType.BEGIN);
   	  			
   	  		ctr = ctpFooter.addNewR();
			ctr.addNewInstrText().setStringValue(" PAGE ");
	
			ctpFooter.addNewR().addNewFldChar().setFldCharType(STFldCharType.SEPARATE);
	
			ctpFooter.addNewR().addNewT().setStringValue("1");
	
			ctpFooter.addNewR().addNewFldChar().setFldCharType(STFldCharType.END);
	
			XWPFParagraph footerParagraph = new XWPFParagraph(ctpFooter, document);
	
			parsFooter = new XWPFParagraph[1];
	
			parsFooter[0] = footerParagraph;
	
			policy.createFooter(XWPFHeaderFooterPolicy.DEFAULT, parsFooter);
   	  			
   	  			
   	  			
   	        
   	        
   	        //create table
      /*XWPFTable table = document.createTable();
		
      //create first row
      XWPFTableRow tableRowOne = table.getRow(0);
      tableRowOne.getCell(0).setText("col one, row one");
      tableRowOne.addNewTableCell().setText("col two, row one");
      tableRowOne.addNewTableCell().setText("col three, row one");
      tableRowOne.addNewTableCell().setText("Extra cell");
		
      //create second row
      XWPFTableRow tableRowTwo = table.createRow();
      tableRowTwo.getCell(0).setText("col one, row two");
      tableRowTwo.getCell(1).setText("col two, row two");
      tableRowTwo.getCell(2).setText("col three, row two");
		
      //create third row
      XWPFTableRow tableRowThree = table.createRow();
      tableRowThree.getCell(0).setText("col one, row three");
      tableRowThree.getCell(1).setText("col two, row three");
      tableRowThree.getCell(2).setText("col three, row three");*/
	
      document.write(out);
      out.close();
      System.out.println("create_table.docx written successully");
   }
   
   
   public static XWPFTable createTableGenRef(XWPFDocument document,GenRef genRef){
	   
	  
	   XWPFTable table = document.createTable();
	   
	  //table.setCellMargins(50, 50, 50, 50);
	   table.setTableAlignment(TableRowAlign.CENTER);
	   
	   /*GenRef genRef = new GenRef();
	   genRef.setClientName("Abc fdfd ");
	   genRef.setAddress("Pune dfdf dfffffffffffff k1 610 pimple gurav new pune test near chowk");
	   genRef.setPono("11");
	   genRef.setCoatingSpec("Eversilk df ");
	   genRef.setCoatingSys("system");
	   genRef.setSerialno("Sr n0");
	   genRef.setHeatno("113");
	   genRef.setHeatlotno("hhh");
	   genRef.setQnt("50");
	   genRef.setJobwo("hhhhhhhhhhhhhh");*/
	   	
	   	//First row
	   
	    XWPFTableRow theaderRow = table.getRow(0);
	    
	    theaderRow.getCell(0).setText("A.	General Reference");
		theaderRow.addNewTableCell().setText("");
		theaderRow.addNewTableCell().setText("");
		theaderRow.addNewTableCell().setText("");
		
	   	
		XWPFTableRow tableRowOne = table.createRow();
	    tableRowOne.getCell(0).setText("Client ");
	    tableRowOne.getCell(1).setText(genRef.getClientName());
	    
	    tableRowOne.getCell(2).setText("Address");
	    tableRowOne.getCell(3).setText(genRef.getAddress());
	    
	    
	    //Second row
	    XWPFTableRow tableRowTwo = table.createRow();
	    tableRowTwo.getCell(0).setText("PO NO");
	    //tableRowTwo.getCell(1).setText(genRef.getPono());
	    tableRowTwo.getCell(2).setText("Coating Specification");
	    tableRowTwo.getCell(3).setText(genRef.getCoatingSpec());
	    
	    //third row
	    XWPFTableRow tableRow3 = table.createRow();
	   
	    tableRow3.getCell(0).setText("Part No");
	    //tableRow3.getCell(1).setText(genRef.getPartno());
	    tableRow3.getCell(2).setText("Coating System");
	    tableRow3.getCell(3).setText(genRef.getCoatingSys());
	    
	    XWPFTableRow tableRow4 = table.createRow();
	    tableRow4.getCell(0).setText("Rev no");
	    //tableRow4.getCell(1).setText(genRef.getRevNo());
	    tableRow4.getCell(2).setText("Serial No");
	    //tableRow4.getCell(3).setText(genRef.getSerialno());
	    
	    
	    // 4 row
	    XWPFTableRow tableRow5 = table.createRow();
	    tableRow5.getCell(0).setText("Heat No.");
	   // tableRow5.getCell(1).setText(genRef.getHeatno());
	    tableRow5.getCell(2).setText("Heat Lot No.");
	    //tableRow5.getCell(3).setText(genRef.getHeatlotno());
	  
	    //5 row
	    XWPFTableRow tableRow6 = table.createRow();
	    tableRow6.getCell(0).setText("Description");
	    //tableRow6.getCell(1).setText(genRef.getDesc());
	    tableRow6.getCell(2).setText("Quantity");
	  //  tableRow6.getCell(3).setText(genRef.getQnt());
	    
	    XWPFTableRow tableRow7 = table.createRow();
	    tableRow7.getCell(0).setText("Job WO");
	    //tableRow7.getCell(1).setText(genRef.getJobwo());
	    tableRow7.getCell(2).setText("Date");
	    tableRow7.getCell(3).setText(genRef.getDate());
	    
	    XWPFTableRow tableRow8 = table.createRow();
	    tableRow8.getCell(0).setText("Masking Requirements");
	    tableRow8.getCell(1).setText(genRef.getMaskingReq());
	    tableRow8.getCell(2).setText("NACE Level II Certification");
	    tableRow8.getCell(3).setText(genRef.getNacelevelcert());
	    
	    XWPFTableRow tableRow9 = table.createRow();
	    tableRow9.getCell(0).setText("Incoming Inspector");
	    tableRow9.getCell(1).setText(genRef.getMaskingReq());
	    tableRow9.getCell(2).setText("Signature");
	    tableRow9.getCell(3).setText("");
	    
	    //Set size of columns
    	int[] cols_size = {4000, 4000, 4000,4000};
        
	       for(int i = 0; i < table.getNumberOfRows(); i++){
	          XWPFTableRow row = table.getRow(i);
	          int numCells = row.getTableCells().size();
	          for(int j = 0; j < numCells; j++){
	              XWPFTableCell cell1 = row.getCell(j);
	              cell1.getCTTc().addNewTcPr().addNewTcW().setW(BigInteger.valueOf(cols_size[j]));
	          }
	       }
	       //Set size of columns end
	    
	     table.getCTTbl().addNewTblGrid().addNewGridCol().setW(BigInteger.valueOf(20*1440));
	     //other columns (2 in this case) also each 1 inches width
	     for (int col = 1 ; col < 4; col++) {
	      table.getCTTbl().getTblGrid().addNewGridCol().setW(BigInteger.valueOf(20*1440));
	     }

	     //create and set column widths for all columns in all rows
	     //most examples don't set the type of the CTTblWidth but this
	     //is necessary for working in all office versions
	     for (int col = 0; col < 4; col++) {
	      CTTblWidth tblWidth = CTTblWidth.Factory.newInstance();
	      tblWidth.setW(BigInteger.valueOf(1*1440));
	      tblWidth.setType(STTblWidth.DXA);
	      for (int row = 0; row < 5; row++) {
	       CTTcPr tcPr = table.getRow(row).getCell(col).getCTTc().getTcPr();
	       if (tcPr != null) {
	        tcPr.setTcW(tblWidth);
	       } else {
	        tcPr = CTTcPr.Factory.newInstance();
	        tcPr.setTcW(tblWidth);
	        table.getRow(row).getCell(col).getCTTc().setTcPr(tcPr);
	       }
	      }
	     }

	     mergeCellHorizontally(table,0,0,3);
	     
	     table.getRow(0).getCell(0).getCTTc().addNewTcPr().addNewShd().setFill("cccccc");
	    		
	    		
	    /*cell.getCTTc().addNewTcPr().addNewShd().setFill("cccccc");
	    cell.setText("gggg");*/
	    
	    return table;
   	
   }
   
   
   	public static XWPFTable createTableSurfacePreparation(XWPFDocument document,String method){
	  SurfacePrepration sfp = new SurfacePrepration();
	  sfp.setCond_of_surface_bf_blasting("Machined");
	  sfp.setMethod_cleaning_bf_blasting(method);
	  sfp.setMethod_of_blasting("Manual");
	  sfp.setAbrasive_Grade("Aluminim Oxide");
	  sfp.setAbrasive_type("F-70");
	  sfp.setDegree_cleanliness_af_blasting("In compliance With sscp sp1 & sp5");
	  sfp.setAnchor_profile("11 ");
	  sfp.setMethod_anchor_rofile_nspection("In Compliance witg sspc ss 5");
	 
	   

	   
	   XWPFTable table = document.createTable();
	   
	   //table.setCellMargins(50, 50, 50, 50);
	   table.setTableAlignment(TableRowAlign.CENTER);
	   
	   
	   	//First row
	   
	    XWPFTableRow theaderRow = table.getRow(0);
	    
	    theaderRow.getCell(0).setText("C.Surface Preparation");
		theaderRow.addNewTableCell().setText("");
		//theaderRow.addNewTableCell().setText("");
		//theaderRow.addNewTableCell().setText("");
		
	   	
		XWPFTableRow tableRowOne = table.createRow();
	    tableRowOne.getCell(0).setText("Condition of Surface Before Blasting");
	    tableRowOne.getCell(1).setText(sfp.getCond_of_surface_bf_blasting());
	   
	    
	    
	    XWPFTableRow row2 = table.createRow();
	    row2.getCell(0).setText("Method of Cleaning Before Blasting");
	    row2.getCell(1).setText(sfp.getMethod_cleaning_bf_blasting());
	   
	    
	    
	    XWPFTableRow row3 = table.createRow();
	    row3.getCell(0).setText("Method of Blasting");
	    row3.getCell(1).setText(sfp.getMethod_of_blasting());
	    
	    XWPFTableRow row4 = table.createRow();
	    row4.getCell(0).setText("Abrasive Grade");
	    row4.getCell(1).setText(sfp.getAbrasive_Grade());
	    
	    XWPFTableRow row5 = table.createRow();
	    row5.getCell(0).setText("Degree of Cleanliness after Blasting");
	    row5.getCell(1).setText(sfp.getDegree_cleanliness_af_blasting());
	    
	    XWPFTableRow row6 = table.createRow();
	    row6.getCell(0).setText("Anchor Profile");
	    row6.getCell(1).setText(sfp.getAnchor_profile());
	   
	    XWPFTableRow row7 = table.createRow();
	    row7.getCell(0).setText("Method of Anchor Profile Inspection");
	    row7.getCell(1).setText(sfp.getMethod_anchor_rofile_nspection());
	   
	    
	    
	    //Set size of columns
    	int[] cols_size = {8000, 8000};
        
	       for(int i = 0; i < table.getNumberOfRows(); i++){
	          XWPFTableRow row = table.getRow(i);
	          int numCells = row.getTableCells().size();
	          for(int j = 0; j < numCells; j++){
	              XWPFTableCell cell1 = row.getCell(j);
	              cell1.getCTTc().addNewTcPr().addNewTcW().setW(BigInteger.valueOf(cols_size[j]));
	          }
	       }
	       //Set size of columns end
	    
	     table.getCTTbl().addNewTblGrid().addNewGridCol().setW(BigInteger.valueOf(20*1440));
	     //other columns (2 in this case) also each 1 inches width
	     for (int col = 1 ; col < 2; col++) {
	      table.getCTTbl().getTblGrid().addNewGridCol().setW(BigInteger.valueOf(20*1440));
	     }

	     //create and set column widths for all columns in all rows
	     //most examples don't set the type of the CTTblWidth but this
	     //is necessary for working in all office versions
	     for (int col = 0; col < 2; col++) {
	      CTTblWidth tblWidth = CTTblWidth.Factory.newInstance();
	      tblWidth.setW(BigInteger.valueOf(1*1440));
	      tblWidth.setType(STTblWidth.DXA);
	      for (int row = 0; row < 3; row++) {
	       CTTcPr tcPr = table.getRow(row).getCell(col).getCTTc().getTcPr();
	       if (tcPr != null) {
	        tcPr.setTcW(tblWidth);
	       } else {
	        tcPr = CTTcPr.Factory.newInstance();
	        tcPr.setTcW(tblWidth);
	        table.getRow(row).getCell(col).getCTTc().setTcPr(tcPr);
	       }
	      }
	     }

	     mergeCellHorizontally(table,0,0,1);
	     
	     table.getRow(0).getCell(0).getCTTc().addNewTcPr().addNewShd().setFill("cccccc");
	     
	    return table;
   }
   
   	public static XWPFTable createTableApplicationAndProduct(XWPFDocument document){
	   ProductAndApplication pna  = new ProductAndApplication();
	   pna.setBaseCoat_Applicator("NA");
	   pna.setIntermediateCoat_Applicator("NA");
	   pna.setTopCoat_Applicator("NA");
	   
	   pna.setBaseCoat_BatchNo("B-19199");
	   pna.setIntermediateCoat_BatchNo("In-1881");
	   pna.setTopCoat_BatchNo("T-1010");
	   
	   

	   
	   XWPFTable table = document.createTable();
	   
	  // table.setCellMargins(50, 50, 50, 50);
	   table.setTableAlignment(TableRowAlign.CENTER);
	   
	   
	   	//First row
	   
	    XWPFTableRow theaderRow = table.getRow(0);
	    
	    theaderRow.getCell(0).setText("B.	References – Products and Applicators");
		theaderRow.addNewTableCell().setText("");
		theaderRow.addNewTableCell().setText("");
		theaderRow.addNewTableCell().setText("");
		
	   	
		XWPFTableRow tableRowOne = table.createRow();
	    tableRowOne.getCell(0).setText("Coating");
	    tableRowOne.getCell(1).setText("Base Coat");
	    tableRowOne.getCell(2).setText("Intermediate Coat");
	    tableRowOne.getCell(3).setText("Top Coat");
	    
	    
	    XWPFTableRow row2 = table.createRow();
	    row2.getCell(0).setText("Batch No./Lot No.");
	    row2.getCell(1).setText(pna.getBaseCoat_BatchNo());
	    row2.getCell(2).setText(pna.getIntermediateCoat_BatchNo());
	    row2.getCell(3).setText(pna.getTopCoat_BatchNo());
	    
	    
	    XWPFTableRow row3 = table.createRow();
	    row3.getCell(0).setText("Processor/Applicator");
	    row3.getCell(1).setText(pna.getBaseCoat_Applicator());
	    row3.getCell(2).setText(pna.getIntermediateCoat_Applicator());
	    row3.getCell(3).setText(pna.getTopCoat_Applicator());
	    
	    
	    //Set size of columns
    	int[] cols_size = {4000, 4000, 4000,4000};
        
	       for(int i = 0; i < table.getNumberOfRows(); i++){
	          XWPFTableRow row = table.getRow(i);
	          int numCells = row.getTableCells().size();
	          for(int j = 0; j < numCells; j++){
	              XWPFTableCell cell1 = row.getCell(j);
	              cell1.getCTTc().addNewTcPr().addNewTcW().setW(BigInteger.valueOf(cols_size[j]));
	          }
	       }
	       //Set size of columns end
	    
	     table.getCTTbl().addNewTblGrid().addNewGridCol().setW(BigInteger.valueOf(20*1440));
	     //other columns (2 in this case) also each 1 inches width
	     for (int col = 1 ; col < 4; col++) {
	      table.getCTTbl().getTblGrid().addNewGridCol().setW(BigInteger.valueOf(20*1440));
	     }

	     //create and set column widths for all columns in all rows
	     //most examples don't set the type of the CTTblWidth but this
	     //is necessary for working in all office versions
	     for (int col = 0; col < 4; col++) {
	      CTTblWidth tblWidth = CTTblWidth.Factory.newInstance();
	      tblWidth.setW(BigInteger.valueOf(1*1440));
	      tblWidth.setType(STTblWidth.DXA);
	      for (int row = 0; row < 3; row++) {
	       CTTcPr tcPr = table.getRow(row).getCell(col).getCTTc().getTcPr();
	       if (tcPr != null) {
	        tcPr.setTcW(tblWidth);
	       } else {
	        tcPr = CTTcPr.Factory.newInstance();
	        tcPr.setTcW(tblWidth);
	        table.getRow(row).getCell(col).getCTTc().setTcPr(tcPr);
	       }
	      }
	     }

	     mergeCellHorizontally(table,0,0,3);
	     
	     table.getRow(0).getCell(0).getCTTc().addNewTcPr().addNewShd().setFill("cccccc");
	     
	    return table;
   }
   static void mergeCellVertically(XWPFTable table, int col, int fromRow, int toRow) {
	   for(int rowIndex = fromRow; rowIndex <= toRow; rowIndex++) {
	    XWPFTableCell cell = table.getRow(rowIndex).getCell(col);
	    CTVMerge vmerge = CTVMerge.Factory.newInstance();
	    if(rowIndex == fromRow){
	     // The first merged cell is set with RESTART merge value
	     vmerge.setVal(STMerge.RESTART);
	    } else {
	     // Cells which join (merge) the first one, are set with CONTINUE
	     vmerge.setVal(STMerge.CONTINUE);
	     // and the content should be removed
	     for (int i = cell.getParagraphs().size(); i > 0; i--) {
	      cell.removeParagraph(0);
	     }
	     cell.addParagraph();
	    }
	    // Try getting the TcPr. Not simply setting an new one every time.
	    CTTcPr tcPr = cell.getCTTc().getTcPr();
	    if (tcPr == null) tcPr = cell.getCTTc().addNewTcPr();
	    tcPr.setVMerge(vmerge);
	   }
	  }

	  //merging horizontally by setting grid span instead of using CTHMerge
	  static void mergeCellHorizontally(XWPFTable table, int row, int fromCol, int toCol) {
	   XWPFTableCell cell = table.getRow(row).getCell(fromCol);
	   
	   // Try getting the TcPr. Not simply setting an new one every time.
	   CTTcPr tcPr = cell.getCTTc().getTcPr();
	   if (tcPr == null) cell.getCTTc().addNewTcPr();
	   // The first merged cell has grid span property set
	   if (tcPr.isSetGridSpan()) {
	    tcPr.getGridSpan().setVal(BigInteger.valueOf(toCol-fromCol+1));
	   } else {
	    tcPr.addNewGridSpan().setVal(BigInteger.valueOf(toCol-fromCol+1));
	   }
	   // Cells which join (merge) the first one, must be removed
	   for(int colIndex = toCol; colIndex > fromCol; colIndex--) {
	    table.getRow(row).getCtRow().removeTc(colIndex);
	   }
	  }
   
}




