package excel; 
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.common.usermodel.HyperlinkType;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFColor;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFHyperlink;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class Excel {
	Workbook wbk;
	Sheet sht;
	Row row;
	Cell cell;
	FileOutputStream out;
	XSSFCellStyle style;
	XSSFFont font;
	XSSFColor clr;
	CreationHelper cHplr;
	XSSFHyperlink link;
	
	public Excel() {
		wbk = new XSSFWorkbook();
	}
	public void rowCreation(int i) {
		row = sht.createRow(i);
	}
	public void cellCreationAndWrite(int i, String data) {
		
		cell = row.createCell(i);
		cell.setCellValue(data);
	}
	public void toShtCrte(String sNme) {
		sht = wbk.createSheet(sNme);
	}
	public void cellFormatting(String sW1) {
		char[] sW2 = sW1.toCharArray();
		for(int i=0;i<sW2.length;i++)
			switch(sW2[i]) {
			case 'a': style.setAlignment(HorizontalAlignment.CENTER);; break;
			case 'b': style.setAlignment(HorizontalAlignment.RIGHT); break;
			case 'c': style.setAlignment(HorizontalAlignment.LEFT); break;
			case 'd': style.setFillPattern(FillPatternType.SOLID_FOREGROUND); break;
			case 'e': font.setFontHeightInPoints((short) 9); break;
			case 'f': font.setFontHeightInPoints((short) 10); break;
			case 'g': font.setFontHeightInPoints((short) 11); break;
			case 'h': font.setFontName("Segoe UI"); break;
			case 'i': font.setFontName("Calibri"); break;
			case 'j': font.setBold(true); break;
			case 'k': clr = new XSSFColor(new java.awt.Color(74,74,74)); break;
			case 'l': clr = new XSSFColor(new java.awt.Color(235,235,235)); break;
			case 'm': clr = new XSSFColor(new java.awt.Color(102,102,102)); break;
			case 'n': clr = new XSSFColor(new java.awt.Color(255,255,255)); break;
			case 'o': clr = new XSSFColor(new java.awt.Color(24,102,219)); break;
			case 'p': style.setFillForegroundColor(clr); break;
			case 'q': font.setColor(clr); break;
			case 'r': style.setFont(font); break;
			case 's': cell.setCellStyle(style); break;
			case 't': cell = null; clr = null; style = (XSSFCellStyle) wbk.createCellStyle(); font = (XSSFFont) wbk.createFont(); break;
			case 'u': font.setBold(false); break;
			case 'v': style.setVerticalAlignment(VerticalAlignment.CENTER); break;
			case 'w': style.setBorderBottom(BorderStyle.THIN); break;
			case 'x': cHplr = null; link = null; cHplr = wbk.getCreationHelper();
			//link = (XSSFHyperlink)cHplr.createHyperlink(HyperlinkType.URL); break;
			case 'y': style.setWrapText(true); break;
			case 'z': style.setBorderTop(BorderStyle.THIN); break;
			case '1': sht.autoSizeColumn(cell.getColumnIndex()); break;
			case '2': row.setHeightInPoints((2 * sht.getDefaultRowHeightInPoints())); break;
			case '3': font.setItalic(true); break;
			case '4': font.setUnderline(XSSFFont.U_SINGLE); break;
			case '5': font.setFontHeightInPoints((short) 25); break;
			}
		}

	public int getLast() {
		return sht.getLastRowNum();
	}
	public void fileCreation() throws IOException {
		out = new FileOutputStream(new File("D:\\CricBuzz.xlsx")); 
		wbk.write(out);
		out.close();
	}
}




