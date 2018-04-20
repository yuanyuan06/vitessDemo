package io.vitess.common;

import com.Ostermiller.util.ExcelCSVParser;
import com.Ostermiller.util.LabeledCSVParser;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Serializable;

/**
 * Created by IntelliJ IDEA. User: hlz Date: 2009-9-16 Time: 9:34:35 To change this template use
 * File | Settings | File Templates.
 */
public class CsvFileParser implements Serializable {
	private static final long serialVersionUID = -3864804071879043194L;

	private LabeledCSVParser csvParser;// csvParser

    private int currLineNum = -1;// The current line number for reading.

    private String[] currLine = null;// The data of current line.

    private InputStreamReader reader;


    public CsvFileParser(FileInputStream fileinput) throws IOException {
        reader = new InputStreamReader(fileinput, "gbk");
        csvParser = new LabeledCSVParser(new ExcelCSVParser(reader));
        currLineNum = csvParser.getLastLineNumber();
    }

    /*
     * Param: in InputStream throws IOException
     */

    // public CsvFileParser(InputStream in) throws IOException {
    //
    // csvParser = new LabeledCSVParser(new ExcelCSVParser(in));
    // currLineNum = csvParser.getLastLineNumber();
    // }

    /*
     * Get the titles of csv file.
     * 
     * return String[]
     */
    public String[] getTitles() throws IOException {
        return csvParser.getLabels();
    }


    /*
     * Checkout whether it has more data.
     * 
     * @return ture: has data��false nodata.
     */
    public boolean hasMore() throws IOException {
        currLine = csvParser.getLine();
        currLineNum = csvParser.getLastLineNumber();
        if (null == currLine) return false;
        return true;
    }

    /*
     * Get the data of filed by field name. param:String filedName
     * 
     * @return:String
     */
    public String getByFieldName(String fieldName) {

        return csvParser.getValueByLabel(fieldName);
    }

    /*
     * Close the parser.
     */
    public void close() throws IOException {
        csvParser.close();

    }

    /*
     * Read the current line.Return the data of current line.
     * 
     * @return String[]
     */
    public String[] readLine() throws IOException {
        currLine = csvParser.getLine();

        currLineNum = csvParser.getLastLineNumber();

        return currLine;
    }

    /*
     * Get current line number.
     * 
     * @return int
     */
    public int getCurrLineNum() {
        return currLineNum;

    }

    /*
     * Get the data of the current line.
     * 
     * @return String[]
     */
    public String[] getCurrLine() {
        return currLine;
    }

}
