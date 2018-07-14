import java.math.BigDecimal;

/**
 * @author caipiaoping
 * @version V1.0
 * @Description: TODO
 * @date 2018-03-31
 */
public class test {
    public static void main(String args[]){
        char[] c = {'a','b','c'};
        StringBuffer buffer = new StringBuffer();
        buffer.append(c).append(c);
        String a = new String(buffer);
        a.codePointAt(1);

        System.out.println(a.codePointBefore(1));


        System.out.println(Integer.toString(123,10));

        System.out.println(BigDecimal.valueOf(1123,10));
    }

}
