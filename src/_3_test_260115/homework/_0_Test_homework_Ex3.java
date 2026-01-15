package _3_test_260115.homework;

public class _0_Test_homework_Ex3 {
    public static void main(String[] args) {
//        3. (메서드 & 최대값 찾기)
//        정수형 배열을 매개변수(파라미터)로 받아,
//        배열 안에 있는 숫자 중 **가장 큰 수(Max Value)**를 찾아
//        반환하는 메서드 getMaxValue를 작성하세요.
//        Main 메서드에서 샘플 배열을 만들어 테스트하세요.
//        String[] numbers = {100,200,300};
//        힌트)
//// 배열의 첫 번째 값을 기준값으로 설정
//        int maxVal = arr[0];
//        if (num > maxVal) { // 현재 꺼낸 숫자가 기준값보다 크다면
//            maxVal = num;   // 기준값을 현재 숫자로 변경
//        }

        // 2 함수 사용.
        // 함수이름(숫자를 요소로 하는 배열)
        int[] numbers = {100,200,300};
        // 메서드를 호출하면, 가장 큰숫자를 결과로 받아 오니, 우리는 이 숫자를 받을 변수를 지정하면됨.

        int max = getMaxValue(numbers);

        // 3. 출력
        System.out.println("배열에서 가장 큰수 : " + max);


    } // main  메서드 닫기

    //        정수형 배열을 매개변수(파라미터)로 받아,
//        배열 안에 있는 숫자 중 **가장 큰 수(Max Value)**를 찾아
//        반환하는 메서드 getMaxValue를 작성하세요.
    // int[] arr : 크기 3개.

    // 함수 정의
    public static int getMaxValue(int[] arr){
        //        int[] numbers = {100,200,300};

        //  함수를 사용 getMaxValue(numbers)
        //  int[] arr = numbers;

        // 1. 임시로 담을 최대 숫자 변수 , 일단, 임시로, 배열의 첫번째 숫자 값을 사용.
        int maxVal = arr[0];
        // 2. 비교, 배열의 모든 요소를 비교해서, 최대값을 찾아야함.
        // 1) maxVal : 100,
        // 2) num(200) > maxVal(100),  -> maxVal 기존값 대신에 새로운 값으로 대체, maxVal = num
        // 3) num(300) > maxVal(200),  -> maxVal 기존값 대신에 새로운 값으로 대체, maxVal = num
        // 최종 : maxVal : 300
        // 반복문을 이용하기.
        for (int num : arr) {
            // 1) num : 100, maxVal : 100 , 100 > 100 : 거짓, maxVal = num; 실행안함
            // 2) num : 200, maxVal : 100 , 200 > 100 : 참, maxVal = num; 실행함
            // 3) num : 300, maxVal : 200 , 300 > 200 : 참, maxVal = num; 실행함
            // 최종 : maxVal : 300
            if(num > maxVal) {
                maxVal = num;
            }

        }
        // 최종 : maxVal : 300, 함수의 결괏값으로 반환
        return  maxVal;
    } // getMaxValue, 함수 닫기
} // 클래스 닫기
