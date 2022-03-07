import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

public class ObjToCSV {

    private static final String CSV_SEPARATOR = ";";

    
    List<Float> L0_gewinn = new ArrayList<Float>();
    List<Float> L1_gewinn = new ArrayList<Float>();
    List<Float> L2_gewinn = new ArrayList<Float>();
    List<Float> L3_gewinn = new ArrayList<Float>();
    List<Float> L4_gewinn = new ArrayList<Float>();
    List<Float> L5_gewinn = new ArrayList<Float>();
    List<Float> L6_gewinn = new ArrayList<Float>();
    List<Float> L7_gewinn = new ArrayList<Float>();
    List<Float> L8_gewinn = new ArrayList<Float>();
    List<Float> L9_gewinn = new ArrayList<Float>();
    List<Float> L10_gewinn = new ArrayList<Float>();
    List<Float> L11_gewinn = new ArrayList<Float>();
    List<Float> L12_gewinn = new ArrayList<Float>();
    List<Float> L13_gewinn = new ArrayList<Float>();
    List<Float> L14_gewinn = new ArrayList<Float>();
    
    List<Integer> L0_doneTrades = new ArrayList<Integer>();
    List<Integer> L1_doneTrades = new ArrayList<Integer>();
    List<Integer> L2_doneTrades = new ArrayList<Integer>();
    List<Integer> L3_doneTrades = new ArrayList<Integer>();
    List<Integer> L4_doneTrades = new ArrayList<Integer>();
    List<Integer> L5_doneTrades = new ArrayList<Integer>();
    List<Integer> L6_doneTrades = new ArrayList<Integer>();
    List<Integer> L7_doneTrades = new ArrayList<Integer>();
    List<Integer> L8_doneTrades = new ArrayList<Integer>();
    List<Integer> L9_doneTrades = new ArrayList<Integer>();
    List<Integer> L10_doneTrades = new ArrayList<Integer>();
    List<Integer> L11_doneTrades = new ArrayList<Integer>();
    List<Integer> L12_doneTrades = new ArrayList<Integer>();
    List<Integer> L13_doneTrades = new ArrayList<Integer>();
    List<Integer> L14_doneTrades = new ArrayList<Integer>();

    List<Float> dca1 = new ArrayList<Float>();
    List<Float> gain = new ArrayList<Float>();
    List<Float> emaFaktor = new ArrayList<Float>();
    List<Float> emaFaktorLimit = new ArrayList<Float>();
    List<Float> layerGewinn = new ArrayList<Float>();
    List<Float> smma500 = new ArrayList<Float>();
    
    List<Integer> maxPairs = new ArrayList<Integer>();
    List<Integer> sollRsiBuy = new ArrayList<Integer>();
    List<Integer> sollRsidownlimitBuy = new ArrayList<Integer>();
    List<Integer> avrHolding = new ArrayList<Integer>();

    List<Integer> istPairs = new ArrayList<Integer>();
    
    public void addDataToCSV(float gewinn, float gainSell, float emaFaktor1, float emaFaktorLimit1, int maxPairs1, int sollRsiBuy1,
    		int sollRsidownlimitBuy1, int avrHolding1, int istPairs1, float layerGewinn1, float smma500_1, float dca1_1,
    		int L0_doneTrades1, String layerLevel) {
    	
    	L0_gewinn.add(gewinn);
    	gain.add(gainSell);
    	emaFaktor.add(emaFaktor1);
    	emaFaktorLimit.add(emaFaktorLimit1);
    	maxPairs.add(maxPairs1);
    	sollRsiBuy.add(sollRsiBuy1);
    	sollRsidownlimitBuy.add(sollRsidownlimitBuy1);
    	avrHolding.add(avrHolding1);
    	istPairs.add(istPairs1);
    	layerGewinn.add(layerGewinn1);
    	smma500.add(smma500_1);
    	dca1.add(dca1_1);
    	L0_doneTrades.add(L0_doneTrades1);
		
//        L1_gewinn.add((float) 0);
//        L2_gewinn.add((float) 0);
//        L3_gewinn.add((float) 0);
//        L4_gewinn.add((float) 0);
//        L5_gewinn.add((float) 0);
//        L6_gewinn.add((float) 0);
//        L7_gewinn.add((float) 0);
//        L8_gewinn.add((float) 0);
//        L9_gewinn.add((float) 0);
//        L10_gewinn.add((float) 0);
//        L11_gewinn.add((float) 0);
//        L12_gewinn.add((float) 0);
//        L13_gewinn.add((float) 0);
//        L14_gewinn.add((float) 0);
//      
//        L1_doneTrades.add(0);
//        L2_doneTrades.add(0);
//        L3_doneTrades.add(0);
//        L4_doneTrades.add(0);
//        L5_doneTrades.add(0);
//        L6_doneTrades.add(0);
//        L7_doneTrades.add(0);
//        L8_doneTrades.add(0);
//        L9_doneTrades.add(0);
//        L10_doneTrades.add(0);
//        L11_doneTrades.add(0);
//        L12_doneTrades.add(0);
//        L13_doneTrades.add(0);
//        L14_doneTrades.add(0);
        
//		if (layerLevel == "Layer1") { 
//			L1_gewinn.add(gewinn);
//	        L2_gewinn.add((float) 0);
//	        L3_gewinn.add((float) 0);
//	        L4_gewinn.add((float) 0);
//	        L5_gewinn.add((float) 0);
//	        L6_gewinn.add((float) 0);
//	        L7_gewinn.add((float) 0);
//	        L8_gewinn.add((float) 0);
//	        L9_gewinn.add((float) 0);
//	        L10_gewinn.add((float) 0);
//	        L11_gewinn.add((float) 0);
//	        L12_gewinn.add((float) 0);
//	        L13_gewinn.add((float) 0);
//	        L14_gewinn.add((float) 0);
//		}
//		if (layerLevel == "Layer2") L2_gewinn.add(gewinn);
//		if (layerLevel == "Layer3") L3_gewinn.add(gewinn);
//		if (layerLevel == "Layer4") L4_gewinn.add(gewinn);
//		if (layerLevel == "Layer5") L5_gewinn.add(gewinn);
//		if (layerLevel == "Layer6") L6_gewinn.add(gewinn);
//		if (layerLevel == "Layer7") L7_gewinn.add(gewinn);
//		if (layerLevel == "Layer8") L8_gewinn.add(gewinn);
//		if (layerLevel == "Layer9") L9_gewinn.add(gewinn);
//		if (layerLevel == "Layer10") L10_gewinn.add(gewinn);
//		if (layerLevel == "Layer11") L11_gewinn.add(gewinn);
//		if (layerLevel == "Layer12") L12_gewinn.add(gewinn);
//		if (layerLevel == "Layer13") L13_gewinn.add(gewinn);
//		if (layerLevel == "Layer14") L14_gewinn.add(gewinn);
//		
//		if (layerLevel == "Layer1") L1_doneTrades.add(L0_doneTrades1);
//		if (layerLevel == "Layer2") L2_doneTrades.add(L0_doneTrades1);
//		if (layerLevel == "Layer3") L3_doneTrades.add(L0_doneTrades1);
//		if (layerLevel == "Layer4") L4_doneTrades.add(L0_doneTrades1);
//		if (layerLevel == "Layer5") L5_doneTrades.add(L0_doneTrades1);
//		if (layerLevel == "Layer6") L6_doneTrades.add(L0_doneTrades1);
//		if (layerLevel == "Layer7") L7_doneTrades.add(L0_doneTrades1);
//		if (layerLevel == "Layer8") L8_doneTrades.add(L0_doneTrades1);
//		if (layerLevel == "Layer9") L9_doneTrades.add(L0_doneTrades1);
//		if (layerLevel == "Layer10") L10_doneTrades.add(L0_doneTrades1);
//		if (layerLevel == "Layer11") L11_doneTrades.add(L0_doneTrades1);
//		if (layerLevel == "Layer12") L12_doneTrades.add(L0_doneTrades1);
//		if (layerLevel == "Layer13") L13_doneTrades.add(L0_doneTrades1);
//		if (layerLevel == "Layer14") L14_doneTrades.add(L0_doneTrades1);
   	
    	
    	}
    
    public void writeAllLayersQueuesToCSV(ObjToCSV core1_ObjCSV, ObjToCSV core2_ObjCSV, ObjToCSV core3_ObjCSV, ObjToCSV core4_ObjCSV, 
    		ObjToCSV core5_ObjCSV, ObjToCSV core6_ObjCSV, ObjToCSV core7_ObjCSV, ObjToCSV core8_ObjCSV, ObjToCSV core9_ObjCSV,
    		ObjToCSV core10_ObjCSV, ObjToCSV core11_ObjCSV, ObjToCSV core12_ObjCSV, String output) {
        try
        {
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(output), "UTF-8"));
            StringBuffer oneLine = new StringBuffer();
          
            oneLine.append("MaxPairs"); 			//a
            oneLine.append(CSV_SEPARATOR);
            oneLine.append("SollRsiBuy"); 			//b
            oneLine.append(CSV_SEPARATOR);
            oneLine.append("sollRsidownlimitBuy"); 	//c
            oneLine.append(CSV_SEPARATOR);
            oneLine.append("emaFaktor"); 			//d
            oneLine.append(CSV_SEPARATOR);
            oneLine.append("emaFaktorLimit"); 		//e
            oneLine.append(CSV_SEPARATOR);
            oneLine.append("gainSell"); 			//f
            oneLine.append(CSV_SEPARATOR);
            oneLine.append("AvrHolding Min"); 		//g
            oneLine.append(CSV_SEPARATOR);
            oneLine.append("IstPairs"); 			//h
            oneLine.append(CSV_SEPARATOR);
            oneLine.append("LayerGewinn"); 			//i
            oneLine.append(CSV_SEPARATOR);
            oneLine.append("smma500");	 			//j
            oneLine.append(CSV_SEPARATOR);
            oneLine.append("dca");	 				//k
            oneLine.append(CSV_SEPARATOR);
            oneLine.append("L0_doneTrades");	 	//l
            oneLine.append(CSV_SEPARATOR);
            oneLine.append("L0_Gewinn"); 			//m
            oneLine.append(CSV_SEPARATOR);
            oneLine.append("L1_doneTrades");	 	//l
            oneLine.append(CSV_SEPARATOR);
            oneLine.append("L1_Gewinn"); 			//m
            oneLine.append(CSV_SEPARATOR);
            oneLine.append("L2_doneTrades");	 	//l
            oneLine.append(CSV_SEPARATOR);
            oneLine.append("L2_Gewinn"); 			//m
            oneLine.append(CSV_SEPARATOR);
            oneLine.append("L3_doneTrades");	 	//l
            oneLine.append(CSV_SEPARATOR);
            oneLine.append("L3_Gewinn"); 			//m
            oneLine.append(CSV_SEPARATOR);
            oneLine.append("L4_doneTrades");	 	//l
            oneLine.append(CSV_SEPARATOR);
            oneLine.append("L4_Gewinn"); 			//m
            oneLine.append(CSV_SEPARATOR);
            oneLine.append("L5_doneTrades");	 	//l
            oneLine.append(CSV_SEPARATOR);
            oneLine.append("L5_Gewinn"); 			//m
            oneLine.append(CSV_SEPARATOR);
            oneLine.append("L6_doneTrades");	 	//l
            oneLine.append(CSV_SEPARATOR);
            oneLine.append("L6_Gewinn"); 			//m
            oneLine.append(CSV_SEPARATOR);
            oneLine.append("L7_doneTrades");	 	//l
            oneLine.append(CSV_SEPARATOR);
            oneLine.append("L7_Gewinn"); 			//m
            oneLine.append(CSV_SEPARATOR);
            oneLine.append("L8_doneTrades");	 	//l
            oneLine.append(CSV_SEPARATOR);
            oneLine.append("L8_Gewinn"); 			//m
            oneLine.append(CSV_SEPARATOR);
            oneLine.append("L9_doneTrades");	 	//l
            oneLine.append(CSV_SEPARATOR);
            oneLine.append("L9_Gewinn"); 			//m
            oneLine.append(CSV_SEPARATOR);
            oneLine.append("L10_doneTrades");	 	//l
            oneLine.append(CSV_SEPARATOR);
            oneLine.append("L10_Gewinn"); 			//m
            oneLine.append(CSV_SEPARATOR);
            oneLine.append("L11_doneTrades");	 	//l
            oneLine.append(CSV_SEPARATOR);
            oneLine.append("L11_Gewinn"); 			//m
            oneLine.append(CSV_SEPARATOR);
            oneLine.append("L12_doneTrades");	 	//l
            oneLine.append(CSV_SEPARATOR);
            oneLine.append("L12_Gewinn"); 			//m
            oneLine.append(CSV_SEPARATOR);
            oneLine.append("L13_doneTrades");	 	//l
            oneLine.append(CSV_SEPARATOR);
            oneLine.append("L13_Gewinn"); 			//m
            oneLine.append(CSV_SEPARATOR);
            oneLine.append("L14_doneTrades");	 	//l
            oneLine.append(CSV_SEPARATOR);
            oneLine.append("L14_Gewinn"); 			//m
            oneLine.append(CSV_SEPARATOR);
            bw.write(oneLine.toString());
            bw.newLine();
            
            System.out.println("erste Line");
            
//            System.out.println(core1_ObjCSV.L0_doneTrades);
//            System.out.println(core1_ObjCSV.L2_doneTrades);
//            System.out.println(core1_ObjCSV.L6_doneTrades);
//            System.out.println(core1_ObjCSV.L9_doneTrades);
//            System.out.println(core1_ObjCSV.L14_doneTrades);
//            
//            System.out.println(core1_ObjCSV.L0_gewinn);
//            System.out.println(core1_ObjCSV.L2_gewinn);
//            System.out.println(core1_ObjCSV.L6_gewinn);
//            System.out.println(core1_ObjCSV.L9_gewinn);
//            System.out.println(core1_ObjCSV.L14_gewinn);            
            
            writeLoopCSV(oneLine, core1_ObjCSV, bw);
            writeLoopCSV(oneLine, core2_ObjCSV, bw);
            writeLoopCSV(oneLine, core3_ObjCSV, bw);
            writeLoopCSV(oneLine, core4_ObjCSV, bw);
            writeLoopCSV(oneLine, core5_ObjCSV, bw);
            writeLoopCSV(oneLine, core6_ObjCSV, bw);
            writeLoopCSV(oneLine, core7_ObjCSV, bw);
            writeLoopCSV(oneLine, core8_ObjCSV, bw);
            writeLoopCSV(oneLine, core9_ObjCSV, bw);
            writeLoopCSV(oneLine, core10_ObjCSV, bw);
            writeLoopCSV(oneLine, core11_ObjCSV, bw);
            writeLoopCSV(oneLine, core12_ObjCSV, bw);
            
            bw.flush();
            bw.close();
        }
        catch (UnsupportedEncodingException e) {}
        catch (FileNotFoundException e){}
        catch (IOException e){}
    }
 
    public void writeAllLayersToCSV(ObjToCSV core1_ObjCSV, ObjToCSV core2_ObjCSV, ObjToCSV core3_ObjCSV, ObjToCSV core4_ObjCSV, 
    		ObjToCSV core5_ObjCSV, ObjToCSV core6_ObjCSV, ObjToCSV core7_ObjCSV, ObjToCSV core8_ObjCSV, ObjToCSV core9_ObjCSV,
    		ObjToCSV core10_ObjCSV, ObjToCSV core11_ObjCSV, ObjToCSV core12_ObjCSV, String output) {
        try
        {
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(output), "UTF-8"));
            StringBuffer oneLine = new StringBuffer();
          
            oneLine.append("MaxPairs"); 			//a
            oneLine.append(CSV_SEPARATOR);
            oneLine.append("SollRsiBuy"); 			//b
            oneLine.append(CSV_SEPARATOR);
            oneLine.append("sollRsidownlimitBuy"); 	//c
            oneLine.append(CSV_SEPARATOR);
            oneLine.append("emaFaktor"); 			//d
            oneLine.append(CSV_SEPARATOR);
            oneLine.append("emaFaktorLimit"); 		//e
            oneLine.append(CSV_SEPARATOR);
            oneLine.append("gainSell"); 			//f
            oneLine.append(CSV_SEPARATOR);
            oneLine.append("AvrHolding Min"); 		//g
            oneLine.append(CSV_SEPARATOR);
            oneLine.append("IstPairs"); 			//h
            oneLine.append(CSV_SEPARATOR);
            oneLine.append("LayerGewinn"); 			//i
            oneLine.append(CSV_SEPARATOR);
            oneLine.append("smma500");	 			//j
            oneLine.append(CSV_SEPARATOR);
            oneLine.append("dca");	 				//k
            oneLine.append(CSV_SEPARATOR);
            oneLine.append("L0_doneTrades");	 	//l
            oneLine.append(CSV_SEPARATOR);
            oneLine.append("L0_Gewinn"); 			//m
            oneLine.append(CSV_SEPARATOR);
//            oneLine.append("L1_doneTrades");	 	//l
//            oneLine.append(CSV_SEPARATOR);
//            oneLine.append("L1_Gewinn"); 			//m
//            oneLine.append(CSV_SEPARATOR);
//            oneLine.append("L2_doneTrades");	 	//l
//            oneLine.append(CSV_SEPARATOR);
//            oneLine.append("L2_Gewinn"); 			//m
//            oneLine.append(CSV_SEPARATOR);
//            oneLine.append("L3_doneTrades");	 	//l
//            oneLine.append(CSV_SEPARATOR);
//            oneLine.append("L3_Gewinn"); 			//m
//            oneLine.append(CSV_SEPARATOR);
//            oneLine.append("L4_doneTrades");	 	//l
//            oneLine.append(CSV_SEPARATOR);
//            oneLine.append("L4_Gewinn"); 			//m
//            oneLine.append(CSV_SEPARATOR);
//            oneLine.append("L5_doneTrades");	 	//l
//            oneLine.append(CSV_SEPARATOR);
//            oneLine.append("L5_Gewinn"); 			//m
//            oneLine.append(CSV_SEPARATOR);
//            oneLine.append("L6_doneTrades");	 	//l
//            oneLine.append(CSV_SEPARATOR);
//            oneLine.append("L6_Gewinn"); 			//m
//            oneLine.append(CSV_SEPARATOR);
//            oneLine.append("L7_doneTrades");	 	//l
//            oneLine.append(CSV_SEPARATOR);
//            oneLine.append("L7_Gewinn"); 			//m
//            oneLine.append(CSV_SEPARATOR);
//            oneLine.append("L8_doneTrades");	 	//l
//            oneLine.append(CSV_SEPARATOR);
//            oneLine.append("L8_Gewinn"); 			//m
//            oneLine.append(CSV_SEPARATOR);
//            oneLine.append("L9_doneTrades");	 	//l
//            oneLine.append(CSV_SEPARATOR);
//            oneLine.append("L9_Gewinn"); 			//m
//            oneLine.append(CSV_SEPARATOR);
//            oneLine.append("L10_doneTrades");	 	//l
//            oneLine.append(CSV_SEPARATOR);
//            oneLine.append("L10_Gewinn"); 			//m
//            oneLine.append(CSV_SEPARATOR);
//            oneLine.append("L11_doneTrades");	 	//l
//            oneLine.append(CSV_SEPARATOR);
//            oneLine.append("L11_Gewinn"); 			//m
//            oneLine.append(CSV_SEPARATOR);
//            oneLine.append("L12_doneTrades");	 	//l
//            oneLine.append(CSV_SEPARATOR);
//            oneLine.append("L12_Gewinn"); 			//m
//            oneLine.append(CSV_SEPARATOR);
//            oneLine.append("L13_doneTrades");	 	//l
//            oneLine.append(CSV_SEPARATOR);
//            oneLine.append("L13_Gewinn"); 			//m
//            oneLine.append(CSV_SEPARATOR);
//            oneLine.append("L14_doneTrades");	 	//l
//            oneLine.append(CSV_SEPARATOR);
//            oneLine.append("L14_Gewinn"); 			//m
//            oneLine.append(CSV_SEPARATOR);
            bw.write(oneLine.toString());
            bw.newLine();
            
            System.out.println("erste Line");
            
//            System.out.println(core1_ObjCSV.L0_doneTrades);
//            System.out.println(core1_ObjCSV.L2_doneTrades);
//            System.out.println(core1_ObjCSV.L6_doneTrades);
//            System.out.println(core1_ObjCSV.L9_doneTrades);
//            System.out.println(core1_ObjCSV.L14_doneTrades);
//            
//            System.out.println(core1_ObjCSV.L0_gewinn);
//            System.out.println(core1_ObjCSV.L2_gewinn);
//            System.out.println(core1_ObjCSV.L6_gewinn);
//            System.out.println(core1_ObjCSV.L9_gewinn);
//            System.out.println(core1_ObjCSV.L14_gewinn);            
            
            writeLoopCSV(oneLine, core1_ObjCSV, bw);
            writeLoopCSV(oneLine, core2_ObjCSV, bw);
            writeLoopCSV(oneLine, core3_ObjCSV, bw);
            writeLoopCSV(oneLine, core4_ObjCSV, bw);
            writeLoopCSV(oneLine, core5_ObjCSV, bw);
            writeLoopCSV(oneLine, core6_ObjCSV, bw);
            writeLoopCSV(oneLine, core7_ObjCSV, bw);
            writeLoopCSV(oneLine, core8_ObjCSV, bw);
            writeLoopCSV(oneLine, core9_ObjCSV, bw);
            writeLoopCSV(oneLine, core10_ObjCSV, bw);
            writeLoopCSV(oneLine, core11_ObjCSV, bw);
            writeLoopCSV(oneLine, core12_ObjCSV, bw);
            
            bw.flush();
            bw.close();
        }
        catch (UnsupportedEncodingException e) {}
        catch (FileNotFoundException e){}
        catch (IOException e){}
    }
    
    public void writeLoopCSV(StringBuffer oneLine, ObjToCSV newObjToCSV, BufferedWriter bw) throws IOException {
    	System.out.println("L0 size: " + newObjToCSV.L0_gewinn.size());
        for (int i = 0; i < newObjToCSV.L0_gewinn.size(); i++)
        {
        	oneLine = new StringBuffer(); 
            oneLine.append(newObjToCSV.maxPairs.get(i).intValue()); 			//a
            oneLine.append(CSV_SEPARATOR);
            oneLine.append(newObjToCSV.sollRsiBuy.get(i).intValue()); 			//b
            oneLine.append(CSV_SEPARATOR);
            oneLine.append(newObjToCSV.sollRsidownlimitBuy.get(i).intValue()); 	//c
            oneLine.append(CSV_SEPARATOR);
            oneLine.append(newObjToCSV.emaFaktor.get(i).floatValue()); 			//d
            oneLine.append(CSV_SEPARATOR);
            oneLine.append(newObjToCSV.emaFaktorLimit.get(i).floatValue()); 	//e
            oneLine.append(CSV_SEPARATOR);
            oneLine.append(newObjToCSV.gain.get(i).floatValue()); 				//f
            oneLine.append(CSV_SEPARATOR);
            oneLine.append(newObjToCSV.avrHolding.get(i).intValue()); 			//g
            oneLine.append(CSV_SEPARATOR);
            oneLine.append(newObjToCSV.istPairs.get(i).intValue()); 			//h
            oneLine.append(CSV_SEPARATOR);
            oneLine.append(newObjToCSV.layerGewinn.get(i).floatValue()); 		//i
            oneLine.append(CSV_SEPARATOR);
            oneLine.append(newObjToCSV.smma500.get(i).floatValue()); 			//j
            oneLine.append(CSV_SEPARATOR);
            oneLine.append(newObjToCSV.dca1.get(i).floatValue()); 				//k
            oneLine.append(CSV_SEPARATOR);
            oneLine.append(newObjToCSV.L0_doneTrades.get(i).intValue()); 		//l
            oneLine.append(CSV_SEPARATOR);
            oneLine.append(newObjToCSV.L0_gewinn.get(i).floatValue()); 			//m
            oneLine.append(CSV_SEPARATOR);  
//            oneLine.append(newObjToCSV.L1_doneTrades.get(i).intValue()); 		//l
//            oneLine.append(CSV_SEPARATOR);
//            oneLine.append(newObjToCSV.L1_gewinn.get(i).floatValue()); 			//m
//            oneLine.append(CSV_SEPARATOR);  
//            oneLine.append(newObjToCSV.L2_doneTrades.get(i).intValue()); 		//l
//            oneLine.append(CSV_SEPARATOR);
//            oneLine.append(newObjToCSV.L2_gewinn.get(i).floatValue()); 			//m
//            oneLine.append(CSV_SEPARATOR);  
//            oneLine.append(newObjToCSV.L3_doneTrades.get(i).intValue()); 		//l
//            oneLine.append(CSV_SEPARATOR);
//            oneLine.append(newObjToCSV.L3_gewinn.get(i).floatValue()); 			//m
//            oneLine.append(CSV_SEPARATOR);  
//            oneLine.append(newObjToCSV.L4_doneTrades.get(i).intValue()); 		//l
//            oneLine.append(CSV_SEPARATOR);
//            oneLine.append(newObjToCSV.L4_gewinn.get(i).floatValue()); 			//m
//            oneLine.append(CSV_SEPARATOR);  
//            oneLine.append(newObjToCSV.L5_doneTrades.get(i).intValue()); 		//l
//            oneLine.append(CSV_SEPARATOR);
//            oneLine.append(newObjToCSV.L5_gewinn.get(i).floatValue()); 			//m
//            oneLine.append(CSV_SEPARATOR);  
//            oneLine.append(newObjToCSV.L6_doneTrades.get(i).intValue()); 		//l
//            oneLine.append(CSV_SEPARATOR);
//            oneLine.append(newObjToCSV.L6_gewinn.get(i).floatValue()); 			//m
//            oneLine.append(CSV_SEPARATOR);  
//            oneLine.append(newObjToCSV.L7_doneTrades.get(i).intValue()); 		//l
//            oneLine.append(CSV_SEPARATOR);
//            oneLine.append(newObjToCSV.L7_gewinn.get(i).floatValue()); 			//m
//            oneLine.append(CSV_SEPARATOR);  
//            oneLine.append(newObjToCSV.L8_doneTrades.get(i).intValue()); 		//l
//            oneLine.append(CSV_SEPARATOR);
//            oneLine.append(newObjToCSV.L8_gewinn.get(i).floatValue()); 			//m
//            oneLine.append(CSV_SEPARATOR);  
//            oneLine.append(newObjToCSV.L9_doneTrades.get(i).intValue()); 		//l
//            oneLine.append(CSV_SEPARATOR);
//            oneLine.append(newObjToCSV.L9_gewinn.get(i).floatValue()); 			//m
//            oneLine.append(CSV_SEPARATOR);  
//            oneLine.append(newObjToCSV.L10_doneTrades.get(i).intValue()); 		//l
//            oneLine.append(CSV_SEPARATOR);
//            oneLine.append(newObjToCSV.L10_gewinn.get(i).floatValue()); 			//m
//            oneLine.append(CSV_SEPARATOR);  
//            oneLine.append(newObjToCSV.L11_doneTrades.get(i).intValue()); 		//l
//            oneLine.append(CSV_SEPARATOR);
//            oneLine.append(newObjToCSV.L11_gewinn.get(i).floatValue()); 			//m
//            oneLine.append(CSV_SEPARATOR);  
//            oneLine.append(newObjToCSV.L12_doneTrades.get(i).intValue()); 		//l
//            oneLine.append(CSV_SEPARATOR);
//            oneLine.append(newObjToCSV.L12_gewinn.get(i).floatValue()); 			//m
//            oneLine.append(CSV_SEPARATOR);  
//            oneLine.append(newObjToCSV.L13_doneTrades.get(i).intValue()); 		//l
//            oneLine.append(CSV_SEPARATOR);
//            oneLine.append(newObjToCSV.L13_gewinn.get(i).floatValue()); 			//m
//            oneLine.append(CSV_SEPARATOR);  
//            oneLine.append(newObjToCSV.L14_doneTrades.get(i).intValue()); 		//l
//            oneLine.append(CSV_SEPARATOR);
//            oneLine.append(newObjToCSV.L14_gewinn.get(i).floatValue()); 			//m
//            oneLine.append(CSV_SEPARATOR);  
           //  --------------------------------------------------------------------
            bw.write(oneLine.toString());
            bw.newLine();
        }
    }
    
    public void nowWriteCSV(String output) {
    	writeToCSV(this, output);
    }
    
    private static void writeToCSV(ObjToCSV newObj, String output) {
    	
        try
        {
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(output), "UTF-8"));
            StringBuffer oneLine = new StringBuffer();
          
            oneLine.append("Gewinn"); 					//A
            oneLine.append(CSV_SEPARATOR);
            oneLine.append("MaxPairs"); 				//B
            oneLine.append(CSV_SEPARATOR);
            oneLine.append("SollRsiBuy"); 				//C
            oneLine.append(CSV_SEPARATOR);
            oneLine.append("sollRsidownlimitBuy"); 		//D
            oneLine.append(CSV_SEPARATOR);
            oneLine.append("emaFaktor"); 				//E
            oneLine.append(CSV_SEPARATOR);
            oneLine.append("emaFaktorLimit"); 			//F
            oneLine.append(CSV_SEPARATOR);
            oneLine.append("gainSell"); 				//G
            oneLine.append(CSV_SEPARATOR);
            oneLine.append("AvrHolding Min"); 			//H
            oneLine.append(CSV_SEPARATOR);
            oneLine.append("IstPairs"); 				//I
            oneLine.append(CSV_SEPARATOR);
            oneLine.append("LayerGewinn"); 				//J
            oneLine.append(CSV_SEPARATOR);
            oneLine.append("smma500"); 					//K
            oneLine.append(CSV_SEPARATOR);
            oneLine.append("dca1"); 					//L
            oneLine.append(CSV_SEPARATOR);
            oneLine.append("doneTrades"); 				//M
            oneLine.append(CSV_SEPARATOR);
            bw.write(oneLine.toString());
            bw.newLine();
            
            for (int i = 0; i < newObj.L0_gewinn.size(); i++)
            {
            	oneLine = new StringBuffer();
                oneLine.append(newObj.L0_gewinn.get(i).floatValue()); 			//A
                oneLine.append(CSV_SEPARATOR);   
                oneLine.append(newObj.maxPairs.get(i).intValue()); 			//B
                oneLine.append(CSV_SEPARATOR);
                oneLine.append(newObj.sollRsiBuy.get(i).intValue()); 			//C
                oneLine.append(CSV_SEPARATOR);
                oneLine.append(newObj.sollRsidownlimitBuy.get(i).intValue()); 	//D
                oneLine.append(CSV_SEPARATOR);
                oneLine.append(newObj.emaFaktor.get(i).floatValue()); 			//E
                oneLine.append(CSV_SEPARATOR);
                oneLine.append(newObj.emaFaktorLimit.get(i).floatValue()); 	//F
                oneLine.append(CSV_SEPARATOR);
                oneLine.append(newObj.gain.get(i).floatValue()); 				//G
                oneLine.append(CSV_SEPARATOR);
                oneLine.append(newObj.avrHolding.get(i).intValue()); 			//H
                oneLine.append(CSV_SEPARATOR);
                oneLine.append(newObj.istPairs.get(i).intValue()); 			//I
                oneLine.append(CSV_SEPARATOR);
                oneLine.append(newObj.layerGewinn.get(i).floatValue()); 		//J
                oneLine.append(CSV_SEPARATOR);
                oneLine.append(newObj.smma500.get(i).floatValue()); 			//K
                oneLine.append(CSV_SEPARATOR);
                oneLine.append(newObj.dca1.get(i).intValue()); 				//L
                oneLine.append(CSV_SEPARATOR);
                oneLine.append(newObj.L0_doneTrades.get(i).intValue()); 			//M
                oneLine.append(CSV_SEPARATOR);
                bw.write(oneLine.toString());
                bw.newLine();
            }
            bw.flush();
            bw.close();
        }
        catch (UnsupportedEncodingException e) {}
        catch (FileNotFoundException e){}
        catch (IOException e){}
    }
    
    }

