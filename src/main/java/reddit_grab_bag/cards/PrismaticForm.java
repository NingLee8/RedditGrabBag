package reddit_grab_bag.cards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import reddit_grab_bag.powers.PrismaticFormPower;
import reddit_grab_bag.util.CardStats;

public class PrismaticForm extends BaseCard{

    public static final String ID = makeID(PrismaticForm.class.getSimpleName());

    private static final CardStats info = new CardStats(CardColor.COLORLESS, CardType.POWER, CardRarity.RARE, CardTarget.SELF, 3);

    public PrismaticForm() {
        super(ID, info);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new ApplyPowerAction(p, p, new PrismaticFormPower(p, 1), 1));
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            upgradeBaseCost(2);
        }
    }

    @Override
    public AbstractCard makeCopy() {
        return new PrismaticForm();
    }

}
