
import java.io.FileNotFoundException;
import java.io.IOException;
import org.w3c.dom.*;
import org.xml.sax.SAXException;
import javax.xml.parsers.*;
import javax.xml.xpath.*;
public class XPathTest { 
	public static void main(String[] args)    throws ParserConfigurationException, SAXException,           IOException, XPathExpressionException {   
		String path = "C:\\Users\\FJD\\workspace\\EBAdvertiseMonitor\\WebRoot\\Data\\210000000000\\210000000000Regular.xml";
		parseRegFile(path);
	}
	/*
	 * DocumentBuilderFactory domFactory = DocumentBuilderFactory.newInstance();    
		domFactory.setNamespaceAware(true); // never forget this!    
		DocumentBuilder builder = domFactory.newDocumentBuilder();    
		Document doc = builder.parse("books.xml");    
		XPathFactory factory = XPathFactory.newInstance();    
		XPath xpath = factory.newXPath();    
		XPathExpression expr      = xpath.compile("//Field/key/regex/text()");   
		Object result = expr.evaluate(doc, XPathConstants.NODESET);   
		NodeList nodes = (NodeList) result;   
		for (int i = 0; i < nodes.getLength(); i++) {    
			System.out.println(nodes.item(i).getNodeValue());  
		}  
		System.out.println("hello");
	 */
	/**
	 * boolean bool = false;
		int score = 0;
		Pattern p;
		Matcher m ;
		if(PCode=="2100000000"){
			//FileReader fr = new FileReader(this.getFieldTestFile());
			char[] chars = new char[1024];
			
			String str = "";
			BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(this.getFieldTestFile())));
			

			while ((str = br.readLine())!=null){
				
				String [] strs = str.trim().split("#!#");
				p = Pattern.compile(strs[0]);
				m = p.matcher(content);
				if (m.find()){
					score+=Integer.parseInt(strs[1]);
				}
			}
			System.out.println("类型分数： "+ score);
			
			
		}
		
		
		if(score>8){
			bool = true;
		}
		System.out.println("是否"+PCode+"："+bool);
		return bool;
	 */
    private static void parseRegFile(String regURL) throws XPathExpressionException, ParserConfigurationException, SAXException
    {
    	try{
    		DocumentBuilderFactory domFactory = DocumentBuilderFactory.newInstance();    
    		domFactory.setNamespaceAware(true); // never forget this!    
    		DocumentBuilder builder = domFactory.newDocumentBuilder();    
    		Document doc = builder.parse(regURL);    
    		XPathFactory factory = XPathFactory.newInstance();    
    		XPath xpath = factory.newXPath();
    		
    		XPathExpression exprRegulation = xpath.compile("//Regular/Regulation");   
    		Object resultRegulation = exprRegulation.evaluate(doc, XPathConstants.NODESET);   
    		NodeList nodesRegulation = (NodeList) resultRegulation;   
    		for (int i = 0; i < nodesRegulation.getLength(); i++) { 
    			Node regulation = nodesRegulation.item(i);
    			System.out.println(""+nodesRegulation.getLength());
    			try{
    				
    				XPathExpression exprKeys = xpath.compile("//Regular/Regulation[i+1]/AD_ILLEGAL_TYPEID");   
    	    		Object resultKeys = exprKeys.evaluate(doc, XPathConstants.NODESET);   
    	    		NodeList nodesKeys = (NodeList) resultKeys;  
    	    		System.out.println(""+regulation.getChildNodes().item(1).getNodeValue());
    				NodeList keys = nodesKeys.item(0).getChildNodes();
    				int ad_illegal_typeID = Integer.parseInt(regulation.getAttributes().getNamedItem("AD_ILLEGAL_TYPEID").getNodeValue());
    				String dealType = regulation.getAttributes().getNamedItem("DealType").getNodeValue();
    				String name = regulation.getAttributes().getNamedItem("name").getNodeValue();
    				
    	           
    			}catch(NumberFormatException e){
    				
    					e.printStackTrace();
    				
    				
    			} catch(java.lang.NullPointerException e){
    				
    					e.printStackTrace();
    				
    				
    			} 
    			
                
    		} 
    		
        }catch (FileNotFoundException ex) {
            
        }catch (IOException ex) {
            
        }
    }
    
}