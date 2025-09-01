package reddit_grab_bag.cards;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import reddit_grab_bag.actions.ForgeAction;
import reddit_grab_bag.util.CardStats;

public class ForgedInFire extends BaseCard {

    public static final String ID = makeID(ForgedInFire.class.getSimpleName());

    private static final CardStats info = new CardStats(CardColor.RED, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.SELF, 1);

    public ForgedInFire() {
        super (ID, info);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new ForgeAction(this.upgraded));
    }

    @Override
    public AbstractCard makeCopy() {
        return new ForgedInFire();
    }
}
