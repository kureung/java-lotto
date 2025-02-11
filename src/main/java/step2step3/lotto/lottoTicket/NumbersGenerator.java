package step2step3.lotto.lottoTicket;

import java.util.Set;

public interface NumbersGenerator {

    Set<Integer> numbers();

    class Fake implements NumbersGenerator {

        private final Set<Integer> numbers;

        public Fake(Set<Integer> numbers) {
            this.numbers = numbers;
        }

        @Override
        public Set<Integer> numbers() {
            return numbers;
        }
    }

}
