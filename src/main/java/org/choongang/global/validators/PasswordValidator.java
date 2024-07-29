package org.choongang.global.validators;

public interface PasswordValidator {

    default boolean alphaCheck(String password, boolean caseInsensitive) {
        if(caseInsensitive) {//대소문자 구분없이 알파벳 하나 있는지 체크
            return password.matches(".*[A-Za-z]+.*");
        }
        return password.matches(".*[A-Z]+.*") && password.matches(".*[a-z]+.*");
    }

    default boolean numberCheck(String password) {
        //return password.matches(".*[0-9].*");
        return password.matches(".*\\d+.*"); //위와 동일함  숫자 1개 이상
    }

    default boolean specialCharsCheck(String password) {
        String pattern = ".*[^0-9a-zA-Zㄱ-ㅎ가-힣]+.*"; // 특수문자 1개 이상 포함
        return password.matches(pattern);
    }
}
