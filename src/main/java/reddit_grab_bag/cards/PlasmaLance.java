package reddit_grab_bag.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.combat.MindblastEffect;
import reddit_grab_bag.actions.PlasmaLanceCostAction;
import reddit_grab_bag.util.CardStats;

public class PlasmaLance extends BaseCard {
    public static final String ID = makeID(PlasmaLance.class.getSimpleName());

    private static final CardStats info = new CardStats(CardColor.COLORLESS, CardType.ATTACK, CardRarity.RARE, CardTarget.ALL_ENEMY, 10);

    public PlasmaLance() {
        super (ID, info);
        this.baseDamage = 20;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new SFXAction("ATTACK_HEAVY"));
        this.addToBot(new VFXAction(p, new MindblastEffect(p.dialogX, p.dialogY, p.flipHorizontal), 0.1F));
        this.addToBot(new DamageAllEnemiesAction(p, this.baseDamage, this.damageTypeForTurn, AbstractGameAction.AttackEffect.NONE));
    }

    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeDamage(6);
            this.upgradeBaseCost(9);
        }
    }

    public void checkAndSetCost() {
        this.addToBot(new PlasmaLanceCostAction());
    }

    @Override
    public AbstractCard makeCopy() {
        return new PlasmaLance();
    }
}
