package reddit_grab_bag.relics;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.megacrit.cardcrawl.relics.Sozu;
import com.megacrit.cardcrawl.vfx.ObtainPotionEffect;

import static reddit_grab_bag.RedditGrabBagMod.makeID;

public class BileVessel extends BaseRelic{

    private static final String NAME = "BileVessel";
    public static final String ID = makeID(NAME);
    private static final AbstractRelic.RelicTier RARITY = RelicTier.UNCOMMON;
    private static final AbstractRelic.LandingSound SOUND = LandingSound.CLINK;

    public BileVessel() {
        super(ID, NAME, AbstractCard.CardColor.COLORLESS, RARITY, SOUND);
    }

    public void onRest() {
        if (AbstractDungeon.player.hasRelic(Sozu.ID)) {
            AbstractDungeon.player.getRelic(Sozu.ID).flash();
        } else {
            for(int i = 0; i < AbstractDungeon.player.potionSlots; ++i) {
                AbstractDungeon.effectsQueue.add(new ObtainPotionEffect(AbstractDungeon.returnRandomPotion()));
            }
        }
    }

    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }

    public AbstractRelic makeCopy() {
        return new BileVessel();
    }
}
