package reddit_grab_bag.relics;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.relics.AbstractRelic;

import static reddit_grab_bag.RedditGrabBagMod.makeID;

public class FountainOfYouth extends BaseRelic{

    private static final String NAME = "FountainOfYouth";
    public static final String ID = makeID(NAME);
    private static final RelicTier RARITY = RelicTier.BOSS;
    private static final LandingSound SOUND = LandingSound.MAGICAL;

    public FountainOfYouth() {
        super(ID, NAME, AbstractCard.CardColor.COLORLESS, RARITY, SOUND);
    }

    public void onEquip() {
        ++AbstractDungeon.player.energy.energyMaster;
        AbstractDungeon.player.decreaseMaxHealth(35);
    }

    public void onUnequip() {
        --AbstractDungeon.player.energy.energyMaster;
    }

    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }

    public AbstractRelic makeCopy() {
        return new FountainOfYouth();
    }

}
