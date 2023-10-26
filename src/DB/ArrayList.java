package DB;
import java.sql.*;
import java.util.Scanner;


class ArrayListData{
    String name,address;
    int number;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }
}

class SQLC{
    private static Connection conn;
    private static PreparedStatement pstmt;
    SQLC() throws SQLException {
        conn =
                DriverManager.getConnection("jdbc:mysql://localhost:3306/testone","root","1234");
    }
    void DataInsert(ArrayListData d){
        try{
            pstmt=conn.prepareStatement("INSERT INTO phone VALUES (?,?,?)");
            pstmt.setString(1,d.getName());
            pstmt.setInt(2,d.getNumber());
            pstmt.setString(3,d.getAddress());
            pstmt.executeUpdate();
        }
        catch (SQLException e){
            e.printStackTrace();
        }
    }
    void selectAll() throws SQLException {
        String sql = "SELECT*FROM phone;";
        pstmt = conn.prepareStatement(sql);
        ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                System.out.print(rs.getString("name") + "/");
                System.out.print(rs.getInt("phoneNumber") + "/");
                System.out.print(rs.getString("address"));
                System.out.println();
            }
        }
    void findSelect(String name) throws SQLException {
        String sql = "SELECT*FROM phone where name =?;";
        pstmt =conn.prepareStatement(sql);
            pstmt.setString(1,name);
            boolean check= true;
        ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                if (rs.getString("name").equals(name)) {
                    check=false;
                    System.out.print(rs.getString("name") + "/");
                    System.out.print(rs.getInt("phoneNumber") + "/");
                    System.out.print(rs.getString("address"));
                    System.out.println();
                }

            }
            if(check){
                System.out.println("전화번호부에 없습니다.");
            }
        }
    void deleteSelect(String name) throws SQLException {
        String sql = "delete from phone where name = ?;";
        pstmt = conn.prepareStatement(sql);
        pstmt.setString(1,name);
        if(pstmt.executeUpdate()==0){
            System.out.println("없는 이름입니다.");
        }

    }
}
class InputClass
{
    ArrayListData valueReturn(){
        ArrayListData d = new ArrayListData();
        Scanner scS = new Scanner(System.in);
        Scanner scI = new Scanner(System.in);
        System.out.println("이름 입력: ");
        d.setName(scS.nextLine());
        System.out.println("전화번호 입력: ");
        d.setNumber(scI.nextInt());
        System.out.println("주소 입력: ");
        d.setAddress(scS.nextLine());
        return d;
    }
    String findString(){
        Scanner scS = new Scanner(System.in);
        System.out.println("이름 입력: ");
        return scS.next();
    }
}
public class ArrayList {
    public static void main(String[] args) throws SQLException {
        Scanner sc = new Scanner(System.in);
        SQLC sq = new SQLC();
        InputClass ic = new InputClass();
        while(true){
            System.out.println("1. 입력 2. 검색 3.삭제 4.출력 5. 종료");
            int num=sc.nextInt();
            if(num==1){
                sq.DataInsert(ic.valueReturn());
            }
            else if(num==2){
                sq.findSelect(ic.findString());
            }
            else if(num==3){
                sq.deleteSelect(ic.findString());
            }
            else if(num==4){
                sq.selectAll();
            }
            else if(num==5){
                System.out.println("프로그램을 종료합니다.");
                break;
            }
            else{
                System.out.println("잘못된 입력입니다.");
            }
        }
    }
}
