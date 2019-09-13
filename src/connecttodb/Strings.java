package connecttodb;

/**
 *
 * @author Dmitry
 */
public class Strings {

    private String subName;
    private String findVar;
    private String[] sqlVarColumns;
    private String tabName;
    private String sqlFindColumn;

    public Strings(String subName, String findVar, String[] sqlVarColumns, String tabName, String sqlFindColumn) {
        this.subName = subName;
        this.findVar = findVar;
        this.sqlVarColumns = sqlVarColumns;
        this.tabName = tabName;
        this.sqlFindColumn = sqlFindColumn;
    }
    
    
    
    public String getSubEmpty (){
        return getSubName() + getInitVars() + getConnectionString() + getConnection() + "\n	'### TO DO: punch questions\n\n" + getSubEnd();
    }

    public String getFindVar() {
        return findVar;
    }

    public void setFindVar(String findVar) {
        this.findVar = findVar;
    }

    public String[] getSqlVarColumns() {
        return sqlVarColumns;
    }

    public void setSqlVarColumns(String[] sqlVarColumns) {
        this.sqlVarColumns = sqlVarColumns;
    }

    public String getTabName() {
        return tabName;
    }

    public void setTabName(String tabName) {
        this.tabName = tabName;
    }

    public String getSqlFindColumn() {
        return sqlFindColumn;
    }

    public void setSqlFindColumn(String sqlFindColumn) {
        this.sqlFindColumn = sqlFindColumn;
    }

    private String getSubName() {
        return "Sub " + subName + "(" + findVar + ", IOM)\n";
    }

    private String getSubEnd() {
        return "End Sub";
    }

    private String getInitVars() {
        String str = "	Dim objConnection, objRecordSet, sDatabase\n\n	Dim ";
        for (String sqlVar : sqlVarColumns) {
            str += "sql" + sqlVar.toUpperCase().charAt(0) + sqlVar.substring(1) + ", ";
        }
        str = str.substring(0, str.length() - 2);
        str += "\n";

        return str;
    }

    private String getConnectionString() {
        return ""
                + "	Set objConnection = CreateObject (\"ADODB.Connection\" )\n"
                + "	Set objRecordset = CreateObject (\"ADODB.Recordset\" )\n"
                + "	\n"
                + "	'Set database connection string.\n"
                + "	sDatabase = \"" + service.getSDB() + "\"\n";
    }

    private String getConnection() {
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
            str += "		sql" + sqlVar.toUpperCase().charAt(0) + sqlVar.substring(1) + " = NULL\n";
        }
        str += "	End If\n	objRecordset.Close()\n";
        return str;
    }
}
