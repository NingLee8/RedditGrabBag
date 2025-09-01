package reddit_grab_bag.cards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import reddit_grab_bag.powers.AnticipationPower;
import reddit_grab_bag.util.CardStats;

public class Anticipation extends BaseCard{

    public static final String ID = makeID(Anticipation.class.getSimpleName());

    private static final CardStats info = new CardStats(CardColor.PURPLE, CardType.POWER, CardRarity.UNCOMMON, CardTarget.SELF, 1);

    public Anticipation() {
        super(ID, info);
        this.baseMagicNumber = 3;
        this.magicNumber = this.baseMagicNumber;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new ApplyPowerAction(p, p, new AnticipationPower(p, this.magicNumber), this.magicNumber));
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
        return new Anticipation();
    }

}
