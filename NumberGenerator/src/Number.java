import java.util.ArrayList;
import java.util.List;

public class Number {

    public String generateNewNumber() {
        String charactersForNumber = "";
        String carNumber;

        int randomNumberForCharacter;
        int firstRandomDigit;
        int secondRandomDigit;
        int thirdRandomDigit;

        int maxAmountOfCharacter = 12;
        int maxAmountOfNumber = 9;

        int minDigitOfRegion = 1;
        int maxDigitOfRegion = 199;

        int digitOfRegion = minDigitOfRegion + (int) (Math.random() * maxDigitOfRegion);
        String region = Integer.toString(digitOfRegion);
        String nullCharacter = "0";
        if (region.length() == 1) {
            region = nullCharacter + nullCharacter + region;
        } else if (region.length() == 2) {
            region = nullCharacter + region;
        }
        char[] randomCharacter = "АВЕКМНОРСТУХ".toCharArray();

        for (int i = 0; i < 3; i++) {
            randomNumberForCharacter = (int) (Math.random() * maxAmountOfCharacter);
            charactersForNumber += Character.toString(randomCharacter[randomNumberForCharacter]);
        }

        firstRandomDigit = (int) (Math.random() * maxAmountOfNumber);
        secondRandomDigit = (int) (Math.random() * maxAmountOfNumber);
        thirdRandomDigit = (int) (Math.random() * maxAmountOfNumber);

        carNumber = firstRandomDigit + charactersForNumber + secondRandomDigit + thirdRandomDigit + region;
        return carNumber;
    }
}
