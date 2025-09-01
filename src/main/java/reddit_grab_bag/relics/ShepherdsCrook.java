package reddit_grab_bag.relics;

import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.tempCards.Miracle;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.megacrit.cardcrawl.rooms.AbstractRoom;

import static reddit_grab_bag.RedditGrabBagMod.makeID;

public class ShepherdsCrook extends BaseRelic {

    private static final String NAME = "ShepherdsCrook";
    public static final String ID = makeID(NAME);
    private static final RelicTier RARITY = RelicTier.UNCOMMON;
    private static final LandingSound SOUND = LandingSound.MAGICAL;

    public ShepherdsCrook() {
        super(ID, NAME, AbstractCard.CardColor.PURPLE, RARITY, SOUND);
        this.pulse = false;
    }

    public void atTurnStart() {
        if (this.pulse) {
            this.pulse = false;
            this.flash();
            this.addToBot(new MakeTempCardInHandAction(new Miracle(), 1, false));
        }
    }

    public void wasHPLost(int damageAmount) {
        if (AbstractDungeon.getCurrRoom().phase == AbstractRoom.RoomPhase.COMBAT && damageAmount > 0) {
            this.flash();
            if (!this.pulse) {
                this.beginPulse();
                this.pulse = true;
            }
        }

    }

    public void onVictory() {
        this.pulse = false;
    }

    public AbstractRelic makeCopy() {
        return new ShepherdsCrook();
    }

    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }
}
