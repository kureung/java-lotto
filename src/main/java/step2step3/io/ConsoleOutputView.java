package step2step3.io;

import step2step3.lotto.LottoTickets;
import step2step3.lotto.lottoTicket.LottoNumber;
import step2step3.lotto.lottoTicket.LottoTicket;
import step2step3.lotto.lottoTicket.Rank;

import java.util.Map;
import java.util.stream.Collectors;

public class ConsoleOutputView {

    public void printNumberOfPurchases(LottoTickets lottoTickets) {
        System.out.printf("%d개를 구매했습니다.%n", lottoTickets.numberOfTickets());
    }

    public void printYield(double yield) {
        if (yield < 1) {
            System.out.printf("총 수익률은 %.2f입니다.(기준이 1이기 때문에 결과적으로 손해라는 의미임)%n", yield);
            return;
        }

        System.out.printf("총 수익률은 %.2f입니다.%n", yield);
    }

    public void printLottoNumbers() {
        System.out.println("수동으로 구매할 번호를 입력해주세요.");

    }

    public void printMatchIndicator(Map<Rank, Long> ranks) {
        System.out.println("당첨 통계");
        System.out.println("---------");

        ranks.forEach((key, value) -> System.out.printf("%d개 일치 (%d원)- %d개%n", key.numberOfMatchingNumbers(), key.prizeAmount(), value));
    }

    public void printLottoTickets(LottoTickets lottoTickets) {
        lottoTickets.lottoTickets()
                .forEach(lottoTicket -> System.out.println(printLottoTicket(lottoTicket)));
        System.out.println();
    }

    private String printLottoTicket(LottoTicket lottoTicket) {
        return lottoTicket.numbers()
                .stream()
                .map(LottoNumber::number)
                .map(String::valueOf)
                .collect(Collectors.joining(",", "[", "]"));
    }

}
