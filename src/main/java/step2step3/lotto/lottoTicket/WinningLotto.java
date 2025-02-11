package step2step3.lotto.lottoTicket;

public class WinningLotto {
    private final int bonusNumber;
    private final LottoTicket winningLottoTicket;

    public WinningLotto(int bonusNumber, LottoTicket winningLottoTicket) {
        this.bonusNumber = bonusNumber;
        this.winningLottoTicket = winningLottoTicket;
    }

    public Rank matchedRank(LottoTicket lottoTicket) {
        return winningLottoTicket.rank(lottoTicket, isMatchBonus(lottoTicket));
    }

    private boolean isMatchBonus(LottoTicket lottoTicket) {
        return lottoTicket.hasBonusNumber(bonusNumber);
    }
}
