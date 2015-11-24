

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jxl.Workbook;
import jxl.format.ScriptStyle;
import jxl.format.UnderlineStyle;
import jxl.format.VerticalAlignment;
import jxl.write.Colour;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.testng.annotations.Test;

public class ParseXml {
	Document document;
	Element root;

	public void load(String filePath) {
		File file = new File(filePath);
		if (file.exists()) {
			SAXReader reader = new SAXReader();
			try {
				document = reader.read(file);
			} catch (DocumentException e) {
				System.out.println((filePath + " ÎÄ¼þ¶ÁÈ¡Òì³£"));
			}
		} else {
			System.out.println((filePath + " ÎÄ¼þ²»´æÔÚ"));
		}
	}

	public Element getElementObject(String elementPath) {
		return (Element) document.selectSingleNode(elementPath);
	}

	public boolean isExist(String elementPath) {
		boolean flag = false;
		Element element = this.getElementObject(elementPath);
		if (element != null)
			flag = true;
		return flag;
	}

	public String getElementText(String elementPath) {
		Element element = this.getElementObject(elementPath);
		if (element != null) {
			return element.getText().trim();
		} else {
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	public List<Element> getElementObjects(String elementPath) {
		return document.selectNodes(elementPath);
	}

	public Map<String, String> getChildrenInfoByElement(Element element) {
		Map<String, String> map = new HashMap<String, String>();
		@SuppressWarnings("unchecked")
		List<Element> children = element.elements();
		for (Element e : children) {
			map.put(e.getName(), e.getText());
		}
		return map;
	}

	

	public  void writeExcel(String fileName)
			throws RowsExceededException, WriteException {
		this.load("test-output/testng-results.xml");
		root = document.getRootElement();
		System.out.println("skipped=" + root.attributeValue("skipped"));
		System.out.println("pass=" + root.attributeValue("passed"));
		System.out.println("failed=" + root.attributeValue("failed"));
		System.out.println("total=" + root.attributeValue("total"));
		WritableWorkbook wwb = null;
		try {
			//
			wwb = Workbook.createWorkbook(new File(fileName));

		} catch (IOException e) {
			e.printStackTrace();
		}
		if (wwb != null) {
			// ï¿½ï¿½ï¿½ï¿½Ò»ï¿½ï¿½ï¿½ï¿½Ð´ï¿½ï¿½Ä¹ï¿½ï¿½ï¿½ï¿½ï¿?
			// Workbookï¿½ï¿½createSheetï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½Ò»ï¿½ï¿½ï¿½Ç¹ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½Æ£ï¿½ï¿½Ú¶ï¿½ï¿½ï¿½ï¿½Ç¹ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½Ú¹ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½Ðµï¿½Î»ï¿½ï¿?
			WritableSheet ws = wwb.createSheet("Sheet", 1);
			Label label1 = new Label(2, 5, root.attributeValue("total"));
			
			try {
				ws.addCell(label1);
				
			} catch (RowsExceededException e) {
				e.printStackTrace();
			} catch (WriteException e) {
				e.printStackTrace();
			}

			try {
				// ï¿½ï¿½ï¿½Ú´ï¿½ï¿½ï¿½Ð´ï¿½ï¿½ï¿½Ä¼ï¿½ï¿½ï¿½
				wwb.write();
				// ï¿½Ø±ï¿½ï¿½ï¿½Ô´ï¿½ï¿½ï¿½Í·ï¿½ï¿½Ú´ï¿½
				wwb.close();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (WriteException e) {
				e.printStackTrace();
			}
		}
	}
	@Test
	public void testExcel() throws RowsExceededException, WriteException
	{
		TimeString ts = new TimeString();
		writeExcel("D:\\" + ts.getDate() + "- " + ts.getTimeString()+ ".xls");
		System.out.println("test");
	}

}
