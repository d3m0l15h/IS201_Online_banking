package DAL.Report;

import DAL.DBConnection;
import DTO.Employee_DTO;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JRDesignQuery;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.sql.*;


public class JasperReport_DAL 
{
    private void showReport(String m_report_source, String m_sql_stmt, Map parametersMap) 
    {
        try {
            // Connect database
            Connection con = DBConnection.ConnectDb();
            // Chỉ định một báo cáo sẽ được tải lên
            InputStream is = getClass().getResourceAsStream(m_report_source);
            // Thực hiện truy vấn JRDesignQuery
            JRDesignQuery jrDesignQuery = new JRDesignQuery();
            jrDesignQuery.setText(m_sql_stmt);
            JasperDesign jasperDesign = JRXmlLoader.load(is);
            jasperDesign.setQuery(jrDesignQuery);
            // Biên dịch JasperReport
            JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parametersMap, con);
            // Hiển thị report
            JasperViewer.viewReport(jasperPrint, false);
        } 
        catch (JRException e) 
        {
            System.out.println("Exception message " + e.getMessage());
        }
    }
    
    public void showPaymentReceipt(long billID) 
    {
        String m_report_source = "PaymentReceipt.jrxml";
        String m_sql_stmt = "SELECT B.BILL_ID, B.BILL_AMOUNT, B.INVOICE_DATE, B.PAYMENT_DATE, C.CUSTOMER_ID, C.FIRST_NAME || ' ' || C.LAST_NAME FULL_NAME, B.SUPPLIER_ID, S.SUPPLIER_NAME, S.SERVICE_TYPE\n" +
                        "FROM ((CUSTOMER C JOIN BILL B ON C.CUSTOMER_ID = B.CUSTOMER_ID) \n" +
                        "        JOIN SUPPLIER S ON S.SUPPLIER_ID = B.SUPPLIER_ID)\n" +
                        "WHERE B.BILL_ID = " + billID;
        Map parametersMap = new HashMap();
        showReport(m_report_source, m_sql_stmt, parametersMap);
    }
    
    public void showTransferReceipt(long transactionID) 
    {
        String m_report_source = "TransferReceipt.jrxml";
        String m_sql_stmt = "select T.TRANSACTION_ID, T.TRANSACTION_DATE, T.TOTAL_TRANSACTION_AMOUNT, TP.NAME, TP.FEE, TD.AMOUNT, TD.SENDER_ACCOUNT, TD.RECEIVER_ACCOUNT, TD.CONTENT , C.FIRST_NAME || ' ' || C.LAST_NAME FULL_NAME\n" +
                    "from (((TRANSACTION T JOIN TRANSACTION_TYPE TP USING(TRANSACTION_TYPE_ID))\n" +
                    "        JOIN TRANSFER_DETAIL TD ON TD.TRANSACTION_ID= T.TRANSACTION_ID)\n" +
                    "            JOIN ACCOUNT A ON A.ACCOUNT_ID = TD.RECEIVER_ACCOUNT)\n" +
                    "                JOIN CUSTOMER C ON C.CUSTOMER_ID = A.CUSTOMER_ID\n" +
                    "WHERE T.TRANSACTION_ID = " + transactionID;
        Map parametersMap = new HashMap();
        showReport(m_report_source, m_sql_stmt, parametersMap);
    }
    
    public void showCustomerList(Employee_DTO dtoEmployee) 
    {
        Map parametersMap = new HashMap();
        parametersMap.put("employeeName", dtoEmployee.getFirstName() + " " + dtoEmployee.getLastName());
        String m_report_source = "CustomerList.jrxml";
        String m_sql_stmt = "SELECT CUSTOMER_ID, FIRST_NAME || ' ' || LAST_NAME FULL_NAME, GENDER, DATE_OF_BIRTH, ADDRESS, PHONE_NUMBER, ID_CARD,REVENUE\n" +
                    "FROM CUSTOMER";
        showReport(m_report_source, m_sql_stmt, parametersMap);
    }
    
    
    public void showSupplierList(Employee_DTO dtoAdmin) 
    {
        Map parametersMap = new HashMap();
        parametersMap.put("employeeName", dtoAdmin.getFirstName() + " " + dtoAdmin.getLastName());
        String m_report_source = "SupplierReport.jrxml";
        String m_sql_stmt = "SELECT * FROM SUPPLIER";
        showReport(m_report_source, m_sql_stmt, parametersMap);
    }
    
    public void showStatementReport(long accountId, Date fromDate, Date toDate) 
    {
        DateFormat df = new SimpleDateFormat("dd-MMM-yyyy");
        DateFormat df2 = new SimpleDateFormat("dd/MM/yyyy");
        Map parametersMap = new HashMap();
        String fDate = df2.format(fromDate);
        String tDate = df2.format(toDate) + " 23:59:00";
        parametersMap.put("fromDate", df2.format(fromDate));
        parametersMap.put("toDate", df2.format(toDate));
        parametersMap.put("accountId", accountId);
        String m_report_source = "Statement.jrxml";
        String m_sql_stmt = "select T.TRANSACTION_ID, T.TRANSACTION_DATE, TP.TRANSACTION_TYPE_ID, T.TOTAL_TRANSACTION_AMOUNT, TP.NAME, TP.FEE, A.ACCOUNT_ID, C. ADDRESS, TP.NAME, A.CURRENT_BALANCE , C.FIRST_NAME || ' ' || C.LAST_NAME FULL_NAME\n" +
                            "from ((TRANSACTION T JOIN TRANSACTION_TYPE TP ON T.TRANSACTION_TYPE_ID = TP.TRANSACTION_TYPE_ID)\n" +
                            "            JOIN ACCOUNT A ON A.ACCOUNT_ID = T.ACCOUNT_ID)\n" +
                            "                JOIN CUSTOMER C ON C.CUSTOMER_ID = A.CUSTOMER_ID\n" +
                            "WHERE A.ACCOUNT_ID = " + accountId + " AND ACCOUNT_TYPE_ID = 'PA'\n" +
                            "AND (TRANSACTION_DATE BETWEEN TO_DATE('" + fDate + "', 'dd/MM/yyyy') AND TO_DATE('" + tDate +"', 'dd/MM/yyyy HH24:MI:SS'))\n" +
                            "ORDER BY T.TRANSACTION_ID";
                            //"AND TRANSACTION_DATE >= '" + df.format(fromDate) + "' AND TRANSACTION_DATE <= '" + df.format(toDate) + "'";
        showReport(m_report_source, m_sql_stmt, parametersMap);
    }
}
