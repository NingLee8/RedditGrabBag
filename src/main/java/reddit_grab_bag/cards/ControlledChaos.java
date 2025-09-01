package reddit_grab_bag.cards;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import reddit_grab_bag.actions.ControlledChaosAction;
import reddit_grab_bag.util.CardStats;

public class ControlledChaos extends BaseCard {

    public static final String ID = makeID(ControlledChaos.class.getSimpleName());

    private static final CardStats info = new CardStats(CardColor.COLORLESS, CardType.SKILL, CardRarity.RARE, CardTarget.SELF, -1);

    public ControlledChaos() {
        super (ID, info);
        this.exhaust = true;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new ControlledChaosAction(p, this.freeToPlayOnce, this.energyOnUse, this.upgraded));
    }

    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.rawDescription = cardStrings.UPGRADE_DESCRIPTION;
            this.initializeDescription();
        }
    }

    @Override
    public AbstractCard makeCopy() {
        return new ControlledChaos();
    }
}
