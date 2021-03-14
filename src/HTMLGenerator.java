import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class HTMLGenerator {
    private String HTMLCode;
    private int numColumn;

    public HTMLGenerator(){
        HTMLCode = "<!DOCTYPE html>\n        <html>\n        <head>\n          <style>\n            * {\n              box-sizing: border-box;\n              -webkit-box-sizing: border-box;\n              -moz-box-sizing: border-box;\n            }\n            body {\n              font-family: Helvetica;\n              -webkit-font-smoothing: antialiased;\n              background: rgba(71, 147, 227, 1);\n            }\n            h2 {\n              text-align: center;\n              font-size: 18px;\n              text-transform: uppercase;\n              letter-spacing: 1px;\n              color: white;\n              padding: 30px 0;\n            }\n            /* Table Styles */\n            \n            .table-wrapper {\n              margin: 10px 70px 70px;\n              box-shadow: 0px 35px 50px rgba(0, 0, 0, 0.2);\n            }\n            .fl-table {\n              border-radius: 5px;\n              font-size: 12px;\n              font-weight: normal;\n              border: none;\n              border-collapse: collapse;\n              width: 100%;\n              max-width: 100%;\n              white-space: nowrap;\n              background-color: white;\n            }\n            .fl-table td,\n            .fl-table th {\n              text-align: center;\n              padding: 8px;\n            }\n            .fl-table tr:hover {\n              background-color: #f2f2ff;\n            }\n            .fl-table tr:nth-child(even):hover {\n              background-color: #f2f2ff;\n            }\n            .fl-table td {\n              border-right: 1px solid #f8f8f8;\n              font-size: 12px;\n            }\n            .fl-table thead th {\n              color: #ffffff;\n              background: #4FC3A1;\n            }\n            .fl-table thead th:nth-child(odd) {\n              color: #ffffff;\n              background: #324960;\n            }\n            .fl-table tr:nth-child(even) {\n              background: #F8F8F8;\n            }\n            /* Responsive */\n            @media (max-width: 767px) {\n              .fl-table {\n                display: block;\n                width: 100%;\n              }\n              .table-wrapper:before {\n                content: \"Scroll horizontally >\";\n                display: block;\n                text-align: right;\n                font-size: 11px;\n                color: white;\n                padding: 0 0 10px;\n              }\n              .fl-table thead,\n              .fl-table tbody,\n              .fl-table thead th {\n                display: block;\n              }\n              .fl-table thead th:last-child {\n                border-bottom: none;\n              }\n              .fl-table thead {\n                float: left;\n              }\n              .fl-table tbody {\n                width: auto;\n                position: relative;\n                overflow-x: auto;\n              }\n              .fl-table td,\n              .fl-table th {\n                padding: 20px .625em .625em .625em;\n                height: 60px;\n                vertical-align: middle;\n                box-sizing: border-box;\n                overflow-x: hidden;\n                overflow-y: auto;\n                width: 120px;\n                font-size: 13px;\n                text-overflow: ellipsis;\n              }\n              .fl-table thead th {\n                text-align: left;\n                border-bottom: 1px solid #f7f7f9;\n              }\n              .fl-table tbody tr {\n                display: table-cell;\n              }\n              .fl-table tbody tr:nth-child(odd) {\n                background: none;\n              }\n              .fl-table tr:nth-child(even) {\n                background: transparent;\n              }\n              .fl-table tr td:nth-child(odd) {\n                background: #F8F8F8;\n                border-right: 1px solid #E6E4E4;\n              }\n              .fl-table tr td:nth-child(even) {\n                border-right: 1px solid #E6E4E4;\n              }\n              .fl-table tbody td {\n                display: block;\n                text-align: center;\n              }\n            }\n          </style>\n        </head>\n";
    }

    public void setCaption(String caption) {
        HTMLCode = HTMLCode + "<h2>" + caption + "</h2>\n        <div class=\"table-wrapper\">\n          <table class=\"fl-table\">\n";
    }

    public void setHeader(List<String> header) {
        numColumn = header.size();

        HTMLCode = HTMLCode + "<thead>\n    <tr>\n";
        for (String element : header) {
            HTMLCode = HTMLCode + "      <th>" + element + "</th>\n";
        }
        HTMLCode = HTMLCode + "    </tr>\n  </thead>\n  <tbody>\n";
    }

    public void setRow(List<String> elements) {
        if(numColumn != elements.size()) {
            throw new IllegalArgumentException("Number of columns is different than list size");
        }
        HTMLCode = HTMLCode + "<tr>\n";
        for(String e : elements) {
            HTMLCode = HTMLCode + "        <td>" + e + "</td>\n";
        }
        HTMLCode = HTMLCode + "       </tr>\n";
    }

    public void end(){
        HTMLCode = HTMLCode + "  </tbody>\n        </table>\n        </div>\n                </html>\n";
    }

    public void printHTML(boolean useFile, String fileName) {
        if(useFile) {
            try {
                File dir = new File("./html");
                boolean created = dir.mkdir();
                FileWriter file = new FileWriter("./html/" + fileName + ".html");
                PrintWriter p = new PrintWriter(file);
                p.println(HTMLCode);
                file.close();
                System.out.println("|----------------------------------------------------|");
                System.out.println("      Le fichier " + fileName + ".html est créé       ");
                System.out.println("|----------------------------------------------------|");
            }
            catch(IOException e) {
                e.printStackTrace();
            }
        }
        else {
            System.out.println(HTMLCode);
        }
    }
}
