package step2step3.lotto;

import org.junit.jupiter.api.Test;
import step2step3.lotto.lottoNumber.NumbersGenerator;
import step2step3.lotto.lottoTicket.LottoTicket;
import step2step3.lotto.lottoTicket.Rank;
import step2step3.lotto.lottoTicket.WinningLottoInformation;

import java.util.List;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

class LottoTicketsTest {

    @Test
    void 천원의_배수만큼_로또를_생성한다() {
        int numberOfTickets = 1;
        int lottoPrice = 1000;
        int totalPrice = lottoPrice * numberOfTickets;
        NumbersGenerator numbersGenerator = new NumbersGenerator.Fake(Set.of(1, 2, 3, 4, 5, 6));

        LottoTickets lottoTickets = new LottoTicketsFactory(totalPrice, numbersGenerator).lottoTickets();

        assertThat(lottoTickets.numberOfTickets()).isOne();
    }

    @Test
    void 수익률_계산기를_생성할_수_있다() {
        int numberOfTickets = 1;
        int lottoPrice = 1000;
        int totalPrice = lottoPrice * numberOfTickets;
        Set<Integer> lottoNumbers = Set.of(1, 2, 3, 4, 5, 6);
        NumbersGenerator numbersGenerator = new NumbersGenerator.Fake(lottoNumbers);

        LottoTickets lottoTickets = new LottoTicketsFactory(totalPrice, numbersGenerator).lottoTickets();

        WinningLottoInformation winningLottoInformation = new WinningLottoInformation(1, LottoTicket.from(numbersGenerator));
        assertThat(lottoTickets.yieldCalculator(winningLottoInformation)).isEqualTo(new YieldCalculator(lottoPrice, List.of(Rank.FIRST)));
    }

    @Test
    void 일치_지표_계산기를_생성할_수_있다() {
        int numberOfTickets = 1;
        int lottoPrice = 1000;
        int totalPrice = lottoPrice * numberOfTickets;
        Set<Integer> lottoNumbers = Set.of(1, 2, 3, 4, 5, 6);
        NumbersGenerator numbersGenerator = new NumbersGenerator.Fake(lottoNumbers);

        LottoTickets lottoTickets = new LottoTicketsFactory(totalPrice, numbersGenerator).lottoTickets();

        WinningLottoInformation winningLottoInformation = new WinningLottoInformation(1, LottoTicket.from(numbersGenerator));
        assertThat(lottoTickets.matchIndicatorCalculator(winningLottoInformation)).isEqualTo(new MatchIndicatorCalculator(List.of(Rank.FIRST)));
    }

}
