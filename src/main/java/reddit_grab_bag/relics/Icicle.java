package reddit_grab_bag.relics;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.DamageRandomEnemyAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.orbs.AbstractOrb;
import com.megacrit.cardcrawl.orbs.Frost;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.megacrit.cardcrawl.vfx.combat.BlizzardEffect;

import static reddit_grab_bag.RedditGrabBagMod.makeID;

public class Icicle extends BaseRelic{

    private static final String NAME = "Icicle";
    public static final String ID = makeID(NAME);
    private static final AbstractRelic.RelicTier RARITY = RelicTier.UNCOMMON;
    private static final AbstractRelic.LandingSound SOUND = LandingSound.CLINK;

    public Icicle() {
        super(ID, NAME, AbstractCard.CardColor.BLUE, RARITY, SOUND);
    }

    public void onEvokeOrb(AbstractOrb ammo) {
        if (ammo.ID.equals(Frost.ORB_ID)) {
            if (Settings.FAST_MODE) {
                this.addToBot(new VFXAction(new BlizzardEffect(1, AbstractDungeon.getMonsters().shouldFlipVfx()), 0.25F));
            } else {
                this.addToBot(new VFXAction(new BlizzardEffect(1, AbstractDungeon.getMonsters().shouldFlipVfx()), 1.0F));
            }
            this.addToBot(new DamageRandomEnemyAction(new DamageInfo(AbstractDungeon.player, 3, DamageInfo.DamageType.THORNS), AbstractGameAction.AttackEffect.BLUNT_LIGHT));
        }
    }

    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }

    public AbstractRelic makeCopy() {
        return new Icicle();
    }
}
