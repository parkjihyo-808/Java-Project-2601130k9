package _11_test_260127.memberProject.memberProject.ui;

import _11_test_260127.memberProject.memberProject.model._3_MemberBase;
import _11_test_260127.memberProject.memberProject.service._3_MemberService;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;

// 260127_리팩토링_코드정리_순서10
// 화면 작업
public class _3_MemberUI extends JFrame {
    // 서비스 클래스의 도움을 받기. 서비스 도구를 가져오기.
    private _3_MemberService service;

    //UI 컴포넌트 재사용.
    private JTextArea displyArea; // 결과 출력을 위한 텍스트 영역.
    private JPanel buttonPanel; // 버튼들이 들어갈 패널
    private JLabel statusLabel; // 현재 로그인 상태 표시
    private JButton btnJoin, btnList, btnLoginLogout, btnEdit, btnSearch, btnExit, btnDelelte;

    // 생성자 만들기. 서비스 클래스의 도움을 받으면서, 생성자 호출.
    // 생성자 주입 기법, 화면에서, 만들어둔, 서비스 기능을 사용가능함.
    public _3_MemberUI(_3_MemberService service) {
        // 자식을 생성자를 초기화 하기전에, 반드시 부모 생성자를 호출해야한다.
        super("회원 관리 시스템 ver2.3 리팩토링");
        this.service = service;

        // UI 구성, 재조립, 화면 그리기 작업,
        // 미구현
        // 260127_리팩토링_코드정리_순서23
        initUI();

        setSize(600, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        // 화면 중앙 배치
        setLocationRelativeTo(null);
    }

    // 260127_리팩토링_코드정리_순서11
    private void initUI() {
        setLayout(new BorderLayout()); // 동,서,남,북, 중앙 배치 관리자.
        statusLabel = new JLabel("로그인 상태 : 로그아웃 됨", SwingConstants.CENTER);
        statusLabel.setFont(new Font("맑은 고딕", Font.BOLD, 14));
        statusLabel.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
        add(statusLabel, BorderLayout.NORTH);

        displyArea = new JTextArea();
        displyArea.setEditable(false);// 수정 못하게 방지
        displyArea.setFont(new Font("Monospaced", Font.PLAIN, 14));
        add(new JScrollPane(displyArea), BorderLayout.CENTER);

        buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(2, 3, 5, 5));
        btnJoin = new JButton("1 회원가입");
        btnList = new JButton("2 목록조회");
        btnLoginLogout = new JButton("3 로그인"); //초깃값, 로그인을 하면, 로그 아웃으로 보일 예정.
        btnEdit = new JButton("4 회원수정");
        btnSearch = new JButton("5 회원검색");
        btnDelelte = new JButton("6 회원탈퇴");
        btnExit = new JButton("7 종료");

        btnList.addActionListener(new ActionHandler());
        btnJoin.addActionListener(new ActionHandler());
        btnLoginLogout.addActionListener(new ActionHandler());
        btnEdit.addActionListener(new ActionHandler());
        btnSearch.addActionListener(new ActionHandler());
        btnDelelte.addActionListener(new ActionHandler());
        btnExit.addActionListener(new ActionHandler());

        buttonPanel.add(btnJoin);
        buttonPanel.add(btnList);
        buttonPanel.add(btnLoginLogout);
        buttonPanel.add(btnEdit);
        buttonPanel.add(btnSearch);
        buttonPanel.add(btnDelelte);
        buttonPanel.add(btnExit);

        add(buttonPanel, BorderLayout.SOUTH);
//        updateButtonState();
    }

    // 260127_리팩토링_코드정리_순서12
    // printLog 정의 가져오기
    private void printLog(String msg) {
        displyArea.append(msg + "\n");
        // 스크롤 하단 이동 기능.
        displyArea.setCaretPosition(displyArea.getDocument().getLength());
    }

    // 260127_리팩토링_코드정리_순서13
    // updateButtonState 정의 가져오기
    private void updateButtonState() {
        // 서비스에서, 로그인한 유저 가져오기.
        _3_MemberBase loggedInMember = service.getLoggedInMember();

        if (loggedInMember != null) { // 로그인이 된 경우 -> 로그아웃 해야하고
            // 라벨 : 로그아웃이 보이면 되고
            statusLabel.setText("로그인 중 : " + loggedInMember.getEmail() + "(" +
                    loggedInMember.getName() + ")");
            // 버튼에 라벨 변경,
            btnLoginLogout.setText("3.로그아웃");
            // 로그인이 되었을 경우, 수정이 가능함을 암시, 버튼을 활성화.
            btnEdit.setEnabled(true);
            btnSearch.setEnabled(true);
            btnList.setEnabled(true);

        } else {// 로그인이 안된 경우 -> 로그인 해야하고
            // 라벨 : 로그인이 보이면 되고
            statusLabel.setText("로그인 상태 : 로그아웃 됨");
            btnLoginLogout.setText("3. 로그인");
            // 로그아웃이 되었을 경우, 수정이 불가능함을 암시, 버튼을 비활성화.
            btnEdit.setEnabled(false);
            btnSearch.setEnabled(false);
            btnList.setEnabled(false);
        }
    }
    // 260127_리팩토링_코드정리_순서14
    // ActionHandler 정의 가져오기
    private class ActionHandler implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            // 버튼이 많아요, 각 버튼의 모양에 따라서, 각 기능을 붙이기.
            // 기존: 콘솔에서, 입력된 내용을 가져와서 사용. 가져오는 방법 다 동일.
            // 변경: 화면에서, 입력된 내용을 가져와서 사용. 가져오는 방법 다름.

            // 입력 대상이 무엇인지 분별하기. 클릭 하는 버튼 요소를 분별하기.
            // Object: 모든 클래스의 부모다, 최종 클래스, 끝판대장.
            Object source = e.getSource();

            // 클릭 한 요소를 일단, 다 받을수 있는 Object 받아두고,
            // 그리고, 각 요소가 무엇인지 정확히 분기.
            if (source == btnJoin) {
                // 아직 기능은 미구현, 메서드 명만 표기.
                // 260123_화면_스윙_변경__순서9-2
                handleJoin();
            } else if (source == btnList) {
                handleList();
            } else if (source == btnLoginLogout) {
                // 260126_화면버전_기능추가_로그인_로그아웃_순서8
                System.out.println("btnLoginLogout 버튼에서 클릭시 감지 됨 확인");
                System.out.println("handleLoginLogout 호출전");
                handleLoginLogout();
                System.out.println("handleLoginLogout 호출후");
            } else if (source == btnEdit) {
                // 260126_화면버전_기능추가_회원수정_순서2
                handleEdit();
            } else if (source == btnSearch) {
                // 260126_화면버전_기능추가_회원검색_순서2
                handleSearch();
            } else if (source == btnDelelte) {
                // 260126_화면버전_기능추가_회원삭제_순서4
                handleDelete();
            } else if (source == btnExit) {
                // 260126_화면버전_기능추가_종료_순서2
                System.exit(0);
            }
        } //actionPerformed 닫기
    } //ActionHandler 닫기


    // 260127_리팩토링_코드정리_순서15
    // 각 기능들 가져오기.
    // 1. 회원 가입
    private void handleJoin() {
        JTextField nameField = new JTextField();
        JTextField emailField = new JTextField();
        JPasswordField passField = new JPasswordField();
        JTextField ageField = new JTextField();

        Object[] message = {
                "이름 : ", nameField,
                "이메일 : ", emailField,
                "패스워드 : ", passField,
                "나이 : ", ageField
        };

        int option = JOptionPane.showConfirmDialog
                (this, message, "회원가입", JOptionPane.OK_CANCEL_OPTION);

        if (option == JOptionPane.OK_OPTION) {
            // 텍스트 필드에 입력되었던 값을 가져오기.
            String name = nameField.getText();
            String email = emailField.getText();
            String password = new String(passField.getPassword());
            String ageStr = ageField.getText();

            try {
                int age = Integer.parseInt(ageStr);
                // 서비스의 기능중, 회원가입 기능을 이용해서, 작업.
               String result = service.join(name,email,password,age);

               if (result.equals("success")) {
                   printLog(">>> 회원가입 완료 : " + emailField.getText());
               } else {
                   JOptionPane.showMessageDialog(this, "회원가입실패");
               }

            } catch (NumberFormatException ex) {
                // 자바 버전 경고창.
                JOptionPane.showMessageDialog(this, "나이는 숫자만 입력하세요.");
            }

        }
    }

    // 260127_리팩토링_코드정리_순서16
    // 각 기능들 가져오기.
    // 2. 목록조회
    private void handleList() {
        displyArea.setText(""); // 최초에 항상 기존 내용 다지우고, 새로 불러오는 형식.
        printLog("===회원 목록====");
        // 서비스의 기능에서, 현재 회원 가입한 목록을 담을 Map을 불러오기.
        Map<String, _3_MemberBase>  members = service.getMembers();

        if (members.isEmpty()) {
            printLog("가입된 회원이 없습니다.");
        } else {
            // 기존에서 사용하던 Map 순회에서, 출력.
            for (_3_MemberBase member : members.values()) {
                String info = String.format("이름 : %s | 이메일 : %s | 나이 : %d",
                        member.getName(), member.getEmail(), member.getAge());
                printLog(info);
            }
        }
    }
    // 260127_리팩토링_코드정리_순서17
    // 각 기능들 가져오기.
    // 3. 로그인/로그아웃 기능
    private void handleLoginLogout() {
        // 260126_화면버전_기능추가_로그인_로그아웃_순서2
        // 로그인이 된 경우,
        System.out.println("handleLoginLogout 메서드안");
        if (service.getLoggedInMember() != null) {
            // 로그아웃 작업을 해야함.
            service.logout();
            printLog(">>>>로그아웃 되었습니다.");
            // 기능은 미구현, 버튼의 라벨을 변경하는 메서드 이용,
            updateButtonState();

            // 260126_화면버전_기능추가_로그인_로그아웃_순서3
        } else { // 로그인이 안된 경우.
            // 로그인 작업을 해야함.
            // 로그인 입력창 만들기.
            JTextField emailField = new JTextField();
            JPasswordField passwordField = new JPasswordField();
            Object[] message = {
                    "이메일:", emailField,
                    "패스워드:", passwordField
            };
            // 다이얼 로그창에서, 입력란이 2개인데, 이메일, 패스워드 입력란.
            int option = JOptionPane.showConfirmDialog(
                    this, message, "로그인", JOptionPane.OK_CANCEL_OPTION);

            // 260126_화면버전_기능추가_로그인_로그아웃_순서4
            // 로그인 하는 경우, 다이얼로그창에서, 확인을 누를 경우,
            if (option == JOptionPane.OK_OPTION) {
                // 입력창에 입력했던 이메일과, 패스워드를 가지고와서,
                String inputEmail = emailField.getText();
                String inputPassword = passwordField.getText();
                // 메모리상에 있는, members 맵에서,
                // 비교하기, 먼저는 이메일찾고, 있다면, 패스워드 비교해서, 로그인 처리하기.

                if (service.getMembers().containsKey(inputEmail)) {
                    // 맵안에 있는 회원들중, 로그인하려는 멤버의 객체를 , 이메일로 가져오기
                    // 키 : 이메일, 값: 회원정보가 들어있는 객체,
                    _3_MemberBase member = service.getMembers().get(inputEmail);
                    if (member.getPassword().equals(inputPassword)) {
                        // 해당 이메일에, 패스워드도 일치하면, 로그인 성공,
                        // 로그인한 유저 정보의 객체를, loggedInMember에 할당하기.
//                        loggedInMember = member;
                        service.login(inputEmail,inputPassword);
                        // 성공 축하 메세지
                        printLog(">>> 로그인 성공!!, " + member.getName() + "님 환영합니다.");
                        // 미구현, 버튼의 로그인, 로그아웃이라는 라벨을 변경을하는 메서드
                        updateButtonState();
                    } else {
                        // 260126_화면버전_기능추가_로그인_로그아웃_순서5
                        JOptionPane.showMessageDialog(this, "패스워드가 틀렸습니다.");
                    }
                } else {
                    // 260126_화면버전_기능추가_로그인_로그아웃_순서6
                    JOptionPane.showMessageDialog(this, "존재하지 않는 이메일입니다..");
                }
            }
        }
    }
    // 260127_리팩토링_코드정리_순서18
    // 각 기능들 가져오기.
    // 4. 회원 수정
    private void handleEdit() {
        // 로그인 체크 후, 로그인시에만, 수정 가능.
        // 260126_화면버전_기능추가_회원수정_순서3-2
        if (service.getLoggedInMember() == null) {
            JOptionPane.showMessageDialog(this, "로그인 후 이용해주세요.");
            return; // 수정 기능 나가기.
        }

        // 260126_화면버전_기능추가_회원수정_순서3-3
        // 화면, 선택 다이얼로그
        String[] options = {"비밀번호", "이름", "나이"};
        int choice = JOptionPane.showOptionDialog(
                this, // 어디에 나타낼것인가? 프레임 창에
                "수정할 항목 선택 해주세요.", // 메세지 내용
                "회원정보변경", // 타이틀 제목바
                JOptionPane.DEFAULT_OPTION, // 옵션 종류
                JOptionPane.QUESTION_MESSAGE, // 메세지 형태 (아이콘)
                null, // 커스텀 아이콘 , 사용안함.
                options, // 선택지 배열, 버튼들.
                options[0]); // 다이얼로그창에 나타 났을 경우, 초깃값 선택을 무엇을 하나요? 비밀번호

        if (choice == -1) { // 닫기/취소
            return;
        }

        String newValue = JOptionPane.showInputDialog(
                this, "새로운 값을 입력하세요:");
        if (newValue == null) {
            return;
        }

        // 상태변수, 수정 했는지 여부
        boolean isUpdated = false;

        // 기존에, 스위치 구분으로, 수정기능 재사용.
        switch (choice) {
            case 0: // 비밀번호 변경
                service.getLoggedInMember().setPassword(newValue);
                isUpdated = true;
                break;
            case 1: // 이름 변경
                service.getLoggedInMember().setName(newValue);
                isUpdated = true;
                break;
            case 2: // 나이 변경, 문자열 -> 숫자 형태로 변환, try ~ catch 사용하기.
                try {
                    int newAge = Integer.parseInt(newValue);
                    service.getLoggedInMember().setAge(newAge);
                    isUpdated = true;

                } catch (NumberFormatException e) {// 숫자 형태로여야 하는데, 아닌경우
                    JOptionPane.showMessageDialog(this, "잘못된 나이 입력입니다.");
                }
                break;
        }

        // 수정 완료 했으면, 파일에 저장 하자.
        if (isUpdated) {
            // 수정된 내용을 파일에 저장.
//            saveMembers(members);
            service.updateMember();
            printLog(">>> 정보가 수정되었습니다.");
            updateButtonState(); // 상단 패널의 업데이트
        }

    }

    // 260127_리팩토링_코드정리_순서19
    // 각 기능들 가져오기.
    // 5. 회원 검색
    private void handleSearch() {
        //  검색 화면 타입 선택
        String[] options = {"이메일 검색", "이름으로 검색"};
        // 다이얼로그 창에서, 옵션을 선택해서, 검색어를 입력 받는 창.
        // 회원 수정 다이얼로그 창 동일 패턴.
        int choice = JOptionPane.showOptionDialog(
                this, // 어디에 나타낼것인가? 프레임 창에
                "검색할 항목 선택 해주세요.", // 메세지 내용
                "회원 검색", // 타이틀 제목바
                JOptionPane.DEFAULT_OPTION, // 옵션 종류, 버튼 형식 사용.
                JOptionPane.QUESTION_MESSAGE, // 메세지 형태 (아이콘)
                null, // 커스텀 아이콘 , 사용안함.
                options, // 선택지 배열, 버튼들.
                options[0]); // 다이얼로그창에 나타 났을 경우, 초깃값 선택을 무엇을 하나요? 비밀번호

        if (choice == -1) { // 닫기/취소
            return;
        }

        // 검색어를 입력 받기.
        String keyword = JOptionPane.showInputDialog(
                this, "검색어를 입력하세요:");
        // 유효성 체크.
        // 검색어가 비어 있다면, 검색 기능 나간다.
        if (keyword == null || keyword.trim().isEmpty()) {
            return;// 검색 기능 나가기.
        }

        // 정상 검색 기능 구현,
        // 이전에 입력 되었던, 검색어를 초기화,
        displyArea.setText("");// 검색 결과를 붙이기 위해서, 이전 내용 모두 삭제,
        printLog(">>> 검색 결과 : " + keyword + " ~~");

        // 기존에 검색 기능에서 사용했던 내용을 재사용.
        //상태 변수 ,
        boolean isFound = false;

        if (choice == 0) { // 이메일 검색
            // 검색어를 받아서, 이메일로, members , Map의 내용을 검색.
            if (service.getMembers().containsKey(keyword)) {
                _3_MemberBase member = service.getMembers().get(keyword);
                printLog("검색결과 : " + member.getName() + ", 이메일 : " + member.getEmail());
                isFound = true;
            }
        } else { // 이름 검색
            for (_3_MemberBase member : service.getMembers().values()) {
                if (member.getName().contains(keyword)) {
                    printLog("검색결과 : " + member.getName() + ", 이메일 : " + member.getEmail());
                    isFound = true;
                }
            }
        }
        // 회원이 없을 경우.
        if (!isFound) {
            printLog("검색 결과가 없습니다. ");
        }

    }

    // 260127_리팩토링_코드정리_순서20
    // 각 기능들 가져오기.
    // 6. 회원 탈퇴
    private void handleDelete() {
        // 1. 로그인 체크
        if(service.getLoggedInMember() == null) { // 로그인이 안된 경우,
            // 알림창 띄우고
            JOptionPane.showMessageDialog(this, "로그인 후 본인 탈퇴만 가능합니다.");
            // 해당 기능 종료
            return;
        }

        // 2. 삭제 재확인 다이얼로그창 , 화면 구현.
        int response = JOptionPane.showConfirmDialog(
                this,
                "정말로 회원 탈퇴를 하시겠습니까? \n 모든 정보가 삭제됩니다.",
                "회원 탈퇴 확인",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.WARNING_MESSAGE
        );

        // yes , no 따라서 로직 구성.
        if( response == JOptionPane.YES_OPTION) {
            // 3 비밀번호 재확인 후, 진행하기.
            String inputPassword = JOptionPane.showInputDialog(this, "비밀번호를 입력하세요:");

            // 입력 비밀번호, 멤버 비밀번호가 일치한다면,
            if(inputPassword != null && inputPassword.equals(service.getLoggedInMember().getPassword())) {
                // 4 삭제 로직 진행.
                String targetEmail = service.getLoggedInMember().getEmail();
                service.getMembers().remove(targetEmail);// 맵에서 삭제 처리.

                // 5 파일 업데이트 , 메모리 상에서 변경된 내용 -> 파일에 업데이트
//                saveMembers(members);
                service.updateMember();

                //  6 상태 초기화 (로그아웃처리)
                printLog(">>> 회원탈퇴완료: " + targetEmail);
//                loggedInMember = null;
                service.logout();
                updateButtonState();

                // 7 알림창
                JOptionPane.showMessageDialog(this, "탈퇴 처리가 완료되었습니다. 이용해 주셔서 감사합니다.");
            } else if (inputPassword != null) {
                JOptionPane.showMessageDialog(this,"비밀번호가 일치하지 않습니다. ");
            }
        }

    }


}// 클래스 닫기
