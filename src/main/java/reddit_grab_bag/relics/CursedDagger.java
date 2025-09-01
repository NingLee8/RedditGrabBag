package reddit_grab_bag.relics;

import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.actions.common.SpawnMonsterAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.monsters.beyond.AwakenedOne;
import com.megacrit.cardcrawl.monsters.beyond.Reptomancer;
import com.megacrit.cardcrawl.monsters.beyond.SnakeDagger;
import com.megacrit.cardcrawl.monsters.city.BronzeAutomaton;
import com.megacrit.cardcrawl.monsters.city.GremlinLeader;
import com.megacrit.cardcrawl.monsters.ending.SpireShield;
import com.megacrit.cardcrawl.monsters.ending.SpireSpear;
import com.megacrit.cardcrawl.powers.MinionPower;
import com.megacrit.cardcrawl.relics.AbstractRelic;

import java.util.Arrays;

import static reddit_grab_bag.RedditGrabBagMod.makeID;

public class CursedDagger extends BaseRelic{

    private static final String NAME = "CursedDagger";
    public static final String ID = makeID(NAME);
    private static final AbstractRelic.RelicTier RARITY = AbstractRelic.RelicTier.BOSS;
    private static final AbstractRelic.LandingSound SOUND = AbstractRelic.LandingSound.MAGICAL;

    // Different positioning rules to make sure summoned minions don't collide.
    private static final String[] SUMMONERS = {Reptomancer.ID, BronzeAutomaton.ID, GremlinLeader.ID};
    private static final String[] SPECIAL = {AwakenedOne.ID, SpireSpear.ID, SpireShield.ID};

    public CursedDagger() {
        super(ID, NAME, AbstractCard.CardColor.COLORLESS, RARITY, SOUND);
    }

    public void onEquip() {
        ++AbstractDungeon.player.energy.energyMaster;
        this.counter = 0;
    }

    public void onUnequip() {
        --AbstractDungeon.player.energy.energyMaster;
    }

    public void atTurnStart() {
        if (this.counter == -1) {
            this.counter += 2;
        } else {
            ++this.counter;
        }

        if (this.counter == 3) {
            this.counter = 0;
            this.flash();
            this.addToTop(new RelicAboveCreatureAction(AbstractDungeon.player, this));
            float x = Float.POSITIVE_INFINITY;
            float y = Float.NEGATIVE_INFINITY;
            float w = 0;
            float h = 0;
            boolean isSummoner = false;
            String foundSpecial = null;
            for (AbstractMonster m : AbstractDungeon.getMonsters().monsters) {
                if (!m.isDead && !m.isDying && !m.hasPower(MinionPower.POWER_ID)) {
                    float temp = x;
                    x = Math.min(x, m.drawX);
                    if (Arrays.stream(SPECIAL).anyMatch(s -> s.equals(m.id))) {
                        foundSpecial = m.id;
                        x = m.drawX;
                    }
                    if (temp != x || foundSpecial != null) {
                        y = Math.max(y, m.drawY);
                        w = m.hb_w;
                        h = m.hb_h;
                        isSummoner = Arrays.stream(SUMMONERS).anyMatch(s -> s.equals(m.id));
                        if (foundSpecial != null) {
                            break;
                        }
                    }
                }
            }

            float calcX = calcX(x);
            float calcY = calcY(y);
            float finalX;
            float finalY;

            if (foundSpecial != null) {
                if (foundSpecial.equals(AwakenedOne.ID)) {
                    finalX = calcX - w - 120f;
                    finalY = calcY + h + 100f;
                }
                else { // SpireShield or SpireShield, spawn relative to player.
                    // Ignore Surrounded rules /shrug
                    finalX = calcX(AbstractDungeon.player.drawX) + 200f;
                    finalY = calcY(AbstractDungeon.player.drawY) + 200f;
                }
            } else {
                finalX = isSummoner ? calcX - w - 220f : calcX - w - 120f;
                finalY = isSummoner ? calcY + h : calcY + h/2 + 50f;
            }

            SnakeDagger daggerToSpawn = new SnakeDagger(finalX , finalY);
            daggerToSpawn.setMove((byte) 1, AbstractMonster.Intent.ATTACK_DEBUFF, 9);
            daggerToSpawn.maxHealth = 20;
            daggerToSpawn.currentHealth = 20;
            daggerToSpawn.healthBarUpdatedEvent();
            daggerToSpawn.createIntent();
            AbstractDungeon.actionManager.addToTop(new SpawnMonsterAction(daggerToSpawn, true));
        }
    }

    private float calcX(float x) {
        return (x - (Settings.WIDTH * 0.75F)) / Settings.xScale;
    }

    private float calcY(float y) {
        return (y / Settings.yScale) - AbstractDungeon.floorY;
    }

    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }

    public AbstractRelic makeCopy() {
        return new CursedDagger();
    }

}
