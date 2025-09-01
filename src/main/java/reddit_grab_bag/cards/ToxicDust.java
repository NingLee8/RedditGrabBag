package reddit_grab_bag.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.PoisonPower;
import com.megacrit.cardcrawl.powers.WeakPower;
import reddit_grab_bag.util.CardStats;

public class ToxicDust extends BaseCard{

    public static final String ID = makeID(ToxicDust.class.getSimpleName());

    private static final CardStats info = new CardStats(CardColor.GREEN, CardType.ATTACK, CardRarity.COMMON, CardTarget.ENEMY, 0);

    public ToxicDust() {
        super(ID, info);
        this.baseMagicNumber = 4;
        this.magicNumber = this.baseMagicNumber;
        this.baseDamage = 4;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new DamageAction(m, new DamageInfo(p, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.POISON));
        if (m.hasPower(WeakPower.POWER_ID)) {
            this.addToBot(new ApplyPowerAction(m, p, new PoisonPower(m, p, this.magicNumber), this.magicNumber, AbstractGameAction.AttackEffect.POISON));
        }
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            upgradeDamage(2);
            upgradeMagicNumber(1);
        }
    }

    @Override
    public AbstractCard makeCopy() {
        return new ToxicDust();
    }
}
