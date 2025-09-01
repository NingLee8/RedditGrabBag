package reddit_grab_bag.cards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.ArtifactPower;
import com.megacrit.cardcrawl.powers.StrengthPower;
import reddit_grab_bag.util.CardStats;

public class Sap extends BaseCard{

    public static final String ID = makeID(Sap.class.getSimpleName());

    private static final CardStats info = new CardStats(CardColor.GREEN, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.SELF_AND_ENEMY, 1);

    public Sap() {
        super(ID, info);
        this.exhaust = true;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        boolean gainStrength = !m.hasPower(ArtifactPower.POWER_ID);
        this.addToBot(new ApplyPowerAction(m, p, new StrengthPower(m, -1), -1));

        if (gainStrength) {
            this.addToBot(new ApplyPowerAction(p, p, new StrengthPower(m, 1), 1));
        }

    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            upgradeBaseCost(0);
        }
    }

    @Override
    public AbstractCard makeCopy() {
        return new Sap();
    }
}
