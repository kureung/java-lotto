package step2step3.lotto;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import step2step3.lotto.lottoTicket.LottoTicket;
import step2step3.lotto.lottoTicket.NumbersGenerator;

import java.util.List;
import java.util.Set;

import static org.assertj.core.api.Assertions.*;

class LottoTicketsFactoryTest {

    @Test
    void 로또_티켓들을_생성할_수_있다() {
        int price = 1000;
        int manualPurchaseCount = 0;
        NumbersGenerator numbersGenerator = new NumbersGenerator.Fake(Set.of(1, 2, 3, 4, 5, 6));

        LottoTicketsFactory lottoTicketsFactory = new LottoTicketsFactory(price, manualPurchaseCount);

        List<LottoTicket> lottoTickets = List.of(LottoTicket.from(numbersGenerator));
        assertThat(lottoTicketsFactory.lottoTickets(numbersGenerator)).isEqualTo(new LottoTickets(lottoTickets, price));
    }

    @ParameterizedTest
    @ValueSource(ints = {
            999,
            1001
    })
    void 천원_단위가_아니거나_천원_미만인_금액일_경우_예외가_발생한다(int price) {
        int manualPurchaseCount = 0;

        assertThatThrownBy(() -> new LottoTicketsFactory(price, manualPurchaseCount))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("금액이 천원 단위가 아니거나 천원 미민일 수 없습니다.");
    }

    @Test
    void 수동_구매_금액이_총_구매_금액을_초과할_경우_예외가_발생한다() {
        int totalPrice = 1000;
        int manualPurchaseCount = 2;

        assertThatThrownBy(() -> new LottoTicketsFactory(totalPrice, manualPurchaseCount))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("수동 구매 금액이 총 구매 금액을 초과할 수 없습니다.");
    }

    @Test
    void 수동_구매_개수가_음수이면_예외가_발생한다() {
        int totalPrice = 1000;
        int manualPurchaseCount = -1;

        assertThatThrownBy(() -> new LottoTicketsFactory(totalPrice, manualPurchaseCount))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("수동 구매 개수가 0 또는 양수이어야 합니다.");
    }

    @ParameterizedTest
    @ValueSource(ints = {
            0,
            1
    })
    void 수동_구매_개수가_0_또는_양수일_경우_예외가_발생하지_않는다(int manualPurchaseCount) {
        int totalPrice = 1000;

        assertThatCode(() -> new LottoTicketsFactory(totalPrice, manualPurchaseCount))
                .doesNotThrowAnyException();
    }
}
