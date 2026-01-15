package _3_test_260115.homework;

import java.util.Scanner;

public class _0_Test_homework_Ex1 {
    public static void main(String[] args) {
//        응용예시 안내, 복습 및 과제
//        1. (1차원 배열 & 누적 연산)
//        사용자로부터 정수 5개를 입력받아 배열에 저장하세요.
//        그 후 배열에 저장된 모든 숫자의
//**합계(Sum)**와 **평균(Average)**을 계산하여 출력하는 코드를 작성하세요.

        // 1. 스캐너 도구 이용
        Scanner scanner = new Scanner(System.in);

        // 2. 입력 받은 숫자를 임시로 저장할 배열 생성. 공간5개인, 빈배열.
        int[] numbers = new int[5];
        // 2-2. 임시로 누적 합계를 저장할 변수
        int sum = 0;

        // 3. 입력 받을 문구 작성.
        System.out.println("숙제 실습1 : 정수 5개를 입력해주세요, 예시) 10 20 30 40 50");

        // 4. 사용자가 입력한 숫자를 1)스캐너를 통해서 가져오고,

        // 반복 작업. 반복문 이용.
//        numbers[0] = scanner.nextInt();// 숫자를 공백 기준으로 가져오기
//        numbers[1] = scanner.nextInt();// 숫자를 공백 기준으로 가져오기
//        numbers[2] = scanner.nextInt();// 숫자를 공백 기준으로 가져오기
//        numbers[3] = scanner.nextInt();// 숫자를 공백 기준으로 가져오기
//        numbers[4] = scanner.nextInt();// 숫자를 공백 기준으로 가져오기
        for (int i = 0; i < numbers.length; i++) {
            // 1)i<5 VS 2)i < numbers.length :
            // 2) 선호, 왜? 지금은 배열의 크기가 일정, 만약, 변하면?
            // 동적으로 알아서, 크기를 저장하는게 더 나음.
            // 2)-2 임시 빈 배열에 담기
            numbers[i] = scanner.nextInt();
//            sum += numbers[0];
//            sum += numbers[1];
//            sum += numbers[2];
//            sum += numbers[3];
//            sum += numbers[4];
            // 3) 각 배열의 요소들의 누적 합계를 구하기.
            sum += numbers[i];
        } //for 닫기, sum 이라는 변수에는 누적합계 있음.

        // 5. 평균 계산. 총합/배열의 갯수, 평균이 소숫점이 나올수 있어서, 타입을 실수 타입으로 변경.
        // (double) : 타입을 변경하는 캐스팅 연산자.
        double avg = (double) sum / numbers.length;

        // 6. 결과 출력.
        System.out.println("입력한 숫자의 합계 : " + sum);
        System.out.println("입력한 숫자의 평균 : " + avg);


    }
}
