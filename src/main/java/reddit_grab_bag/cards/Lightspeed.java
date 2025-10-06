package reddit_grab_bag.cards;

import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import reddit_grab_bag.actions.LightspeedAction;
import reddit_grab_bag.util.CardStats;

public class Lightspeed extends BaseCard{

    public static final String ID = makeID(Lightspeed.class.getSimpleName());

    private static final CardStats info = new CardStats(CardColor.BLUE, CardType.SKILL, CardRarity.RARE, CardTarget.SELF, 2);

    public Lightspeed() {
        super (ID, info);
        this.exhaust = true;
        this.baseMagicNumber = 6;
        this.magicNumber = this.baseMagicNumber;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new LightspeedAction(this.magicNumber));

        this.addToBot(new DrawCardAction(p, this.magicNumber));
    }

    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeMagicNumber(1);
        }
    }

    @Override
    public AbstractCard makeCopy() {
        return new Lightspeed();
    }
}
