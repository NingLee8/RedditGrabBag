package reddit_grab_bag.cards;


import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.actions.common.ExhaustAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import reddit_grab_bag.util.CardStats;

public class CursedFlash extends BaseCard{

    public static final String ID = makeID(CursedFlash.class.getSimpleName());

    private static final CardStats info = new CardStats(CardColor.RED, CardType.ATTACK, CardRarity.RARE, CardTarget.ALL_ENEMY, 0);

    public CursedFlash() {
        super (ID, info);
        this.baseDamage = 14;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new ExhaustAction(1, false));
        this.addToBot(new DamageAllEnemiesAction(p, this.multiDamage, this.damageTypeForTurn, AbstractGameAction.AttackEffect.SLASH_DIAGONAL));
    }

    public boolean canUse(AbstractPlayer p, AbstractMonster m) {
        boolean canUse = super.canUse(p, m);
        if (!canUse) {
            return false;
        }
        else {
            for(AbstractCard c : p.hand.group) {
                if (c.type.equals(CardType.CURSE)) {
                    return true;
                }
            }
        }
        this.cantUseMessage = cardStrings.EXTENDED_DESCRIPTION[0];
        return false;
    }

    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeDamage(4);
        }
    }

    @Override
    public AbstractCard makeCopy() {
        return new CursedFlash();
    }
}
