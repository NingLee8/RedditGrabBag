package reddit_grab_bag.relics;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import com.megacrit.cardcrawl.vfx.RainingGoldEffect;

import static reddit_grab_bag.RedditGrabBagMod.makeID;

public class BloodMoney extends BaseRelic {


    private static final String NAME = "BloodMoney";
    public static final String ID = makeID(NAME);
    private static final RelicTier RARITY = RelicTier.UNCOMMON;
    private static final LandingSound SOUND = LandingSound.CLINK;

    public BloodMoney() {
        super(ID, NAME, AbstractCard.CardColor.COLORLESS, RARITY, SOUND);
    }

    @Override
    public void onLoseHp(int damageAmount) {
        if (damageAmount > 0) {
            this.flash();
            if (AbstractDungeon.getCurrRoom().phase.equals(AbstractRoom.RoomPhase.COMBAT)) {
                AbstractDungeon.effectList.add(new RainingGoldEffect(damageAmount * 2, true));
            }
            // Intentional do not use play.gainGOld to prevent BloodyIdol interaction
            CardCrawlGame.goldGained += damageAmount;
            AbstractDungeon.player.gold += damageAmount;
        }
    }

    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }

}
