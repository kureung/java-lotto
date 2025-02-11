package step2step3.lotto;

import step2step3.lotto.lottoTicket.LottoTicket;
import step2step3.lotto.lottoTicket.NumbersGenerator;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class LottoTicketsFactory {

    private static final int LOTTO_PRICE = 1000;

    private final int purchaseAmount;
    private final int manualPurchaseCount;

    public LottoTicketsFactory(int purchaseAmount, int manualPurchaseCount) {
        verifyValidProperty(purchaseAmount, manualPurchaseCount);
        this.purchaseAmount = purchaseAmount;
        this.manualPurchaseCount = manualPurchaseCount;
    }

    private void verifyValidProperty(int purchaseAmount, int manualPurchaseCount) {
        verifyPurchaseAmount(purchaseAmount);
        verifyManualPurchaseCount(purchaseAmount, manualPurchaseCount);
    }

    private void verifyPurchaseAmount(int purchaseAmount) {
        if (purchaseAmount < LOTTO_PRICE || purchaseAmount % LOTTO_PRICE != 0) {
            throw new IllegalArgumentException("금액이 천원 단위가 아니거나 천원 미민일 수 없습니다.");
        }
    }

    private void verifyManualPurchaseCount(int purchaseAmount, int manualPurchaseCount) {
        if (manualPurchaseCount < 0) {
            throw new IllegalArgumentException("수동 구매 개수가 0 또는 양수이어야 합니다.");
        }

        if (purchaseAmount < manualPurchaseCount * LOTTO_PRICE) {
            System.out.println("purchaseAmount = " + purchaseAmount);
            System.out.println("manualPurchaseCount = " + manualPurchaseCount);

            throw new IllegalArgumentException("수동 구매 금액이 총 구매 금액을 초과할 수 없습니다.");
        }
    }

    public LottoTickets autoPurchasedLottoTickets(NumbersGenerator numbersGenerator) {
        int totalTicketCount = purchaseAmount / LOTTO_PRICE;
        int autoTicketCount = totalTicketCount - manualPurchaseCount;
        return new LottoTickets(numberAsLottoTickets(autoTicketCount, numbersGenerator), LOTTO_PRICE);
    }

    public LottoTickets manualPurchasedLottoTickets(List<LottoTicket> lottoTickets) {
        return new LottoTickets(lottoTickets, LOTTO_PRICE);
    }

    private List<LottoTicket> numberAsLottoTickets(int numberOfTickets, NumbersGenerator numbersGenerator) {
        return IntStream.range(0, numberOfTickets)
                .mapToObj(numberOfTicket -> LottoTicket.from(numbersGenerator))
                .collect(Collectors.toUnmodifiableList());
    }
}
