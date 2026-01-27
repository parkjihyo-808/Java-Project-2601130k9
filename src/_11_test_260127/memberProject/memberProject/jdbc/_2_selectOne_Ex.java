package _11_test_260127.memberProject.memberProject.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import static java.sql.DriverManager.getConnection;

public class _2_selectOne_Ex {
    public static void main(String[] args) throws ClassNotFoundException {
        System.out.println("연습용으로 members 테이블에 데이터 조 예제 확인. ");
        //드라이버 로딩.
        // 순서1, 드라이버 로드, 자바 -> 오라클로 쿼리 를 전달하기 위한 도구 모음집을 메모리에 로드
        // 연습용으로, 메인 메서드에서, 예외를 처리하고,
        // 원래는 try catch 구문 작업 하면된다 .or try with resources 등으로
        // 고정1
        Class.forName("oracle.jdbc.driver.OracleDriver");

        //순서2, 실제 조회 기능 사용하기.
        searchMember("jdbc@naver.com");
    }

    // 한명 조회 기능 추가.
    public static void searchMember(String email) {
        //순서3, 조회할 sql 구문 작성,
        String sql = "SELECT * FROM members WHERE email = ?";

        // 회원 추가 기능에서, 같은 코드 재사용.
        // 순서4, try with resources 구문에, 연결 객체 도구를 이용하고,
        try (
                //1, 고정2
                Connection conn = getConnection(
                "jdbc:oracle:thin:@localhost:1521:xe",
                "scott",
                "tiger");// 회원 추가 기능에서, 같은 코드 재사용.

                //2
                // 순서5, sql 를 전달하는 기능의 클래스를 이용하고,
                PreparedStatement pstmt = conn.prepareStatement(sql)
        ) {

            // 순서6, 전달할 sql 에서, 검색할 이메일 정보를 입력,
            pstmt.setString(1, email);
            // 순서7, 오라클 서버에 쿼리 문을 전달해서, 해당 결과 테이블의 내용을 받아오고,
            ResultSet rs = pstmt.executeQuery();

            // 순서8, 받아온 내용이 테이블 형식으로 되어 있고,
            //  ResultSet rs  : 테이블 구조 형식.
            if (rs.next()) { // 포인터를 다음 행으로 이동, 데이터가 있으면 true
                // 순서9, 받아온 테이블에서, 각컬럼의 값들을 하나씩 읽어오기.
                String name = rs.getString("name");
                String email2 = rs.getString("email");
                String password = rs.getString("password");
                int age = rs.getInt("age");
                System.out.println("검색 결과 -> 이름: " + name + ", 나이: " + age);
                System.out.println("이메일 : " + email2);
                System.out.println("password : " + password);
                // 참고로 try catch finally  는 사용할 도구를 반납해야하는데,
                // 현재는 try with resources 문법을 사용해서, 자동 반납을 한다. (현재 이설정)
            } else {
                System.out.println("해당 이메일의 회원이 없습니다.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
