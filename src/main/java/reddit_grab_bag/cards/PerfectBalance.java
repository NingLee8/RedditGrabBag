package reddit_grab_bag.cards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import reddit_grab_bag.powers.AnticipationPower;
import reddit_grab_bag.powers.PerfectBalancePower;
import reddit_grab_bag.util.CardStats;

public class PerfectBalance extends BaseCard{

    public static final String ID = makeID(PerfectBalance.class.getSimpleName());

    private static final CardStats info = new CardStats(CardColor.GREEN, CardType.POWER, CardRarity.UNCOMMON, CardTarget.SELF, 1);

    public PerfectBalance() {
        super(ID, info);
        this.baseMagicNumber = 3;
        this.magicNumber = 5;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new ApplyPowerAction(p, p, new PerfectBalancePower(p, this.magicNumber), this.magicNumber));
    }

    @Override
    public void upgrade() {
        if(!this.upgraded) {
            this.upgradeName();
            this.upgradeMagicNumber(2);
            this.rawDescription = cardStrings.UPGRADE_DESCRIPTION;
            this.initializeDescription();
        }
    }

    @Override
    public AbstractCard makeCopy() {
        return new PerfectBalance();
    }

}
