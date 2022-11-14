package step2step3.lotto.lottoTicket;

import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

public class LottoTicket {

    private static final int NUMBER_OF_LOTTO = 6;

    private final List<LottoNumber> numbers;

    private LottoTicket(List<LottoNumber> numbers) {
        verifyNumbers(numbers);
        this.numbers = numbers;
    }

    private static void verifyNumbers(List<LottoNumber> numbers) {
        Set<LottoNumber> lottoNumbers = new HashSet<>(numbers);

        if (lottoNumbers.size() != NUMBER_OF_LOTTO) {
            throw new IllegalArgumentException("로또 번호는 6개이어야 합니다.");
        }
    }

    public static LottoTicket from(NumbersGenerator numbersGenerator) {
        return new LottoTicket(lottoNumbers(numbersGenerator));
    }

    private static List<LottoNumber> lottoNumbers(NumbersGenerator numbersGenerator) {
        Set<Integer> randomNumbers = numbersGenerator.numbers();
        return randomNumbers.stream()
                .map(LottoNumber::lottoNumber)
                .collect(Collectors.toUnmodifiableList());
    }

    public List<LottoNumber> numbers() {
        return List.copyOf(numbers);
    }

    public Rank rank(LottoTicket winningLottoTicket, boolean isMatchBonus) {
        long matchCount = numbers.stream()
                .filter(winningLottoTicket::hasLottoNumber)
                .count();

        return Rank.rank(matchCount, isMatchBonus);
    }
    
    private boolean hasLottoNumber(LottoNumber lottoNumber) {
        return numbers.contains(lottoNumber);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LottoTicket that = (LottoTicket) o;
        return Objects.equals(numbers, that.numbers);
    }

    @Override
    public int hashCode() {
        return Objects.hash(numbers);
    }

    public boolean hasBonusNumber(int number) {
        return numbers.stream()
                .anyMatch(lottoNumber -> lottoNumber.isSameNumber(number));
    }
}
