package _11_test_260127.memberProject.memberProject.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;

import static java.sql.DriverManager.getConnection;

public class _1_Insert_Ex {
    public static void main(String[] args) throws ClassNotFoundException {
        System.out.println("연습용으로 members 테이블에 데이터 쓰기 예제 확인. ");
            //드라이버 로딩.
        // 순서1, 드라이버 로드, 자바 -> 오라클로 쿼리 를 전달하기 위한 도구 모음집을 메모리에 로드
            Class.forName("oracle.jdbc.driver.OracleDriver");

            insertMember("이상용jdbc","jdbc2@naver.com","1234",20);
    }

    // 기능 추가
//    public void insertMember(MemberDTO member) {
    // 연습용, 직접 email, password, name, age
    public static void insertMember(String name, String email, String password, int age) {
        // 순서2, 작업할 sql 문장을 만들어야 함.
        String sql = "INSERT INTO members(name, email, password, age) VALUES(?, ?, ?, ?)";

        // try-with-resources 문법: 자동으로 close 해줌
        // 순서3, 연결하기 위한 도구(Connection conn)를 이용해야하고,
        // 재사용 준비물 :1) url 주소 (현재는 로컬의 도커로 접근) 2)유저 3) 패스워드
        try (Connection conn = getConnection(
                "jdbc:oracle:thin:@localhost:1521:xe",
                "scott",
                "tiger"
        );

             // 순서4, sql를 전달하는 도구 : PreparedStatement pstmt,
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            // ?(위치홀더) 순서대로 값 세팅
//            pstmt.setString(1, member.getName());
//            pstmt.setString(2, member.getEmail());
//            pstmt.setString(3, member.getPassword());
//            pstmt.setInt(4, member.getAge());

            // 순서5, sql ? 와일드 카드 문자, 4개, ? ? ? ?  VALUES(?, ?, ?, ?)
            pstmt.setString(1, name);
            pstmt.setString(2, email);
            pstmt.setString(3, password);
            pstmt.setInt(4, age);

            // 순서6, 실행하기.
            int result = pstmt.executeUpdate(); // 성공한 행의 개수 리턴
            System.out.println(result + "행이 추가되었습니다.");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
