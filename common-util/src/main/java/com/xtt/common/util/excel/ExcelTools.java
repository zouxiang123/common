package com.xtt.common.util.excel;

import static javax.xml.datatype.DatatypeConstants.FIELD_UNDEFINED;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.Validate;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.PaneInformation;
import org.apache.poi.poifs.filesystem.OfficeXmlFileException;
import org.apache.poi.ss.SpreadsheetVersion;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.ConditionalFormatting;
import org.apache.poi.ss.usermodel.ConditionalFormattingRule;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.SheetConditionalFormatting;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.ss.util.CellReference;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelTools {

    public static Workbook openExcelFile(File f) throws FileNotFoundException, IOException {
        if ("xlsx".equalsIgnoreCase(FilenameUtils.getExtension(f.getName())))
            return new XSSFWorkbook(new FileInputStream(f));
        try {
            return new HSSFWorkbook(new FileInputStream(f));
        } catch (OfficeXmlFileException e) {
            return new XSSFWorkbook(new FileInputStream(f));
        }
    }

    public static Sheet getRequiredSheet(Workbook workbook, String sheetName) {
        try {
            Validate.notNull(workbook.getSheet(sheetName), "Sheet '%s' not found in the Excel workbook", sheetName);
        } catch (NullPointerException ex) {
            throw new BadInputException(BadInputException.KEY_SHEET_NOT_FOUND, sheetName);
        }
        return workbook.getSheet(sheetName);
    }

    public static CellStyle createBoldStyle(Workbook workbook) {
        CellStyle bold = workbook.createCellStyle();
        Font f = workbook.createFont();
        f.setBoldweight(Font.BOLDWEIGHT_BOLD);
        bold.setFont(f);
        return bold;
    }

    public static Integer toInteger(Cell c) {
        BigDecimal d = toBigDecimal(c);
        if (d == null)
            return null;
        return d.intValueExact();
    }

    public static BigDecimal toBigDecimal(Cell c) {
        if (c == null || c.getCellType() == Cell.CELL_TYPE_BLANK || c.getCellType() == Cell.CELL_TYPE_ERROR)
            return null;

        if (c.getCellType() == Cell.CELL_TYPE_STRING) {
            String x = toString(c);
            if (x == null)
                return null;
            return new BigDecimal(x);
        }

        // assume numeric type
        double x = c.getNumericCellValue();
        return BigDecimal.valueOf(x);
    }

    /**
     * @param c
     * @return true for "Y", "YES", "X", "1", null for blank, false for "NO", "N", "0"
     */
    public static Boolean toBoolean(Cell c) {
        String x = toString(c);
        if (x == null)
            return null;
        x = x.toUpperCase();
        if (ArrayUtils.contains(new String[] { "Y", "YES", "X", "1" }, x))
            return true;
        if (ArrayUtils.contains(new String[] { "N", "NO", "0" }, x))
            return false;
        return null;
    }

    public static String toString(Cell c) {
        if (c == null || c.getCellType() == Cell.CELL_TYPE_BLANK || c.getCellType() == Cell.CELL_TYPE_ERROR)
            return null;

        if (c.getCellType() == Cell.CELL_TYPE_STRING) {
            return StringUtils.stripToNull(c.getStringCellValue());
        }

        if (c.getCellType() == Cell.CELL_TYPE_FORMULA) {
            if (c.getCachedFormulaResultType() == Cell.CELL_TYPE_STRING) {
                return StringUtils.stripToNull(c.getStringCellValue());
            }
            if (c.getCachedFormulaResultType() == Cell.CELL_TYPE_BLANK)
                return null;
            if (c.getCachedFormulaResultType() == Cell.CELL_TYPE_ERROR)
                return null;
        }

        // assume numeric type
        double x = c.getNumericCellValue();
        if (x == 0)
            return "0";
        // we don't want the trailing .0
        long xx = (long) x;
        if (xx == x) {
            return String.valueOf(xx);
        }
        return String.valueOf(x);
    }

    // fix for https://issues.apache.org/bugzilla/show_bug.cgi?id=55295
    private static boolean isCellDateFormatted(Cell c) {
        if (c.getCellType() != Cell.CELL_TYPE_NUMERIC)
            return false;
        double d = c.getNumericCellValue();
        if (DateUtil.isValidExcelDate(d)) {
            CellStyle style = c.getCellStyle();
            if (style == null)
                return false;
            int i = style.getDataFormat();
            String f = style.getDataFormatString();
            if ("reserved-0x1c".equals(f))
                return true;
            if (DateUtil.isADateFormat(i, f))
                return true;
        }
        return false;
    }

    public static XMLGregorianCalendar toDay(Cell c) throws DatatypeConfigurationException {
        String d = toString(c);
        if (d == null)
            return null;

        if (isCellDateFormatted(c)) {
            d = new SimpleDateFormat("yyyy/MM/dd").format(c.getDateCellValue());
        }

        // also allow YYYY/MM/DD in addition to YYYY-MM-DD
        d = d.replace('/', '-');
        // also allow YYYY-M-D
        if (d.length() < 10) {
            String[] x = StringUtils.split(d, '-');
            if (x.length == 3) {
                d = String.format("%d-%02d-%02d", Integer.parseInt(x[0]), Integer.parseInt(x[1]), Integer.parseInt(x[2]));
            } else {
                throw new BadInputException(BadInputException.KEY_INVALID_DATE, d);
            }
        }

        XMLGregorianCalendar day = DatatypeFactory.newInstance().newXMLGregorianCalendar(d);
        day.setTime(FIELD_UNDEFINED, FIELD_UNDEFINED, FIELD_UNDEFINED);
        day.setTimezone(FIELD_UNDEFINED);
        if (day.getDay() == FIELD_UNDEFINED || day.getYear() == FIELD_UNDEFINED || day.getMonth() == FIELD_UNDEFINED)
            throw new BadInputException(BadInputException.KEY_INVALID_DATE, d);
        return day;
    }

    public static boolean isEmpty(Row r) {
        if (r == null)
            return true;
        int c = r.getLastCellNum();
        for (int i = 0; i < c; i++) {
            Cell x = r.getCell(i);
            if (StringUtils.isNotBlank(toString(x)))
                return false;

        }
        return true;
    }

    public static Row createRow(Sheet sheet, CellStyle style, int rowNum, Object... cells) {
        Row row = sheet.createRow(rowNum);

        int i = 0;
        for (Object c : cells) {
            Cell cell = createCell(row, i++, c);
            if (style != null)
                cell.setCellStyle(style);
        }
        return row;
    }

    public static Cell createCell(Row row, int cellNum, Object value) {
        Cell cell = row.createCell(cellNum);
        if (value != null) {
            if (value instanceof Number) {
                cell.setCellValue(((Number) value).doubleValue());
            } else {
                cell.setCellValue(value.toString());
            }
        }

        return cell;
    }

    public static void clearColumn(Sheet sheet, int columnNum) {
        Iterator<Row> itr = sheet.rowIterator();
        while (itr.hasNext()) {
            Row row = itr.next();
            Cell cell = row.getCell(columnNum);
            if (cell != null) {
                row.removeCell(cell);
            }
        }
    }

    public static void deleteColumn(Sheet sheet, int columnNum) {
        int col = getColumnCount(sheet);

        // column break
        int[] breaks = sheet.getColumnBreaks();
        if (breaks != null) {
            for (int idx : breaks) {
                if (idx >= columnNum) {
                    sheet.removeColumnBreak(idx);
                }
            }
            for (int idx : breaks) {
                if (idx > columnNum) {
                    sheet.setColumnBreak(idx - 1);
                }
            }
        }
        // column style
        for (int i = columnNum + 1; i < col; i++) {
            CellStyle cs = sheet.getColumnStyle(i);
            if (cs != null) {
                sheet.setDefaultColumnStyle(i - 1, cs);
            }
        }
        // column width
        for (int i = columnNum + 1; i < col; i++) {
            sheet.setColumnWidth(i - 1, sheet.getColumnWidth(i));
        }
        // column hidden
        for (int i = columnNum + 1; i < col; i++) {
            sheet.setColumnHidden(i - 1, sheet.isColumnHidden(i));
        }
        // merged region
        ArrayList<CellRangeAddress> mergedregions = new ArrayList<CellRangeAddress>();
        for (int i = 0; i < sheet.getNumMergedRegions(); i++) {
            if (sheet.getMergedRegion(i).getFirstColumn() == columnNum) {
                continue;
            }
            mergedregions.add(sheet.getMergedRegion(i));
        }
        for (int i = 0; i < mergedregions.size(); i++) {
            CellRangeAddress address = mergedregions.get(i);
            if (address.getFirstColumn() > columnNum) {
                address.setFirstColumn(address.getFirstColumn() - 1);
            }
            if (address.getLastColumn() >= columnNum) {
                address.setLastColumn(address.getLastColumn() - 1);
            }
        }
        while (sheet.getNumMergedRegions() > 0) {
            sheet.removeMergedRegion(0);
        }
        for (int i = 0; i < mergedregions.size(); i++) {
            sheet.addMergedRegion(mergedregions.get(i));
        }
        // repeating columns
        CellRangeAddress repeatAddress = sheet.getRepeatingColumns();
        if (repeatAddress != null) {
            if (repeatAddress.getFirstColumn() > columnNum) {
                repeatAddress.setFirstColumn(repeatAddress.getFirstColumn() - 1);
            }
            if (repeatAddress.getLastColumn() >= columnNum) {
                repeatAddress.setLastColumn(repeatAddress.getLastColumn() - 1);
            }
            sheet.setRepeatingColumns(repeatAddress);
        }
        // freeze pane / split pane
        PaneInformation paneif = sheet.getPaneInformation();
        if (paneif != null) {
            if (paneif.isFreezePane()) {
                int vp = paneif.getVerticalSplitPosition();
                int vc = paneif.getVerticalSplitLeftColumn();
                vp = vp > columnNum ? vp - 1 : vp;
                vc = vc > columnNum ? vc - 1 : vc;
                sheet.createFreezePane(vp, paneif.getHorizontalSplitPosition(), vc, paneif.getHorizontalSplitTopRow());
            } else {
                int vc = paneif.getVerticalSplitLeftColumn();
                vc = vc > columnNum ? vc - 1 : vc;
                sheet.createSplitPane(paneif.getVerticalSplitPosition(), paneif.getHorizontalSplitPosition(), vc, paneif.getHorizontalSplitTopRow(),
                                paneif.getActivePane());
            }
        }
        // sheet condition formatting
        SheetConditionalFormatting scf = sheet.getSheetConditionalFormatting();
        if (scf != null) {
            ArrayList<CellRangeAddress[]> addresses = new ArrayList<CellRangeAddress[]>();
            ArrayList<ConditionalFormattingRule[]> rules = new ArrayList<ConditionalFormattingRule[]>();
            for (int i = 0; i < scf.getNumConditionalFormattings(); i++) {
                ConditionalFormatting cf = scf.getConditionalFormattingAt(i);
                addresses.add(cf.getFormattingRanges());

                ConditionalFormattingRule[] rule = new ConditionalFormattingRule[cf.getNumberOfRules()];
                for (int j = 0; j < cf.getNumberOfRules(); j++) {
                    rule[j] = cf.getRule(j);
                }
                rules.add(rule);
            }
            for (int i = 0; i < addresses.size(); i++) {
                CellRangeAddress[] address = addresses.get(i);
                for (CellRangeAddress add : address) {
                    if (add.getFirstColumn() > columnNum) {
                        add.setFirstColumn(add.getFirstColumn() - 1);
                    }
                    if (add.getLastColumn() >= columnNum) {
                        add.setLastColumn(add.getLastColumn() - 1);
                    }
                }
            }
            while (scf.getNumConditionalFormattings() > 0) {
                scf.removeConditionalFormatting(0);
            }
            for (int i = 0; i < addresses.size(); i++) {
                scf.addConditionalFormatting(addresses.get(i), rules.get(i));
            }
        }

        // remove and clone cells
        Iterator<Row> itr = sheet.rowIterator();
        while (itr.hasNext()) {
            Row row = itr.next();
            Cell cell = row.getCell(columnNum);
            if (cell != null) {
                row.removeCell(cell);
            }
            for (int idx = columnNum + 1; idx < row.getLastCellNum(); idx++) {
                if (row.getCell(idx) != null) {
                    cloneCell(row.getCell(idx), row.createCell(idx - 1));
                }

                // change formula
                if (row.getCell(idx - 1).getCellType() == Cell.CELL_TYPE_FORMULA) {
                    String formula = row.getCell(idx - 1).getCellFormula();
                    Matcher matcher = Pattern.compile("\\w+").matcher(formula);
                    StringBuffer newFormula = new StringBuffer();

                    while (matcher.find()) {
                        String maybeCell = matcher.group();
                        if (CellReference.classifyCellReference(maybeCell, sheet.getWorkbook() instanceof XSSFWorkbook ? SpreadsheetVersion.EXCEL2007
                                        : SpreadsheetVersion.EXCEL97) == CellReference.NameType.CELL) {
                            int c = CellReference.convertColStringToIndex(maybeCell.split("\\d+")[0]);
                            if (c == columnNum) {
                                matcher.appendReplacement(newFormula, "");
                            } else if (c > columnNum) {
                                c--;
                                matcher.appendReplacement(newFormula, maybeCell.replaceAll("[A-Za-z]+", CellReference.convertNumToColString(c)));
                            }
                        }
                    }
                    matcher.appendTail(newFormula);

                    row.getCell(idx - 1).setCellFormula(newFormula.toString());
                }
            }
            row.removeCell(row.getCell(row.getLastCellNum() - 1));
        }
    }

    public static void cloneCell(Cell originCell, Cell clonedCell) {
        clonedCell.setCellType(originCell.getCellType());
        clonedCell.removeCellComment();
        if (originCell.getCellComment() != null) {
            clonedCell.setCellComment(originCell.getCellComment());
        }
        if (originCell.getCellStyle() != null) {
            clonedCell.setCellStyle(originCell.getCellStyle());
        }
        if (originCell.getHyperlink() != null) {
            clonedCell.setHyperlink(originCell.getHyperlink());
        }
        switch (originCell.getCellType()) {
        case Cell.CELL_TYPE_BLANK:
            break;
        case Cell.CELL_TYPE_BOOLEAN:
            clonedCell.setCellValue(originCell.getBooleanCellValue());
            break;
        case Cell.CELL_TYPE_ERROR:
            clonedCell.setCellErrorValue(originCell.getErrorCellValue());
            break;
        case Cell.CELL_TYPE_FORMULA:
            if (originCell.getCellFormula() != null) {
                clonedCell.setCellFormula(originCell.getCellFormula());
            }
            break;
        case Cell.CELL_TYPE_NUMERIC:
            clonedCell.setCellValue(originCell.getNumericCellValue());
            break;
        case Cell.CELL_TYPE_STRING:
            clonedCell.setCellValue(originCell.getStringCellValue());
            break;
        default:
            break;
        }
    }

    public static int getColumnCount(Sheet sheet) {
        Iterator<Row> itr = sheet.rowIterator();
        int col = 0;
        while (itr.hasNext()) {
            col = Math.max(col, itr.next().getLastCellNum());
        }
        return col;
    }
}
