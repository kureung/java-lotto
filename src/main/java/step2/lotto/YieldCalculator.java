package step2.lotto;

import java.util.List;
import java.util.Objects;

public class YieldCalculator {

    private final int individualAmount;

    private final List<Rank> rankingAggregation;

    public YieldCalculator(int individualAmount, List<Rank> rankingAggregation) {
        this.individualAmount = individualAmount;
        this.rankingAggregation = rankingAggregation;
    }

    public double yield() {
        return totalPrizeAmount() / (rankingAggregation.size() * individualAmount);
    }

    private double totalPrizeAmount() {
        return rankingAggregation.stream()
                .reduce(0.0, (prizeAmount, rank) -> rank.addedPrizeAmount(prizeAmount), Double::sum);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        YieldCalculator that = (YieldCalculator) o;
        return individualAmount == that.individualAmount && Objects.equals(rankingAggregation, that.rankingAggregation);
    }

    @Override
    public int hashCode() {
        return Objects.hash(individualAmount, rankingAggregation);
    }

}
