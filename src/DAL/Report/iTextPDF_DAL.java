package DAL.Report;

import DAL.DBConnection;
import DTO.Account_DTO;
import DTO.Customer_DTO;
import DTO.Transaction_DTO;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;
import com.itextpdf.text.Document;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Desktop;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.JOptionPane;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.labels.PieSectionLabelGenerator;
import org.jfree.chart.labels.StandardPieSectionLabelGenerator;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PiePlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.chart.renderer.category.LineAndShapeRenderer;
import org.jfree.chart.renderer.category.StandardBarPainter;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;


public class iTextPDF_DAL 
{
    DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
    DateFormat df2 = new SimpleDateFormat("yyyy-MM-dd");
    DateFormat df5 = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
    DateFormat df6 = new SimpleDateFormat("MMMMM dd, yyyy HH:mm:ss");
    DateFormat df7 = new SimpleDateFormat("MMMMM dd, yyyy");
    DateFormat df8 = new SimpleDateFormat("yyyyMMddHHmmss");
    
    public ArrayList<Transaction_DTO> getTransactionList(Account_DTO dtoAccount, Date fromDate, Date toDate) 
    {
        Connection con = DBConnection.ConnectDb();
        ArrayList<Transaction_DTO> transactionList = new ArrayList<Transaction_DTO>();
        try
        {
            String SQL =    "SELECT * \n" +
                            "FROM TRANSACTION \n" +
                            "WHERE account_id = ?\n" +
                            "AND (TRANSACTION_DATE BETWEEN TO_DATE(?, 'dd/MM/yyyy') AND TO_DATE(?, 'dd/MM/yyyy HH24:MI:SS'))\n" +
                            "ORDER BY transaction_id";
            PreparedStatement prest = con.prepareStatement(SQL);
            prest.setLong(1, dtoAccount.getId());
            String fDate = df.format(fromDate);
            String tDate = df.format(toDate) + " 23:59:00";
            prest.setString(2, fDate);
            prest.setString(3, tDate);
            ResultSet rs = prest.executeQuery();
            while(rs.next())
            {
                Transaction_DTO dtoTransaction = new Transaction_DTO(rs.getInt(1), rs.getString(2), rs.getDate(3), rs.getLong(4), rs.getInt(5));
                transactionList.add(dtoTransaction);
            } 
            con.close();
        }
        catch(Exception e)
        {
            JOptionPane.showMessageDialog(null, e);
        }
        return transactionList ;
    }
    
    public long getCurrentBalanceAtOneTime(Account_DTO dtoAccount, Date time)
    {
        
        // List transaction từ thời điểm time đến thời điểm hiện tại
        ArrayList<Transaction_DTO> transactionList = new ArrayList<Transaction_DTO>();
        try
        {
            Connection con = DBConnection.ConnectDb();
            String SQL =    "SELECT * \n" +
                            "FROM TRANSACTION \n" +
                            "WHERE account_id = ?\n" +
                            "AND TRANSACTION_DATE >= TO_DATE(?, 'dd/MM/yyyy')\n" +
                            "ORDER BY transaction_id DESC";
            PreparedStatement prest = con.prepareStatement(SQL);
            prest.setLong(1, dtoAccount.getId());
            String date = df.format(time);
            prest.setString(2, date);
            ResultSet rs = prest.executeQuery();
            while(rs.next())
            {
                Transaction_DTO dtoTransaction = new Transaction_DTO(rs.getInt(1), rs.getString(2), rs.getDate(3), rs.getLong(4), rs.getInt(5));
                transactionList.add(dtoTransaction);
            } 
            con.close();
        }
        catch(Exception e)
        {
            JOptionPane.showMessageDialog(null, e);
        }
        
        long balance = dtoAccount.getCurrentBalance();
        // Tính số tiền tại fromDate
        for(Transaction_DTO transaction : transactionList)
        {
            if(time.compareTo(transaction.getTrasactionDate()) == 1)
                 break;
            if(transaction.getTransactionTypeID().equals("NT01") || transaction.getTransactionTypeID().equals("TK02"))
            {
                balance -= transaction.getTotalTransactionAmount();
            }
            else
                balance += transaction.getTotalTransactionAmount();
        }
        return balance;
    }

    private static final long MILLIS_IN_A_DAY = 1000 * 60 * 60 * 24;
 
    private static Date findNextDay(Date date)
    {
        return new Date(date.getTime() + MILLIS_IN_A_DAY);
    }
    
    
    public long getTotalSavingsAmount(Customer_DTO dtoCustomer)
    {
        long totalSavingAccount = 0;
        try{
            Connection con = DBConnection.ConnectDb();
            String SQL = "SELECT SUM(CURRENT_BALANCE) "
                    + "FROM Account "
                    + "WHERE CUSTOMER_ID = ? "
                    + "AND ACCOUNT_TYPE_ID LIKE '%SA%' "
                    + "AND STATUS = 'Active'";
            PreparedStatement prest = con.prepareStatement(SQL);
            prest.setLong(1, dtoCustomer.getId());
            ResultSet rs = prest.executeQuery();
            while(rs.next())
            {
                totalSavingAccount = rs.getLong(1);
            } 
            con.close();
        }
        catch(SQLException e)
        {
            JOptionPane.showMessageDialog(null, e);    
        }
        return totalSavingAccount;
    }
    
    public void printAccountStatementReport(Account_DTO dtoAccount, Customer_DTO dtoCustomer, Date fromDate, Date toDate)
    {
        Document document = new Document(PageSize.A4);
        Date nowDate = new Date();
        String filename = df8.format(nowDate) + "-" + dtoAccount.getId() + "-AT";
        
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        try
        {
            PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream("D:\\JAVA\\"+ filename + ".pdf"));
            document.open();
            File filefontTieuDe = new File("C:\\Users\\ACER\\Downloads\\OnlineBankingSystem-SourceCode\\OnlineBankingSystemProject\\src\\DAL\\Report\\fonts\\vuArialBold.ttf");
            BaseFont bfTieuDe = BaseFont.createFont(filefontTieuDe.getAbsolutePath(), BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
            Font fontTieuDe1 = new Font(bfTieuDe, 16);
            Font fontTieuDe2 = new Font(bfTieuDe, 13);
            Font fontTieuDe3 = new Font(bfTieuDe, 12);

            File filefontNoiDung = new File("C:\\Users\\ACER\\Downloads\\OnlineBankingSystem-SourceCode\\OnlineBankingSystemProject\\src\\DAL\\Report\\fonts\\vuArial.ttf");
            BaseFont bfNoiDung = BaseFont.createFont(filefontNoiDung.getAbsolutePath(), BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
            Font fontNoiDung1 = new Font(bfNoiDung, 13);
            Font fontNoiDung2 = new Font(bfNoiDung, 12);
            Font fontNoiDung3 = new Font(bfNoiDung, 11);

            Paragraph prgTen = new Paragraph("JOINT STOCK COMMERCIAL BANK FOR FOREIGN TRADE OF VIETNAM", fontTieuDe2);
            prgTen.setIndentationLeft(0);
            document.add(prgTen);

            Paragraph prgDiaChi = new Paragraph("53-55 Vo Thi Sau, Quyet Thang Ward, Bien Hoa City, Dong Nai", fontNoiDung2);
            prgDiaChi.setIndentationLeft(0);
            document.add(prgDiaChi);

            Paragraph prgSoDT = new Paragraph("Tel: 04-8243524/8245716", fontNoiDung2);
            prgSoDT.setIndentationLeft(0);
            document.add(prgSoDT);

            Image logo = Image.getInstance("C:\\Users\\ACER\\Downloads\\OnlineBankingSystem-SourceCode\\OnlineBankingSystemProject\\src\\GUI\\Images\\Vietcombank-icon.jpg");
            logo.setAbsolutePosition(400, 750);
            logo.scaleAbsolute(150, 50);
            document.add(logo);

            Paragraph prgTieuDe = new Paragraph("ACCOUNT STATEMENT", fontTieuDe1);
            prgTieuDe.setAlignment(Element.ALIGN_CENTER);
            prgTieuDe.setSpacingBefore(10);
            //prgTieuDe.setSpacingAfter(10);
            document.add(prgTieuDe);
            
            String tdate = df.format(toDate);
            String fdate = df.format(fromDate);
            String title = "";
            if(tdate.equals(fdate))
            {
                title += "For " + tdate;
            }
            else
            {
                title += "From " + fdate + " to " + tdate;
            }
            Paragraph prgNgay = new Paragraph(title, fontTieuDe3);
            prgNgay.setAlignment(Element.ALIGN_CENTER);
            prgNgay.setSpacingAfter(20);
            document.add(prgNgay);
           
            Paragraph prgPrintTime= new Paragraph("Implementation date: " + df5.format(nowDate), fontNoiDung1);
            prgPrintTime.setIndentationLeft(0);
            document.add(prgPrintTime);
            
            Paragraph prgAccountId= new Paragraph("Account ID: " + dtoAccount.getId(), fontNoiDung1);
            prgAccountId.setIndentationLeft(0);
            document.add(prgAccountId);
            
            Paragraph prgAccountName = new Paragraph("Account owner: " + dtoCustomer.getFirstName().toUpperCase() + " " + dtoCustomer.getLastName().toUpperCase(), fontNoiDung1);
            prgAccountName.setIndentationLeft(0);
            document.add(prgAccountName);
            
            Paragraph prgIDC = new Paragraph("ID Card: " + dtoCustomer.getIdCard(), fontNoiDung1);
            prgIDC.setIndentationLeft(0);
            document.add(prgIDC);
            
            Paragraph prgAddress = new Paragraph("Address: " + dtoCustomer.getAddress().toUpperCase(), fontNoiDung1);
            prgAddress.setIndentationLeft(0);
            document.add(prgAddress);

            Paragraph prgMoneyType = new Paragraph("Currency: VND", fontNoiDung1);
            prgMoneyType.setIndentationLeft(0);
            document.add(prgMoneyType);

            //Lấy số dư đầu kỳ (fromDate) và cuối kì (toDate)
            ArrayList<Transaction_DTO> transactionList = this.getTransactionList(dtoAccount, fromDate, toDate);
            long toDateBalance =  this.getCurrentBalanceAtOneTime(dtoAccount, toDate);
            long fromDateBalance =  this.getCurrentBalanceAtOneTime(dtoAccount, fromDate);
           
            
            // TẠO BẢNG
            PdfPTable table = new PdfPTable(4);
            table.setWidthPercentage(100);
            table.setSpacingBefore(10);
            table.setSpacingAfter(30);

            float[] tableDV_columnWidths = {120, 100, 180, 100};
            table.setWidths(tableDV_columnWidths);
            
            PdfPCell cellTransactionDate = new PdfPCell(new Paragraph("Time", fontTieuDe3));
            cellTransactionDate.setBorderColor(BaseColor.BLACK);
            cellTransactionDate.setHorizontalAlignment(Element.ALIGN_CENTER);
            cellTransactionDate.setVerticalAlignment(Element.ALIGN_MIDDLE);
            table.addCell(cellTransactionDate);

            PdfPCell cellTransactionAmount = new PdfPCell(new Paragraph("Total transaction amount", fontTieuDe3));
            cellTransactionAmount.setBorderColor(BaseColor.BLACK);
            cellTransactionAmount.setHorizontalAlignment(Element.ALIGN_CENTER);
            cellTransactionAmount.setVerticalAlignment(Element.ALIGN_MIDDLE);
            table.addCell(cellTransactionAmount);

            PdfPCell cellContent = new PdfPCell(new Paragraph("Content", fontTieuDe3));
            cellContent.setBorderColor(BaseColor.BLACK);
            cellContent.setHorizontalAlignment(Element.ALIGN_CENTER);
            cellContent.setVerticalAlignment(Element.ALIGN_MIDDLE);
            table.addCell(cellContent);
            
            PdfPCell cellBalance = new PdfPCell(new Paragraph("Balance", fontTieuDe3));
            cellBalance.setBorderColor(BaseColor.BLACK);
            cellBalance.setHorizontalAlignment(Element.ALIGN_CENTER);
            cellBalance.setVerticalAlignment(Element.ALIGN_MIDDLE);
            table.addCell(cellBalance);
          
            PdfPCell cellPreBalanceText = new PdfPCell(new Paragraph("Beginning balance: ", fontTieuDe3));
            cellPreBalanceText.setColspan(3);
            cellPreBalanceText.setHorizontalAlignment(Element.ALIGN_LEFT);
            cellPreBalanceText.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cellPreBalanceText.setMinimumHeight(20);
            table.addCell(cellPreBalanceText);
            
            PdfPCell cellPreBalance = new PdfPCell(new Paragraph(String.format("%,d", fromDateBalance) + " VND", fontTieuDe3));
            cellPreBalance.setHorizontalAlignment(Element.ALIGN_LEFT);
            cellPreBalance.setVerticalAlignment(Element.ALIGN_MIDDLE);
            table.addCell(cellPreBalance);
            
            long balance = fromDateBalance;
            try{
                Connection con = DBConnection.ConnectDb();
                String SQL = "select T.TRANSACTION_ID, T.TRANSACTION_DATE, T.TOTAL_TRANSACTION_AMOUNT, TP.NAME, TP.TRANSACTION_TYPE_ID, TP.FEE\n" +
                            "from ((TRANSACTION T JOIN TRANSACTION_TYPE TP ON T.TRANSACTION_TYPE_ID = TP.TRANSACTION_TYPE_ID)\n" +
                            "            JOIN ACCOUNT A ON A.ACCOUNT_ID = T.ACCOUNT_ID)\n" +
                            "                JOIN CUSTOMER C ON C.CUSTOMER_ID = A.CUSTOMER_ID\n" +
                            "WHERE A.ACCOUNT_ID = ? AND ACCOUNT_TYPE_ID = 'PA'\n" +
                            "AND (TRANSACTION_DATE BETWEEN TO_DATE(?, 'dd/MM/yyyy') AND TO_DATE(?, 'dd/MM/yyyy HH24:MI:SS'))\n" +
                            "ORDER BY T.TRANSACTION_ID";
                PreparedStatement prest = con.prepareStatement(SQL);
                prest.setLong(1, dtoAccount.getId());
                String fDate = df.format(fromDate);
                String tDate = df.format(toDate) + " 23:59:00";
                prest.setString(2, fDate);
                prest.setString(3, tDate);
                ResultSet rs = prest.executeQuery();
                while(rs.next())
                {
                    PdfPCell cellTranDate = new PdfPCell(new Paragraph(df5.format(rs.getDate(2)), fontNoiDung3));
                    cellTranDate.setHorizontalAlignment(Element.ALIGN_CENTER);
                    cellTranDate.setVerticalAlignment(Element.ALIGN_MIDDLE);
                    cellTranDate.setMinimumHeight(20);
                    table.addCell(cellTranDate);

                    String amount = "";
                    if(rs.getString(5).equals("NT01") || rs.getString(5).equals("TK02"))
                    {
                        amount = "+" + String.format("%,d", rs.getLong(3)) + " VND";
                        balance += rs.getLong(3);
                    }
                    else
                    {
                        amount = "-" + String.format("%,d", rs.getLong(3)) + " VND";
                        balance -= rs.getLong(3);
                    }
                    PdfPCell cellAmount = new PdfPCell(new Paragraph(amount, fontNoiDung3));
                    cellAmount.setHorizontalAlignment(Element.ALIGN_LEFT);
                    cellAmount.setVerticalAlignment(Element.ALIGN_MIDDLE);
                    table.addCell(cellAmount);

                    PdfPCell cellCont = new PdfPCell(new Paragraph(rs.getString(4), fontNoiDung3));
                    cellCont.setHorizontalAlignment(Element.ALIGN_LEFT);
                    cellCont.setVerticalAlignment(Element.ALIGN_MIDDLE);
                    table.addCell(cellCont);
                    
                    PdfPCell cellBal = new PdfPCell(new Paragraph(String.format("%,d", balance) + " VND", fontNoiDung3));
                    cellBal.setHorizontalAlignment(Element.ALIGN_LEFT);
                    cellBal.setVerticalAlignment(Element.ALIGN_MIDDLE);
                    table.addCell(cellBal);
                }
                con.close();
            }
            catch(SQLException e)
            {
                JOptionPane.showMessageDialog(null, e);    
            }

            PdfPCell cellBalanceText = new PdfPCell(new Paragraph("Ending balance: ", fontTieuDe3));
            cellBalanceText.setColspan(3);
            cellBalanceText.setHorizontalAlignment(Element.ALIGN_LEFT);
            cellBalanceText.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cellBalanceText.setMinimumHeight(20);
            table.addCell(cellBalanceText);
            
            PdfPCell cellPostBalance = new PdfPCell(new Paragraph(String.format("%,d", toDateBalance) + " VND", fontTieuDe3));
            cellPostBalance.setHorizontalAlignment(Element.ALIGN_LEFT);
            cellPostBalance.setVerticalAlignment(Element.ALIGN_MIDDLE);
            table.addCell(cellPostBalance);
            
            document.add(table);
            
            SimpleDateFormat df3 = new SimpleDateFormat("dd/MM HH:mm");
            SimpleDateFormat df4 = new SimpleDateFormat("dd/MM");
            
            dataset.addValue(fromDateBalance , "balance" , df4.format(fromDate));
             //create data set
            int i = 0;
            
            Date iDate = fromDate;
            boolean flag = false;
            
            if(df.format(iDate).equals(df.format(toDate))) // trường hợp sao kê 1 ngày
                dataset.addValue(toDateBalance , "balance" , df3.format(toDate));
            else // sao kê nhiều ngày
            {
                for(Transaction_DTO transaction : transactionList)
                {
                    flag = false;
                    if(df.format(iDate).equals(df.format(transaction.getTrasactionDate())))
                        flag = true;
                    while(df.format(iDate).equals(df.format(transaction.getTrasactionDate())) == false)
                    {
                        if(!df.format(iDate).equals(df.format(fromDate))) // không cho ghi đè lên dòng dataset đầu tiên (số dư đầu kỳ)
                            dataset.addValue(fromDateBalance , "balance", df4.format(iDate));
                        iDate = findNextDay(iDate);
                    }

                    if(transaction.getTransactionTypeID().equals("NT01") || transaction.getTransactionTypeID().equals("TK02"))
                    {
                        fromDateBalance += transaction.getTotalTransactionAmount();
                    }
                    else
                        fromDateBalance -= transaction.getTotalTransactionAmount();

                    if(flag == true && !df.format(iDate).equals(df.format(fromDate))) // không cho ghi đè lên dòng dataset đầu tiên (số dư đầu kỳ)
                    {
                        dataset.addValue(fromDateBalance , "balance", df4.format(iDate));
                    }
                }
                while(df.format(iDate).equals(df.format(toDate)) == false) 
                {
                    iDate = findNextDay(iDate);
                    dataset.addValue(fromDateBalance , "balance", df4.format(iDate));
                }
            }
            
            BufferedImage bufferedImageBDDT = createLineChart(dataset, toDate, fromDate);
            Image image = Image.getInstance(writer, bufferedImageBDDT, 1.0f);
            image.setAlignment(Element.ALIGN_CENTER);
            image.scaleAbsolute(500, 300);
            document.add(image);
            
            //xac nhan cua ngan hang va nguoi lap bao cao
            PdfPTable tableXN = new PdfPTable(2);
            tableXN.setWidthPercentage(90);           
            tableXN.setSpacingAfter(20);
            
            float[] tableXN_columnWidths = {150, 350};
            tableXN.setWidths(tableXN_columnWidths);
            
            PdfPCell cellXN = new PdfPCell(new Paragraph("Reporter", fontTieuDe2));
            cellXN.setBorder(0);
            cellXN.setRowspan(3);
            cellXN.setHorizontalAlignment(Element.ALIGN_CENTER);
            cellXN.setVerticalAlignment(Element.ALIGN_TOP);
            tableXN.addCell(cellXN);
            
            Paragraph prgPTime= new Paragraph("Bien Hoa City, " + df7.format(new Date()), fontNoiDung1);
            prgPTime.setIndentationLeft(300);
            prgPTime.setSpacingBefore(30);
            prgPTime.setSpacingAfter(10); 
            document.add(prgPTime);
            
            PdfPCell cellEmloyee = new PdfPCell(new Paragraph("CONFIRMATION OF VIETCOMBANK\n\n\n\n", fontTieuDe2));
            cellEmloyee.setBorder(0);
            cellEmloyee.setHorizontalAlignment(Element.ALIGN_RIGHT);
            cellEmloyee.setVerticalAlignment(Element.ALIGN_MIDDLE);            
            tableXN.addCell(cellEmloyee);         
           
            //loi ket
            Paragraph prg4 = new Paragraph("Thank you for banking with Vietcombank!", fontNoiDung2);
            prg4.setAlignment(Element.ALIGN_CENTER);
            prg4.setSpacingBefore(50);     
            document.add(tableXN);
            document.add(prg4); 
            
            document.close();
            writer.close();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        
        try
        {
            File file = new File("D:\\JAVA\\"+ filename + "1.pdf");
            addPageNum("D:\\JAVA\\"+ filename + ".pdf", "D:\\JAVA\\"+ filename + "1.pdf");
            if(!Desktop.isDesktopSupported())
            {
                System.out.println("Not supported");
                return;
            }
            Desktop desktop = Desktop.getDesktop();
            if(file.exists())
                desktop.open(file);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
    
    public BufferedImage createLineChart(DefaultCategoryDataset dataset, Date toDate, Date fromDate)
    {
        BufferedImage bufferedImage = null;

        java.awt.Font fontTieuDe = new java.awt.Font("Tahoma", java.awt.Font.BOLD, 120);
        java.awt.Font fontNoiDung1 = new java.awt.Font("Tahoma", java.awt.Font.PLAIN, 80);
        java.awt.Font fontNoiDung2 = new java.awt.Font("Tahoma", java.awt.Font.ITALIC, 80);
        
        try
        {
            String tDate = df.format(toDate);
            String fDate = df.format(fromDate);
            String title = "Balance tracking chart ";
            if(tDate.equals(fDate))
            {
                title += "for " + tDate;
            }
            else
            {
                title += "from " + fDate + " to " + tDate;
            }
            JFreeChart lineChart = ChartFactory.createLineChart(title, "Time" ,"Balance", dataset, PlotOrientation.VERTICAL, true,true,false);
            lineChart.getTitle().setFont(new java.awt.Font("Tahoma", java.awt.Font.BOLD, 120));
            lineChart.setAntiAlias(true);
            
            CategoryPlot plot = (CategoryPlot)lineChart.getPlot();
            plot.setBackgroundPaint(Color.white);
            plot.setOutlinePaint(null);
            
            //CategoryItemRenderer renderer = ((CategoryPlot) lineChart.getPlot()).getRenderer();
            LineAndShapeRenderer renderer = (LineAndShapeRenderer) lineChart.getCategoryPlot().getRenderer();
            renderer.setBaseStroke(new BasicStroke(5.0f));
            renderer.setAutoPopulateSeriesStroke(false);
                    
            CategoryAxis domainAxis = plot.getDomainAxis();
            domainAxis.setTickLabelFont(fontNoiDung1);
            domainAxis.setLabelFont(fontNoiDung2);
            
            ValueAxis rangeAxis = plot.getRangeAxis();
            rangeAxis.setTickLabelFont(fontNoiDung1);
            rangeAxis.setLabelFont(fontNoiDung2);

            bufferedImage = lineChart.createBufferedImage(5000, 3000);
            
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return bufferedImage;
    }
    
    public void printAccountBalanceConfirmationReport(Account_DTO dtoAccount, Customer_DTO dtoCustomer)
    {
        Document document = new Document(PageSize.A4);
        Date date = new Date();
        String filename = df8.format(date) + "-" + dtoAccount.getId() + "-ABC";
        
        DefaultPieDataset dataset = new DefaultPieDataset();
        try
        {
            PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream("D:\\JAVA\\"+ filename + ".pdf"));
            document.open();
            File filefontTieuDe = new File("C:\\Users\\ACER\\Downloads\\OnlineBankingSystem-SourceCode\\OnlineBankingSystemProject\\src\\DAL\\Report\\fonts\\vuArialBold.ttf");
            BaseFont bfTieuDe = BaseFont.createFont(filefontTieuDe.getAbsolutePath(), BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
            Font fontTieuDe1 = new Font(bfTieuDe, 16);
            Font fontTieuDe2 = new Font(bfTieuDe, 13);
            Font fontTieuDe3 = new Font(bfTieuDe, 12);
            

            File filefontNoiDung = new File("C:\\Users\\ACER\\Downloads\\OnlineBankingSystem-SourceCode\\OnlineBankingSystemProject\\src\\DAL\\Report\\fonts\\vuArial.ttf");
            BaseFont bfNoiDung = BaseFont.createFont(filefontNoiDung.getAbsolutePath(), BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
            Font fontNoiDung1 = new Font(bfNoiDung, 13);
            Font fontNoiDung2 = new Font(bfNoiDung, 12);
            Font fontNoiDung3 = new Font(bfNoiDung, 11);
                        
            Image logo = Image.getInstance("C:\\Users\\ACER\\Downloads\\OnlineBankingSystem-SourceCode\\OnlineBankingSystemProject\\src\\GUI\\Images\\Vietcombank-icon.jpg");
            logo.setAbsolutePosition(440, 750);
            logo.scaleAbsolute(150, 50);
            document.add(logo);

            Paragraph prgTenPK = new Paragraph("JOINT STOCK COMMERCIAL BANK FOR FOREIGN TRADE OF VIETNAM", fontTieuDe2);
            prgTenPK.setIndentationLeft(0);
            document.add(prgTenPK);

            Paragraph prgDiaChiPK = new Paragraph("53-55 Vo Thi Sau, Quyet Thang Ward, Bien Hoa City, Dong Nai", fontNoiDung2);
            prgDiaChiPK.setIndentationLeft(0);
            document.add(prgDiaChiPK);

            Paragraph prgSoDTPK = new Paragraph("Tel: 04-8243524/8245716", fontNoiDung2);
            prgSoDTPK.setIndentationLeft(0);
            document.add(prgSoDTPK);

            Paragraph prgTieuDe = new Paragraph("ACCOUNT BALANCE CONFIRMATION", fontTieuDe1);
            prgTieuDe.setAlignment(Element.ALIGN_CENTER);
            prgTieuDe.setSpacingBefore(10);
            prgTieuDe.setSpacingAfter(10);
            document.add(prgTieuDe);
            
            Paragraph prg1= new Paragraph("At the time of " + df6.format(date) + ", the Vietcombank (VCB) - Bien Hoa branch confirms that:", fontNoiDung2);
            prg1.setIndentationLeft(0);
            document.add(prg1);

            List list = new List(List.UNORDERED);
            String gender = (dtoCustomer.getGender().equals("Male")) ? "Mr. " : "Mrs. ";
            list.add(new ListItem(gender + dtoCustomer.getFirstName().toUpperCase() + " " + dtoCustomer.getLastName().toUpperCase(), fontNoiDung2));
            list.add(new ListItem("Date of birth (dd/mm/yyyy): " + df.format(dtoCustomer.getDateOfBirth()), fontNoiDung2));
            list.add(new ListItem("ID Card: " + dtoCustomer.getIdCard(), fontNoiDung2));
            list.add(new ListItem("Address: " + dtoCustomer.getAddress().toUpperCase(), fontNoiDung2));
            list.add(new ListItem("Phone number: " + dtoCustomer.getPhoneNumber(), fontNoiDung2));
            document.add(list);
           
            Paragraph prg2 = new Paragraph("Is the owner of these account(s) at our Bank with details at follow:", fontNoiDung2);
            prg2.setIndentationLeft(0);
            document.add(prg2);
            
            long paymentBalance = dtoAccount.getCurrentBalance();
            long savingBalance = this.getTotalSavingsAmount(dtoCustomer);
            long totalBalance = paymentBalance + savingBalance;

            // Tạo bảng ở đây
            
            PdfPTable tableTK = new PdfPTable(6);
            tableTK.setWidthPercentage(100);
            tableTK.setSpacingBefore(10);
            tableTK.setSpacingAfter(5);
            
            float[] tableDV_ColumWidths = {50,100,250,120,200,100};
            tableTK.setWidths(tableDV_ColumWidths);
            
            PdfPCell cellTDTT = new PdfPCell(new Paragraph("No.",fontTieuDe3));
            cellTDTT.setHorizontalAlignment(Element.ALIGN_CENTER);
            cellTDTT.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cellTDTT.setMinimumHeight(30);
            tableTK.addCell(cellTDTT);
            
            PdfPCell cellTDMaTK = new PdfPCell(new Paragraph("Account ID",fontTieuDe3));
            cellTDMaTK.setHorizontalAlignment(Element.ALIGN_CENTER);
            cellTDMaTK.setVerticalAlignment(Element.ALIGN_MIDDLE);
            tableTK.addCell(cellTDMaTK);
            
            PdfPCell cellTDTenTK = new PdfPCell(new Paragraph("Account Type",fontTieuDe3));
            cellTDTenTK.setHorizontalAlignment(Element.ALIGN_CENTER);
            cellTDTenTK.setVerticalAlignment(Element.ALIGN_MIDDLE);
            tableTK.addCell(cellTDTenTK);
            
            PdfPCell cellTDNgMo = new PdfPCell(new Paragraph("Open Day",fontTieuDe3));
            cellTDNgMo.setHorizontalAlignment(Element.ALIGN_CENTER);
            cellTDNgMo.setVerticalAlignment(Element.ALIGN_MIDDLE);
            tableTK.addCell(cellTDNgMo);
            
            PdfPCell cellTDSodu = new PdfPCell(new Paragraph("Current Balance",fontTieuDe3));
            cellTDSodu.setHorizontalAlignment(Element.ALIGN_CENTER);
            cellTDSodu.setVerticalAlignment(Element.ALIGN_MIDDLE);
            tableTK.addCell(cellTDSodu);
            
            PdfPCell cellTDStatus= new PdfPCell(new Paragraph("Status",fontTieuDe3));
            cellTDStatus.setHorizontalAlignment(Element.ALIGN_CENTER);
            cellTDStatus.setVerticalAlignment(Element.ALIGN_MIDDLE);
            tableTK.addCell(cellTDStatus);
           
            int STT=1;
            try{
                    Connection con = DBConnection.ConnectDb();
                    String SQL = "SELECT A.ACCOUNT_ID,AT.NAME,TO_CHAR(OPEN_DAY, 'dd-MM-yyyy'),CURRENT_BALANCE, STATUS "
                                +"FROM ACCOUNT A JOIN ACCOUNT_TYPE AT ON A.ACCOUNT_TYPE_ID = AT.ACCOUNT_TYPE_ID WHERE STATUS = 'Active' AND CUSTOMER_ID=? ORDER BY A.ACCOUNT_ID";
                    PreparedStatement pres = con.prepareStatement(SQL);
                    pres.setLong(1,dtoCustomer.getId());
                    ResultSet rs = pres.executeQuery();
                    while(rs.next()){
                        PdfPCell cellSTT = new PdfPCell(new Paragraph(String.valueOf(STT),fontNoiDung2));
                        cellSTT.setHorizontalAlignment(Element.ALIGN_CENTER);
                        cellSTT.setVerticalAlignment(Element.ALIGN_MIDDLE);
                        tableTK.addCell(cellSTT);
                            
                        PdfPCell cellMaTK = new PdfPCell(new Paragraph(rs.getString(1),fontNoiDung2));
                        cellMaTK.setHorizontalAlignment(Element.ALIGN_CENTER);
                        cellMaTK.setVerticalAlignment(Element.ALIGN_MIDDLE);
                        tableTK.addCell(cellMaTK);
                            
                        PdfPCell cellTenTK = new PdfPCell(new Paragraph(rs.getString(2),fontNoiDung2));
                        cellTenTK.setHorizontalAlignment(Element.ALIGN_LEFT);
                        cellTenTK.setVerticalAlignment(Element.ALIGN_LEFT);
                        tableTK.addCell(cellTenTK);
                        
                        PdfPCell cellNgMo = new PdfPCell(new Paragraph(rs.getString(3),fontNoiDung2));
                        cellNgMo.setHorizontalAlignment(Element.ALIGN_CENTER);
                        cellNgMo.setVerticalAlignment(Element.ALIGN_MIDDLE);
                        tableTK.addCell(cellNgMo);
                        
                        PdfPCell cellSodu = new PdfPCell(new Paragraph(String.format("%,d VND",rs.getLong(4))));
                        cellSodu.setHorizontalAlignment(Element.ALIGN_CENTER);
                        cellSodu.setVerticalAlignment(Element.ALIGN_MIDDLE);
                        tableTK.addCell(cellSodu);
                        
                        PdfPCell cellStatus = new PdfPCell(new Paragraph(rs.getString(5)));
                        cellStatus.setHorizontalAlignment(Element.ALIGN_CENTER);
                        cellStatus.setVerticalAlignment(Element.ALIGN_MIDDLE);
                        tableTK.addCell(cellStatus);
                        STT++;
                        }
                    }catch(SQLException e){
                        e.printStackTrace();
                    }

            // Tạo dataset
            dataset.setValue("Payment account", paymentBalance);
            dataset.setValue("Saving account", savingBalance);
            
            PdfPCell cellTongCong = new PdfPCell(new Paragraph("Payment account balance: ", fontTieuDe3));
            cellTongCong.setColspan(4);
            cellTongCong.setHorizontalAlignment(Element.ALIGN_RIGHT);
            cellTongCong.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cellTongCong.setMinimumHeight(20);
            tableTK.addCell(cellTongCong);
            
            PdfPCell cellTongTien = new PdfPCell(new Paragraph(String.format("%,d", paymentBalance) + " VND", fontTieuDe3));
            cellTongTien.setHorizontalAlignment(Element.ALIGN_CENTER);
            cellTongTien.setVerticalAlignment(Element.ALIGN_MIDDLE);
            tableTK.addCell(cellTongTien);

            PdfPCell cellCotTrong = new PdfPCell(new Paragraph("", fontTieuDe3));
            cellCotTrong.setHorizontalAlignment(Element.ALIGN_CENTER);
            cellCotTrong.setVerticalAlignment(Element.ALIGN_MIDDLE);
            tableTK.addCell(cellCotTrong);
            

            PdfPCell cellTSAB = new PdfPCell(new Paragraph("Total savings account balance: ", fontTieuDe3));
            cellTSAB.setColspan(4);
            cellTSAB.setHorizontalAlignment(Element.ALIGN_RIGHT);
            cellTSAB.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cellTSAB.setMinimumHeight(20);
            tableTK.addCell(cellTSAB);
            
            PdfPCell cellSB = new PdfPCell(new Paragraph(String.format("%,d", savingBalance) + " VND", fontTieuDe3));
            cellSB.setHorizontalAlignment(Element.ALIGN_CENTER);
            cellSB.setVerticalAlignment(Element.ALIGN_MIDDLE);
            tableTK.addCell(cellSB);

            tableTK.addCell(cellCotTrong);
            
            PdfPCell cellTA = new PdfPCell(new Paragraph("TOTAL ASSETS: ", fontTieuDe3));
            cellTA.setColspan(4);
            cellTA.setHorizontalAlignment(Element.ALIGN_RIGHT);
            cellTA.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cellTA.setMinimumHeight(20);
            tableTK.addCell(cellTA);
            
            PdfPCell cellTB = new PdfPCell(new Paragraph(String.format("%,d", totalBalance) + " VND", fontTieuDe3));
            cellTB.setHorizontalAlignment(Element.ALIGN_CENTER);
            cellTB.setVerticalAlignment(Element.ALIGN_MIDDLE);
            tableTK.addCell(cellTB);

            tableTK.addCell(cellCotTrong);
            
            document.add(tableTK);
            
            /*Paragraph prgChartTitile = new Paragraph("Financial overview chart", fontTieuDe3);
            prgChartTitile.setAlignment(Element.ALIGN_CENTER);
            prgChartTitile.setSpacingBefore(10);
            prgChartTitile.setSpacingAfter(0);*/
            BufferedImage bufferedImage = createPieChart(dataset);
            Image image = Image.getInstance(writer, bufferedImage, 1.0f);
            image.setAlignment(Element.ALIGN_CENTER);
            image.scaleAbsolute(200, 200);
            //document.add(prgChartTitile);
            document.add(image);
           
            //xac nhan cua ngan hang va nguoi lap bao cao
            PdfPTable tableXN = new PdfPTable(2);
            tableXN.setWidthPercentage(90);           
            tableXN.setSpacingAfter(20);
            
            float[] tableXN_columnWidths = {150, 350};
            tableXN.setWidths(tableXN_columnWidths);
            
            PdfPCell cellXN = new PdfPCell(new Paragraph("Reporter", fontTieuDe2));
            cellXN.setBorder(0);
            cellXN.setRowspan(3);
            cellXN.setHorizontalAlignment(Element.ALIGN_CENTER);
            cellXN.setVerticalAlignment(Element.ALIGN_TOP);
            tableXN.addCell(cellXN);
            
            Paragraph prgPrintTime= new Paragraph("Bien Hoa City, " + df7.format(date), fontNoiDung1);
            prgPrintTime.setIndentationLeft(300);
            prgPrintTime.setSpacingBefore(30);
            prgPrintTime.setSpacingAfter(10); 
            document.add(prgPrintTime);
            
            PdfPCell cellEmloyee = new PdfPCell(new Paragraph("CONFIRMATION OF VIETCOMBANK\n\n\n\n", fontTieuDe2));
            cellEmloyee.setBorder(0);
            cellEmloyee.setHorizontalAlignment(Element.ALIGN_RIGHT);
            cellEmloyee.setVerticalAlignment(Element.ALIGN_MIDDLE);            
            tableXN.addCell(cellEmloyee);         
           
            //loi ket
            Paragraph prg4 = new Paragraph("Thank you for banking with Vietcombank!", fontNoiDung2);
            prg4.setAlignment(Element.ALIGN_CENTER);
            prg4.setSpacingBefore(50);     
            document.add(tableXN);
            document.add(prg4); 
                    
            document.close();
            writer.close();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }

        try
        {
            File file = new File("D:\\JAVA\\"+ filename + ".pdf");
            if(!Desktop.isDesktopSupported())
            {
                System.out.println("Not supported");
                return;
            }
            Desktop desktop = Desktop.getDesktop();
            if(file.exists())
                desktop.open(file);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
    
    public BufferedImage createPieChart(DefaultPieDataset dataset)
    {
        BufferedImage bufferedImage = null;
        try
        {
            JFreeChart chart = ChartFactory.createPieChart("Financial overview chart", dataset, false, true, false);
            chart.getTitle().setFont(new java.awt.Font("Tahoma", java.awt.Font.BOLD, 220));
            PiePlot piePlot = (PiePlot) chart.getPlot();
            piePlot.setShadowPaint(null);
            piePlot.setBackgroundPaint(Color.white);
            piePlot.setOutlinePaint(null);
            piePlot.setLabelBackgroundPaint(Color.white);
            piePlot.setLabelOutlinePaint(Color.white);
            piePlot.setLabelShadowPaint(null);
            piePlot.setLabelFont(new java.awt.Font("Tahoma", java.awt.Font.ITALIC, 140));
            PieSectionLabelGenerator labelGenerator = new StandardPieSectionLabelGenerator("{0} - {2}");
            piePlot.setLabelGenerator(labelGenerator);
            bufferedImage = chart.createBufferedImage(4000, 4000);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return bufferedImage;
    }
    
    public void printRevenueReportFromCustomer(Account_DTO dtoAccount, Customer_DTO dtoCustomer, Date fromDate, Date toDate)
    {
        Document document = new Document(PageSize.A4);
        String filename = df8.format(new Date()) + "-" + dtoAccount.getId() + "-RRFC";
        
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        try
        {
            PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream("D:\\JAVA\\"+ filename + ".pdf"));
            document.open();
            File filefontTieuDe = new File("C:\\Users\\ACER\\Downloads\\OnlineBankingSystem-SourceCode\\OnlineBankingSystemProject\\src\\DAL\\Report\\fonts\\vuArialBold.ttf");
            BaseFont bfTieuDe = BaseFont.createFont(filefontTieuDe.getAbsolutePath(), BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
            Font fontTieuDe1 = new Font(bfTieuDe, 16);
            Font fontTieuDe2 = new Font(bfTieuDe, 13);
            Font fontTieuDe3 = new Font(bfTieuDe, 12);

            File filefontNoiDung = new File("C:\\Users\\ACER\\Downloads\\OnlineBankingSystem-SourceCode\\OnlineBankingSystemProject\\src\\DAL\\Report\\fonts\\vuArial.ttf");
            BaseFont bfNoiDung = BaseFont.createFont(filefontNoiDung.getAbsolutePath(), BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
            Font fontNoiDung1 = new Font(bfNoiDung, 13);
            Font fontNoiDung2 = new Font(bfNoiDung, 12);
            Font fontNoiDung3 = new Font(bfNoiDung, 11);

            Paragraph prgTen = new Paragraph("JOINT STOCK COMMERCIAL BANK FOR FOREIGN TRADE OF VIETNAM", fontTieuDe2);
            prgTen.setIndentationLeft(0);
            document.add(prgTen);

            Paragraph prgDiaChi = new Paragraph("53-55 Vo Thi Sau, Quyet Thang Ward, Bien Hoa City, Dong Nai", fontNoiDung2);
            prgDiaChi.setIndentationLeft(0);
            document.add(prgDiaChi);

            Paragraph prgSoDT = new Paragraph("Tel: 04-8243524/8245716", fontNoiDung2);
            prgSoDT.setIndentationLeft(0);
            document.add(prgSoDT);
            
            Image logo = Image.getInstance("C:\\Users\\ACER\\Downloads\\OnlineBankingSystem-SourceCode\\OnlineBankingSystemProject\\src\\GUI\\Images\\Vietcombank-icon.jpg");
            logo.setAbsolutePosition(400, 750);
            logo.scaleAbsolute(150, 50);
            document.add(logo);

            Paragraph prgPt = new Paragraph("Print time: " + df5.format(new Date()), fontNoiDung3);
            prgPt.setAlignment(Element.ALIGN_RIGHT);
            prgPt.setSpacingAfter(10);
            document.add(prgPt);
            
            Paragraph prgTieuDe = new Paragraph("REVENUE REPORT FROM CUSTOMER", fontTieuDe1);
            prgTieuDe.setAlignment(Element.ALIGN_CENTER);
            prgTieuDe.setSpacingBefore(10);
            prgTieuDe.setSpacingAfter(10);
            document.add(prgTieuDe); 
            
            String tdate = df.format(toDate);
            String fdate = df.format(fromDate);
            String title = "";
            if(tdate.equals(fdate))
            {
                title += "For " + tdate;
            }
            else
            {
                title += "From " + fdate + " to " + tdate;
            }
            Paragraph prgNgay = new Paragraph(title, fontTieuDe3);
            prgNgay.setAlignment(Element.ALIGN_CENTER);
            prgNgay.setSpacingAfter(20);
            document.add(prgNgay);


            //thong tin 
            Paragraph prgInfor = new Paragraph("I. Customer information", fontTieuDe2);
            prgInfor.setSpacingBefore(10);
            //prgDichVu.setSpacingAfter(10);
            document.add(prgInfor);
            
            Paragraph prgAccountName = new Paragraph("Customer name: " + dtoCustomer.getFirstName().toUpperCase() + " " + dtoCustomer.getLastName().toUpperCase(), fontNoiDung1);
            prgAccountName.setIndentationLeft(0);
            document.add(prgAccountName);
            
            Paragraph prgDob= new Paragraph("Date of birth (dd/mm/yyyy): " + df.format(dtoCustomer.getDateOfBirth()), fontNoiDung1);
            prgDob.setIndentationLeft(0);
            document.add(prgDob);
            
            Paragraph prgIDC = new Paragraph("ID Card: " + dtoCustomer.getIdCard(), fontNoiDung1);
            prgIDC.setIndentationLeft(0);
            document.add(prgIDC);
            
            Paragraph prgPNo = new Paragraph("Phone number: " + dtoCustomer.getPhoneNumber(), fontNoiDung1);
            prgPNo.setIndentationLeft(0);
            document.add(prgPNo);
            
            Paragraph prgAddress = new Paragraph("Address: " + dtoCustomer.getAddress().toUpperCase(), fontNoiDung1);
            prgAddress.setIndentationLeft(0);
            document.add(prgAddress);
            
            Paragraph prgAccountId= new Paragraph("Payment Account ID: " + dtoAccount.getId(), fontNoiDung1);
            prgAccountId.setIndentationLeft(0);
            document.add(prgAccountId);
            
            Paragraph prgOD = new Paragraph("Account opening date: " + df.format(dtoAccount.getOpenDay()), fontNoiDung1);
             prgOD.setIndentationLeft(0);
            document.add( prgOD);
            
            //tao bang
            Paragraph prgtbl = new Paragraph("II. Revenue from customer", fontTieuDe2);
            prgtbl.setSpacingBefore(10);
            prgtbl.setSpacingAfter(10);
            document.add(prgtbl);
            
            PdfPTable tableRevenue = new PdfPTable(4);
            tableRevenue.setWidthPercentage(80);
            tableRevenue.setSpacingBefore(10);
            tableRevenue.setSpacingAfter(10);
            
             float[] tableRevenue_columnWidths = {50, 130, 180, 100};
            tableRevenue.setWidths(tableRevenue_columnWidths);

            PdfPCell cellTDTT = new PdfPCell(new Paragraph("No.", fontTieuDe3));
            cellTDTT.setBorderColor(BaseColor.BLACK);
            cellTDTT.setHorizontalAlignment(Element.ALIGN_CENTER);
            cellTDTT.setVerticalAlignment(Element.ALIGN_MIDDLE);    
            cellTDTT.setMinimumHeight(30);
            tableRevenue.addCell(cellTDTT);

            PdfPCell cellTDate = new PdfPCell(new Paragraph("Transaction date", fontTieuDe3));
            cellTDate.setBorderColor(BaseColor.BLACK);
            cellTDate.setHorizontalAlignment(Element.ALIGN_CENTER);
            cellTDate.setVerticalAlignment(Element.ALIGN_MIDDLE);                    
            tableRevenue.addCell(cellTDate);
             
            PdfPCell cellTDTransName = new PdfPCell(new Paragraph("Number of transaction",fontTieuDe3));
            cellTDTransName.setBorderColor(BaseColor.BLACK);
            cellTDTransName.setHorizontalAlignment(Element.ALIGN_CENTER);
            cellTDTransName.setVerticalAlignment(Element.ALIGN_MIDDLE);                    
            tableRevenue.addCell(cellTDTransName);

            PdfPCell cellTDFee = new PdfPCell(new Paragraph("Revenue", fontTieuDe3));
            cellTDFee.setBorderColor(BaseColor.BLACK);
            cellTDFee.setHorizontalAlignment(Element.ALIGN_CENTER);
            cellTDFee.setVerticalAlignment(Element.ALIGN_MIDDLE);                    
            tableRevenue.addCell(cellTDFee);
            long totalRevenue = 0;
            try{
                Connection con = DBConnection.ConnectDb();
                String SQL = "SELECT TO_CHAR(TRANSACTION_DATE, 'yyyy-MM-dd'), COUNT(T.TRANSACTION_ID), SUM(FEE)\n" +
                            "FROM TRANSACTION T JOIN TRANSACTION_TYPE TP ON T.TRANSACTION_TYPE_ID = TP.TRANSACTION_TYPE_ID\n" +
                            "WHERE ACCOUNT_ID = ?\n" +
                            "AND (TRANSACTION_DATE BETWEEN TO_DATE(?, 'dd/MM/yyyy') AND TO_DATE(?, 'dd/MM/yyyy HH24:MI:SS'))\n" +
                            "GROUP BY TO_CHAR(TRANSACTION_DATE, 'yyyy-MM-dd')\n" +
                            "ORDER BY TO_CHAR(TRANSACTION_DATE, 'yyyy-MM-dd')";
                PreparedStatement prest = con.prepareStatement(SQL);
                prest.setLong(1, dtoAccount.getId());
                String fDate = df.format(fromDate);
                String tDate = df.format(toDate) + " 23:59:00";
                prest.setString(2, fDate);
                prest.setString(3, tDate);
                ResultSet rs = prest.executeQuery();
                int TT = 1;
                
                while(rs.next())
                {
                    // Tạo bảng ở đây                  
                    PdfPCell cellTT = new PdfPCell(new Paragraph(String.valueOf(TT), fontNoiDung3));
                    cellTT.setHorizontalAlignment(Element.ALIGN_CENTER);
                    cellTT.setVerticalAlignment(Element.ALIGN_MIDDLE);
                    cellTT.setMinimumHeight(20);
                    tableRevenue.addCell(cellTT);
                                       
                    PdfPCell cellDate = new PdfPCell(new Paragraph(rs.getString(1), fontNoiDung3));                     
                    cellDate.setHorizontalAlignment(Element.ALIGN_CENTER);
                    cellDate.setVerticalAlignment(Element.ALIGN_MIDDLE);
                    tableRevenue.addCell(cellDate);
                    
                    PdfPCell cellTransID = new PdfPCell(new Paragraph(rs.getString(2), fontNoiDung3));
                    cellTransID.setHorizontalAlignment(Element.ALIGN_CENTER);
                    cellTransID.setVerticalAlignment(Element.ALIGN_MIDDLE);
                    tableRevenue.addCell(cellTransID);

                    PdfPCell cellFee = new PdfPCell(new Paragraph(String.format("%,d",rs.getLong(3)) + " VND", fontNoiDung3));
                    cellFee.setHorizontalAlignment(Element.ALIGN_CENTER);                    
                    cellFee.setVerticalAlignment(Element.ALIGN_MIDDLE);
                    tableRevenue.addCell(cellFee);
                    
                    
                    TT++;
                    totalRevenue += rs.getLong(3);
                    // Tạo dataset
                    String date = rs.getString(1); // format: 0123-56-89 (yyyy-mm-dd)
                    String strDate = date.charAt(8) + "" + date.charAt(9) + "/" + date.charAt(5) + ""+ date.charAt(6);
                    dataset.setValue(rs.getLong(3), "Doanhthu", strDate);
                } 
                con.close();
            }
            catch(SQLException e)
            {
                JOptionPane.showMessageDialog(null, e);    
            }
            
            PdfPCell cellTA = new PdfPCell(new Paragraph("Total revenue: ", fontTieuDe3));
            cellTA.setColspan(3);
            cellTA.setHorizontalAlignment(Element.ALIGN_RIGHT);
            cellTA.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cellTA.setMinimumHeight(20);
            tableRevenue.addCell(cellTA);
 
            PdfPCell cellTB = new PdfPCell(new Paragraph(String.format("%,d", totalRevenue ) + " VND", fontTieuDe3));
            cellTB.setHorizontalAlignment(Element.ALIGN_CENTER);
            cellTB.setVerticalAlignment(Element.ALIGN_MIDDLE);
            tableRevenue.addCell(cellTB);

            BufferedImage bufferedImageBDDT = createBarChart(dataset, fromDate, toDate);
            Image image = Image.getInstance(writer, bufferedImageBDDT, 1.0f);
            image.setAlignment(Element.ALIGN_CENTER);
            image.scaleAbsolute(500, 300);
            document.add(tableRevenue);
            document.add(image);
//
//            Paragraph prgChart = new Paragraph("III. Chart of revenue from customers", fontTieuDe2);
//            prgChart.setSpacingBefore(10);
//            prgChart.setSpacingAfter(10);
//            document.add(prgChart);
//            document.add(image);
            
            //xac nhan cua nguoi lap bao cao
            PdfPTable tableXN = new PdfPTable(2);
            tableXN.setWidthPercentage(90);           
            tableXN.setSpacingAfter(20);
            
            float[] tableXN_columnWidths = {150, 350};
            tableXN.setWidths(tableXN_columnWidths);
            
            PdfPCell cellXN = new PdfPCell(new Paragraph("", fontTieuDe2));
            cellXN.setBorder(0);
            cellXN.setRowspan(3);
            cellXN.setHorizontalAlignment(Element.ALIGN_CENTER);
            cellXN.setVerticalAlignment(Element.ALIGN_TOP);
            tableXN.addCell(cellXN);
            
            Paragraph prgPrintTime= new Paragraph("Bien Hoa City, " + df7.format(new Date()), fontNoiDung1);
            prgPrintTime.setIndentationLeft(300);
            prgPrintTime.setSpacingBefore(30);
            prgPrintTime.setSpacingAfter(10); 
            document.add(prgPrintTime);
            
            PdfPCell cellEmloyee = new PdfPCell(new Paragraph("                                                   Reporter\n\n\n\n", fontTieuDe2));
            cellEmloyee.setBorder(0);
            cellEmloyee.setHorizontalAlignment(Element.ALIGN_LEFT);
            cellEmloyee.setVerticalAlignment(Element.ALIGN_MIDDLE);            
            tableXN.addCell(cellEmloyee); 
            document.add(tableXN);
            
            
            document.close();
            writer.close();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }

        try
        {
            File file = new File("D:\\JAVA\\"+ filename + ".pdf");
            if(!Desktop.isDesktopSupported())
            {
                System.out.println("Not supported");
                return;
            }
            Desktop desktop = Desktop.getDesktop();
            if(file.exists())
                desktop.open(file);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
    
     public BufferedImage createBarChart(DefaultCategoryDataset dataset, Date fromDate, Date toDate)
    {
        BufferedImage bufferedImage = null;
        try{
            String tDate = df.format(toDate);
            String fDate = df.format(fromDate);
            String title = "Statistical chart of revenue from customer ";
            if(tDate.equals(fDate))
            {
                title += "for " + tDate;
            }
            else
            {
                title += "from " + fDate + " to " + tDate;
            }
            
            JFreeChart chart = ChartFactory.createBarChart(title, "Ngày", "Doanh thu", dataset, PlotOrientation.VERTICAL, false, false, false);
            java.awt.Font fontTieuDe = new java.awt.Font("Tahoma", java.awt.Font.BOLD, 120);
            java.awt.Font fontNoiDung1 = new java.awt.Font("Tahoma", java.awt.Font.PLAIN, 80);
            java.awt.Font fontNoiDung2 = new java.awt.Font("Tahoma", java.awt.Font.ITALIC, 80);
            
            chart.getTitle().setFont(fontTieuDe);
            
            CategoryPlot plot = (CategoryPlot) chart.getPlot();
            ((BarRenderer) plot.getRenderer()).setBarPainter(new StandardBarPainter());
            plot.setBackgroundPaint(Color.white);
            plot.setOutlinePaint(null);
            
//            CategoryItemRenderer renderer = ((CategoryPlot) chart.getPlot()).getRenderer();
//            renderer.setDefaultItemLabelGenerator(new StandardCategoryItemLabelGenerator());
//            renderer.setDefaultItemLabelsVisible(true);
//            ItemLabelPosition position = new ItemLabelPosition(ItemLabelAnchor.OUTSIDE12, TextAnchor.TOP_CENTER);
//            renderer.setDefaultPositiveItemLabelPosition(position);
//            renderer.setDefaultItemLabelFont(fontNoiDung1);
            
            CategoryAxis domainAxis = plot.getDomainAxis();
            domainAxis.setTickLabelFont(fontNoiDung1);
            domainAxis.setLabelFont(fontNoiDung2);
            
            ValueAxis rangeAxis = plot.getRangeAxis();
            rangeAxis.setTickLabelFont(fontNoiDung1);
            rangeAxis.setLabelFont(fontNoiDung2);
            
            bufferedImage = chart.createBufferedImage(5000, 3000);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return bufferedImage;
    }
     
     public void addPageNum(String pdfPath, String outFilePath) {
		PdfReader reader = null;
		PdfStamper stamper = null;
		try {
			 // Create a pdf reading stream
			reader = new PdfReader(pdfPath);
			 // Create a pdfStamper based on a pdfreader. Used to generate a new pdf.
			stamper = new PdfStamper(reader, new FileOutputStream(outFilePath));
			 // This font comes with itext-asian.jar, so there is no need to consider the operating system environment.
                        File filefontNoiDung = new File("C:\\Users\\ACER\\Downloads\\OnlineBankingSystem-SourceCode\\OnlineBankingSystemProject\\src\\DAL\\Report\\fonts\\vuArial.ttf");
			BaseFont bf = BaseFont.createFont(filefontNoiDung.getAbsolutePath(), BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED);
			 // baseFont does not support font style settings. However, font font requires the operating system to support this font will bring porting problems.
			Font font = new Font(bf, 10);
			font.setStyle(Font.BOLD);
			font.getBaseFont();
			 // get wide
			Rectangle pageSize = reader.getPageSize(1);
	        float width = pageSize.getWidth();
			 // Get the page number
			int num = reader.getNumberOfPages();
			for (int i = 1; i <= num; i++) {
				PdfContentByte over = stamper.getOverContent(i);
				
				over.beginText();
				over.setFontAndSize(font.getBaseFont(), 13);
				over.setColorFill(BaseColor.BLACK);
				 // Set the coordinates of the page number in the page
				over.setTextMatrix((int) width - 55, 15); 
 // over.setTextRenderingMode(1); // set font bold
				 over.showText(" " + i + " ");
				over.endText();
				over.stroke();
			}
			stamper.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (reader != null) {
				reader.close();
			}
		}
	}

}



    