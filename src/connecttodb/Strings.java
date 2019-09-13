package connecttodb;

/**
 *
 * @author Dmitry
 */
public class Strings {

    public String getSubName(String name, String findVar) {
        return "Sub " + name + "(" + findVar + ", IOM)\n";
    }

    public String getSubEnd() {
        return "End Sub";
    }

    public String getInitVars(String[] sqlVarColumns) {
        String str = "	Dim objConnection, objRecordSet, sDatabase\n\n	Dim ";
        for (String sqlVar : sqlVarColumns) {
            str += "sql" + sqlVar.toUpperCase().charAt(0) + sqlVar.substring(1) + ", ";
        }
        str = str.substring(0, str.length() - 2);
        str += "\n";

        return str;
    }

    public String getConnectionString() {
        return ""
                + "	Set objConnection = CreateObject (\"ADODB.Connection\" )\n"
                + "	Set objRecordset = CreateObject (\"ADODB.Recordset\" )\n"
                + "	\n"
                + "	'Set database connection string.\n"
                + "	sDatabase = \"Provider=SQLOLEDB.1;Initial Catalog=surveydatalookup;Data Source=tcp:ram97st6l6.database.windows.net,1433;;Persist Security Info=False;User ID=surveydatalookupuser@ram97st6l6;Password=M6UHsdb+$?@!EGAD;MultipleActiveResultSets=False;Encrypt=True;TrustServerCertificate=False;Connection Timeout=30\"\n";
    }

    public String getConnection(String tabName, String[] sqlVarColumns, String sqlFindColumn, String findVar) {
        String str = "	objConnection.Open(sDatabase)\n	objRecordset.Open( \"select ";

        for (String sqlVar : sqlVarColumns) {
            str += sqlVar + ", ";
        }
        str = str.substring(0, str.length() - 2) + " "
                + "from dbo." + tabName + ""
                + " where " + sqlFindColumn + " = '\" + " + findVar + " + \"'\" , objConnection)\n\n"
                + "	If objRecordSet.BOF = False Or objRecordSet.EOF = False Then\n";
        for (String sqlVar : sqlVarColumns) {
            str += "		sql" + sqlVar.toUpperCase().charAt(0) + sqlVar.substring(1) + " = objRecordSet.Fields[\"" + sqlVar + "\"].Value\n";
        }
        str += "	Else\n";
        for (String sqlVar : sqlVarColumns) {
            str += "		sql" + sqlVar.toUpperCase().charAt(0) + sqlVar.substring(1) + " = = NULL\n";
        }
        str += "	End If\n	objRecordset.Close()\n";
        return str;
    }
}
