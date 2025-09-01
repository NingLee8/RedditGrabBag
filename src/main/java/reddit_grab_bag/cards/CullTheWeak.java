package reddit_grab_bag.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;

import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.WeakPower;
import reddit_grab_bag.util.CardStats;

public class CullTheWeak extends BaseCard{

    public static final String ID = makeID(CullTheWeak.class.getSimpleName());

    private static final CardStats info = new CardStats(CardColor.GREEN, CardType.ATTACK, CardRarity.RARE, CardTarget.ENEMY, 1);

    public CullTheWeak() {
        super (ID, info);
        this.baseDamage = 8;
        this.baseMagicNumber = 1;
        this.magicNumber = this.baseMagicNumber;

    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new ApplyPowerAction(m, p, new WeakPower(m, this.magicNumber, false), this.magicNumber));
        for (int i = 0; i < m.getPower(WeakPower.POWER_ID).amount + 1; i++) {
            this.addToBot(new DamageAction(m, new DamageInfo(p, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.BLUNT_HEAVY));
        }
    }

    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeDamage(2);
        }
    }


    @Override
    public AbstractCard makeCopy() {
        return new CullTheWeak();
    }
}
