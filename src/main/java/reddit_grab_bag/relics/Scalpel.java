package reddit_grab_bag.relics;

import com.evacipated.cardcrawl.mod.stslib.relics.OnRemoveCardFromMasterDeckRelic;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.relics.AbstractRelic;

import static reddit_grab_bag.RedditGrabBagMod.makeID;

public class Scalpel extends BaseRelic implements OnRemoveCardFromMasterDeckRelic {

    private static final String NAME = "Scalpel";
    public static final String ID = makeID(NAME);
    private static final AbstractRelic.RelicTier RARITY = RelicTier.RARE;
    private static final AbstractRelic.LandingSound SOUND = LandingSound.FLAT;

    public Scalpel() {
        super(ID, NAME, AbstractCard.CardColor.COLORLESS, RARITY, SOUND);
    }

    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }

    public AbstractRelic makeCopy() {
        return new Scalpel();
    }

    @Override
    public void onRemoveCardFromMasterDeck(AbstractCard abstractCard) {
        AbstractDungeon.player.increaseMaxHp( 2, true);

    }
}
