package reddit_grab_bag.relics;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.VulnerablePower;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.megacrit.cardcrawl.relics.MercuryHourglass;

import static reddit_grab_bag.RedditGrabBagMod.makeID;

public class StepStool extends BaseRelic{

    private static final String NAME = "StepStool";
    public static final String ID = makeID(NAME);
    private static final RelicTier RARITY = RelicTier.UNCOMMON;
    private static final LandingSound SOUND = LandingSound.HEAVY;

    public StepStool() {
        super(ID, NAME, AbstractCard.CardColor.GREEN, RARITY, SOUND);
    }

    @Override
    public void onPlayCard(AbstractCard c, AbstractMonster m) {
        if (c.type == AbstractCard.CardType.ATTACK) {
            if (m == null) {
                for (AbstractMonster monster:AbstractDungeon.getMonsters().monsters) {
                    this.checkAndDealDamage(monster);
                }
            }
            else {
                this.checkAndDealDamage(m);
            }
        }
    }

    private void checkAndDealDamage(AbstractMonster m) {
        if (m.currentHealth > AbstractDungeon.player.currentHealth) {
            this.addToBot(new RelicAboveCreatureAction(m, this));
            this.addToBot(new DamageAction(m, new DamageInfo(null, 2, DamageInfo.DamageType.THORNS), AbstractGameAction.AttackEffect.BLUNT_LIGHT));        }
    }

    public AbstractRelic makeCopy() {
        return new StepStool();
    }

    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }

}
