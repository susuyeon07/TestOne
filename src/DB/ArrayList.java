package DB;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;


class Data{
    String a,c;
    int b;

    public String getA() {
        return a;
    }

    public void setA(String a) {
        this.a = a;
    }

    public String getC() {
        return c;
    }

    public void setC(String c) {
        this.c = c;
    }

    public int getB() {
        return b;
    }

    public void setB(int b) {
        this.b = b;
    }
}

class SQLC{
    private static Connection conn;
    private static PreparedStatement pstmt;
    SQLC() throws SQLException {
        conn =
                DriverManager.getConnection("jdbc:mysql://localhost:3306/database"
                        ,"root","1234");
    }
    void DataInsert(Data d){
        try{
            pstmt=conn.prepareStatement("INSERT INTO arraylist VALUES (?,?,?)");
            pstmt.setString(1,d.getA());
            pstmt.setInt(2,d.getB());
            pstmt.setString(3,d.getC());



        }
    }
}


public class ArrayList {
    public static void main(String[] args) {

    }
}
