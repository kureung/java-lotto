package step2step3.lotto;

import org.junit.jupiter.api.Test;
import step2step3.lotto.lottoTicket.LottoTicket;
import step2step3.lotto.lottoTicket.NumbersGenerator;
import step2step3.lotto.lottoTicket.Rank;
import step2step3.lotto.lottoTicket.WinningLotto;

import java.util.List;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

class LottoTicketsTest {
    @Test
    void 수익률_계산기를_생성할_수_있다() {
        int lottoPrice = 1000;
        NumbersGenerator numbersGenerator = new NumbersGenerator.Fake(Set.of(1, 2, 3, 4, 5, 6));

        LottoTickets lottoTickets = new LottoTickets(List.of(LottoTicket.from(numbersGenerator)), lottoPrice);

        WinningLotto winningLotto = new WinningLotto(1, LottoTicket.from(numbersGenerator));
        assertThat(lottoTickets.yieldCalculator(winningLotto)).isEqualTo(new YieldCalculator(lottoPrice, List.of(Rank.FIRST)));
    }

    @Test
    void 일치_지표_계산기를_생성할_수_있다() {
        int lottoPrice = 1000;
        NumbersGenerator numbersGenerator = new NumbersGenerator.Fake(Set.of(1, 2, 3, 4, 5, 6));

        LottoTickets lottoTickets = new LottoTickets(List.of(LottoTicket.from(numbersGenerator)), lottoPrice);

        WinningLotto winningLotto = new WinningLotto(1, LottoTicket.from(numbersGenerator));
        assertThat(lottoTickets.matchIndicatorCalculator(winningLotto)).isEqualTo(new MatchIndicatorCalculator(List.of(Rank.FIRST)));
    }

    @Test
    void 로또티켓들을_합칠_수_있다() {
        int lottoPrice = 1000;
        NumbersGenerator numbersGenerator = new NumbersGenerator.Fake(Set.of(1, 2, 3, 4, 5, 6));
        LottoTickets lottoTickets = new LottoTickets(List.of(LottoTicket.from(numbersGenerator)), lottoPrice);

        List<LottoTicket> addedLottoTickets = List.of(LottoTicket.from(numbersGenerator), LottoTicket.from(numbersGenerator));
        assertThat(lottoTickets.addedLottoTickets(lottoTickets))
                .isEqualTo(new LottoTickets(addedLottoTickets, lottoPrice));
    }
}
