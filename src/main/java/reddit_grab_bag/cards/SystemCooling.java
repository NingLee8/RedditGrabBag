package reddit_grab_bag.cards;

import com.megacrit.cardcrawl.actions.defect.ChannelAction;
import com.megacrit.cardcrawl.actions.defect.IncreaseMaxOrbAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.orbs.Frost;
import reddit_grab_bag.util.CardStats;

public class SystemCooling extends BaseCard{

    public static final String ID = makeID(SystemCooling.class.getSimpleName());

    private static final CardStats info = new CardStats(CardColor.BLUE, CardType.POWER, CardRarity.UNCOMMON, CardTarget.SELF, 1);

    public SystemCooling() {
        super(ID, info);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new IncreaseMaxOrbAction(1));
        this.addToBot(new ChannelAction(new Frost()));
    }

    @Override
    public void upgrade() {
        if(!this.upgraded) {
            this.upgradeName();
            this.isInnate = true;
            this.rawDescription = cardStrings.UPGRADE_DESCRIPTION;
            initializeDescription();
        }
    }

    @Override
    public AbstractCard makeCopy() {
        return new SystemCooling();
    }

}
