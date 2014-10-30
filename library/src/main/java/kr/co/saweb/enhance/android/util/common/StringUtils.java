package kr.co.saweb.enhance.android.util.common;

/**
 * Created by OKS on 2014-10-15.
 */
public class StringUtils {
    public static String lengthLimit(String inputStr, int limit, String fixStr) {
        try {
            if (inputStr == null)
                return "";

            if (limit <= 0)
                return inputStr;

            byte[] strbyte = inputStr.getBytes();

            if (strbyte.length <= limit) {
                return inputStr;
            }

            char[] charArray = inputStr.toCharArray();

            int checkLimit = limit;
            for (char aCharArray : charArray) {
                if (aCharArray < 256) {
                    checkLimit -= 1;
                } else {
                    checkLimit -= 2;
                }

                if (checkLimit <= 0) {
                    break;
                }
            }

            //대상 문자열 마지막 자리가 2바이트의 중간일 경우 제거함
            byte[] newByte = new byte[limit + checkLimit];

            System.arraycopy(strbyte, 0, newByte, 0, newByte.length);

            if (fixStr == null) {
                return new String(newByte, "utf-8");
            } else {
                return new String(newByte, "utf-8") + fixStr;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "";
    }

    public static String cutStr(String str, int cutByte, String append) {
        byte[] strByte = str.getBytes();

        if (strByte.length < cutByte)
            return str;

        int cnt = 0;
        for (int i = 0; i < cutByte; i++) {
            if (strByte[i] < 0)
                cnt++;
        }

        String r_str;
        if (cnt % 2 == 0) {
            r_str = new String(strByte, 0, cutByte);
        } else {
            r_str = new String(strByte, 0, cutByte + 1);
        }

        return r_str + append;
    }

    public static String cutStr(String str, int cutByte) {
        return cutStr(str, cutByte, "...");
    }

    public static String lpad(String str, int len, String addStr) {
        String result = str;
        int templen = len - result.length();

        for (int i = 0; i < templen; i++) {
            result = addStr + result;
        }

        return result;
    }

    public static String strCut(String str, int cutLength) {
        return strCut(str, cutLength, "...");
    }

    public static String strCut(String str, int cutLength, String append) {
        if (str.getBytes().length > cutLength) {
            StringBuilder sbStr = new StringBuilder(cutLength);

            int iTotal = 0;
            for (char c : str.toCharArray()) {
                iTotal += String.valueOf(c).getBytes().length;
                if (iTotal > cutLength) {
                    break;
                }
                sbStr.append(c);
            }

            return sbStr.append(append).toString();
        }

        return str;
    }

    public static String rPad(String str, Integer length, String car) {
        return String.format("%-" + length + "s", str).replaceAll(" ", car);
    }

    public static String lPad(int num, Integer length, String car) {
        return String.format("%" + length + "s", num + "").replaceAll(" ", car);
    }

    public static String lPad(String str, Integer length, String car) {
        if (str == null)
            return "";
        return String.format("%" + length + "s", str + "").replaceAll(" ", car);
    }

    public static String cutOffUTF8String(String str, int maxByteSize, String trail) {

        try {
            // 널일 경우에는 그냥 리턴
            if (str == null)
                return null;
            if (str.length() == 0)
                return str;

            byte strByte[] = str.getBytes("UTF-8");

            if (strByte.length <= maxByteSize)
                return str;

            // 마지막 줄임말
            int trailByteSize = 0;

            // 줄임말의 바이트 수 계산
            if (trail != null)
                trailByteSize = trail.getBytes("UTF-8").length;

            // 실질적으로 포함되는 최대 바이트 수는 trailByte를 뺀 것이다.
            maxByteSize = maxByteSize - trailByteSize;

            int endPos = 0; // 마지막 바이트 위치
            int currByte = 0; // 현재까지 조사한 바이트 수

            for (int i = 0; i < str.length(); i++) {
                // 순차적으로 문자들을 가져옴.
                char ch = str.charAt(i);

                // 이 문자가 몇 바이트로 구성된 UTF-8 코드인지를 검사하여 currByte에 누적 시킨다.
                currByte = currByte + availibleByteNum(ch);

                // 현재까지 조사된 바이트가 maxSize를 넘는다면 이전 단계 까지 누적된 바이트 까지를 유효한 바이트로
                // 간주한다.
                if (currByte > maxByteSize) {
                    endPos = currByte - availibleByteNum(ch);
                    break;
                }
            }

            // 원래 문자열을 바이트로 가져와서 유효한 바이트 까지 배열 복사를 한다.
            byte newStrByte[] = new byte[endPos];

            System.arraycopy(strByte, 0, newStrByte, 0, endPos);

            String newStr = new String(newStrByte, "UTF-8");

            newStr += trail;

            return newStr;

        } catch (Exception e) {
            return "";
        }
    }

    public static int availibleByteNum(char c) {
        int ONE_BYTE_MIN = 0x0000;
        int ONE_BYTE_MAX = 0x007F;

        int TWO_BYTE_MIN = 0x0800;
        int TWO_BYTE_MAX = 0x07FF;

        int THREE_BYTE_MIN = 0x0800;
        int THREE_BYTE_MAX = 0xFFFF;

        int SURROGATE_MIN = 0x10000;
        int SURROGATE_MAX = 0x10FFFF;

        int digit = (int) c;

        if (ONE_BYTE_MIN <= digit && digit <= ONE_BYTE_MAX)
            return 1;
        else if (TWO_BYTE_MIN <= digit && digit <= TWO_BYTE_MAX)
            return 2;
        else if (THREE_BYTE_MIN <= digit && digit <= THREE_BYTE_MAX)
            return 3;
        else if (SURROGATE_MIN <= digit && digit <= SURROGATE_MAX)
            return 4;

        return -1;
    }

    public static String carriageReturnToBr(String str) {
        if (str == null)
            return "";

        return str.replaceAll(System.getProperty("line.separator"), "<br/>");
    }
}
