package reddit_grab_bag.cards;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import reddit_grab_bag.actions.SingularityAction;
import reddit_grab_bag.util.CardStats;

public class Singularity extends BaseCard{

    public static final String ID = makeID(Singularity.class.getSimpleName());

    private static final CardStats info = new CardStats(CardColor.BLUE, CardType.SKILL, CardRarity.RARE, CardTarget.SELF, 1);

    public Singularity() {
        super (ID, info);
        this.exhaust = true;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new SingularityAction(this.upgraded));
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
        return new Singularity();
    }
}
