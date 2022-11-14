package step2step3.lotto.lottoTicket;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import step2step3.lotto.lottoTicket.LottoNumber;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertTrue;

class LottoNumberTest {
    @Test
    void 로또_숫자가_같으면_서로_동일한_객체다() {
        assertThat(LottoNumber.lottoNumber(5)).isEqualTo(LottoNumber.lottoNumber(5));
    }

    @ParameterizedTest
    @ValueSource(ints = {
            0,
            46
    })
    void 로또_숫자는_1이상_45이하만_가능하다(int number) {
        assertThatThrownBy(() -> LottoNumber.lottoNumber(number))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("숫자는 1이상 45이하이어야 합니다.");
    }

    @Test
    void 로또_숫자가_같으면_참을_반환한다() {
        LottoNumber lottoNumber = LottoNumber.lottoNumber(5);

        assertTrue(lottoNumber.isSameNumber(5));
    }
}
