package step2step3.lottoGame;

import step2step3.io.ConsoleInputView;
import step2step3.io.ConsoleOutputView;
import step2step3.lotto.LottoTickets;
import step2step3.lotto.LottoTicketsFactory;
import step2step3.lotto.MatchIndicatorCalculator;
import step2step3.lotto.YieldCalculator;
import step2step3.lotto.lottoTicket.LottoTicket;
import step2step3.lotto.lottoTicket.NumbersGenerator;
import step2step3.lotto.lottoTicket.WinningLotto;
import step2step3.randomNumbers.InfusedNumbersGenerator;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class LottoGame {

    private final ConsoleOutputView consoleOutputView;
    private final ConsoleInputView consoleInputView;
    private final NumbersGenerator numbersGenerator;

    public LottoGame(ConsoleOutputView consoleOutputView, ConsoleInputView consoleInputView, NumbersGenerator numbersGenerator) {
        this.consoleOutputView = consoleOutputView;
        this.consoleInputView = consoleInputView;
        this.numbersGenerator = numbersGenerator;
    }

    public LottoTickets purchasedLotteries() throws IOException {
        return purchasedLottoTickets();
    }

    private LottoTickets purchasedLottoTickets() throws IOException {
        int purchaseAmount = consoleInputView.purchaseAmount();
        int manualPurchasedCount = consoleInputView.manualPurchasedCount();

        LottoTicketsFactory lottoTicketsFactory = new LottoTicketsFactory(purchaseAmount, manualPurchasedCount);
        LottoTickets autoPurchasedLottoTickets = lottoTicketsFactory.autoPurchasedLottoTickets(numbersGenerator);
        LottoTickets manualPurchasedLottoTickets = manualPurchasedLottoTickets(manualPurchasedCount, lottoTicketsFactory);
        return manualPurchasedLottoTickets.addedLottoTickets(autoPurchasedLottoTickets);
    }

    private LottoTickets manualPurchasedLottoTickets(int manualPurchasedCount, LottoTicketsFactory lottoTicketsFactory) throws IOException {
        consoleOutputView.printLottoNumbers();
        List<LottoTicket> lottoTickets = new ArrayList<>();
        for (int i = 0; i< manualPurchasedCount; i++) {
            NumbersGenerator infusedNumbersGenerator = new InfusedNumbersGenerator(consoleInputView.manualPurchasedLottoTickets());
            lottoTickets.add(LottoTicket.from(infusedNumbersGenerator));
        }
        return lottoTicketsFactory.manualPurchasedLottoTickets(lottoTickets);
    }

    public void printLottoTicketsNumbers(LottoTickets lottoTickets) {
        consoleOutputView.printLottoTickets(lottoTickets);
    }

    public void winningStatistics(LottoTickets lottoTickets) throws IOException {
        LottoTicket winningLottoTicket = winningLottoTicket();
        int bonusNumber = consoleInputView.bonusNumber();

        WinningLotto winningLotto = new WinningLotto(bonusNumber, winningLottoTicket);
        printMatchIndicator(lottoTickets, winningLotto);
        printYield(lottoTickets, winningLotto);
    }

    private LottoTicket winningLottoTicket() throws IOException {
        InfusedNumbersGenerator infusedNumbersGenerator = new InfusedNumbersGenerator(consoleInputView.winningNumbers());
        return LottoTicket.from(infusedNumbersGenerator);
    }

    private void printMatchIndicator(LottoTickets lottoTickets, WinningLotto winningLotto) {
        MatchIndicatorCalculator matchIndicatorCalculator = lottoTickets.matchIndicatorCalculator(winningLotto);
        consoleOutputView.printMatchIndicator(matchIndicatorCalculator.matchIndicators());
    }

    private void printYield(LottoTickets lottoTickets, WinningLotto winningLotto) {
        YieldCalculator yieldCalculator = lottoTickets.yieldCalculator(winningLotto);
        consoleOutputView.printYield(yieldCalculator.yield());
    }
}
