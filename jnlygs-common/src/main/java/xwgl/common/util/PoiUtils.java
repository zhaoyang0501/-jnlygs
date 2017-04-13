package xwgl.common.util;

import java.math.BigDecimal;

import org.apache.poi.ss.usermodel.Cell;

public class PoiUtils {
	   public static  String getStringCellValue(Cell cell) {
	   		if(cell==null)
	   			return null;
	        String strCell = "";
	        switch (cell.getCellType()) {
	        case Cell.CELL_TYPE_STRING:
	            strCell = cell.getStringCellValue();
	            break;
	        case Cell.CELL_TYPE_NUMERIC:
	            strCell = BigDecimal.valueOf(cell.getNumericCellValue()).stripTrailingZeros().toPlainString();
	            break;
	        case Cell.CELL_TYPE_BOOLEAN:
	            strCell = String.valueOf(cell.getBooleanCellValue());
	            break;
	        case Cell.CELL_TYPE_BLANK:
	            strCell = "";
	            break;
	        default:
	            strCell = "";
	            break;
	        }
	        if (strCell.equals("") || strCell == null) {
	            return "";
	        }
	        return strCell;
	}
}
