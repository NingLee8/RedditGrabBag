package reddit_grab_bag.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.combat.*;
import com.megacrit.cardcrawl.vfx.stance.StanceChangeParticleGenerator;
import reddit_grab_bag.util.CardStats;

public class MindWipe extends BaseCard{

    public static final String ID = makeID(MindWipe.class.getSimpleName());

    private static final CardStats info = new CardStats(CardColor.PURPLE, CardType.ATTACK, CardRarity.RARE, CardTarget.ALL_ENEMY, -2);

    public MindWipe() {
        super(ID, info);
        this.baseDamage = 15;
        this.isMultiDamage = true;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {}

    public boolean canUse(AbstractPlayer p, AbstractMonster m) {
        this.cantUseMessage = cardStrings.EXTENDED_DESCRIPTION[0];
        return false;
    }

    public void onScryDiscard() {
        AbstractPlayer p = AbstractDungeon.player;
        this.addToBot(new SFXAction("POWER_MANTRA", 0.1F));
        if (Settings.FAST_MODE) {
            addToBot(new VFXAction(p, new ShockWaveEffect(p.hb.cX, p.hb.cY, Settings.PURPLE_COLOR, ShockWaveEffect.ShockWaveType.NORMAL), 0.3F));
        } else {
            addToBot(new VFXAction(p, new ShockWaveEffect(p.hb.cX, p.hb.cY, Settings.PURPLE_COLOR, ShockWaveEffect.ShockWaveType.NORMAL), 1.5F));
        }
        for (AbstractMonster m : (AbstractDungeon.getMonsters()).monsters) {
            if (!m.isDeadOrEscaped()) {
                addToBot(new VFXAction(new ThirdEyeEffect(m.hb.cX, m.hb.cY), 0.3F));
            }
        }
        this.addToBot(new DamageAllEnemiesAction(p, this.baseDamage, this.damageTypeForTurn, AbstractGameAction.AttackEffect.NONE));
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            upgradeDamage(5);
        }
    }

    @Override
    public AbstractCard makeCopy() {
        return new MindWipe();
    }
}
