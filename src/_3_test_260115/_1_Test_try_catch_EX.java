package _3_test_260115;

public class _1_Test_try_catch_EX {
    public static void main(String[] args) {
//        기본예시
//        1
//        0으로 나누는 예외 처리

// 이 유형을 많이 보게 됨. !!90%이상.
//```java
            try {
                System.out.println("실행1: try 구문");
                int result = 10 / 0; // 실패하는 예시, 실행 순서 1,2,3
//                int result = 10 / 2; // 실패하는 예시, 실행 순서 1, 3
            } catch (ArithmeticException e) { // 작은 범위 처리.
                // 현재는 뭐가 문제인지를 여부를 정확히 알고 있으니, 작은 범위로 처리.
                // 뭐가 문제가 모를 경우, 1) 각각을 세부적으로 모두 정의 2) 큰 범위로 정의
                System.out.println("실행2: catch 구문");
                System.out.println("0으로 나눌 수 없습니다.");
            } finally {
                System.out.println("실행3 finally  구문" +
                        "__try 에서 예외가 발생 하건, 안하건, 무조건 실행되는 구간");
            }
    }
}
