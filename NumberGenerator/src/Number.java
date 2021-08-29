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

        int digitOfRegion = (int) (Math.random() * (maxDigitOfRegion - minDigitOfRegion) + minDigitOfRegion);

        char[] randomCharacter = "АВЕКМНОРСТУХ".toCharArray();

        for (int i = 0; i < 3; i++) {
            randomNumberForCharacter = (int) (Math.random() * maxAmountOfCharacter);
            charactersForNumber += Character.toString(randomCharacter[randomNumberForCharacter]);
        }

        firstRandomDigit = (int) (Math.random() * maxAmountOfNumber);
        secondRandomDigit = (int) (Math.random() * maxAmountOfNumber);
        thirdRandomDigit = (int) (Math.random() * maxAmountOfNumber);

        carNumber = firstRandomDigit + charactersForNumber + secondRandomDigit + thirdRandomDigit + digitOfRegion;
        return carNumber;
    }
}
