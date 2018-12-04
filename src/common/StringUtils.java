package common;


public class StringUtils {

    public static final int DEFAULT_FIXED_PRINT_LENGTH = 20;

    public static String generateAsterics(int count){
        StringBuilder builder = new StringBuilder();
        for(int i=0;i<count;i++) builder.append('*');
        return builder.toString();
    }

    public static String getFixedLengthString(String text, int printLength){
        return String.format("%-"+printLength+"s", text);
    }

    public static String getFixedLengthString(String text){
        return getFixedLengthString(text, DEFAULT_FIXED_PRINT_LENGTH);
    }

    public static String getFixedLineOfAsterics(){
        return getFixedLengthString(generateAsterics(2*DEFAULT_FIXED_PRINT_LENGTH+3))+"\n";
    }

}
