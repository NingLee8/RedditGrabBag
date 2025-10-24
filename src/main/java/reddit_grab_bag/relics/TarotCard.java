package reddit_grab_bag.relics;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.StrengthPower;
import com.megacrit.cardcrawl.relics.AbstractRelic;

import static reddit_grab_bag.RedditGrabBagMod.makeID;

public class TarotCard extends BaseRelic {

    private static final String NAME = "TarotCard";
    public static final String ID = makeID(NAME);
    private static final RelicTier RARITY = RelicTier.RARE;
    private static final LandingSound SOUND = LandingSound.MAGICAL;
    private final AbstractPlayer p;

    public TarotCard() {
        super(ID, NAME, AbstractCard.CardColor.PURPLE, RARITY, SOUND);
        this.p = AbstractDungeon.player;

    }

    // Implemented in MindWipePatch
    public void onTrigger() {
        this.addToBot(new ApplyPowerAction(p, p, new StrengthPower(p, 1), 1));
        this.addToBot(new RelicAboveCreatureAction(p, this));
    }

    public AbstractRelic makeCopy() {
        return new TarotCard();
    }

    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }
}
