package reddit_grab_bag.relics;

import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.megacrit.cardcrawl.rooms.AbstractRoom;

import static reddit_grab_bag.RedditGrabBagMod.makeID;

public class PocketTorch extends BaseRelic{

    private static final String NAME = "PocketTorch";
    public static final String ID = makeID(NAME);
    private static final RelicTier RARITY = RelicTier.COMMON;
    private static final LandingSound SOUND = LandingSound.CLINK;
    private static boolean usedThisCombat = false;

    public PocketTorch() {
        super(ID, NAME, AbstractCard.CardColor.COLORLESS, RARITY, SOUND);
    }
    public void atPreBattle() {
        usedThisCombat = false;
        this.pulse = true;
        this.beginPulse();
    }

    public void onAfterUseCard() {
        this.checkAndTrigger();
    }

    public void onAfterPotionUse() {
        this.checkAndTrigger();
    }

    private void checkAndTrigger() {
        if (!usedThisCombat && AbstractDungeon.player.hand.size() == 1) {
            this.pulse = false;
            this.addToBot(new RelicAboveCreatureAction(AbstractDungeon.player, this));
            this.addToBot(new GainEnergyAction(2));
            usedThisCombat = true;
            this.grayscale = true;
        }
    }

    public void justEnteredRoom(AbstractRoom room) {
        this.grayscale = false;
    }

    public void onVictory() {
        this.pulse = false;
    }

    public AbstractRelic makeCopy() {
        return new PocketTorch();
    }

    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }

}
