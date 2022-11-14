package step2step3.lotto.lottoTicket;

import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class LottoNumber implements Comparable<LottoNumber> {

    private static final int LOTTO_NUMBER_MIN = 1;
    private static final int LOTTO_NUMBER_MAX = 45;
    private static final Map<Integer, LottoNumber> lottoNumbers;

    private final int number;

    static {
        lottoNumbers = IntStream.range(LOTTO_NUMBER_MIN, LOTTO_NUMBER_MAX + 1)
                .boxed()
                .collect(Collectors.toUnmodifiableMap(number -> number, LottoNumber::new));
    }

    private LottoNumber(int number) {
        this.number = number;
    }
    public static LottoNumber lottoNumber(int number) {
        verifyNumber(number);
        return lottoNumbers.get(number);
    }

    private static void verifyNumber(int number) {
        if (number < LOTTO_NUMBER_MIN || LOTTO_NUMBER_MAX < number) {
            throw new IllegalArgumentException("숫자는 1이상 45이하이어야 합니다.");
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LottoNumber that = (LottoNumber) o;
        return number == that.number;
    }

    @Override
    public int hashCode() {
        return Objects.hash(number);
    }

    public boolean isSameNumber(int number) {
        return this.number == number;
    }

    @Override
    public int compareTo(LottoNumber lottoNumber) {
        return this.number - lottoNumber.number;
    }

    @Override
    public String toString() {
        return String.valueOf(number);
    }

    public int number() {
        return number;
    }
}
