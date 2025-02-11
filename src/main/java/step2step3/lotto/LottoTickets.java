package step2step3.lotto;

import step2step3.lotto.lottoTicket.LottoTicket;
import step2step3.lotto.lottoTicket.Rank;
import step2step3.lotto.lottoTicket.WinningLotto;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

public class LottoTickets {

    private final List<LottoTicket> lottoTickets;

    private final int lottoPrice;

    public LottoTickets(List<LottoTicket> lottoTickets, int lottoPrice) {
        this.lottoTickets = lottoTickets;
        this.lottoPrice = lottoPrice;
    }

    public int numberOfTickets() {
        return lottoTickets.size();
    }

    public List<LottoTicket> lottoTickets() {
        return List.copyOf(lottoTickets);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LottoTickets that = (LottoTickets) o;
        return lottoPrice == that.lottoPrice && Objects.equals(lottoTickets, that.lottoTickets);
    }

    @Override
    public int hashCode() {
        return Objects.hash(lottoTickets, lottoPrice);
    }

    public YieldCalculator yieldCalculator(WinningLotto winningLotto) {
        return new YieldCalculator(lottoPrice, matchedRanks(winningLotto));
    }

    private List<Rank> matchedRanks(WinningLotto winningLotto) {
        return lottoTickets.stream()
                .map(winningLotto::matchedRank)
                .collect(toList());
    }

    public MatchIndicatorCalculator matchIndicatorCalculator(WinningLotto winningLotto) {
        return new MatchIndicatorCalculator(matchedRanks(winningLotto));
    }

    public LottoTickets addedLottoTickets(LottoTickets lottoTickets) {
        List<LottoTicket> addedLottoTickets = Stream.concat(this.lottoTicketStream(), lottoTickets.lottoTicketStream())
                .collect(Collectors.toUnmodifiableList());

        return new LottoTickets(addedLottoTickets, lottoPrice);
    }

    private Stream<LottoTicket> lottoTicketStream() {
        return lottoTickets.stream();
    }

}

