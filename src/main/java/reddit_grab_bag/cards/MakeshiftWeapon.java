package reddit_grab_bag.cards;

import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInDiscardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.combat.VerticalAuraEffect;
import reddit_grab_bag.util.CardStats;

public class MakeshiftWeapon extends BaseCard {

    public static final String ID = makeID(MakeshiftWeapon.class.getSimpleName());

    private static final CardStats info = new CardStats(CardColor.RED, CardType.ATTACK, CardRarity.COMMON, CardTarget.ENEMY, 1);

    public MakeshiftWeapon() {
        super (ID, info);
        this.baseDamage = 9;
        this.exhaust = true;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new DamageAction(m, new DamageInfo(p, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.BLUNT_HEAVY));
        this.addToBot(new VFXAction(p, new VerticalAuraEffect(Color.FIREBRICK, p.hb.cX, p.hb.cY), 0.0F));
        this.addToBot(new MakeTempCardInDiscardAction(this.makeStatEquivalentCopy(), 1));
    }

    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeDamage(4);
        }
    }

    @Override
    public AbstractCard makeCopy() {
        return new MakeshiftWeapon();
    }
}
