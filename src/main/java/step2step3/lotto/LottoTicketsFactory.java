package step2step3.lotto;

import step2step3.lotto.lottoTicket.NumbersGenerator;
import step2step3.lotto.lottoTicket.LottoTicket;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class LottoTicketsFactory {

    private static final int LOTTO_PRICE = 1000;

    private final int purchaseAmount;
    private final int manualPurchaseCount;

    public LottoTicketsFactory(int purchaseAmount, int manualPurchaseCount) {
        verifyPurchaseAmount(purchaseAmount);
        this.purchaseAmount = purchaseAmount;
        this.manualPurchaseCount = manualPurchaseCount;
    }

    private void verifyPurchaseAmount(int purchaseAmount) {
        if (purchaseAmount < LOTTO_PRICE || purchaseAmount % LOTTO_PRICE != 0) {
            throw new IllegalArgumentException("금액이 천원 단위가 아니거나 천원 미민일 수 없습니다.");
        }
    }

    public LottoTickets lottoTickets(NumbersGenerator numbersGenerator) {
        int numberOfTickets = purchaseAmount / LOTTO_PRICE;
        return new LottoTickets(numberAsLottoTickets(numberOfTickets, numbersGenerator), LOTTO_PRICE);
    }

    private List<LottoTicket> numberAsLottoTickets(int numberOfTickets, NumbersGenerator numbersGenerator) {
        return IntStream.range(0, numberOfTickets)
                .mapToObj(numberOfTicket -> LottoTicket.from(numbersGenerator))
                .collect(Collectors.toUnmodifiableList());
    }
}
