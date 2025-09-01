package reddit_grab_bag.cards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import reddit_grab_bag.powers.EndlessFlaskPower;
import reddit_grab_bag.util.CardStats;

public class EndlessFlask extends BaseCard{

    public static final String ID = makeID(EndlessFlask.class.getSimpleName());

    private static final CardStats info = new CardStats(CardColor.PURPLE, CardType.POWER, CardRarity.UNCOMMON, CardTarget.SELF, 1);

    public EndlessFlask() {
        super(ID, info);
        this.baseMagicNumber = 2;
        this.magicNumber = this.baseMagicNumber;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new ApplyPowerAction(p, p, new EndlessFlaskPower(p, this.magicNumber), this.magicNumber));
    }

    @Override
    public void upgrade() {
        if(!this.upgraded) {
            this.upgradeName();
            this.isInnate = true;
            this.rawDescription = cardStrings.UPGRADE_DESCRIPTION;
            this.initializeDescription();
        }
    }

    @Override
    public AbstractCard makeCopy() {
        return new EndlessFlask();
    }
}
